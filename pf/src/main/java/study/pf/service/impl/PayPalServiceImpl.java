//package study.pf.service.impl;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.http.entity.ContentType;
//import study.pf.service.PayPalService;
//
///**
// * paypal service
// *
// * @author chao
// * @since 2018-04-19
// */
//@Slf4j
//public class PayPalServiceImpl implements PayPalService {
//
//    @Override
//    public boolean webscrNotifyValidate(Map<String, String> params) {
//        return false;
//    }
//
//    @Override
//    public String webPayHtml(String uid, String invoice) {
//        return null;
//    }
//
//    @Override
//    public void webPayNotify(String paymentStatus, String receiverEmail, String txnId, String invoice) {
//
//    }
////
////    /**
////     * PayPal账户
////     */
////    private static String BUSINESS;
////    /**
////     * IPN Sandbox Endpoint: https://www.sandbox.paypal.com/cgi-bin/webscr IPN Live Endpoint: https://www.paypal.com/cgi-bin/webscr
////     */
////    private static String IPN_ENDPOINT;
////
////    /**
////     * 客户完成付款后将返回的互联网 URL
////     */
////    //private static String RETURN;
////
////    /**
////     * 客户取消付款后将返回的互联网 URL
////     */
////    private static String CANCEL_RETURN;
////
////    static {
////        //Prop prop = PropKit.use(BaseConstant.DEFAULT_CONFIG_FILE_NAME);
////        Map<String,String> prop = new HashMap<>();
////        BUSINESS = prop.get("paypal.business");
////        IPN_ENDPOINT = prop.get("paypal.ipn.endpoint");
////        //RETURN = prop.get("paypal.ipn.return");
////        CANCEL_RETURN = prop.get("paypal.ipn.cancelReturn");
////    }
////
////    /**
////     * IPN请求来源校验结果
////     */
////    enum IPNNotifyValidate {
////        VERIFIED, INVALID
////    }
////
////    /**
////     * @return html
////     */
////    @SuppressWarnings("rawtypes")
////	@Override
////    public String webPayHtml(String uid, String invoice) {
////        if (StringUtils.isBlank(invoice)) {
////            throw new BusinessException(APICode.FAIL_PARAM.getCode(), APICode.FAIL_PARAM.getMsg());
////        }
////        Order order = Order.dao.findFirst(BaseConstant.USER_ORDER_SQL, uid, invoice);
////        if (order == null) {
////            throw new BusinessException(APICode.ORDER_NOT_EXISTS.getCode(), APICode.ORDER_NOT_EXISTS.getMsg());
////        }
////        if (!order.get("unit").equals(3)) {
////            throw new BusinessException(APICode.ORDER_PAY_WAY_ERROR.getCode(), APICode.ORDER_PAY_WAY_ERROR.getMsg());
////        }
////
////        /*
////         *  html
////         */
////        StringBuilder sb = new StringBuilder();
////        sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
////        sb.append("<form id=\"pay_form\" name=\"pay_form\" action=\"");
////        sb.append(IPN_ENDPOINT);
////        sb.append("\" method=\"post\" accept-charset=\"utf-8\" onsubmit=\"document.charset='utf-8';\">");
////
////        Map<String, String> map = new HashMap<>();
////        //按钮类型，包含：_xclick单个商品立即购买、_xclick_subscription订阅、_cart购物车、s_x-click加密
////        map.put("cmd", "_xclick");
////        map.put("business", BUSINESS);
////        map.put("cancel_return", CANCEL_RETURN);
////        //map.put("return", RETURN);
////        //货币类型，默认美元
////        map.put("currency_code", "USD");
////        //商品名称
////        map.put("item_name", order.getStr("pro_title"));
////        //物品号
////        map.put("item_number", order.getStr("pid"));
////        //数量
////        map.put("quantity", order.getStr("num"));
////        // 订单号
////        map.put("invoice", invoice);
////        //支付金额
////        map.put("amount", order.getStr("price"));
////        //通知地址
////        //map.put("notify_url", "https://www.shark.gold/api/other/paypalWebPayNotify");
////
////        for (Map.Entry entry : map.entrySet()) {
////            sb.append("<input type=\"hidden\" name=\"");
////            sb.append(entry.getKey());
////            sb.append("\" value=\"");
////            sb.append(entry.getValue());
////            sb.append("\">");
////        }
////
////        sb.append("<script language=\"javascript\">document.pay_form.submit();</script>");
////        return sb.toString();
////    }
////
////    /**
////     * 校验请求来源
////     *
////     * @param params request params
////     */
////    @Override
////    public boolean webscrNotifyValidate(Map<String, String> params) {
////        if (params == null) {
////            return false;
////        }
////        params.put("cmd", "_notify-validate");
////
////        String result = HttpUtils.doHttpsPost(IPN_ENDPOINT, params,
////                ContentType.APPLICATION_FORM_URLENCODED, BaseConstant.UTF_8);
////
////        if (IPNNotifyValidate.VERIFIED.name().equals(result)) {
////            return true;
////        } else if (IPNNotifyValidate.INVALID.name().equals(result)) {
////            log.error("not from paypal request");
////            return false;
////        } else {
////            log.error("validate request fail");
////            return false;
////        }
////    }
////
////    /**
////     * paypal web pay notify
////     *
////     * @param paymentStatus 确认"payment_status"为"Completed",其他结果（如“Pending”或“Failed”）
////     * @param receiverEmail PayPal账户
////     * @param txnId PayPal交易号
////     * @param invoice 订单号
////     */
////    @Before(Tx.class)
////    @Override
////    public void webPayNotify(String paymentStatus, String receiverEmail, String txnId, String invoice) {
////        log.info("webPayNotify:payment_status[" + paymentStatus + "],receiver_email[" + receiverEmail + "],txn_id[" + txnId + "],invoice["
////                + invoice + "]");
////        if (StringUtils.isBlank(paymentStatus) || StringUtils.isBlank(receiverEmail) || StringUtils.isBlank(txnId) || StringUtils
////                .isBlank(invoice)) {
////            return;
////        }
////        if ("Completed".equals(paymentStatus)) {
////            if (BUSINESS.equals(receiverEmail)) {
////                // txnId 是否重复校验
////                Order order = Order.dao.findFirst("select * from sys_order where txn_id=?", txnId);
////                if (order == null) {
////                    /*
////                     * 更新订单
////                     */
////                    order = Order.dao.findFirst("select * from sys_order where order_no = ?", invoice);
////                    if (order != null && order.get("status") != null && order.get("status").equals(OrderStatus.PRE_PAYMENT.getCode())) {
////                        order.set("txn_id", txnId).set("status", OrderStatus.PAID.getCode()).set("pay_time", new Date()).update();
////                        reduceProductNum(order.getLong("pid"),order.getInt("ptype"),order.getInt("num"));
////                    }
////                } else if (invoice.equals(order.get("order_no"))) {
////                    if (order.get("status") != null && order.get("status").equals(OrderStatus.PRE_PAYMENT.getCode())) {
////                        order.set("txn_id", txnId).set("status", OrderStatus.PAID.getCode()).set("pay_time", new Date()).update();
////                        reduceProductNum(order.getLong("pid"),order.getInt("ptype"),order.getInt("num"));
////                    }
////                }
////            }
////        }
////    }
////
////    /**
////     * 减少产品剩余数量
////     *
////     * @param pid  pid
////     * @param ptype 1 算力  2 矿机
////     * @param num 数量
////     */
////    private void reduceProductNum(long pid, int ptype, int num) {
////        if (ptype == 1) {
////            Db.update("update power set remain_num=remain_num-? where id= ?", num, pid);
////        } else if (ptype == 2) {
////            Db.update("update machine set remain_num=remain_num-? where id= ?", num, pid);
////        }
////    }
//
//}
