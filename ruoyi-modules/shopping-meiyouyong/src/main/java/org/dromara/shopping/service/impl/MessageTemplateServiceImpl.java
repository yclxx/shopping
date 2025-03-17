package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.MessageTemplate;
import org.dromara.shopping.domain.PlatformChannel;
import org.dromara.shopping.domain.bo.MessageTemplateBo;
import org.dromara.shopping.domain.vo.MessageTemplateVo;
import org.dromara.shopping.domain.vo.PlatformChannelVo;
import org.dromara.shopping.mapper.MessageTemplateMapper;
import org.dromara.shopping.mapper.PlatformChannelMapper;
import org.dromara.shopping.service.IMessageTemplateService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * 消息模板Service业务层处理
 *
 * @author yzg
 * @date 2023-11-23
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class MessageTemplateServiceImpl implements IMessageTemplateService {

    private final MessageTemplateMapper baseMapper;
    private final PlatformChannelMapper platformChannelMapper;

    /**
     * 查询消息模板
     */
    @Override
    public MessageTemplateVo queryById(Long templateId) {
        return baseMapper.selectVoById(templateId);
    }

    /**
     * 查询消息模板列表
     */
    @Override
    public TableDataInfo<MessageTemplateVo> queryPageList(MessageTemplateBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MessageTemplate> lqw = buildQueryWrapper(bo);
        Page<MessageTemplateVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询消息模板列表
     */
    @Override
    public List<MessageTemplateVo> queryList(MessageTemplateBo bo) {
        LambdaQueryWrapper<MessageTemplate> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MessageTemplate> buildQueryWrapper(MessageTemplateBo bo) {
        //Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MessageTemplate> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), MessageTemplate::getStatus, bo.getStatus());
        lqw.eq(bo.getPlatformKey() != null, MessageTemplate::getPlatformKey, bo.getPlatformKey());
        lqw.eq(StringUtils.isNotBlank(bo.getTemplateKey()), MessageTemplate::getTemplateKey, bo.getTemplateKey());
        lqw.like(StringUtils.isNotBlank(bo.getTemplateName()), MessageTemplate::getTemplateName, bo.getTemplateName());
        lqw.eq(StringUtils.isNotBlank(bo.getChannel()), MessageTemplate::getChannel, bo.getChannel());
        return lqw;
    }

    /**
     * 新增消息模板
     */
    @Override
    public Boolean insertByBo(MessageTemplateBo bo) {
        MessageTemplate add = BeanUtil.toBean(bo, MessageTemplate.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setTemplateId(add.getTemplateId());
        }
        return flag;
    }

    /**
     * 修改消息模板
     */
    @Override
    public Boolean updateByBo(MessageTemplateBo bo) {
        MessageTemplate update = BeanUtil.toBean(bo, MessageTemplate.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MessageTemplate entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除消息模板
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public void importData(MessageTemplateBo bo) throws IOException {
        MessageTemplateVo vo = baseMapper.selectVoById(bo.getTemplateId());
        LambdaQueryWrapper<PlatformChannel> lqw = Wrappers.lambdaQuery();
        lqw.eq(PlatformChannel::getChannel, vo.getChannel());
        lqw.eq(PlatformChannel::getPlatformKey, vo.getPlatformKey());
        PlatformChannelVo platformChannelVo = platformChannelMapper.selectVoOne(lqw);
        if (ObjectUtil.isEmpty(platformChannelVo)) throw new ServiceException("平台没有此支持端APP数据");
    }

}
