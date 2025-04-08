import request from '@/utils/request'

// 查询多平台类别列表
export function listCategoryPlatform(query) {
  return request({
    url: '/zlyyh-admin/categoryPlatform/list',
    method: 'get',
    params: query
  })
}

// 查询多平台类别列表
export function listCategoryPlatformAll(query) {
  return request({
    url: '/zlyyh-admin/categoryPlatform/listAll',
    method: 'get',
    params: query
  })
}

// 查询多平台类别详细
export function getCategoryPlatform(id) {
  return request({
    url: '/zlyyh-admin/categoryPlatform/' + id,
    method: 'get'
  })
}

// 新增多平台类别
export function addCategoryPlatform(data) {
  return request({
    url: '/zlyyh-admin/categoryPlatform',
    method: 'post',
    data: data
  })
}

// 修改多平台类别
export function updateCategoryPlatform(data) {
  return request({
    url: '/zlyyh-admin/categoryPlatform',
    method: 'put',
    data: data
  })
}

// 删除多平台类别
export function delCategoryPlatform(id) {
  return request({
    url: '/zlyyh-admin/categoryPlatform/' + id,
    method: 'delete'
  })
}
