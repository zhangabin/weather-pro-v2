import request from '@/utils/request'

// 查询趋势预报管理列表
export function listForecast(query) {
  return request({
    url: '/forecast/forecast/list',
    method: 'get',
    params: query
  })
}

// 查询趋势预报管理详细
export function getForecast(id) {
  return request({
    url: '/forecast/forecast/' + id,
    method: 'get'
  })
}

// 新增趋势预报管理
export function addForecast(data) {
  return request({
    url: '/forecast/forecast',
    method: 'post',
    data: data
  })
}

// 修改趋势预报管理
export function updateForecast(data) {
  return request({
    url: '/forecast/forecast',
    method: 'put',
    data: data
  })
}

// 删除趋势预报管理
export function delForecast(id) {
  return request({
    url: '/forecast/forecast/' + id,
    method: 'delete'
  })
}

// 导出趋势预报管理
export function exportForecast(query) {
  return request({
    url: '/forecast/forecast/export',
    method: 'get',
    params: query
  })
}