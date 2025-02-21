package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.MerchantBo;
import org.dromara.shopping.domain.vo.MerchantVo;

import java.util.Collection;
import java.util.List;

/**
 * 商户号Service接口
 *
 * @author yzgnet
 * @date 2023-03-21
 */
public interface IMerchantService {

    /**
     * 查询商户号
     */
    MerchantVo queryById(Long id);

    /**
     * 查询商户号列表
     */
    TableDataInfo<MerchantVo> queryPageList(MerchantBo bo, PageQuery pageQuery);

    /**
     * 查询商户号列表
     */
    List<MerchantVo> queryList(MerchantBo bo);

    /**
     * 修改商户号
     */
    Boolean insertByBo(MerchantBo bo);

    /**
     * 修改商户号
     */
    Boolean updateByBo(MerchantBo bo);

    /**
     * 校验并批量删除商户号信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
