package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.json.utils.JsonUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.PlatformChannel;
import org.dromara.shopping.domain.SendDyInfo;
import org.dromara.shopping.domain.bo.SendDyInfoBo;
import org.dromara.shopping.domain.vo.PlatformChannelVo;
import org.dromara.shopping.domain.vo.SendDyInfoVo;
import org.dromara.shopping.domain.vo.WxMsgJobVo;
import org.dromara.shopping.mapper.PlatformChannelMapper;
import org.dromara.shopping.mapper.SendDyInfoMapper;
import org.dromara.shopping.service.ISendDyInfoService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 用户订阅Service业务层处理
 *
 * @author yzg
 * @date 2023-12-07
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SendDyInfoServiceImpl implements ISendDyInfoService {

    private final SendDyInfoMapper baseMapper;
    private final PlatformChannelMapper platformChannelMapper;

    /**
     * 查询用户订阅
     */
    @Override
    public SendDyInfoVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询用户订阅列表
     */
    @Override
    public TableDataInfo<SendDyInfoVo> queryPageList(SendDyInfoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SendDyInfo> lqw = buildQueryWrapper(bo);
        Page<SendDyInfoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询用户订阅列表
     */
    @Override
    public List<SendDyInfoVo> queryList(SendDyInfoBo bo) {
        LambdaQueryWrapper<SendDyInfo> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    @Override
    public void sendHuBeiDyInfo(String job) {
        List<Long> userIds = new ArrayList<>();
        if (StringUtils.isNotBlank(job)) {
            if ("123".equals(job)) {
                log.info("订阅定时任务可以正常执行");
                return;
            }
            String[] split = job.split(",");
            for (String s : split) {
                if (NumberUtil.isLong(s)) {
                    userIds.add(Long.valueOf(s));
                }
            }
        }
        LambdaQueryWrapper<SendDyInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(SendDyInfo::getTmplId, "gMc9x6bNe4fBhcQFZMSaOYQDM2hpC3KYozpvFofUuQ4");
        lqw.gt(SendDyInfo::getDyCount, 0);
        lqw.in(ObjectUtil.isNotEmpty(userIds), SendDyInfo::getUserId, userIds);
        long total = baseMapper.selectCount(lqw);
        //分页查询
        int pageIndex = 1;
        int pageSize = 50;
        PageQuery pageQuery = new PageQuery();
        pageQuery.setPageSize(pageSize);
        while (true) {
            pageQuery.setPageNum(pageIndex);
            Page<SendDyInfoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
            List<SendDyInfoVo> sendDyInfoVos = result.getRecords();
            //发送信息
            if (!CollectionUtils.isEmpty(sendDyInfoVos)) {
                for (SendDyInfoVo sendDyInfoVo : sendDyInfoVos) {
                    sendWxMsg(sendDyInfoVo);
                }
            }
            int sum = pageIndex * pageSize;
            if (sum >= total) {
                break;
            }
            pageIndex++;
        }
    }

    /**
     * 微信订阅消息
     */
    public void sendWxMsg(String job) {
        if (StringUtils.isBlank(job)) {
            return;
        }
        WxMsgJobVo wxMsgJobVo = JsonUtils.parseObject(job, WxMsgJobVo.class);
        if (null == wxMsgJobVo || StringUtils.isBlank(wxMsgJobVo.getTemplateId()) || ObjectUtil.isEmpty(wxMsgJobVo.getMsgMap())) {
            return;
        }
        LambdaQueryWrapper<SendDyInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(SendDyInfo::getTmplId, wxMsgJobVo.getTemplateId());
        lqw.gt(SendDyInfo::getDyCount, 0);
        lqw.in(ObjectUtil.isNotEmpty(wxMsgJobVo.getUserIds()), SendDyInfo::getUserId, wxMsgJobVo.getUserIds());
        long total = baseMapper.selectCount(lqw);
        if (total < 1) {
            return;
        }
        //分页查询
        int pageIndex = 1;
        int pageSize = 100;
        PageQuery pageQuery = new PageQuery();
        pageQuery.setPageSize(pageSize);
        while (true) {
            pageQuery.setPageNum(pageIndex);
            Page<SendDyInfoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
            List<SendDyInfoVo> sendDyInfoVos = result.getRecords();
            //发送信息
            if (ObjectUtil.isNotEmpty(sendDyInfoVos)) {
                for (SendDyInfoVo sendDyInfoVo : sendDyInfoVos) {
                    try {
                        PlatformChannelVo platformChannelVo = platformChannelMapper.selectVoOne(new LambdaQueryWrapper<PlatformChannel>().eq(PlatformChannel::getPlatformKey, sendDyInfoVo.getPlatformKey()).eq(PlatformChannel::getChannel, "1"));
                        if (null == platformChannelVo || StringUtils.isBlank(platformChannelVo.getAppId()) || StringUtils.isBlank(platformChannelVo.getSecret())) {
                            return;
                        }

                        // 组装消息数据
                        Map<String, Object> msgData = new HashMap<>();
                        for (String s : wxMsgJobVo.getMsgMap().keySet()) {
                            Map<String, String> thingMap = new HashMap<>();
                            thingMap.put("value", wxMsgJobVo.getMsgMap().get(s));
                            msgData.put(s, thingMap);
                        }
                        // 发送消息
                        // 扣减订阅次数
                        SendDyInfo sendDyInfo = new SendDyInfo();
                        sendDyInfo.setId(sendDyInfoVo.getId());
                        sendDyInfo.setDyCount(sendDyInfoVo.getDyCount() - 1);
                        baseMapper.updateById(sendDyInfo);
                    } catch (Exception e) {
                        log.error(e.getMessage());
                    }
                }
            }
            int sum = pageIndex * pageSize;
            if (sum >= total) {
                break;
            }
            pageIndex++;
        }
    }

    private void sendWxMsg(SendDyInfoVo sendDyInfoVo) {
        if (sendDyInfoVo.getDyCount() <= 0) {
            return;
        }
        try {
            sendDyInfoVo.setDyCount(sendDyInfoVo.getDyCount() - 1);
            SendDyInfo sendDyInfo = BeanUtil.toBean(sendDyInfoVo, SendDyInfo.class);
            baseMapper.updateById(sendDyInfo);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private LambdaQueryWrapper<SendDyInfo> buildQueryWrapper(SendDyInfoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SendDyInfo> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getPlatformKey() != null, SendDyInfo::getPlatformKey, bo.getPlatformKey());
        lqw.eq(bo.getUserId() != null, SendDyInfo::getUserId, bo.getUserId());
        lqw.eq(StringUtils.isNotBlank(bo.getOpenId()), SendDyInfo::getOpenId, bo.getOpenId());
        lqw.eq(StringUtils.isNotBlank(bo.getTmplId()), SendDyInfo::getTmplId, bo.getTmplId());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), SendDyInfo::getStatus, bo.getStatus());
        lqw.eq(bo.getDyCount() != null, SendDyInfo::getDyCount, bo.getDyCount());
        lqw.between(params.get("beginCreateTime") != null && params.get("endCreateTime") != null,
            SendDyInfo::getCreateTime, params.get("beginCreateTime"), params.get("endCreateTime"));
        return lqw;
    }

    /**
     * 新增用户订阅
     */
    @Override
    public Boolean insertByBo(SendDyInfoBo bo) {
        SendDyInfo add = BeanUtil.toBean(bo, SendDyInfo.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改用户订阅
     */
    @Override
    public Boolean updateByBo(SendDyInfoBo bo) {
        SendDyInfo update = BeanUtil.toBean(bo, SendDyInfo.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SendDyInfo entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除用户订阅
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
