import request from '@/utils/request'

// 查询雨量站信息列表
export function listRainfall_station(query) {
  return request({
    url: '/station/rainfall_station/list',
    method: 'get',
    params: query
  })
}

// 查询雨量站信息列表（携带工区）
export function listRainfall_stationAndWorkArea() {
  return request({
    url: '/station/rainfall_station/list/wa',
    method: 'get',
  })
}

// 查询雨量站信息详细
export function getRainfall_station(id) {
  return request({
    url: '/station/rainfall_station/' + id,
    method: 'get'
  })
}

// 新增雨量站信息
export function addRainfall_station(data) {
  return request({
    url: '/station/rainfall_station',
    method: 'post',
    data: data
  })
}

// 修改雨量站信息
export function updateRainfall_station(data) {
  return request({
    url: '/station/rainfall_station',
    method: 'put',
    data: data
  })
}

// 删除雨量站信息
export function delRainfall_station(id) {
  return request({
    url: '/station/rainfall_station/' + id,
    method: 'delete'
  })
}

// 导出雨量站信息
export function exportRainfall_station(query) {
  return request({
    url: '/station/rainfall_station/export',
    method: 'get',
    params: query
  })
}
