package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.UnionpayMissionUserLogBo;
import org.dromara.shopping.domain.vo.UnionpayMissionUserLogVo;

import java.util.Collection;
import java.util.List;

/**
 * 银联任务奖励发放记录Service接口
 *
 * @author yzg
 * @date 2024-02-21
 */
public interface IUnionpayMissionUserLogService {

    /**
     * 查询银联任务奖励发放记录
     */
    UnionpayMissionUserLogVo queryById(Long upMissionUserLog);

    /**
     * 查询银联任务奖励发放记录列表
     */
    TableDataInfo<UnionpayMissionUserLogVo> queryPageList(UnionpayMissionUserLogBo bo, PageQuery pageQuery);

    /**
     * 查询银联任务奖励发放记录列表
     */
    List<UnionpayMissionUserLogVo> queryList(UnionpayMissionUserLogBo bo);

    /**
     * 修改银联任务奖励发放记录
     */
    Boolean insertByBo(UnionpayMissionUserLogBo bo);

    /**
     * 修改银联任务奖励发放记录
     */
    Boolean updateByBo(UnionpayMissionUserLogBo bo);

    /**
     * 校验并批量删除银联任务奖励发放记录信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
