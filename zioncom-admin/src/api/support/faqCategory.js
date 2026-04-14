import request from '@/utils/request'

// 查询FAQ分类列表
export function listFaqCategory(query) {
  return request({
    url: '/api/support/faq/category/list',
    method: 'get',
    params: query
  })
}

// 查询FAQ分类详细
export function getFaqCategory(id) {
  return request({
    url: '/api/support/faq/category/' + id,
    method: 'get'
  })
}

// 新增FAQ分类
export function addFaqCategory(data) {
  return request({
    url: '/api/support/faq/category',
    method: 'post',
    data: data
  })
}

// 修改FAQ分类
export function updateFaqCategory(data) {
  return request({
    url: '/api/support/faq/category',
    method: 'put',
    data: data
  })
}

// 删除FAQ分类
export function delFaqCategory(id) {
  return request({
    url: '/api/support/faq/category/' + id,
    method: 'delete'
  })
}