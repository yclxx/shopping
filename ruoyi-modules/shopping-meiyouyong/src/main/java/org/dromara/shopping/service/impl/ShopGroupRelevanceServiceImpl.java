package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.ShopGroupRelevance;
import org.dromara.shopping.domain.bo.ShopGroupRelevanceBo;
import org.dromara.shopping.domain.vo.ShopGroupRelevanceVo;
import org.dromara.shopping.mapper.ShopGroupRelevanceMapper;
import org.dromara.shopping.service.IShopGroupRelevanceService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 门店组门店关联Service业务层处理
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@RequiredArgsConstructor
@Service
public class ShopGroupRelevanceServiceImpl implements IShopGroupRelevanceService {

    private final ShopGroupRelevanceMapper baseMapper;

    /**
     * 查询门店组门店关联
     */
    @Override
    public ShopGroupRelevanceVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询门店组门店关联列表
     */
    @Override
    public TableDataInfo<ShopGroupRelevanceVo> queryPageList(ShopGroupRelevanceBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ShopGroupRelevance> lqw = buildQueryWrapper(bo);
        Page<ShopGroupRelevanceVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询门店组门店关联列表
     */
    @Override
    public List<ShopGroupRelevanceVo> queryList(ShopGroupRelevanceBo bo) {
        LambdaQueryWrapper<ShopGroupRelevance> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ShopGroupRelevance> buildQueryWrapper(ShopGroupRelevanceBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ShopGroupRelevance> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getShopGroupId() != null, ShopGroupRelevance::getShopGroupId, bo.getShopGroupId());
        lqw.eq(bo.getShopId() != null, ShopGroupRelevance::getShopId, bo.getShopId());
        return lqw;
    }

    /**
     * 新增门店组门店关联
     */
    @Override
    public Boolean insertByBo(ShopGroupRelevanceBo bo) {
        ShopGroupRelevance add = BeanUtil.toBean(bo, ShopGroupRelevance.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改门店组门店关联
     */
    @Override
    public Boolean updateByBo(ShopGroupRelevanceBo bo) {
        ShopGroupRelevance update = BeanUtil.toBean(bo, ShopGroupRelevance.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ShopGroupRelevance entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除门店组门店关联
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public Boolean remove(LambdaQueryWrapper<ShopGroupRelevance> queryWrapper) {
        return SqlHelper.retBool(baseMapper.delete(queryWrapper));
    }
}
