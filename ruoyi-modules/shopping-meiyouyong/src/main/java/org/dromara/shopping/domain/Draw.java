package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 奖品管理对象 t_draw
 *
 * @author yzg
 * @date 2023-05-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_draw")
public class Draw extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 奖品ID
     */
    @TableId(value = "draw_id")
    private Long drawId;

    private Long missionGroupId;
    /**
     * 奖品名称
     */
    private String drawName;
    /**
     * 奖品简称
     */
    private String drawSimpleName;
    /**
     * 奖品图片
     */
    private String drawImg;
    /**
     * 中奖概率
     */
    private BigDecimal drawProbability;

    /**
     * 第二套中奖概率
     */
    private BigDecimal secondDrawProbability;


    /**
     * 状态
     */
    private String status;
    /**
     * 奖品类型（0银联票券 1云闪付红包 2云闪付积点）
     */
    private String drawType;
    /**
     * 取码编号
     */
    private String drawNo;
    /**
     * 发放金额
     */
    private BigDecimal sendValue;
    /**
     * 是否能中奖
     */
    private String drawWinning;
    /**
     * 奖品额度
     */
    private BigDecimal drawQuota;
    /**
     * 跳转类型
     */
    private String toType;
    /**
     * 小程序ID
     */
    private String appId;
    /**
     * 页面地址
     */
    private String url;
    /**
     * 展示开始时间
     */
    private Date showStartDate;
    /**
     * 展示结束时间
     */
    private Date showEndDate;
    /**
     * 领取开始时间
     */
    private Date sellStartDate;
    /**
     * 领取结束时间
     */
    private Date sellEndDate;
    /**
     * 总次数，0为不限制
     */
    private Long totalCount;
    /**
     * 每月次数，0为不限制
     */
    private Long monthCount;
    /**
     * 每周次数，0为不限制
     */
    private Long weekCount;
    /**
     * 每日次数，0为不限制
     */
    private Long dayCount;
    /**
     * 用户每日限领次数，0为不限制
     */
    private Long dayUserCount;
    /**
     * 用户每周限领次数，0为不限制
     */
    private Long weekUserCount;
    /**
     * 用户每月限领次数，0为不限制
     */
    private Long monthUserCount;
    /**
     * 用户累计限领次数，0为不限制
     */
    private Long totalUserCount;
    /**
     * 删除标志
     */
    @TableLogic
    private Long delFlag;
    /**
     * 平台标识
     */
    private Long platformKey;
    /**
     * 排序：从小到大
     */
    private Long sort;

    /**
     * 部门id
     */
    private Long sysDeptId;

    /**
     * 用户id
     */
    private Long sysUserId;

    /**
     * 机构账户（介入方代码）
     */
     private String accessCode;

    /**
     *  是否专属活动日奖品
     */
    private String luckyDayDraw;

}
