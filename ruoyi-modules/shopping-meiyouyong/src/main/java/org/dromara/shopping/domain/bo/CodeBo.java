package org.dromara.shopping.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotNull;
import java.util.Date;

/**
 * 商品券码业务对象
 *
 * @author yzg
 * @date 2023-09-20
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class CodeBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 场次ID
     */
    private Long productSessionId;

    /**
     * 规格ID
     */
    private Long productSkuId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 场次名称
     */
    private String productSessionName;

    /**
     * 规格名称
     */
    private String productSkuName;

    /**
     * 券号
     */
    private String codeNo;

    /**
     * 分配状态：0-未分配，1-已分配
     */
    private String allocationState;

    /**
     * 所属订单号
     */
    private Long number;

    /**
     * 用户所属订单
     */
    private Long userNumber;

    /**
     * 核销状态：0-未核销，1-已核销，2-已失效，3-已作废
     */
    private String usedStatus;

    /**
     * 券码类型：0-系统券码，1-外部券码
     */
    private String codeType;

    /**
     * 核销或作废时间
     */
    private Date usedTime;
    /**
     * 核销或作废时间 (这两个参数只用作查询条件)
     */
    private String usedTimes;
    private String usedEndTime;

    /**
     * 核销店铺ID
     */
    private Long shopId;

    /**
     * 核销店铺名称
     */
    private String shopName;

    /**
     * 核销人员ID
     */
    private Long verifierId;

    /**
     * 核销人员手机号
     */
    private String verifierMobile;

    /**
     * 二维码图片URL
     */
    private String qrcodeImgUrl;

    /**
     * 预约店铺ID
     */
    private Long appointmentShopId;

    /**
     * 预约店铺名称
     */
    private String appointmentShopName;

    /**
     * 预约时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date appointmentDate;

    /**
     * 预约状态：0-未预约，1-已预约，2-已取消
     */
    private String appointmentStatus;

    /**
     * 预约时间ID
     */
    private Long appointmentId;

    /**
     * 部门id
     */
    private Long sysDeptId;

    /**
     * 用户id
     */
    private Long sysUserId;

    private String beginUsedTime;
    private String endUsedTime;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 客户电话
     */
    private String customMobile;
    /**
     * 短链接
     */
    private String shortUrl;

    /**
     * 导出到的邮箱
     */
    private String linkmanEmail;
}
