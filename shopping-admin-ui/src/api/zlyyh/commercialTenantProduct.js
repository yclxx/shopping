import request from '@/utils/request'

// 查询商户商品配置列表
export function listCommercialTenantProduct(query) {
  return request({
    url: '/zlyyh-admin/commercialTenantProduct/list',
    method: 'get',
    params: query
  })
}

// 查询商户商品配置详细
export function getCommercialTenantProduct(id) {
  return request({
    url: '/zlyyh-admin/commercialTenantProduct/' + id,
    method: 'get'
  })
}

// 新增商户商品配置
export function addCommercialTenantProduct(data) {
  return request({
    url: '/zlyyh-admin/commercialTenantProduct',
    method: 'post',
    data: data
  })
}

// 修改商户商品配置
export function updateCommercialTenantProduct(data) {
  return request({
    url: '/zlyyh-admin/commercialTenantProduct',
    method: 'put',
    data: data
  })
}

// 删除商户商品配置
export function delCommercialTenantProduct(id) {
  return request({
    url: '/zlyyh-admin/commercialTenantProduct/' + id,
    method: 'delete'
  })
}
