import request from '@/utils/request'

// 查询供应商产品分类列表
export function listCategorySupplier(query) {
  return request({
    url: '/zlyyh-admin/categorySupplier/list',
    method: 'get',
    params: query
  })
}

// 查询供应商产品分类详细
export function getCategorySupplier(id) {
  return request({
    url: '/zlyyh-admin/categorySupplier/' + id,
    method: 'get'
  })
}

// 新增供应商产品分类
export function addCategorySupplier(data) {
  return request({
    url: '/zlyyh-admin/categorySupplier',
    method: 'post',
    data: data
  })
}

// 修改供应商产品分类
export function updateCategorySupplier(data) {
  return request({
    url: '/zlyyh-admin/categorySupplier',
    method: 'put',
    data: data
  })
}

// 删除供应商产品分类
export function delCategorySupplier(id) {
  return request({
    url: '/zlyyh-admin/categorySupplier/' + id,
    method: 'delete'
  })
}
