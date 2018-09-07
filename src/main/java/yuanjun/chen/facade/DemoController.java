/**
 * @Title: DemoController.java
 * @Package: yuanjun.chen.facade
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: 陈元俊
 * @date: 2018年8月7日 下午5:23:31
 * @version V1.0
 * @Copyright: 2018 All rights reserved.
 */
package yuanjun.chen.facade;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import yuanjun.chen.dao.mybatis.mapper.UserMapper;
import yuanjun.chen.disruptor.model.DataResponseVo;
import yuanjun.chen.disruptor.model.SeriesData;
import yuanjun.chen.disruptor.queuehelper.SeriesDataEventQueueHelper;

/**
 * @ClassName: DemoController
 * @Description: Controller类
 * @author: 陈元俊
 * @date: 2018年8月7日 下午5:23:31
 */
@RestController
public class DemoController {
    private static final Logger logger = LogManager.getLogger(DemoController.class);

    @Autowired
    UserMapper userMapper;

    @RequestMapping(value = "/zz", method = RequestMethod.GET)
    public ResponseEntity<String> hellozz() {
        return ResponseEntity.badRequest().body("haha");
    }

    @RequestMapping("/index")
    String index2() {
        logger.info("vvv");
        return JSONObject.toJSONString(userMapper.selectAllUser());
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello(@RequestParam String name) {
        return "Hello " + name;
    }

    // 注入SeriesDataEventQueueHelper消息生产者
    @Autowired
    private SeriesDataEventQueueHelper seriesDataEventQueueHelper;

    @RequestMapping(value = "/data", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public DataResponseVo<String> receiverDeviceData(@RequestBody String deviceData) {
        long startTime1 = System.currentTimeMillis();

        if (StringUtils.isEmpty(deviceData)) {
            logger.info("receiver data is empty !");
            return new DataResponseVo<String>(400, "failed");
        }
        seriesDataEventQueueHelper.publishEvent(new SeriesData(deviceData));
        long startTime2 = System.currentTimeMillis();
        logger.info("receiver data ==[{}] millisecond ==[{}]", deviceData, startTime2 - startTime1);
        return new DataResponseVo<String>(200, "success");
    }
}
