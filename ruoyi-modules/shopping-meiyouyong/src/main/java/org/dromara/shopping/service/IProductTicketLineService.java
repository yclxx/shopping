package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.ProductTicketLineBo;
import org.dromara.shopping.domain.vo.ProductTicketLineVo;

import java.util.Collection;
import java.util.List;

/**
 * 演出票种Service接口
 *
 * @author yzg
 * @date 2023-09-12
 */
public interface IProductTicketLineService {

    /**
     * 查询演出票种
     */
    ProductTicketLineVo queryById(Long lineId);

    /**
     * 查询演出票种列表
     */
    TableDataInfo<ProductTicketLineVo> queryPageList(ProductTicketLineBo bo, PageQuery pageQuery);

    /**
     * 查询演出票种列表
     */
    List<ProductTicketLineVo> queryList(ProductTicketLineBo bo);

    /**
     * 修改演出票种
     */
    Boolean insertByBo(ProductTicketLineBo bo);

    /**
     * 修改演出票种
     */
    Boolean updateByBo(ProductTicketLineBo bo);

    /**
     * 批量修改演出票种
     */
    void updateByBoList(List<ProductTicketLineBo> ticketLine, Long productId, Long sessionId);

    /**
     * 根据条件删除票种
     */
    void deleteBySessionAndProduct(Long productId, List<Long> notSessionIds);

    /**
     * 校验并批量删除演出票种信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
