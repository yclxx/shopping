package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.MarketLogBo;
import org.dromara.shopping.domain.vo.MarketLogVo;

import java.util.Collection;
import java.util.List;

/**
 * 奖励发放记录Service接口
 *
 * @author yzg
 * @date 2023-10-18
 */
public interface IMarketLogService {

    /**
     * 查询奖励发放记录
     */
    MarketLogVo queryById(Long logId);

    /**
     * 查询奖励发放记录列表
     */
    TableDataInfo<MarketLogVo> queryPageList(MarketLogBo bo, PageQuery pageQuery);

    /**
     * 查询奖励发放记录列表
     */
    List<MarketLogVo> queryList(MarketLogBo bo);

    /**
     * 修改奖励发放记录
     */
    Boolean insertByBo(MarketLogBo bo);

    /**
     * 修改奖励发放记录
     */
    Boolean updateByBo(MarketLogBo bo);

    /**
     * 校验并批量删除奖励发放记录信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
