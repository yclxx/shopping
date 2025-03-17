package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.PlatformPromotionCodeBo;
import org.dromara.shopping.domain.vo.PlatformPromotionCodeVo;

import java.util.Collection;
import java.util.List;

/**
 * 平台用户推广码Service接口
 *
 * @author yzg
 * @date 2024-05-27
 */
public interface IPlatformPromotionCodeService {

    /**
     * 查询平台用户推广码
     */
    PlatformPromotionCodeVo queryById(Long id);

    /**
     * 查询平台用户推广码列表
     */
    TableDataInfo<PlatformPromotionCodeVo> queryPageList(PlatformPromotionCodeBo bo, PageQuery pageQuery);

    /**
     * 查询平台用户推广码列表
     */
    List<PlatformPromotionCodeVo> queryList(PlatformPromotionCodeBo bo);

    /**
     * 修改平台用户推广码
     */
    Boolean insertByBo(PlatformPromotionCodeBo bo);

    /**
     * 修改平台用户推广码
     */
    Boolean updateByBo(PlatformPromotionCodeBo bo);

    /**
     * 校验并批量删除平台用户推广码信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
