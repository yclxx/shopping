import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { ProductVO, ProductForm, ProductQuery } from '@/api/shopping/product/types';

/**
 * 查询商品信息列表
 * @param query
 * @returns {*}
 */

export const listProduct = (query ?: ProductQuery) : AxiosPromise<ProductVO[]> => {
  return request({
    url: '/admin/product/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询商品信息详细
 * @param productId
 */
export const getProduct = (productId : string | number) : AxiosPromise<ProductVO> => {
  return request({
    url: '/admin/product/' + productId,
    method: 'get'
  });
};

/**
 * 新增商品信息
 * @param data
 */
export const addProduct = (data : ProductForm) => {
  return request({
    url: '/admin/product',
    method: 'post',
    data: data
  });
};

/**
 * 修改商品信息
 * @param data
 */
export const updateProduct = (data : ProductForm) => {
  return request({
    url: '/admin/product',
    method: 'put',
    data: data
  });
};

/**
 * 删除商品信息
 * @param productId
 */
export const delProduct = (productId : string | number | Array<string | number>) => {
  return request({
    url: '/admin/product/' + productId,
    method: 'delete'
  });
};
