package org.dromara.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.ShareUserRecord;
import org.dromara.shopping.domain.bo.ShareUserRecordBo;
import org.dromara.shopping.domain.bo.UserBo;
import org.dromara.shopping.domain.vo.ShareStatisticsVo;
import org.dromara.shopping.mapper.ShareUserRecordMapper;
import org.dromara.shopping.service.IShareStatisticsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xiexi
 * @description
 * @date 2024/12/18 14:08
 */
@Service
@RequiredArgsConstructor
public class ShareStatisticsServiceImpl implements IShareStatisticsService {
    private final ShareUserRecordMapper shareUserRecordMapper;

    @Override
    public TableDataInfo<ShareStatisticsVo> queryPageList(ShareUserRecordBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ShareUserRecord> lqw = buildWrapper(bo);
        if (null == lqw) {
            return TableDataInfo.build();
        }
        TableDataInfo<ShareStatisticsVo> result = TableDataInfo.build();

        this.setExtraInfo(result.getRows(), bo);

        return result;
    }

    @Override
    public List<ShareStatisticsVo> queryList(ShareUserRecordBo bo) {
        LambdaQueryWrapper<ShareUserRecord> lqw = buildWrapper(bo);
        if (null == lqw) {
            return new ArrayList<>();
        }

        this.setExtraInfo(new ArrayList<>(), bo);

        return new ArrayList<>();
    }

    private void setExtraInfo(List<ShareStatisticsVo> shareStatisticsVos, ShareUserRecordBo bo) {
        for (ShareStatisticsVo shareStatisticsVo : shareStatisticsVos) {
            // 查询分销成功数量
            this.setShareCount(shareStatisticsVo, bo);
        }
    }

    private void setShareCount(ShareStatisticsVo resultVo, ShareUserRecordBo bo) {
        LambdaQueryWrapper<ShareUserRecord> lqw = buildWrapper(bo);
        if (null == lqw) {
            return;
        }
        lqw.eq(ShareUserRecord::getUserId, resultVo.getUserId());

    }

    private LambdaQueryWrapper<ShareUserRecord> buildWrapper(ShareUserRecordBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ShareUserRecord> lqw = Wrappers.lambdaQuery();
        if (null != bo.getUserId()) {
            if (bo.getUserId().toString().length() == 11) {
                UserBo userBo = new UserBo();
                userBo.setMobile(bo.getUserId().toString());
            } else {
                lqw.eq(ShareUserRecord::getUserId, bo.getUserId());
            }
        }
        if (null != bo.getPlatformKey()) {
            lqw.inSql(ShareUserRecord::getUserId, "select user_id from t_share_user where platform_key = " + bo.getPlatformKey());
        }
        if (null != bo.getInviteeUserId()) {
            if (bo.getInviteeUserId().toString().length() == 11) {
                UserBo userBo = new UserBo();
                userBo.setMobile(bo.getInviteeUserId().toString());
            } else {
                lqw.eq(ShareUserRecord::getInviteeUserId, bo.getInviteeUserId());
            }
        }
        lqw.eq(bo.getNumber() != null, ShareUserRecord::getNumber, bo.getNumber());
        lqw.eq(bo.getOrderUsedTime() != null, ShareUserRecord::getOrderUsedTime, bo.getOrderUsedTime());
        lqw.eq(bo.getAwardAmount() != null, ShareUserRecord::getAwardAmount, bo.getAwardAmount());
        lqw.eq(StringUtils.isNotBlank(bo.getInviteeStatus()), ShareUserRecord::getInviteeStatus, bo.getInviteeStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getAwardStatus()), ShareUserRecord::getAwardStatus, bo.getAwardStatus());
        lqw.eq(bo.getAwardTime() != null, ShareUserRecord::getAwardTime, bo.getAwardTime());
        lqw.eq(StringUtils.isNotBlank(bo.getAwardAccount()), ShareUserRecord::getAwardAccount, bo.getAwardAccount());
        lqw.between(params.get("beginCreateTime") != null && params.get("endCreateTime") != null,
            ShareUserRecord::getCreateTime, params.get("beginCreateTime"), params.get("endCreateTime"));

        return lqw;
    }
}
