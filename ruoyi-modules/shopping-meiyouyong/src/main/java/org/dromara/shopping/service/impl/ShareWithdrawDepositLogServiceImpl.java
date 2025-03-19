package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.lock.LockInfo;
import com.baomidou.lock.LockTemplate;
import com.baomidou.lock.executor.RedissonLockExecutor;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.ShareUserAccount;
import org.dromara.shopping.domain.ShareWithdrawDepositLog;
import org.dromara.shopping.domain.UserChannel;
import org.dromara.shopping.domain.bo.ShareWithdrawDepositLogBo;
import org.dromara.shopping.domain.vo.ShareWithdrawDepositLogVo;
import org.dromara.shopping.domain.vo.UserChannelVo;
import org.dromara.shopping.mapper.ShareUserAccountMapper;
import org.dromara.shopping.mapper.ShareWithdrawDepositLogMapper;
import org.dromara.shopping.mapper.UserChannelMapper;
import org.dromara.shopping.service.IShareWithdrawDepositLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 提现审核Service业务层处理
 *
 * @author yzg
 * @date 2024-10-31
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ShareWithdrawDepositLogServiceImpl implements IShareWithdrawDepositLogService {

    private final ShareWithdrawDepositLogMapper baseMapper;
    private final UserChannelMapper userChannelMapper;
    private final ShareUserAccountMapper userAccountMapper;
    private final LockTemplate lockTemplate;

    /**
     * 查询提现审核
     */
    @Override
    public ShareWithdrawDepositLogVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询提现审核列表
     */
    @Override
    public TableDataInfo<ShareWithdrawDepositLogVo> queryPageList(ShareWithdrawDepositLogBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ShareWithdrawDepositLog> lqw = buildQueryWrapper(bo);
        if (null == lqw) {
            return TableDataInfo.build(new ArrayList<>());
        }
        Page<ShareWithdrawDepositLogVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询提现审核列表
     */
    @Override
    public List<ShareWithdrawDepositLogVo> queryList(ShareWithdrawDepositLogBo bo) {
        LambdaQueryWrapper<ShareWithdrawDepositLog> lqw = buildQueryWrapper(bo);
        if (null == lqw) {
            return new ArrayList<>();
        }
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ShareWithdrawDepositLog> buildQueryWrapper(ShareWithdrawDepositLogBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ShareWithdrawDepositLog> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getId() != null, ShareWithdrawDepositLog::getId, bo.getId());
        if (null != bo.getUserId()) {
            lqw.eq(ShareWithdrawDepositLog::getUserId, bo.getUserId());
        }
        lqw.eq(bo.getAmount() != null, ShareWithdrawDepositLog::getAmount, bo.getAmount());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), ShareWithdrawDepositLog::getStatus, bo.getStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getSendStatus()), ShareWithdrawDepositLog::getSendStatus, bo.getSendStatus());
        lqw.eq(bo.getSendTime() != null, ShareWithdrawDepositLog::getSendTime, bo.getSendTime());
        lqw.eq(StringUtils.isNotBlank(bo.getSendAccount()), ShareWithdrawDepositLog::getSendAccount, bo.getSendAccount());
        lqw.eq(StringUtils.isNotBlank(bo.getPushNumber()), ShareWithdrawDepositLog::getPushNumber, bo.getPushNumber());
        lqw.eq(StringUtils.isNotBlank(bo.getPushRemake()), ShareWithdrawDepositLog::getPushRemake, bo.getPushRemake());
        lqw.eq(StringUtils.isNotBlank(bo.getRemake()), ShareWithdrawDepositLog::getRemake, bo.getRemake());
        lqw.between(params.get("beginCreateTime") != null && params.get("endCreateTime") != null, ShareWithdrawDepositLog::getCreateTime, params.get("beginCreateTime"), params.get("endCreateTime"));
        return lqw;
    }

    /**
     * 新增提现审核
     */
    @Override
    public Boolean insertByBo(ShareWithdrawDepositLogBo bo) {
        ShareWithdrawDepositLog add = BeanUtil.toBean(bo, ShareWithdrawDepositLog.class);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改提现审核
     */
    @Override
    public Boolean updateByBo(ShareWithdrawDepositLogBo bo) {
        ShareWithdrawDepositLog update = BeanUtil.toBean(bo, ShareWithdrawDepositLog.class);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 批量删除提现审核
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 查询需要回退金额的提现
     */
    public List<ShareWithdrawDepositLogVo> queryListByCallback() {
        LambdaQueryWrapper<ShareWithdrawDepositLog> lqw = Wrappers.lambdaQuery();
        lqw.eq(ShareWithdrawDepositLog::getCallbackAmount, "0");
        lqw.and(lw -> lw.eq(ShareWithdrawDepositLog::getStatus, "2").or().eq(ShareWithdrawDepositLog::getSendStatus, "3"));

        return baseMapper.selectVoList(lqw);
    }

    /**
     * 回滚金额
     */
    @Transactional
    @Override
    public void callbackAmount(ShareWithdrawDepositLogVo depositLogVo) {
        // 先修改成已回退
        ShareWithdrawDepositLog update = new ShareWithdrawDepositLog();
        update.setCallbackAmount("1");

        LambdaQueryWrapper<ShareWithdrawDepositLog> lqw = Wrappers.lambdaQuery();
        lqw.in(ShareWithdrawDepositLog::getId, depositLogVo.getId());
        lqw.eq(ShareWithdrawDepositLog::getCallbackAmount, "0");
        lqw.and(lw -> lw.eq(ShareWithdrawDepositLog::getStatus, "2").or().eq(ShareWithdrawDepositLog::getSendStatus, "3"));
        int updateCount = baseMapper.update(update, lqw);
        if (updateCount < 1) {
            return;
        }
        if (depositLogVo.getAmount().signum() < 1) {
            return;
        }
        ShareUserAccount shareUserAccount = userAccountMapper.selectById(depositLogVo.getUserId());
        if (null == shareUserAccount) {
            return;
        }
        shareUserAccount.setFreezeBalance(shareUserAccount.getFreezeBalance().subtract(depositLogVo.getAmount()));
        if (shareUserAccount.getFreezeBalance().signum() < 0) {
            shareUserAccount.setBalance(shareUserAccount.getBalance().add(depositLogVo.getAmount().add(shareUserAccount.getFreezeBalance())));
            shareUserAccount.setFreezeBalance(BigDecimal.ZERO);
            log.info("冻结金额不足，扣除回退金额：{},用户账户：{}", shareUserAccount.getFreezeBalance(), shareUserAccount);
        } else {
            shareUserAccount.setBalance(shareUserAccount.getBalance().add(depositLogVo.getAmount()));
        }
        userAccountMapper.updateById(shareUserAccount);
    }

    @Override
    public void sendAmount(Long depositLogId) {
        // 加锁
        String lockKey = "share_withdraw_deposit_log_" + depositLogId;
        final LockInfo lockInfo = lockTemplate.lock(lockKey, 30000L, 5000L, RedissonLockExecutor.class);
        if (null == lockInfo) {
            return;
        }
        try {
            // 查询记录
            ShareWithdrawDepositLogVo shareWithdrawDepositLogVo = baseMapper.selectVoById(depositLogId);
            if (!"1".equals(shareWithdrawDepositLogVo.getStatus())) {
                // 不是审核通过的状态，直接返回
                return;
            }
            if (!"0".equals(shareWithdrawDepositLogVo.getSendStatus())) {
                return;
            }
            LambdaQueryWrapper<UserChannel> userChannelLambdaQueryWrapper = Wrappers.lambdaQuery();
            userChannelLambdaQueryWrapper.eq(UserChannel::getUserId, shareWithdrawDepositLogVo.getUserId());
            userChannelLambdaQueryWrapper.eq(UserChannel::getChannel, "1");
            userChannelLambdaQueryWrapper.last("order by id desc limit 1");
            UserChannelVo userChannelVo = userChannelMapper.selectVoOne(userChannelLambdaQueryWrapper);
            if (null == userChannelVo || StringUtils.isBlank(userChannelVo.getOpenId())) {
                log.error("用户没有绑定微信小程序，无法发放提现，记录ID：{}，用户信息：{}", shareWithdrawDepositLogVo.getId(), userChannelVo);
                return;
            }
            // 修改状态为发放中
            ShareWithdrawDepositLog update = new ShareWithdrawDepositLog();
            update.setId(shareWithdrawDepositLogVo.getId());
            update.setSendStatus("1");
            update.setSendTime(new Date());
            update.setPushNumber(IdUtil.getSnowflakeNextIdStr());
            // 查询发放账号
            update.setSendAccount(userChannelVo.getOpenId());

            int i = baseMapper.updateById(update);
            if (i < 1) {
                return;
            }
            update = new ShareWithdrawDepositLog();
            update.setId(shareWithdrawDepositLogVo.getId());
            update.setSendStatus("3");
            baseMapper.updateById(update);
        } finally {
            lockTemplate.releaseLock(lockInfo);
        }
    }

    @Override
    public List<ShareWithdrawDepositLogVo> querySendStatusList() {
        LambdaQueryWrapper<ShareWithdrawDepositLog> lqw = Wrappers.lambdaQuery();
        lqw.eq(ShareWithdrawDepositLog::getStatus, "1");
        lqw.in(ShareWithdrawDepositLog::getSendStatus, "0", "1");
        return baseMapper.selectVoList(lqw);
    }

    @Transactional
    @Override
    public void queryCloudStatus(ShareWithdrawDepositLogVo depositLogVo) {
        // 发放失败
        ShareWithdrawDepositLog depositLog = new ShareWithdrawDepositLog();
        depositLog.setId(depositLogVo.getId());
        depositLog.setSendStatus("3");

        int i = baseMapper.updateById(depositLog);
        if (i < 1) {
            throw new ServiceException("订单更新失败");
        }
    }
}
