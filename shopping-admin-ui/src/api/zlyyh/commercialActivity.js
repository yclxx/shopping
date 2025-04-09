import request from '@/utils/request'

// 查询商户活动列表
export function listCommercialActivity(query) {
  return request({
    url: '/zlyyh-admin/commercialActivity/list',
    method: 'get',
    params: query
  })
}

// 查询商户活动详细
export function getCommercialActivity(activityId) {
  return request({
    url: '/zlyyh-admin/commercialActivity/' + activityId,
    method: 'get'
  })
}

// 新增商户活动
export function addCommercialActivity(data) {
  return request({
    url: '/zlyyh-admin/commercialActivity',
    method: 'post',
    data: data
  })
}

// 修改商户活动
export function updateCommercialActivity(data) {
  return request({
    url: '/zlyyh-admin/commercialActivity',
    method: 'put',
    data: data
  })
}

// 删除商户活动
export function delCommercialActivity(activityId) {
  return request({
    url: '/zlyyh-admin/commercialActivity/' + activityId,
    method: 'delete'
  })
}
