package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.Merchant;
import org.dromara.shopping.domain.Platform;
import org.dromara.shopping.domain.PlatformChannel;
import org.dromara.shopping.domain.bo.PlatformBo;
import org.dromara.shopping.domain.bo.PlatformChannelBo;
import org.dromara.shopping.domain.vo.PlatformChannelVo;
import org.dromara.shopping.domain.vo.PlatformVo;
import org.dromara.shopping.mapper.MerchantMapper;
import org.dromara.shopping.mapper.PlatformChannelMapper;
import org.dromara.shopping.mapper.PlatformMapper;
import org.dromara.shopping.service.IPlatformService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 平台信息Service业务层处理
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@RequiredArgsConstructor
@Service
public class PlatformServiceImpl implements IPlatformService {

    private final PlatformMapper baseMapper;
    private final PlatformChannelMapper platformChannelMapper;
    private final MerchantMapper merchantMapper;

    /**
     * 查询平台信息
     */
    @Override
    public PlatformVo queryById(Long platformKey) {
        PlatformVo platformVo = baseMapper.selectVoById(platformKey);
        if (StringUtils.isNotEmpty(platformVo.getSupportChannel())) {
            LambdaQueryWrapper<PlatformChannel> queryWrapper = Wrappers.lambdaQuery();
            queryWrapper.eq(PlatformChannel::getPlatformKey, platformKey);
            queryWrapper.orderByAsc(PlatformChannel::getChannel);
            List<PlatformChannelVo> platformChannelVos = platformChannelMapper.selectVoList(queryWrapper);
            platformVo.setPlatformChannel(platformChannelVos);
        }
        return platformVo;
    }

    /**
     * 查询平台信息列表
     */
    @Override
    public TableDataInfo<PlatformVo> queryPageList(PlatformBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Platform> lqw = buildQueryWrapper(bo);
        Page<PlatformVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        TableDataInfo<PlatformVo> build = TableDataInfo.build(result);
        List<PlatformVo> rows = build.getRows();
        if (CollectionUtils.isNotEmpty(rows)) {
            for (PlatformVo platformVo : rows) {
                Merchant merchant = merchantMapper.selectById(platformVo.getMerchantId());
                if (null != merchant) {
                    platformVo.setMerchantNo(merchant.getMerchantName());
                }
            }
        }
        return TableDataInfo.build(result);
    }

    /**
     * 查询平台信息列表
     */
    @Override
    public List<PlatformVo> queryList(PlatformBo bo) {
        LambdaQueryWrapper<Platform> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Platform> buildQueryWrapper(PlatformBo bo) {
        //Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Platform> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getPlatformName()), Platform::getPlatformName, bo.getPlatformName());
        lqw.eq(StringUtils.isNotBlank(bo.getPlatformTitle()), Platform::getPlatformTitle, bo.getPlatformTitle());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), Platform::getStatus, bo.getStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getAppId()), Platform::getAppId, bo.getAppId());
        lqw.eq(StringUtils.isNotBlank(bo.getEncryptAppId()), Platform::getEncryptAppId, bo.getEncryptAppId());
        lqw.eq(StringUtils.isNotBlank(bo.getSecret()), Platform::getSecret, bo.getSecret());
        lqw.eq(StringUtils.isNotBlank(bo.getSymmetricKey()), Platform::getSymmetricKey, bo.getSymmetricKey());
        lqw.eq(StringUtils.isNotBlank(bo.getRsaPrivateKey()), Platform::getRsaPrivateKey, bo.getRsaPrivateKey());
        lqw.eq(StringUtils.isNotBlank(bo.getRsaPublicKey()), Platform::getRsaPublicKey, bo.getRsaPublicKey());
        lqw.eq(StringUtils.isNotBlank(bo.getServiceTel()), Platform::getServiceTel, bo.getServiceTel());
        lqw.eq(StringUtils.isNotBlank(bo.getServiceTime()), Platform::getServiceTime, bo.getServiceTime());
        lqw.eq(StringUtils.isNotBlank(bo.getPlatformCity()), Platform::getPlatformCity, bo.getPlatformCity());
        lqw.eq(bo.getMerchantId() != null, Platform::getMerchantId, bo.getMerchantId());
        return lqw;
    }

    /**
     * 新增平台信息
     */
    @Override
    public Boolean insertByBo(PlatformBo bo) {
        Platform add = BeanUtil.toBean(bo, Platform.class);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setPlatformKey(add.getPlatformKey());
            if (ObjectUtil.isNotEmpty(bo.getPlatformChannel())) {
                handlePlatformChannel(bo.getPlatformChannel(), add.getPlatformKey());
            }
        }
        return flag;
    }

    /**
     * 修改平台信息
     */
    @Override
    public Boolean updateByBo(PlatformBo bo) {
        Platform update = BeanUtil.toBean(bo, Platform.class);
        boolean b = baseMapper.updateById(update) > 0;
        if (b) {
            if (ObjectUtil.isNotEmpty(bo.getPlatformChannel())) {
                handlePlatformChannel(bo.getPlatformChannel(), bo.getPlatformKey());
            }
        }
        return b;
    }

    /**
     * 批量删除平台信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        boolean b = baseMapper.deleteBatchIds(ids) > 0;
        if (b) {
            LambdaQueryWrapper<PlatformChannel> queryWrapper = Wrappers.lambdaQuery();
            queryWrapper.in(PlatformChannel::getId, ids);
            platformChannelMapper.delete(queryWrapper);
        }
        return b;
    }

    /**
     * 处理平台渠道配置
     */
    private void handlePlatformChannel(List<PlatformChannelBo> platformChannelBoList, Long platformKey) {
        // 查询平台信息
        LambdaQueryWrapper<PlatformChannel> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(PlatformChannel::getPlatformKey, platformKey);
        List<PlatformChannel> platformChannels = platformChannelMapper.selectList(queryWrapper);
        if (ObjectUtil.isNotEmpty(platformChannels)) {
            List<PlatformChannel> add = new ArrayList<>();
            List<PlatformChannel> update = new ArrayList<>();
            List<Long> notDelete = new ArrayList<>();
            for (PlatformChannelBo bo : platformChannelBoList) {
                PlatformChannel platformChannel = BeanUtil.toBean(bo, PlatformChannel.class);
                if (ObjectUtil.isNotEmpty(bo.getId())) {
                    platformChannel.setId(bo.getId());
                    update.add(platformChannel);
                    notDelete.add(platformChannel.getId());
                } else {
                    add.add(platformChannel);
                }
            }

            if (ObjectUtil.isNotEmpty(notDelete)) {
                queryWrapper.notIn(PlatformChannel::getId, notDelete);
                platformChannelMapper.delete(queryWrapper);
            }
            if (ObjectUtil.isNotEmpty(add)) platformChannelMapper.insertBatch(add);
            if (ObjectUtil.isNotEmpty(update)) platformChannelMapper.updateBatchById(update);
        }
    }
}
