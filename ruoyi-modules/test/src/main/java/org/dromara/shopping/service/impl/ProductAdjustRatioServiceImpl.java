package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.ProductAdjustRatio;
import org.dromara.shopping.domain.bo.ProductAdjustRatioBo;
import org.dromara.shopping.domain.vo.ProductAdjustRatioVo;
import org.dromara.shopping.mapper.ProductAdjustRatioMapper;
import org.dromara.shopping.service.IProductAdjustRatioService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 商品调价比例Service业务层处理
 *
 * @author yzg
 * @date 2024-05-30
 */
@RequiredArgsConstructor
@Service
public class ProductAdjustRatioServiceImpl implements IProductAdjustRatioService {

    private final ProductAdjustRatioMapper baseMapper;

    /**
     * 查询商品调价比例
     */
    @Override
    public ProductAdjustRatioVo queryById(Long ratioId){
        return baseMapper.selectVoById(ratioId);
    }

    /**
     * 查询商品调价比例列表
     */
    @Override
    public TableDataInfo<ProductAdjustRatioVo> queryPageList(ProductAdjustRatioBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ProductAdjustRatio> lqw = buildQueryWrapper(bo);
        Page<ProductAdjustRatioVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询商品调价比例列表
     */
    @Override
    public List<ProductAdjustRatioVo> queryList(ProductAdjustRatioBo bo) {
        LambdaQueryWrapper<ProductAdjustRatio> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ProductAdjustRatio> buildQueryWrapper(ProductAdjustRatioBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ProductAdjustRatio> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getPlatformKey() != null, ProductAdjustRatio::getPlatformKey, bo.getPlatformKey());
        lqw.eq(StringUtils.isNotBlank(bo.getAdjustType()), ProductAdjustRatio::getAdjustType, bo.getAdjustType());
        lqw.eq(bo.getCategoryId() != null, ProductAdjustRatio::getCategoryId, bo.getCategoryId());
        lqw.eq(bo.getAdjustRatio() != null, ProductAdjustRatio::getAdjustRatio, bo.getAdjustRatio());
        return lqw;
    }

    /**
     * 新增商品调价比例
     */
    @Override
    public Boolean insertByBo(ProductAdjustRatioBo bo) {
        ProductAdjustRatio add = BeanUtil.toBean(bo, ProductAdjustRatio.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setRatioId(add.getRatioId());
        }
        return flag;
    }

    /**
     * 修改商品调价比例
     */
    @Override
    public Boolean updateByBo(ProductAdjustRatioBo bo) {
        ProductAdjustRatio update = BeanUtil.toBean(bo, ProductAdjustRatio.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ProductAdjustRatio entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除商品调价比例
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
