package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.GroupActivityBo;
import org.dromara.shopping.domain.vo.GroupActivityVo;

import java.util.Collection;
import java.util.List;

/**
 * 拼团活动Service接口
 *
 * @author yzg
 * @date 2024-09-26
 */
public interface IGroupActivityService {

    /**
     * 查询拼团活动
     */
    GroupActivityVo queryById(Long activityId);

    /**
     * 查询拼团活动列表
     */
    TableDataInfo<GroupActivityVo> queryPageList(GroupActivityBo bo, PageQuery pageQuery);

    /**
     * 查询拼团活动列表
     */
    List<GroupActivityVo> queryList(GroupActivityBo bo);

    /**
     * 修改拼团活动
     */
    Boolean insertByBo(GroupActivityBo bo);

    /**
     * 修改拼团活动
     */
    Boolean updateByBo(GroupActivityBo bo);

    /**
     * 校验并批量删除拼团活动信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 改变拼团活动信息
     */
    Boolean changeGroupActivity(GroupActivityBo bo);

    /**
     * 上下架商品
     */
    void groupStatus();
}
