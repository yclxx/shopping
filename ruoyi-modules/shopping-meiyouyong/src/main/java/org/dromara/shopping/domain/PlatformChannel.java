package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 平台渠道配置对象 t_platform_channel
 *
 * @author yzg
 * @date 2023-10-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_platform_channel")
public class PlatformChannel extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 平台标识
     */
    private Long platformKey;
    /**
     * 小程序标题
     */
    private String platformTitle;
    /**
     * appId
     */
    private String appId;
    /**
     * 小程序ID
     */
    private String encryptAppId;
    /**
     * 密钥
     */
    private String secret;
    /**
     * 对称密钥
     */
    private String symmetricKey;
    /**
     * rsa签名私钥
     */
    private String rsaPrivateKey;
    /**
     * rsa签名公钥
     */
    private String rsaPublicKey;
    /**
     * 渠道：0-云闪付，1-微信小程序
     */
    private String channel;
    /**
     * 默认支付商户
     */
    private Long merchantId;

}
