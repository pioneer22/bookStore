<template>
  <div class="topicDiv">
    <div class="topDiv">
      <div>
        <el-upload
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
          class="avatar-uploader"
          action="/api/upload">
          <img v-if="imageUrl" :src="imageUrl" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </div>
      <div class="inputDiv">
        <el-input
          v-model="input"
          placeholder="专题名称"
          clearable>
        </el-input>
        <div style="padding-top: 10px">
          <el-button type="primary" style="width: 100%;" @click="sendTopic">发布专题</el-button>
        </div>
      </div>
    </div>

    <div class="bookTable">
      <el-table
        key="bookData"
        ref="multipleTable"
        :data="bookData"
        :height="height"
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange">
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column
          prop="bookId"
          label="图书Id"
          width="120">
        </el-table-column>
        <el-table-column
          prop="bookImg"
          label="图书图片"
          width="120">
          <template slot-scope="scope">
            <img :src="scope.row.bookImg" class="bookImg" >
          </template>
        </el-table-column>
        <el-table-column
          prop="bookName"
          label="图书名">
        </el-table-column>
        <el-table-column
          prop="bookAuthor"
          label="作者"
          show-overflow-tooltip>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import { reqBook } from '../../../api/book'
import { insertTopic } from '../../../api/topic'
export default {
  data() {
    return {
      imageUrl: '',
      input: '',
      height: '400',
      bookData: [],
      multipleSelection: [] // 存放选中图书
    }
  },

  created() {
    this.getBook()
    this._getHeight()
  },

  methods: {
    async getBook() {
      const result = await reqBook()
      if (result.statu === 0) {
        this.bookData = result.data
      }
    },

    handleSelectionChange(val) {
      this.multipleSelection = val
    },

    handleAvatarSuccess(res, file) { // 上传图片
      this.imageUrl = res.data
    },

    beforeAvatarUpload(file) { // 图片格式
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是JPG格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过2MB!')
      }
      return isJPG && isLt2M
    },

    _getHeight() {
      const height = window.innerHeight || document.body.clientHeight || document.documentElement.clientHeight
      this.height = height - 190
    },

    sendTopic() {
      if (this.input === '' || this.imageUrl === '') {
        this.$message({
          type: 'warning',
          message: '专题图片和专题名不能为空！'
        })
        return
      }
      if (this.multipleSelection.length === 0) {
        this.$message({
          type: 'warning',
          message: '未选择图书！'
        })
        return
      }
      this.$confirm('确认发布?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async() => {
        const topicName = this.input
        const topicImg = this.imageUrl
        const books = []
        this.multipleSelection.forEach(item => {
          books.push(item.bookId)
        })

        const result = await insertTopic(topicImg, topicName, books)
        if (result === 1) {
          this.input = ''
          this.imageUrl = ''
          this.$message({
            type: 'success',
            message: '专题发布成功!'
          })
          location.reload() // 页面刷新
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消发布专题'
        })
      })
    }
  }
}
</script>

<style type="text/scss" rel="stylesheet/scss" lang="scss" scoped>
  .topicDiv {
    width: 84%;
    margin: auto;
    .topDiv {
      display: flex;
      flex-direction: row;
      padding: 10px;
      .inputDiv{
        padding:0px 10px;
      }
    }
    .bookTable {
      box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
      padding:5px;
      .bookImg {
        width: 80px;
        height: 80px;
      }
    }
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 200px;
    height: 90px;
    line-height: 90px;
    text-align: center;
  }

  .avatar{
    max-width: 200px;
    max-height: 90px;
  }

</style>

<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }

  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
</style>
