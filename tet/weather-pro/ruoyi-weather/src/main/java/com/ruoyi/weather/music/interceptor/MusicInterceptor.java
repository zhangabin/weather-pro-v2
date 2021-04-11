package com.ruoyi.weather.music.interceptor;

import com.dtflys.forest.http.ForestRequest;
import com.dtflys.forest.http.ForestResponse;
    import com.dtflys.forest.interceptor.Interceptor;
import com.dtflys.forest.reflection.ForestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author yyeran yyearn_a@163.cim
 * @title MusicInterceptor
 * @date 2021/4/7
 */
public class MusicInterceptor implements Interceptor<Object> {

    private final static Logger log = LoggerFactory.getLogger(MusicInterceptor.class);


    @Override
    public boolean beforeExecute(ForestRequest request) {
        log.info("===================>beforeExecute");
        return true;
    }
}
