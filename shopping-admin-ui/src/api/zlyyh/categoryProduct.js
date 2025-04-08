import request from '@/utils/request'

// 查询栏目商品关联列表
export function listCategoryProduct(query) {
  return request({
    url: '/zlyyh-admin/categoryProduct/list',
    method: 'get',
    params: query
  })
}

// 查询栏目商品关联详细
export function getCategoryProduct(id) {
  return request({
    url: '/zlyyh-admin/categoryProduct/' + id,
    method: 'get'
  })
}

// 新增栏目商品关联
export function addCategoryProduct(data) {
  return request({
    url: '/zlyyh-admin/categoryProduct',
    method: 'post',
    data: data
  })
}

// 修改栏目商品关联
export function updateCategoryProduct(data) {
  return request({
    url: '/zlyyh-admin/categoryProduct',
    method: 'put',
    data: data
  })
}

// 删除栏目商品关联
export function delCategoryProduct(id) {
  return request({
    url: '/zlyyh-admin/categoryProduct/' + id,
    method: 'delete'
  })
}

export function addProductByCategory(data) {
  return request({
    url: '/zlyyh-admin/categoryProduct/addProductByCategory',
    method: 'post',
    data: data
  })
}

export function delProductByCategory(data) {
  return request({
    url: '/zlyyh-admin/categoryProduct/delProductByCategory',
    method: 'post',
    data: data
  })
}
