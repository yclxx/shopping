package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.ProductTicketSession;
import org.dromara.shopping.domain.bo.ProductTicketLineBo;
import org.dromara.shopping.domain.bo.ProductTicketSessionBo;
import org.dromara.shopping.domain.vo.ProductTicketLineVo;
import org.dromara.shopping.domain.vo.ProductTicketSessionVo;
import org.dromara.shopping.mapper.ProductTicketSessionMapper;
import org.dromara.shopping.service.IProductTicketLineService;
import org.dromara.shopping.service.IProductTicketSessionService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 演出场次Service业务层处理
 *
 * @author yzg
 * @date 2023-09-12
 */
@RequiredArgsConstructor
@Service
public class ProductTicketSessionServiceImpl implements IProductTicketSessionService {

    private final ProductTicketSessionMapper baseMapper;
    private final IProductTicketLineService productTicketLineService;

    /**
     * 查询演出场次
     */
    @Override
    public ProductTicketSessionVo queryById(Long priceId) {
        ProductTicketSessionVo productTicketSessionVo = baseMapper.selectVoById(priceId);
        ProductTicketLineBo lineBo = new ProductTicketLineBo();
        lineBo.setSessionId(productTicketSessionVo.getSessionId());
        List<ProductTicketLineVo> productTicketLineVos = productTicketLineService.queryList(lineBo);
        productTicketSessionVo.setTicketLine(productTicketLineVos);
        return productTicketSessionVo;
    }

    /**
     * 查询演出场次列表
     */
    @Override
    public TableDataInfo<ProductTicketSessionVo> queryPageList(ProductTicketSessionBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ProductTicketSession> lqw = buildQueryWrapper(bo);
        Page<ProductTicketSessionVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询演出场次列表
     */
    @Override
    public List<ProductTicketSessionVo> queryList(ProductTicketSessionBo bo) {
        LambdaQueryWrapper<ProductTicketSession> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    /**
     * 查询演出场次与票种列表
     */
    @Override
    public List<ProductTicketSessionVo> queryLists(ProductTicketSessionBo bo) {
        LambdaQueryWrapper<ProductTicketSession> lqw = buildQueryWrapper(bo);
        List<ProductTicketSessionVo> ticketSessionVos = baseMapper.selectVoList(lqw);
        for (ProductTicketSessionVo sessionVo : ticketSessionVos) {
            ProductTicketLineBo line = new ProductTicketLineBo();
            line.setProductId(sessionVo.getProductId());
            line.setSessionId(sessionVo.getSessionId());
            List<ProductTicketLineVo> productTicketLineVos = productTicketLineService.queryList(line);
            sessionVo.setTicketLine(productTicketLineVos);
        }
        return ticketSessionVos;
    }

    private LambdaQueryWrapper<ProductTicketSession> buildQueryWrapper(ProductTicketSessionBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ProductTicketSession> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getProductId() != null, ProductTicketSession::getProductId, bo.getProductId());
        lqw.eq(StringUtils.isNotBlank(bo.getSession()), ProductTicketSession::getSession, bo.getSession());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), ProductTicketSession::getStatus, bo.getStatus());
        lqw.eq(bo.getDate() != null, ProductTicketSession::getDate, bo.getDate());
        lqw.eq(StringUtils.isNotBlank(bo.getDescription()), ProductTicketSession::getDescription, bo.getDescription());
        return lqw;
    }

    /**
     * 新增演出场次
     */
    @Override
    public Boolean insertByBo(ProductTicketSessionBo bo) {
        bo.setSessionId(IdUtil.getSnowflakeNextId());
        if (!bo.getTicketLine().isEmpty()) {
            for (ProductTicketLineBo lineBo : bo.getTicketLine()) {
                lineBo.setSessionId(bo.getSessionId());
                lineBo.setProductId(bo.getProductId());
                productTicketLineService.insertByBo(lineBo);
            }
        } else {
            throw new ServiceException("票种信息不能为空.");
        }
        ProductTicketSession add = BeanUtil.toBean(bo, ProductTicketSession.class);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setSessionId(add.getSessionId());
        }
        return flag;
    }

    /**
     * 新增演出场次
     */
    public void insertByBoList(List<ProductTicketSessionBo> bos, Long productId) {
        for (ProductTicketSessionBo bo : bos) {
            bo.setProductId(productId);
            bo.setSessionId(IdUtil.getSnowflakeNextId());
            if (!bo.getTicketLine().isEmpty()) {
                for (ProductTicketLineBo lineBo : bo.getTicketLine()) {
                    lineBo.setProductId(productId);
                    lineBo.setSessionId(bo.getSessionId());
                    productTicketLineService.insertByBo(lineBo);
                }
            } else {
                throw new ServiceException("票种信息不能为空.");
            }
            ProductTicketSession add = BeanUtil.toBean(bo, ProductTicketSession.class);
            baseMapper.insert(add);
        }
    }

    /**
     * 修改演出场次
     */
    @Override
    public Boolean updateByBo(ProductTicketSessionBo bo) {
        // 修改票种
        productTicketLineService.updateByBoList(bo.getTicketLine(), bo.getProductId(), bo.getSessionId());
        ProductTicketSession update = BeanUtil.toBean(bo, ProductTicketSession.class);
        return baseMapper.updateById(update) > 0;
    }

    @Override
    public Boolean updateByBoList(List<ProductTicketSessionBo> bos, Long productId) {
        // 删除所有非修改的次数相关票种数据
        List<Long> sessionIds = bos.stream().map(ProductTicketSessionBo::getSessionId).collect(Collectors.toList());
        LambdaQueryWrapper<ProductTicketSession> delWrapper = new LambdaQueryWrapper<>();
        delWrapper.eq(ProductTicketSession::getProductId, productId);
        delWrapper.notIn(ProductTicketSession::getSessionId, sessionIds);
        baseMapper.delete(delWrapper);
        productTicketLineService.deleteBySessionAndProduct(productId, sessionIds);
        // 新增或修改场次数据
        for (ProductTicketSessionBo bo : bos) {
            if (null == bo.getSessionId()) {
                bo.setProductId(productId);
                this.insertByBo(bo);
            } else {
                this.updateByBo(bo);
            }
        }
        return null;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ProductTicketSession entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除演出场次
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
