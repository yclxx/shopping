package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.ShareWithdrawDepositLogBo;
import org.dromara.shopping.domain.vo.ShareWithdrawDepositLogVo;

import java.util.Collection;
import java.util.List;

/**
 * 提现审核Service接口
 *
 * @author yzg
 * @date 2024-10-31
 */
public interface IShareWithdrawDepositLogService {

    /**
     * 查询提现审核
     */
    ShareWithdrawDepositLogVo queryById(Long id);

    /**
     * 查询提现审核列表
     */
    TableDataInfo<ShareWithdrawDepositLogVo> queryPageList(ShareWithdrawDepositLogBo bo, PageQuery pageQuery);

    /**
     * 查询提现审核列表
     */
    List<ShareWithdrawDepositLogVo> queryList(ShareWithdrawDepositLogBo bo);

    /**
     * 修改提现审核
     */
    Boolean insertByBo(ShareWithdrawDepositLogBo bo);

    /**
     * 修改提现审核
     */
    Boolean updateByBo(ShareWithdrawDepositLogBo bo);

    /**
     * 校验并批量删除提现审核信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);


    /**
     * 查询需要回退金额的提现
     */
    List<ShareWithdrawDepositLogVo> queryListByCallback();

    /**
     * 回滚金额
     */
    void callbackAmount(ShareWithdrawDepositLogVo depositLogVo);

    void sendAmount(Long depositLogId);

    List<ShareWithdrawDepositLogVo> querySendStatusList();

    void queryCloudStatus(ShareWithdrawDepositLogVo depositLogVo);
}
