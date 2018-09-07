package yuanjun.chen.disruptor.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lmax.disruptor.ExceptionHandler;

public class MyHandlerException implements ExceptionHandler<Object> {
    private Logger logger = LoggerFactory.getLogger(MyHandlerException.class);

    @Override
    public void handleEventException(Throwable ex, long sequence, Object event) {
        ex.printStackTrace();
        logger.error("process data error sequence ==[{}] event==[{}] ,ex ==[{}]", sequence, event.toString(), ex.getMessage());
    }

    @Override
    public void handleOnStartException(Throwable ex) {
        logger.error("start disruptor error ==[{}]!", ex.getMessage());
    }

    @Override
    public void handleOnShutdownException(Throwable ex) {
        logger.error("shutdown disruptor error ==[{}]!", ex.getMessage());
    }
}