package yuanjun.chen.disruptor.queuehelper;

import com.lmax.disruptor.EventFactory;
import yuanjun.chen.disruptor.model.SeriesDataEvent;

/**
 * @author chenyuanjun
 * @create 2018-01-18 下午6:24
 * @description
 * @SpecialThanksTo xielongwang
 */
public class MyEventFactory implements EventFactory<SeriesDataEvent> {
    @Override
    public SeriesDataEvent newInstance() {
        return new SeriesDataEvent();
    }
}
