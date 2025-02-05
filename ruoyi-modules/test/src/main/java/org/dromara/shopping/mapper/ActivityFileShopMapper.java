package org.dromara.shopping.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.shopping.domain.ActivityFileShop;
import org.dromara.shopping.domain.bo.ActivityFileShopBo;
import org.dromara.shopping.domain.vo.ActivityFileShopVo;
import org.dromara.shopping.domain.vo.AreaVo;

import java.util.List;

/**
 * 活动商户Mapper接口
 *
 * @author yzg
 * @date 2024-01-03
 */
public interface ActivityFileShopMapper extends BaseMapperPlus<ActivityFileShop, ActivityFileShopVo> {

    /**
     * 获取商户列表
     */
    Page<ActivityFileShopVo> selectFileShopList(Page page,@Param("bo") ActivityFileShopBo bo);

    /**
     * 获取当前查询批次城市列表
     */
    List<AreaVo> selectCityList(String fileId);

    /**
     * 查询商户类型
     */
    List<Long> selectTypeByFileId(String fileId);
}
