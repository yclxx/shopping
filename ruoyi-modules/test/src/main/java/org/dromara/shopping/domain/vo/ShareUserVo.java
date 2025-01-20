package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.core.annotation.Sensitive;
import com.ruoyi.common.core.enums.SensitiveStrategy;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 分销员视图对象
 *
 * @author yzg
 * @date 2023-11-09
 */
@Data
@ExcelIgnoreUnannotated
public class ShareUserVo {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ExcelProperty(value = "用户ID")
    private Long userId;

    /**
     * 商圈名称
     */
    @ExcelProperty(value = "商圈名称")
    private String businessDistrictName;

    /**
     * 品牌名称
     */
    @ExcelProperty(value = "品牌名称")
    private String commercialTenantName;

    /**
     * 门店名称
     */
    @ExcelProperty(value = "门店名称")
    private String shopName;

    /**
     * 用户手机号
     */
    @Sensitive(strategy = SensitiveStrategy.PHONE)
    @ExcelProperty(value = "用户手机号")
    private String userMobile;

    /**
     * 云闪付手机号
     */
    @Sensitive(strategy = SensitiveStrategy.PHONE)
    @ExcelProperty(value = "云闪付手机号")
    private String upMobile;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;

    /**
     * 审核状态
     */
    @ExcelProperty(value = "审核状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "audit_status")
    private String auditStatus;

    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;

    /**
     * 上级ID
     */
    @ExcelProperty(value = "上级ID")
    private Long parentId;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ExcelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 姓名
     */
    @Sensitive(strategy = SensitiveStrategy.NAME)
    @ExcelProperty(value = "姓名")
    private String userName;

    /**
     * 性别
     */
    @ExcelProperty(value = "性别", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sex_type")
    private String sex;

    /**
     * 年龄段
     */
    @ExcelProperty(value = "年龄", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "age_type_list")
    private String ageType;

    /**
     * 扩展推广，0-不开启，1-开启
     */
    private String openExtend;

    /**
     * 页面标题
     */
    private String sharePageTitle;

    /**
     * 推广说明
     */
    private String description;

    /**
     * 商品ID
     */
    private Long productId;

    private ShareUserAccountVo shareUserAccountVo;

    /**
     * 分销奖励：0-随平台，1-自定义比例
     */
    private String shareAmountType;

    /**
     * 分成比例，0只记录
     */
    private BigDecimal shareAmount;

    /**
     * 被动分销奖励
     */
    private BigDecimal passivityShareAmount;

    private String bankBranch;

    private String bankSubBranch;

    private String bankWebsite;
}
