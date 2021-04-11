<template>
  <div style="height: 100%;"  class="big-screen-background">
    <div v-title data-title="车站预报"></div>
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
      <!-- 左侧tab -->
      <ul class="left-tab">
        <li class="tabNor" @mouseenter ="mousemoveWorkArea(1)" @mouseleave="mouseleaveWorkArea(1)">
          <i class="el-icon-caret-right"></i>
          <span>大准线</span>
          <!-- 鼠标移入显示工区 -->
          <ul class="work-list" v-if="showDaZhunLine"  >
            <div class="triangle-l"></div>
            <li class="w-item" v-for="item in daZhunLineGetData"  @click="handleZoneSelect(item.id)">{{item.workAreaName}}</li>
          </ul>
        </li>
        <li class="tabNor" @mouseenter ="mousemoveWorkArea(2)" @mouseleave="mouseleaveWorkArea(2)">
          <i class="el-icon-caret-right"></i>
          <span>准池线</span>
          <!-- 鼠标移入显示工区 -->
          <ul class="work-list" v-if="showZhunChiLine" >
            <div class="triangle-l"></div>
            <li class="w-item" v-for="item in zhunChiLineGetData" @click="handleZoneSelect(item.id)">{{item.workAreaName}}</li>
          </ul>
        </li>
        <li class="tabNor" @mouseenter ="mousemoveWorkArea(3)" @mouseleave="mouseleaveWorkArea(3)">
          <i class="el-icon-caret-right" ></i>
          <span>新准线</span>
          <!-- 鼠标移入显示工区 -->
          <ul class="work-list" v-if="showBaZhunLine" >
            <div class="triangle-l"></div>
            <li class="w-item" v-for="item in baZhunLineGetData" @click="handleZoneSelect(item.id)">{{item.workAreaName}}</li>
          </ul>
        </li>
      </ul>
      <div class="tabWeek"  @click="handleTrendForecast()">
        <span>未来四天趋势预报</span>
        <img class="tab-arrow" src="@/assets/images/bigScreen/arrow.png">
      </div>
      <!-- 工区表格 -->
      <ul class="work-area" v-if="showStationForecast">
        <li class="work-item" v-for="item in workAreaForceast" >
          <table class="r-table">
            <tr>
              <td>工区/日期</td>
              <td :colspan="item.length">{{item[0].forecastDate}}--{{item[0].workName}}</td>
            </tr>
            <tr>
              <td>时间</td>
              <td v-for="num in item">{{num.forecastHour}}</td>
            </tr>
            <tr>
              <td>风向</td>
              <td v-for="num in item">
                <span v-if="num.wind_d >= 337.5 && num.wind_d <= 360">北</span>
                <span v-else-if="num.wind_d >= 0 && num.wind_d <= 22.5">北</span>
                <span v-else-if="num.wind_d > 22.5 && num.wind_d <= 67.5">东北</span>
                <span v-else-if="num.wind_d > 67.5 && num.wind_d <= 112.5">东</span>
                <span v-else-if="num.wind_d > 112.5 && num.wind_d <= 157.5">东南</span>
                <span v-else-if="num.wind_d > 157.5 && num.wind_d <= 202.5">南</span>
                <span v-else-if="num.wind_d > 202.5 && num.wind_d <= 247.5">西南</span>
                <span v-else-if="num.wind_d > 247.5 && num.wind_d <= 292.5">西</span>
                <span v-else-if="num.wind_d > 292.5 && num.wind_d < 337.5">西北</span>
              </td>
            </tr>
            <tr>
              <td>风速(m/s)</td>
              <td v-for="num in item">{{num.wind_v}}</td>
            </tr>
            <tr>
              <td>温度(℃)</td>
              <td v-for="num in item">{{num.temperature}}</td>
            </tr>
            <tr>
              <td>降水量(mm)</td>
              <td v-for="num in item">{{num.sum_precipitation}}</td>
            </tr>
          </table>
        </li>
      </ul>
      <!-- 未来四天趋势预报 -->
      <ul class="future"  v-if="showTrendForecast">
        <li class="f-text1">未来四天趋势预报</li>
        <li class="leg-line"></li>
        <li class="f-text2">新朔铁路天气预报</li>
        <ul class="f-info inner">
          <li class="f-text3">
            <p>1、7月1日天气预报</p>
            <p>新准铁路：</p>
            <p>康巴什：多云，16/28摄氏度，东南风3-4级。</p>
            <p>大准铁路：</p>
            <p>准格尔：多云间晴，17/28摄氏度，东南风3-4级转东北风3-4级。</p>
            <p>凉城：雷阵雨，14/26摄氏度，西南风3-4级。</p>
            <p>准池铁路：</p>
            <p>右玉：小雨，12/26摄氏度，微风级。</p>
            <p>平鲁：小雨，13/25摄氏度，西北风3-4级东南风3-4级。</p>
          </li>
          <li class="sep-line-c"></li>
          <li class="f-text3">
            <p>2、7月1日天气预报</p>
            <p>康巴什、准格尔地区持续多云有雷阵雨；</p>
            <p>凉城、大同、右玉、平鲁地区持续小雨。</p>
          </li>
        </ul>
        <div class="leg-line"></div>
        <div class="set-flex add-m-t2">
          <el-button>数据导出<i class="el-icon-caret-right"></i></el-button>
        </div>
      </ul>
    </div>
  </div>
</template>

<script>
  import {listWork_area, getWork_area, delWork_area, addWork_area, updateWork_area, exportWork_area } from "@/api/sysmanagement/work_area";
  import {listWorkAreaForecast,getWorkAreaForecast,addWorkAreaForecast,updateWorkAreaForecast,delWorkAreaForecast,exportWorkAreaForecast} from "@/api/sysmanagement/workAreaForecast";
  import '@/assets/styles/bigScreen.scss';
  export default {
    data() {
      return {
        showStationForecast: true,
        showTrendForecast: false,
        showDaZhunLine: false,
        showZhunChiLine:false,
        showBaZhunLine:false,
        workArea:[],
        workAreaForceast:[],
      }
    },
    created(){
        this.getWorkAreaForecastData(22);
        listWork_area(null).then(response =>{
          this.workArea = response.rows;
        })
    },
    computed: {
        daZhunLineGetData() {
           return this.workArea.filter(v => v.line === "1")
        },
        zhunChiLineGetData() {
           return this.workArea.filter(v => v.line === "2")
        },
        baZhunLineGetData() {
           return this.workArea.filter(v => v.line === "3")
        }
    },
    methods: {
      //处理工区选择显示表格加载预报
      async handleZoneSelect(workAreaId){
          await this.getWorkAreaForecastData(workAreaId);
          this.showTrendForecast=false;
          this.showStationForecast=true;
          this.showDaZhunLine=false,
          this.showZhunChiLine=false,
          this.showBaZhunLine=false
      },
      //处理趋势预报显示趋势预报
      handleTrendForecast(){
        this.showTrendForecast=true;
        this.showStationForecast=false;
      },
      mousemoveWorkArea(line){
          if(line == 1){
            this.showDaZhunLine = true;
          }else if(line == 2){
            this.showZhunChiLine = true;
          }else if(line == 3){
            this.showBaZhunLine = true;
          }
      },
      mouseleaveWorkArea(line){
        if(line == 1){
          this.showDaZhunLine = false;
        }else if(line == 2){
          this.showZhunChiLine = false;
        }else if(line == 3){
          this.showBaZhunLine = false;
        }
      },
      getWorkAreaForecastData(workAreaId){
        getWorkAreaForecast(workAreaId).then(response =>{
            this.workAreaForceast = response.data;
        })
      }
    }
  }
</script>

<style scoped lang="scss">
</style>
