package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.ProductGroupConnect;
import org.dromara.shopping.domain.bo.ProductGroupConnectBo;
import org.dromara.shopping.domain.vo.ProductGroupConnectVo;
import org.dromara.shopping.mapper.ProductGroupConnectMapper;
import org.dromara.shopping.service.IProductGroupConnectService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 商品商品组关联Service业务层处理
 *
 * @author yzg
 * @date 2024-01-16
 */
@RequiredArgsConstructor
@Service
public class ProductGroupConnectServiceImpl implements IProductGroupConnectService {

    private final ProductGroupConnectMapper baseMapper;

    /**
     * 查询商品商品组关联
     */
    @Override
    public ProductGroupConnectVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询商品商品组关联列表
     */
    @Override
    public TableDataInfo<ProductGroupConnectVo> queryPageList(ProductGroupConnectBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ProductGroupConnect> lqw = buildQueryWrapper(bo);
        Page<ProductGroupConnectVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询商品商品组关联列表
     */
    @Override
    public List<ProductGroupConnectVo> queryList(ProductGroupConnectBo bo) {
        LambdaQueryWrapper<ProductGroupConnect> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ProductGroupConnect> buildQueryWrapper(ProductGroupConnectBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ProductGroupConnect> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getProductGroupId() != null, ProductGroupConnect::getProductGroupId, bo.getProductGroupId());
        lqw.eq(bo.getProductId() != null, ProductGroupConnect::getProductId, bo.getProductId());
        return lqw;
    }

    /**
     * 新增商品商品组关联
     */
    @Override
    public Boolean insertByBo(ProductGroupConnectBo bo) {
        ProductGroupConnect add = BeanUtil.toBean(bo, ProductGroupConnect.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改商品商品组关联
     */
    @Override
    public Boolean updateByBo(ProductGroupConnectBo bo) {
        ProductGroupConnect update = BeanUtil.toBean(bo, ProductGroupConnect.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ProductGroupConnect entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除商品商品组关联
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public Boolean remove(LambdaQueryWrapper<ProductGroupConnect> queryWrapper) {
        return SqlHelper.retBool(baseMapper.delete(queryWrapper));
    }
}
