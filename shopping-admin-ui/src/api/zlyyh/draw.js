import request from '@/utils/request'

// 查询奖品管理列表
export function listDraw(query) {
  return request({
    url: '/zlyyh-admin/draw/list',
    method: 'get',
    params: query
  })
}

// 查询奖品管理详细
export function getDraw(drawId) {
  return request({
    url: '/zlyyh-admin/draw/' + drawId,
    method: 'get'
  })
}

// 新增奖品管理
export function addDraw(data) {
  return request({
    url: '/zlyyh-admin/draw',
    method: 'post',
    data: data
  })
}

// 修改奖品管理
export function updateDraw(data) {
  return request({
    url: '/zlyyh-admin/draw',
    method: 'put',
    data: data
  })
}

// 删除奖品管理
export function delDraw(drawId) {
  return request({
    url: '/zlyyh-admin/draw/' + drawId,
    method: 'delete'
  })
}