<template>
  <div class="app-container">
    <el-container style="width: 100%;height: 100%; border: 1px solid #eee;">
      <el-aside width="25%" >
        <el-table
          @row-click="setContent"
          border="true"
          height="100%"
          style="width: 100%;height:100%" :data="tImportantWeatherPromptList">
          <el-table-column label="历史产品选择" prop="title" />
        </el-table>
      </el-aside>

      <el-container v-if="tImportantWeatherPromptList">
        <el-header style="height: 55px;text-align:center; border: 1px solid #eee;">
          <h2>{{title}} {{title?"详情":""}}</h2>
        </el-header>
        <el-main v-html="content"></el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import { listTImportantWeatherPrompt } from "@/api/prompt/tImportantWeatherPrompt";
import Editor from '@/components/Editor';

export default {
  name: "TImportantWeatherPrompt",
  components: {
    Editor,
  },
  data() {
    return {
      title:'',
      content:'',
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 重要天气提示表格数据
      tImportantWeatherPromptList: [],
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        title: null,
        content: null,
        uploadTime: null,
        uploadUser: null,
        state: "2"
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [
          { required: true, message: "标题不能为空", trigger: "blur" }
        ],
        content: [
          { required: true, message: "内容不能为空", trigger: "blur" }
        ],
      },
      // 状态(待发布、已发布、已撤回)字典
      stateOptions: [],
      // 日期范围
      dateRange: "",
    };
  },
  created() {
    this.getList();
    this.getDicts("publish_state").then(response => {
      this.stateOptions = response.data;
    });
  },
  methods: {
    /** 查询重要天气提示列表 */
    getList() {
      this.loading = true;
      let addDateRange = this.addDateRange(this.queryParams, this.dateRange);
      listTImportantWeatherPrompt(addDateRange).then(response => {
        this.tImportantWeatherPromptList = response.rows;
        this.total = response.total;
        this.loading = false;
        if (this.tImportantWeatherPromptList) {
          let item = this.tImportantWeatherPromptList[0];
          this.title = item.title;
          this.content = item.content;
        }
      });
    },
    stateFormat(row) {
      return this.selectDictLabel(this.stateOptions, row.state);
    },
    setContent(row){
      console.log(row)
      this.title = row.title;
      this.content = row.content;
    }
  }
};
</script>
<style scoped >
/deep/ aside {
  padding: 0;
  margin-bottom: 0;
}
.app-container {
  position: absolute;
  height: 100%;
  width: 100%;
  padding: 0;
}

::-webkit-scrollbar
{
  width: 4px;
  height: 10px;
}
/*滑动轨道*/
::-webkit-scrollbar-track
{
  border-radius: 0px;
  background: none;
}
/*滑块*/
::-webkit-scrollbar-thumb
{
  border-radius: 5px;
  -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,.2);
  background-color: #8b8b8b
}
/*滑块效果*/
::-webkit-scrollbar-thumb:hover
{
  border-radius: 5px;
  -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,.2);
  background-color: rgba(85, 85, 85, 0.4);
}
</style>
