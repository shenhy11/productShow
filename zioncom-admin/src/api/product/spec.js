import request from '@/utils/request'

// 查询产品规格列表
export function listProductSpec(query) {
  return request({
    url: '/api/product/product/spec/list',
    method: 'get',
    params: query
  })
}

// 查询产品规格详细
export function getProductSpec(id) {
  return request({
    url: '/api/product/product/spec/' + id,
    method: 'get'
  })
}

// 新增产品规格
export function addProductSpec(data) {
  return request({
    url: '/api/product/product/spec',
    method: 'post',
    data: data
  })
}

// 修改产品规格
export function updateProductSpec(data) {
  return request({
    url: '/api/product/product/spec',
    method: 'put',
    data: data
  })
}

// 删除产品规格
export function delProductSpec(id) {
  return request({
    url: '/api/product/product/spec/' + id,
    method: 'delete'
  })
}