package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.PlatformProductRuleBo;
import org.dromara.shopping.domain.vo.PlatformProductRuleVo;

import java.util.Collection;
import java.util.List;

/**
 * 商品规则Service接口
 *
 * @author yzg
 * @date 2024-06-24
 */
public interface IPlatformProductRuleService {

    /**
     * 查询商品规则
     */
    PlatformProductRuleVo queryById(Long platformKey);

    /**
     * 查询商品规则列表
     */
    TableDataInfo<PlatformProductRuleVo> queryPageList(PlatformProductRuleBo bo, PageQuery pageQuery);

    /**
     * 查询商品规则列表
     */
    List<PlatformProductRuleVo> queryList(PlatformProductRuleBo bo);

    /**
     * 修改商品规则
     */
    Boolean insertByBo(PlatformProductRuleBo bo);

    /**
     * 修改商品规则
     */
    Boolean updateByBo(PlatformProductRuleBo bo);

    /**
     * 校验并批量删除商品规则信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
