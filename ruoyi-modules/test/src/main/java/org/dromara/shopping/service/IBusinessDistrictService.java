package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.BusinessDistrictBo;
import org.dromara.shopping.domain.vo.BusinessDistrictVo;

import java.util.Collection;
import java.util.List;

/**
 * 商圈Service接口
 *
 * @author yzg
 * @date 2023-09-15
 */
public interface IBusinessDistrictService {

    /**
     * 查询商圈
     */
    BusinessDistrictVo queryById(Long businessDistrictId);

    /**
     * 查询商圈列表
     */
    TableDataInfo<BusinessDistrictVo> queryPageList(BusinessDistrictBo bo, PageQuery pageQuery);

    /**
     * 查询商圈列表
     */
    List<BusinessDistrictVo> queryList(BusinessDistrictBo bo);

    /**
     * 修改商圈
     */
    Boolean insertByBo(BusinessDistrictBo bo);

    /**
     * 修改商圈
     */
    Boolean updateByBo(BusinessDistrictBo bo);

    /**
     * 校验并批量删除商圈信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
