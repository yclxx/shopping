package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.CityMerchantBo;
import org.dromara.shopping.domain.vo.CityMerchantVo;

import java.util.Collection;
import java.util.List;

/**
 * 城市商户Service接口
 *
 * @author yzgnet
 * @date 2023-03-21
 */
public interface ICityMerchantService {

    /**
     * 查询城市商户
     */
    CityMerchantVo queryById(Long id);

    /**
     * 查询城市商户列表
     */
    TableDataInfo<CityMerchantVo> queryPageList(CityMerchantBo bo, PageQuery pageQuery);

    /**
     * 查询城市商户列表
     */
    List<CityMerchantVo> queryList(CityMerchantBo bo);

    /**
     * 修改城市商户
     */
    Boolean insertByBo(CityMerchantBo bo);

    /**
     * 修改城市商户
     */
    Boolean updateByBo(CityMerchantBo bo);

    /**
     * 校验并批量删除城市商户信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
