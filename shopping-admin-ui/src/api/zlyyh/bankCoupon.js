import request from '@/utils/request'

// 查询银行票券关联列表
export function listBankCoupon(query) {
  return request({
    url: '/zlyyh-admin/bankCoupon/list',
    method: 'get',
    params: query
  })
}

// 查询银行票券关联详细
export function getBankCoupon(id) {
  return request({
    url: '/zlyyh-admin/bankCoupon/' + id,
    method: 'get'
  })
}

// 新增银行票券关联
export function addBankCoupon(data) {
  return request({
    url: '/zlyyh-admin/bankCoupon',
    method: 'post',
    data: data
  })
}

// 修改银行票券关联
export function updateBankCoupon(data) {
  return request({
    url: '/zlyyh-admin/bankCoupon',
    method: 'put',
    data: data
  })
}

// 删除银行票券关联
export function delBankCoupon(id) {
  return request({
    url: '/zlyyh-admin/bankCoupon/' + id,
    method: 'delete'
  })
}
