package org.dromara.shopping.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.BusinessDistrictShop;
import org.dromara.shopping.domain.bo.BusinessDistrictShopBo;
import org.dromara.shopping.domain.vo.BusinessDistrictShopVo;

import java.util.Collection;
import java.util.List;

/**
 * 商圈门店关联Service接口
 *
 * @author yzg
 * @date 2023-09-15
 */
public interface IBusinessDistrictShopService {

    /**
     * 查询商圈门店关联
     */
    BusinessDistrictShopVo queryById(Long id);

    /**
     * 查询商圈门店关联列表
     */
    TableDataInfo<BusinessDistrictShopVo> queryPageList(BusinessDistrictShopBo bo, PageQuery pageQuery);

    /**
     * 查询商圈门店关联列表
     */
    List<BusinessDistrictShopVo> queryList(BusinessDistrictShopBo bo);

    /**
     * 修改商圈门店关联
     */
    Boolean insertByBo(BusinessDistrictShopBo bo);

    /**
     * 修改商圈门店关联
     */
    Boolean updateByBo(BusinessDistrictShopBo bo);

    /**
     * 校验并批量删除商圈门店关联信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    Integer deleteWithValidByShopId(Long shopId);

    Boolean remove(LambdaQueryWrapper<BusinessDistrictShop> queryWrapper);

    Boolean addShopByProduct(BusinessDistrictShopBo bo);

    int delByShopProduct(BusinessDistrictShopBo bo);
}
