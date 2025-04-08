import request from '@/utils/request'

// 查询商圈列表
export function listBusinessDistrict(query) {
  return request({
    url: '/zlyyh-admin/businessDistrict/list',
    method: 'get',
    params: query
  })
}

// 查询商圈详细
export function getBusinessDistrict(businessDistrictId) {
  return request({
    url: '/zlyyh-admin/businessDistrict/' + businessDistrictId,
    method: 'get'
  })
}

// 新增商圈
export function addBusinessDistrict(data) {
  return request({
    url: '/zlyyh-admin/businessDistrict',
    method: 'post',
    data: data
  })
}

// 修改商圈
export function updateBusinessDistrict(data) {
  return request({
    url: '/zlyyh-admin/businessDistrict',
    method: 'put',
    data: data
  })
}

// 删除商圈
export function delBusinessDistrict(businessDistrictId) {
  return request({
    url: '/zlyyh-admin/businessDistrict/' + businessDistrictId,
    method: 'delete'
  })
}

//查询商户下拉列表
export function selectListBusinessDistrict(query) {
  return request({
    url: '/zlyyh-admin/businessDistrict/selectListBusinessDistrict',
    method: 'get',
    params: query
  })

}
