package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 平台渠道配置业务对象
 *
 * @author yzg
 * @date 2023-10-12
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class PlatformChannelBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 平台标识
     */
    @NotNull(message = "平台标识不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long platformKey;

    /**
     * 小程序标题
     */
    @NotBlank(message = "小程序标题不能为空", groups = { AddGroup.class, EditGroup.class })
    private String platformTitle;

    /**
     * appId
     */
    @NotBlank(message = "appId不能为空", groups = { AddGroup.class, EditGroup.class })
    private String appId;

    /**
     * 小程序ID
     */
    @NotBlank(message = "小程序ID不能为空", groups = { AddGroup.class, EditGroup.class })
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
    @NotNull(message = "默认支付商户不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long merchantId;


}
