import request from '@/utils/request'

// 查询行政区列表
export function listCity(query) {
  return request({
    url: '/zlyyh-admin/city/list',
    method: 'get',
    params: query
  })
}

// 查询行政区详细
export function getCity(adcode) {
  return request({
    url: '/zlyyh-admin/city/' + adcode,
    method: 'get'
  })
}

// 新增行政区
export function addCity(data) {
  return request({
    url: '/zlyyh-admin/city',
    method: 'post',
    data: data
  })
}

// 修改行政区
export function updateCity(data) {
  return request({
    url: '/zlyyh-admin/city',
    method: 'put',
    data: data
  })
}

// 删除行政区
export function delCity(adcode) {
  return request({
    url: '/zlyyh-admin/city/' + adcode,
    method: 'delete'
  })
}