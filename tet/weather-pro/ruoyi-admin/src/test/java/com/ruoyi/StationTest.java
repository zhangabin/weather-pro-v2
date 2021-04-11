package com.ruoyi;

import com.ruoyi.weather.sysmanagement.station.domain.TRainfallStation;
import com.ruoyi.weather.sysmanagement.station.service.ITRainfallStationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 雨量站测试
 * @author 良子
 * @title: StationTest
 * @projectName ruoyi
 * @description: TODO
 * @date 2021/3/19 9:40
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class StationTest {

    @Autowired
    private ITRainfallStationService itRainfallStationService;

    /**
     * 雨量站添加测试 （正常）
     */
    @Test
    public void addStation(){
        TRainfallStation tRainfallStation = new TRainfallStation();
        tRainfallStation.setInstallationSite("测试");
        tRainfallStation.setLatitude("测试");
        tRainfallStation.setLongitude("测试");
        tRainfallStation.setResponsibilityWorkArea("测试");
        int i = itRainfallStationService.insertTRainfallStation(tRainfallStation);
        System.out.println("添加影响行数："+i);
    }

    /**
     * 雨量站查询测试 （正常）
     */
    @Test
    public void findStation() {
        TRainfallStation tRainfallStation = itRainfallStationService.selectTRainfallStationById(1L);
        System.out.println(tRainfallStation);
    }
}
