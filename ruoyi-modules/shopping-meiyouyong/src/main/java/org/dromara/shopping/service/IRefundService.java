package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.RefundBo;
import org.dromara.shopping.domain.vo.RefundVo;

import java.util.Collection;
import java.util.List;

/**
 * 退款订单登记Service接口
 *
 * @author yzg
 * @date 2023-08-07
 */
public interface IRefundService {

    /**
     * 查询退款订单登记
     */
    RefundVo queryById(Long refundId);

    /**
     * 查询退款订单登记列表
     */
    TableDataInfo<RefundVo> queryPageList(RefundBo bo, PageQuery pageQuery);

    /**
     * 查询退款订单登记列表
     */
    List<RefundVo> queryList(RefundBo bo);

    /**
     * 修改退款订单登记
     */
    Boolean insertByBo(RefundBo bo);

    /**
     * 修改退款订单登记
     */
    Boolean updateByBo(RefundBo bo);

    /**
     * 审核通过
     */
    void agreeSubmit(Long refundId);

    /**
     * 审核拒绝
     */
    void refuseSubmit(Long refundId,String refuseReason);

    /**
     * 校验并批量删除退款订单登记信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
