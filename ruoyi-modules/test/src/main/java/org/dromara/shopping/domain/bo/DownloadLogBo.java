package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;

/**
 * 下载记录业务对象
 *
 * @author yzg
 * @date 2024-08-15
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class DownloadLogBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long downloadId;

    /**
     * 下载说明
     */
    @NotBlank(message = "下载说明不能为空", groups = { AddGroup.class, EditGroup.class })
    private String downloadRemark;

    /**
     * 下载来源
     */
    @NotBlank(message = "下载来源不能为空", groups = { AddGroup.class, EditGroup.class })
    private String downloadSource;

    /**
     * 文件地址
     */
    private String fileUrl;

    /**
     * 状态
     */
    private String status;

    /**
     * 失败原因
     */
    private String failReason;


}
