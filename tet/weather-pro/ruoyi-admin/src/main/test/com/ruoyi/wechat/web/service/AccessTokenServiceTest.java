package com.ruoyi.wechat.web.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class AccessTokenServiceTest {

    @Autowired
    private AccessTokenService accessTokenService;

    @Test
    void refreshAccessToken() {
        System.out.println(accessTokenService.refreshAccessToken());
    }

    @Test
    void getAccessToken() {
        System.out.println(accessTokenService.getAccessToken());
    }
}