import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { ProductImgVO, ProductImgForm, ProductImgQuery } from '@/api/shopping/productImg/types';

/**
 * 查询商品图片列表
 * @param query
 * @returns {*}
 */

export const listProductImg = (query ?: ProductImgQuery) : AxiosPromise<ProductImgVO[]> => {
  return request({
    url: '/admin/productImg/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询商品图片详细
 * @param id
 */
export const getProductImg = (id : string | number) : AxiosPromise<ProductImgVO> => {
  return request({
    url: '/admin/productImg/' + id,
    method: 'get'
  });
};

/**
 * 新增商品图片
 * @param data
 */
export const addProductImg = (data : ProductImgForm) => {
  return request({
    url: '/admin/productImg',
    method: 'post',
    data: data
  });
};

/**
 * 修改商品图片
 * @param data
 */
export const updateProductImg = (data : ProductImgForm) => {
  return request({
    url: '/admin/productImg',
    method: 'put',
    data: data
  });
};

/**
 * 删除商品图片
 * @param id
 */
export const delProductImg = (id : string | number | Array<string | number>) => {
  return request({
    url: '/admin/productImg/' + id,
    method: 'delete'
  });
};
