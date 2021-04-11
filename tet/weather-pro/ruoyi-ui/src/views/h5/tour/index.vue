<!-- 出巡情况-移动端 -->
<template>
  <div class="app">
    <van-nav-bar title="出巡情况" />
    <van-cell-group>
      <van-cell title="时间" value="2020-12-8 5时" />
      <van-cell title="值班人员" value="自动填写" />
      <van-cell title="降雨情况" value="自动填写" />
    </van-cell-group>
    <van-form @submit="onSubmit">
      <van-field name="checkboxGroup" label="报警等级">
        <template #input>
          <van-radio-group v-model="form.alarmLevel" direction="horizontal">
            <van-radio name="1" >出巡</van-radio>
            <van-radio name="2" >限速</van-radio>
            <van-radio name="3" >封锁</van-radio>
          </van-radio-group>
        </template>
      </van-field>
      <van-field
        :value="inspectionTimeStartStr"
        readonly
        clickable
        label="巡查开始时间"
        placeholder="请选择"
        @click="openDatePicker('inspectionTimeStart','开始时间')"
      />
      <van-field
        :value="inspectionTimeEndStr"
        readonly
        clickable
        label="巡查结束时间"
        placeholder="请选择"
        @click="openDatePicker('inspectionTimeEnd','结束时间')"
      />
      <van-field v-model="form.inspectionLot" label="地段" placeholder="请输入巡查地段" />
      <van-field name="checkboxGroup" label="方式">
        <template #input>
          <van-checkbox-group v-model="form.inspectionType"  direction="horizontal">
            <van-checkbox name="a">人工</van-checkbox>
            <van-checkbox name="b">轨道车</van-checkbox>
          </van-checkbox-group>
        </template>
      </van-field>
      <van-field
        v-model="form.inspectorsVehicles"
        label="人数、车辆"
        placeholder="请输入巡查人数、车辆"
        rows="2"
        :autosize="true"
        maxlength="50"
        show-word-limit/>
      <van-field
        v-model="form.inspectionHiddenDangers"
        label="巡查隐患情况"
        placeholder="请输入巡查隐患情况"
        rows="2"
        :autosize="true"
        maxlength="50"
        show-word-limit/>
      <van-field
        v-model="form.disposalMeasures"
        label="处置措施"
        placeholder="请输入处置措施"
        rows="2"
        :autosize="true"
        maxlength="50"
        show-word-limit/>
<!--      :rules="[{ required: true, message: '请填写密码' }]"-->
<!--      :disabled="!form.errorDescription||buttonLoading"-->
      <div style="margin: 16px;">
        <van-button
          :loading="buttonLoading"
          loading-text="提交中..."
          round
          block
          type="info"
          native-type="submit"
        >提交</van-button>
      </div>
    </van-form>

    <van-popup v-model="showDatePicker" round position="bottom" :transition-appear="true">
      <van-datetime-picker
        v-model="curValue"
        @confirm="dateSet"
        @cancel="showDatePicker = false"
        type="datetime"
        :title="title"
        :columns-order="['year','month','day','hour','minute']"
      />
    </van-popup>
  </div>
</template>

<script>
import { getSituation, addSituation, updateSituation } from "@/api/situation/situation";
import { vantComponents } from "@/views/h5/vantComponents";
import {  Toast } from 'vant';

export default {
  name: "ReportedForm",
  components: {
    ...vantComponents()
  },
  data() {
    return {
      curValue:new Date(),
      curType:null,
      title:null,
      form:{
        inspectionTimeStart:null,
        inspectionTimeEnd:null,
        remark:null,
      },
      // 遮罩层
      loading: true,
      buttonLoading: false,
      showDatePicker: false,
      alarmTypes:['工区无降水', '降水实际偏小', '降水实际偏小', '其他']
    };
  },
  async created() {

  },
  computed:{
    inspectionTimeStartStr(){
      if(this.form.inspectionTimeStart){
        return this.dateFormat(this.form.inspectionTimeStart,"yyyy-MM-dd hh:mm")
      }else{
        console.log(this.form.inspectionTimeStart)
        return this.form.inspectionTimeStart
      }
    },
    inspectionTimeEndStr(){
      if(this.form.inspectionTimeEnd){
        return this.dateFormat(this.form.inspectionTimeEnd,"yyyy-MM-dd hh:mm")
      }else{
        console.log(this.form.inspectionTimeEnd)
        return this.form.inspectionTimeEnd
      }

    },
  },
  methods: {
    openDatePicker(type,title){
      this.curType=type;
      this.title=title;
      if(!this.form[type]){
        this.curValue = new Date();
      }else{
        this.curValue = this.form[type];
      }

      this.showDatePicker = true;
    },
    dateSet(value){
      this.form[this.curType] = value;
      this.showDatePicker = false;
    },
    async onSubmit() {
      this.buttonLoading = true
      this.form.inspectionType = this.form.inspectionType.join(',')
      await addSitu8ation(this.form);
      this.buttonLoading = false
      Toast.success('提交成功');

    },
    onConfirm(value) {
      this.form.errorDescription = value;
      this.showPicker = false;
    },
    dateFormat(date,fmt) {
      let o = {
        "M+" : date.getMonth()+1,                 //月份
        "d+" : date.getDate(),                    //日
        "h+" : date.getHours(),                   //小时
        "m+" : date.getMinutes(),                 //分
        "s+" : date.getSeconds(),                 //秒
        "q+" : Math.floor((date.getMonth()+3)/3), //季度
        "S" : date.getMilliseconds()             //毫秒
      };
      if(/(y+)/.test(fmt)) {
        fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));
      }
      for(let k in o) {
        if(new RegExp("("+ k +")").test(fmt)){
          fmt = fmt.replace(RegExp.$1, (RegExp.$1.length===1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
      }
      return fmt;
    }
  }
};
</script>
