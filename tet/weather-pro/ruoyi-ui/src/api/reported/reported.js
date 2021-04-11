import request from '@/utils/request'

// 查询维保上报列表
export function listReported(query) {
  return request({
    url: '/reported/reported/list',
    method: 'get',
    params: query
  })
}

// 查询维保上报详细
export function getReported(id) {
  return request({
    url: '/reported/reported/' + id,
    method: 'get'
  })
}

// 新增维保上报
export function addReported(data) {
  return request({
    url: '/reported/reported',
    method: 'post',
    data: data
  })
}

// 修改维保上报
export function updateReported(data) {
  return request({
    url: '/reported/reported',
    method: 'put',
    data: data
  })
}

// 删除维保上报
export function delReported(id) {
  return request({
    url: '/reported/reported/' + id,
    method: 'delete'
  })
}

// 导出维保上报
export function exportReported(query) {
  return request({
    url: '/reported/reported/export',
    method: 'get',
    params: query
  })
}