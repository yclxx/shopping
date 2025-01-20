package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文件导入记录对象 t_file_import_log
 *
 * @author yzg
 * @date 2024-01-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_file_import_log")
public class FileImportLog extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 文件导入记录id
     */
    @TableId(value = "import_log_id")
    private Long importLogId;
    /**
     * 文件地址
     */
    private String url;
    /**
     * 文件名
     */
    private String name;
    /**
     * 数据数量
     */
    private Long count;
    /**
     * 导入数据数量
     */
    private Long importCount;
    /**
     * 删除标志  0-存在  2-删除
     */
    @TableLogic
    private String delFlag;

    /**
     * 页面标题
     */
    private String pageTitle;

    private String pageUrl;

}
