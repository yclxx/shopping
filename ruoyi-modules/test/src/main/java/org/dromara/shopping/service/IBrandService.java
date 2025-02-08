package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.BrandBo;
import org.dromara.shopping.domain.vo.BrandVo;

import java.util.Collection;
import java.util.List;

/**
 * 品牌管理Service接口
 *
 * @author yzg
 * @date 2023-12-29
 */
public interface IBrandService {

    /**
     * 查询品牌管理
     */
    BrandVo queryById(Long brandId);

    /**
     * 查询品牌管理列表
     */
    TableDataInfo<BrandVo> queryPageList(BrandBo bo, PageQuery pageQuery);

    /**
     * 查询品牌管理列表
     */
    List<BrandVo> queryList(BrandBo bo);

    /**
     * 修改品牌管理
     */
    Boolean insertByBo(BrandBo bo);

    /**
     * 修改品牌管理
     */
    Boolean updateByBo(BrandBo bo);

    /**
     * 校验并批量删除品牌管理信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
