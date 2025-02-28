package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.PlatformSaleProfit;
import org.dromara.shopping.domain.bo.PlatformSaleProfitBo;
import org.dromara.shopping.domain.vo.PlatformSaleProfitVo;
import org.dromara.shopping.mapper.PlatformSaleProfitMapper;
import org.dromara.shopping.service.IPlatformSaleProfitService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 平台销售利润Service业务层处理
 *
 * @author yzg
 * @date 2024-09-11
 */
@RequiredArgsConstructor
@Service
public class PlatformSaleProfitServiceImpl implements IPlatformSaleProfitService {

    private final PlatformSaleProfitMapper baseMapper;

    /**
     * 查询平台销售利润
     */
    @Override
    public PlatformSaleProfitVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询平台销售利润列表
     */
    @Override
    public TableDataInfo<PlatformSaleProfitVo> queryPageList(PlatformSaleProfitBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<PlatformSaleProfit> lqw = buildQueryWrapper(bo);
        Page<PlatformSaleProfitVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询平台销售利润列表
     */
    @Override
    public List<PlatformSaleProfitVo> queryList(PlatformSaleProfitBo bo) {
        LambdaQueryWrapper<PlatformSaleProfit> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<PlatformSaleProfit> buildQueryWrapper(PlatformSaleProfitBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<PlatformSaleProfit> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getPlatformKey() != null, PlatformSaleProfit::getPlatformKey, bo.getPlatformKey());
        lqw.eq(StringUtils.isNotBlank(bo.getMonth()), PlatformSaleProfit::getMonth, bo.getMonth());
        lqw.eq(bo.getSalePrice() != null, PlatformSaleProfit::getSalePrice, bo.getSalePrice());
        lqw.eq(bo.getSettlementPrice() != null, PlatformSaleProfit::getSettlementPrice, bo.getSettlementPrice());
        lqw.eq(bo.getUsedSalePrice() != null, PlatformSaleProfit::getUsedSalePrice, bo.getUsedSalePrice());
        lqw.eq(bo.getUsedSettlementPrice() != null, PlatformSaleProfit::getUsedSettlementPrice, bo.getUsedSettlementPrice());
        lqw.eq(bo.getUsedProfit() != null, PlatformSaleProfit::getUsedProfit, bo.getUsedProfit());
        return lqw;
    }

    /**
     * 新增平台销售利润
     */
    @Override
    public Boolean insertByBo(PlatformSaleProfitBo bo) {
        PlatformSaleProfit add = BeanUtil.toBean(bo, PlatformSaleProfit.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改平台销售利润
     */
    @Override
    public Boolean updateByBo(PlatformSaleProfitBo bo) {
        PlatformSaleProfit update = BeanUtil.toBean(bo, PlatformSaleProfit.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(PlatformSaleProfit entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除平台销售利润
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
