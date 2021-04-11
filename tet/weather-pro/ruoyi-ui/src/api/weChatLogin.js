import request from '@/utils/request'

//微信登录
export function weChatLogin(code){
  return request({
    url: '/weChatLogin',
    method: 'post',
    data: code,
    headers: {
      isToken: false
    }
  })
}
