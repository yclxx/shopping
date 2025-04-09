import request from '@/utils/request'

// 查询下载记录列表
export function listDownloadLog(query) {
  return request({
    url: '/zlyyh-admin/downloadLog/list',
    method: 'get',
    params: query
  })
}

// 查询下载记录详细
export function getDownloadLog(downloadId) {
  return request({
    url: '/zlyyh-admin/downloadLog/' + downloadId,
    method: 'get'
  })
}

// 新增下载记录
export function addDownloadLog(data) {
  return request({
    url: '/zlyyh-admin/downloadLog',
    method: 'post',
    data: data
  })
}

// 修改下载记录
export function updateDownloadLog(data) {
  return request({
    url: '/zlyyh-admin/downloadLog',
    method: 'put',
    data: data
  })
}

// 删除下载记录
export function delDownloadLog(downloadId) {
  return request({
    url: '/zlyyh-admin/downloadLog/' + downloadId,
    method: 'delete'
  })
}
