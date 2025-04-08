import request from '@/utils/request'

// 查询多平台栏目商品关联列表
export function listCategoryPlatformProduct(query) {
  return request({
    url: '/zlyyh-admin/categoryPlatformProduct/list',
    method: 'get',
    params: query
  })
}

// 查询多平台栏目商品关联详细
export function getCategoryPlatformProduct(id) {
  return request({
    url: '/zlyyh-admin/categoryPlatformProduct/' + id,
    method: 'get'
  })
}

// 新增多平台栏目商品关联
export function addCategoryPlatformProduct(data) {
  return request({
    url: '/zlyyh-admin/categoryPlatformProduct',
    method: 'post',
    data: data
  })
}

// 修改多平台栏目商品关联
export function updateCategoryPlatformProduct(data) {
  return request({
    url: '/zlyyh-admin/categoryPlatformProduct',
    method: 'put',
    data: data
  })
}

// 删除多平台栏目商品关联
export function delCategoryPlatformProduct(id) {
  return request({
    url: '/zlyyh-admin/categoryPlatformProduct/' + id,
    method: 'delete'
  })
}

export function addProductByCategory(data) {
  return request({
    url: '/zlyyh-admin/categoryPlatformProduct/addProductByCategory',
    method: 'post',
    data: data
  })
}

export function delProductByCategory(data) {
  return request({
    url: '/zlyyh-admin/categoryPlatformProduct/delProductByCategory',
    method: 'post',
    data: data
  })
}
