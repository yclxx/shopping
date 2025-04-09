import request from '@/utils/request'

// 查询商户账户明细列表
export function listCommercialTenantAccountDetail(query) {
  return request({
    url: '/zlyyh-admin/commercialTenantAccountDetail/list',
    method: 'get',
    params: query
  })
}

// 查询商户账户明细详细
export function getCommercialTenantAccountDetail(id) {
  return request({
    url: '/zlyyh-admin/commercialTenantAccountDetail/' + id,
    method: 'get'
  })
}

// 新增商户账户明细
export function addCommercialTenantAccountDetail(data) {
  return request({
    url: '/zlyyh-admin/commercialTenantAccountDetail',
    method: 'post',
    data: data
  })
}

// 修改商户账户明细
export function updateCommercialTenantAccountDetail(data) {
  return request({
    url: '/zlyyh-admin/commercialTenantAccountDetail',
    method: 'put',
    data: data
  })
}

// 删除商户账户明细
export function delCommercialTenantAccountDetail(id) {
  return request({
    url: '/zlyyh-admin/commercialTenantAccountDetail/' + id,
    method: 'delete'
  })
}
