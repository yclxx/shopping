package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.UnionPayContentOrder;
import org.dromara.shopping.domain.bo.UnionPayContentOrderBo;
import org.dromara.shopping.domain.vo.UnionPayContentOrderVo;
import org.dromara.shopping.mapper.UnionPayContentOrderMapper;
import org.dromara.shopping.service.IUnionPayContentOrderService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 内容分销内容方订单Service业务层处理
 *
 * @author yzg
 * @date 2023-09-16
 */
@RequiredArgsConstructor
@Service
public class UnionPayContentOrderServiceImpl implements IUnionPayContentOrderService {

    private final UnionPayContentOrderMapper baseMapper;

    /**
     * 查询内容分销内容方订单
     */
    @Override
    public UnionPayContentOrderVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询内容分销内容方订单列表
     */
    @Override
    public TableDataInfo<UnionPayContentOrderVo> queryPageList(UnionPayContentOrderBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<UnionPayContentOrder> lqw = buildQueryWrapper(bo);
        Page<UnionPayContentOrderVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询内容分销内容方订单列表
     */
    @Override
    public List<UnionPayContentOrderVo> queryList(UnionPayContentOrderBo bo) {
        LambdaQueryWrapper<UnionPayContentOrder> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<UnionPayContentOrder> buildQueryWrapper(UnionPayContentOrderBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<UnionPayContentOrder> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getUnionPayOrderId()), UnionPayContentOrder::getUnionPayOrderId, bo.getUnionPayOrderId());
        lqw.eq(StringUtils.isNotBlank(bo.getUnionPayProdId()), UnionPayContentOrder::getUnionPayProdId, bo.getUnionPayProdId());
        lqw.eq(StringUtils.isNotBlank(bo.getUnionPayResultStatus()), UnionPayContentOrder::getUnionPayResultStatus, bo.getUnionPayResultStatus());
        lqw.eq(bo.getNumber() != null, UnionPayContentOrder::getNumber, bo.getNumber());
        return lqw;
    }

    /**
     * 新增内容分销内容方订单
     */
    @Override
    public Boolean insertByBo(UnionPayContentOrderBo bo) {
        UnionPayContentOrder add = BeanUtil.toBean(bo, UnionPayContentOrder.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改内容分销内容方订单
     */
    @Override
    public Boolean updateByBo(UnionPayContentOrderBo bo) {
        UnionPayContentOrder update = BeanUtil.toBean(bo, UnionPayContentOrder.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(UnionPayContentOrder entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除内容分销内容方订单
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
