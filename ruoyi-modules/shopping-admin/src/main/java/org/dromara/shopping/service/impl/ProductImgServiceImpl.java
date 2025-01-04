package org.dromara.shopping.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.shopping.base.domain.bo.ProductImgBo;
import org.dromara.shopping.base.domain.vo.ProductImgVo;
import org.dromara.shopping.base.domain.ProductImg;
import org.dromara.shopping.base.mapper.ProductImgMapper;
import org.dromara.shopping.service.IProductImgService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 商品图片Service业务层处理
 *
 * @author Xie Xi
 * @date 2025-01-04
 */
@RequiredArgsConstructor
@Service
public class ProductImgServiceImpl implements IProductImgService {

    private final ProductImgMapper baseMapper;

    /**
     * 查询商品图片
     *
     * @param id 主键
     * @return 商品图片
     */
    @Override
    public ProductImgVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询商品图片列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 商品图片分页列表
     */
    @Override
    public TableDataInfo<ProductImgVo> queryPageList(ProductImgBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ProductImg> lqw = buildQueryWrapper(bo);
        Page<ProductImgVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的商品图片列表
     *
     * @param bo 查询条件
     * @return 商品图片列表
     */
    @Override
    public List<ProductImgVo> queryList(ProductImgBo bo) {
        LambdaQueryWrapper<ProductImg> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ProductImg> buildQueryWrapper(ProductImgBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ProductImg> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getProductId() != null, ProductImg::getProductId, bo.getProductId());
        lqw.eq(StringUtils.isNotBlank(bo.getImgAttribution()), ProductImg::getImgAttribution, bo.getImgAttribution());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), ProductImg::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增商品图片
     *
     * @param bo 商品图片
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ProductImgBo bo) {
        ProductImg add = MapstructUtils.convert(bo, ProductImg.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改商品图片
     *
     * @param bo 商品图片
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ProductImgBo bo) {
        ProductImg update = MapstructUtils.convert(bo, ProductImg.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ProductImg entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除商品图片信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }
}
