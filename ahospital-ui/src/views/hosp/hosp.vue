<template>
  <div class="app-container">

    <el-card>

      <el-card>
        <div>
          <!--搜索表单区域-->
          <el-form :inline="true" class="demo-form-inline">
            <el-form-item>
              <el-select
                v-model="queryHospForm.provinceCode"
                placeholder="请选择省"
                @change="provinceChanged">
                <el-option
                  v-for="item in province"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"/>
              </el-select>
            </el-form-item>

            <el-form-item>
              <el-select
                v-model="queryHospForm.cityCode"
                placeholder="请选择市">
                <el-option
                  v-for="item in cityList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"/>
              </el-select>
            </el-form-item>

            <el-form-item>
              <el-input v-model="queryHospForm.hosname" placeholder="医院名称"/>
            </el-form-item>

            <el-button type="primary" icon="el-icon-search" @click="getHospList()">查询</el-button>
            <el-button type="default" @click="resetData()">清空</el-button>
          </el-form>
        </div>
        <!--end 搜索表单区域-->
      </el-card>

      <!--table 表单区域-->
      <div>
        <el-table v-loading="listLoading" :data="hospList" border fit highlight-current-row>
          <el-table-column
            label="序号"
            width="60"
            align="center">
            <template slot-scope="scope">
              {{ (queryHospForm.page - 1) * queryHospForm.pageSize + scope.$index + 1 }}
            </template>
          </el-table-column>

          <el-table-column label="医院logo">
            <template slot-scope="scope">
              <img :src="'data:image/jpeg;base64,'+scope.row.logoData" width="80">
            </template>
          </el-table-column>

          <el-table-column prop="hosname" label="医院名称"/>
          <el-table-column prop="param.hostypeString" label="等级" width="90"/>
          <el-table-column prop="param.fullAddress" label="详情地址"/>
          <el-table-column label="状态" width="80">
            <template slot-scope="scope">
              {{ scope.row.status === 0 ? '未上线' : '已上线' }}
            </template>
          </el-table-column>
          <el-table-column prop="createTime" label="创建时间"/>

          <el-table-column label="操作" width="230" align="center">
            <template slot-scope="scope">
              <router-link :to="'/hospset/hospital/show/'+scope.row.id">
                <el-button type="primary" size="mini">查看</el-button>
              </router-link>
              <router-link :to="'/hospset/hospital/schedule/'+scope.row.hoscode">
                <el-button type="primary" size="mini">排班</el-button>
              </router-link>

              <el-button v-if="scope.row.status == 1"  type="primary" size="mini" @click="updateStatus(scope.row.id, 0)">下线</el-button>
              <el-button v-if="scope.row.status == 0"  type="danger" size="mini" @click="updateStatus(scope.row.id, 1)">上线</el-button>
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

  </div>
</template>

<script>
import hosp from '@/api/hosp'

export default {
  data() {
    return {
      listLoading:true,
      province:[],
      queryHospForm: {
        hosname:"",
        provinceCode:"",
        cityCode:"",
        page: 1,
        pageSize: 10,
      },
      cityList : [],
      hospList: [],      // 用于接收后端请求返回的table数据
      total: 0,   // 分页条数
    }
  }, created() {
    this.getHospList()
    this.findAllProvince()
  }, methods: {
    getHospList() {
      hosp.getHospList(this.queryHospForm).then(result => {
        //每页数据集合
        this.hospList = result.object.object.content
        //总记录数
        this.total = result.object.object.totalElements
        //加载图表不显示
        this.listLoading = false

      })
    },
    /**
     * 页数点击事件
     * @param pageSize
     */
    pageClick(pageSize) {
      this.queryHospForm.pageSize = pageSize;
      this.getHospList();
    },
    /**
     * 页数跳转事件
     * @param page
     */
    pageJump(page) {
      this.queryHospForm.page = page;
      this.getHospList();
    },
    //查询所有省
    findAllProvince() {
      hosp.getDictByDictCode('Province')
        .then(response => {
          this.province = response.object
        })
    },
    //点击某个省，显示里面市（联动）
    provinceChanged() {
      //初始化值
      this.cityList = []
      this.queryHospForm.cityCode = ''
      //调用方法，根据省id，查询下面子节点
      hosp.queryByParentId(this.queryHospForm.provinceCode)
        .then(response => {
          this.cityList = response.object
        })
    },
    resetData(){
      this.queryHospForm.hosname = "";
      this.queryHospForm.provinceCode = "";
      this.queryHospForm.cityCode = "";
    },
    //更新医院上线状态
    updateStatus(id,status) {
      let data = {id:id,status:status};
      hosp.updateStatus(data)
        .then(response => {
          //刷新页面
          this.getHospList();
        })
    },
  }
}
</script>
