package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

/**
 * 订单卡密视图对象
 *
 * @author yzg
 * @date 2023-05-31
 */
@Data
@ExcelIgnoreUnannotated
public class OrderCardVo {

    private static final long serialVersionUID = 1L;

    /**
     * 订单卡密ID
     */
    @ExcelProperty(value = "订单卡密ID")
    private Long orderCardId;

    /**
     * 订单号
     */
    @ExcelProperty(value = "订单号")
    private Long number;

    /**
     * 明文卡号
     */
    @ExcelProperty(value = "明文卡号")
    private String cardNumber;

    /**
     * 明文密码
     */
    @ExcelProperty(value = "明文密码")
    private String cardPwd;

    /**
     * 卡密有效期
     */
    @ExcelProperty(value = "卡密有效期")
    private String cardDeadline;

    /**
     * 卡券状态: 1 正常；2 作废；3 已核销；4 已过期
     */
    @ExcelProperty(value = "卡券状态: 1 正常；2 作废；3 已核销；4 已过期")
    private String status;

    /**
     * 使用方式：0-兑换码（只需要展示卡密给客户即可）,1-卡号卡密兑换(需要展示卡号加卡密给客户),2-兑换码生成二维码（接入方需将卡密自行生成二维码展示给客户）,3-跳转链接（卡密就是需要跳转的链接，接入方需要跳转至该链接）,4-图片链接（卡密就是图片链接，接入方需在页面展示该图片）
     */
    @ExcelProperty(value = "使用方式：0-兑换码", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "只=需要展示卡密给客户即可")
    private String usedType;

    /**
     * 卡券生效时间
     */
    @ExcelProperty(value = "卡券生效时间")
    private String cardStartTime;

    /**
     * 核销时间
     */
    @ExcelProperty(value = "核销时间")
    private String usedTime;


}
