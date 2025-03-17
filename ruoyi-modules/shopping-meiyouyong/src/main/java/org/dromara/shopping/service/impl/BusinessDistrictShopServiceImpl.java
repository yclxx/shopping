package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.BusinessDistrictShop;
import org.dromara.shopping.domain.bo.BusinessDistrictShopBo;
import org.dromara.shopping.domain.vo.BusinessDistrictShopVo;
import org.dromara.shopping.mapper.BusinessDistrictShopMapper;
import org.dromara.shopping.service.IBusinessDistrictShopService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 商圈门店关联Service业务层处理
 *
 * @author yzg
 * @date 2023-09-15
 */
@RequiredArgsConstructor
@Service
public class BusinessDistrictShopServiceImpl implements IBusinessDistrictShopService {

    private final BusinessDistrictShopMapper baseMapper;

    /**
     * 查询商圈门店关联
     */
    @Override
    public BusinessDistrictShopVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询商圈门店关联列表
     */
    @Override
    public TableDataInfo<BusinessDistrictShopVo> queryPageList(BusinessDistrictShopBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BusinessDistrictShop> lqw = buildQueryWrapper(bo);
        Page<BusinessDistrictShopVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询商圈门店关联列表
     */
    @Override
    public List<BusinessDistrictShopVo> queryList(BusinessDistrictShopBo bo) {
        LambdaQueryWrapper<BusinessDistrictShop> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BusinessDistrictShop> buildQueryWrapper(BusinessDistrictShopBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BusinessDistrictShop> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getBusinessDistrictId() != null, BusinessDistrictShop::getBusinessDistrictId, bo.getBusinessDistrictId());
        lqw.eq(bo.getShopId() != null, BusinessDistrictShop::getShopId, bo.getShopId());
        return lqw;
    }

    /**
     * 新增商圈门店关联
     */
    @Override
    public Boolean insertByBo(BusinessDistrictShopBo bo) {
        BusinessDistrictShop add = BeanUtil.toBean(bo, BusinessDistrictShop.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改商圈门店关联
     */
    @Override
    public Boolean updateByBo(BusinessDistrictShopBo bo) {
        BusinessDistrictShop update = BeanUtil.toBean(bo, BusinessDistrictShop.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BusinessDistrictShop entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除商圈门店关联
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public Integer deleteWithValidByShopId(Long shopId) {
        return baseMapper.delete(new LambdaQueryWrapper<BusinessDistrictShop>().eq(BusinessDistrictShop::getShopId,shopId));
    }

    @Override
    public Boolean remove(LambdaQueryWrapper<BusinessDistrictShop> queryWrapper) {
        return SqlHelper.retBool(baseMapper.delete(queryWrapper));
    }

    @Override
    public Boolean addShopByProduct(BusinessDistrictShopBo bo) {
        List<BusinessDistrictShop> add = new ArrayList<>();
        if (ObjectUtil.isNotEmpty(bo.getShopIds()) && ObjectUtil.isNotEmpty(bo.getBusinessDistrictId())) {
            bo.getShopIds().forEach(o -> {
                BusinessDistrictShop shopProduct = new BusinessDistrictShop();
                shopProduct.setShopId(o);
                shopProduct.setBusinessDistrictId(bo.getBusinessDistrictId());
                add.add(shopProduct);
            });
            return baseMapper.insertBatch(add);
        } else if (ObjectUtil.isNotEmpty(bo.getBusinessDistrictIds()) && ObjectUtil.isNotEmpty(bo.getShopId())) {
            bo.getBusinessDistrictIds().forEach(o -> {
                BusinessDistrictShop shopProduct = new BusinessDistrictShop();
                shopProduct.setShopId(bo.getShopId());
                shopProduct.setBusinessDistrictId(o);
                add.add(shopProduct);
            });
            return baseMapper.insertBatch(add);
        }
        return false;
    }

    @Override
    public int delByShopProduct(BusinessDistrictShopBo bo) {
        LambdaQueryWrapper<BusinessDistrictShop> wrapper = Wrappers.lambdaQuery();
        if (ObjectUtil.isNotEmpty(bo.getShopIds()) && ObjectUtil.isNotEmpty(bo.getBusinessDistrictId())) {
            wrapper.eq(BusinessDistrictShop::getBusinessDistrictId,bo.getBusinessDistrictId());
            wrapper.in(BusinessDistrictShop::getShopId,bo.getShopIds());
            return baseMapper.delete(wrapper);
        } else if (ObjectUtil.isNotEmpty(bo.getBusinessDistrictIds()) && ObjectUtil.isNotEmpty(bo.getShopId())) {
            wrapper.eq(BusinessDistrictShop::getShopId,bo.getShopId());
            wrapper.in(BusinessDistrictShop::getBusinessDistrictId,bo.getBusinessDistrictIds());
            return baseMapper.delete(wrapper);
        }
        return 0;
    }
}
