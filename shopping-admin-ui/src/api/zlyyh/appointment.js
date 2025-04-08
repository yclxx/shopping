import request from '@/utils/request'

// 查询预约信息列表
export function listAppointment(query) {
  return request({
    url: '/zlyyh-admin/appointment/list',
    method: 'get',
    params: query
  })
}

// 查询预约信息详细
export function getAppointment(id) {
  return request({
    url: '/zlyyh-admin/appointment/' + id,
    method: 'get'
  })
}

// 新增预约信息
export function addAppointment(data) {
  return request({
    url: '/zlyyh-admin/appointment',
    method: 'post',
    data: data
  })
}

// 修改预约信息
export function updateAppointment(data) {
  return request({
    url: '/zlyyh-admin/appointment',
    method: 'put',
    data: data
  })
}

// 删除预约信息
export function delAppointment(id) {
  return request({
    url: '/zlyyh-admin/appointment/' + id,
    method: 'delete'
  })
}
