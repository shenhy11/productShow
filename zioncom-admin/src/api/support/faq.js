import request from '@/utils/request'

// 查询FAQ列表
export function listFaq(query) {
  return request({
    url: '/api/support/faq/list',
    method: 'get',
    params: query
  })
}

// 查询FAQ详细
export function getFaq(id) {
  return request({
    url: '/api/support/faq/' + id,
    method: 'get'
  })
}

// 新增FAQ
export function addFaq(data) {
  return request({
    url: '/api/support/faq',
    method: 'post',
    data: data
  })
}

// 修改FAQ
export function updateFaq(data) {
  return request({
    url: '/api/support/faq',
    method: 'put',
    data: data
  })
}

// 删除FAQ
export function delFaq(id) {
  return request({
    url: '/api/support/faq/' + id,
    method: 'delete'
  })
}