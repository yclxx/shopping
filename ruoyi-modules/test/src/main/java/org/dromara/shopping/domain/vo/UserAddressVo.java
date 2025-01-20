package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

/**
 * 用户地址视图对象
 *
 * @author yzg
 * @date 2023-09-15
 */
@Data
@ExcelIgnoreUnannotated
public class UserAddressVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long userAddressId;

    /**
     * 用户ID
     */
    @ExcelProperty(value = "用户ID")
    private Long userId;

    /**
     * 联系人
     */
    @ExcelProperty(value = "联系人")
    private String name;

    /**
     * 联系电话
     */
    @ExcelProperty(value = "联系电话")
    private String tel;

    /**
     * 是否默认 0-默认 1-不默认
     */
    @ExcelProperty(value = "是否默认 0-默认 1-不默认")
    private String isDefault;

    /**
     * 地址中文，省市县等，用空格隔开
     */
    @ExcelProperty(value = "地址中文，省市县等，用空格隔开")
    private String address;

    /**
     * 地址四级联动ID，对应t_area表，多个之间用空格隔开
     */
    @ExcelProperty(value = "县市code")
    private String areaId;

    /**
     * 详细地址（街道门牌号啥的，全地址需要address+address_info）
     */
    @ExcelProperty(value = "详细地址", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "街=道门牌号啥的，全地址需要address+address_info")
    private String addressInfo;


}
