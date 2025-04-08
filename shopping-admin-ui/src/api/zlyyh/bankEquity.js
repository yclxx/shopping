import request from '@/utils/request'

// 查询银行权益列表
export function listBankEquity(query) {
  return request({
    url: '/zlyyh-admin/bankEquity/list',
    method: 'get',
    params: query
  })
}

export function selectBankEquity(query) {
  return request({
    url: '/zlyyh-admin/bankEquity/selectList',
    method: 'get',
    params: query
  })
}

// 查询银行权益详细
export function getBankEquity(id) {
  return request({
    url: '/zlyyh-admin/bankEquity/' + id,
    method: 'get'
  })
}

// 新增银行权益
export function addBankEquity(data) {
  return request({
    url: '/zlyyh-admin/bankEquity',
    method: 'post',
    data: data
  })
}

// 修改银行权益
export function updateBankEquity(data) {
  return request({
    url: '/zlyyh-admin/bankEquity',
    method: 'put',
    data: data
  })
}

// 删除银行权益
export function delBankEquity(id) {
  return request({
    url: '/zlyyh-admin/bankEquity/' + id,
    method: 'delete'
  })
}
