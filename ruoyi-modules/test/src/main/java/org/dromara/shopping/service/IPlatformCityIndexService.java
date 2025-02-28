package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.PlatformCityIndexBo;
import org.dromara.shopping.domain.vo.PlatformCityIndexVo;

import java.util.Collection;
import java.util.List;

/**
 * 自定义首页Service接口
 *
 * @author yzg
 * @date 2023-08-07
 */
public interface IPlatformCityIndexService {

    /**
     * 查询自定义首页
     */
    PlatformCityIndexVo queryById(Long id);

    /**
     * 查询自定义首页列表
     */
    TableDataInfo<PlatformCityIndexVo> queryPageList(PlatformCityIndexBo bo, PageQuery pageQuery);

    /**
     * 查询自定义首页列表
     */
    List<PlatformCityIndexVo> queryList(PlatformCityIndexBo bo);

    /**
     * 修改自定义首页
     */
    Boolean insertByBo(PlatformCityIndexBo bo);

    /**
     * 修改自定义首页
     */
    Boolean updateByBo(PlatformCityIndexBo bo);

    /**
     * 校验并批量删除自定义首页信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
