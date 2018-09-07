/**  
 * @Title: DeviceInfoService.java   
 * @Package: yuanjun.chen.disruptor   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author: 陈元俊     
 * @date: 2018年9月7日 下午2:40:01   
 * @version V1.0 
 * @Copyright: 2018 All rights reserved. 
 */
package yuanjun.chen.disruptor.biz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**   
 * @ClassName: DeviceInfoService   
 * @Description: TODO(这里用一句话描述这个类的作用)   
 * @author: 陈元俊 
 * @date: 2018年9月7日 下午2:40:01  
 */
@Service
public class DeviceInfoService {
    private static final Logger logger = LoggerFactory.getLogger(DeviceInfoService.class);
    
    public void processData(String deviceInfoStr) {
        logger.info("Received data---" + deviceInfoStr + " and prepare to Persisit!");
    }
}
