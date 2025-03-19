package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.SupplierBrand;
import org.dromara.shopping.domain.bo.SupplierBrandBo;
import org.dromara.shopping.domain.vo.SupplierBrandVo;
import org.dromara.shopping.mapper.SupplierBrandMapper;
import org.dromara.shopping.service.ISupplierBrandService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 供应商品牌Service业务层处理
 *
 * @author yzg
 * @date 2024-12-26
 */
@RequiredArgsConstructor
@Service
public class SupplierBrandServiceImpl implements ISupplierBrandService {

    private final SupplierBrandMapper baseMapper;

    /**
     * 查询供应商品牌
     */
    @Override
    public SupplierBrandVo queryById(Long brandId){
        return baseMapper.selectVoById(brandId);
    }

    /**
     * 查询供应商品牌列表
     */
    @Override
    public TableDataInfo<SupplierBrandVo> queryPageList(SupplierBrandBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SupplierBrand> lqw = buildQueryWrapper(bo);
        Page<SupplierBrandVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询供应商品牌列表
     */
    @Override
    public List<SupplierBrandVo> queryList(SupplierBrandBo bo) {
        LambdaQueryWrapper<SupplierBrand> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SupplierBrand> buildQueryWrapper(SupplierBrandBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SupplierBrand> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getSupplierId() != null, SupplierBrand::getSupplierId, bo.getSupplierId());
        lqw.eq(StringUtils.isNotBlank(bo.getBrandTrademark()), SupplierBrand::getBrandTrademark, bo.getBrandTrademark());
        lqw.eq(StringUtils.isNotBlank(bo.getAuthorizeLetter()), SupplierBrand::getAuthorizeLetter, bo.getAuthorizeLetter());
        return lqw;
    }

    /**
     * 新增供应商品牌
     */
    @Override
    public Boolean insertByBo(SupplierBrandBo bo) {
        SupplierBrand add = BeanUtil.toBean(bo, SupplierBrand.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setBrandId(add.getBrandId());
        }
        return flag;
    }

    /**
     * 修改供应商品牌
     */
    @Override
    public Boolean updateByBo(SupplierBrandBo bo) {
        SupplierBrand update = BeanUtil.toBean(bo, SupplierBrand.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SupplierBrand entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除供应商品牌
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
