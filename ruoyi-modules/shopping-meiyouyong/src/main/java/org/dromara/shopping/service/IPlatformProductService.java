package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.PlatformProductBo;
import org.dromara.shopping.domain.vo.PlatformProductVo;

import java.util.Collection;
import java.util.List;

/**
 * 平台商品配置Service接口
 *
 * @author yzg
 * @date 2024-05-23
 */
public interface IPlatformProductService {

    /**
     * 查询平台商品配置
     */
    PlatformProductVo queryById(Long platformProductId);

    /**
     * 查询平台商品配置列表
     */
    TableDataInfo<PlatformProductVo> queryPageList(PlatformProductBo bo, PageQuery pageQuery);

    /**
     * 查询平台商品配置列表
     */
    List<PlatformProductVo> queryList(PlatformProductBo bo);

    /**
     * 修改平台商品配置
     */
    Boolean insertByBo(PlatformProductBo bo);

    /**
     * 修改平台商品配置
     */
    Boolean updateByBo(PlatformProductBo bo);

    /**
     * 校验并批量删除平台商品配置信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 同步平台产品
     */
    void synchronizationPlatformProduct();

    /**
     * 设置平台产品
     */
    void setProduct(List<Long> platformProductIds);

    /**
     * 取消设置平台产品
     */
    void unsetProduct(List<Long> platformProductIds);

    /**
     * 商品调价
     * @param bo
     */
    void productAdjustPrice(PlatformProductBo bo);
}
