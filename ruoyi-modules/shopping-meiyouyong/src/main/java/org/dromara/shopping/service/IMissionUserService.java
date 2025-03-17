package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.MissionUserBo;
import org.dromara.shopping.domain.vo.MissionUserVo;

import java.util.Collection;
import java.util.List;

/**
 * 任务用户Service接口
 *
 * @author yzg
 * @date 2023-05-10
 */
public interface IMissionUserService {

    /**
     * 查询任务用户
     */
    MissionUserVo queryById(Long missionUserId);

    /**
     * 查询任务用户列表
     */
    TableDataInfo<MissionUserVo> queryPageList(MissionUserBo bo, PageQuery pageQuery);

    /**
     * 查询任务用户列表
     */
    List<MissionUserVo> queryList(MissionUserBo bo);

    /**
     * 修改任务用户
     */
    Boolean insertByBo(MissionUserBo bo);

    /**
     * 修改任务用户
     */
    Boolean updateByBo(MissionUserBo bo);

    /**
     * 校验并批量删除任务用户信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
