/**  
 * @Title: DataResponseVo.java   
 * @Package: yuanjun.chen.facade   
 * @Description: VO  
 * @author: 陈元俊     
 * @date: 2018年9月7日 下午2:49:07   
 * @version V1.0 
 * @Copyright: 2018 All rights reserved. 
 */
package yuanjun.chen.disruptor.model;

/**   
 * @ClassName: DataResponseVo   
 * @Description: VO   
 * @author: 陈元俊 
 * @date: 2018年9月7日 下午2:49:07  
 */
public class DataResponseVo<T> {

    private int responseCode;
    private String responseMsg;
    /**
     * @param i
     * @param string
     */
    public DataResponseVo(int i, String msg) {
        this.responseCode = i;
        this.responseMsg = msg;
    }
    /**
     * @return the responseCode
     */
    public int getResponseCode() {
        return responseCode;
    }
    /**
     * @param responseCode the responseCode to set
     */
    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
    /**
     * @return the responseMsg
     */
    public String getResponseMsg() {
        return responseMsg;
    }
    /**
     * @param responseMsg the responseMsg to set
     */
    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

}
