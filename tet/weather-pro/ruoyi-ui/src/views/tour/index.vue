<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="时间">
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
<!--      <el-form-item label="路线" prop="dutyOfficer">
        <el-input
          v-model="queryParams.dutyOfficer"
          placeholder="请输入"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>-->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button type="warning" icon="el-icon-download"  size="mini" @click="handleExport">导出</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['situation:situation:add']"-->
<!--        >新增</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['situation:situation:edit']"-->
<!--        >修改</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="danger"-->
<!--          plain-->
<!--          icon="el-icon-delete"-->
<!--          size="mini"-->
<!--          :disabled="multiple"-->
<!--          @click="handleDelete"-->
<!--          v-hasPermi="['situation:situation:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['situation:situation:export']"
        >导出</el-button>
      </el-col>-->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="situationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="时间" align="center" prop="recordTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.recordTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="值班人员" align="center" prop="dutyOfficer" />
      <el-table-column label="降雨情况" align="center" prop="rainfall" />
      <el-table-column label="报警等级" align="center" prop="alarmLevel" />
      <el-table-column label="巡查开始时间" align="center" prop="inspectionTimeStart" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.inspectionTimeStart, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="巡查结束时间" align="center" prop="inspectionTimeEnd" />
      <el-table-column label="巡查地段" align="center" prop="inspectionLot" />
      <el-table-column label="巡查方式" align="center" prop="inspectionType" />
      <el-table-column label="巡查人数、车辆" align="center" prop="inspectorsVehicles" />
      <el-table-column label="巡查隐患情况" align="center" prop="inspectionHiddenDangers" />
      <el-table-column label="处置措施" align="center" prop="disposalMeasures" />
      <el-table-column label="创建人" align="center" prop="createUser" />
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改防洪值班记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="时间" prop="recordTime">
          <el-date-picker clearable size="small"
            v-model="form.recordTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="值班人员" prop="dutyOfficer">
          <el-input v-model="form.dutyOfficer" placeholder="请输入值班人员" />
        </el-form-item>
        <el-form-item label="降雨情况" prop="rainfall">
          <el-input v-model="form.rainfall" placeholder="请输入降雨情况" />
        </el-form-item>
        <el-form-item label="报警等级" prop="alarmLevel">
          <el-input v-model="form.alarmLevel" placeholder="请输入报警等级" />
        </el-form-item>
        <el-form-item label="巡查开始时间" prop="inspectionTimeStart">
          <el-date-picker clearable size="small"
            v-model="form.inspectionTimeStart"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择巡查开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="巡查结束时间" prop="inspectionTimeEnd">
          <el-input v-model="form.inspectionTimeEnd" placeholder="请输入巡查结束时间" />
        </el-form-item>
        <el-form-item label="巡查地段" prop="inspectionLot">
          <el-input v-model="form.inspectionLot" placeholder="请输入巡查地段" />
        </el-form-item>
        <el-form-item label="巡查方式" prop="inspectionType">
          <el-select v-model="form.inspectionType" placeholder="请选择巡查方式">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="巡查人数、车辆" prop="inspectorsVehicles">
          <el-input v-model="form.inspectorsVehicles" placeholder="请输入巡查人数、车辆" />
        </el-form-item>
        <el-form-item label="巡查隐患情况" prop="inspectionHiddenDangers">
          <el-input v-model="form.inspectionHiddenDangers" placeholder="请输入巡查隐患情况" />
        </el-form-item>
        <el-form-item label="处置措施" prop="disposalMeasures">
          <el-input v-model="form.disposalMeasures" placeholder="请输入处置措施" />
        </el-form-item>
        <el-form-item label="创建人" prop="createUser">
          <el-input v-model="form.createUser" placeholder="请输入创建人" />
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
import { listSituation, getSituation, delSituation, addSituation, updateSituation, exportSituation } from "@/api/situation/situation";

export default {
  name: "Situation",
  components: {
  },
  data() {
    return {
      dateRange:null,
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
      // 防洪值班记录表格数据
      situationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        recordTime: null,
        dutyOfficer: null,
        rainfall: null,
        alarmLevel: null,
        inspectionTimeStart: null,
        inspectionTimeEnd: null,
        inspectionLot: null,
        inspectionType: null,
        inspectorsVehicles: null,
        inspectionHiddenDangers: null,
        disposalMeasures: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        recordTime: [
          { required: true, message: "时间不能为空", trigger: "blur" }
        ],
        createUser: [
          { required: true, message: "创建人不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询防洪值班记录列表 */
    getList() {
      this.loading = true;
      listSituation(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.situationList = response.rows;
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
        recordTime: null,
        dutyOfficer: null,
        rainfall: null,
        alarmLevel: null,
        inspectionTimeStart: null,
        inspectionTimeEnd: null,
        inspectionLot: null,
        inspectionType: null,
        inspectorsVehicles: null,
        inspectionHiddenDangers: null,
        disposalMeasures: null,
        createUser: null,
        createTime: null,
        updateTime: null
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
      this.dateRange = null;
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
      this.title = "添加防洪值班记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getSituation(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改防洪值班记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateSituation(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSituation(this.form).then(response => {
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
      this.$confirm('是否确认删除防洪值班记录编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delSituation(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('确认导出?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportSituation(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
