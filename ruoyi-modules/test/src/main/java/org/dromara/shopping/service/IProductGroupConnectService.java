package org.dromara.shopping.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.ProductGroupConnect;
import org.dromara.shopping.domain.bo.ProductGroupConnectBo;
import org.dromara.shopping.domain.vo.ProductGroupConnectVo;

import java.util.Collection;
import java.util.List;

/**
 * 商品商品组关联Service接口
 *
 * @author yzg
 * @date 2024-01-16
 */
public interface IProductGroupConnectService {

    /**
     * 查询商品商品组关联
     */
    ProductGroupConnectVo queryById(Long id);

    /**
     * 查询商品商品组关联列表
     */
    TableDataInfo<ProductGroupConnectVo> queryPageList(ProductGroupConnectBo bo, PageQuery pageQuery);

    /**
     * 查询商品商品组关联列表
     */
    List<ProductGroupConnectVo> queryList(ProductGroupConnectBo bo);

    /**
     * 修改商品商品组关联
     */
    Boolean insertByBo(ProductGroupConnectBo bo);

    /**
     * 修改商品商品组关联
     */
    Boolean updateByBo(ProductGroupConnectBo bo);

    /**
     * 校验并批量删除商品商品组关联信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    Boolean remove(LambdaQueryWrapper<ProductGroupConnect> queryWrapper);
}
