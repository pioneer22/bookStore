<template>
  <div class="app-container">
    <!-- $t is vue-i18n global function to translate lang -->
    <el-input v-model="filename" :placeholder="('输入导出Excel表名')" style="width:350px;" prefix-icon="el-icon-document" />
    <el-button :loading="downloadLoading" style="margin-bottom:10px" type="primary" icon="document" @click="handleDownload">
      {{ ('订单信息导出') }}
    </el-button>

    <div>
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="全部订单" name="1"></el-tab-pane>
        <el-tab-pane label="待收货" name="2"></el-tab-pane>
        <el-tab-pane label="退换货" name="3"></el-tab-pane>
        <el-tab-pane label="已完成订单" name="4"></el-tab-pane>
      </el-tabs>
    </div>

    <el-table
      v-loading="listLoading"
      ref="multipleTable"
      :data="list"
      :height="setHeight"
      :default-sort = "{prop: 'orderTime', order: 'descending'}"
      element-loading-text="拼命加载中"
      border
      fit
      highlight-current-row
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" fixed align="center" />
      <el-table-column label="订单号" prop="orderId" fixed sortable width="150">
        <template slot-scope="scope">
          {{ scope.row.orderId }}
        </template>
      </el-table-column>
      <el-table-column label="用户编号" width="150" align="center">
        <template slot-scope="scope">
          {{ scope.row.userId }}
        </template>
      </el-table-column>
      <el-table-column label="订单时间" prop="orderTime" sortable width="200" align="center">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span>{{ scope.row.orderTime | formatDate }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="订单总价" prop="orderPrice" sortable width="220">
        <template slot-scope="scope">
          ￥{{ scope.row.orderPrice }}
        </template>
      </el-table-column>
      <el-table-column label="联系人信息" width="250" align="center">
        <template slot-scope="scope">
          {{ scope.row.contactMess.contactName }}
          {{ scope.row.contactMess.contactPhone }}</span><br>
          <span>{{ scope.row.contactMess.contactAddress }}</span>
        </template>
      </el-table-column>
      <el-table-column label="订单信息" align="center" width="300">
        <template slot-scope="scope">
          <div v-for="it in scope.row.books" class="orderMess" :key="it">
            <span>{{ it.bookName }}</span>
            <span>￥{{ it.bookPrice }}X{{ it.bookNum }}</span>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="订单状态" align="center" width="150">
        <template slot-scope="scope">
          {{ scope.row.orderStatu }}
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { getOrderList } from '@/api/article'

export default {
  name: 'SelectExcel',
  data() {
    return {
      list: null,
      listLoading: true,
      multipleSelection: [],
      downloadLoading: false,
      filename: '',
      activeName: '1',
      all: [],
      waitGet: [],
      replace: [],
      complete: [],
      setHeight: ''
    }
  },
  created() {
    this._getHeight()
    this.fetchData()
  },
  methods: {
    async fetchData() {
      this.listLoading = true
      const result = await getOrderList()
      if (result.statu === 0) {
        this.list = result.data.all
        this.all = result.data.all
        this.waitGet = result.data.waitGet
        this.replace = result.data.replace
        this.complete = result.data.complete
        this.listLoading = false
      }
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    handleDownload() {
      if (this.multipleSelection.length) {
        this.downloadLoading = true
        import('@/vendor/Export2Excel').then(excel => {
          const tHeader = ['订单号', '用户编号', '订单时间', '订单总价', '联系人信息', '订单信息']
          const filterVal = ['orderId', 'userId', 'orderTime', 'orderPrice', 'contactMess', 'books']
          let list = this.multipleSelection
          list = JSON.parse(JSON.stringify(list)) // 深拷贝
          list = list.map(e => {
            let [book] = e.books
            for (let i = 0; i < e.books.length; i++) {
              e.books[i] = `${book.bookName} ￥${book.bookPrice}X${book.bookNum}\n`
            }
            let { contactMess } = e
            e.contactMess = `${contactMess.contactName} ${contactMess.contactPhone}\n ${contactMess.contactAddress}`
            return e
          })
          const data = this.formatJson(filterVal, list)
          excel.export_json_to_excel({
            header: tHeader,
            data,
            filename: this.filename
          })
          this.$refs.multipleTable.clearSelection()
          this.downloadLoading = false
          this.filename = ''
        })
      } else {
        this.$message({
          message: '至少选中一条数据',
          type: 'warning'
        })
      }
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => v[j]))
    },
    handleClick(tab, event) {
      switch (this.activeName) {
        case '1':
          this.list = this.all
          break
        case '2':
          this.list = this.waitGet
          break
        case '3':
          this.list = this.replace
          break
        case '4':
          this.list = this.complete
          break
      }
    },
    _getHeight() {
      const height = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight
      this.setHeight = height - 200 + 'px'
    }
  }
}
</script>

<style type="text/scss" rel="stylesheet/scss" scoped>
  .orderMess span{
    padding-right: 7px;
  }
</style>
