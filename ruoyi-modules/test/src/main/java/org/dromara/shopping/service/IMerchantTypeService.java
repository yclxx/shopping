package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.MerchantTypeBo;
import org.dromara.shopping.domain.vo.MerchantTypeVo;

import java.util.Collection;
import java.util.List;

/**
 * 商户门店类别Service接口
 *
 * @author yzg
 * @date 2024-01-04
 */
public interface IMerchantTypeService {

    /**
     * 查询商户门店类别
     */
    MerchantTypeVo queryById(Long merchantTypeId);

    /**
     * 查询商户门店类别列表
     */
    TableDataInfo<MerchantTypeVo> queryPageList(MerchantTypeBo bo, PageQuery pageQuery);

    /**
     * 查询商户门店类别列表
     */
    List<MerchantTypeVo> queryList(MerchantTypeBo bo);

    /**
     * 修改商户门店类别
     */
    Boolean insertByBo(MerchantTypeBo bo);

    /**
     * 修改商户门店类别
     */
    Boolean updateByBo(MerchantTypeBo bo);

    /**
     * 校验并批量删除商户门店类别信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
