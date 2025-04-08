import request from '@/utils/request'

// 查询银行权益发放列表
export function listBankEquityCouponUser(query) {
  return request({
    url: '/zlyyh-admin/bankEquityCouponUser/list',
    method: 'get',
    params: query
  })
}

// 查询银行权益发放详细
export function getBankEquityCouponUser(id) {
  return request({
    url: '/zlyyh-admin/bankEquityCouponUser/' + id,
    method: 'get'
  })
}

// 新增银行权益发放
export function addBankEquityCouponUser(data) {
  return request({
    url: '/zlyyh-admin/bankEquityCouponUser',
    method: 'post',
    data: data
  })
}

// 修改银行权益发放
export function updateBankEquityCouponUser(data) {
  return request({
    url: '/zlyyh-admin/bankEquityCouponUser',
    method: 'put',
    data: data
  })
}

// 删除银行权益发放
export function delBankEquityCouponUser(id) {
  return request({
    url: '/zlyyh-admin/bankEquityCouponUser/' + id,
    method: 'delete'
  })
}
