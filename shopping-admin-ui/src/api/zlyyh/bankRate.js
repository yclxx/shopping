import request from '@/utils/request'

// 查询银行费率列表
export function listBankRate(query) {
  return request({
    url: '/zlyyh-admin/bankRate/list',
    method: 'get',
    params: query
  })
}

export function selectBankRateList(query) {
  return request({
    url: '/zlyyh-admin/bankRate/selectBankRateList',
    method: 'get',
    params: query
  })
}

// 查询银行费率详细
export function getBankRate(bankRateId) {
  return request({
    url: '/zlyyh-admin/bankRate/' + bankRateId,
    method: 'get'
  })
}

// 新增银行费率
export function addBankRate(data) {
  return request({
    url: '/zlyyh-admin/bankRate',
    method: 'post',
    data: data
  })
}

// 修改银行费率
export function updateBankRate(data) {
  return request({
    url: '/zlyyh-admin/bankRate',
    method: 'put',
    data: data
  })
}

// 删除银行费率
export function delBankRate(bankRateId) {
  return request({
    url: '/zlyyh-admin/bankRate/' + bankRateId,
    method: 'delete'
  })
}
