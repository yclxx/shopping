import request from '@/utils/request'

// 查询商户账户列表
export function listCommercialTenantAccount(query) {
  return request({
    url: '/zlyyh-admin/commercialTenantAccount/list',
    method: 'get',
    params: query
  })
}

// 查询商户账户详细
export function getCommercialTenantAccount(commercialTenantId) {
  return request({
    url: '/zlyyh-admin/commercialTenantAccount/' + commercialTenantId,
    method: 'get'
  })
}

// 新增商户账户
export function addCommercialTenantAccount(data) {
  return request({
    url: '/zlyyh-admin/commercialTenantAccount',
    method: 'post',
    data: data
  })
}

// 修改商户账户
export function updateCommercialTenantAccount(data) {
  return request({
    url: '/zlyyh-admin/commercialTenantAccount',
    method: 'put',
    data: data
  })
}

// 删除商户账户
export function delCommercialTenantAccount(commercialTenantId) {
  return request({
    url: '/zlyyh-admin/commercialTenantAccount/' + commercialTenantId,
    method: 'delete'
  })
}
