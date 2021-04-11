<!-- 维保上报-移动端 -->
<template>
  <div class="app">
    <van-nav-bar title="维保上报" />
    <van-cell-group>
      <van-cell title="发布时间" value="2020-12-8 5时" />
      <van-cell title="警戒级别" value="封锁警戒" />
      <van-cell title="警戒工区" value="XXXXXXX" />
      <van-cell title="警戒降水" value="1小时降水40mm" />
    </van-cell-group>
    <van-form @submit="onSubmit">
      <van-field
        :value="form.errorDescription"
        readonly
        clickable
        label="错误描述"
        placeholder="请选择"
        @click="showPicker = true"
      />
      <van-field
        v-model="form.remark"
        rows="2"
        :autosize="true"
        label="备注"
        type="textarea"
        maxlength="50"
        placeholder="请输入备注"
        show-word-limit
        :rules="[{ required: form.errorDescription === '其他', message: '错误描述为其他时必须填写！' }]"
      />
<!--      :rules="[{ required: true, message: '请填写密码' }]"-->
      <div style="margin: 16px;">
        <van-button
          :loading="buttonLoading"
          loading-text="提交中..."
          round
          block
          :disabled="!form.errorDescription||buttonLoading"
          type="info"
          native-type="submit"
        >提交</van-button>
      </div>
    </van-form>

    <van-popup v-model="showPicker" round position="bottom">
      <van-picker
        show-toolbar
        :columns="alarmTypes"
        @cancel="showPicker = false"
        @confirm="onConfirm"
      />
    </van-popup>
  </div>
</template>

<script>
import { updateReported } from "@/api/reported/reported";
import { vantComponents } from "@/views/h5/vantComponents";

export default {
  name: "ReportedForm",
  components: {
    ...vantComponents()
  },
  data() {
    return {
      form:{
        id:1,
        errorDescription:'',
        remark:null,
      },
      // 遮罩层
      loading: true,
      buttonLoading: false,
      value: '',
      showPicker: false,
      alarmTypes:['工区无降水', '降水实际偏小', '降水实际偏小', '其他']
    };
  },
  async created() {

  },
  computed:{},
  methods: {
    async onSubmit() {
      this.buttonLoading = true
      await updateReported(this.form);
      this.buttonLoading = false
      Toast.success('提交成功');

    },
    onConfirm(value) {
      this.form.errorDescription = value;
      this.showPicker = false;
    },
  }
};
</script>
