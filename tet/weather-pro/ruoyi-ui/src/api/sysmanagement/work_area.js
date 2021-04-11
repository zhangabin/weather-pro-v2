import request from '@/utils/request'

// 查询工区信息列表
export function listWork_area(query) {
  return request({
    url: '/weather/work_area/list',
    method: 'get',
    params: query
  })
}

// 查询工区信息详细
export function getWork_area(id) {
  return request({
    url: '/weather/work_area/' + id,
    method: 'get'
  })
}

// 新增工区信息
export function addWork_area(data) {
  return request({
    url: '/weather/work_area',
    method: 'post',
    data: data
  })
}

// 修改工区信息
export function updateWork_area(data) {
  return request({
    url: '/weather/work_area',
    method: 'put',
    data: data
  })
}

// 删除工区信息
export function delWork_area(id) {
  return request({
    url: '/weather/work_area/' + id,
    method: 'delete'
  })
}

// 导出工区信息
export function exportWork_area(query) {
  return request({
    url: '/weather/work_area/export',
    method: 'get',
    params: query
  })
}