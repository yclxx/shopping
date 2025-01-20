package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 页面配置业务对象
 *
 * @author yzgnet
 * @date 2023-03-21
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class PageSettingBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 页面标识
     */
    @NotBlank(message = "页面标识不能为空", groups = { AddGroup.class, EditGroup.class })
    private String pagePath;

    /**
     * 支持功能：0-顶部广告,1-icon,2-长图轮播，3-腰部广告(两张)，4-腰部广告(三张)，5-浮框，6-弹窗，7-银行专属优惠，99-自定义版块
     */
    @NotBlank(message = "支持功能不能为空", groups = { AddGroup.class, EditGroup.class })
    private String bannerType;

    /**
     * 版块模板编号，自定义版块时必填
     */
    private Long blockId;

    /**
     * 版块字段值，JSON字符串，格式Map：key=版块模板字段中JAVA字段名，value=对应值
     */
    private String blockColumnValue;

    /**
     * 板块模版主键字段
     */
    private String mainField;

    /**
     * 状态（0正常 1停用）
     */
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * 排序，从小到大
     */
    private Long sort;

    /**
     * 平台标识
     */
    @NotNull(message = "平台不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long platformKey;


}
