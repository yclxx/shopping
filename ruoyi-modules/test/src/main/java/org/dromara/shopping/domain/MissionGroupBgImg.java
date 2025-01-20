package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 任务组背景图片配置对象 t_mission_group_bg_img
 *
 * @author yzg
 * @date 2024-01-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_mission_group_bg_img")
public class MissionGroupBgImg extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 任务ID
     */
    @TableId(value = "mission_bg_img_id")
    private Long missionBgImgId;
    /**
     * ID
     */
    private Long missionGroupId;
    /**
     * 状态（0正常 1停用）
     */
    private String status;
    /**
     * 开始时间
     */
    private Date startDate;
    /**
     * 结束时间
     */
    private Date endDate;
    /**
     * 任务图片
     */
    private String missionBgImg;
    /**
     * 平台标识
     */
    private Long platformKey;
    /**
     * 删除标志
     */
    @TableLogic
    private Long delFlag;

}
