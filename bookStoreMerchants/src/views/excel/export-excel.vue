<template>
  <!-- $t is vue-i18n global function to translate lang -->
  <div class="app-container">
    <div>
      <FilenameOption v-model="filename" />
      <AutoWidthOption v-model="autoWidth" />
      <BookTypeOption v-model="bookType" />
      <el-button :loading="downloadLoading" style="margin:0 0 20px 20px;" type="primary" icon="document" @click="handleDownload">
        {{ ('导出Excel') }}
      </el-button>
    </div>

    <el-table
      v-loading="listLoading"
      :data="list"
      :height="setHeight"
      ref="multipleTable"
      element-loading-text="拼命加载中"
      border
      fit
      :default-sort = "{prop: 'bookId', order: 'ascending'}"
      highlight-current-row
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" fixed align="center" />
      <el-table-column label="图书Id" prop="bookId" width="110" fixed sortable align="center">
        <template slot-scope="scope">
          {{ scope.row.bookId }}
        </template>
      </el-table-column>
      <el-table-column label="图书图片" width="120" fixed align="center">
        <template slot-scope="scope">
          <img :src="scope.row.bookImg" style="max-height: 80px;max-width: 80px">
        </template>
      </el-table-column>
      <el-table-column label="作者" width="120" align="center">
        <template slot-scope="scope">
          {{ scope.row.bookAuthor }}
        </template>
      </el-table-column>
      <el-table-column label="图书名" width="200" align="center">
        <template slot-scope="scope">
          {{ scope.row.bookName }}
        </template>
      </el-table-column>
      <el-table-column label="出版社" width="150" align="center">
        <template slot-scope="scope">
          {{ scope.row.bookPress }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="出版时间" prop="bookTime" sortable width="150">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span>{{ scope.row.bookTime | formatDate('YYYY-MM-DD') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="价格" width="120" prop="bookPrice" sortable align="center">
        <template slot-scope="scope">
          {{ scope.row.bookPrice }}
        </template>
      </el-table-column>
      <el-table-column label="库存" width="120" prop="bookNum" sortable align="center">
        <template slot-scope="scope">
          {{ scope.row.bookNum }}
        </template>
      </el-table-column>
      <el-table-column label="简介" width="220" align="center">
        <template slot-scope="scope">
          {{ scope.row.bookIntro }}
        </template>
      </el-table-column>
      <el-table-column label="销量" width="120" prop="bookSale" sortable align="center">
        <template slot-scope="scope">
          {{ scope.row.bookSale }}
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { getBookList } from '@/api/article'
import { parseTime } from '@/utils'
// options components
import FilenameOption from './components/FilenameOption'
import AutoWidthOption from './components/AutoWidthOption'
import BookTypeOption from './components/BookTypeOption'
export default {
  name: 'ExportExcel',
  components: { FilenameOption, AutoWidthOption, BookTypeOption },
  data() {
    return {
      list: null,
      listLoading: true,
      multipleSelection: [],
      downloadLoading: false,
      filename: '',
      autoWidth: true,
      bookType: 'xlsx',
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
      const result = await getBookList()
      if (result.statu === 0) {
        this.list = result.data
        this.listLoading = false
      }

    },
    handleDownload() {
      if (this.multipleSelection.length) {
        this.downloadLoading = true
        import('@/vendor/Export2Excel').then(excel => {
          const tHeader = ['图书Id', '图书图片', '作者', '图书名', '出版社', '出版时间', '价格', '库存', '简介', '销量']
          const filterVal = ['bookId', 'bookImg', 'bookAuthor', 'bookName', 'bookPress', 'bookTime', 'bookPrice', 'bookNum', 'bookIntro', 'bookSale']
          // const list = this.list
          const list = this.multipleSelection
          const data = this.formatJson(filterVal, list)
          excel.export_json_to_excel({
            header: tHeader,
            data,
            filename: this.filename,
            autoWidth: this.autoWidth,
            bookType: this.bookType
          })
          this.downloadLoading = false
        })
      } else {
        this.$message({
          message: '至少选中一条数据',
          type: 'warning'
        })
      }
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => {
        if (j === 'timestamp') {
          return parseTime(v[j])
        } else {
          return v[j]
        }
      }))
    },
    _getHeight() {
      const height = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight
      this.setHeight = height - 150 + 'px'
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
  }
}
</script>

<style>
.radio-label {
  font-size: 14px;
  color: #606266;
  line-height: 40px;
  padding: 0 12px 0 30px;
}
</style>
