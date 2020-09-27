
<template>
  <div class="noticeDiv">
    <div class="topDiv">
      <div class="inputDiv">
        <el-input
          v-model="input"
          placeholder="图书Id/图书名"
          clearable>
        </el-input>
      </div>
      <div style="padding-right: 10px">
        <el-button type="primary" icon="el-icon-search" circle @click="toSearchBook"></el-button>
      </div>
      <div @click="backComment">
        <el-button type="danger" icon="el-icon-back" circle></el-button>
      </div>
    </div>

    <div v-if="isShow" class="showTable">
      <el-table
        key="bookComment"
        :data="bookComment"
        :height="height"
        :default-sort = "{prop: 'book.bookId', order: 'ascending'}"
        highlight-current-row
        style="width: 100%"
        @row-click="showComment">
        <el-table-column
          prop="book.bookId"
          label="图书编号"
          width="150"
          sortable>
        </el-table-column>
        <el-table-column
          label="图书图片"
          width="200">
          <template slot-scope="scope">
            <div>
              <img :src="scope.row.book.bookImg" class="Img">
            </div>
          </template>
        </el-table-column>
        <el-table-column
          prop="book.bookName"
          label="图书名">
        </el-table-column>
        <el-table-column
          prop="book.bookPress"
          label="出版社"
          width="200">
        </el-table-column>
        <el-table-column
          prop="book.bookTime"
          label="出版时间"
          width="200"
          sortable>
          <template slot-scope="scope">
            {{ scope.row.book.bookTime | formatDate("YYYY-MM-DD") }}
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div v-if="!isShow" class="showTable">
      <el-table
        key="commentShow"
        :data="commentShow"
        :height="height"
        :default-sort = "{prop: 'comment.comTime', order: 'ascending'}"
        highlight-current-row
        style="width: 100%">
        <el-table-column
          prop="userId"
          label="用户Id/用户名"
          width="150"
          sortable>
          <template slot-scope="scope">
            {{ scope.row.comment.userId }} / {{ scope.row.userName }}
          </template>
        </el-table-column>
        <el-table-column
          prop="userImg"
          label="用户头像"
          width="120">
          <template slot-scope="scope">
            <div>
              <img :src="scope.row.userImg" class="Img" style="width: 40px;height: 40px;border-radius: 20px">
            </div>
          </template>
        </el-table-column>
        <el-table-column
          label="评论内容">
          <template slot-scope="scope">
            <div class="content">
              <div v-if="scope.row.comment.comStatus === '1'" class="ImgDiv">
                <img :src="scope.row.picAddress" class="Img">
              </div>
              <div>
                {{ scope.row.comment.comMess }}<br>
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column
          prop="comment.replyTime"
          width="1"
          sortable>
          <template slot-scope="scope">
            <div></div>
          </template>
        </el-table-column>
        <el-table-column
          prop="comment.comTime"
          label="评论时间"
          width="180"
          sortable>
          <template slot-scope="scope">
            <div>
              {{ scope.row.comment.comTime | formatDate }}
            </div>
          </template>
        </el-table-column>
        <el-table-column
          prop="comment.comReply"
          label="回复内容">
          <template slot-scope="scope">
            {{ scope.row.comment.comReply }}
          </template>
        </el-table-column>
        <el-table-column
          prop="comment.replyTime"
          label="回复时间"
          width="180"
          sortable>
          <template slot-scope="scope">
            <div v-if="scope.row.comment.comReply != null">
              {{ scope.row.comment.replyTime | formatDate }}
            </div>
          </template>
        </el-table-column>
        <el-table-column
          label="回复"
          width="90">
          <template slot-scope="scope">
            <div v-if="scope.row.comment.comReply === null">
              <el-button type="success" icon="el-icon-edit" circle @click="toReply(scope.row)"></el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import { reqComment, replyComment, searchBook } from '../../../api/comment'
export default {
  data() {
    return {
      input: '',
      bookComment: [],
      commentShow: [],
      height: '400',
      isShow: true // 控制图书列表显示或图书评论显示
    }
  },

  created() {
    this.getBookComment()
    this._getHeight()
  },

  methods: {
    async getBookComment() {
      const result = await reqComment()
      if (result.statu === 0) {
        this.bookComment = result.data
      }
    },

    _getHeight() {
      const height = window.innerHeight || document.body.clientHeight || document.documentElement.clientHeight
      this.height = height - 130
    },

    showComment(row, column, event) {
      this.commentShow = row.commentList
      this.isShow = false
    },

    toReply(row) {
      this.$prompt('请输入回复信息', '回复评论', {
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(async({ value }) => {
        if (value.length > 0) {
          const comId = row.comment.comId
          const comReply = value
          const result = await replyComment(comId, comReply)
          if (result === 1) {
            this.$message({
              type: 'success',
              message: '回复成功'
            })
            const index = this.commentShow.findIndex(e => {
              return e === row
            })
            this.commentShow[index].comment.comReply = value
            this.commentShow[index].comment.replyTime = new Date()
            this.getBookComment()
          }
        } else {
          this.$message({
            type: 'warning',
            message: '回复评论信息不能为空！'
          })
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消回复'
        })
      })
    },

    async toSearchBook() {
      const searchKey = this.input
      this.isShow = true
      if (searchKey.length > 0) {
        const result = await searchBook(searchKey)
        if (result.statu === 0) {
          this.input = ''
          this.bookComment = result.data
        } else {
          this.$message({
            type: 'warning',
            message: '查询不到相关图书!'
          })
          this.input = ''
        }
      }
    },

    backComment() {
      this.isShow = true
      this.getBookComment()
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
        max-width: 80px;
        max-height: 80px;
      }
    }
    .content{
      display: flex;
      flex-direction: row;
      .ImgDiv{
        padding-right: 10px;
      }
    }
  }
</style>
