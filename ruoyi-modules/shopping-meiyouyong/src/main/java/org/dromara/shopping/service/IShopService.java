package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.ShopBo;
import org.dromara.shopping.domain.vo.ShopVo;

import java.util.Collection;
import java.util.List;

/**
 * 门店Service接口
 *
 * @author yzgnet
 * @date 2023-03-21
 */
public interface IShopService {

    /**
     * 项目启动初始化门店缓存
     */
    void loadingShopCache();

    /**
     * 查询门店
     */
    ShopVo queryById(Long shopId);

    /**
     * 查询门店列表(特殊查询条件使用)
     */
    TableDataInfo<ShopVo> queryPageLists(ShopBo bo, PageQuery pageQuery);

    /**
     * 查询门店列表
     */
    TableDataInfo<ShopVo> queryPageList(ShopBo bo, PageQuery pageQuery);

    /**
     * 查询门店列表
     */
    List<ShopVo> queryList(ShopBo bo);

    List<ShopVo> queryList(List<String> ids);

    /**
     * 修改门店
     */
    Boolean insertByBo(ShopBo bo, boolean supper);

    Boolean insertShop(ShopBo bo, boolean supper);

    /**
     * 修改门店
     */
    Boolean updateByBo(ShopBo bo, boolean supper);

    /**
     * 校验并批量删除门店信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);


    ShopVo queryByNameAndAddress(String shopName, String address, Long platformKey);

    List<ShopVo> queryByCommercialTenantId(Long commercialTenantId);

    int deleteByCommercialTenantId(Long commercialTenantId);

    int deleteBySupplierShopId(Long supplierShopId);

    ShopVo queryByNameAndCommercialTenantId(String name, Long commercialTenantId);

    ShopVo queryByNameAndSupplierId(String name, String supplierShopId);

    TableDataInfo<ShopVo> getPageList(ShopBo bo, PageQuery pageQuery);
}
