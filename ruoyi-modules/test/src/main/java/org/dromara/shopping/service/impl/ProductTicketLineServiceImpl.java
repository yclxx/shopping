package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.ProductTicketLine;
import org.dromara.shopping.domain.bo.ProductTicketLineBo;
import org.dromara.shopping.domain.vo.ProductTicketLineVo;
import org.dromara.shopping.mapper.ProductTicketLineMapper;
import org.dromara.shopping.service.IProductTicketLineService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 演出票种Service业务层处理
 *
 * @author yzg
 * @date 2023-09-12
 */
@RequiredArgsConstructor
@Service
public class ProductTicketLineServiceImpl implements IProductTicketLineService {

    private final ProductTicketLineMapper baseMapper;

    /**
     * 查询演出票种
     */
    @Override
    public ProductTicketLineVo queryById(Long lineId) {
        return baseMapper.selectVoById(lineId);
    }

    /**
     * 查询演出票种列表
     */
    @Override
    public TableDataInfo<ProductTicketLineVo> queryPageList(ProductTicketLineBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ProductTicketLine> lqw = buildQueryWrapper(bo);
        Page<ProductTicketLineVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询演出票种列表
     */
    @Override
    public List<ProductTicketLineVo> queryList(ProductTicketLineBo bo) {
        LambdaQueryWrapper<ProductTicketLine> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ProductTicketLine> buildQueryWrapper(ProductTicketLineBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ProductTicketLine> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getProductId() != null, ProductTicketLine::getProductId, bo.getProductId());
        lqw.eq(bo.getSessionId() != null, ProductTicketLine::getSessionId, bo.getSessionId());
        lqw.eq(StringUtils.isNotBlank(bo.getLineTitle()), ProductTicketLine::getLineTitle, bo.getLineTitle());
        lqw.eq(StringUtils.isNotBlank(bo.getLineStatus()), ProductTicketLine::getLineStatus, bo.getLineStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getLineDescription()), ProductTicketLine::getLineDescription, bo.getLineDescription());
        return lqw;
    }

    /**
     * 新增演出票种
     */
    @Override
    public Boolean insertByBo(ProductTicketLineBo bo) {
        ProductTicketLine add = BeanUtil.toBean(bo, ProductTicketLine.class);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setLineId(add.getLineId());
        }
        return flag;
    }

    /**
     * 修改演出票种
     */
    @Override
    public Boolean updateByBo(ProductTicketLineBo bo) {
        ProductTicketLine update = BeanUtil.toBean(bo, ProductTicketLine.class);
        return baseMapper.updateById(update) > 0;
    }

    @Override
    public void updateByBoList(List<ProductTicketLineBo> ticketLine, Long productId, Long sessionId) {
        if (ticketLine.isEmpty()) throw new ServiceException("票种信息不能为空。");
        List<Long> lineIds = ticketLine.stream().map(ProductTicketLineBo::getLineId).collect(Collectors.toList());
        this.deleteBySessionAndProduct(productId, sessionId, lineIds);
        for (ProductTicketLineBo lineBo : ticketLine) {
            if (lineBo.getLineId() == null) {
                lineBo.setProductId(productId);
                lineBo.setSessionId(sessionId);
                this.insertByBo(lineBo);
            } else {
                this.updateByBo(lineBo);
            }
        }
    }

    @Override
    public void deleteBySessionAndProduct(Long productId, List<Long> notSessionIds) {
        // 删除不惜要的数据
        LambdaQueryWrapper<ProductTicketLine> lines = new LambdaQueryWrapper<>();
        lines.eq(ProductTicketLine::getProductId, productId);
        lines.notIn(ProductTicketLine::getSessionId, notSessionIds);
        baseMapper.delete(lines);
    }

    public void deleteBySessionAndProduct(Long productId, Long sessionId, List<Long> notIds) {
        // 删除不惜要的数据
        LambdaQueryWrapper<ProductTicketLine> lines = new LambdaQueryWrapper<>();
        lines.eq(ProductTicketLine::getProductId, productId);
        lines.eq(ProductTicketLine::getSessionId, sessionId);
        lines.notIn(ProductTicketLine::getLineId, notIds);
        baseMapper.delete(lines);
    }

    /**
     * 批量删除演出票种
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
