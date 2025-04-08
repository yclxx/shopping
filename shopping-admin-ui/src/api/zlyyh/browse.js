import request from '@/utils/request'

// 查询浏览任务列表
export function listBrowse(query) {
  return request({
    url: '/zlyyh-admin/browse/list',
    method: 'get',
    params: query
  })
}

// 查询浏览任务详细
export function getBrowse(browseId) {
  return request({
    url: '/zlyyh-admin/browse/' + browseId,
    method: 'get'
  })
}

// 新增浏览任务
export function addBrowse(data) {
  return request({
    url: '/zlyyh-admin/browse',
    method: 'post',
    data: data
  })
}

// 修改浏览任务
export function updateBrowse(data) {
  return request({
    url: '/zlyyh-admin/browse',
    method: 'put',
    data: data
  })
}

// 删除浏览任务
export function delBrowse(browseId) {
  return request({
    url: '/zlyyh-admin/browse/' + browseId,
    method: 'delete'
  })
}