package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.util.Date;

/**
 * 银联任务配置视图对象
 *
 * @author yzg
 * @date 2024-02-21
 */
@Data
@ExcelIgnoreUnannotated
public class UnionpayMissionVo {

    private static final long serialVersionUID = 1L;

    /**
     * 银联任务ID
     */
    @ExcelProperty(value = "银联任务ID")
    private Long upMissionId;

    /**
     * 银联任务组ID
     */
    @ExcelProperty(value = "银联任务组ID")
    private Long upMissionGroupId;

    /**
     * 银联任务名称
     */
    @ExcelProperty(value = "银联任务名称")
    private String upMissionName;

    /**
     * 银联任务id
     */
    private String upMissionUpid;

    /**
     * 发放奖励产品id
     */
    @ExcelProperty(value = "发放奖励产品id")
    private Long productId;

    /**
     * 状态  0-正常  1-停用
     */
    @ExcelProperty(value = "状态  0-正常  1-停用", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;

    /**
     * 开始时间
     */
    @ExcelProperty(value = "开始时间")
    private Date startDate;

    /**
     * 结束时间
     */
    @ExcelProperty(value = "结束时间")
    private Date endDate;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     * 用户每日限参与次数
     */
    @ExcelProperty(value = "用户每日限参与次数")
    private Long userCountDay;

    /**
     * 用户每周限参与次数
     */
    @ExcelProperty(value = "用户每周限参与次数")
    private Long userCountWeek;

    /**
     * 用户每月限参与次数
     */
    @ExcelProperty(value = "用户每月限参与次数")
    private Long userCountMonth;

    /**
     * 用户活动期间限参与次数
     */
    @ExcelProperty(value = "用户活动期间限参与次数")
    private Long userCountActivity;

    /**
     * 创建时间
     */
    private Date createTime;

    private ProductVo productVo;

    private String remark;

    /**
     * 交易类型
     */
    private String tranType;

    /**
     * 限制交易数量
     */
    private Long tranCount;

    /**
     * 单笔支付金额
     */
    private Long payAmount;

}
