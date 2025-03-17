package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.PlatformCityChangeBo;
import org.dromara.shopping.domain.vo.PlatformCityChangeVo;

import java.util.Collection;
import java.util.List;

/**
 * 平台切换Service接口
 *
 * @author yzg
 * @date 2024-03-19
 */
public interface IPlatformCityChangeService {

    /**
     * 查询平台切换
     */
    PlatformCityChangeVo queryById(Long id);

    /**
     * 查询平台切换列表
     */
    TableDataInfo<PlatformCityChangeVo> queryPageList(PlatformCityChangeBo bo, PageQuery pageQuery);

    /**
     * 查询平台切换列表
     */
    List<PlatformCityChangeVo> queryList(PlatformCityChangeBo bo);

    /**
     * 修改平台切换
     */
    Boolean insertByBo(PlatformCityChangeBo bo);

    /**
     * 修改平台切换
     */
    Boolean updateByBo(PlatformCityChangeBo bo);

    /**
     * 校验并批量删除平台切换信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
