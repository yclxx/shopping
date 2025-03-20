package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.UnionpayProductBo;
import org.dromara.shopping.domain.vo.UnionpayProductVo;

import java.util.Collection;
import java.util.List;

/**
 * 银联活动Service接口
 *
 * @author yzg
 * @date 2023-12-08
 */
public interface IUnionpayProductService {

    /**
     * 查询银联活动
     */
    UnionpayProductVo queryById(String activityNo);

    /**
     * 查询银联活动列表
     */
    TableDataInfo<UnionpayProductVo> queryPageList(UnionpayProductBo bo, PageQuery pageQuery);

    /**
     * 查询银联活动列表
     */
    List<UnionpayProductVo> queryList(UnionpayProductBo bo);

    /**
     * 修改银联活动
     */
    Boolean insertByBo(UnionpayProductBo bo);

    /**
     * 修改银联活动
     */
    Boolean updateByBo(UnionpayProductBo bo);

    /**
     * 校验并批量删除银联活动信息
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);
}
