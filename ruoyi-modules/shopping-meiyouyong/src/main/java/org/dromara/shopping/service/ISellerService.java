package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.SellerBo;
import org.dromara.shopping.domain.vo.SellerVo;

import java.util.Collection;
import java.util.List;

/**
 * 店家配置Service接口
 *
 * @author yzg
 * @date 2024-10-21
 */
public interface ISellerService {

    /**
     * 查询店家配置
     */
    SellerVo queryById(Long id);

    /**
     * 查询店家配置列表
     */
    TableDataInfo<SellerVo> queryPageList(SellerBo bo, PageQuery pageQuery);

    /**
     * 查询店家配置列表
     */
    List<SellerVo> queryList(SellerBo bo);

    /**
     * 修改店家配置
     */
    Boolean insertByBo(SellerBo bo);

    /**
     * 修改店家配置
     */
    Boolean updateByBo(SellerBo bo);

    /**
     * 校验并批量删除店家配置信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    int updateProductSeller(List<Long> productIds, Long sellerId, Integer type);
}
