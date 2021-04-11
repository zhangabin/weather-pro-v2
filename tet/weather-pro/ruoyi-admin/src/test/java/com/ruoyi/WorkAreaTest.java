package com.ruoyi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.weather.sysmanagement.station.domain.TRainfallStation;
import com.ruoyi.weather.sysmanagement.station.service.ITRainfallStationService;
import com.ruoyi.weather.sysmanagement.workarea.domain.TWorkArea;
import com.ruoyi.weather.sysmanagement.workarea.service.ITWorkAreaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * 工区信息单元测试视
 * @author 良子
 * @title: RuoYiApplication
 * @projectName ruoyi
 * @description: TODO
 * @date 2021/3/18 16:57
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class WorkAreaTest {

    @Autowired
    private ITWorkAreaService itWorkAreaService;


    /**
     * 工区信息 查询测试（正常）
     */
    @Test
    public void findWorkArea() {
        TWorkArea tWorkArea = itWorkAreaService.selectTWorkAreaById(2L);
        System.out.println(tWorkArea.gettRainfallStations());
    }
    /**
     * 工区信息新增测试 （正常）
     */
    @Test
    public void addWorkArea() {
        TWorkArea tworkArea = new TWorkArea();
        tworkArea.setWorkAreaName("新增工区");
        tworkArea.setStartingPoint("起点里程");
        tworkArea.setTerminus("终点里程");
        tworkArea.setCentre("中心里程数");
        tworkArea.setLine("1");
        int i = itWorkAreaService.insertTWorkArea(tworkArea);
        System.out.println("新增影响行数："+ i);
    }

    /**
     * 更新工区信息测试 （正常）
     */
    @Test
    public void updateWorkArea() {
        TWorkArea tworkArea = new TWorkArea();
        tworkArea.setId(2L);
        tworkArea.setWorkAreaName("更新");
        int i = itWorkAreaService.updateTWorkArea(tworkArea);
        System.out.println("更新影响行数："+ i);

    }

    /**
     * 删除工区信息测试 （正常）
     */
    @Test
    public void deleteWorkArea() {
        int i = itWorkAreaService.deleteTWorkAreaById(1L);
        System.out.println("删除影响行数："+ i);
    }

    @Test
    public void geojson() {
        ClassPathResource xinsuo = new ClassPathResource("gis/xinsuo.geojson");
        try {
            InputStream inputStream = xinsuo.getInputStream();
            Map map = new ObjectMapper().readValue(inputStream, Map.class);
            System.out.println(map);
        }catch (IOException e){
           e.printStackTrace();
        }
    }
}
