package org.dromara.shopping.mapper;

import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.shopping.domain.HistoryMissionUserRecordLog;
import org.dromara.shopping.domain.vo.MissionUserRecordLogVo;

import java.util.List;

/**
 * 活动订单取码记录Mapper接口
 *
 * @author yzg
 * @date 2023-05-10
 */
public interface HistoryMissionUserRecordLogMapper extends BaseMapperPlus<HistoryMissionUserRecordLog, MissionUserRecordLogVo> {

    int insertByMissionUserRecordLog(@Param("recordIds") List<Long> recordIds);

    int deleteByMissionUserRecordLog(@Param("recordIds") List<Long> recordIds);
}
