package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.SearchGroupBo;
import org.dromara.shopping.domain.vo.SearchGroupVo;

import java.util.Collection;
import java.util.List;

/**
 * 搜索彩蛋配置Service接口
 *
 * @author yzg
 * @date 2023-07-24
 */
public interface ISearchGroupService {

    /**
     * 查询搜索彩蛋配置
     */
    SearchGroupVo queryById(Long searchId);

    /**
     * 查询搜索彩蛋配置列表
     */
    TableDataInfo<SearchGroupVo> queryPageList(SearchGroupBo bo, PageQuery pageQuery);

    /**
     * 查询搜索彩蛋配置列表
     */
    List<SearchGroupVo> queryList(SearchGroupBo bo);

    /**
     * 修改搜索彩蛋配置
     */
    Boolean insertByBo(SearchGroupBo bo);

    /**
     * 修改搜索彩蛋配置
     */
    Boolean updateByBo(SearchGroupBo bo);

    /**
     * 校验并批量删除搜索彩蛋配置信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
