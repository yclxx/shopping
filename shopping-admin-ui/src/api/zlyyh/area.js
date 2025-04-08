import request from '@/utils/request'

// 查询行政区列表
export function listArea(query) {
  return request({
    url: '/zlyyh-admin/area/list',
    method: 'get',
    params: query
  })
}

// 查询下拉行政区列表
export function listSelectArea(query) {
  return request({
    url: '/zlyyh-admin/area/listSelect',
    method: 'get',
    params: query
  })
}

// 查询行政区详细
export function getArea(id) {
  return request({
    url: '/zlyyh-admin/area/' + id,
    method: 'get'
  })
}

// 新增行政区
export function addArea(data) {
  return request({
    url: '/zlyyh-admin/area',
    method: 'post',
    data: data
  })
}

// 修改行政区
export function updateArea(data) {
  return request({
    url: '/zlyyh-admin/area',
    method: 'put',
    data: data
  })
}

// 删除行政区
export function delArea(adcode) {
  return request({
    url: '/zlyyh-admin/area/' + adcode,
    method: 'delete'
  })
}

//查询城市下拉树结构
export function treeselect() {
  return request({
    url: '/zlyyh-admin/area/treeselect',
    method: 'get'
  })
}

// 根据角色ID查询菜单下拉树结构
export function platformCityTreeselect(platformKey) {
  return request({
    url: '/zlyyh-admin/area/platformCityTreeselect/' + platformKey,
    method: 'get'
  })
}

// 根据bannerID查询城市下拉数结构
export function bannerShowCityTreeSelect(bannerId) {
  return request({
    url: '/zlyyh-admin/area/bannerShowCityTreeSelect/' + bannerId,
    method: 'get'
  })
}

// 根据bannerID查询城市下拉数结构
export function hotNewsShowCityTreeSelect(bannerId) {
  return request({
    url: '/zlyyh-admin/area/hotNewsShowCityTreeSelect/' + bannerId,
    method: 'get'
  })
}

// 根据bannerID查询城市下拉数结构
export function productShowCityTreeSelect(productId) {
  return request({
    url: '/zlyyh-admin/area/productShowCityTreeSelect/' + productId,
    method: 'get'
  })
}

// 根据bannerID查询城市下拉数结构
export function searchShowCityTreeSelect(searchId) {
  return request({
    url: '/zlyyh-admin/area/searchShowCityTreeSelect/' + searchId,
    method: 'get'
  })
}
export function browseShowCityTreeSelect(browseId) {
  return request({
    url: '/zlyyh-admin/area/browseShowCityTreeSelect/' + browseId,
    method: 'get'
  })
}

//查询page列表
export function selectCityList(query) {
  return request({
    url: '/zlyyh-admin/area/selectCityList',
    method: 'get',
    params: query
  })
}