package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.util.Date;



/**
 * 新用户营销视图对象
 *
 * @author yzg
 * @date 2023-10-18
 */
@Data
@ExcelIgnoreUnannotated
public class MarketVo {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long marketId;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     * 名称
     */
    @ExcelProperty(value = "名称")
    private String marketName;
    /**
     * 图片
     */
    @ExcelProperty(value = "图片")
    private String marketImage;

    /**
     * 开始时间
     */
    @ExcelProperty(value = "开始时间")
    private Date beginTime;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态")
    private String status;

    /**
     * 结束时间
     */
    @ExcelProperty(value = "结束时间")
    private Date endTime;

    /**
     * 指定时间
     */
    @ExcelProperty(value = "指定时间")
    private Date dateSpecific;

    /**
     * 天数
     */
    @ExcelProperty(value = "天数")
    private Long marketDay;

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
     * 优惠券批次id
     */
    @ExcelProperty(value = "优惠券批次id")
    private Long actionId;

    /**
     * 支持端
     */
    @ExcelProperty(value = "支持端")
    private String supportChannel;

}
