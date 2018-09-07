package yuanjun.chen.disruptor.queuehelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;
import yuanjun.chen.disruptor.exceptions.MyHandlerException;
import yuanjun.chen.disruptor.model.ValueWrapper;

/**
 * Lmax.disruptor 高效队列处理模板. 支持初始队列，即在init()前进行发布。
 * 其实可以具体化这个类，避免不必要的泛型带来的麻烦
 * 调用init()时才真正启动线程开始处理 系统退出自动清理资源.
 *
 * @author xielongwang
 * @create 2018-01-18 下午3:49
 * @email xielong.wang@nvr-china.com
 * @description
 */
public abstract class BaseQueueHelper<D, E extends ValueWrapper<D>, H extends WorkHandler<E>> {
    private static final Logger logger = LogManager.getLogger(BaseQueueHelper.class);
    
    /** 记录所有的队列，系统退出时统一清理资源. */
    private static List<BaseQueueHelper<?, ?, ?>> queueHelperList = new ArrayList<>();
    /** Disruptor 对象. */
    private Disruptor<E> disruptor;
    /** RingBuffer. */
    private RingBuffer<E> ringBuffer;
    /** InitQueue. */
    private List<D> initQueue = new ArrayList<>();

    /**
     * 队列大小.
     *
     * @return 队列长度，必须是2的幂
     */
    protected abstract int getQueueSize();

    /**
     * 事件工厂.
     *
     * @return EventFactory
     */
    protected abstract MyEventFactory eventFactory();

    /**
     * 事件消费者.
     *
     * @return WorkHandler[]
     */
    protected abstract WorkHandler<E>[] getHandler();

    /** 初始化. */
    @SuppressWarnings("unchecked")
    public void init() {
        ThreadFactory namedThreadFactory = Executors.defaultThreadFactory();
        disruptor = new Disruptor<E>((EventFactory<E>) eventFactory(), getQueueSize(), namedThreadFactory,
                ProducerType.SINGLE, getStrategy());
        disruptor.setDefaultExceptionHandler(new MyHandlerException());
        EventHandlerGroup<E> handleGroup = disruptor.handleEventsWithWorkerPool(getHandler());
        handleGroup.then(new EventHandler<E>() {
            @Override
            public void onEvent(E event, long sequence, boolean endOfBatch) throws Exception {
                logger.info("Finished-" + event.getValue());
            }
        });
        ringBuffer = disruptor.start();

        // 初始化数据发布
        for (D data : initQueue) {
            ringBuffer.publishEvent(new EventTranslatorOneArg<E, D>() {
                @Override
                public void translateTo(E event, long sequence, D data) {
                    event.setValue(data);
                }
            }, data);
        }

        // 加入资源清理钩子？？？
        synchronized (queueHelperList) {
            if (!queueHelperList.isEmpty()) {
                Runtime.getRuntime().addShutdownHook(new Thread() {
                    @Override
                    public void run() {
                        for (BaseQueueHelper<?, ?, ?> baseQueueHelper : queueHelperList) {
                            System.out.println("starting kill " + baseQueueHelper);
                            baseQueueHelper.shutdown();
                        }
                    }
                });
            }
            queueHelperList.add(this);
        }
    }

    /**
     * 如果要改变线程执行优先级，override此策略. YieldingWaitStrategy会提高响应并在闲时占用70%以上CPU，
     * 慎用SleepingWaitStrategy会降低响应更减少CPU占用，用于日志等场景.
     *
     * @return WaitStrategy
     */
    protected abstract WaitStrategy getStrategy();

    /** 插入队列消息，支持在对象init前插入队列，则在队列建立时立即发布到队列处理. */
    public synchronized void publishEvent(D data) {
        if (ringBuffer == null) {
            initQueue.add(data);
            return;
        }
        ringBuffer.publishEvent(new EventTranslatorOneArg<E, D>() {
            @Override
            public void translateTo(E event, long sequence, D data) {
                event.setValue(data);
            }
        }, data);
    }

    /** 关闭队列. */
    public void shutdown() {
        disruptor.shutdown();
    }
}
