package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.shopping.domain.Supplier;
import org.dromara.shopping.domain.SupplierUser;
import org.dromara.shopping.domain.bo.SupplierBo;
import org.dromara.shopping.domain.vo.SupplierUserVo;
import org.dromara.shopping.domain.vo.SupplierVo;
import org.dromara.shopping.mapper.SupplierMapper;
import org.dromara.shopping.mapper.SupplierUserMapper;
import org.dromara.shopping.service.ISupplierService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * 供应商Service业务层处理
 *
 * @author yzg
 * @date 2023-10-11
 */
@RequiredArgsConstructor
@Service
public class SupplierServiceImpl implements ISupplierService {

    private final SupplierMapper baseMapper;
    private final SupplierUserMapper supplierUserMapper;


    /**
     * 查询供应商
     */
    @Override
    public SupplierVo queryById(Long supplierId){
        SupplierVo supplierVo = baseMapper.selectVoById(supplierId);
        return supplierVo;
    }

    /**
     * 查询供应商列表
     */
    @Override
    public TableDataInfo<SupplierVo> queryPageList(SupplierBo bo, PageQuery pageQuery) {
        Long userId = LoginHelper.getUserId();
        LambdaQueryWrapper<SupplierUser> supplierUserWrapper = Wrappers.lambdaQuery();
        supplierUserWrapper.eq(SupplierUser::getUserId,userId);
        supplierUserWrapper.last("LIMIT 1");
        SupplierUserVo supplierUserVo = supplierUserMapper.selectVoOne(supplierUserWrapper);
        if(ObjectUtils.isNotEmpty(supplierUserVo)){
            bo.setSupplierId(supplierUserVo.getSupplierId());
        }
        LambdaQueryWrapper<Supplier> lqw = buildQueryWrapper(bo);
        Page<SupplierVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询供应商列表
     */
    @Override
    public List<SupplierVo> queryList(SupplierBo bo) {
        Long userId = LoginHelper.getUserId();
        LambdaQueryWrapper<SupplierUser> supplierUserWrapper = Wrappers.lambdaQuery();
        supplierUserWrapper.eq(SupplierUser::getUserId,userId);
        supplierUserWrapper.last("LIMIT 1");
        SupplierUserVo supplierUserVo = supplierUserMapper.selectVoOne(supplierUserWrapper);
        if(ObjectUtils.isNotEmpty(supplierUserVo)){
            bo.setSupplierId(supplierUserVo.getSupplierId());
        }
        LambdaQueryWrapper<Supplier> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Supplier> buildQueryWrapper(SupplierBo bo) {
        LambdaQueryWrapper<Supplier> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getSupplierName()), Supplier::getSupplierName, bo.getSupplierName());
        lqw.eq(StringUtils.isNotBlank(bo.getLinkman()), Supplier::getLinkman, bo.getLinkman());
        lqw.eq(StringUtils.isNotBlank(bo.getMobile()), Supplier::getMobile, bo.getMobile());
        lqw.eq(StringUtils.isNotBlank(bo.getInvoiceType()), Supplier::getInvoiceType, bo.getInvoiceType());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), Supplier::getStatus, bo.getStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getWarning()), Supplier::getWarning, bo.getWarning());
        lqw.eq(bo.getSupplierId() != null,Supplier::getSupplierId,bo.getSupplierId());
        return lqw;
    }

    /**
     * 新增供应商
     */
    @Override
    public Boolean insertByBo(SupplierBo bo) {
        Supplier add = BeanUtil.toBean(bo, Supplier.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setSupplierId(add.getSupplierId());
        }
        //新增用户
        if(ObjectUtils.isNotEmpty(bo.getNickName()) && ObjectUtils.isNotEmpty(bo.getPassword())){
            Long userId = 100L;
            //新增
            if(ObjectUtils.isNotEmpty(userId)){
                SupplierUser supplierUser = new SupplierUser();
                supplierUser.setSupplierId(add.getSupplierId());
                supplierUser.setUserId(userId);
                supplierUserMapper.insert(supplierUser);
            }
        }
        return flag;
    }

    /**
     * 修改供应商
     */
    @Override
    public Boolean updateByBo(SupplierBo bo) {
        Supplier update = BeanUtil.toBean(bo, Supplier.class);
        validEntityBeforeSave(update);
        boolean b = baseMapper.updateById(update) > 0;
        if(b && ObjectUtils.isNotEmpty(bo.getNickName()) && ObjectUtils.isNotEmpty(bo.getPassword())){
            LambdaQueryWrapper<SupplierUser> supplierUserWrapper = Wrappers.lambdaQuery();
            supplierUserWrapper.eq(SupplierUser::getSupplierId,bo.getSupplierId());
            List<SupplierUserVo> supplierUserVoList = supplierUserMapper.selectVoList(supplierUserWrapper);
            if(CollectionUtils.isEmpty(supplierUserVoList)){
                Long userId = 200L;
                //新增
                if(ObjectUtils.isNotEmpty(userId)){
                    SupplierUser supplierUser = new SupplierUser();
                    supplierUser.setSupplierId(bo.getSupplierId());
                    supplierUser.setUserId(userId);
                    supplierUserMapper.insert(supplierUser);
                }
            }
        }
        return b;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Supplier entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除供应商
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
