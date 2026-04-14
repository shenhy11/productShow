import request from '@/utils/request'

// 查询渠道商列表
export function listWorldwidePartner(query) {
  return request({
    url: '/api/brand/worldwide/partner/list',
    method: 'get',
    params: query
  })
}

// 查询渠道商详细
export function getWorldwidePartner(id) {
  return request({
    url: '/api/brand/worldwide/partner/' + id,
    method: 'get'
  })
}

// 新增渠道商
export function addWorldwidePartner(data) {
  return request({
    url: '/api/brand/worldwide/partner',
    method: 'post',
    data: data
  })
}

// 修改渠道商
export function updateWorldwidePartner(data) {
  return request({
    url: '/api/brand/worldwide/partner',
    method: 'put',
    data: data
  })
}

// 删除渠道商
export function delWorldwidePartner(id) {
  return request({
    url: '/api/brand/worldwide/partner/' + id,
    method: 'delete'
  })
}