package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.ShareUserAccount;
import org.dromara.shopping.domain.bo.ShareUserAccountBo;
import org.dromara.shopping.domain.vo.ShareUserAccountVo;
import org.dromara.shopping.mapper.ShareUserAccountMapper;
import org.dromara.shopping.service.IShareUserAccountService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 分销用户账户Service业务层处理
 *
 * @author yzg
 * @date 2023-11-09
 */
@RequiredArgsConstructor
@Service
public class ShareUserAccountServiceImpl implements IShareUserAccountService {

    private final ShareUserAccountMapper baseMapper;

    /**
     * 查询分销用户账户
     */
    @Override
    public ShareUserAccountVo queryById(Long userId){
        return baseMapper.selectVoById(userId);
    }

    /**
     * 查询分销用户账户列表
     */
    @Override
    public TableDataInfo<ShareUserAccountVo> queryPageList(ShareUserAccountBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ShareUserAccount> lqw = buildQueryWrapper(bo);
        Page<ShareUserAccountVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询分销用户账户列表
     */
    @Override
    public List<ShareUserAccountVo> queryList(ShareUserAccountBo bo) {
        LambdaQueryWrapper<ShareUserAccount> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ShareUserAccount> buildQueryWrapper(ShareUserAccountBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ShareUserAccount> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getUserId() != null, ShareUserAccount::getUserId, bo.getUserId());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), ShareUserAccount::getStatus, bo.getStatus());
        lqw.eq(bo.getFreezeBalance() != null, ShareUserAccount::getFreezeBalance, bo.getFreezeBalance());
        lqw.eq(bo.getBalance() != null, ShareUserAccount::getBalance, bo.getBalance());
        lqw.eq(bo.getWithdrawDeposit() != null, ShareUserAccount::getWithdrawDeposit, bo.getWithdrawDeposit());
        lqw.eq(bo.getRefundBalance() != null, ShareUserAccount::getRefundBalance, bo.getRefundBalance());
        lqw.eq(bo.getUpdateTime() != null, ShareUserAccount::getUpdateTime, bo.getUpdateTime());
        return lqw;
    }

    /**
     * 新增分销用户账户
     */
    @Override
    public Boolean insertByBo(ShareUserAccountBo bo) {
        ShareUserAccount add = BeanUtil.toBean(bo, ShareUserAccount.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setUserId(add.getUserId());
        }
        return flag;
    }

    /**
     * 修改分销用户账户
     */
    @Override
    public Boolean updateByBo(ShareUserAccountBo bo) {
        ShareUserAccount update = BeanUtil.toBean(bo, ShareUserAccount.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ShareUserAccount entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除分销用户账户
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
