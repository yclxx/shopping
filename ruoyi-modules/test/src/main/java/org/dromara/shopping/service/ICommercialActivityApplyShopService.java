package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.CommercialActivityApplyShopBo;
import org.dromara.shopping.domain.vo.CommercialActivityApplyShopVo;

import java.util.Collection;
import java.util.List;

/**
 * 商户活动报名门店Service接口
 *
 * @author yzg
 * @date 2024-04-10
 */
public interface ICommercialActivityApplyShopService {

    /**
     * 查询商户活动报名门店
     */
    CommercialActivityApplyShopVo queryById(Long id);

    /**
     * 查询商户活动报名门店列表
     */
    TableDataInfo<CommercialActivityApplyShopVo> queryPageList(CommercialActivityApplyShopBo bo, PageQuery pageQuery);

    /**
     * 查询商户活动报名门店列表
     */
    List<CommercialActivityApplyShopVo> queryList(CommercialActivityApplyShopBo bo);

    /**
     * 修改商户活动报名门店
     */
    Boolean insertByBo(CommercialActivityApplyShopBo bo);

    /**
     * 修改商户活动报名门店
     */
    Boolean updateByBo(CommercialActivityApplyShopBo bo);

    /**
     * 校验并批量删除商户活动报名门店信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
