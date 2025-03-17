package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.encrypt.annotation.EncryptField;
import org.dromara.common.mybatis.core.domain.BaseEntity;

/**
 * 用户地址对象 t_user_address
 *
 * @author yzg
 * @date 2023-09-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_user_address")
public class UserAddress extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "user_address_id")
    private Long userAddressId;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 联系人
     */
    private String name;
    /**
     * 联系电话
     */
    @EncryptField()
    private String tel;
    /**
     * 是否默认 0-默认 1-不默认
     */
    private String isDefault;
    /**
     * 地址中文，省市县等，用空格隔开
     */
    private String address;
    /**
     * 县市code
     */
    private String areaId;
    /**
     * 详细地址（街道门牌号啥的，全地址需要address+address_info）
     */
    private String addressInfo;
    /**
     * 删除标志
     */
    @TableLogic
    private Long delFlag;

}
