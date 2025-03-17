package org.dromara.shopping.enumd.UnionPay;

/**
 * 银联接口类型(渠道方)
 */
public enum UnionPayBizMethod {
    CHNLPRODLST("ma.ids.query.chnlprodlst"),//查询商品列表
    PRODDTL("ma.ids.query.proddtl"),//查询商品商品详情
    CHNLPUR("ma.ids.order.chnlpur"),//发券
    CHNLRFD("ma.ids.order.chnlrfd"),//退券
    STQUERY("ma.ids.order.stquery"),//订单状态查询
    CREATE("ma.ids.order.create"),//订单创建
    FRONT("ma.ids.order.pay.front"),//订单支付(前台)
    CANCEL("ma.ids.order.cancel"),//订单取消
    chnlpur("ma.ids.order.pay.chnlpur"),//订单发券
    BONDCANCEL("ma.ids.notify.bondcancel"),//核销通知
    BONDRFD("ma.ids.notify.bondrfd"),// 退券通知
    BONDROLLBACK("ma.ids.notify.bondrollback"),//卡券返还通知
    REFUND("ma.ids.notify.refund"),//退款通知
    CHNLSYN("ma.ids.notify.chnlsyn");// 渠道信息同步通知

    private String bizMethod;

    UnionPayBizMethod(String bizMethod) {
        this.bizMethod = bizMethod;
    }

    public String getBizMethod() {
        return bizMethod;
    }
}
