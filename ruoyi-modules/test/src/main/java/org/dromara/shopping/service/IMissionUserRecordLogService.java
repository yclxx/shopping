package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.MissionUserRecordLogBo;
import org.dromara.shopping.domain.vo.MissionUserRecordLogVo;

import java.util.Collection;
import java.util.List;

/**
 * 活动订单取码记录Service接口
 *
 * @author yzg
 * @date 2023-05-10
 */
public interface IMissionUserRecordLogService {

    /**
     * 查询活动订单取码记录
     */
    MissionUserRecordLogVo queryById(Long id);

    /**
     * 查询活动订单取码记录列表
     */
    TableDataInfo<MissionUserRecordLogVo> queryPageList(MissionUserRecordLogBo bo, PageQuery pageQuery);

    /**
     * 查询活动订单取码记录列表
     */
    List<MissionUserRecordLogVo> queryList(MissionUserRecordLogBo bo);

    /**
     * 修改活动订单取码记录
     */
    Boolean insertByBo(MissionUserRecordLogBo bo);

    /**
     * 修改活动订单取码记录
     */
    Boolean updateByBo(MissionUserRecordLogBo bo);

    /**
     * 校验并批量删除活动订单取码记录信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
