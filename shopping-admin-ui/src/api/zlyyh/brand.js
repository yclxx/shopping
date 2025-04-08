import request from '@/utils/request'

// 查询品牌管理列表
export function listBrand(query) {
  return request({
    url: '/zlyyh-admin/brand/list',
    method: 'get',
    params: query
  })
}

// 查询栏目下拉列表
export function selectListBrand(query) {
  return request({
    url: '/zlyyh-admin/brand/selectList',
    method: 'get',
    params: query
  })
}

// 查询品牌管理详细
export function getBrand(brandId) {
  return request({
    url: '/zlyyh-admin/brand/' + brandId,
    method: 'get'
  })
}

// 新增品牌管理
export function addBrand(data) {
  return request({
    url: '/zlyyh-admin/brand',
    method: 'post',
    data: data
  })
}

// 修改品牌管理
export function updateBrand(data) {
  return request({
    url: '/zlyyh-admin/brand',
    method: 'put',
    data: data
  })
}

// 删除品牌管理
export function delBrand(brandId) {
  return request({
    url: '/zlyyh-admin/brand/' + brandId,
    method: 'delete'
  })
}
