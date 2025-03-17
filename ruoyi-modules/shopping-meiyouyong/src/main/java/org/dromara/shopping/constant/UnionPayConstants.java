package org.dromara.shopping.constant;

/**
 * 银联分销（供应商）取码常量
 * @author 25487
 */
public interface UnionPayConstants {
    /**
     * 版本号
     */
    String VERSION = "version";
    /**
     * 发送方索引类型 由请求或应答的发送方填写，发送方索引类型与发送方索引标识码唯一标识一个签名索引。
     * 取值：00：银联索引  01：机构索引  02：商户索引
     */
    String APP_TYPE = "appType";
    /**
     * 发送方索引标识码
     * 由银联分配，请求或应答的发送方填写，标识签名验签对应的发送方。
     * 取值：
     * 如发送方索引类型是商户，填写商户代码；
     * 如发送方索引类型是机构，填写机构代码。
     * 如发送方索引类型是银联，填写银联代码（如：00010000）。
     */
    String APP_ID = "appId";
    /**
     * 接口类型
     */
    String BIZ_METHOD = "bizMethod";
    /**
     * 签名
     */
    String SIGN = "sign";
    /**
     * 签名或摘要方式
     * 由请求或应答的发送方填写，用于标识对报文签名或摘要的方式。
     * 取值（推荐使用SM2）：
     * SM2：先使用SM3计算摘要，再使用SM2完成签名
     * RSA2：先使用SHA-256计算摘要，再使用RSA完成签名
     * SM2-CERT：先使用SM3计算摘要，再使用SM2完成签名
     * RSA2-CERT：先使用SHA-256计算摘要，再使用RSA完成签名，
     * <p>
     * 对于用户，仅填写SM2或RSA2。
     * 对于银联，将根据用户入网时选择，填写各取值。如用户不需要在每笔报文中传递银联签名公钥证书（signPubKeyCert），则银联填写SM2或RSA2；
     * 如用户需要在每笔报文中传递银联签名公钥证书（signPubKeyCert），则银联填写SM2-CERT或RSA2-CERT
     */
    String SIGN_METHOD = "signMethod";
    /**
     * 发送方流水号
     * 由请求的发送方自行生成，保证每笔请求不重复，但非交易主键。
     * 同步应答原样返回，异步应答由应答的发送方重新生成新值。
     */
    String REQ_ID = "reqId";
    /**
     * 异步通知标识字段 异步通知用
     */
    String UNION_PAY = "UnionPay";
    /**
     * 银联分销验签公钥系统参数前缀 publicKey+采购商ID组成完整key
     */
    String PUBLIC_KEY = "unionSignPublicKey:";
    /**
     * 银联分销验签私钥系统参数前缀 privateKey+采购商ID组成完整key
     */
    String PRIVATE_KEY = "unionSignPrivateKey:";
    /**
     * 商户号
     */
    String MER_ID = "merId:";
    /**
     * 发券结果通知地址
     */
    String CALLBACK_URL = "callbackUrl:";
}
