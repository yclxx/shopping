package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.MissionBo;
import org.dromara.shopping.domain.vo.MissionVo;

import java.util.Collection;
import java.util.List;

/**
 * 任务配置Service接口
 *
 * @author yzg
 * @date 2023-05-10
 */
public interface IMissionService {

    /**
     * 查询任务配置
     */
    MissionVo queryById(Long missionId);

    /**
     * 查询任务配置列表
     */
    TableDataInfo<MissionVo> queryPageList(MissionBo bo, PageQuery pageQuery);

    /**
     * 查询任务配置列表
     */
    List<MissionVo> queryList(MissionBo bo);

    /**
     * 修改任务配置
     */
    Boolean insertByBo(MissionBo bo);

    /**
     * 修改任务配置
     */
    Boolean updateByBo(MissionBo bo);

    /**
     * 校验并批量删除任务配置信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
