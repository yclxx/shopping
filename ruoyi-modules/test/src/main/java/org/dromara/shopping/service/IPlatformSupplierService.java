package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.PlatformSupplierBo;
import org.dromara.shopping.domain.vo.PlatformSupplierVo;

import java.util.Collection;
import java.util.List;

/**
 * 平台供应商Service接口
 *
 * @author yzg
 * @date 2025-01-13
 */
public interface IPlatformSupplierService {

    /**
     * 查询平台供应商
     */
    PlatformSupplierVo queryById(Long id);

    /**
     * 查询平台供应商列表
     */
    TableDataInfo<PlatformSupplierVo> queryPageList(PlatformSupplierBo bo, PageQuery pageQuery);

    /**
     * 查询平台供应商列表
     */
    List<PlatformSupplierVo> queryList(PlatformSupplierBo bo);

    /**
     * 修改平台供应商
     */
    Boolean insertByBo(PlatformSupplierBo bo);

    /**
     * 修改平台供应商
     */
    Boolean updateByBo(PlatformSupplierBo bo);

    /**
     * 校验并批量删除平台供应商信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
