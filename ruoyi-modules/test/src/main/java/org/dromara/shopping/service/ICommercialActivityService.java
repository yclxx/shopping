package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.CommercialActivityBo;
import org.dromara.shopping.domain.vo.CommercialActivityVo;

import java.util.Collection;
import java.util.List;

/**
 * 商户活动Service接口
 *
 * @author yzg
 * @date 2024-03-26
 */
public interface ICommercialActivityService {

    /**
     * 查询商户活动
     */
    CommercialActivityVo queryById(Long activityId);

    /**
     * 查询商户活动列表
     */
    TableDataInfo<CommercialActivityVo> queryPageList(CommercialActivityBo bo, PageQuery pageQuery);

    /**
     * 查询商户活动列表
     */
    List<CommercialActivityVo> queryList(CommercialActivityBo bo);

    /**
     * 修改商户活动
     */
    Boolean insertByBo(CommercialActivityBo bo);

    /**
     * 修改商户活动
     */
    Boolean updateByBo(CommercialActivityBo bo);

    /**
     * 校验并批量删除商户活动信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
