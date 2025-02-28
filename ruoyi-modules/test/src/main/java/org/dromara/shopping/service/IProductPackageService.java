package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.ProductPackageBo;
import org.dromara.shopping.domain.vo.ProductPackageVo;

import java.util.Collection;
import java.util.List;

/**
 * 商品券包Service接口
 *
 * @author yzg
 * @date 2023-06-30
 */
public interface IProductPackageService {

    /**
     * 查询商品券包
     */
    ProductPackageVo queryById(Long packageId);

    /**
     * 查询商品券包列表
     */
    TableDataInfo<ProductPackageVo> queryPageList(ProductPackageBo bo, PageQuery pageQuery);

    /**
     * 查询商品券包列表
     */
    List<ProductPackageVo> queryList(ProductPackageBo bo);

    /**
     * 修改商品券包
     */
    Boolean insertByBo(ProductPackageBo bo);

    /**
     * 修改商品券包
     */
    Boolean updateByBo(ProductPackageBo bo);

    /**
     * 校验并批量删除商品券包信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
