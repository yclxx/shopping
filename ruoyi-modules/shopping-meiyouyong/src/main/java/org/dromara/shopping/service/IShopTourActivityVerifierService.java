package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.ShopTourActivityVerifierBo;
import org.dromara.shopping.domain.vo.ShopTourActivityVerifierVo;

import java.util.Collection;
import java.util.List;

/**
 * 巡检活动白名单Service接口
 *
 * @author yzg
 * @date 2024-04-17
 */
public interface IShopTourActivityVerifierService {

    /**
     * 查询巡检活动白名单
     */
    ShopTourActivityVerifierVo queryById(Long id);

    /**
     * 查询巡检活动白名单列表
     */
    TableDataInfo<ShopTourActivityVerifierVo> queryPageList(ShopTourActivityVerifierBo bo, PageQuery pageQuery);

    /**
     * 查询巡检活动白名单列表
     */
    List<ShopTourActivityVerifierVo> queryList(ShopTourActivityVerifierBo bo);

    /**
     * 修改巡检活动白名单
     */
    Boolean insertByBo(ShopTourActivityVerifierBo bo);

    /**
     * 修改巡检活动白名单
     */
    Boolean updateByBo(ShopTourActivityVerifierBo bo);

    /**
     * 校验并批量删除巡检活动白名单信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
