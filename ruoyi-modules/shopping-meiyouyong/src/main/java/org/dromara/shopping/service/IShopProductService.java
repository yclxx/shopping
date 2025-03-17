package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.ShopProductBo;
import org.dromara.shopping.domain.vo.ShopProductVo;

import java.util.Collection;
import java.util.List;

/**
 * 商品门店关联Service接口
 *
 * @author yzg
 * @date 2023-05-16
 */
public interface IShopProductService {

    /**
     * 查询商品门店关联
     */
    ShopProductVo queryById(Long id);

    /**
     * 查询商品门店关联列表
     */
    TableDataInfo<ShopProductVo> queryPageList(ShopProductBo bo, PageQuery pageQuery);

    /**
     * 查询商品门店关联列表
     */
    List<ShopProductVo> queryList(ShopProductBo bo);

    /**
     * 修改商品门店关联
     */
    Boolean insertByBo(ShopProductBo bo);

    /**
     * 修改商品门店关联
     */
    Boolean updateByBo(ShopProductBo bo);

    /**
     * 校验并批量删除商品门店关联信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    Boolean addShopByProduct(ShopProductBo bo);

    int delByShopProduct(ShopProductBo bo);

    /**
     * 根据门店id删除门店商品关联信息
     * @param shopId
     * @return
     */
    Integer deleteWithValidByShopId(Long shopId);

    List<ShopProductVo> queryByShopId(Long shopId);

    Integer deleteByProductId(Long productId);

    List<Long> queryByProductId(Long productId);

    /**
     * 查询商品城市
     * @param productId 商品ID
     * @return 城市编码，多个之间英文逗号隔开
     */
    String queryCityCode(Long productId);
}
