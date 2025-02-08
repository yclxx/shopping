package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.BrowseBo;
import org.dromara.shopping.domain.vo.BrowseVo;

import java.util.Collection;
import java.util.List;

/**
 * 浏览任务Service接口
 *
 * @author yzg
 * @date 2023-12-14
 */
public interface IBrowseService {

    /**
     * 查询浏览任务
     */
    BrowseVo queryById(Long browseId);

    /**
     * 查询浏览任务列表
     */
    TableDataInfo<BrowseVo> queryPageList(BrowseBo bo, PageQuery pageQuery);

    /**
     * 查询浏览任务列表
     */
    List<BrowseVo> queryList(BrowseBo bo);

    /**
     * 修改浏览任务
     */
    Boolean insertByBo(BrowseBo bo);

    /**
     * 修改浏览任务
     */
    Boolean updateByBo(BrowseBo bo);

    /**
     * 校验并批量删除浏览任务信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
