package yuanjun.chen.disruptor.queuehelper;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.WorkHandler;
import yuanjun.chen.disruptor.handler.SeriesDataEventHandler;
import yuanjun.chen.disruptor.model.SeriesData;
import yuanjun.chen.disruptor.model.SeriesDataEvent;

@Component
public class SeriesDataEventQueueHelper extends BaseQueueHelper<SeriesData, SeriesDataEvent, SeriesDataEventHandler>
        implements InitializingBean {
    private static final int QUEUE_SIZE = 1024;

    @Autowired
    @Qualifier("seriesDataEventHandler1")
    private SeriesDataEventHandler series1;
    @Autowired
    @Qualifier("seriesDataEventHandler2")
    private SeriesDataEventHandler series2;
    @Autowired
    @Qualifier("seriesDataEventHandler3")
    private SeriesDataEventHandler series3;
    @Autowired
    @Qualifier("seriesDataEventHandler4")
    private SeriesDataEventHandler series4;
    
    @Override
    protected int getQueueSize() {
        return QUEUE_SIZE;
    }

    @Override
    protected MyEventFactory eventFactory() {
        return new MyEventFactory();
    }

    // 这里组pool集群
    @Override
    protected WorkHandler<SeriesDataEvent>[] getHandler() {
        WorkHandler<SeriesDataEvent>[] r = new SeriesDataEventHandler[4];
        r[0] = series1;
        r[1] = series2;
        r[2] = series3;
        r[3] = series4;
        return r;
    }

    @Override
    protected WaitStrategy getStrategy() {
        return new BlockingWaitStrategy();
        // return new YieldingWaitStrategy();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }
}
