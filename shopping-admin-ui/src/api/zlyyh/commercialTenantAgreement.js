import request from '@/utils/request'

// 查询商户合同列表
export function listCommercialTenantAgreement(query) {
  return request({
    url: '/zlyyh-admin/commercialTenantAgreement/list',
    method: 'get',
    params: query
  })
}

// 查询商户合同详细
export function getCommercialTenantAgreement(agreementId) {
  return request({
    url: '/zlyyh-admin/commercialTenantAgreement/' + agreementId,
    method: 'get'
  })
}

// 新增商户合同
export function addCommercialTenantAgreement(data) {
  return request({
    url: '/zlyyh-admin/commercialTenantAgreement',
    method: 'post',
    data: data
  })
}

// 修改商户合同
export function updateCommercialTenantAgreement(data) {
  return request({
    url: '/zlyyh-admin/commercialTenantAgreement',
    method: 'put',
    data: data
  })
}

// 删除商户合同
export function delCommercialTenantAgreement(agreementId) {
  return request({
    url: '/zlyyh-admin/commercialTenantAgreement/' + agreementId,
    method: 'delete'
  })
}
