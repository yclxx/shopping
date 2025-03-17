package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;

/**
 * 行政区业务对象
 *
 * @author yzg
 * @date 2024-01-12
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class CityBo extends BaseEntity {

    /**
     * 区域编码
     */
    @NotNull(message = "区域编码不能为空", groups = { EditGroup.class })
    private Long adcode;

    /**
     * 城市编码
     */
    @NotBlank(message = "城市编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String citycode;

    /**
     * 行政区名称
     */
    @NotBlank(message = "行政区名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String areaName;

    /**
     * 行政区划级别  country:国家  province:省份（直辖市会在province显示）  city:市（直辖市会在province显示）  district:区县  street:街道
     */
    @NotBlank(message = "行政区划级别  country:国家  province:省份（直辖市会在province显示）  city:市（直辖市会在province显示）  district:区县  street:街道不能为空", groups = { AddGroup.class, EditGroup.class })
    private String level;

    /**
     * 上级区域编码  000000-代表无上级
     */
    @NotBlank(message = "上级区域编码  000000-代表无上级不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long parentCode;

    /**
     * 首字母
     */
    @NotBlank(message = "首字母不能为空", groups = { AddGroup.class, EditGroup.class })
    private String firstLetter;


}
