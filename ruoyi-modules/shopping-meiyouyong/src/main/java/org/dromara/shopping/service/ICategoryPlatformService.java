package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.CategoryPlatformBo;
import org.dromara.shopping.domain.vo.CategoryPlatformVo;

import java.util.Collection;
import java.util.List;

/**
 * 多平台类别Service接口
 *
 * @author yzg
 * @date 2024-02-27
 */
public interface ICategoryPlatformService {

    /**
     * 查询多平台类别
     */
    CategoryPlatformVo queryById(Long id);

    /**
     * 查询多平台类别列表
     */
    TableDataInfo<CategoryPlatformVo> queryPageList(CategoryPlatformBo bo, PageQuery pageQuery);

    /**
     * 查询多平台类别列表
     */
    List<CategoryPlatformVo> queryList(CategoryPlatformBo bo);

    /**
     * 修改多平台类别
     */
    Boolean insertByBo(CategoryPlatformBo bo);

    /**
     * 修改多平台类别
     */
    Boolean updateByBo(CategoryPlatformBo bo);

    /**
     * 校验并批量删除多平台类别信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
