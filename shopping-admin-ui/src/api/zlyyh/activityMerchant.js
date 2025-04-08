import request from '@/utils/request'

// 查询活动商户号列表
export function listActivityMerchant(query) {
  return request({
    url: '/zlyyh-admin/activityMerchant/list',
    method: 'get',
    params: query
  })
}

// 查询活动商户号详细
export function getActivityMerchant(id) {
  return request({
    url: '/zlyyh-admin/activityMerchant/' + id,
    method: 'get'
  })
}

// 新增活动商户号
export function addActivityMerchant(data) {
  return request({
    url: '/zlyyh-admin/activityMerchant',
    method: 'post',
    data: data
  })
}

// 修改活动商户号
export function updateActivityMerchant(data) {
  return request({
    url: '/zlyyh-admin/activityMerchant',
    method: 'put',
    data: data
  })
}

// 删除活动商户号
export function delActivityMerchant(id) {
  return request({
    url: '/zlyyh-admin/activityMerchant/' + id,
    method: 'delete'
  })
}
