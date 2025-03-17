package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.FileImportLogBo;
import org.dromara.shopping.domain.bo.ProductArrivalListBo;
import org.dromara.shopping.domain.vo.ProductArrivalListVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * 到货清单Service接口
 *
 * @author yzg
 * @date 2024-05-08
 */
public interface IProductArrivalListService {

    /**
     * 查询到货清单
     */
    ProductArrivalListVo queryById(Long arrivalListId);

    /**
     * 查询到货清单列表
     */
    TableDataInfo<ProductArrivalListVo> queryPageList(ProductArrivalListBo bo, PageQuery pageQuery);

    /**
     * 查询到货清单列表
     */
    List<ProductArrivalListVo> queryList(ProductArrivalListBo bo);

    /**
     * 修改到货清单
     */
    Boolean insertByBo(ProductArrivalListBo bo);

    /**
     * 修改到货清单
     */
    Boolean updateByBo(ProductArrivalListBo bo);

    /**
     * 校验并批量删除到货清单信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 导入清单
     */
    void importMerchant(MultipartFile file, Long platformKey, FileImportLogBo logBo) throws IOException;
}
