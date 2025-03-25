package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.VerifierSupplier;
import org.dromara.shopping.domain.bo.VerifierSupplierBo;
import org.dromara.shopping.domain.vo.VerifierSupplierVo;
import org.dromara.shopping.mapper.VerifierSupplierMapper;
import org.dromara.shopping.service.IVerifierSupplierService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 核销员供应商关联Service业务层处理
 *
 * @author yzg
 * @date 2024-03-25
 */
@RequiredArgsConstructor
@Service
public class VerifierSupplierServiceImpl implements IVerifierSupplierService {

    private final VerifierSupplierMapper baseMapper;

    /**
     * 查询核销员供应商关联
     */
    @Override
    public VerifierSupplierVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询核销员供应商关联列表
     */
    @Override
    public TableDataInfo<VerifierSupplierVo> queryPageList(VerifierSupplierBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<VerifierSupplier> lqw = buildQueryWrapper(bo);
        Page<VerifierSupplierVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询核销员供应商关联列表
     */
    @Override
    public List<VerifierSupplierVo> queryList(VerifierSupplierBo bo) {
        LambdaQueryWrapper<VerifierSupplier> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<VerifierSupplier> buildQueryWrapper(VerifierSupplierBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<VerifierSupplier> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getVerifierId() != null, VerifierSupplier::getVerifierId, bo.getVerifierId());
        lqw.eq(bo.getSupplierId() != null, VerifierSupplier::getSupplierId, bo.getSupplierId());
        return lqw;
    }

    /**
     * 新增核销员供应商关联
     */
    @Override
    public Boolean insertByBo(VerifierSupplierBo bo) {
        VerifierSupplier add = BeanUtil.toBean(bo, VerifierSupplier.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改核销员供应商关联
     */
    @Override
    public Boolean updateByBo(VerifierSupplierBo bo) {
        VerifierSupplier update = BeanUtil.toBean(bo, VerifierSupplier.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(VerifierSupplier entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除核销员供应商关联
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
