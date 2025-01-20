package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 平台用户推广码对象 t_platform_promotion_code
 *
 * @author yzg
 * @date 2024-05-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_platform_promotion_code")
public class PlatformPromotionCode extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 平台标识
     */
    private Long platformKey;
    /**
     * 推广码
     */
    private String promotionCode;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 姓名
     */
    private String name;
    /**
     * 所属机构
     */
    private String institution;
    /**
     * 备注
     */
    private String remark;
    /**
     * 删除标志
     */
    @TableLogic
    private Long delFlag;

}
