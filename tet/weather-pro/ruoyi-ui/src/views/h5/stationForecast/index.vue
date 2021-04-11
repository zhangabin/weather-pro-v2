<!-- 车总预报-移动端 -->
<template>
  <div class="app">
   <ul class="work-area">
     <li class="work-item" style="margin-top: 10px;text-align: center;"  v-for="item in workAreaForceast">
        <van-nav-bar :title="item[0].workName" :border="false" />
        <el-table :data="item" style="width: 100%;margin-top: 10px;" border stripe>
          <el-table-column align = "center" prop="forecastHour" label="时间"></el-table-column>
          <el-table-column align = "center" prop="wind_d" label="风向">
            <template slot-scope="scope">
              <span v-if="scope.row.wind_d >= 337.5 && scope.row.wind_d <= 360">北</span>
              <span v-else-if="scope.row.wind_d >= 0 && scope.row.wind_d <= 22.5">北</span>
              <span v-else-if="scope.row.wind_d > 22.5 && scope.row.wind_d <= 67.5">东北</span>
              <span v-else-if="scope.row.wind_d > 67.5 && scope.row.wind_d <= 112.5">东</span>
              <span v-else-if="scope.row.wind_d > 112.5 && scope.row.wind_d <= 157.5">东南</span>
              <span v-else-if="scope.row.wind_d > 157.5 && scope.row.wind_d <= 202.5">南</span>
              <span v-else-if="scope.row.wind_d > 202.5 && scope.row.wind_d <= 247.5">西南</span>
              <span v-else-if="scope.row.wind_d > 247.5 && scope.row.wind_d <= 292.5">西</span>
              <span v-else-if="scope.row.wind_d > 292.5 && scope.row.wind_d < 337.5">西北</span>
            </template>
          </el-table-column>
          <el-table-column align = "center" prop="wind_v" label="风速"> </el-table-column>
          <el-table-column align = "center" prop="sum_precipitation" label="降水量"> </el-table-column>
          <el-table-column align = "center" prop="temperature" label="气温"> </el-table-column>
        </el-table>
     </li>
   </ul>
   <div style="display: inline-block; height: 50px;"></div>
   <img src="@/assets/images/bigScreen/select.png" @click="showPicker" class="select">
   <van-popup v-model="showDatePicker" round position="bottom" :transition-appear="true">
      <van-picker show-toolbar title="请选择线路和时间" :columns="columns" @confirm="notarize" @cancel="cancelPicker" />
    </van-popup>
  </div>
</template>

<script>
import { getForecastByLineAndForecastDate } from "@/api/sysmanagement/workAreaForecast";
import { vantComponents } from "@/views/h5/vantComponents";
import {  Toast,Icon  } from 'vant';
export default {
  name: "ReportedForm",
  components: {
    ...vantComponents()
  },
  data() {
    return {
        workAreaForceast: [],
        showDatePicker:false,
        columns: [
          // 第一列
          {
            values: ['大准线', '准池线', '新准线',],
            defaultIndex: 2,
          },
          // 第二列
          {
            values: ['2021-04-09','2021-04-10','2021-04-11'],//这个值是动态的，再下面会动态赋值
            defaultIndex: 1,
          },
        ],
    };
  },
  created() {
    this.getWorkAreaForecastData("1",null);//默认大准线，当前日期未来一天，因为是默认，传null就行
  },
  methods: {
    showPicker(){
      this.showDatePicker = true;
    },
    cancelPicker(){
      this.showDatePicker = false;
    },
    notarize(values,indexs){
      //values是选中的值，index是索引
      let line = "-1";
      if(values[0] === "大准线"){
          line = "1";
      }else if(values[0] === "准池线"){
         line = "2";
      }else if(values[0] === "新准线"){
        line = "3"
      }
      this.getWorkAreaForecastData(line,values[1])
    },
    getWorkAreaForecastData(line,forecastDate){
      getForecastByLineAndForecastDate({line:line,forecastDate:forecastDate}).then(response =>{
          this.workAreaForceast = response.data.areaForecastMap;
          this.columns[1].values = response.data.forecastDateStr;
          this.showDatePicker = false;
      })
    }
  }
};
</script>
<style scoped="scoped">
  .select {
    position: fixed;
    right: 10px;
    bottom: 9px;
    height: 45px;
  }
</style>
