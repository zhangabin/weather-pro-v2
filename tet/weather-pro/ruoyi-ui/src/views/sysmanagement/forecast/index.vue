<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
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
      <el-form-item label="上传时间">
        <el-date-picker
          v-model="daterangeUploadTime"
          size="small"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="状态" prop="state">
        <el-select v-model="queryParams.state" placeholder="请选择状态" clearable size="small">
          <el-option
            v-for="dict in stateOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
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
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-light-rain"
          size="mini"
          @click="fourForecast"
        >四日城镇预报</el-button>
      </el-col>
<!--      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
        >导出</el-button>
      </el-col> -->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="forecastList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="标题" align="center" prop="title" />
      <el-table-column label="上传人" align="center" prop="uploadUser" />
      <el-table-column label="上传时间" align="center" prop="uploadTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.uploadTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="state" :formatter="stateFormat" />
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
            v-if="scope.row.state == '2'"
            size="mini"
            type="text"
            icon="el-icon-finished"
            @click="handleWithdraw(scope.row)"
          >撤回</el-button>
          <el-button
            v-if="scope.row.state !== '2'"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >编辑</el-button>
          <el-button
            v-if="scope.row.state !== '2'"
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          >删除</el-button>
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

    <!-- 添加或修改趋势预报管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="900px" append-to-body :close-on-click-modal="false">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入标题" maxlength="50"  show-word-limit />
        </el-form-item>
        <el-form-item label="24小时预报" prop="forecast24">
          <editor v-model="form.forecast24" :min-height="192"/>
        </el-form-item>
        <el-form-item label="48-96小时预报" prop="forecast96">
          <editor v-model="form.forecast96" :min-height="192"/>
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
import { listForecast, getForecast, delForecast, addForecast, updateForecast, exportForecast } from "@/api/sysmanagement/forecast";
import Editor from '@/components/Editor';

export default {
  name: "Forecast",
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
      // 趋势预报管理表格数据
      forecastList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 上传时间时间范围
      daterangeUploadTime: [],
      // 状态(待发布、已发布、已撤回)字典
      stateOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        uploadUser: null,
        uploadTime: null,
        state: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [
          { required: true, message: "标题不能为空", trigger: "blur" }
        ],
        forecast24: [
          { required: true, message: "24小时预报不能为空", trigger: "blur" }
        ],
        forecast96: [
          { required: true, message: "48-96小时预报不能为空", trigger: "blur"}
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("publish_state").then(response => {
      this.stateOptions = response.data;
    });
  },
  methods: {
    /** 查询趋势预报管理列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeUploadTime && '' != this.daterangeUploadTime) {
        this.queryParams.params["beginUploadTime"] = this.daterangeUploadTime[0];
        this.queryParams.params["endUploadTime"] = this.daterangeUploadTime[1];
      }
      listForecast(this.queryParams).then(response => {
        this.forecastList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 状态字典翻译
    stateFormat(row, column) {
      return this.selectDictLabel(this.stateOptions, row.state);
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
        uploadUser: null,
        uploadTime: null,
        forecast24: null,
        forecast96: null,
        state: null,
        publishTime: null
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
      this.daterangeUploadTime = [];
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
      this.reset(); //这个只能放在这里，放在下面会导致数据必要的数据被清空
      this.form.forecast96 = this.forecastList[0].forecast96DefaultValue
      this.open = true;
      this.title = "添加趋势预报管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getForecast(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改趋势预报管理";
      });
    },
    /** 发布按钮操作 */
    async handleRelease(row){
      await updateForecast({id:row.id,state:2})
      this.msgSuccess("已发布");
      this.getList();
    },
    /** 撤回按钮操作 */
    async handleWithdraw(row){
      await updateForecast({id:row.id,state:3})
      this.msgSuccess("已撤回");
      this.getList();
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateForecast(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addForecast(this.form).then(response => {
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
      this.$confirm('是否确认删除趋势预报管理编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delForecast(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有趋势预报管理数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportForecast(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    },
    fourForecast(){
      console.log("进入四日城镇预报方法")
    }
  }
};
</script>
