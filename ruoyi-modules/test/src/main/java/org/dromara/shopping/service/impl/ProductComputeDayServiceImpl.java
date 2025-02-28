package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.core.utils.DateUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.ProductComputeDay;
import org.dromara.shopping.domain.bo.ProductComputeDayBo;
import org.dromara.shopping.domain.vo.ProductComputeDayVo;
import org.dromara.shopping.mapper.ProductComputeDayMapper;
import org.dromara.shopping.service.IProductComputeDayService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 订单数据统计（每天）Service业务层处理
 *
 * @author yzg
 * @date 2023-07-12
 */
@RequiredArgsConstructor
@Service
public class ProductComputeDayServiceImpl implements IProductComputeDayService {

    private final ProductComputeDayMapper baseMapper;

    /**
     * 查询订单数据统计（每天）
     */
    @Override
    public ProductComputeDayVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询订单数据统计（每天）列表
     */
    @Override
    public TableDataInfo<ProductComputeDayVo> queryPageList(ProductComputeDayBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ProductComputeDay> lqw = buildQueryWrapper(bo);
        Page<ProductComputeDayVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询订单数据统计（每天）列表
     */
    @Override
    public List<ProductComputeDayVo> queryList(ProductComputeDayBo bo) {
        LambdaQueryWrapper<ProductComputeDay> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ProductComputeDay> buildQueryWrapper(ProductComputeDayBo bo) {
        LambdaQueryWrapper<ProductComputeDay> lqw = Wrappers.lambdaQuery();
        if (StringUtils.isNotEmpty(bo.getDayTimes())) {
            Date dayTime = DateUtils.parseDate(bo.getDayTimes());
            lqw.ge(StringUtils.isNotEmpty(bo.getDayTimes()), ProductComputeDay::getDayTime, DateUtils.parseDate(bo.getDayTimes()));
            lqw.le(StringUtils.isNotEmpty(bo.getDayTimes()), ProductComputeDay::getDayTime, DateUtil.endOfDay(dayTime));
        }
        lqw.eq(bo.getProductId() != null, ProductComputeDay::getProductId, bo.getProductId());
        lqw.eq(StringUtils.isNotBlank(bo.getCityCode()), ProductComputeDay::getCityCode, bo.getCityCode());
        lqw.like(StringUtils.isNotBlank(bo.getCityName()), ProductComputeDay::getCityName, bo.getCityName());
        return lqw;
    }

    /**
     * 新增订单数据统计（每天）
     */
    @Override
    public Boolean insertByBo(ProductComputeDayBo bo) {
        ProductComputeDay add = BeanUtil.toBean(bo, ProductComputeDay.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改订单数据统计（每天）
     */
    @Override
    public Boolean updateByBo(ProductComputeDayBo bo) {
        ProductComputeDay update = BeanUtil.toBean(bo, ProductComputeDay.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ProductComputeDay entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除订单数据统计（每天）
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
