<template>
  <div style="height: 100%;" class="big-screen-background">
    <!-- 头部内容 -->
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
      <!-- 地图组件 -->
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
      </ul>
      <!-- 顶部tab -->
      <div class="tab-box">
        <ul class="top-tab">
          <li class="tabNor" @click="disaster=0" :class="{tabNow:disaster==0}">雷达回波图</li>
          <li class="tabNor" @click="disaster=1" :class="{tabNow:disaster==1}">实况警戒</li>
          <li class="tabNor" @click="disaster=2" :class="{tabNow:disaster==2}">气象灾害预警</li>
        </ul>
      </div>
      <!-- 1.雷达回波图 -->
      <!-- 地图区域 -->
      <ul class="map-box" v-show="disaster==0">
        <li class="map-item">
          <img class="r-img" src="@/assets/images/bigScreen/img1.png">
          <!-- 地图点位、提示框 -->
          <div class="map-sta station1">
            <span class="map-cir"></span>
            <div class="map-info">某某车站</div>
          </div>
        </li>
      </ul>
      <!-- 右侧图例 -->
      <ul class="r-legend legend2" v-show="disaster==0">
        <li class="leg-text"><p>基本反射率</p><p> dBZ</p></li>
        <li class="leg-line"></li>
        <li class="leg-piece piece1"><span></span><p>70</p></li>
        <li class="leg-piece piece2"><span></span><p>65</p></li>
        <li class="leg-piece piece3"><span></span><p>60</p></li>
        <li class="leg-piece piece4"><span></span><p>55</p></li>
        <li class="leg-piece piece5"><span></span><p>50</p></li>
        <li class="leg-piece piece6"><span></span><p>45</p></li>
        <li class="leg-piece piece7"><span></span><p>40</p></li>
        <li class="leg-piece piece8"><span></span><p>35</p></li>
        <li class="leg-piece piece9"><span></span><p>30</p></li>
        <li class="leg-piece piece10"><span></span><p>25</p></li>
        <li class="leg-piece piece11"><span></span><p>20</p></li>
        <li class="leg-piece piece12"><span></span><p>15</p></li>
        <li class="leg-piece piece13"><span></span><p>10</p></li>
      </ul>
      <!-- 底部滑块 -->
      <div class="r-slider" v-show="disaster==0">
        <div class="block">
          <el-slider
            v-model="value"
            :step="5"
            show-stops
            :marks="marks">
          </el-slider>
        </div>
      </div><!-- 雷达回波图止 -->

      <!-- 2.实况警戒 -->
      <!-- 地图区域 -->
      <ul class="map-box" v-show="disaster==1">
        <!-- 线路图-->
        <li class="map-item">
<!--          <img class="r-img" src="@/assets/images/bigScreen/img3.png">-->
        </li>
        <!-- 点位 1 起 -->
        <li class="live-cir-out live-p1">
          <!-- 定位点 -->
          <span class="live-cir-in"></span>
          <!-- 指向线、弹窗 -->
          <div class="m-line line-t">
            <span class="m-line-cir"></span>
            <div class="win-box position1">
              <p class="win-sta">布耳洞沟（5**）</p>
              <ul class="win-info">
                <li class="win-con">
                  <p>气 温（℃）：18</p>
                  <p class="add-m-t text-red">降 水（mm）：26</p>
                </li>
                <li class="win-line"></li>
                <li class="win-con">
                  <p>风 速（m/s）：4</p>
                  <p class="add-m-t">能见度（km）：20</p>
                </li>
              </ul>
            </div>
          </div>
        </li> <!-- 点位 1 止 -->

        <!-- 点位 2 起 -->
        <li class="live-cir-out live-p2">
          <!-- 定位点 -->
          <span class="live-cir-in"></span>
          <!-- 指向线、弹窗 -->
          <div class="m-line line-b">
            <span class="m-line-cir"></span>
            <div class="win-box position2">
              <p class="win-sta">布耳洞沟（5**）</p>
              <ul class="win-info">
                <li class="win-con">
                  <p>气 温（℃）：18</p>
                  <p class="add-m-t">降 水（mm）：26</p>
                </li>
                <li class="win-line"></li>
                <li class="win-con">
                  <p>风 速（m/s）：4</p>
                  <p class="add-m-t">能见度（km）：20</p>
                </li>
              </ul>
            </div>
          </div>
        </li> <!-- 点位 2 止 -->
      </ul>
      <!-- 右侧预警 -->
      <ul class="r-legend legend3" v-show="disaster==1">
        <li class="warn-type type1"><p>暴雨告警</p><span>[发布时间：2020-08-29 05]</span></li>
        <li class="warn-sta">大准线  外西沟—清水河</li>
        <li class="warn-info">外西沟—清水河周边有4个地面自动站30分钟累积降水达20mm，工区全段可能出现暴雨，请相关单位做好应急处置。</li>
        <li class="leg-line"></li>
        <li class="warn-type type2"><p>能见度告警</p><span>[发布时间：2020-08-29 05]</span></li>
        <li class="warn-sta">准池线   卧厂— 神池南</li>
        <li class="warn-info">卧厂— 神池南周边有3个地面自动站近一小时内能见度不足一公里，可能存在局部大雾或其他极端天气，请相关单位确认情况并限速途经列车。</li>
        <li class="leg-line"></li>
        <li class="warn-type type3"><p>大风告警</p><span>[发布时间：2020-08-29 05]</span></li>
        <li class="warn-sta">巴准线   巴图塔—点岱沟</li>
        <li class="warn-info">巴图塔—点岱沟周边有5个地面自动站近一小时极大风速超过10米/秒，该工区已受大风影响，请相关单位及时采取应急措施。</li>
      </ul><!-- 实况警戒止 -->

      <!-- 3.气象灾害预警 -->
      <!-- 地图区域 -->
      <ul class="map-box" v-show="disaster==2">
        <li class="map-item">
<!--          <img class="r-img" src="@/assets/images/bigScreen/img4.png">-->
          <!-- 地图点位、提示框 -->
          <div class="map-sta station1">
            <span class="map-cir"></span>
            <div class="map-info">某某车站</div>
          </div>
        </li>
        <!-- 点位1 -->
        <li class="warn-icon set-icon1">
          <svg class="icon1" aria-hidden="true">
            <use xlink:href="#icon-leidian"></use>
          </svg>
          <!-- 弹窗 -->
          <div class="win-box win-warn">
            <div class="direction"></div>
            <p class="warn-type">雷电红色预警<span class="warn w-red">红色预警</span></p>
            <p class="warn-info">准格尔旗气象局2020年9月6日16时15分发布暴雨橙色预警，预计3小时内降雨量将达50mm以上，或已达50mm且降雨可能持续，请相关单位做好防范。</p>
          </div>
        </li>
        <!-- 点位2 -->
        <li class="warn-icon set-icon2">
          <svg class="icon1" aria-hidden="true">
            <use xlink:href="#icon-ziyuan"></use>
          </svg>
          <!-- 弹窗 -->
          <div class="win-box win-warn">
            <div class="direction"></div>
            <p class="warn-type">暴雨橙色预警<span class="warn w-orange">橙色预警</span></p>
            <p class="warn-info">准格尔旗气象局2020年9月6日16时15分发布暴雨橙色预警，预计3小时内降雨量将达50mm以上，或已达50mm且降雨可能持续，请相关单位做好防范。</p>
          </div>
        </li>
        <!-- 点位3 -->
        <li class="warn-icon set-icon3">
          <svg class="icon1" aria-hidden="true">
            <use xlink:href="#icon-dafeng"></use>
          </svg>
          <!-- 弹窗 -->
          <div class="win-box win-warn">
            <div class="direction"></div>
            <p class="warn-type">大风蓝色预警<span class="warn w-blue">蓝色预警</span></p>
            <p class="warn-info">准格尔旗气象局2020年9月6日16时15分发布暴雨橙色预警，预计3小时内降雨量将达50mm以上，或已达50mm且降雨可能持续，请相关单位做好防范。</p>
          </div>
        </li>
      </ul>
      <!-- 右下角预警说明 -->
      <ul class="warn-legend" v-show="disaster==2">
        <el-badge :value="3">
          <li class="leg-info">
            <div>
              <svg aria-hidden="true">
                <use xlink:href="#icon-leidian"></use>
              </svg>
            </div>
            <p>雷电</p>
          </li>
        </el-badge>
        <li class="leg-line-c"></li>
        <el-badge :value="1">
          <li class="leg-info">
            <div>
              <svg aria-hidden="true">
                <use xlink:href="#icon-ziyuan"></use>
              </svg>
            </div>
            <p>暴雨</p>
          </li>
        </el-badge>
        <li class="leg-line-c"></li>
        <li class="leg-info">
          <div>
            <svg aria-hidden="true">
              <use xlink:href="#icon-dawu"></use>
            </svg>
          </div>
          <p>大雾</p>
        </li>
        <li class="leg-line-c"></li>
        <li class="leg-info">
          <div>
            <svg aria-hidden="true">
              <use xlink:href="#icon-dafeng"></use>
            </svg>
          </div>
          <p>大风</p>
        </li>
      </ul>
      <!-- 气象灾害预警止 -->
    </div>
  </div>
</template>

<script>
  import '@/assets/styles/bigScreen.scss';
  import '@/assets/mapbox/mapbox-gl.scss';
  import bigScreenMixin from "@/utils/bigScreenMixin";
  import mapboxgl from 'mapbox-gl'
  export default {
    mixins: [ bigScreenMixin ],
    data() {
      return {
        map: undefined,
        railway:0,
        value: 50,
        disaster:0,
        marks: {
          0:{
            style: {
              color: '#00DEB3',
              fontSize:'12px',
            },
            label: this.$createElement('span', '28日,08:00')
          },
          20:{
            style: {
              color: '#00DEB3',
              fontSize:'12px',
            },
            label: this.$createElement('span', '28日,21:00')
          },
          40:{
            style: {
              color: '#00DEB3',
              fontSize:'12px',
            },
            label: this.$createElement('span', '29日,04:00')
          },
          95:{
            style: {
              color: '#00DEB3',
              fontSize:'12px',
            },
            label: this.$createElement('span', '30日,10:00')
          },
        }
      }
    },
    mounted(){
      // mapboxgl.accessToken = this.mapBoxAccessToken;
      // this.map = new mapboxgl.Map({
      //   container: this.$refs.mapContainer, // container id
      //   style: this.mapBoxStyleURI, // style URL
      //   center: [112.322, 39.523], // starting position [lng, lat]
      //   zoom: 7, // starting zoom
      // });
    },
  }
</script>

<style scoped lang="scss">

</style>
