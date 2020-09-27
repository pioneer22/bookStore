<template>
  <div class="app-container">
    <upload-excel-component :on-success="handleSuccess" :before-upload="beforeUpload" />
    <el-button type="primary" plain @click="oneSelf">一键上架</el-button>
    <el-table
      v-if="!isShow"
      :data="tableData"
      :height="height"
      border
      highlight-current-row
      style="width: 100%;margin-top:10px;"
    >
      <el-table-column v-for="item of tableHeader" :key="item" :prop="item" :label="item"/>
    </el-table>

    <el-table
      v-if="isShow"
      :data="tableData"
      :height="height"
      border
      highlight-current-row
      style="width: 100%;margin-top:10px;">
      <el-table-column
        label="bookImg">
      </el-table-column>
      <el-table-column
        label="bookAuthor">
      </el-table-column>
      <el-table-column
        label="bookPress">
      </el-table-column>
      <el-table-column
        label="bookTime">
      </el-table-column>
      <el-table-column
        label="bookName">
      </el-table-column>
      <el-table-column
        label="bookPrice">
      </el-table-column>
      <el-table-column
        label="bookNum">
      </el-table-column>
      <el-table-column
        label="bookIntro">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import UploadExcelComponent from '@/components/UploadExcel/index.vue'
import { uploadBook } from '@/api/article'

export default {
  name: 'UploadExcel',
  components: { UploadExcelComponent },
  data() {
    return {
      tableData: [],
      tableHeader: [],
      height: 100,
      isShow: true
    }
  },
  watch: {
    tableData() {
      this._getHeight()
      this.isShow = false
    }
  },
  methods: {
    beforeUpload(file) {
      const isLt1M = file.size / 1024 / 1024 < 1

      if (isLt1M) {
        return true
      }

      this.$message({
        message: '请不要上传大于1M的文件',
        type: 'warning'
      })
      return false
    },

    handleSuccess({ results, header }) {
      this.tableData = results
      this.tableHeader = header
    },

    _getHeight() {
      const height = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight
      this.height = height - 300 + 'px'
    },

    async oneSelf() {
      if (this.tableData.length > 0) {
        const books = this.tableData
        const result = await uploadBook(books)
        if (result === 1) {
          this.$message({
            message: '图书一键上传成功',
            type: 'success'
          })
          this.tableData = []
          this.tableHeader = []
        }
      } else {
        this.$message({
          message: '请打开文件再上架',
          type: 'warning'
        })
      }
    }
  }
}
</script>
