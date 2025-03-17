package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.ProductDateMessage;
import org.dromara.shopping.domain.bo.ProductDateMessageBo;
import org.dromara.shopping.domain.vo.ProductDateMessageVo;
import org.dromara.shopping.mapper.ProductDateMessageMapper;
import org.dromara.shopping.service.IProductDateMessageService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 商品（旅游相关）每日价格库存Service业务层处理
 *
 * @author yzg
 * @date 2024-06-19
 */
@RequiredArgsConstructor
@Service
public class ProductDateMessageServiceImpl implements IProductDateMessageService {

    private final ProductDateMessageMapper baseMapper;

    /**
     * 查询商品（旅游相关）每日价格库存
     */
    @Override
    public ProductDateMessageVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询商品（旅游相关）每日价格库存列表
     */
    @Override
    public TableDataInfo<ProductDateMessageVo> queryPageList(ProductDateMessageBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ProductDateMessage> lqw = buildQueryWrapper(bo);
        Page<ProductDateMessageVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询商品（旅游相关）每日价格库存列表
     */
    @Override
    public List<ProductDateMessageVo> queryList(ProductDateMessageBo bo) {
        LambdaQueryWrapper<ProductDateMessage> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ProductDateMessage> buildQueryWrapper(ProductDateMessageBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ProductDateMessage> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getProductId() != null, ProductDateMessage::getProductId, bo.getProductId());
        lqw.eq(bo.getSellAmount() != null, ProductDateMessage::getSellAmount, bo.getSellAmount());
        lqw.eq(bo.getSettlementPrice() != null, ProductDateMessage::getSettlementPrice, bo.getSettlementPrice());
        lqw.eq(bo.getStock() != null, ProductDateMessage::getStock, bo.getStock());
        lqw.eq(StringUtils.isNotBlank(bo.getDate()), ProductDateMessage::getDate, bo.getDate());
        lqw.eq(StringUtils.isNotBlank(bo.getPriceType()), ProductDateMessage::getPriceType, bo.getPriceType());
        return lqw;
    }

    /**
     * 新增商品（旅游相关）每日价格库存
     */
    @Override
    public Boolean insertByBo(ProductDateMessageBo bo) {
        ProductDateMessage add = BeanUtil.toBean(bo, ProductDateMessage.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改商品（旅游相关）每日价格库存
     */
    @Override
    public Boolean updateByBo(ProductDateMessageBo bo) {
        ProductDateMessage update = BeanUtil.toBean(bo, ProductDateMessage.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ProductDateMessage entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除商品（旅游相关）每日价格库存
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public ProductDateMessageVo queryByIdAndDate(String exProductId, String date) {
        LambdaQueryWrapper<ProductDateMessage> lqw = Wrappers.lambdaQuery();
        lqw.eq(ProductDateMessage::getItemId, exProductId);
        lqw.eq(ProductDateMessage::getDate, date);
        lqw.last("order by id desc limit 1");
        return baseMapper.selectVoOne(lqw);

    }
}
