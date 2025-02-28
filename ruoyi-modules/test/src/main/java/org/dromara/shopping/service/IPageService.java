package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.PageBo;
import org.dromara.shopping.domain.vo.PageVo;

import java.util.Collection;
import java.util.List;

/**
 * 页面Service接口
 *
 * @author yzgnet
 * @date 2023-03-21
 */
public interface IPageService {

    /**
     * 查询页面
     */
    PageVo queryById(Long id);

    /**
     * 查询页面列表
     */
    TableDataInfo<PageVo> queryPageList(PageBo bo, PageQuery pageQuery);

    /**
     * 查询页面列表
     */
    List<PageVo> queryList(PageBo bo);

    /**
     * 修改页面
     */
    Boolean insertByBo(PageBo bo);

    /**
     * 修改页面
     */
    Boolean updateByBo(PageBo bo);

    /**
     * 校验并批量删除页面信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
