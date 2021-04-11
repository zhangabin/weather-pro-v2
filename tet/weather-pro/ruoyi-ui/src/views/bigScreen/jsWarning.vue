<template>
  <div style="height: 100%;" class="big-screen-background">
    <div v-title data-title="降水告警"></div>
    <!-- 头部组件 -->
    <ul class="r-head">
      <li class="r-line">
        <img src="@/assets/images/bigScreen/line1.png">
      </li>
      <li class="r-title">
        <img src="@/assets/images/bigScreen/logo.png">
        <span>新朔铁路气象服务</span>
      </li>
      <li class="r-line">
        <img src="@/assets/images/bigScreen/line2.png">
      </li>
    </ul>
    <div class="set-line1"><span></span></div>
    <div class="r-con">
      <!-- 背景图片 -->
      <!-- <img class="r-bgr" src="@/assets/images/bigScreen/bgr.png"> -->
      <div ref="mapContainer" class="r-bgr"></div>
      <!-- 左侧tab -->
      <ul class="left-tab">
        <li class="tabNor" @click="changeRail('qx',0)" :class="{tabNow:railway==0}">
          <i class="el-icon-caret-right"></i>
          <span>新朔铁路全线</span>
        </li>
        <li class="tabNor" @click="changeRail('dz',1)" :class="{tabNow:railway==1}">
          <i class="el-icon-caret-right"></i>
          <span>大准线</span>
        </li>
        <li class="tabNor" @click="changeRail('zc',2)" :class="{tabNow:railway==2}">
          <i class="el-icon-caret-right"></i>
          <span>准池线</span>
        </li>
        <li class="tabNor" @click="changeRail('xz',3)" :class="{tabNow:railway==3}">
          <i class="el-icon-caret-right"></i>
          <span>巴准线</span>
        </li>
        <li class="tab-text" @click="allArea = true">全线工区降雨量统计<i class="el-icon-arrow-right"></i></li>
      </ul>
      <!--示意图片-->
      <!--            <ul class="map-box">-->
      <!--              <li class="map-item">-->
      <!--                <img class="r-img" src="@/assets/images/bigScreen/img5.png">-->
      <!--                &lt;!&ndash; 地图点位、提示框 &ndash;&gt;-->
      <!--                <div class="map-sta station1">-->
      <!--                  <span class="map-cir"></span>-->
      <!--                  <div class="map-info info-left">某某站（52mm）</div>-->
      <!--                </div>-->
      <!--                <div class="map-sta station2">-->
      <!--                  <div class="map-info info-right">某某站（52mm）</div>-->
      <!--                  <span class="map-cir"></span>-->
      <!--                </div>-->
      <!-- 预警点位、弹窗 -->
      <!--                <ul class="sta-warn sta-warn1">-->
      <!--                  <li class="w-cir w-cir1"><span></span></li>-->
      <!--                  <li class="w-info">-->
      <!--                    <div class="w-sta">-->
      <!--                      <p @click="singleArea = true">外西沟—清水河</p>-->
      <!--                      <span class="w-alert1">封锁警戒</span>-->
      <!--                    </div>-->
      <!--                    <p class="w-text">1小时降水量48mm（阈值：45mm）</p>-->
      <!--                  </li>-->
      <!--                </ul>-->
      <!--                <ul class="sta-warn sta-warn2">-->
      <!--                  <li class="w-cir w-cir2"><span></span></li>-->
      <!--                  <li class="w-info">-->
      <!--                    <div class="w-sta">-->
      <!--                      <p>卧厂— 神池南</p>-->
      <!--                      <span class="w-alert2">封锁预警</span>-->
      <!--                    </div>-->
      <!--                    <p class="w-text">1小时降水量48mm（阈值：45mm）</p>-->
      <!--                  </li>-->
      <!--                </ul>-->
      <!--                <ul class="sta-warn sta-warn3">-->
      <!--                  <li class="w-cir w-cir3"><span></span></li>-->
      <!--                  <li class="w-info">-->
      <!--                    <div class="w-sta">-->
      <!--                      <p>外西沟—清水河</p>-->
      <!--                      <span class="w-alert3">封锁预警</span>-->
      <!--                    </div>-->
      <!--                    <p class="w-text">1小时降水量48mm（阈值：45mm）</p>-->
      <!--                  </li>-->
      <!--                </ul>-->
      <!--              </li>-->
      <!--            </ul>-->
      <!-- 右侧图例 -->
      <div class="r-legend legend3 leg-alert">
        <ul class="spill-over1 inner">
          <div v-for="num in 3" :key="num">
            <li class="alert-box">
              <div class="alert-cir cir1"></div>
              <div class="alert-text">
                <p class="warn-type type1">封锁警戒 <span>[发布时间：2020-08-29 05]</span></p>
                <p class="warn-sta">大准线 外西沟—清水河</p>
                <p class="warn-sta">1小时降水量48mm（阈值：45mm）
                  <i class="el-icon-video-play play-icon"></i>
                </p>
              </div>
            </li>
            <li class="leg-line"></li>
            <li class="alert-box">
              <div class="alert-cir cir3"></div>
              <div class="alert-text">
                <p class="warn-type type3">限速警戒 <span>[发布时间：2020-08-29 05]</span></p>
                <p class="warn-sta">准池线 卧厂— 神池南</p>
                <p class="warn-sta">日连续+1小时降水量52mm（阈值：43mm）
                  <i class="el-icon-video-play play-icon"></i>
                </p>
              </div>
            </li>
            <li class="leg-line"></li>
            <li class="alert-box">
              <div class="alert-cir cir2"></div>
              <div class="alert-text">
                <p class="warn-type type2">出巡警戒 <span>[发布时间：2020-08-29 05]</span></p>
                <p class="warn-sta">巴准线 巴图塔—点岱沟</p>
                <p class="warn-sta">1小时降水量48mm（阈值：45mm）
                  <i class="el-icon-video-play play-icon"></i>
                </p>
              </div>
            </li>
          </div>
        </ul>
      </div>
    </div><!-- r-con止 -->

    <!-- 全线工区降雨量统计弹窗 -->
    <div class="js-shadow" v-show="allArea">
      <ul class="js-dialog">
        <el-button class="js-close" @click="allArea = false" icon="el-icon-close" circle></el-button>
        <!-- 全线工区弹窗显示内容组件 -->
        <allArea v-bind:showDialog="allArea"></allArea>
      </ul>
    </div>

    <!-- 单工区弹窗 -->
    <div class="js-shadow" v-if="singleArea">
      <ul class="js-dialog">
        <el-button class="js-close" @click="singleArea = false" icon="el-icon-close" circle></el-button>
        <!-- 单工区弹窗显示内容组件 -->
        <singleArea v-bind:showDialog="singleArea"></singleArea>
      </ul>
    </div>
  </div>
</template>

<script>
import echarts from 'echarts'
import bigScreenMixin from '@/utils/bigScreenMixin'
import mapboxgl from 'mapbox-gl'
import { getXsRailGisData } from '@/api/gis/gis'
import { listRainfall_stationAndWorkArea } from '@/api/sysmanagement/rainfall_station'
import { markerPoint, showMarkerByRailType, createPopup } from '@/utils/marker'

export default {
  mixins: [bigScreenMixin],
  data() {
    return {
      allArea: false, //全区雨量站弹窗
      singleArea: false //单工区弹窗
    }
  },
  mounted() {
    this.map.on('load', () => {
      this.addSource()
      this.setMarkerPopup()
    })
  },
  methods: {
    //给雨量计添加弹窗
    setMarkerPopup(){
      this.stations.forEach((item, index) => {
        if(index % 2 === 0){
          item.setPopup(createPopup(this.map))
        }
      })
    }
  }
}
</script>

<style scoped lang="scss">
::v-deep .map-info {
  height: 30px;
  line-height: 30px;
  color: #fff;
  font-size: 14px;
  min-width: 110px;
  padding: 0 25px;
  background: url(../../assets/images/bigScreen/box2.png) no-repeat;
  background-size: 100% 100%;
}
::v-deep .info-left{
  background: url(../../assets/images/bigScreen/box2-1.png) no-repeat;
  background-size: 100% 100%;
  height: 35px;
  line-height: 35px;
  //opacity: 0.8;
}

::v-deep .info-right {
  background: url(../../assets/images/bigScreen/box2-2.png) no-repeat;
  background-size: 100% 100%;
  height: 35px;
  line-height: 35px;
}

::v-deep .mapboxgl-popup-tip {
  display: none;
}

::v-deep .mapboxgl-popup-content {
  background: rgba(0, 0, 0, 0);
  border-radius: 3px;
  -webkit-box-shadow: 0 0 0;
  box-shadow: 0 0 0;
  padding: 10px 10px 15px;
  pointer-events: auto;
}

::v-deep .w-info {
  min-width: 300px;
  height: 90px;
  background: url(../../assets/images/bigScreen/box7.png) no-repeat;
  background-size: 100% 100%;
  position: absolute;
  top: -45px;
  left: -20px;
  padding: 20px 40px 30px 50px;
  font-size: 12px;
  color: #fff;

  .w-sta {
    display: flex;
    align-items: center;

    p {
      font-size: 13px;
    }

    p:hover {
      cursor: pointer;
    }

    .w-alert1 {
      margin-left: 10px;
      padding: 1px 5px;
      border-radius: 4px;
      border: 1px solid #E90000;
      color: #E90000;
      background: rgba(234, 0, 0, 0.2);
    }

     .w-alert2 {
      margin-left: 10px;
      padding: 1px 5px;
      border-radius: 4px;
      border: 1px solid #FF721A;
      color: #FF721A;
      background: rgba(255, 114, 26, 0.2);
    }

    .w-alert3 {
      margin-left: 10px;
      padding: 1px 5px;
      border-radius: 4px;
      border: 1px solid #FFBD0A;
      color: #FFBD0A;
      background: rgba(255, 189, 10, 0.2);
    }
  }

  ::v-deep .w-text {
    margin-top: 5px;
  }
}
</style>
