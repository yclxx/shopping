package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.ActionBo;
import org.dromara.shopping.domain.vo.ActionVo;

import java.util.Collection;
import java.util.List;

/**
 * 优惠券批次Service接口
 *
 * @author yzg
 * @date 2023-10-12
 */
public interface IActionService {

    /**
     * 查询优惠券批次
     */
    ActionVo queryById(Long actionId);

    /**
     * 查询优惠券批次列表
     */
    TableDataInfo<ActionVo> queryPageList(ActionBo bo, PageQuery pageQuery);

    /**
     * 查询优惠券批次列表
     */
    List<ActionVo> queryList(ActionBo bo);

    /**
     * 修改优惠券批次
     */
    Boolean insertByBo(ActionBo bo);

    /**
     * 创建优惠券
     */
    Boolean createCoupon(Long actionId,Long number);

    /**
     * 修改批次商品关联表
     *
     * @return
     */
    int updateActionProduct(List<Long> productIds, Long actionId,Integer type);

    /**
     * 修改优惠券批次
     */
    Boolean updateByBo(ActionBo bo);

    /**
     * 校验并批量删除优惠券批次信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
