package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.PlatformProductRuleList;
import org.dromara.shopping.domain.bo.PlatformProductRuleListBo;
import org.dromara.shopping.domain.vo.PlatformProductRuleListVo;
import org.dromara.shopping.mapper.PlatformProductRuleListMapper;
import org.dromara.shopping.service.IPlatformProductRuleListService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 黑白名单Service业务层处理
 *
 * @author yzg
 * @date 2024-06-24
 */
@RequiredArgsConstructor
@Service
public class PlatformProductRuleListServiceImpl implements IPlatformProductRuleListService {

    private final PlatformProductRuleListMapper baseMapper;

    /**
     * 查询黑白名单
     */
    @Override
    public PlatformProductRuleListVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询黑白名单列表
     */
    @Override
    public TableDataInfo<PlatformProductRuleListVo> queryPageList(PlatformProductRuleListBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<PlatformProductRuleList> lqw = buildQueryWrapper(bo);
        Page<PlatformProductRuleListVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询黑白名单列表
     */
    @Override
    public List<PlatformProductRuleListVo> queryList(PlatformProductRuleListBo bo) {
        LambdaQueryWrapper<PlatformProductRuleList> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<PlatformProductRuleList> buildQueryWrapper(PlatformProductRuleListBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<PlatformProductRuleList> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getPlatformKey() != null, PlatformProductRuleList::getPlatformKey, bo.getPlatformKey());
        lqw.eq(bo.getProductId() != null, PlatformProductRuleList::getProductId, bo.getProductId());
        lqw.eq(StringUtils.isNotBlank(bo.getListType()), PlatformProductRuleList::getListType, bo.getListType());
        return lqw;
    }

    /**
     * 新增黑白名单
     */
    @Override
    public Boolean insertByBo(PlatformProductRuleListBo bo) {
        PlatformProductRuleList add = BeanUtil.toBean(bo, PlatformProductRuleList.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改黑白名单
     */
    @Override
    public Boolean updateByBo(PlatformProductRuleListBo bo) {
        PlatformProductRuleList update = BeanUtil.toBean(bo, PlatformProductRuleList.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(PlatformProductRuleList entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除黑白名单
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
