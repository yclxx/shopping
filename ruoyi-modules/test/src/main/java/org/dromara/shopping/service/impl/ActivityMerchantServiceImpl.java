package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.ActivityMerchant;
import org.dromara.shopping.domain.bo.ActivityMerchantBo;
import org.dromara.shopping.domain.vo.ActivityMerchantVo;
import org.dromara.shopping.mapper.ActivityMerchantMapper;
import org.dromara.shopping.service.IActivityMerchantService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 门店商户号Service业务层处理
 *
 * @author yzg
 * @date 2023-12-14
 */
@RequiredArgsConstructor
@Service
public class ActivityMerchantServiceImpl implements IActivityMerchantService {

    private final ActivityMerchantMapper baseMapper;

    /**
     * 查询活动商户号
     */
    @Override
    public ActivityMerchantVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询活动商户号列表
     */
    @Override
    public TableDataInfo<ActivityMerchantVo> queryPageList(ActivityMerchantBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ActivityMerchant> lqw = buildQueryWrapper(bo);
        Page<ActivityMerchantVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询活动商户号列表
     */
    @Override
    public List<ActivityMerchantVo> queryList(ActivityMerchantBo bo) {
        LambdaQueryWrapper<ActivityMerchant> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ActivityMerchant> buildQueryWrapper(ActivityMerchantBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ActivityMerchant> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getMerchantNo()), ActivityMerchant::getMerchantNo, bo.getMerchantNo());
        lqw.eq(StringUtils.isNotBlank(bo.getMerchantType()), ActivityMerchant::getMerchantType, bo.getMerchantType());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), ActivityMerchant::getStatus, bo.getStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getSettlementWay()), ActivityMerchant::getSettlementWay, bo.getSettlementWay());
        lqw.eq(StringUtils.isNotBlank(bo.getSettlement()), ActivityMerchant::getSettlement, bo.getSettlement());
        lqw.eq(StringUtils.isNotBlank(bo.getPaymentMethod()), ActivityMerchant::getPaymentMethod, bo.getPaymentMethod());
        lqw.eq(StringUtils.isNotBlank(bo.getAcquirer()), ActivityMerchant::getAcquirer, bo.getAcquirer());
        lqw.eq(StringUtils.isNotBlank(bo.getTerminalNo()), ActivityMerchant::getTerminalNo, bo.getTerminalNo());
        return lqw;
    }

    /**
     * 新增活动商户号
     */
    @Override
    public Boolean insertByBo(ActivityMerchantBo bo) {
        ActivityMerchant add = BeanUtil.toBean(bo, ActivityMerchant.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改活动商户号
     */
    @Override
    public Boolean updateByBo(ActivityMerchantBo bo) {
        ActivityMerchant update = BeanUtil.toBean(bo, ActivityMerchant.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ActivityMerchant entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除活动商户号
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
