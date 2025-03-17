package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 核销人员对象 t_verifier
 *
 * @author yzg
 * @date 2023-10-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_verifier")
public class Verifier extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private Long id;
    /**
     * 平台标识
     */
    private Long platformKey;
    /**
     * 名称
     */
    private String username;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 状态 0 正常 1停用
     */
    private String status;
    /**
     * 第三方平台联登唯一标识
     */
    private String openId;
    /**
     * 最后登录IP
     */
    private String loginIp;
    /**
     * 最后登录时间
     */
    private Date loginDate;
    /**
     * 上次登录时间
     */
    private Date lastLoginDate;

    /**
     * 信息授权 (0未授权，1已授权)用于静默登录
     */
    private String reloadUser;
    /**
     * 部门id
     */
    private Long sysDeptId;
    /**
     * 用户id
     */
    private Long sysUserId;
    /**
     * 服务商表id
     */
    private Long extensionServiceProviderId;
    /**
     * 是否BD
     */
    private Boolean isBd;
    /**
     * 是否管理员
     */
    private Boolean isAdmin;
    /**
     * 是否核销人员
     */
    private Boolean isVerifier;
    /**
     * 所处地处
     */
    private String cityCode;
    /**
     * 归属公司
     */
    private String org;
    /**
     * 是否同意合同 （0不同意，1同意）
     */
    private String contract;

    public Verifier() {
    }

    public Verifier(String mobile) {
        this.mobile = mobile;
    }

    private String province;

    private String city;

    private String district;

    private String procode;

    private String citycode;

    private String adcode;


}
