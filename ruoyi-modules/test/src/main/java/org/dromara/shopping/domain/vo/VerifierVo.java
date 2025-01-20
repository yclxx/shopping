package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 核销人员视图对象
 *
 * @author yzg
 * @date 2023-10-31
 */
@Data
@ExcelIgnoreUnannotated
public class VerifierVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     * 名称
     */
    @ExcelProperty(value = "名称")
    private String username;

    /**
     * 手机号
     */
    @ExcelProperty(value = "手机号")
    private String mobile;

    /**
     * 状态 0 正常 1停用
     */
    @ExcelProperty(value = "状态")
    private String status;

    /*
     * 人员类型，admin管理员 verifier 核销人员
     */
    //@ExcelProperty(value = "人员类型，admin管理员 verifier 核销人员")
    //private String verifierType;

    /**
     * 第三方平台联登唯一标识
     */
    @ExcelProperty(value = "第三方平台联登唯一标识")
    private String openId;

    /**
     * 最后登录IP
     */
    @ExcelProperty(value = "最后登录IP")
    private String loginIp;

    /**
     * 最后登录时间
     */
    @ExcelProperty(value = "最后登录时间")
    private Date loginDate;
    /**
     * 上次登录时间
     */
    @ExcelProperty(value = "上次登录时间")
    private Date lastLoginDate;

    /**
     * 信息授权
     */
    private String reloadUser;

    /**
     * 部门id
     */
    @ExcelProperty(value = "部门id")
    private Long sysDeptId;

    /**
     * 用户id
     */
    @ExcelProperty(value = "用户id")
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
     * 所处区域
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

    private String province;

    private String city;

    private String district;

    private String procode;

    private String citycode;

    private String adcode;

    private Long[] supplierIds;

    private List<Long> roleIds;
}
