package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.PlatformShareConfig;
import org.dromara.shopping.domain.bo.PlatformShareConfigBo;
import org.dromara.shopping.domain.vo.PlatformShareConfigVo;
import org.dromara.shopping.mapper.PlatformShareConfigMapper;
import org.dromara.shopping.service.IPlatformShareConfigService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 平台分享配置Service业务层处理
 *
 * @author yzg
 * @date 2025-01-14
 */
@RequiredArgsConstructor
@Service
public class PlatformShareConfigServiceImpl implements IPlatformShareConfigService {

    private final PlatformShareConfigMapper baseMapper;

    /**
     * 查询平台分享配置
     */
    @Override
    public PlatformShareConfigVo queryById(Long platformKey){
        return baseMapper.selectVoById(platformKey);
    }

    /**
     * 查询平台分享配置列表
     */
    @Override
    public TableDataInfo<PlatformShareConfigVo> queryPageList(PlatformShareConfigBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<PlatformShareConfig> lqw = buildQueryWrapper(bo);
        Page<PlatformShareConfigVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询平台分享配置列表
     */
    @Override
    public List<PlatformShareConfigVo> queryList(PlatformShareConfigBo bo) {
        LambdaQueryWrapper<PlatformShareConfig> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<PlatformShareConfig> buildQueryWrapper(PlatformShareConfigBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<PlatformShareConfig> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getPlatformKey() != null, PlatformShareConfig::getPlatformKey, bo.getPlatformKey());
        lqw.eq(StringUtils.isNotBlank(bo.getShareTitle()), PlatformShareConfig::getShareTitle, bo.getShareTitle());
        lqw.like(StringUtils.isNotBlank(bo.getShareName()), PlatformShareConfig::getShareName, bo.getShareName());
        return lqw;
    }

    /**
     * 修改平台分享配置
     */
    @Override
    public Boolean updateByBo(PlatformShareConfigBo bo) {
        PlatformShareConfig update = BeanUtil.toBean(bo, PlatformShareConfig.class);
        return baseMapper.insertOrUpdate(update);
    }

}
