package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 下载记录对象 t_download_log
 *
 * @author yzg
 * @date 2024-08-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_download_log")
public class DownloadLog extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "download_id")
    private Long downloadId;
    /**
     * 下载说明
     */
    private String downloadRemark;
    /**
     * 下载来源
     */
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
    /**
     * 部门id
     */
    private Long sysDeptId;
    /**
     * 用户id
     */
    private Long sysUserId;

}
