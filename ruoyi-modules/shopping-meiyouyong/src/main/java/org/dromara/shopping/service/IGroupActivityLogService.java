package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.GroupActivityLogBo;
import org.dromara.shopping.domain.vo.GroupActivityLogVo;

import java.util.Collection;
import java.util.List;

/**
 * 拼团活动记录Service接口
 *
 * @author yzg
 * @date 2024-10-10
 */
public interface IGroupActivityLogService {

    /**
     * 查询拼团活动记录
     */
    GroupActivityLogVo queryById(Long id);

    /**
     * 查询拼团活动记录列表
     */
    TableDataInfo<GroupActivityLogVo> queryPageList(GroupActivityLogBo bo, PageQuery pageQuery);

    /**
     * 查询拼团活动记录列表
     */
    List<GroupActivityLogVo> queryList(GroupActivityLogBo bo);

    /**
     * 修改拼团活动记录
     */
    Boolean insertByBo(GroupActivityLogBo bo);

    /**
     * 修改拼团活动记录
     */
    Boolean updateByBo(GroupActivityLogBo bo);

    /**
     * 校验并批量删除拼团活动记录信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
