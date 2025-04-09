import request from '@/utils/request'

// 查询商户活动报名门店列表
export function listCommercialActivityApplyShop(query) {
  return request({
    url: '/zlyyh-admin/commercialActivityApplyShop/list',
    method: 'get',
    params: query
  })
}

// 查询商户活动报名门店详细
export function getCommercialActivityApplyShop(id) {
  return request({
    url: '/zlyyh-admin/commercialActivityApplyShop/' + id,
    method: 'get'
  })
}

// 新增商户活动报名门店
export function addCommercialActivityApplyShop(data) {
  return request({
    url: '/zlyyh-admin/commercialActivityApplyShop',
    method: 'post',
    data: data
  })
}

// 修改商户活动报名门店
export function updateCommercialActivityApplyShop(data) {
  return request({
    url: '/zlyyh-admin/commercialActivityApplyShop',
    method: 'put',
    data: data
  })
}

// 删除商户活动报名门店
export function delCommercialActivityApplyShop(id) {
  return request({
    url: '/zlyyh-admin/commercialActivityApplyShop/' + id,
    method: 'delete'
  })
}
