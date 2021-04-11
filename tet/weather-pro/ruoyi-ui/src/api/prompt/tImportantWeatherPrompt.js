import request from '@/utils/request'

// 查询重要天气提示列表
export function listTImportantWeatherPrompt(query) {
  return request({
    url: '/prompt/tImportantWeatherPrompt/list',
    method: 'get',
    params: query
  })
}

// 查询重要天气提示详细
export function getTImportantWeatherPrompt(id) {
  return request({
    url: '/prompt/tImportantWeatherPrompt/' + id,
    method: 'get'
  })
}

// 新增重要天气提示
export function addTImportantWeatherPrompt(data) {
  return request({
    url: '/prompt/tImportantWeatherPrompt',
    method: 'post',
    data: data
  })
}

// 修改重要天气提示
export function updateTImportantWeatherPrompt(data) {
  return request({
    url: '/prompt/tImportantWeatherPrompt',
    method: 'put',
    data: data
  })
}

// 删除重要天气提示
export function delTImportantWeatherPrompt(id) {
  return request({
    url: '/prompt/tImportantWeatherPrompt/' + id,
    method: 'delete'
  })
}

// 导出重要天气提示
export function exportTImportantWeatherPrompt(query) {
  return request({
    url: '/prompt/tImportantWeatherPrompt/export',
    method: 'get',
    params: query
  })
}