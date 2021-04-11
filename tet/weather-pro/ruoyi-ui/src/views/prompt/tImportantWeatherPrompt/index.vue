<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="上传时间">
        <el-date-picker
          v-model="dateRange"
          size="small"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="上传人" prop="uploadUser">
        <el-input
          v-model="queryParams.uploadUser"
          placeholder="请输入上传人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['prompt:tImportantWeatherPrompt:add']"
        >新增</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="tImportantWeatherPromptList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" type="index" width="50px" align="center">
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="标题" align="center" prop="title" />
<!--      <el-table-column label="内容" align="center" prop="content" />-->
      <el-table-column label="上传时间" align="center" prop="uploadTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.uploadTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="上传人" align="center" prop="uploadUser" />
      <el-table-column label="状态" align="center" prop="state" :formatter="stateFormat"  />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            v-if="scope.row.state !== '2'"
            size="mini"
            type="text"
            icon="el-icon-s-promotion"
            @click="handleRelease(scope.row)"
          >发布</el-button>
          <el-button
            v-if="scope.row.state !== '2'"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['prompt:tImportantWeatherPrompt:edit']"
          >编辑</el-button>
          <el-button
            v-if="scope.row.state !== '2'"
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['prompt:tImportantWeatherPrompt:remove']"
          >删除</el-button>
          <el-button
            v-if="scope.row.state === '2'"
            size="mini"
            type="text"
            icon="el-icon-finished"
            @click="handleWithdraw(scope.row)"
          >撤回</el-button>

        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改重要天气提示对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="900px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="内容" prop="content">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listTImportantWeatherPrompt, getTImportantWeatherPrompt, delTImportantWeatherPrompt, addTImportantWeatherPrompt, updateTImportantWeatherPrompt, exportTImportantWeatherPrompt } from "@/api/prompt/tImportantWeatherPrompt";
import Editor from '@/components/Editor';

export default {
  name: "TImportantWeatherPrompt",
  components: {
    Editor,
  },
  data() {
    return {
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
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        content: null,
        uploadTime: null,
        uploadUser: null,
        state: null
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

      listTImportantWeatherPrompt(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.tImportantWeatherPromptList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        title: null,
        content: null,
        uploadTime: null,
        uploadUser: null,
        state: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加重要天气提示";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getTImportantWeatherPrompt(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "编辑重要天气提示";
      });
    },
    /** 撤回按钮操作 */
    async handleWithdraw(row){
      await updateTImportantWeatherPrompt({id:row.id,state:3})
      this.msgSuccess("已撤回");
      this.getList();
    },
    /** 发布按钮操作 */
    async handleRelease(row){
      await updateTImportantWeatherPrompt({id:row.id,state:2})
      this.msgSuccess("已发布");
      this.getList();
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateTImportantWeatherPrompt(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTImportantWeatherPrompt(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除重要天气提示标题为"' + row.title + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delTImportantWeatherPrompt(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有重要天气提示数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportTImportantWeatherPrompt(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    },
    stateFormat(row, column) {
      return this.selectDictLabel(this.stateOptions, row.state);
    },
  }
};
</script>
