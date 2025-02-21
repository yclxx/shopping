package org.dromara.shopping.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.HistoryMissionUserRecord;
import org.dromara.shopping.domain.MissionUserRecord;
import org.dromara.shopping.domain.bo.MissionUserRecordBo;
import org.dromara.shopping.domain.vo.MissionUserRecordVo;
import org.dromara.shopping.domain.vo.MissionVo;
import org.dromara.shopping.mapper.HistoryMissionUserRecordMapper;
import org.dromara.shopping.mapper.MissionMapper;
import org.dromara.shopping.mapper.MissionUserRecordMapper;
import org.dromara.shopping.service.IMissionUserRecordService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 活动记录Service业务层处理
 *
 * @author yzg
 * @date 2023-05-10
 */
@RequiredArgsConstructor
@Service
public class MissionUserRecordServiceImpl implements IMissionUserRecordService {

    private final MissionUserRecordMapper baseMapper;
    private final HistoryMissionUserRecordMapper historyMapper;
    private final MissionMapper missionMapper;

    /**
     * 查询活动记录
     */
    @Override
    public MissionUserRecordVo queryById(Long missionUserRecordId) {
        return baseMapper.selectVoById(missionUserRecordId);
    }

    /**
     * 查询活动记录列表
     */
    @Override
    public TableDataInfo<MissionUserRecordVo> queryPageList(MissionUserRecordBo bo, PageQuery pageQuery) {
        Page<MissionUserRecordVo> result;
        if (StringUtils.isNotBlank(bo.getQueryHistory()) && "true".equals(bo.getQueryHistory())) {
            LambdaQueryWrapper<HistoryMissionUserRecord> lqw = buildQueryHistoryWrapper(bo);
            result = historyMapper.selectVoPage(pageQuery.build(), lqw);
        } else {
            LambdaQueryWrapper<MissionUserRecord> lqw = buildQueryWrapper(bo);
            result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        }

        return TableDataInfo.build(result);
    }

    /**
     * 查询活动记录列表
     */
    @Override
    public List<MissionUserRecordVo> queryList(MissionUserRecordBo bo) {
        LambdaQueryWrapper<MissionUserRecord> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MissionUserRecord> buildQueryWrapper(MissionUserRecordBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MissionUserRecord> lqw = Wrappers.lambdaQuery();
        if (bo.getMissionUserId() != null) {
            List<Long> missionUserIds = getMissionUserIds(bo.getMissionUserId(), null, bo.getMissionGroupId());
            if (ObjectUtil.isEmpty(missionUserIds)) {
                lqw.eq(MissionUserRecord::getMissionUserId, bo.getMissionUserId());
            } else {
                lqw.in(MissionUserRecord::getMissionUserId, missionUserIds);
            }
        }
        lqw.eq(bo.getMissionGroupId() != null, MissionUserRecord::getMissionGroupId, bo.getMissionGroupId());
        lqw.eq(bo.getMissionId() != null, MissionUserRecord::getMissionId, bo.getMissionId());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), MissionUserRecord::getStatus, bo.getStatus());
        lqw.between(params.get("beginExpiryTime") != null && params.get("endExpiryTime") != null,
            MissionUserRecord::getExpiryTime, params.get("beginExpiryTime"), params.get("endExpiryTime"));
        lqw.eq(bo.getDrawId() != null, MissionUserRecord::getDrawId, bo.getDrawId());
        lqw.eq(StringUtils.isNotBlank(bo.getDrawType()), MissionUserRecord::getDrawType, bo.getDrawType());
        lqw.like(StringUtils.isNotBlank(bo.getDrawName()), MissionUserRecord::getDrawName, bo.getDrawName());
        lqw.eq(StringUtils.isNotBlank(bo.getSendStatus()), MissionUserRecord::getSendStatus, bo.getSendStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getSendAccount()), MissionUserRecord::getSendAccount, bo.getSendAccount());
        lqw.eq(StringUtils.isNotBlank(bo.getPushNumber()), MissionUserRecord::getPushNumber, bo.getPushNumber());
        lqw.between(params.get("beginDrawTime") != null && params.get("endDrawTime") != null,
            MissionUserRecord::getDrawTime, params.get("beginDrawTime"), params.get("endDrawTime"));
        return lqw;
    }

    private LambdaQueryWrapper<HistoryMissionUserRecord> buildQueryHistoryWrapper(MissionUserRecordBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<HistoryMissionUserRecord> lqw = Wrappers.lambdaQuery();
        if (bo.getMissionUserId() != null) {
            List<Long> missionUserIds = getMissionUserIds(bo.getMissionUserId(), null, bo.getMissionGroupId());
            if (ObjectUtil.isEmpty(missionUserIds)) {
                lqw.eq(HistoryMissionUserRecord::getMissionUserId, bo.getMissionUserId());
            } else {
                lqw.in(HistoryMissionUserRecord::getMissionUserId, missionUserIds);
            }
        }
        lqw.eq(bo.getMissionGroupId() != null, HistoryMissionUserRecord::getMissionGroupId, bo.getMissionGroupId());
        lqw.eq(bo.getMissionId() != null, HistoryMissionUserRecord::getMissionId, bo.getMissionId());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), HistoryMissionUserRecord::getStatus, bo.getStatus());
        lqw.between(params.get("beginExpiryTime") != null && params.get("endExpiryTime") != null,
            HistoryMissionUserRecord::getExpiryTime, params.get("beginExpiryTime"), params.get("endExpiryTime"));
        lqw.eq(bo.getDrawId() != null, HistoryMissionUserRecord::getDrawId, bo.getDrawId());
        lqw.eq(StringUtils.isNotBlank(bo.getDrawType()), HistoryMissionUserRecord::getDrawType, bo.getDrawType());
        lqw.like(StringUtils.isNotBlank(bo.getDrawName()), HistoryMissionUserRecord::getDrawName, bo.getDrawName());
        lqw.eq(StringUtils.isNotBlank(bo.getSendStatus()), HistoryMissionUserRecord::getSendStatus, bo.getSendStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getSendAccount()), HistoryMissionUserRecord::getSendAccount, bo.getSendAccount());
        lqw.eq(StringUtils.isNotBlank(bo.getPushNumber()), HistoryMissionUserRecord::getPushNumber, bo.getPushNumber());
        lqw.between(params.get("beginDrawTime") != null && params.get("endDrawTime") != null,
            HistoryMissionUserRecord::getDrawTime, params.get("beginDrawTime"), params.get("endDrawTime"));
        return lqw;
    }

    /**
     * 新增活动记录
     */
    @Override
    public Boolean insertByBo(MissionUserRecordBo bo) {
        if (null == bo.getMissionId() || null == bo.getMissionUserId()) {
            throw new ServiceException("缺少必要参数");
        }
        MissionVo missionVo = missionMapper.selectVoById(bo.getMissionId());
        if (null == missionVo || !"0".equals(missionVo.getStatus())) {
            throw new ServiceException("任务不存在或已停用");
        }
        List<Long> missionUserIds = getMissionUserIds(bo.getMissionUserId(), missionVo.getPlatformKey(), missionVo.getMissionGroupId());
        if (ObjectUtil.isEmpty(missionUserIds)) {
            throw new ServiceException("用户信息不存在");
        }
        if (missionUserIds.size() != 1) {
            throw new ServiceException("用户信息存在多条，不可操作");
        }
        MissionUserRecord add = new MissionUserRecord();
        add.setMissionId(bo.getMissionId());
        add.setMissionGroupId(missionVo.getMissionGroupId());
        add.setMissionUserId(missionUserIds.get(0));
        Date expiryDate = null;
        if ("1".equals(missionVo.getAwardExpiryType())) {
            try {
                expiryDate = DateUtil.parseDate(missionVo.getAwardExpiryDate()).toJdkDate();
            } catch (Exception ignored) {
            }
        } else if ("2".equals(missionVo.getAwardExpiryType())) {
            if (NumberUtil.isLong(missionVo.getAwardExpiryDate())) {
                int addDay = Integer.parseInt(missionVo.getAwardExpiryDate());
                if (addDay < 1) {
                    // TODO 填写当天的结束时间
                    expiryDate = DateUtil.endOfDay(new Date()).offset(DateField.MILLISECOND, -999).toJdkDate();
                } else {
                    expiryDate = DateUtil.endOfDay(DateUtil.offsetDay(new Date(), addDay)).offset(DateField.MILLISECOND, -999).toJdkDate();
                }
            }
        } else if ("3".equals(missionVo.getAwardExpiryType())) {
            String format = DateUtil.format(DateUtil.offsetMonth(new Date(), 1), DatePattern.NORM_MONTH_PATTERN);
            format = format + missionVo.getAwardExpiryDate();
            try {
                expiryDate = DateUtil.parseDate(format).toJdkDate();
            } catch (Exception ignored) {
            }
        }
        add.setExpiryTime(expiryDate);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setMissionUserRecordId(add.getMissionUserRecordId());
        }
        return flag;
    }

    /**
     * 修改活动记录
     */
    @Override
    public Boolean expiry(Collection<Long> ids) {
        MissionUserRecord add = new MissionUserRecord();
        add.setStatus("3");
        return baseMapper.update(add, new LambdaQueryWrapper<MissionUserRecord>().in(MissionUserRecord::getMissionUserRecordId, ids).eq(MissionUserRecord::getStatus, "0")) > 0;
    }

    private List<Long> getMissionUserIds(Long missionUserId, Long platformKey, Long missionGroupId) {
        if (null == missionUserId) {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }
}
