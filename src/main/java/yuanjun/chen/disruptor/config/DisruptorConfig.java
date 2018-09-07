package yuanjun.chen.disruptor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import yuanjun.chen.disruptor.handler.SeriesDataEventHandler;

@Configuration
@ComponentScan({"yuanjun.chen"})
public class DisruptorConfig {

    /**
     * SmsParamEventHandler1.
     *
     * @return SeriesDataEventHandler
     */
    @Bean("seriesDataEventHandler1")
    public SeriesDataEventHandler seriesDataEventHandler1() {
        return new SeriesDataEventHandler(1);
    }

    /**
     * SmsParamEventHandler2.
     *
     * @return SeriesDataEventHandler
     */
    @Bean("seriesDataEventHandler2")
    public SeriesDataEventHandler seriesDataEventHandler2() {
        return new SeriesDataEventHandler(2);
    }

    /**
     * SmsParamEventHandler3.
     *
     * @return SeriesDataEventHandler
     */
    @Bean("seriesDataEventHandler3")
    public SeriesDataEventHandler seriesDataEventHandler3() {
        return new SeriesDataEventHandler(3);
    }

    /**
     * SmsParamEventHandler4.
     *
     * @return SeriesDataEventHandler
     */
    @Bean("seriesDataEventHandler4")
    public SeriesDataEventHandler seriesDataEventHandler4() {
        return new SeriesDataEventHandler(4);
    }

    /**
     * SmsParamEventHandler5.
     *
     * @return SeriesDataEventHandler
     */
    @Bean("seriesDataEventHandler5")
    public SeriesDataEventHandler seriesDataEventHandler5() {
        return new SeriesDataEventHandler(5);
    }
}
