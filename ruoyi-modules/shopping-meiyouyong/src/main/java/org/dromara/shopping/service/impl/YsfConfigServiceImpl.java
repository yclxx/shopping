package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.YsfConfig;
import org.dromara.shopping.domain.bo.YsfConfigBo;
import org.dromara.shopping.domain.vo.YsfConfigVo;
import org.dromara.shopping.mapper.YsfConfigMapper;
import org.dromara.shopping.service.IYsfConfigService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * 云闪付参数配置Service业务层处理
 *
 * @author yzg
 * @date 2023-07-31
 */
@RequiredArgsConstructor
@Service
public class YsfConfigServiceImpl implements IYsfConfigService {

    private final YsfConfigMapper baseMapper;

    @Override
    public String queryValueByKey(Long platformId, String key) {
        try {
            if (null != platformId) {
                String result = baseMapper.selectVoById(key).getConfigValue();
                if (StringUtils.isNotBlank(result)) {
                    return result;
                }
            } else {
                return queryValueByKey(key);
            }
        } catch (Exception ignored) {
        }
        return "";
    }

    @Override
    public String queryValueByKey(String key) {
        try {
            if (null != key) {
                String result = baseMapper.selectVoById(key).getConfigValue();
                if (StringUtils.isNotBlank(result)) {
                    return result;
                }
            }
        } catch (Exception ignored) {
        }
        return "";
    }

    /**
     * 查询云闪付参数配置
     */
    @Override
    public YsfConfigVo queryById(Long configId) {
        return baseMapper.selectVoById(configId);
    }

    /**
     * 查询云闪付参数配置列表
     */
    @Override
    public TableDataInfo<YsfConfigVo> queryPageList(YsfConfigBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<YsfConfig> lqw = buildQueryWrapper(bo);
        Page<YsfConfigVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询云闪付参数配置列表
     */
    @Override
    public List<YsfConfigVo> queryList(YsfConfigBo bo) {
        LambdaQueryWrapper<YsfConfig> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<YsfConfig> buildQueryWrapper(YsfConfigBo bo) {
        //Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<YsfConfig> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getPlatformId() != null, YsfConfig::getPlatformId, bo.getPlatformId());
        lqw.like(StringUtils.isNotBlank(bo.getConfigName()), YsfConfig::getConfigName, bo.getConfigName());
        lqw.eq(StringUtils.isNotBlank(bo.getConfigKey()), YsfConfig::getConfigKey, bo.getConfigKey());
        lqw.eq(StringUtils.isNotBlank(bo.getConfigValue()), YsfConfig::getConfigValue, bo.getConfigValue());
        lqw.orderByDesc(YsfConfig::getCreateTime);
        return lqw;
    }

    /**
     * 新增云闪付参数配置
     */
    @Override
    public Boolean insertByBo(YsfConfigBo bo) {
        YsfConfig add = BeanUtil.toBean(bo, YsfConfig.class);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setConfigId(add.getConfigId());
        }
        return flag;
    }

    /**
     * 修改云闪付参数配置
     */
    @Override
    public Boolean updateByBo(YsfConfigBo bo) {
        YsfConfig update = BeanUtil.toBean(bo, YsfConfig.class);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 批量删除云闪付参数配置
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
