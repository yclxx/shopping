package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 版块模板字段对象 t_page_block_column
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_page_block_column")
public class PageBlockColumn extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 编号
     */
    @TableId(value = "column_id")
    private Long columnId;
    /**
     * 归属模板编号
     */
    private Long blockId;
    /**
     * 字段名称
     */
    private String columnName;
    /**
     * 字段描述
     */
    private String columnComment;
    /**
     * JAVA字段名
     */
    private String javaField;
    /**
     * 是否必填（1是）
     */
    private String isRequired;
    /**
     * 显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）
     */
    private String htmlType;
    /**
     * 字典类型
     */
    private String dictType;
    /**
     * 排序
     */
    private Long sort;

}
