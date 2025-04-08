import request from '@/utils/request'

// 查询广告管理列表
export function listBanner(query) {
  return request({
    url: '/zlyyh-admin/banner/list',
    method: 'get',
    params: query
  })
}

// 查询广告管理详细
export function getBanner(bannerId) {
  return request({
    url: '/zlyyh-admin/banner/' + bannerId,
    method: 'get'
  })
}

// 新增广告管理
export function addBanner(data) {
  return request({
    url: '/zlyyh-admin/banner',
    method: 'post',
    data: data
  })
}

// 修改广告管理
export function updateBanner(data) {
  return request({
    url: '/zlyyh-admin/banner',
    method: 'put',
    data: data
  })
}

// 删除广告管理
export function delBanner(bannerId) {
  return request({
    url: '/zlyyh-admin/banner/' + bannerId,
    method: 'delete'
  })
}
