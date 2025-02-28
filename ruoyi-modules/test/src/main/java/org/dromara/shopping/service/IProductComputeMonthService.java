package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.ProductComputeMonthBo;
import org.dromara.shopping.domain.vo.ProductComputeMonthVo;

import java.util.Collection;
import java.util.List;

/**
 * 订单数据统计（月份）Service接口
 *
 * @author yzg
 * @date 2023-07-12
 */
public interface IProductComputeMonthService {

    /**
     * 查询订单数据统计（月份）
     */
    ProductComputeMonthVo queryById(Long id);

    /**
     * 查询订单数据统计（月份）列表
     */
    TableDataInfo<ProductComputeMonthVo> queryPageList(ProductComputeMonthBo bo, PageQuery pageQuery);

    /**
     * 查询订单数据统计（月份）列表
     */
    List<ProductComputeMonthVo> queryList(ProductComputeMonthBo bo);

    /**
     * 修改订单数据统计（月份）
     */
    Boolean insertByBo(ProductComputeMonthBo bo);

    /**
     * 修改订单数据统计（月份）
     */
    Boolean updateByBo(ProductComputeMonthBo bo);

    /**
     * 校验并批量删除订单数据统计（月份）信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
