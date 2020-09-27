
<template>
  <div class="noticeDiv">
    <div class="topDiv">
      <div class="inputDiv">
        <el-input
          v-model="input"
          placeholder="专题名称/专题编号"
          clearable>
        </el-input>
      </div>
      <div style="padding-right: 10px">
        <el-button type="primary" icon="el-icon-search" circle @click="toSearchTopic"></el-button>
      </div>
      <div @click="backTopic">
        <el-button type="danger" icon="el-icon-back" circle></el-button>
      </div>
    </div>

    <div v-if="isShow" class="showTable">
      <el-table
        key="topic"
        :data="topic"
        :height="height"
        :default-sort = "{prop: 'topic.topicId', order: 'ascending'}"
        highlight-current-row
        style="width: 100%">
        <el-table-column
          prop="topic.topicId"
          label="专题编号"
          width="150"
          sortable>
        </el-table-column>
        <el-table-column
          label="专题图片"
          width="300">
          <template slot-scope="scope">
            <div>
              <img :src="scope.row.topic.topicImg" class="Img">
            </div>
          </template>
        </el-table-column>
        <el-table-column
          prop="topic.topicName"
          label="专题名称"
          width="150">
        </el-table-column>
        <el-table-column
          prop="topic.topicTime"
          label="发布时间"
          width="200"
          sortable>
          <template slot-scope="scope">
            {{ scope.row.topic.topicTime | formatDate }}
          </template>
        </el-table-column>
        <el-table-column
          label="操作">
          <template slot-scope="scope">
            <el-button type="danger" icon="el-icon-delete" circle @click = "toDeleteTopic(scope.row.topic.topicId)"></el-button>
            <el-button type="primary" icon="el-icon-folder-opened" circle @click="showTopicBook(scope.row)"></el-button>
            <el-button type="success" icon="el-icon-edit-outline" circle @click="editTopic(scope.row.topic.topicId)"></el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div>
      <el-dialog :visible.sync="dialogTableVisible" title="编辑该专题图书">
        <el-table
          key="bookData"
          ref="multipleTable"
          :data="bookData"
          height="400"
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
              <img :src="scope.row.bookImg" class="bookImg">
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
        <div style="margin-top: 20px;text-align: right">
          <el-button type="primary" size="small" @click="addTopicBook">添加</el-button>
        </div>
      </el-dialog>
    </div>

    <div v-if="!isShow" class="showTable">
      <el-table
        key="topicShow"
        :data="topicShow"
        :height="height"
        :default-sort = "{prop: 'bookId', order: 'ascending'}"
        highlight-current-row
        style="width: 100%">
        <el-table-column
          prop="bookName"
          label="图书名"
          width="200">
        </el-table-column>
        <el-table-column
          label="图书图片"
          width="150">
          <template slot-scope="scope">
            <div>
              <img :src="scope.row.bookImg" class="Img">
            </div>
          </template>
        </el-table-column>
        <el-table-column
          prop="bookAuthor"
          label="图书作者"
          width="200">
        </el-table-column>
        <el-table-column
          prop="bookPrice"
          label="图书价格"
          width="180"
          sortable>
          <template slot-scope="scope">
            ￥{{ scope.row.bookPrice }}
          </template>
        </el-table-column>
        <el-table-column
          prop="bookSale"
          label="图书销量(本)"
          width="150"
          sortable>
        </el-table-column>
        <el-table-column
          prop="bookNum-bookSale"
          label="图书库存(本)"
          width="150"
          sortable>
          <template slot-scope="scope">
            {{ scope.row.bookNum-scope.row.bookSale }}
          </template>
        </el-table-column>
        <el-table-column
          label="操作">
          <template slot-scope="scope">
            <el-button type="danger" icon="el-icon-delete" circle @click="toDeleteTopicBook(scope.row)"></el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import { reqTopic, deleteTopic, deleteTopicBook, searchTopic, reqBook, insertTopicBook } from '../../../api/topic'
export default {
  data() {
    return {
      input: '',
      topic: [],
      topicShow: [],
      height: '400',
      isShow: true, // 控制图书列表显示或图书评论显示
      topicId: '', // 存放当前选中的专题Id
      bookData: [],
      dialogTableVisible: false,
      multipleSelection: [] // 存放选中图书
    }
  },

  created() {
    this.getTopic()
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

    async getTopic() {
      const result = await reqTopic()
      if (result.statu === 0) {
        this.topic = result.data
      }
    },

    _getHeight() {
      const height = window.innerHeight || document.body.clientHeight || document.documentElement.clientHeight
      this.height = height - 140
    },

    showTopicBook(row) { // 显示专题图书详情
      this.topicShow = row.book
      this.topicId = row.topic.topicId
      this.isShow = false
    },

    backTopic() { // 返回专题
      this.isShow = true
      this.getTopic()
    },

    async toSearchTopic() { // 查找专题
      const searchKey = this.input
      const result = await searchTopic(searchKey)
      if (result.statu === 0) {
        if (result.data.length === 0) {
          this.$message({
            type: 'warning',
            message: '查询不到相关专题!'
          })
          this.input = ''
          return
        }
        this.topic = result.data
      }
    },

    toDeleteTopic(topicId) { // 删除专题
      this.$confirm('此操作将永久删除该专题, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async() => {
        const result = await deleteTopic(topicId)
        if (result === 1) {
          this.getTopic()
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },

    toDeleteTopicBook(row) { // 删除专题图书
      this.$confirm('删除该专题图书?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async() => {
        const topicId = this.topicId
        const bookId = row.bookId
        const result = await deleteTopicBook(topicId, bookId)
        if (result === 1) {
          const index = this.topicShow.findIndex(e => {
            return e === row
          })
          this.topicShow.splice(index, 1)
          this.getTopic()
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },

    editTopic(topicId) { // 打开图书页面供选择编辑
      this.dialogTableVisible = true
      this.topicId = topicId
    },

    handleSelectionChange(val) {
      this.multipleSelection = val
    },

    async addTopicBook() { // 为专题添加图书
      if (this.multipleSelection.length > 0) {
        const books = []
        const topicId = this.topicId
        this.multipleSelection.forEach(item => {
          books.push(item.bookId)
        })
        const result = await insertTopicBook(topicId, books)
        if (result === 1) {
          this.$message({
            type: 'success',
            message: '添加成功!'
          })
          this.getTopic()
          this.dialogTableVisible = false
        }
      } else {
        this.$message({
          type: 'warning',
          message: '未选择添加图书!'
        })
      }
    }
  }
}
</script>

<style type="text/scss" rel="stylesheet/scss" lang="scss" scoped>
  .noticeDiv{
    width: 90%;
    margin: auto;
    .topDiv{
      display: flex;
      flex-direction: row;
      padding-top: 10px;
      padding-bottom: 10px;
      .inputDiv{
        width: 250px;
        padding-right: 10px;
      }
    }
    .showTable{
      box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
      padding: 5px;
      .Img{
        max-width: 200px;
        max-height: 100px;
      }
    }
    .content{
      display: flex;
      flex-direction: row;
      .ImgDiv{
        padding-right: 10px;
      }
    }
    .bookImg{
      max-width: 80px;
      max-height: 80px;
    }
  }
</style>
