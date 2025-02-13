package org.dromara.boss.service;

import org.dromara.boss.domain.vo.ApplyLogVo;
import org.dromara.boss.domain.bo.ApplyLogBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 沟通记录Service接口
 *
 * @author xx
 * @date 2024-11-16
 */
public interface IApplyLogService {

    /**
     * 查询沟通记录
     *
     * @param applyLogId 主键
     * @return 沟通记录
     */
    ApplyLogVo queryById(Long applyLogId);

    /**
     * 分页查询沟通记录列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 沟通记录分页列表
     */
    TableDataInfo<ApplyLogVo> queryPageList(ApplyLogBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的沟通记录列表
     *
     * @param bo 查询条件
     * @return 沟通记录列表
     */
    List<ApplyLogVo> queryList(ApplyLogBo bo);

    /**
     * 新增沟通记录
     *
     * @param bo 沟通记录
     * @return 是否新增成功
     */
    Boolean insertByBo(ApplyLogBo bo);

    /**
     * 修改沟通记录
     *
     * @param bo 沟通记录
     * @return 是否修改成功
     */
    Boolean updateByBo(ApplyLogBo bo);

    /**
     * 校验并批量删除沟通记录信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
