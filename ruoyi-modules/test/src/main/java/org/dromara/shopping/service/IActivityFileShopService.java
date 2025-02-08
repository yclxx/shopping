package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.ActivityFileShopBo;
import org.dromara.shopping.domain.vo.ActivityFileShopVo;

import java.util.Collection;
import java.util.List;

/**
 * 活动商户Service接口
 *
 * @author yzg
 * @date 2024-01-03
 */
public interface IActivityFileShopService {

    /**
     * 查询活动商户
     */
    ActivityFileShopVo queryById(Long activityShopId);

    /**
     * 查询活动商户列表
     */
    TableDataInfo<ActivityFileShopVo> queryPageList(ActivityFileShopBo bo, PageQuery pageQuery);

    /**
     * 查询活动商户列表
     */
    List<ActivityFileShopVo> queryList(ActivityFileShopBo bo);

    /**
     * 修改活动商户
     */
    Boolean insertByBo(ActivityFileShopBo bo);

    /**
     * 修改活动商户
     */
    Boolean updateByBo(ActivityFileShopBo bo);

    /**
     * 校验并批量删除活动商户信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
