package study.pf.service;

import java.util.Map;

/**
 * Paypal service
 *
 * @author chao
 * @since 2018-04-18
 */
public interface PayPalService {

    /**
     * 校验请求来源
     *
     * @param params request params
     * @see <a href="https://www.paypal.com/en/cgi-bin/webscr?cmd=p/acc/ipn-info-outside">IPN</a>
     */
    boolean webscrNotifyValidate(Map<String, String> params);

    /**
     * paypal web pay
     *
     * @param invoice 订单号
     * @return html
     * @see <a href="https://www.paypal.com/cgi-bin/webscr?cmd=p/pdn/howto_checkout-outside">PayPal结账功能</a>
     */
    String webPayHtml(String uid, String invoice);

    /**
     * paypal web pay notify
     *
     * @param paymentStatus   确认"payment_status"为"Completed",其他结果（如“Pending”或“Failed”）
     * @param receiverEmail PayPal账户
     * @param txnId PayPal交易号
     * @param invoice 订单号
     */
    void webPayNotify(String paymentStatus, String receiverEmail, String txnId, String invoice);
}
