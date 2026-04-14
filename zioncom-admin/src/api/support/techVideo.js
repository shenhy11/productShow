import request from '@/utils/request'

// 查询技术视频列表
export function listTechVideo(query) {
  return request({
    url: '/api/support/tech/video/list',
    method: 'get',
    params: query
  })
}

// 查询技术视频详细
export function getTechVideo(id) {
  return request({
    url: '/api/support/tech/video/' + id,
    method: 'get'
  })
}

// 新增技术视频
export function addTechVideo(data) {
  return request({
    url: '/api/support/tech/video',
    method: 'post',
    data: data
  })
}

// 修改技术视频
export function updateTechVideo(data) {
  return request({
    url: '/api/support/tech/video',
    method: 'put',
    data: data
  })
}

// 删除技术视频
export function delTechVideo(id) {
  return request({
    url: '/api/support/tech/video/' + id,
    method: 'delete'
  })
}