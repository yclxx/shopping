import request from '@/utils/request'

// 查询城市商户列表
export function listCityMerchant(query) {
  return request({
    url: '/zlyyh-admin/cityMerchant/list',
    method: 'get',
    params: query
  })
}

// 查询城市商户详细
export function getCityMerchant(id) {
  return request({
    url: '/zlyyh-admin/cityMerchant/' + id,
    method: 'get'
  })
}

// 新增城市商户
export function addCityMerchant(data) {
  return request({
    url: '/zlyyh-admin/cityMerchant',
    method: 'post',
    data: data
  })
}

// 修改城市商户
export function updateCityMerchant(data) {
  return request({
    url: '/zlyyh-admin/cityMerchant',
    method: 'put',
    data: data
  })
}

// 删除城市商户
export function delCityMerchant(id) {
  return request({
    url: '/zlyyh-admin/cityMerchant/' + id,
    method: 'delete'
  })
}
