import request from '@/utils/request'

// 查询防洪值班记录列表
export function listSituation(query) {
  return request({
    url: '/situation/situation/list',
    method: 'get',
    params: query
  })
}

// 查询防洪值班记录详细
export function getSituation(id) {
  return request({
    url: '/situation/situation/' + id,
    method: 'get'
  })
}

// 新增防洪值班记录
export function addSituation(data) {
  return request({
    url: '/situation/situation',
    method: 'post',
    data: data
  })
}

// 修改防洪值班记录
export function updateSituation(data) {
  return request({
    url: '/situation/situation',
    method: 'put',
    data: data
  })
}

// 删除防洪值班记录
export function delSituation(id) {
  return request({
    url: '/situation/situation/' + id,
    method: 'delete'
  })
}

// 导出防洪值班记录
export function exportSituation(query) {
  return request({
    url: '/situation/situation/export',
    method: 'get',
    params: query
  })
}