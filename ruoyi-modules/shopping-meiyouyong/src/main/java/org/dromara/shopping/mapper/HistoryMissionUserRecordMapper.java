package org.dromara.shopping.mapper;

import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.shopping.domain.HistoryMissionUserRecord;
import org.dromara.shopping.domain.vo.MissionUserRecordVo;

import java.util.List;

/**
 * 活动记录Mapper接口
 *
 * @author yzg
 * @date 2023-05-10
 */
public interface HistoryMissionUserRecordMapper extends BaseMapperPlus<HistoryMissionUserRecord, MissionUserRecordVo> {

    int insertByMissionUserRecord(@Param("recordIds") List<Long> recordIds);

    int deleteByMissionUserRecord(@Param("recordIds") List<Long> recordIds);
}
