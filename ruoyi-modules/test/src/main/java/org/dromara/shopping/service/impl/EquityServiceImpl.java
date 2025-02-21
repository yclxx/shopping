package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.Equity;
import org.dromara.shopping.domain.bo.EquityBo;
import org.dromara.shopping.domain.vo.EquityVo;
import org.dromara.shopping.mapper.EquityMapper;
import org.dromara.shopping.service.IEquityService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 权益包Service业务层处理
 *
 * @author yzg
 * @date 2023-06-06
 */
@RequiredArgsConstructor
@Service
public class EquityServiceImpl implements IEquityService {

    private final EquityMapper baseMapper;

    /**
     * 查询权益包
     */
    @Override
    public EquityVo queryById(Long equityId) {
        return baseMapper.selectVoById(equityId);
    }

    /**
     * 查询权益包列表
     */
    @Override
    public TableDataInfo<EquityVo> queryPageList(EquityBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Equity> lqw = buildQueryWrapper(bo);
        Page<EquityVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询权益包列表
     */
    @Override
    public List<EquityVo> queryList(EquityBo bo) {
        LambdaQueryWrapper<Equity> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Equity> buildQueryWrapper(EquityBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Equity> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getEquityName()), Equity::getEquityName, bo.getEquityName());
        lqw.between(params.get("beginShowStartDate") != null && params.get("endShowStartDate") != null,
            Equity::getShowStartDate, params.get("beginShowStartDate"), params.get("endShowStartDate"));
        lqw.between(params.get("beginShowEndDate") != null && params.get("endShowEndDate") != null,
            Equity::getShowEndDate, params.get("beginShowEndDate"), params.get("endShowEndDate"));
        lqw.between(params.get("beginSellStartDate") != null && params.get("endSellStartDate") != null,
            Equity::getSellStartDate, params.get("beginSellStartDate"), params.get("endSellStartDate"));
        lqw.between(params.get("beginSellEndDate") != null && params.get("endSellEndDate") != null,
            Equity::getSellEndDate, params.get("beginSellEndDate"), params.get("endSellEndDate"));
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), Equity::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增权益包
     */
    @Override
    public Boolean insertByBo(EquityBo bo) {
        Equity add = BeanUtil.toBean(bo, Equity.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setEquityId(add.getEquityId());
        }
        return flag;
    }

    /**
     * 修改权益包
     */
    @Override
    public Boolean updateByBo(EquityBo bo) {
        Equity update = BeanUtil.toBean(bo, Equity.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Equity entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除权益包
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
