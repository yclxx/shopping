package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.MarketActivityBo;
import org.dromara.shopping.domain.vo.MarketActivityVo;

import java.util.Collection;
import java.util.List;

/**
 * 营销活动Service接口
 *
 * @author yzg
 * @date 2023-12-14
 */
public interface IMarketActivityService {

    /**
     * 查询营销活动
     */
    MarketActivityVo queryById(Long activityId);

    /**
     * 查询营销活动列表
     */
    TableDataInfo<MarketActivityVo> queryPageList(MarketActivityBo bo, PageQuery pageQuery);

    /**
     * 查询营销活动列表
     */
    List<MarketActivityVo> queryList(MarketActivityBo bo);

    /**
     * 修改营销活动
     */
    Boolean insertByBo(MarketActivityBo bo);

    /**
     * 修改营销活动
     */
    Boolean updateByBo(MarketActivityBo bo);

    /**
     * 校验并批量删除营销活动信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
