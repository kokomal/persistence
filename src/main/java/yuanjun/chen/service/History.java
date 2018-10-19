/**  
 * @Title: History.java   
 * @Package: yuanjun.chen.mokito   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author: 陈元俊     
 * @date: 2018年10月18日 下午5:02:53   
 * @version V1.0 
 * @Copyright: 2018 All rights reserved. 
 */
package yuanjun.chen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**   
 * @ClassName: History   
 * @Description: TODO(这里用一句话描述这个类的作用)   
 * @author: 陈元俊 
 * @date: 2018年10月18日 下午5:02:53  
 */
@Service
public class History {
    @Autowired
    QinDynasty qin;
    
    public String tellStoryOfQin() {
        return qin.getDeer();
    }
}
