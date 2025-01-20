package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商户拓展服务商对象 t_extension_service_provider
 *
 * @author yzg
 * @date 2023-09-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_extension_service_provider")
public class ExtensionServiceProvider extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 服务商名称
     */
    private String providerName;
    /**
     * 服务商联系人
     */
    private String providerUserName;
    /**
     * 联系电话
     */
    private String providerUserMobile;
    /**
     * 状态（0正常 1停用）
     */
    private String status;
    /**
     * 部门id
     */
    private Long sysDeptId;
    /**
     * 用户id
     */
    private Long sysUserId;

}
