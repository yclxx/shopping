package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.MissionGroupBo;
import org.dromara.shopping.domain.vo.MissionGroupVo;

import java.util.Collection;
import java.util.List;

/**
 * 任务组Service接口
 *
 * @author yzg
 * @date 2023-05-10
 */
public interface IMissionGroupService {

    /**
     * 查询任务组
     */
    MissionGroupVo queryById(Long missionGroupId);

    /**
     * 查询任务组列表
     */
    TableDataInfo<MissionGroupVo> queryPageList(MissionGroupBo bo, PageQuery pageQuery);

    /**
     * 查询任务组列表
     */
    List<MissionGroupVo> queryList(MissionGroupBo bo);

    /**
     * 修改任务组
     */
    Boolean insertByBo(MissionGroupBo bo);

    /**
     * 修改任务组
     */
    Boolean updateByBo(MissionGroupBo bo);

    /**
     * 校验并批量删除任务组信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
