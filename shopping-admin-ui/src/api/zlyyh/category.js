import request from '@/utils/request'

// 查询栏目列表
export function listCategory(query) {
  return request({
    url: '/zlyyh-admin/category/list',
    method: 'get',
    params: query
  })
}

// 查询栏目下拉列表
export function selectListCategory(query) {
  return request({
    url: '/zlyyh-admin/category/selectList',
    method: 'get',
    params: query
  })
}

// 查询栏目详细
export function getCategory(categoryId) {
  return request({
    url: '/zlyyh-admin/category/' + categoryId,
    method: 'get'
  })
}

// 新增栏目
export function addCategory(data) {
  return request({
    url: '/zlyyh-admin/category',
    method: 'post',
    data: data
  })
}

// 修改栏目
export function updateCategory(data) {
  return request({
    url: '/zlyyh-admin/category',
    method: 'put',
    data: data
  })
}

// 删除栏目
export function delCategory(categoryId) {
  return request({
    url: '/zlyyh-admin/category/' + categoryId,
    method: 'delete'
  })
}
