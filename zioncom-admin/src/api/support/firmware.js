import request from '@/utils/request'

// 查询固件列表
export function listFirmware(query) {
  return request({
    url: '/api/support/firmware/list',
    method: 'get',
    params: query
  })
}

// 查询固件详细
export function getFirmware(id) {
  return request({
    url: '/api/support/firmware/' + id,
    method: 'get'
  })
}

// 新增固件
export function addFirmware(data) {
  return request({
    url: '/api/support/firmware',
    method: 'post',
    data: data
  })
}

// 修改固件
export function updateFirmware(data) {
  return request({
    url: '/api/support/firmware',
    method: 'put',
    data: data
  })
}

// 删除固件
export function delFirmware(id) {
  return request({
    url: '/api/support/firmware/' + id,
    method: 'delete'
  })
}