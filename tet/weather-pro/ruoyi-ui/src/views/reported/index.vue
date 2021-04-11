<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="上报时间" prop="reportedTime">
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
      <el-form-item label="上报人" prop="reportedUser">
        <el-input
          v-model="queryParams.reportedUser"
          placeholder="请输入上报人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="告警类型" prop="alarmType">
        <el-select v-model="queryParams.alarmType" placeholder="请选择告警类型" clearable size="small">
          <el-option v-for="item in alarmTypes" :key="item.id" :label="item.alarmType" :value="item.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="state">
        <el-select v-model="queryParams.state" placeholder="请选择状态" clearable size="small">
          <el-option label="待通知" value="1" />
          <el-option label="已通知" value="2" />
          <el-option label="已恢复" value="3" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8" v-if="false">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['reported:reported:add']"
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
          v-hasPermi="['reported:reported:edit']"
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
          v-hasPermi="['reported:reported:remove']"
        >删除</el-button>
      </el-col>

      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="reportedList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" v-if="false" />
      <el-table-column label="序号" type="index" width="50px" align="center">
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="告警类型" align="center" prop="alarmType" >
        <template slot-scope="{row}">
          {{getAlarmTypeStr(row.alarmType)}}
        </template>
      </el-table-column>
      <el-table-column label="上报时间" align="center" prop="reportedTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.reportedTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="上报人" align="center" prop="reportedUser" />
      <el-table-column label="告警内容" align="center" prop="alarmContent" />
      <el-table-column label="错误描述" align="center" prop="errorDescription" />
      <el-table-column label="状态" align="center" prop="state" :formatter="stateFormat"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleDetails(scope.row)"
            v-hasPermi="['reported:reported:edit']"
          >详情</el-button>
          <el-button
            v-if="scope.row.state !== '2'"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate({id:scope.row.id,state:'2'})"
            v-hasPermi="['reported:reported:edit']"
          >通知</el-button>
          <el-button
            v-if="scope.row.state === '2'"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate({id:scope.row.id,state:'3'})"
            v-hasPermi="['reported:reported:edit']"
          >恢复</el-button>
          <el-button
            v-if="false"
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['reported:reported:remove']"
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

    <!-- 添加或修改维保上报对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="告警内容">
          <el-input v-model="form.alarmContent" type="textarea" placeholder="无" readonly />
        </el-form-item>
        <el-form-item label="错误描述" prop="errorDescription1">
          <el-input v-model="form.errorDescription" placeholder="无" readonly/>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="无" readonly/>
        </el-form-item>
        <el-form-item label="操作记录" prop="operatingRecord">
          <el-input v-model="form.operatingRecord" placeholder="无" readonly/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
<!--        <el-button type="primary" @click="submitForm">确 定</el-button>-->
        <el-button type="primary" @click="cancel">关 闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listReported, getReported, delReported, addReported, updateReported, exportReported } from "@/api/reported/reported";
import {getAlarmTypes} from '@/api/alarmType/alarmType';

export default {
  name: "Reported",
  components: {},
  data() {
    return {
      dateRange:undefined,
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
      // 维保上报表格数据
      reportedList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        alarmType: null,
        reportedTime: null,
        reportedUser: null,
        alarmContent: null,
        errorDescription: null,
        state: null,
        operatingRecord: null
      },
      form:{},
      stateOptions:[],
      alarmTypes:[],
    };
  },
  created() {
    this.getList();
    this.getDicts("notification_status").then(response => {
      this.stateOptions = response.data;
    });
    getAlarmTypes("0").then(response => {
      this.alarmTypes = response.data;
    });
  },
  computed:{},
  methods: {
    getAlarmTypeStr(id){
      for (const item of this.alarmTypes) {
        console.log(item)
        if(item.id === id){
          return item.alarmType;
        }
      }
      return '-';
    },
    /** 查询维保上报列表 */
    getList() {
      this.loading = true;
      listReported(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.reportedList = response.rows;
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
        alarmType: null,
        reportedTime: null,
        reportedUser: null,
        alarmContent: null,
        errorDescription: null,
        remark: null,
        state: null,
        operatingRecord: null
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
      this.dateRange = '';
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
      this.title = "添加维保上报";
    },
    /** 修改按钮操作 */
    async handleUpdate(data) {
      await updateReported(data);
      this.msgSuccess("操作成功");
      this.getList();
    },
    async handleDetails(row) {
      this.reset();
      const id = row.id || this.ids
      let response = await getReported(id);
      this.form = response.data;
      this.open = true;
      this.title = "维保上报详情";
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateReported(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addReported(this.form).then(response => {
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
      this.$confirm('是否确认删除维保上报编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delReported(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有维保上报数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportReported(queryParams);
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
