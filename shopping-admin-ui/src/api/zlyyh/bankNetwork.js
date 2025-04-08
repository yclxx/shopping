import request from '@/utils/request'

// 查询银行网点列表
export function listBankNetwork(query) {
  return request({
    url: '/zlyyh-admin/bankNetwork/list',
    method: 'get',
    params: query
  })
}

// 查询银行网点详细
export function getBankNetwork(id) {
  return request({
    url: '/zlyyh-admin/bankNetwork/' + id,
    method: 'get'
  })
}

export function getNetworkList(query) {
  return request({
    url: '/zlyyh-admin/bankNetwork/selectNetworkList',
    method: 'get',
    params: query
  })
}

// 新增银行网点
export function addBankNetwork(data) {
  return request({
    url: '/zlyyh-admin/bankNetwork',
    method: 'post',
    data: data
  })
}

// 修改银行网点
export function updateBankNetwork(data) {
  return request({
    url: '/zlyyh-admin/bankNetwork',
    method: 'put',
    data: data
  })
}

// 删除银行网点
export function delBankNetwork(id) {
  return request({
    url: '/zlyyh-admin/bankNetwork/' + id,
    method: 'delete'
  })
}
