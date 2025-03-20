package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.UnionpayMissionProgressBo;
import org.dromara.shopping.domain.vo.UnionpayMissionProgressVo;

import java.util.Collection;
import java.util.List;

/**
 * 银联任务进度Service接口
 *
 * @author yzg
 * @date 2024-02-22
 */
public interface IUnionpayMissionProgressService {

    /**
     * 查询银联任务进度
     */
    UnionpayMissionProgressVo queryById(Long progressId);

    /**
     * 查询银联任务进度列表
     */
    TableDataInfo<UnionpayMissionProgressVo> queryPageList(UnionpayMissionProgressBo bo, PageQuery pageQuery);

    /**
     * 查询银联任务进度列表
     */
    List<UnionpayMissionProgressVo> queryList(UnionpayMissionProgressBo bo);

    /**
     * 修改银联任务进度
     */
    Boolean insertByBo(UnionpayMissionProgressBo bo);

    /**
     * 修改银联任务进度
     */
    Boolean updateByBo(UnionpayMissionProgressBo bo);

    /**
     * 校验并批量删除银联任务进度信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
