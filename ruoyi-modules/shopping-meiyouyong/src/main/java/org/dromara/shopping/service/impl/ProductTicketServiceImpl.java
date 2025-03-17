package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.ProductTicket;
import org.dromara.shopping.domain.bo.ProductTicketBo;
import org.dromara.shopping.domain.vo.ProductTicketVo;
import org.dromara.shopping.mapper.ProductTicketMapper;
import org.dromara.shopping.service.IProductTicketService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * 演出票Service业务层处理
 *
 * @author yzg
 * @date 2023-09-11
 */
@RequiredArgsConstructor
@Service
public class ProductTicketServiceImpl implements IProductTicketService {

    private final ProductTicketMapper baseMapper;

    /**
     * 查询演出票
     */
    @Override
    public ProductTicketVo queryById(Long ticketId) {
        return baseMapper.selectVoById(ticketId);
    }

    public ProductTicketVo queryByProductId(Long productId) {
        LambdaQueryWrapper<ProductTicket> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ProductTicket::getProductId, productId);
        return baseMapper.selectVoOne(lqw);
    }


    /**
     * 查询演出票列表
     */
    @Override
    public TableDataInfo<ProductTicketVo> queryPageList(ProductTicketBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ProductTicket> lqw = buildQueryWrapper(bo);
        Page<ProductTicketVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询演出票列表
     */
    @Override
    public List<ProductTicketVo> queryList(ProductTicketBo bo) {
        LambdaQueryWrapper<ProductTicket> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ProductTicket> buildQueryWrapper(ProductTicketBo bo) {
        //Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ProductTicket> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getProductId() != null, ProductTicket::getProductId, bo.getProductId());
        lqw.eq(StringUtils.isNotBlank(bo.getTicketStatus()), ProductTicket::getTicketStatus, bo.getTicketStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getTicketTicketType()), ProductTicket::getTicketTicketType, bo.getTicketTicketType());
        lqw.eq(StringUtils.isNotBlank(bo.getTicketChooseSeat()), ProductTicket::getTicketChooseSeat, bo.getTicketChooseSeat());
        lqw.eq(StringUtils.isNotBlank(bo.getTicketForm()), ProductTicket::getTicketForm, bo.getTicketForm());
        lqw.eq(StringUtils.isNotBlank(bo.getTicketCard()), ProductTicket::getTicketCard, bo.getTicketCard());
        lqw.eq(StringUtils.isNotBlank(bo.getTicketNonsupport()), ProductTicket::getTicketNonsupport, bo.getTicketNonsupport());
        lqw.eq(StringUtils.isNotBlank(bo.getTicketInvoice()), ProductTicket::getTicketInvoice, bo.getTicketInvoice());
        lqw.eq(StringUtils.isNotBlank(bo.getTicketExpired()), ProductTicket::getTicketExpired, bo.getTicketExpired());
        lqw.eq(StringUtils.isNotBlank(bo.getTicketAnyTime()), ProductTicket::getTicketAnyTime, bo.getTicketAnyTime());
        lqw.eq(StringUtils.isNotBlank(bo.getTicketPostWay()), ProductTicket::getTicketPostWay, bo.getTicketPostWay());
        lqw.eq(bo.getTicketPostage() != null, ProductTicket::getTicketPostage, bo.getTicketPostage());
        lqw.eq(StringUtils.isNotBlank(bo.getTicketNotice()), ProductTicket::getTicketNotice, bo.getTicketNotice());
        return lqw;
    }

    /**
     * 新增演出票
     */
    @Override
    public Boolean insertByBo(ProductTicketBo bo) {
        ProductTicket add = BeanUtil.toBean(bo, ProductTicket.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setTicketId(add.getTicketId());
        }
        return flag;
    }

    /**
     * 修改演出票
     */
    @Override
    public Boolean updateByBo(ProductTicketBo bo) {
        ProductTicket update = BeanUtil.toBean(bo, ProductTicket.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ProductTicket entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除演出票
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
