package com.ruoyi.weather.music.api;

import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.DataVariable;
import com.dtflys.forest.annotation.Get;
import com.ruoyi.weather.music.interceptor.MusicInterceptor;
import com.ruoyi.weather.sysmanagement.auto.station.domain.StationReturnVo;

/**
 * @author yyeran yyearn_a@163.cim
 * @title StationApi
 * @date 2021/4/8
 */
public interface StationApi {


    @Get(
            url = "http://10.62.90.120/music-ws/api?serviceNodeId=NMIC_MUSIC_CMADAAS&userId=USR_XSTL53553&interfaceId=getStaInfoByStaId&dataCode=STA_INFO_SURF_GLB&elements=City,Town,Province,Station_Name,Admin_Code_CHN,Town_code,Cnty,Station_Id_C,Lat,Lon&staIds=54511,54512&dataFormat=json&timestamp=${timestamp}&nonce=e512d153-947e-465a-8f78-29cf50fd6675&sign=${sign}",
            dataType = "json"
    )
    StationReturnVo getRegionalAutomaticStationSiteInformation(
            @DataVariable("timestamp") Long timestamp,
            @DataVariable("sign") String sign
    );


    @Get(
            url = "http://127.0.0.1:8899/test",
            dataType = "json",
            interceptor = {MusicInterceptor.class}
    )
    StationReturnVo test();
}
