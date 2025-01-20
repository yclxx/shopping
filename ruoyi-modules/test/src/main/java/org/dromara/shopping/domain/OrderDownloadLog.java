package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 订单下载记录对象 t_order_download_log
 *
 * @author yzg
 * @date 2023-04-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_order_download_log")
public class OrderDownloadLog extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "t_order_download_id")
    private Long tOrderDownloadId;
    /**
     * 文件地址
     */
    private String fileUrl;
    /**
     * 状态：0：未导出   1：导出中   2：导出成功   3：导出失败
     */
    private String status;

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
