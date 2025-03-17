package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 银行对象 t_bank
 *
 * @author yzg
 * @date 2024-04-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_bank")
public class Bank extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 银行ID
     */
    @TableId(value = "bank_id")
    private Long bankId;
    /**
     * 银行名称
     */
    private String bankName;
    /**
     * 银行logo
     */
    private String bankLogo;
    /**
     * 简称
     */
    private String bankShortName;
    /**
     * 英文简称
     */
    private String englishAbbreviation;
    /**
     * 状态
     */
    private String status;

}
