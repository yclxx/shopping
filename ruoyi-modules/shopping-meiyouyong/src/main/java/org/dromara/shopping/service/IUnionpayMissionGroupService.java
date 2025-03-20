package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.UnionpayMissionGroupBo;
import org.dromara.shopping.domain.vo.UnionpayMissionGroupVo;

import java.util.Collection;
import java.util.List;

/**
 * 银联任务组Service接口
 *
 * @author yzg
 * @date 2024-02-21
 */
public interface IUnionpayMissionGroupService {

    /**
     * 查询银联任务组
     */
    UnionpayMissionGroupVo queryById(Long upMissionGroupId);

    /**
     * 查询银联任务组列表
     */
    TableDataInfo<UnionpayMissionGroupVo> queryPageList(UnionpayMissionGroupBo bo, PageQuery pageQuery);

    /**
     * 查询银联任务组列表
     */
    List<UnionpayMissionGroupVo> queryList(UnionpayMissionGroupBo bo);

    /**
     * 修改银联任务组
     */
    Boolean insertByBo(UnionpayMissionGroupBo bo);

    /**
     * 修改银联任务组
     */
    Boolean updateByBo(UnionpayMissionGroupBo bo);

    /**
     * 校验并批量删除银联任务组信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
