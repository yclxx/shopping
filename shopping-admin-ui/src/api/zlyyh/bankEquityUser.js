import request from '@/utils/request'

// 查询银行权益用户列表
export function listBankEquityUser(query) {
  return request({
    url: '/zlyyh-admin/bankEquityUser/list',
    method: 'get',
    params: query
  })
}

// 查询银行权益用户详细
export function getBankEquityUser(id) {
  return request({
    url: '/zlyyh-admin/bankEquityUser/' + id,
    method: 'get'
  })
}

// 新增银行权益用户
export function addBankEquityUser(data) {
  return request({
    url: '/zlyyh-admin/bankEquityUser',
    method: 'post',
    data: data
  })
}

// 修改银行权益用户
export function updateBankEquityUser(data) {
  return request({
    url: '/zlyyh-admin/bankEquityUser',
    method: 'put',
    data: data
  })
}

// 删除银行权益用户
export function delBankEquityUser(id) {
  return request({
    url: '/zlyyh-admin/bankEquityUser/' + id,
    method: 'delete'
  })
}
