package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 微信小程序二维码对象 t_wx_scene
 *
 * @author yzg
 * @date 2024-10-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_wx_scene")
public class WxScene extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * scene内容，传给微信生成二维码的
     */
    @TableId(value = "scene_id")
    private Long sceneId;
    /**
     * 平台标识
     */
    private Long platformKey;
    /**
     * 跳转页面
     */
    private String page;
    /**
     * 真实scene内容
     */
    private String scene;

}
