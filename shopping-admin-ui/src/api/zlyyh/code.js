import request from '@/utils/request'

// 查询商品券码列表
export function listCode(query) {
  return request({
    url: '/zlyyh-admin/code/list',
    method: 'get',
    params: query
  })
}

// 查询商品券码详细
export function getCode(id) {
  return request({
    url: '/zlyyh-admin/code/' + id,
    method: 'get'
  })
}

// 新增商品券码
export function addCode(data) {
  return request({
    url: '/zlyyh-admin/code',
    method: 'post',
    data: data
  })
}

// 修改商品券码
export function updateCode(data) {
  return request({
    url: '/zlyyh-admin/code',
    method: 'put',
    data: data
  })
}

// 删除商品券码
export function delCode(id) {
  return request({
    url: '/zlyyh-admin/code/' + id,
    method: 'delete'
  })
}

// 预约订单
export function appointmentCode(data) {
  return request({
    url: '/zlyyh-admin/code/appointmentCode',
    method: 'post',
    data: data
  })
}

// 取消预约订单
export function userCancelAppointmentCode(data) {
  return request({
    url: '/zlyyh-admin/code/userCancelAppointmentCode',
    method: 'post',
    data: data
  })
}


// 核销预约订单
export function usedCode(data) {
  return request({
    url: '/zlyyh-admin/code/usedCode',
    method: 'post',
    data: data
  })
}


// 导出订单列表
export function exportCode(data) {
  return request({
    url: '/zlyyh-admin/code/exportCode',
    method: 'post',
    data: data
  })
}
