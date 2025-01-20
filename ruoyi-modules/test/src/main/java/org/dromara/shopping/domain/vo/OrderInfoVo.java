package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.math.BigDecimal;



/**
 * 订单扩展信息视图对象
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@Data
@ExcelIgnoreUnannotated
public class OrderInfoVo {

    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    @ExcelProperty(value = "订单号")
    private Long number;

    /**
     * 62会员状态：00-普通用户 01-会员用户 02-会员冻结用户 03-试用用户
     */
    @ExcelProperty(value = "62会员状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_order_info_vip62_status")
    private String vip62Status;

    /**
     * 62会员类型 00-试用 01-月卡 02-季卡 03-年卡 04-普通用户
     */
    @ExcelProperty(value = "62会员类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_order_info_vip62_member_type")
    private String vip62MemberType;

    /**
     * 62会员到期时间
     */
    @ExcelProperty(value = "62会员到期时间")
    private String vip62EndTime;

    /**
     * 62会员首次开通时间
     */
    @ExcelProperty(value = "62会员首次开通时间")
    private String vip62BeginTime;

    /**
     * （银联支付）订单发送时间：格式YYYYMMDDhhmmss
     */
    private String txnTime;

    /**
     * （银联支付）查询流水号
     */
    private String queryId;

    /**
     * （银联支付）交易传输时间
     */
    private String traceTime;

    /**
     * （银联支付）系统跟踪号
     */
    private String traceNo;

    /**
     * （银联支付）支付金额
     */
    private BigDecimal txnAmt;

    /**
     * （银联支付）订单优惠信息（支持单品）json字符串，详细内容参照银联单品营销issAddnData内容信息
     */
    private String issAddnData;

    /**
     * 商品快照信息json字符串
     */
    private String commodityJson;

    private String payBankType;


}
