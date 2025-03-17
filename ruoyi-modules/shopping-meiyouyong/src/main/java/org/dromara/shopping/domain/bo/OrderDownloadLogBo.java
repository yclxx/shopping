package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 订单下载记录业务对象
 *
 * @author yzg
 * @date 2023-04-01
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderDownloadLogBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long tOrderDownloadId;

    /**
     * 文件地址
     */
    private String fileUrl;

    /**
     * 状态：0：未导出   1：导出中   2：导出成功   3：导出失败
     */
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    private String failReason;

}
