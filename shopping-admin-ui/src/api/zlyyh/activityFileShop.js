import request from '@/utils/request'

// 查询活动商户列表
export function listActivityFileShop(query) {
  return request({
    url: '/zlyyh-admin/activityFileShop/list',
    method: 'get',
    params: query
  })
}

// 查询活动商户详细
export function getActivityFileShop(activityShopId) {
  return request({
    url: '/zlyyh-admin/activityFileShop/' + activityShopId,
    method: 'get'
  })
}

// 新增活动商户
export function addActivityFileShop(data) {
  return request({
    url: '/zlyyh-admin/activityFileShop',
    method: 'post',
    data: data
  })
}

// 修改活动商户
export function updateActivityFileShop(data) {
  return request({
    url: '/zlyyh-admin/activityFileShop',
    method: 'put',
    data: data
  })
}

// 删除活动商户
export function delActivityFileShop(activityShopId) {
  return request({
    url: '/zlyyh-admin/activityFileShop/' + activityShopId,
    method: 'delete'
  })
}