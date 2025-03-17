package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.MissionUserRecordBo;
import org.dromara.shopping.domain.vo.MissionUserRecordVo;

import java.util.Collection;
import java.util.List;

/**
 * 活动记录Service接口
 *
 * @author yzg
 * @date 2023-05-10
 */
public interface IMissionUserRecordService {

    /**
     * 查询活动记录
     */
    MissionUserRecordVo queryById(Long missionUserRecordId);

    /**
     * 查询活动记录列表
     */
    TableDataInfo<MissionUserRecordVo> queryPageList(MissionUserRecordBo bo, PageQuery pageQuery);

    /**
     * 查询活动记录列表
     */
    List<MissionUserRecordVo> queryList(MissionUserRecordBo bo);

    /**
     * 修改活动记录
     */
    Boolean insertByBo(MissionUserRecordBo bo);

    /**
     * 作废
     */
    Boolean expiry(Collection<Long> ids);
}
