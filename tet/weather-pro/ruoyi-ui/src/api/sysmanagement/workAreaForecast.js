import request from '@/utils/request'

// 查询工区预报数据列表
export function listWorkAreaForecast(query) {
  return request({
    url: '/workareaforecast/workAreaForecast/list',
    method: 'get',
    params: query
  })
}

// 查询工区预报数据详细
export function getWorkAreaForecast(workAreaId) {
  return request({
    url: '/workareaforecast/workAreaForecast/' + workAreaId,
    method: 'get'
  })
}

// 新增工区预报数据
export function addWorkAreaForecast(data) {
  return request({
    url: '/workareaforecast/workAreaForecast',
    method: 'post',
    data: data
  })
}

// 修改工区预报数据
export function updateWorkAreaForecast(data) {
  return request({
    url: '/workareaforecast/workAreaForecast',
    method: 'put',
    data: data
  })
}

// 删除工区预报数据
export function delWorkAreaForecast(workAreaId) {
  return request({
    url: '/workareaforecast/workAreaForecast/' + workAreaId,
    method: 'delete'
  })
}

// 导出工区预报数据
export function exportWorkAreaForecast(query) {
  return request({
    url: '/workareaforecast/workAreaForecast/export',
    method: 'get',
    params: query
  })
}

// 根据线路和选择的预报日期查询数据,服务于车站预报的H5端
export function getForecastByLineAndForecastDate(query) {
  return request({
    url: '/workareaforecast/workAreaForecast/getForecastByLineAndForecastDate',
    method: 'get',
    params: query
  })
}
