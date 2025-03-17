package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.ShopTourRewardBo;
import org.dromara.shopping.domain.vo.ShopTourRewardVo;

import java.util.Collection;
import java.util.List;

/**
 * 巡检奖励Service接口
 *
 * @author yzg
 * @date 2024-01-28
 */
public interface IShopTourRewardService {

    /**
     * 查询巡检奖励
     */
    ShopTourRewardVo queryById(Long tourRewardId);

    /**
     * 查询巡检奖励列表
     */
    TableDataInfo<ShopTourRewardVo> queryPageList(ShopTourRewardBo bo, PageQuery pageQuery);

    /**
     * 查询巡检奖励列表
     */
    List<ShopTourRewardVo> queryList(ShopTourRewardBo bo);

    /**
     * 修改巡检奖励
     */
    Boolean insertByBo(ShopTourRewardBo bo);

    /**
     * 修改巡检奖励
     */
    Boolean updateByBo(ShopTourRewardBo bo);

    /**
     * 校验并批量删除巡检奖励信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
