package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.BannerBo;
import org.dromara.shopping.domain.vo.BannerVo;

import java.util.Collection;
import java.util.List;

/**
 * 广告管理Service接口
 *
 * @author ruoyi
 * @date 2023-03-21
 */
public interface IBannerService {

    /**
     * 查询广告管理
     */
    BannerVo queryById(Long bannerId);

    /**
     * 查询广告管理列表
     */
    TableDataInfo<BannerVo> queryPageList(BannerBo bo, PageQuery pageQuery);

    /**
     * 查询广告管理列表
     */
    List<BannerVo> queryList(BannerBo bo);

    /**
     * 修改广告管理
     */
    Boolean insertByBo(BannerBo bo);

    /**
     * 修改广告管理
     */
    Boolean updateByBo(BannerBo bo);

    /**
     * 校验并批量删除广告管理信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
