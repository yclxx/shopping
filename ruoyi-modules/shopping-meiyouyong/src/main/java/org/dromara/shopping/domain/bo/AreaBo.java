package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 行政区业务对象
 *
 * @author ruoyi
 * @date 2023-03-18
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class AreaBo extends BaseEntity {

    /**
     * 编号
     */
    private Long id;

    /**
     * 行政编码
     */
    @NotNull(message = "行政编码不能为空", groups = { EditGroup.class })
    private Long adcode;

    /**
     * 行政区名称
     */
    @NotBlank(message = "行政区名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String areaName;

    /**
     * 行政区划级别,country:国家,province:省份（直辖市会在province和city显示）,city:市（直辖市会在province和city显示）,district:区县,street:街道
     */
    @NotBlank(message = "行政区划级别不能为空", groups = { AddGroup.class, EditGroup.class })
    private String level;

    /**
     * 首字母
     */
    private String firstLetter;

    /**
     * 上级区域编码,000000代表无上级
     */
    @NotNull(message = "上级区域编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long parentCode;


}
