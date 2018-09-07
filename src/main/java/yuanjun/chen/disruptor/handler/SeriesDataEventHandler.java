package yuanjun.chen.disruptor.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import com.lmax.disruptor.WorkHandler;
import yuanjun.chen.disruptor.biz.DeviceInfoService;
import yuanjun.chen.disruptor.model.SeriesDataEvent;

public class SeriesDataEventHandler implements WorkHandler<SeriesDataEvent> {
    private Logger logger = LoggerFactory.getLogger(SeriesDataEventHandler.class);

    private int clusterNo;

    /**
     * @param step
     */
    public SeriesDataEventHandler(int clusterNo) {
        super();
        this.clusterNo = clusterNo;
    }

    @Autowired
    private DeviceInfoService deviceInfoService;

    @Override
    public void onEvent(SeriesDataEvent event)  {
        if (event.getValue() == null || StringUtils.isEmpty(event.getValue().getDeviceInfoStr())) {
            logger.warn("receiver series data is empty!");
        }
        biz(clusterNo);
        //业务处理
        deviceInfoService.processData("CLUSTERNO[" + clusterNo + "]" + event.getValue().getDeviceInfoStr());
    }
    
    private void biz(int clusterNo) {
        logger.info("Start proceeding @ CLUSTERNO[" + clusterNo + "]...");
        //blablabla
    }
}