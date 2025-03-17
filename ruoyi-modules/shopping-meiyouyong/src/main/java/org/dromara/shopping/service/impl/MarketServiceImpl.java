package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.Market;
import org.dromara.shopping.domain.bo.MarketBo;
import org.dromara.shopping.domain.vo.ActionVo;
import org.dromara.shopping.domain.vo.MarketVo;
import org.dromara.shopping.mapper.ActionMapper;
import org.dromara.shopping.mapper.MarketMapper;
import org.dromara.shopping.service.IMarketService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 新用户营销Service业务层处理
 *
 * @author yzg
 * @date 2023-10-18
 */
@RequiredArgsConstructor
@Service
public class MarketServiceImpl implements IMarketService {
    private final MarketMapper baseMapper;
    // 优惠券批次表
    private final ActionMapper actionMapper;

    /**
     * 查询新用户营销
     */
    @Override
    public MarketVo queryById(Long marketId) {
        return baseMapper.selectVoById(marketId);
    }

    /**
     * 查询新用户营销
     */
    @Override
    public Map<String, Object> queryByIds(Long marketId) {
        MarketVo marketVo = baseMapper.selectVoById(marketId);
        if (marketVo != null) {
            Map<String, Object> map = new HashMap<>();
            map.put("market", marketVo);
            if (marketVo.getRewardType().equals("2")) {
                ActionVo actionVo = actionMapper.selectVoById(marketVo.getActionId());
                map.put("action", actionVo);
            } else {
            }
            return map;
        }
        return null;
    }

    @Override
    public TableDataInfo<MarketVo> queryPageList(MarketBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Market> lqw = buildQueryWrapper(bo);
        Page<MarketVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询新用户营销列表
     */
    @Override
    public List<MarketVo> queryList(MarketBo bo) {
        LambdaQueryWrapper<Market> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Market> buildQueryWrapper(MarketBo bo) {
        //Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Market> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getPlatformKey() != null, Market::getPlatformKey, bo.getPlatformKey());
        lqw.like(StringUtils.isNotBlank(bo.getMarketName()), Market::getMarketName, bo.getMarketName());
        lqw.eq(bo.getBeginTime() != null, Market::getBeginTime, bo.getBeginTime());
        lqw.eq(bo.getEndTime() != null, Market::getEndTime, bo.getEndTime());
        lqw.eq(bo.getDateSpecific() != null, Market::getDateSpecific, bo.getDateSpecific());
        lqw.eq(StringUtils.isNotBlank(bo.getRewardType()), Market::getRewardType, bo.getRewardType());
        return lqw;
    }

    /**
     * 新增新用户营销
     */
    @Override
    public Boolean insertByBo(MarketBo bo) {
        Market add = BeanUtil.toBean(bo, Market.class);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setMarketId(add.getMarketId());
        }
        return flag;
    }

    /**
     * 修改新用户营销
     */
    @Override
    public Boolean updateByBo(MarketBo bo) {
        Market update = BeanUtil.toBean(bo, Market.class);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 批量删除新用户营销
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
