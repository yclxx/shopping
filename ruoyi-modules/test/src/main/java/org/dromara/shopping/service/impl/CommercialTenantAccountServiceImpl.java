package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.CommercialTenantAccount;
import org.dromara.shopping.domain.bo.CommercialTenantAccountBo;
import org.dromara.shopping.domain.vo.CommercialTenantAccountVo;
import org.dromara.shopping.domain.vo.CommercialTenantVo;
import org.dromara.shopping.mapper.CommercialTenantAccountMapper;
import org.dromara.shopping.mapper.CommercialTenantMapper;
import org.dromara.shopping.service.ICommercialTenantAccountService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 商户账户Service业务层处理
 *
 * @author yzg
 * @date 2024-09-13
 */
@RequiredArgsConstructor
@Service
public class CommercialTenantAccountServiceImpl implements ICommercialTenantAccountService {

    private final CommercialTenantAccountMapper baseMapper;
    private final CommercialTenantMapper commercialTenantMapper;

    /**
     * 查询商户账户
     */
    @Override
    public CommercialTenantAccountVo queryById(Long commercialTenantId) {
        return baseMapper.selectVoById(commercialTenantId);
    }

    /**
     * 查询商户账户列表
     */
    @Override
    public TableDataInfo<CommercialTenantAccountVo> queryPageList(CommercialTenantAccountBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<CommercialTenantAccount> lqw = buildQueryWrapper(bo);
        Page<CommercialTenantAccountVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询商户账户列表
     */
    @Override
    public List<CommercialTenantAccountVo> queryList(CommercialTenantAccountBo bo) {
        LambdaQueryWrapper<CommercialTenantAccount> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<CommercialTenantAccount> buildQueryWrapper(CommercialTenantAccountBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<CommercialTenantAccount> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getBalance() != null, CommercialTenantAccount::getBalance, bo.getBalance());
        lqw.eq(bo.getFrozenBalance() != null, CommercialTenantAccount::getFrozenBalance, bo.getFrozenBalance());
        lqw.eq(bo.getWithdrawBalance() != null, CommercialTenantAccount::getWithdrawBalance, bo.getWithdrawBalance());
        lqw.eq(bo.getTotalBalance() != null, CommercialTenantAccount::getTotalBalance, bo.getTotalBalance());
        lqw.like(StringUtils.isNotBlank(bo.getCommercialTenantName()), CommercialTenantAccount::getCommercialTenantName, bo.getCommercialTenantName());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), CommercialTenantAccount::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增商户账户
     */
    @Override
    public Boolean insertByBo(CommercialTenantAccountBo bo) {
        if (null == bo.getCommercialTenantId()) {
            throw new RuntimeException("商户ID不能为空");
        }
        CommercialTenantVo commercialTenantVo = commercialTenantMapper.selectVoById(bo.getCommercialTenantId());
        if(null == commercialTenantVo){
            throw new RuntimeException("商户不存在");
        }
        CommercialTenantAccount account = new CommercialTenantAccount();
        account.setCommercialTenantId(commercialTenantVo.getCommercialTenantId());
        account.setCommercialTenantName(commercialTenantVo.getCommercialTenantName());
        account.setStatus("0");

        return baseMapper.insertOrUpdate(account);
    }

    /**
     * 修改商户账户
     */
    @Override
    public Boolean updateByBo(CommercialTenantAccountBo bo) {
        CommercialTenantAccount update = BeanUtil.toBean(bo, CommercialTenantAccount.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(CommercialTenantAccount entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除商户账户
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
