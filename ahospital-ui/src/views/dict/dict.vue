<template>
  <div class="app-container">

    <el-card>
      <el-card>
        <div>
            <el-button type="success" @click="exportDict" size="mini">导出</el-button>
        </div>
      </el-card>

      <!--table 表单区域-->
      <div>
        <el-table :data="dictTable" highlight-current-row @fit="true" row-key="id" border lazy :load="getDictChildren"
                  style="width: 100%;height: 100%;margin-top: 10px" size="mini" :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
          <el-table-column prop="name" label="名称"></el-table-column>
          <el-table-column prop="dictCode" label="编码"></el-table-column>
          <el-table-column prop="value" label="值"></el-table-column>
          <el-table-column prop="createTime" label="创建时间"></el-table-column>

        </el-table>
      </div>
      <!--end table 表单区域-->

    </el-card>
  </div>
</template>

<script>
import dict from '@/api/dict'

export default {
  data() {
    return {
      dictTable: []
    }
  }, created() {
    this.getDictList()
  }, methods: {
    getDictList() {
      dict.queryDictByParentId(1).then(result => {
        this.dictTable = result.object;
      })
    },
    /**
     * 获取dict子节点查询
     */
    getDictChildren(tree, treeNode, resolve) {
      dict.queryDictByParentId(tree.id).then(result => {
        resolve(result.object);
      })
    },
    exportDict(){
      window.location.href="http://localhost:9001/cmn/dict/export"
    }
  }
}
</script>
