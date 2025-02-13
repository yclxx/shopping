package org.dromara.boss.service;

import org.dromara.boss.domain.vo.ApplyJobVo;
import org.dromara.boss.domain.bo.ApplyJobBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 沟通任务Service接口
 *
 * @author xx
 * @date 2024-11-16
 */
public interface IApplyJobService {

    /**
     * 查询沟通任务
     *
     * @param applyJobId 主键
     * @return 沟通任务
     */
    ApplyJobVo queryById(Long applyJobId);

    /**
     * 分页查询沟通任务列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 沟通任务分页列表
     */
    TableDataInfo<ApplyJobVo> queryPageList(ApplyJobBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的沟通任务列表
     *
     * @param bo 查询条件
     * @return 沟通任务列表
     */
    List<ApplyJobVo> queryList(ApplyJobBo bo);

    /**
     * 新增沟通任务
     *
     * @param bo 沟通任务
     * @return 是否新增成功
     */
    Boolean insertByBo(ApplyJobBo bo);

    /**
     * 修改沟通任务
     *
     * @param bo 沟通任务
     * @return 是否修改成功
     */
    Boolean updateByBo(ApplyJobBo bo);

    /**
     * 校验并批量删除沟通任务信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
