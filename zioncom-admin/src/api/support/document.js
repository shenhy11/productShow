import request from '@/utils/request'

// 查询文档列表
export function listDocument(query) {
  return request({
    url: '/api/support/document/list',
    method: 'get',
    params: query
  })
}

// 查询文档详细
export function getDocument(id) {
  return request({
    url: '/api/support/document/' + id,
    method: 'get'
  })
}

// 新增文档
export function addDocument(data) {
  return request({
    url: '/api/support/document',
    method: 'post',
    data: data
  })
}

// 修改文档
export function updateDocument(data) {
  return request({
    url: '/api/support/document',
    method: 'put',
    data: data
  })
}

// 删除文档
export function delDocument(id) {
  return request({
    url: '/api/support/document/' + id,
    method: 'delete'
  })
}