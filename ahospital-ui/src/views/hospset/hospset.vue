<template>
  <div class="app-container">

    <el-card>

      <el-card>
        <div>
          <!--搜索表单区域-->
          <el-form ref="form" :model="queryHospForm" :inline="true" size="mini">
            <el-form-item label="医院名称">
              <el-input v-model="queryHospForm.hosName" placeholder="请输入医院名称" size="mini"></el-input>
            </el-form-item>
            <el-form-item label="医院编号">
              <el-input v-model="queryHospForm.hosCode" placeholder="请输入医院编号" size="mini"></el-input>
            </el-form-item>

            <el-button type="success" @click="addPage" size="mini">新增</el-button>
            <el-button type="warning" @click="editPage" size="mini">修改</el-button>
            <el-button type="primary" @click="getHospList" size="mini">查询</el-button>
            <el-button type="danger" @click="removeHosp" size="mini">批量删除</el-button>
          </el-form>
        </div>
        <!--end 搜索表单区域-->
      </el-card>

      <!--table 表单区域-->
      <div>
        <el-table :data="hospTable" highlight-current-row  @fit="true"
                  style="width: 100%;height: 100%;margin-top: 10px" @selection-change="selectionClick" size="mini">
          <el-table-column type="selection" width="55"></el-table-column>
          <el-table-column type="index" label="编号"></el-table-column>
          <el-table-column prop="hosName" label="医院名称" show-overflow-tooltip></el-table-column>
          <el-table-column prop="hosCode" label="医院编号" show-overflow-tooltip></el-table-column>
          <el-table-column prop="apiUrl" label="api基础路径" show-overflow-tooltip></el-table-column>
          <el-table-column prop="contactsName" label="联系人" show-overflow-tooltip></el-table-column>
          <el-table-column prop="contactsPhone" label="联系人手机" show-overflow-tooltip></el-table-column>
          <el-table-column prop="status" label="状态" width="60">
            <template slot-scope="scope">
              <el-tag v-if="scope.row.status === 0" type="success" size="mini">正常</el-tag>
              <el-tag v-if="scope.row.status === 1" type="danger" size="mini">锁定</el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="createTime" label="创建时间" show-overflow-tooltip></el-table-column>
          <el-table-column prop="updateTime" label="更新时间" show-overflow-tooltip></el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button size="mini" type="danger" @click="removeHospById(scope.row.id)">删除</el-button>
              <el-button v-if="scope.row.status === 1" size="mini" type="success" @click="updateHosp(scope.row.id,0)">
                取消锁定
              </el-button>
              <el-button v-else size="mini" type="info" @click="updateHosp(scope.row.id,1)">锁定</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <!--end table 表单区域-->

      <!--page 插件区域-->
      <div style="margin-top: 10px;text-align: right;">
        <el-pagination
          @size-change="pageClick"
          @current-change="pageJump"
          :total="total"
          :current-page="queryHospForm.page"
          :page-sizes="[10, 20, 50, 100]"
          :page-size="queryHospForm.pageSize"
          layout="total, sizes, prev, pager, next, jumper">
        </el-pagination>
      </div>
    </el-card>
    <!--end page 插件区域-->

    <!--弹框-新增页面-->
    <el-dialog title="新增医院设置" :visible.sync="addVisible" width="70%">
      <el-card>
        <el-form :model="addHospForm" :rules="rules" ref="addHospRulesForm" class="demo-ruleForm">
          <el-form-item label="医院名称" prop="hosName">
            <el-input v-model="addHospForm.hosName"></el-input>
          </el-form-item>
          <el-form-item label="医院编号" prop="hosCode">
            <el-input v-model="addHospForm.hosCode"></el-input>
          </el-form-item>
          <el-form-item label="医院api基础路径" prop="apiUrl">
            <el-input v-model="addHospForm.apiUrl"></el-input>
          </el-form-item>
          <el-form-item label="联系人姓名" prop="contactsName">
            <el-input v-model="addHospForm.contactsName"></el-input>
          </el-form-item>
          <el-form-item label="联系人手机号" prop="contactsPhone">
            <el-input v-model="addHospForm.contactsPhone"></el-input>
          </el-form-item>
        </el-form>
        <el-button type="primary" @click="addHosp">确 定</el-button>
        <el-button @click="addVisible = false">取 消</el-button>
      </el-card>
    </el-dialog>
    <!--end 弹框-新增页面-->

    <!--弹框-修改页面-->
    <el-dialog title="修改医院设置" :visible.sync="editVisible" width="70%">
      <el-card>
        <el-form :model="editForm" status-icon :rules="rules" ref="form" class="demo-ruleForm">
          <el-form-item label="医院名称" prop="hosName">
            <el-input v-model="editForm.hosName"></el-input>
          </el-form-item>
          <el-form-item label="医院编号" prop="hosCode">
            <el-input v-model="editForm.hosCode"></el-input>
          </el-form-item>
          <el-form-item label="医院api基础路径" prop="apiUrl">
            <el-input v-model="editForm.apiUrl"></el-input>
          </el-form-item>
          <el-form-item label="联系人姓名" prop="contactsName">
            <el-input v-model="editForm.contactsName"></el-input>
          </el-form-item>
          <el-form-item label="联系人手机号" prop="contactsPhone">
            <el-input v-model="editForm.contactsPhone"></el-input>
          </el-form-item>
        </el-form>
        <el-button type="primary" @click="editHosp">确 定</el-button>
        <el-button @click="editVisible = false">取 消</el-button>
      </el-card>
    </el-dialog>
    <!--end 弹框-新增页面-->
  </div>
</template>

<script>
import hospset from '@/api/hospset'

export default {
  data() {
    return {
      editForm: {},
      addHospForm: {
        hosName: '',
        hosCode: '',
        apiUrl: '',
        contactsName: '',
        contactsPhone: '',
      },
      /**
       * 表单校验规则 通过在表单上绑定 rules prop绑定对应名称,完成校验
       */
      rules: {
        hosName: [
          {required: true, message: '请输入医院名称', trigger: 'blur'},
        ],
        hosCode: [
          {required: true, message: '请输入医院编号', trigger: 'blur'},
        ],
        apiUrl: [
          {required: true, message: '请输入医院api基础路径', trigger: 'blur'},
        ],
        contactsName: [
          {required: true, message: '请输入联系人姓名', trigger: 'blur'},
        ],
        contactsPhone: [
          {required: true, message: '请输入联系人手机号', trigger: 'blur'},
          {min: 11, message: '请输入11位联系人手机号', trigger: 'blur'}
        ]
      },
      queryHospForm: {
        ids: [],
        page: 1,
        pageSize: 10,
        hosName: '',
        hosCode: '',
      },
      editVisible: false,
      addVisible: false,
      hospTable: [],      // 用于接收后端请求返回的table数据
      total: 0,   // 分页条数
    }
  }, created() {
    this.getHospList()
  }, methods: {
    getHospList() {
      hospset.getHospsetList(this.queryHospForm).then(result => {
        this.total = result.total
        this.hospTable = result.objects
      })
    },
    /**
     * 页数点击事件
     * @param pageSize
     */
    pageClick(pageSize) {
      console.log(pageSize)
      this.queryHospForm.pageSize = pageSize;
      this.getHospList();
    },
    /**
     * 页数跳转事件
     * @param page
     */
    pageJump(page) {
      console.log(page)
      this.queryHospForm.page = page;
      this.getHospList();
    },
    /**
     * 表单选择事件,通过 <el-table-column type="selection" width="55"></el-table-column> 在表单前生成一个选择框
     * 并通过 @selection-change="selectionClick" 在表单头绑定
     * @param val 选中的值为 array【】
     */
    selectionClick(val) {
      this.ids = [];
      for (let i = 0; i < val.length; i++) {
        this.ids.push(val[i].id);
      }
    },
    /**
     * 根据id删除事件
     * @param val id
     */
    removeHospById: function (val) {
      this.$confirm('此操作将删除选中的数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        hospset.removeHospsetById(val).then(result => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
          this.getHospList();
        })
      })
    },
    /**
     * 更新 Hospiter
     * @param val
     */
    updateHosp(val, status) {
      let params = {
        id: val,
        status: status
      }
      hospset.updateHospset(params).then(result => {
        this.$message({
          type: 'success',
          message: '修改成功!'
        });
        this.getHospList();
      })
    },
    /**
     * 批量删除点击事件
     */
    removeHosp() {
      this.$confirm('此操作将删除选中的数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        hospset.removeHospsetByIds(this.ids).then(result => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
          this.getHospList();
        })
      })
    },
    /**
     * 新增弹框事件
     */
    addPage() {
      this.addVisible = true
    },
    /**
     * 新增保存事件
     */
    addHosp() {
      hospset.addHospset(this.addHospForm).then(result => {
        this.addVisible = false;
        this.$message({
          type: 'success',
          message: '新增成功!'
        });
        this.addHospForm = {};
        this.$refs['addHospRulesForm'].resetFields();   // 重置表单校验
        this.getHospList();
      })
    },
    /**
     * 修改点击事件
     */
    editPage() {
      if (this.ids.length > 1) {
        this.$message({
          type: 'warning',
          message: '只能选中单个的数据进行修改!'
        });
      } else if (this.ids.length == 0) {
        this.$message({
          type: 'warning',
          message: '当前没有选中需要修改的数据!'
        });
      } else {
        let id = this.ids[0];
        hospset.queryHospById(id).then(result => {
          this.editVisible = true;
          this.editForm = result.object;
        })
      }
    },
    /**
     * 修改确定事件
     */
    editHosp() {
      this.$refs.form.validate((vaild) => {
        if (vaild) {
          hospset.updateHospset(this.editForm).then(result => {
            this.editVisible = false;
            this.$refs['form'].clearValidate();   // 重置表单校验
            this.getHospList();
          })
        }
      })

    },
  }
}
</script>
