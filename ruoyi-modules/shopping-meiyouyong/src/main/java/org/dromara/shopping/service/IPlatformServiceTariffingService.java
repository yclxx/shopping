package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.PlatformServiceTariffingBo;
import org.dromara.shopping.domain.vo.PlatformServiceTariffingVo;

import java.util.Collection;
import java.util.List;

/**
 * 类别服务费Service接口
 *
 * @author yzg
 * @date 2024-06-13
 */
public interface IPlatformServiceTariffingService {

    /**
     * 查询类别服务费
     */
    PlatformServiceTariffingVo queryById(Long id);

    /**
     * 查询类别服务费列表
     */
    TableDataInfo<PlatformServiceTariffingVo> queryPageList(PlatformServiceTariffingBo bo, PageQuery pageQuery);

    /**
     * 查询类别服务费列表
     */
    List<PlatformServiceTariffingVo> queryList(PlatformServiceTariffingBo bo);

    /**
     * 修改类别服务费
     */
    Boolean insertByBo(PlatformServiceTariffingBo bo);

    /**
     * 修改类别服务费
     */
    Boolean updateByBo(PlatformServiceTariffingBo bo);

    /**
     * 校验并批量删除类别服务费信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
