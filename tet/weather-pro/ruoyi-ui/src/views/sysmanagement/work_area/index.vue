<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="工区名" prop="workAreaName">
        <el-input
          v-model="queryParams.workAreaName"
          placeholder="请输入工区名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="路线" prop="line">
        <el-select v-model="queryParams.line" placeholder="请选择路线" clearable size="small">
          <el-option
            v-for="dict in lineOptions"
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
<!--      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['weather:work_area:export']"
        >导出</el-button>
      </el-col> -->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="work_areaList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="工区名" align="center" prop="workAreaName"/>
      <el-table-column label="起点里程" align="center" prop="startingPoint" />
      <el-table-column label="终点里程" align="center" prop="terminus" />
      <el-table-column label="中心里程数" align="center" prop="centre" />
      <el-table-column label="路线" align="center" prop="line" :formatter="lineFormat" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-light-rain"
            @click="bindingRainfallStation(scope.row)"
          >绑定雨量站</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >编辑</el-button>
          <el-button
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

    <!-- 添加或修改工区信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body :close-on-click-modal="false">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="工区名" prop="workAreaName">
          <el-input v-model="form.workAreaName" placeholder="请输入工区名" />
        </el-form-item>
        <el-form-item label="起点里程" prop="startingPoint">
          <el-input v-model="form.startingPoint" placeholder="请输入起点里程" />
        </el-form-item>
        <el-form-item label="终点里程" prop="terminus">
          <el-input v-model="form.terminus" placeholder="请输入终点里程" />
        </el-form-item>
        <el-form-item label="中心里程数" prop="centre" >
          <el-input v-model="form.centre" placeholder="请输入中心里程数" />
        </el-form-item>
        <el-form-item label="路线" prop="line">
          <el-select v-model="form.line" placeholder="请选择路线">
            <el-option
              v-for="dict in lineOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 对应工区id的雨量站 -->
    <el-dialog :title="titleRainfallStations" :visible.sync="openRainfallStations" width="800px" append-to-body :close-on-click-modal="false">
        <RainfallStation :id="workAreaId" v-if="openRainfallStations"></RainfallStation>
    </el-dialog>
  </div>
</template>

<script>
import { listWork_area, getWork_area, delWork_area, addWork_area, updateWork_area, exportWork_area } from "@/api/sysmanagement/work_area";
import RainfallStation from "@/views/sysmanagement/rainfall_station/index";
export default {
  name: "Work_area",
  components: {
     RainfallStation
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
      // 工区信息表格数据
      work_areaList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 路线字典
      lineOptions: [],
      //对应工区id的雨量站信息
      tRainfallStations:[],
      //对应工区详情的弹窗标题
      titleRainfallStations:null,
      //是否开启对应工区详情弹窗
      openRainfallStations:false,
      //当前工区id
      workAreaId:null,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        workAreaName: null,
        line: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        workAreaName: [
          { required: true, message: "工区名不能为空", trigger: "blur" }
        ],
        centre: [
          { required: true, message: "中心里程数不能为空", trigger: "blur" }
        ],
        line: [
          { required: true, message: "路线不能为空", trigger: "change" }
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("rail_line_type").then(response => {
      this.lineOptions = response.data;
    });
  },
  methods: {
    /** 查询工区信息列表 */
    getList() {
      this.loading = true;
      listWork_area(this.queryParams).then(response => {
        this.work_areaList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 路线字典翻译
    lineFormat(row, column) {
      return this.selectDictLabel(this.lineOptions, row.line);
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
        workAreaName: null,
        startingPoint: null,
        terminus: null,
        centre: null,
        line: null
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
      this.title = "添加工区信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getWork_area(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改工区信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateWork_area(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addWork_area(this.form).then(response => {
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
      this.$confirm('是否确认删除工区信息编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delWork_area(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有工区信息数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportWork_area(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    },
    //根据工区id获取对应雨量站id
    async bindingRainfallStation(row){
     let rainfallStations = await getWork_area(row.id);
     if(rainfallStations.data != undefined){
        this.tRainfallStations = rainfallStations.data.tRainfallStations;
     }else{
        console.log("没有对应的雨量站数据");
     }
     this.workAreaId = row.id;
     this.titleRainfallStations = row.workAreaName+"雨量站信息";
     this.openRainfallStations = true;
    }
  }
};
</script>
