import request from '@/utils/request'

// 查询商户列表
export function listCommercialTenant(query) {
  return request({
    url: '/zlyyh-admin/commercialTenant/list',
    method: 'get',
    params: query
  })
}

export function categoryCommercialList(query) {
  return request({
    url: '/zlyyh-admin/commercialTenant/categoryCommercialList',
    method: 'get',
    params: query
  })
}


// 查询商户详细
export function getCommercialTenant(commercialTenantId) {
  return request({
    url: '/zlyyh-admin/commercialTenant/' + commercialTenantId,
    method: 'get'
  })
}

// 新增商户
export function addCommercialTenant(data) {
  return request({
    url: '/zlyyh-admin/commercialTenant',
    method: 'post',
    data: data
  })
}

// 修改商户
export function updateCommercialTenant(data) {
  return request({
    url: '/zlyyh-admin/commercialTenant',
    method: 'put',
    data: data
  })
}

// 删除商户
export function delCommercialTenant(commercialTenantId) {
  return request({
    url: '/zlyyh-admin/commercialTenant/' + commercialTenantId,
    method: 'delete'
  })
}

//查询商户下拉列表
export function selectListMerchant(query) {
  return request({
    url: '/zlyyh-admin/commercialTenant/selectListMerchant',
    method: 'get',
    params: query
  })
}
