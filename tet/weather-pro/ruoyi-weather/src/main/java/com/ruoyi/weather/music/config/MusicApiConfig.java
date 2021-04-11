package com.ruoyi.weather.music.config;

import com.dtflys.forest.springboot.annotation.ForestScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName MusicApiConfig
 * @Author 1129017402@qq.com
 * @Date 2021/2/3 15:54
 */
@Component
@ForestScan(basePackages = {"com.ruoyi.**.api"})
@ConfigurationProperties(prefix = "music")
public class MusicApiConfig {

}
