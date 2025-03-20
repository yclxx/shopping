package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.UnionpayMissionGroupBgBo;
import org.dromara.shopping.domain.vo.UnionpayMissionGroupBgVo;

import java.util.Collection;
import java.util.List;

/**
 * 任务组背景Service接口
 *
 * @author yzg
 * @date 2024-03-02
 */
public interface IUnionpayMissionGroupBgService {

    /**
     * 查询任务组背景
     */
    UnionpayMissionGroupBgVo queryById(Long missionBgId);

    /**
     * 查询任务组背景列表
     */
    TableDataInfo<UnionpayMissionGroupBgVo> queryPageList(UnionpayMissionGroupBgBo bo, PageQuery pageQuery);

    /**
     * 查询任务组背景列表
     */
    List<UnionpayMissionGroupBgVo> queryList(UnionpayMissionGroupBgBo bo);

    /**
     * 修改任务组背景
     */
    Boolean insertByBo(UnionpayMissionGroupBgBo bo);

    /**
     * 修改任务组背景
     */
    Boolean updateByBo(UnionpayMissionGroupBgBo bo);

    /**
     * 校验并批量删除任务组背景信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
