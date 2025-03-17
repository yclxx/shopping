package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.PlatformSharePosterBo;
import org.dromara.shopping.domain.vo.PlatformSharePosterVo;

import java.util.Collection;
import java.util.List;

/**
 * 平台分享海报Service接口
 *
 * @author yzg
 * @date 2025-01-03
 */
public interface IPlatformSharePosterService {

    /**
     * 查询平台分享海报
     */
    PlatformSharePosterVo queryById(Long id);

    /**
     * 查询平台分享海报列表
     */
    TableDataInfo<PlatformSharePosterVo> queryPageList(PlatformSharePosterBo bo, PageQuery pageQuery);

    /**
     * 查询平台分享海报列表
     */
    List<PlatformSharePosterVo> queryList(PlatformSharePosterBo bo);

    /**
     * 修改平台分享海报
     */
    Boolean insertByBo(PlatformSharePosterBo bo);

    /**
     * 修改平台分享海报
     */
    Boolean updateByBo(PlatformSharePosterBo bo);

    /**
     * 校验并批量删除平台分享海报信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
