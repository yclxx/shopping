package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;

/**
 * 文件导入记录业务对象
 *
 * @author yzg
 * @date 2024-01-04
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class FileImportLogBo extends BaseEntity {

    /**
     * 文件导入记录id
     */
    @NotNull(message = "文件导入记录id不能为空", groups = { EditGroup.class })
    private Long importLogId;

    /**
     * 文件地址
     */
    //@NotBlank(message = "文件地址不能为空", groups = { AddGroup.class, EditGroup.class })
    private String url;

    /**
     * 文件名
     */
    //@NotBlank(message = "文件名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 数据数量
     */
    //@NotNull(message = "数据数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long count;

    /**
     * 导入数据数量
     */
    private Long importCount;

    /**
     * 页面标题
     */
    private String pageTitle;

    private String pageUrl;

}
