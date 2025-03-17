package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.CommercialTenantProduct;
import org.dromara.shopping.domain.bo.CommercialTenantProductBo;
import org.dromara.shopping.domain.vo.CommercialTenantProductVo;
import org.dromara.shopping.mapper.CommercialTenantProductMapper;
import org.dromara.shopping.service.ICommercialTenantProductService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 商户商品配置Service业务层处理
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@RequiredArgsConstructor
@Service
public class CommercialTenantProductServiceImpl implements ICommercialTenantProductService {

    private final CommercialTenantProductMapper baseMapper;

    /**
     * 查询商户商品配置
     */
    @Override
    public CommercialTenantProductVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询商户商品配置列表
     */
    @Override
    public TableDataInfo<CommercialTenantProductVo> queryPageList(CommercialTenantProductBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<CommercialTenantProduct> lqw = buildQueryWrapper(bo);
        Page<CommercialTenantProductVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询商户商品配置列表
     */
    @Override
    public List<CommercialTenantProductVo> queryList(CommercialTenantProductBo bo) {
        LambdaQueryWrapper<CommercialTenantProduct> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<CommercialTenantProduct> buildQueryWrapper(CommercialTenantProductBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<CommercialTenantProduct> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getCommercialTenantId() != null, CommercialTenantProduct::getCommercialTenantId, bo.getCommercialTenantId());
        lqw.eq(bo.getProductId() != null, CommercialTenantProduct::getProductId, bo.getProductId());
        lqw.eq(bo.getSort() != null, CommercialTenantProduct::getSort, bo.getSort());
        return lqw;
    }

    /**
     * 新增商户商品配置
     */
    @Override
    public Boolean insertByBo(CommercialTenantProductBo bo) {
        CommercialTenantProduct add = BeanUtil.toBean(bo, CommercialTenantProduct.class);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改商户商品配置
     */
    @Override
    public Boolean updateByBo(CommercialTenantProductBo bo) {
        CommercialTenantProduct update = BeanUtil.toBean(bo, CommercialTenantProduct.class);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 批量删除商户商品配置
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public Boolean remove(LambdaQueryWrapper<CommercialTenantProduct> queryWrapper) {
        return SqlHelper.retBool(baseMapper.delete(queryWrapper));
    }
}
