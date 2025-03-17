package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.RefundCouponLogBo;
import org.dromara.shopping.domain.vo.RefundCouponLogVo;

import java.util.Collection;
import java.util.List;

/**
 * 返还记录Service接口
 *
 * @author yzg
 * @date 2025-01-15
 */
public interface IRefundCouponLogService {

    /**
     * 查询返还记录
     */
    RefundCouponLogVo queryById(Long id);

    /**
     * 查询返还记录列表
     */
    TableDataInfo<RefundCouponLogVo> queryPageList(RefundCouponLogBo bo, PageQuery pageQuery);

    /**
     * 查询返还记录列表
     */
    List<RefundCouponLogVo> queryList(RefundCouponLogBo bo);

    /**
     * 修改返还记录
     */
    Boolean insertByBo(RefundCouponLogBo bo);

    /**
     * 修改返还记录
     */
    Boolean updateByBo(RefundCouponLogBo bo);

    /**
     * 校验并批量删除返还记录信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
