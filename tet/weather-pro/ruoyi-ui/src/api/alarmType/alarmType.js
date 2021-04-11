import request from '@/utils/request'

// 查询告警列表
export function getAlarmTypes(stat) {
  return request({
    url: '/alarmType/' + stat,
    method: 'get'
  })
}
