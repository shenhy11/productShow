import request from '@/utils/request'

// 查询Web模拟器列表
export function listWebEmulator(query) {
  return request({
    url: '/api/support/web/emulator/list',
    method: 'get',
    params: query
  })
}

// 查询Web模拟器详细
export function getWebEmulator(id) {
  return request({
    url: '/api/support/web/emulator/' + id,
    method: 'get'
  })
}

// 新增Web模拟器
export function addWebEmulator(data) {
  return request({
    url: '/api/support/web/emulator',
    method: 'post',
    data: data
  })
}

// 修改Web模拟器
export function updateWebEmulator(data) {
  return request({
    url: '/api/support/web/emulator',
    method: 'put',
    data: data
  })
}

// 删除Web模拟器
export function delWebEmulator(id) {
  return request({
    url: '/api/support/web/emulator/' + id,
    method: 'delete'
  })
}