package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.MissionGroupProductBo;
import org.dromara.shopping.domain.vo.MissionGroupProductVo;

import java.util.Collection;
import java.util.List;

/**
 * 任务组可兑换商品配置Service接口
 *
 * @author yzg
 * @date 2023-05-10
 */
public interface IMissionGroupProductService {

    /**
     * 查询任务组可兑换商品配置
     */
    MissionGroupProductVo queryById(Long id);

    /**
     * 查询任务组可兑换商品配置列表
     */
    TableDataInfo<MissionGroupProductVo> queryPageList(MissionGroupProductBo bo, PageQuery pageQuery);

    /**
     * 查询任务组可兑换商品配置列表
     */
    List<MissionGroupProductVo> queryList(MissionGroupProductBo bo);

    /**
     * 修改任务组可兑换商品配置
     */
    Boolean insertByBo(MissionGroupProductBo bo);

    /**
     * 修改任务组可兑换商品配置
     */
    Boolean updateByBo(MissionGroupProductBo bo);

    /**
     * 校验并批量删除任务组可兑换商品配置信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
