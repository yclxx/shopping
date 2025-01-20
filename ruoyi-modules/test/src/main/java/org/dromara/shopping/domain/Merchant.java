package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商户号对象 t_merchant
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_merchant")
public class Merchant extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 商户名称
     */
    private String merchantName;
    /**
     * 商户号
     */
    private String merchantNo;
    /**
     * 证书地址
     */
    private String certPath;
    /**
     * 证书密码
     */
    private String merchantKey;
    /**
     * 微信apiv3密钥
     */
    private String apiKey;
    /**
     * 商户号类型：0-云闪付，1-微信
     */
    private String merchantType;
    /**
     * 状态（0正常 1停用）
     */
    private String status;
    /**
     * 支付成功回调通知地址
     */
    private String payCallbackUrl;
    /**
     * 退款成功回调通知地址
     */
    private String refundCallbackUrl;
    /**
     * 删除标志
     */
    @TableLogic
    private Long delFlag;

    /**
     * 部门id
     */
    private Long sysDeptId;

    /**
     * 用户id
     */
    private Long sysUserId;
}
