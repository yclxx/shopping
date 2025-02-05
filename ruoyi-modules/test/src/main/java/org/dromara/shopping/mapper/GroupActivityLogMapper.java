package org.dromara.shopping.mapper;

import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.shopping.domain.GroupActivityLog;
import org.dromara.shopping.domain.vo.GroupActivityLogVo;

import java.util.List;

/**
 * 拼团活动记录Mapper接口
 *
 * @author yzg
 * @date 2024-10-10
 */
public interface GroupActivityLogMapper extends BaseMapperPlus<GroupActivityLog, GroupActivityLogVo> {

    /**
     * 拼团过期
     */
    List<GroupActivityLog> groupExpire();
}
