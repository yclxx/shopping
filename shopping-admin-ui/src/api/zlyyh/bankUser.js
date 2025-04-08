import request from '@/utils/request'

// 查询银行用户关联列表
export function listBankUser(query) {
  return request({
    url: '/zlyyh-admin/bankUser/list',
    method: 'get',
    params: query
  })
}

// 查询银行用户关联详细
export function getBankUser(id) {
  return request({
    url: '/zlyyh-admin/bankUser/' + id,
    method: 'get'
  })
}

// 新增银行用户关联
export function addBankUser(data) {
  return request({
    url: '/zlyyh-admin/bankUser',
    method: 'post',
    data: data
  })
}

// 修改银行用户关联
export function updateBankUser(data) {
  return request({
    url: '/zlyyh-admin/bankUser',
    method: 'put',
    data: data
  })
}

// 删除银行用户关联
export function delBankUser(id) {
  return request({
    url: '/zlyyh-admin/bankUser/' + id,
    method: 'delete'
  })
}
