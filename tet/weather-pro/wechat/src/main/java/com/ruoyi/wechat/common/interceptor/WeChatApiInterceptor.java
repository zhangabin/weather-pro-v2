package com.ruoyi.wechat.common.interceptor;

import com.dtflys.forest.exceptions.ForestRuntimeException;
import com.dtflys.forest.http.ForestRequest;
import com.dtflys.forest.http.ForestResponse;
import com.dtflys.forest.interceptor.Interceptor;
import com.ruoyi.framework.web.service.UserDetailsServiceImpl;
import com.ruoyi.wechat.api.domain.WeChatBaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 微信基础拦截器
 * @ClassName WeChatApiInterceptor
 * @Author 1129017402@qq.com
 * @Date 2021/3/5 23:20
 */
public class WeChatApiInterceptor implements Interceptor<WeChatBaseResult> {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    private static final String STATE = "ok";

    @Override
    public void onSuccess(WeChatBaseResult data, ForestRequest request, ForestResponse response) {
        if(!STATE.equals(data.getErrmsg())){
            log.error("Error Code：{} Error Message：{}", data.getErrcode(), data.getErrmsg());
            throw new RuntimeException("Error Code：" + data.getErrcode() + " Error Message：" + data.getErrmsg());
        }
    }

    @Override
    public void onError(ForestRuntimeException ex, ForestRequest request, ForestResponse response) {
        log.error(ex.getMessage());
        throw ex;
    }
}
