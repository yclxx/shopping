package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.CommercialActivityApplyBo;
import org.dromara.shopping.domain.vo.CommercialActivityApplyVo;

import java.util.Collection;
import java.util.List;

/**
 * 商户活动报名Service接口
 *
 * @author yzg
 * @date 2024-04-10
 */
public interface ICommercialActivityApplyService {

    /**
     * 查询商户活动报名
     */
    CommercialActivityApplyVo queryById(Long applyId);

    /**
     * 查询商户活动报名列表
     */
    TableDataInfo<CommercialActivityApplyVo> queryPageList(CommercialActivityApplyBo bo, PageQuery pageQuery);

    /**
     * 查询商户活动报名列表
     */
    List<CommercialActivityApplyVo> queryList(CommercialActivityApplyBo bo);

    /**
     * 修改商户活动报名
     */
    Boolean insertByBo(CommercialActivityApplyBo bo);

    /**
     * 修改商户活动报名
     */
    Boolean updateByBo(CommercialActivityApplyBo bo);

    /**
     * 校验并批量删除商户活动报名信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
