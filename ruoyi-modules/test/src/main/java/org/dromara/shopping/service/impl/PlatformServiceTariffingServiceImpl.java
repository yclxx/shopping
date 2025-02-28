package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.PlatformServiceTariffing;
import org.dromara.shopping.domain.bo.PlatformServiceTariffingBo;
import org.dromara.shopping.domain.vo.PlatformServiceTariffingVo;
import org.dromara.shopping.mapper.PlatformServiceTariffingMapper;
import org.dromara.shopping.service.IPlatformServiceTariffingService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 类别服务费Service业务层处理
 *
 * @author yzg
 * @date 2024-06-13
 */
@RequiredArgsConstructor
@Service
public class PlatformServiceTariffingServiceImpl implements IPlatformServiceTariffingService {

    private final PlatformServiceTariffingMapper baseMapper;

    /**
     * 查询类别服务费
     */
    @Override
    public PlatformServiceTariffingVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询类别服务费列表
     */
    @Override
    public TableDataInfo<PlatformServiceTariffingVo> queryPageList(PlatformServiceTariffingBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<PlatformServiceTariffing> lqw = buildQueryWrapper(bo);
        Page<PlatformServiceTariffingVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询类别服务费列表
     */
    @Override
    public List<PlatformServiceTariffingVo> queryList(PlatformServiceTariffingBo bo) {
        LambdaQueryWrapper<PlatformServiceTariffing> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<PlatformServiceTariffing> buildQueryWrapper(PlatformServiceTariffingBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<PlatformServiceTariffing> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getPlatformKey() != null, PlatformServiceTariffing::getPlatformKey, bo.getPlatformKey());
        lqw.eq(bo.getCategoryId() != null, PlatformServiceTariffing::getCategoryId, bo.getCategoryId());
        return lqw;
    }

    /**
     * 新增类别服务费
     */
    @Override
    public Boolean insertByBo(PlatformServiceTariffingBo bo) {
        PlatformServiceTariffing add = BeanUtil.toBean(bo, PlatformServiceTariffing.class);
        boolean b = validEntityBeforeSave(add);
        if (b) {
            boolean flag = baseMapper.insert(add) > 0;
            if (flag) {
                bo.setId(add.getId());
            }
            return flag;
        }
        return true;
    }

    /**
     * 修改类别服务费
     */
    @Override
    public Boolean updateByBo(PlatformServiceTariffingBo bo) {
        PlatformServiceTariffing update = BeanUtil.toBean(bo, PlatformServiceTariffing.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private boolean validEntityBeforeSave(PlatformServiceTariffing entity) {
        // 查询是否重复
        PlatformServiceTariffing platformServiceTariffing = baseMapper.selectOne(new LambdaQueryWrapper<PlatformServiceTariffing>()
            .eq(PlatformServiceTariffing::getPlatformKey, entity.getPlatformKey())
            .eq(PlatformServiceTariffing::getCategoryId, entity.getCategoryId())
        );
        if (platformServiceTariffing != null) {
            if (null == entity.getId()) {
                entity.setId(platformServiceTariffing.getId());
                baseMapper.insertOrUpdate(entity);
            } else {
                if (!entity.getId().equals(platformServiceTariffing.getId())) {
                    throw new ServiceException("已存在，不可重复设置");
                }
            }
            return false;
        }
        return true;
    }

    /**
     * 批量删除类别服务费
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
