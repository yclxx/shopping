package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.SupplierUserBo;
import org.dromara.shopping.domain.vo.SupplierUserVo;

import java.util.Collection;
import java.util.List;

/**
 * 供应商用户关联Service接口
 *
 * @author yzg
 * @date 2024-12-20
 */
public interface ISupplierUserService {

    /**
     * 查询供应商用户关联
     */
    SupplierUserVo queryById(Long id);

    /**
     * 查询供应商用户关联列表
     */
    TableDataInfo<SupplierUserVo> queryPageList(SupplierUserBo bo, PageQuery pageQuery);

    /**
     * 查询供应商用户关联列表
     */
    List<SupplierUserVo> queryList(SupplierUserBo bo);

    /**
     * 修改供应商用户关联
     */
    Boolean insertByBo(SupplierUserBo bo);

    /**
     * 修改供应商用户关联
     */
    Boolean updateByBo(SupplierUserBo bo);

    /**
     * 校验并批量删除供应商用户关联信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    Long querySupplierId(Long userId);
}
