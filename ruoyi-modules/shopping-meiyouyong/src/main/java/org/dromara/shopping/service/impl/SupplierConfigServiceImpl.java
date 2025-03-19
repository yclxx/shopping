package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.SupplierConfig;
import org.dromara.shopping.domain.bo.SupplierConfigBo;
import org.dromara.shopping.domain.vo.SupplierConfigVo;
import org.dromara.shopping.mapper.SupplierConfigMapper;
import org.dromara.shopping.service.ISupplierConfigService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * 供应商参数配置Service业务层处理
 *
 * @author yzg
 * @date 2023-10-11
 */
@RequiredArgsConstructor
@Service
public class SupplierConfigServiceImpl implements ISupplierConfigService {

    private final SupplierConfigMapper baseMapper;

    /**
     * 查询供应商参数配置
     */
    @Override
    public SupplierConfigVo queryById(Long configId) {
        return baseMapper.selectVoById(configId);
    }

    /**
     * 查询供应商参数配置列表
     */
    @Override
    public TableDataInfo<SupplierConfigVo> queryPageList(SupplierConfigBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SupplierConfig> lqw = buildQueryWrapper(bo);
        Page<SupplierConfigVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询供应商参数配置列表
     */
    @Override
    public List<SupplierConfigVo> queryList(SupplierConfigBo bo) {
        LambdaQueryWrapper<SupplierConfig> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SupplierConfig> buildQueryWrapper(SupplierConfigBo bo) {
        LambdaQueryWrapper<SupplierConfig> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getConfigName()), SupplierConfig::getConfigName, bo.getConfigName());
        lqw.eq(bo.getSupplierId() != null, SupplierConfig::getSupplierId, bo.getSupplierId());
        lqw.eq(StringUtils.isNotBlank(bo.getConfigKey()), SupplierConfig::getConfigKey, bo.getConfigKey());
        lqw.eq(StringUtils.isNotBlank(bo.getConfigValue()), SupplierConfig::getConfigValue, bo.getConfigValue());
        return lqw;
    }

    /**
     * 新增供应商参数配置
     */
    @Override
    public Boolean insertByBo(SupplierConfigBo bo) {
        SupplierConfig add = BeanUtil.toBean(bo, SupplierConfig.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setConfigId(add.getConfigId());
        }
        return flag;
    }

    /**
     * 修改供应商参数配置
     */
    @Override
    public Boolean updateByBo(SupplierConfigBo bo) {
        SupplierConfig update = BeanUtil.toBean(bo, SupplierConfig.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SupplierConfig entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除供应商参数配置
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
