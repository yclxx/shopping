package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.util.Date;


/**
 * 奖励发放记录视图对象
 *
 * @author yzg
 * @date 2023-10-18
 */
@Data
@ExcelIgnoreUnannotated
public class MarketLogVo {

    private static final long serialVersionUID = 1L;

    private Long logId;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     *
     */
    private Long marketId;

    /**
     * 用户id
     */
    @ExcelProperty(value = "用户id")
    private Long userId;

    private String status;
    /**
     * 领取时间
     */
    @ExcelProperty(value = "领取时间")
    private Date receiveDate;

    /**
     * 奖励类型
     */
    @ExcelProperty(value = "奖励类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "mission_award_type")
    private String rewardType;

    /**
     * 商品id
     */
    @ExcelProperty(value = "商品id")
    private Long productId;

    /**
     * 优惠券id
     */
    @ExcelProperty(value = "优惠券id")
    private Long couponId;

    /**
     * 支持端
     */
    @ExcelProperty(value = "支持端")
    private String supportChannel;


}
