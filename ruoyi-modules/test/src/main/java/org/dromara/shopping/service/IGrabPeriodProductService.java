package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.GrabPeriodProductBo;
import org.dromara.shopping.domain.vo.GrabPeriodProductVo;

import java.util.Collection;
import java.util.List;

/**
 * 秒杀商品配置Service接口
 *
 * @author yzgnet
 * @date 2023-03-21
 */
public interface IGrabPeriodProductService {

    /**
     * 查询秒杀商品配置
     */
    GrabPeriodProductVo queryById(Long id);

    /**
     * 查询秒杀商品配置列表
     */
    TableDataInfo<GrabPeriodProductVo> queryPageList(GrabPeriodProductBo bo, PageQuery pageQuery);

    /**
     * 查询秒杀商品配置列表
     */
    List<GrabPeriodProductVo> queryList(GrabPeriodProductBo bo);

    /**
     * 修改秒杀商品配置
     */
    Boolean insertByBo(GrabPeriodProductBo bo);

    /**
     * 修改秒杀商品配置
     */
    Boolean updateByBo(GrabPeriodProductBo bo);

    /**
     * 校验并批量删除秒杀商品配置信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
