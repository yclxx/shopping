import request from '@/utils/request'

// 查询银行权益记录列表
export function listBankEquityUserLog(query) {
  return request({
    url: '/zlyyh-admin/bankEquityUserLog/list',
    method: 'get',
    params: query
  })
}

// 查询银行权益记录详细
export function getBankEquityUserLog(id) {
  return request({
    url: '/zlyyh-admin/bankEquityUserLog/' + id,
    method: 'get'
  })
}

// 新增银行权益记录
export function addBankEquityUserLog(data) {
  return request({
    url: '/zlyyh-admin/bankEquityUserLog',
    method: 'post',
    data: data
  })
}

// 修改银行权益记录
export function updateBankEquityUserLog(data) {
  return request({
    url: '/zlyyh-admin/bankEquityUserLog',
    method: 'put',
    data: data
  })
}

// 删除银行权益记录
export function delBankEquityUserLog(id) {
  return request({
    url: '/zlyyh-admin/bankEquityUserLog/' + id,
    method: 'delete'
  })
}
