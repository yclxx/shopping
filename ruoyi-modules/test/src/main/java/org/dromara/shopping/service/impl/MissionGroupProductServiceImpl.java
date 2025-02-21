package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.MissionGroupProduct;
import org.dromara.shopping.domain.bo.MissionGroupProductBo;
import org.dromara.shopping.domain.vo.MissionGroupProductVo;
import org.dromara.shopping.mapper.MissionGroupProductMapper;
import org.dromara.shopping.service.IMissionGroupProductService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 任务组可兑换商品配置Service业务层处理
 *
 * @author yzg
 * @date 2023-05-10
 */
@RequiredArgsConstructor
@Service
public class MissionGroupProductServiceImpl implements IMissionGroupProductService {

    private final MissionGroupProductMapper baseMapper;

    /**
     * 查询任务组可兑换商品配置
     */
    @Override
    public MissionGroupProductVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询任务组可兑换商品配置列表
     */
    @Override
    public TableDataInfo<MissionGroupProductVo> queryPageList(MissionGroupProductBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MissionGroupProduct> lqw = buildQueryWrapper(bo);
        Page<MissionGroupProductVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询任务组可兑换商品配置列表
     */
    @Override
    public List<MissionGroupProductVo> queryList(MissionGroupProductBo bo) {
        LambdaQueryWrapper<MissionGroupProduct> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MissionGroupProduct> buildQueryWrapper(MissionGroupProductBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MissionGroupProduct> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getMissionGroupId() != null, MissionGroupProduct::getMissionGroupId, bo.getMissionGroupId());
        lqw.eq(bo.getMissionId() != null, MissionGroupProduct::getMissionId, bo.getMissionId());
        lqw.eq(bo.getProductId() != null, MissionGroupProduct::getProductId, bo.getProductId());
        return lqw;
    }

    /**
     * 新增任务组可兑换商品配置
     */
    @Override
    public Boolean insertByBo(MissionGroupProductBo bo) {
        MissionGroupProduct add = BeanUtil.toBean(bo, MissionGroupProduct.class);
        validEntityBeforeSave(bo);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改任务组可兑换商品配置
     */
    @Override
    public Boolean updateByBo(MissionGroupProductBo bo) {
        MissionGroupProduct update = BeanUtil.toBean(bo, MissionGroupProduct.class);
        validEntityBeforeSave(bo);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MissionGroupProductBo entity) {
        LambdaQueryWrapper<MissionGroupProduct> lqw = buildQueryWrapper(entity);
        lqw.last("limit 1");
        MissionGroupProductVo missionGroupProductVo = baseMapper.selectVoOne(lqw);
        if (null != missionGroupProductVo && (null == entity.getId() || !missionGroupProductVo.getId().equals(entity.getId()))) {
            throw new ServiceException("不允许添加重复商品");
        }
    }

    /**
     * 批量删除任务组可兑换商品配置
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
