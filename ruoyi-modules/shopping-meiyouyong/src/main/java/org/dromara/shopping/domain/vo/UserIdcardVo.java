package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import org.dromara.common.sensitive.annotation.Sensitive;
import org.dromara.common.sensitive.core.SensitiveStrategy;

/**
 * 观影用户信息视图对象
 *
 * @author yzg
 * @date 2023-09-15
 */
@Data
@ExcelIgnoreUnannotated
public class UserIdcardVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long userIdcardId;

    /**
     * 用户ID
     */
    @ExcelProperty(value = "用户ID")
    private Long userId;

    /**
     * 真实姓名
     */
    @ExcelProperty(value = "真实姓名")
    private String name;

    /**
     * 证件类型0-身份证 1-护照 2-港澳台居民居住证 3-户口簿
     */
    @Sensitive(strategy = SensitiveStrategy.ID_CARD)
    @ExcelProperty(value = "证件类型0-身份证 1-护照 2-港澳台居民居住证 3-户口簿")
    private String cardType;

    /**
     * 证件号
     */
    @ExcelProperty(value = "证件号")
    private String idCard;


}
