package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.PageBlockBo;
import org.dromara.shopping.domain.vo.PageBlockVo;

import java.util.Collection;
import java.util.List;

/**
 * 版块模板Service接口
 *
 * @author yzgnet
 * @date 2023-03-21
 */
public interface IPageBlockService {

    /**
     * 查询版块模板
     */
    PageBlockVo queryById(Long id);

    /**
     * 查询版块模板列表
     */
    TableDataInfo<PageBlockVo> queryPageList(PageBlockBo bo, PageQuery pageQuery);

    /**
     * 查询版块模板列表
     */
    List<PageBlockVo> queryList(PageBlockBo bo);

    /**
     * 修改版块模板
     */
    Boolean insertByBo(PageBlockBo bo);

    /**
     * 修改版块模板
     */
    Boolean updateByBo(PageBlockBo bo);

    /**
     * 校验并批量删除版块模板信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
