package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.CommercialTenantAccountDetail;
import org.dromara.shopping.domain.bo.CommercialTenantAccountDetailBo;
import org.dromara.shopping.domain.vo.CommercialTenantAccountDetailVo;
import org.dromara.shopping.mapper.CommercialTenantAccountDetailMapper;
import org.dromara.shopping.service.ICommercialTenantAccountDetailService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 商户账户明细Service业务层处理
 *
 * @author yzg
 * @date 2024-09-13
 */
@RequiredArgsConstructor
@Service
public class CommercialTenantAccountDetailServiceImpl implements ICommercialTenantAccountDetailService {

    private final CommercialTenantAccountDetailMapper baseMapper;

    /**
     * 查询商户账户明细
     */
    @Override
    public CommercialTenantAccountDetailVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询商户账户明细列表
     */
    @Override
    public TableDataInfo<CommercialTenantAccountDetailVo> queryPageList(CommercialTenantAccountDetailBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<CommercialTenantAccountDetail> lqw = buildQueryWrapper(bo);
        Page<CommercialTenantAccountDetailVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询商户账户明细列表
     */
    @Override
    public List<CommercialTenantAccountDetailVo> queryList(CommercialTenantAccountDetailBo bo) {
        LambdaQueryWrapper<CommercialTenantAccountDetail> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<CommercialTenantAccountDetail> buildQueryWrapper(CommercialTenantAccountDetailBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<CommercialTenantAccountDetail> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getCommercialTenantId() != null, CommercialTenantAccountDetail::getCommercialTenantId, bo.getCommercialTenantId());
        lqw.eq(bo.getAmount() != null, CommercialTenantAccountDetail::getAmount, bo.getAmount());
        lqw.eq(StringUtils.isNotBlank(bo.getDetailType()), CommercialTenantAccountDetail::getDetailType, bo.getDetailType());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), CommercialTenantAccountDetail::getStatus, bo.getStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getPushNumber()), CommercialTenantAccountDetail::getPushNumber, bo.getPushNumber());
        lqw.eq(StringUtils.isNotBlank(bo.getFailReason()), CommercialTenantAccountDetail::getFailReason, bo.getFailReason());
        lqw.like(StringUtils.isNotBlank(bo.getCommercialTenantName()),CommercialTenantAccountDetail::getCommercialTenantName,bo.getCommercialTenantName());
        return lqw;
    }

    /**
     * 新增商户账户明细
     */
    @Override
    public Boolean insertByBo(CommercialTenantAccountDetailBo bo) {
        CommercialTenantAccountDetail add = BeanUtil.toBean(bo, CommercialTenantAccountDetail.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改商户账户明细
     */
    @Override
    public Boolean updateByBo(CommercialTenantAccountDetailBo bo) {
        CommercialTenantAccountDetail update = BeanUtil.toBean(bo, CommercialTenantAccountDetail.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(CommercialTenantAccountDetail entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除商户账户明细
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
