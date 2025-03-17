package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.PlatformProductRuleListBo;
import org.dromara.shopping.domain.vo.PlatformProductRuleListVo;

import java.util.Collection;
import java.util.List;

/**
 * 黑白名单Service接口
 *
 * @author yzg
 * @date 2024-06-24
 */
public interface IPlatformProductRuleListService {

    /**
     * 查询黑白名单
     */
    PlatformProductRuleListVo queryById(Long id);

    /**
     * 查询黑白名单列表
     */
    TableDataInfo<PlatformProductRuleListVo> queryPageList(PlatformProductRuleListBo bo, PageQuery pageQuery);

    /**
     * 查询黑白名单列表
     */
    List<PlatformProductRuleListVo> queryList(PlatformProductRuleListBo bo);

    /**
     * 修改黑白名单
     */
    Boolean insertByBo(PlatformProductRuleListBo bo);

    /**
     * 修改黑白名单
     */
    Boolean updateByBo(PlatformProductRuleListBo bo);

    /**
     * 校验并批量删除黑白名单信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
