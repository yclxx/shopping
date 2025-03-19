package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.SupplierReconciliation;
import org.dromara.shopping.domain.bo.SupplierReconciliationBo;
import org.dromara.shopping.domain.vo.SupplierReconciliationDataVo;
import org.dromara.shopping.domain.vo.SupplierReconciliationVo;
import org.dromara.shopping.domain.vo.SupplierVo;
import org.dromara.shopping.mapper.SupplierMapper;
import org.dromara.shopping.mapper.SupplierReconciliationMapper;
import org.dromara.shopping.mapper.SupplierUserMapper;
import org.dromara.shopping.service.ISupplierReconciliationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 供应商结算Service业务层处理
 *
 * @author yzg
 * @date 2024-12-24
 */
@RequiredArgsConstructor
@Service
public class SupplierReconciliationServiceImpl implements ISupplierReconciliationService {

    private final SupplierReconciliationMapper baseMapper;
    private final SupplierUserMapper supplierUserMapper;
    private final SupplierMapper supplierMapper;

    /**
     * 查询供应商结算
     */
    @Override
    public SupplierReconciliationVo queryById(Long reconciliationId){
        return baseMapper.selectVoById(reconciliationId);
    }

    /**
     * 查询供应商结算列表
     */
    @Override
    public TableDataInfo<SupplierReconciliationVo> queryPageList(SupplierReconciliationBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SupplierReconciliation> lqw = buildQueryWrapper(bo);
        Page<SupplierReconciliationVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        TableDataInfo<SupplierReconciliationVo> build = TableDataInfo.build(result);
        List<SupplierReconciliationVo> rows = build.getRows();
        if(ObjectUtils.isNotEmpty(rows)){
            for (SupplierReconciliationVo row : rows) {
                SupplierVo supplierVo = supplierMapper.selectVoById(row.getSupplierId());
                if(ObjectUtils.isNotEmpty(supplierVo)){
                    row.setSupplierName(supplierVo.getSupplierName());
                }
            }
        }
        return build;
    }

    @Override
    public TableDataInfo<SupplierReconciliationDataVo> getSupplierOrderData(SupplierReconciliationBo bo, PageQuery pageQuery){
//        Long userId = LoginHelper.getUserId();
//        LambdaQueryWrapper<SupplierUser> supplierUserWrapper = Wrappers.lambdaQuery();
//        supplierUserWrapper.eq(SupplierUser::getUserId,userId);
//        supplierUserWrapper.last("LIMIT 1");
//        SupplierUserVo supplierUserVo = supplierUserMapper.selectVoOne(supplierUserWrapper);
        Page<SupplierReconciliationDataVo> result = null;
        Page page = new Page<>(pageQuery.getPageNum(),pageQuery.getPageSize());
        if(ObjectUtil.isNotEmpty(bo.getSupplierId())){
            return TableDataInfo.build();
        }
        return null;
    }

    @Override
    public BigDecimal getSupplierOrderAmount(SupplierReconciliationBo bo){
//        Long userId = LoginHelper.getUserId();
//        LambdaQueryWrapper<SupplierUser> supplierUserWrapper = Wrappers.lambdaQuery();
//        supplierUserWrapper.eq(SupplierUser::getUserId,userId);
//        supplierUserWrapper.last("LIMIT 1");
//        SupplierUserVo supplierUserVo = supplierUserMapper.selectVoOne(supplierUserWrapper);
        if(ObjectUtil.isNotEmpty(bo.getSupplierId())){
//            bo.setSupplierId(supplierUserVo.getSupplierId());
        }
        return null;
    }

    /**
     * 查询供应商结算列表
     */
    @Override
    public List<SupplierReconciliationVo> queryList(SupplierReconciliationBo bo) {
        LambdaQueryWrapper<SupplierReconciliation> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SupplierReconciliation> buildQueryWrapper(SupplierReconciliationBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SupplierReconciliation> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getReconciliationName()), SupplierReconciliation::getReconciliationName, bo.getReconciliationName());
        lqw.eq(bo.getSupplierId() != null, SupplierReconciliation::getSupplierId, bo.getSupplierId());
        lqw.eq(bo.getStartTime() != null, SupplierReconciliation::getStartTime, bo.getStartTime());
        lqw.eq(bo.getEndTime() != null, SupplierReconciliation::getEndTime, bo.getEndTime());
        lqw.eq(bo.getAmount() != null, SupplierReconciliation::getAmount, bo.getAmount());
        lqw.eq(bo.getSettlementTime() != null, SupplierReconciliation::getSettlementTime, bo.getSettlementTime());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), SupplierReconciliation::getStatus, bo.getStatus());
        lqw.between(params.get("beginCrDate") != null && params.get("endCrDate") != null, SupplierReconciliation::getCreateTime, params.get("beginCrDate"), params.get("endCrDate"));
        return lqw;
    }

    /**
     * 新增供应商结算
     */
    @Override
    public Boolean insertByBo(SupplierReconciliationBo bo) {
//        Long userId = LoginHelper.getUserId();
//        LambdaQueryWrapper<SupplierUser> supplierUserWrapper = Wrappers.lambdaQuery();
//        supplierUserWrapper.eq(SupplierUser::getUserId,userId);
//        supplierUserWrapper.last("LIMIT 1");
//        SupplierUserVo supplierUserVo = supplierUserMapper.selectVoOne(supplierUserWrapper);
//        if(ObjectUtil.isEmpty(supplierUserVo)){
//            throw new ServiceException("当前账号非供应商账号");
//        }
//        bo.setSupplierId(supplierUserVo.getSupplierId());
        bo.setStatus("3");
        SupplierReconciliation add = BeanUtil.toBean(bo, SupplierReconciliation.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setReconciliationId(add.getReconciliationId());
        }
        return flag;
    }

    /**
     * 修改供应商结算
     */
    @Override
    public Boolean updateByBo(SupplierReconciliationBo bo) {
        SupplierReconciliation update = BeanUtil.toBean(bo, SupplierReconciliation.class);
        validEntityBeforeSave(update);
        boolean b = baseMapper.updateById(update) > 0;
        if(b && bo.getStatus().equals("5")){
            //新增扣除
            SupplierReconciliation supplierReconciliation = baseMapper.selectById(bo.getReconciliationId());
            SupplierVo supplierVo = supplierMapper.selectVoById(supplierReconciliation.getSupplierId());
        }
        return b;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SupplierReconciliation entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除供应商结算
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
