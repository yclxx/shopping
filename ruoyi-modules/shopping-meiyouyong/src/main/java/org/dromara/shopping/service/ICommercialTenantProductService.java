package org.dromara.shopping.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.CommercialTenantProduct;
import org.dromara.shopping.domain.bo.CommercialTenantProductBo;
import org.dromara.shopping.domain.vo.CommercialTenantProductVo;

import java.util.Collection;
import java.util.List;

/**
 * 商户商品配置Service接口
 *
 * @author yzgnet
 * @date 2023-03-21
 */
public interface ICommercialTenantProductService {

    /**
     * 查询商户商品配置
     */
    CommercialTenantProductVo queryById(Long id);

    /**
     * 查询商户商品配置列表
     */
    TableDataInfo<CommercialTenantProductVo> queryPageList(CommercialTenantProductBo bo, PageQuery pageQuery);

    /**
     * 查询商户商品配置列表
     */
    List<CommercialTenantProductVo> queryList(CommercialTenantProductBo bo);

    /**
     * 修改商户商品配置
     */
    Boolean insertByBo(CommercialTenantProductBo bo);

    /**
     * 修改商户商品配置
     */
    Boolean updateByBo(CommercialTenantProductBo bo);

    /**
     * 校验并批量删除商户商品配置信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    Boolean remove(LambdaQueryWrapper<CommercialTenantProduct> queryWrapper);
}
