import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { ProductAttrValueVO, ProductAttrValueForm, ProductAttrValueQuery } from '@/api/shopping/productAttrValue/types';

/**
 * 查询商品属性值列表
 * @param query
 * @returns {*}
 */

export const listProductAttrValue = (query ?: ProductAttrValueQuery) : AxiosPromise<ProductAttrValueVO[]> => {
  return request({
    url: '/admin/productAttrValue/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询商品属性值详细
 * @param id
 */
export const getProductAttrValue = (id : string | number) : AxiosPromise<ProductAttrValueVO> => {
  return request({
    url: '/admin/productAttrValue/' + id,
    method: 'get'
  });
};

/**
 * 新增商品属性值
 * @param data
 */
export const addProductAttrValue = (data : ProductAttrValueForm) => {
  return request({
    url: '/admin/productAttrValue',
    method: 'post',
    data: data
  });
};

/**
 * 修改商品属性值
 * @param data
 */
export const updateProductAttrValue = (data : ProductAttrValueForm) => {
  return request({
    url: '/admin/productAttrValue',
    method: 'put',
    data: data
  });
};

/**
 * 删除商品属性值
 * @param id
 */
export const delProductAttrValue = (id : string | number | Array<string | number>) => {
  return request({
    url: '/admin/productAttrValue/' + id,
    method: 'delete'
  });
};
