import request from '@/utils/request'

// 查询分销商信息列表
export function listDistributor(query) {
  return request({
    url: '/zlyyh-admin/distributor/list',
    method: 'get',
    params: query
  })
}

// 查询平台下拉信息列表
export function selectListDistributor(query) {
  return request({
    url: '/zlyyh-admin/distributor/selectList',
    method: 'get',
    params: query
  })
}

// 查询分销商信息详细
export function getDistributor(distributorId) {
  return request({
    url: '/zlyyh-admin/distributor/' + distributorId,
    method: 'get'
  })
}

// 新增分销商信息
export function addDistributor(data) {
  return request({
    url: '/zlyyh-admin/distributor',
    method: 'post',
    data: data
  })
}

// 修改分销商信息
export function updateDistributor(data) {
  return request({
    url: '/zlyyh-admin/distributor',
    method: 'put',
    data: data
  })
}

// 删除分销商信息
export function delDistributor(distributorId) {
  return request({
    url: '/zlyyh-admin/distributor/' + distributorId,
    method: 'delete'
  })
}