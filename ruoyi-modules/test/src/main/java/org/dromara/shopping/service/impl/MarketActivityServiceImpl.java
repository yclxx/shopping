package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.MarketActivity;
import org.dromara.shopping.domain.bo.MarketActivityBo;
import org.dromara.shopping.domain.vo.MarketActivityVo;
import org.dromara.shopping.mapper.MarketActivityMapper;
import org.dromara.shopping.service.IMarketActivityService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 营销活动Service业务层处理
 *
 * @author yzg
 * @date 2023-12-14
 */
@RequiredArgsConstructor
@Service
public class MarketActivityServiceImpl implements IMarketActivityService {

    private final MarketActivityMapper baseMapper;

    /**
     * 查询营销活动
     */
    @Override
    public MarketActivityVo queryById(Long activityId) {
        return baseMapper.selectVoById(activityId);
    }

    /**
     * 查询营销活动列表
     */
    @Override
    public TableDataInfo<MarketActivityVo> queryPageList(MarketActivityBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MarketActivity> lqw = buildQueryWrapper(bo);
        Page<MarketActivityVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询营销活动列表
     */
    @Override
    public List<MarketActivityVo> queryList(MarketActivityBo bo) {
        LambdaQueryWrapper<MarketActivity> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MarketActivity> buildQueryWrapper(MarketActivityBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MarketActivity> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getPlatformKey() != null, MarketActivity::getPlatformKey, bo.getPlatformKey());
        lqw.like(StringUtils.isNotBlank(bo.getName()), MarketActivity::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getImage()), MarketActivity::getImage, bo.getImage());
        lqw.eq(StringUtils.isNotBlank(bo.getRuleImg()), MarketActivity::getRuleImg, bo.getRuleImg());
        lqw.eq(StringUtils.isNotBlank(bo.getDetailsImg()), MarketActivity::getDetailsImg, bo.getDetailsImg());
        lqw.eq(StringUtils.isNotBlank(bo.getChannel()), MarketActivity::getChannel, bo.getChannel());
        return lqw;
    }

    /**
     * 新增营销活动
     */
    @Override
    public Boolean insertByBo(MarketActivityBo bo) {
        MarketActivity add = BeanUtil.toBean(bo, MarketActivity.class);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setActivityId(add.getActivityId());
        }
        return flag;
    }

    /**
     * 修改营销活动
     */
    @Override
    public Boolean updateByBo(MarketActivityBo bo) {
        MarketActivity update = BeanUtil.toBean(bo, MarketActivity.class);
        return baseMapper.updateById(update) > 0;
    }


    /**
     * 批量删除营销活动
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
