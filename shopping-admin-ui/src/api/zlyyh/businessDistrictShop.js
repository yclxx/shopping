import request from '@/utils/request'

export function addShopByProduct(data) {
  return request({
    url: '/zlyyh-admin/businessDistrictShop/addShopByProduct',
    method: 'post',
    data: data
  })
}

export function delByShopProduct(data) {
  return request({
    url: '/zlyyh-admin/businessDistrictShop/delByShopProduct',
    method: 'post',
    data: data
  })
}
