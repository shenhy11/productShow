import request from '@/utils/request'

// 查询支持请求列表
export function listSupportRequest(query) {
  return request({
    url: '/api/support/support/request/list',
    method: 'get',
    params: query
  })
}

// 查询支持请求详细
export function getSupportRequest(id) {
  return request({
    url: '/api/support/support/request/' + id,
    method: 'get'
  })
}

// 新增支持请求
export function addSupportRequest(data) {
  return request({
    url: '/api/support/support/request',
    method: 'post',
    data: data
  })
}

// 修改支持请求
export function updateSupportRequest(data) {
  return request({
    url: '/api/support/support/request',
    method: 'put',
    data: data
  })
}

// 删除支持请求
export function delSupportRequest(id) {
  return request({
    url: '/api/support/support/request/' + id,
    method: 'delete'
  })
}