import request from '@/utils/request'

// 查询商户活动报名列表
export function listCommercialActivityApply(query) {
  return request({
    url: '/zlyyh-admin/commercialActivityApply/list',
    method: 'get',
    params: query
  })
}

// 查询商户活动报名详细
export function getCommercialActivityApply(applyId) {
  return request({
    url: '/zlyyh-admin/commercialActivityApply/' + applyId,
    method: 'get'
  })
}

// 新增商户活动报名
export function addCommercialActivityApply(data) {
  return request({
    url: '/zlyyh-admin/commercialActivityApply',
    method: 'post',
    data: data
  })
}

// 修改商户活动报名
export function updateCommercialActivityApply(data) {
  return request({
    url: '/zlyyh-admin/commercialActivityApply',
    method: 'put',
    data: data
  })
}

// 删除商户活动报名
export function delCommercialActivityApply(applyId) {
  return request({
    url: '/zlyyh-admin/commercialActivityApply/' + applyId,
    method: 'delete'
  })
}
