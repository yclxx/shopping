package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.Brand;
import org.dromara.shopping.domain.bo.BrandBo;
import org.dromara.shopping.domain.vo.BrandVo;
import org.dromara.shopping.mapper.BrandMapper;
import org.dromara.shopping.service.IBrandService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 品牌管理Service业务层处理
 *
 * @author yzg
 * @date 2023-12-29
 */
@RequiredArgsConstructor
@Service
public class BrandServiceImpl implements IBrandService {

    private final BrandMapper baseMapper;

    /**
     * 查询品牌管理
     */
    @Override
    public BrandVo queryById(Long brandId){
        return baseMapper.selectVoById(brandId);
    }

    /**
     * 查询品牌管理列表
     */
    @Override
    public TableDataInfo<BrandVo> queryPageList(BrandBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Brand> lqw = buildQueryWrapper(bo);
        Page<BrandVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询品牌管理列表
     */
    @Override
    public List<BrandVo> queryList(BrandBo bo) {
        LambdaQueryWrapper<Brand> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Brand> buildQueryWrapper(BrandBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Brand> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getBrandName()), Brand::getBrandName, bo.getBrandName());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), Brand::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增品牌管理
     */
    @Override
    public Boolean insertByBo(BrandBo bo) {
        Brand add = BeanUtil.toBean(bo, Brand.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setBrandId(add.getBrandId());
        }
        return flag;
    }

    /**
     * 修改品牌管理
     */
    @Override
    public Boolean updateByBo(BrandBo bo) {
        Brand update = BeanUtil.toBean(bo, Brand.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Brand entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除品牌管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
