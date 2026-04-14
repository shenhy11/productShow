import request from '@/utils/request'

// 查询文件管理列表
export function listOssFile(query) {
  return request({
    url: '/api/oss/oss/file/list',
    method: 'get',
    params: query
  })
}

// 查询文件管理详细
export function getOssFile(id) {
  return request({
    url: '/api/oss/oss/file/' + id,
    method: 'get'
  })
}

// 新增文件管理
export function addOssFile(data) {
  return request({
    url: '/api/oss/oss/file',
    method: 'post',
    data: data
  })
}

// 修改文件管理
export function updateOssFile(data) {
  return request({
    url: '/api/oss/oss/file',
    method: 'put',
    data: data
  })
}

// 删除文件管理
export function delOssFile(id) {
  return request({
    url: '/api/oss/oss/file/' + id,
    method: 'delete'
  })
}