package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.ProductTicketBo;
import org.dromara.shopping.domain.vo.ProductTicketVo;

import java.util.Collection;
import java.util.List;

/**
 * 演出票Service接口
 *
 * @author yzg
 * @date 2023-09-11
 */
public interface IProductTicketService {

    /**
     * 查询演出票
     */
    ProductTicketVo queryById(Long ticketId);

    ProductTicketVo queryByProductId(Long productId);

    /**
     * 查询演出票列表
     */
    TableDataInfo<ProductTicketVo> queryPageList(ProductTicketBo bo, PageQuery pageQuery);

    /**
     * 查询演出票列表
     */
    List<ProductTicketVo> queryList(ProductTicketBo bo);

    /**
     * 修改演出票
     */
    Boolean insertByBo(ProductTicketBo bo);

    /**
     * 修改演出票
     */
    Boolean updateByBo(ProductTicketBo bo);

    /**
     * 校验并批量删除演出票信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
