package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.ShopTourLsMerchantBo;
import org.dromara.shopping.domain.vo.ShopTourLsMerchantVo;

import java.util.Collection;
import java.util.List;

/**
 * 巡检商户号临时Service接口
 *
 * @author yzg
 * @date 2024-03-10
 */
public interface IShopTourLsMerchantService {

    /**
     * 查询巡检商户号临时
     */
    ShopTourLsMerchantVo queryById(Long tourMerchantLsId);

    /**
     * 查询巡检商户号临时列表
     */
    TableDataInfo<ShopTourLsMerchantVo> queryPageList(ShopTourLsMerchantBo bo, PageQuery pageQuery);

    /**
     * 查询巡检商户号临时列表
     */
    List<ShopTourLsMerchantVo> queryList(ShopTourLsMerchantBo bo);

    /**
     * 修改巡检商户号临时
     */
    Boolean insertByBo(ShopTourLsMerchantBo bo);

    /**
     * 修改巡检商户号临时
     */
    Boolean updateByBo(ShopTourLsMerchantBo bo);

    /**
     * 校验并批量删除巡检商户号临时信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
