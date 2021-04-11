import request from '@/utils/request'

//获取新朔铁路igs数据
export function getXsRailGisData() {
  return request({
    url: '/gis/xs',
    method: 'get'
  })
}
