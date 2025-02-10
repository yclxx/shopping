package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.CommercialActivityApplyShop;
import org.dromara.shopping.domain.bo.CommercialActivityApplyShopBo;
import org.dromara.shopping.domain.vo.CommercialActivityApplyShopVo;
import org.dromara.shopping.mapper.CommercialActivityApplyShopMapper;
import org.dromara.shopping.service.ICommercialActivityApplyShopService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 商户活动报名门店Service业务层处理
 *
 * @author yzg
 * @date 2024-04-10
 */
@RequiredArgsConstructor
@Service
public class CommercialActivityApplyShopServiceImpl implements ICommercialActivityApplyShopService {

    private final CommercialActivityApplyShopMapper baseMapper;

    /**
     * 查询商户活动报名门店
     */
    @Override
    public CommercialActivityApplyShopVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询商户活动报名门店列表
     */
    @Override
    public TableDataInfo<CommercialActivityApplyShopVo> queryPageList(CommercialActivityApplyShopBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<CommercialActivityApplyShop> lqw = buildQueryWrapper(bo);
        Page<CommercialActivityApplyShopVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询商户活动报名门店列表
     */
    @Override
    public List<CommercialActivityApplyShopVo> queryList(CommercialActivityApplyShopBo bo) {
        LambdaQueryWrapper<CommercialActivityApplyShop> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<CommercialActivityApplyShop> buildQueryWrapper(CommercialActivityApplyShopBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<CommercialActivityApplyShop> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getApplyId() != null, CommercialActivityApplyShop::getApplyId, bo.getApplyId());
        lqw.eq(bo.getActivityId() != null, CommercialActivityApplyShop::getActivityId, bo.getActivityId());
        lqw.like(StringUtils.isNotBlank(bo.getActivityName()), CommercialActivityApplyShop::getActivityName, bo.getActivityName());
        lqw.eq(bo.getShopId() != null, CommercialActivityApplyShop::getShopId, bo.getShopId());
        lqw.like(StringUtils.isNotBlank(bo.getShopName()), CommercialActivityApplyShop::getShopName, bo.getShopName());
        lqw.eq(StringUtils.isNotBlank(bo.getMerchantNo()), CommercialActivityApplyShop::getMerchantNo, bo.getMerchantNo());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), CommercialActivityApplyShop::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增商户活动报名门店
     */
    @Override
    public Boolean insertByBo(CommercialActivityApplyShopBo bo) {
        CommercialActivityApplyShop add = BeanUtil.toBean(bo, CommercialActivityApplyShop.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改商户活动报名门店
     */
    @Override
    public Boolean updateByBo(CommercialActivityApplyShopBo bo) {
        CommercialActivityApplyShop update = BeanUtil.toBean(bo, CommercialActivityApplyShop.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(CommercialActivityApplyShop entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除商户活动报名门店
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
