package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.PlatformSaleProfitBo;
import org.dromara.shopping.domain.vo.PlatformSaleProfitVo;

import java.util.Collection;
import java.util.List;

/**
 * 平台销售利润Service接口
 *
 * @author yzg
 * @date 2024-09-11
 */
public interface IPlatformSaleProfitService {

    /**
     * 查询平台销售利润
     */
    PlatformSaleProfitVo queryById(Long id);

    /**
     * 查询平台销售利润列表
     */
    TableDataInfo<PlatformSaleProfitVo> queryPageList(PlatformSaleProfitBo bo, PageQuery pageQuery);

    /**
     * 查询平台销售利润列表
     */
    List<PlatformSaleProfitVo> queryList(PlatformSaleProfitBo bo);

    /**
     * 修改平台销售利润
     */
    Boolean insertByBo(PlatformSaleProfitBo bo);

    /**
     * 修改平台销售利润
     */
    Boolean updateByBo(PlatformSaleProfitBo bo);

    /**
     * 校验并批量删除平台销售利润信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
