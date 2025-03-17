package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.ShopMerchantBo;
import org.dromara.shopping.domain.vo.ShopMerchantVo;

import java.util.Collection;
import java.util.List;

/**
 * 门店商户号Service接口
 *
 * @author yzgnet
 * @date 2023-03-21
 */
public interface IShopMerchantService {

    /**
     * 查询门店商户号
     */
    ShopMerchantVo queryById(Long id);

    /**
     * 查询门店商户号列表
     */
    TableDataInfo<ShopMerchantVo> queryPageList(ShopMerchantBo bo, PageQuery pageQuery);

    /**
     * 查询门店商户号列表
     */
    List<ShopMerchantVo> queryList(ShopMerchantBo bo);

    /**
     * 修改门店商户号
     */
    Boolean insertByBo(ShopMerchantBo bo);

    /**
     * 修改门店商户号
     */
    Boolean updateByBo(ShopMerchantBo bo);

    /**
     * 校验并批量删除门店商户号信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    List<ShopMerchantVo> queryByShopId(Long shopId, String merchantType);

    ShopMerchantVo queryByMerNo(String merchantNo);
}
