import request from '@/utils/request'

// 查询联系消息列表
export function listContactMessage(query) {
  return request({
    url: '/api/brand/contact/message/list',
    method: 'get',
    params: query
  })
}

// 查询联系消息详细
export function getContactMessage(id) {
  return request({
    url: '/api/brand/contact/message/' + id,
    method: 'get'
  })
}

// 新增联系消息
export function addContactMessage(data) {
  return request({
    url: '/api/brand/contact/message',
    method: 'post',
    data: data
  })
}

// 修改联系消息
export function updateContactMessage(data) {
  return request({
    url: '/api/brand/contact/message',
    method: 'put',
    data: data
  })
}

// 删除联系消息
export function delContactMessage(id) {
  return request({
    url: '/api/brand/contact/message/' + id,
    method: 'delete'
  })
}