package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 商品券码视图对象
 *
 * @author yzg
 * @date 2023-09-20
 */
@Data
@ExcelIgnoreUnannotated
public class CodeVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 商品ID
     */
    @ExcelProperty(value = "商品ID")
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
    @ExcelProperty(value = "商品名称")
    private String productName;

    /**
     * 场次名称
     */
    @ExcelProperty(value = "场次名称")
    private String productSessionName;

    /**
     * 规格名称
     */
    @ExcelProperty(value = "规格名称")
    private String productSkuName;

    /**
     * 券号
     */
    @ExcelProperty(value = "券号")
    private String codeNo;

    /**
     * 分配状态：0-未分配，1-已分配
     */
    @ExcelProperty(value = "分配状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_code_allocation_state")
    private String allocationState;

    /**
     * 所属订单号
     */
    @ExcelProperty(value = "所属订单")
    private Long number;


    /**
     * 用户所属订单
     */
    private Long userNumber;

    /**
     * 核销状态：0-未核销，1-已核销，2-已失效，3-已作废
     */
    @ExcelProperty(value = "核销状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_code_used_status")
    private String usedStatus;

    /**
     * 券码类型：0-系统券码，1-外部券码
     */
    @ExcelProperty(value = "券码类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_code_type")
    private String codeType;

    /**
     * 核销或作废时间
     */
    @ExcelProperty(value = "核销或作废时间")
    private Date usedTime;

    /**
     * 核销店铺ID
     */
    @ExcelProperty(value = "核销店铺ID")
    private Long shopId;

    /**
     * 核销店铺名称
     */
    @ExcelProperty(value = "核销店铺名称")
    private String shopName;

    /**
     * 核销人员ID
     */
    @ExcelProperty(value = "核销人员ID")
    private Long verifierId;

    /**
     * 核销人员手机号
     */
    @ExcelProperty(value = "核销人员手机号")
    private String verifierMobile;

    /**
     * 二维码图片URL
     */
    private String qrcodeImgUrl;

    /**
     * 预约店铺ID
     */
    @ExcelProperty(value = "预约店铺ID")
    private Long appointmentShopId;

    /**
     * 预约店铺名称
     */
    @ExcelProperty(value = "预约店铺名称")
    private String appointmentShopName;

    /**
     * 预约时间
     */
    @ExcelProperty(value = "预约时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date appointmentDate;

    /**
     * 预约状态：0-未预约，1-已预约，2-已取消
     */
    @ExcelProperty(value = "预约状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "appointment_status")
    private String appointmentStatus;

    @ExcelProperty(value = "短链接")
    private String shortUrl;

    @ExcelProperty(value = "券码来源", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_order_source")
    private String orderSource;


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

    /**
     * 核销数量
     */
    private Long usedCount;
    /**
     * 预约数量
     */
    private Long appointmentCount;
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

    private List<ShopVo> shopVoList = new ArrayList<>();
    private List<AppointmentVo> appointmentVoList;
    /**
     * 组装预约列表的map
     */
    private Map<Date, List<AppointmentVo>> appointmentMap;

    private String addressInfo;

    private String name;

}
