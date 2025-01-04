import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { ProductAttrVO, ProductAttrForm, ProductAttrQuery } from '@/api/shopping/productAttr/types';

/**
 * 查询商品属性列表
 * @param query
 * @returns {*}
 */

export const listProductAttr = (query ?: ProductAttrQuery) : AxiosPromise<ProductAttrVO[]> => {
  return request({
    url: '/admin/productAttr/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询商品属性详细
 * @param id
 */
export const getProductAttr = (id : string | number) : AxiosPromise<ProductAttrVO> => {
  return request({
    url: '/admin/productAttr/' + id,
    method: 'get'
  });
};

/**
 * 新增商品属性
 * @param data
 */
export const addProductAttr = (data : ProductAttrForm) => {
  return request({
    url: '/admin/productAttr',
    method: 'post',
    data: data
  });
};

/**
 * 修改商品属性
 * @param data
 */
export const updateProductAttr = (data : ProductAttrForm) => {
  return request({
    url: '/admin/productAttr',
    method: 'put',
    data: data
  });
};

/**
 * 删除商品属性
 * @param id
 */
export const delProductAttr = (id : string | number | Array<string | number>) => {
  return request({
    url: '/admin/productAttr/' + id,
    method: 'delete'
  });
};
