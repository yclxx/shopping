package org.dromara.shopping.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.annotation.DataColumn;
import org.dromara.common.mybatis.annotation.DataPermission;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.shopping.domain.HistoryOrder;
import org.dromara.shopping.domain.vo.HistoryOrderVo;
import org.dromara.shopping.domain.vo.UserPayCountVo;

/**
 * 历史订单Mapper接口
 *
 * @author yzg
 * @date 2023-08-01
 */
@DataPermission({
    @DataColumn(key = "deptName", value = "sys_dept_id"),
    @DataColumn(key = "userName", value = "sys_user_id")
})
public interface HistoryOrderMapper extends BaseMapperPlus< HistoryOrder, HistoryOrderVo> {

    Page<UserPayCountVo> selectUserPayCount(Page<UserPayCountVo> page, @Param("productId") Long productId);
}
