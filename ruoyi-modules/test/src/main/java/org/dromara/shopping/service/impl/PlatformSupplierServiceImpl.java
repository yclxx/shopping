package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.PlatformSupplier;
import org.dromara.shopping.domain.bo.PlatformSupplierBo;
import org.dromara.shopping.domain.vo.PlatformSupplierVo;
import org.dromara.shopping.mapper.PlatformSupplierMapper;
import org.dromara.shopping.service.IPlatformSupplierService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 平台供应商Service业务层处理
 *
 * @author yzg
 * @date 2025-01-13
 */
@RequiredArgsConstructor
@Service
public class PlatformSupplierServiceImpl implements IPlatformSupplierService {

    private final PlatformSupplierMapper baseMapper;

    /**
     * 查询平台供应商
     */
    @Override
    public PlatformSupplierVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询平台供应商列表
     */
    @Override
    public TableDataInfo<PlatformSupplierVo> queryPageList(PlatformSupplierBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<PlatformSupplier> lqw = buildQueryWrapper(bo);
        Page<PlatformSupplierVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询平台供应商列表
     */
    @Override
    public List<PlatformSupplierVo> queryList(PlatformSupplierBo bo) {
        LambdaQueryWrapper<PlatformSupplier> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<PlatformSupplier> buildQueryWrapper(PlatformSupplierBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<PlatformSupplier> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getPlatformKey() != null, PlatformSupplier::getPlatformKey, bo.getPlatformKey());
        lqw.eq(bo.getSupplierId() != null, PlatformSupplier::getSupplierId, bo.getSupplierId());
        return lqw;
    }

    /**
     * 新增平台供应商
     */
    @Override
    public Boolean insertByBo(PlatformSupplierBo bo) {
        PlatformSupplier add = BeanUtil.toBean(bo, PlatformSupplier.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改平台供应商
     */
    @Override
    public Boolean updateByBo(PlatformSupplierBo bo) {
        PlatformSupplier update = BeanUtil.toBean(bo, PlatformSupplier.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(PlatformSupplier entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除平台供应商
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
