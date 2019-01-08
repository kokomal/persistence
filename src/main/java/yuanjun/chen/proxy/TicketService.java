package yuanjun.chen.proxy;

/**
 * 售票服务接口 .
 * 
 * @author louluan
 */
public interface TicketService {
    /** 售票. */
    void sellTicket();

    /** 问询. */
    void inquire();

    /** 退票. */
    void withdraw();
}
