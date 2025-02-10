package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.CommercialTenantAgreement;
import org.dromara.shopping.domain.bo.CommercialTenantAgreementBo;
import org.dromara.shopping.domain.vo.CommercialTenantAgreementVo;
import org.dromara.shopping.mapper.CommercialTenantAgreementMapper;
import org.dromara.shopping.service.ICommercialTenantAgreementService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 商户合同Service业务层处理
 *
 * @author yzg
 * @date 2024-09-10
 */
@RequiredArgsConstructor
@Service
public class CommercialTenantAgreementServiceImpl implements ICommercialTenantAgreementService {

    private final CommercialTenantAgreementMapper baseMapper;

    /**
     * 查询商户合同
     */
    @Override
    public CommercialTenantAgreementVo queryById(Long agreementId){
        return baseMapper.selectVoById(agreementId);
    }

    /**
     * 查询商户合同列表
     */
    @Override
    public TableDataInfo<CommercialTenantAgreementVo> queryPageList(CommercialTenantAgreementBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<CommercialTenantAgreement> lqw = buildQueryWrapper(bo);
        Page<CommercialTenantAgreementVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询商户合同列表
     */
    @Override
    public List<CommercialTenantAgreementVo> queryList(CommercialTenantAgreementBo bo) {
        LambdaQueryWrapper<CommercialTenantAgreement> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<CommercialTenantAgreement> buildQueryWrapper(CommercialTenantAgreementBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<CommercialTenantAgreement> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getAgreementName()), CommercialTenantAgreement::getAgreementName, bo.getAgreementName());
        lqw.eq(bo.getAgreementTime() != null, CommercialTenantAgreement::getAgreementTime, bo.getAgreementTime());
        lqw.eq(StringUtils.isNotBlank(bo.getAgreementUrl()), CommercialTenantAgreement::getAgreementUrl, bo.getAgreementUrl());
        lqw.eq(bo.getAmount() != null, CommercialTenantAgreement::getAmount, bo.getAmount());
        lqw.eq(StringUtils.isNotBlank(bo.getServiceRate()), CommercialTenantAgreement::getServiceRate, bo.getServiceRate());
        lqw.eq(bo.getContractDate() != null, CommercialTenantAgreement::getContractDate, bo.getContractDate());
        lqw.eq(bo.getContractDateStart() != null, CommercialTenantAgreement::getContractDateStart, bo.getContractDateStart());
        lqw.eq(bo.getContractDateEnd() != null, CommercialTenantAgreement::getContractDateEnd, bo.getContractDateEnd());
        lqw.eq(bo.getCommercialTenantId() != null, CommercialTenantAgreement::getCommercialTenantId, bo.getCommercialTenantId());
        return lqw;
    }

    /**
     * 新增商户合同
     */
    @Override
    public Boolean insertByBo(CommercialTenantAgreementBo bo) {
        CommercialTenantAgreement add = BeanUtil.toBean(bo, CommercialTenantAgreement.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setAgreementId(add.getAgreementId());
        }
        return flag;
    }

    /**
     * 修改商户合同
     */
    @Override
    public Boolean updateByBo(CommercialTenantAgreementBo bo) {
        CommercialTenantAgreement update = BeanUtil.toBean(bo, CommercialTenantAgreement.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(CommercialTenantAgreement entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除商户合同
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
