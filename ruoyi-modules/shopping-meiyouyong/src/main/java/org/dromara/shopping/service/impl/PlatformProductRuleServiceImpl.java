package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.PlatformProductRule;
import org.dromara.shopping.domain.bo.PlatformProductRuleBo;
import org.dromara.shopping.domain.vo.PlatformProductRuleVo;
import org.dromara.shopping.mapper.PlatformProductRuleMapper;
import org.dromara.shopping.service.IPlatformProductRuleService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 商品规则Service业务层处理
 *
 * @author yzg
 * @date 2024-06-24
 */
@RequiredArgsConstructor
@Service
public class PlatformProductRuleServiceImpl implements IPlatformProductRuleService {

    private final PlatformProductRuleMapper baseMapper;

    /**
     * 查询商品规则
     */
    @Override
    public PlatformProductRuleVo queryById(Long platformKey) {
        return baseMapper.selectVoById(platformKey);
    }

    /**
     * 查询商品规则列表
     */
    @Override
    public TableDataInfo<PlatformProductRuleVo> queryPageList(PlatformProductRuleBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<PlatformProductRule> lqw = buildQueryWrapper(bo);
        Page<PlatformProductRuleVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询商品规则列表
     */
    @Override
    public List<PlatformProductRuleVo> queryList(PlatformProductRuleBo bo) {
        LambdaQueryWrapper<PlatformProductRule> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<PlatformProductRule> buildQueryWrapper(PlatformProductRuleBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<PlatformProductRule> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getSupportSupplier()), PlatformProductRule::getSupportSupplier, bo.getSupportSupplier());
        lqw.eq(StringUtils.isNotBlank(bo.getCategoryIds()), PlatformProductRule::getCategoryIds, bo.getCategoryIds());
        return lqw;
    }

    /**
     * 新增商品规则
     */
    @Override
    public Boolean insertByBo(PlatformProductRuleBo bo) {
        // 清除商品列表缓存
        PlatformProductRule add = BeanUtil.toBean(bo, PlatformProductRule.class);
        return baseMapper.insertOrUpdate(add);
    }

    /**
     * 修改商品规则
     */
    @Override
    public Boolean updateByBo(PlatformProductRuleBo bo) {
        PlatformProductRule update = BeanUtil.toBean(bo, PlatformProductRule.class);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 批量删除商品规则
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
