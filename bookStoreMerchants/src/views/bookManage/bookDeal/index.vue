<template>
  <div class="content">
    <div class="search">
      <el-input
        v-model="input"
        placeholder="请输入搜索内容"
        clearable
        class="input">
      </el-input>
      <el-button type="primary" @click="goSearch" icon="el-icon-search"></el-button>
      <el-button type="danger" @click="getBook" icon="el-icon-refresh"></el-button>
      <el-button type="warning" @click="clearBook">清空书架</el-button>
    </div>
    <div class="table">
      <el-table
        :data="showTen"
        :default-sort = "{prop: 'bookId', order: 'ascending'}"
        style="width: 100%">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item label="图书名称">
                <span>{{ props.row.bookName }}</span>
              </el-form-item>
              <el-form-item label="作者">
                <span>{{ props.row.bookAuthor }}</span>
              </el-form-item>
              <el-form-item label="出版社">
                <span>{{ props.row.bookPress }}</span>
              </el-form-item>
              <el-form-item label="出版日期">
                <span>{{ props.row.bookTime | formatDate('YYYY-MM-DD') }}</span>
              </el-form-item>
              <el-form-item label="简介">
                <span>{{ props.row.bookIntro }}</span>
              </el-form-item>
              <el-form-item label="价格">
                <span>￥{{ props.row.bookPrice }}</span>
              </el-form-item>
              <el-form-item label="库存">
                <span>{{ props.row.bookNum }}</span>
              </el-form-item>
              <el-form-item label="销量">
                <span>{{ props.row.bookSale }}</span>
              </el-form-item>
              <el-form-item label="图片">
                <img :src="props.row.bookImg" class="bookImg">
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column
          label="图书 ID"
          prop="bookId"
          sortable>
        </el-table-column>
        <el-table-column
          label="图书名称"
          prop="bookName">
        </el-table-column>
        <el-table-column
          label="简介"
          prop="bookIntro">
        </el-table-column>

        <el-table-column
          align="right">
          <template slot="header" slot-scope="scope">
            <div style="margin-right: 50px">操作</div>
          </template>
          <template slot-scope="scope">
            <el-button
              type="primary"
              icon="el-icon-edit"
              circle
              @click="handleEdit(scope.$index, scope.row)"></el-button>

            <el-button
              type="danger"
              icon="el-icon-delete"
              circle
              @click="handleDelete(scope.$index, scope.row)"></el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div class="block">
      <el-pagination
        :current-page="currentPage"
        :page-size="pageSize"
        :total="itemSize"
        background
        layout="total, prev, pager, next, jumper"
        @current-change="handleCurrentChange">
      </el-pagination>
    </div>

    <el-dialog
      :visible.sync="dialogVisible"
      :before-close="handleClose"
      title="编辑图书信息"
      width="64%">
      <div class="dialog">
        <div class="editLeft">
          <span>图书名称：</span>
          <el-input
            v-model="bookName"
            placeholder="请输入书名"
            clearable
            class="bookMess">
          </el-input>
          <span>图书作者：</span>
          <el-input
            v-model="bookAuthor"
            placeholder="请输入作者"
            clearable
            class="bookMess">
          </el-input>
          <span>出版日期：</span>
          <el-date-picker
            v-model="bookTime"
            type="date"
            placeholder="选择日期"
            class="bookMess">
          </el-date-picker>
          <span>出版社区：</span>
          <el-input
            v-model="bookPress"
            placeholder="请输入出版社"
            clearable
            class="bookMess">
          </el-input>
          <span>图书价格：</span>
          <el-input
            v-model="bookPrice"
            placeholder="请输入价格"
            clearable
            class="bookMess">
          </el-input>
          <span>图书库存：</span>
          <el-input
            v-model="bookNum"
            placeholder="请输入库存"
            clearable
            class="bookMess">
          </el-input>
          <span class="textSpan">图书简介：</span>
          <el-input
            :rows="2"
            v-model="bookIntro"
            type="textarea"
            placeholder="请输入简介"
            class="bookMess">
          </el-input>
        </div>

        <div class="editRight">
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
      </div>

      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editBook">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { reqBook, searchBook, editBookMess, deleteBook, deleteAllBook } from '../../../api/book'
export default {
  data() {
    return {
      itemSize: 0,
      showBook: [],
      showTen: [], // 显示数组
      currentPage: 1,
      pageSize: 8, // 每页显示条数
      input: '',
      dialogVisible: false,
      bookId: '',
      bookName: '',
      bookAuthor: '',
      bookPress: '',
      bookPrice: '',
      bookNum: '',
      bookIntro: '',
      bookTime: '',
      imageUrl: '',
      val: 1
    }
  },

  created() {
    this.getBook()
  },

  methods: {
    async getBook() {
      const result = await reqBook()
      if (result.statu === 0) {
        this.itemSize = result.data.length
        this.showBook = []
        let temp = []
        result.data.forEach((item, index) => {
          temp.push(item)
          if (temp.length === this.pageSize) {
            this.showBook.push(temp)
            temp = []
          }
          if (index === result.data.length - 1) {
            this.showBook.push(temp)
            temp = []
          }
        })
        if (this.showBook[this.val - 1].length > 0) {
          this.showTen = this.showBook[this.val - 1]
        } else {
          this.showTen = this.showBook[this.val - 2]
        }
      }
    },

    handleEdit(index, row) { // 弹出编辑框
      this.dialogVisible = true
      this.bookId = row.bookId
      this.bookName = row.bookName
      this.bookAuthor = row.bookAuthor
      this.bookPress = row.bookPress
      this.bookPrice = row.bookPrice
      this.bookNum = row.bookNum
      this.bookIntro = row.bookIntro
      this.bookTime = row.bookTime
      this.imageUrl = row.bookImg
    },

    handleDelete(index, row) { // 删除图书
      this.$confirm('此操作将永久删除该图书, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async() => {
        const bookId = row.bookId
        const result = await deleteBook(bookId)
        if (result === 1) {
          this.success('图书删除信息成功！')
          this.getBook()
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },

    handleCurrentChange(val) { // 显示的内容
      this.showTen = this.showBook[val - 1]
      this.val = val
    },

    async goSearch() { // 查找图书
      const searchKey = this.input
      const result = await searchBook(searchKey)
      if (result.statu === 0) {
        this.itemSize = result.data.length
        this.input = ''
        this.showBook = []
        let temp = []
        result.data.forEach((item, index) => {
          temp.push(item)
          if (temp.length === this.pageSize) {
            this.showBook.push(temp)
            temp = []
          }
          if (index === result.data.length - 1) {
            this.showBook.push(temp)
            temp = []
          }
        })
        this.showTen = this.showBook[0]
      }
    },

    handleClose(done) { // 关闭弹框
      this.$confirm('确认关闭？')
        .then(_ => {
          done()
        })
        .catch(_ => {})
    },

    handleAvatarSuccess(res, file) { // 上传图片
      // this.imageUrl = URL.createObjectURL(file.raw)
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

    async editBook() { // 编辑图书信息
      this.dialogVisible = false
      const time = Date.parse(this.bookTime) + '' // 转时间戳
      const searchMess = [this.bookId,
        this.bookName,
        this.bookAuthor,
        this.bookPress,
        this.bookPrice,
        this.bookNum,
        this.bookIntro,
        time,
        this.imageUrl]
      const result = await editBookMess(searchMess)
      if (result === 1) {
        this.success('图书信息修改成功！')
      }
    },

    success(message) {
      this.$message({
        message: message,
        type: 'success'
      })
    },

    clearBook() { // 清空书架
      this.$prompt('涉及全部删除，请输入密码', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(async({ value }) => {
        const businessPass = this.$store.state.admin.business.businessPass
        if (value === businessPass) {
          const result = await deleteAllBook()
          if (result === 1) {
            this.success('所有图书成功下架！')
          }
        } else {
          this.$message({
            type: 'info',
            message: '密码输入错误！'
          })
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消图书下架！'
        })
      })
    }
  }
}
</script>

<style type="text/scss" rel="stylesheet/scss" lang="scss" scoped>
 .content{
   width: 90%;
   margin:10px auto;
   .search{
     display: flex;
     flex-direction: row;
     align-items: center;
     .input{
       width: 250px;
       margin-right: 10px;
     }
   }
   .table{
     .bookImg{
       width: 100px;
       height: 100px;
     }
   }
   .block{
     text-align: center;
     margin-top: 10px
   }
   .dialog{
     display:flex;
     flex-decoration:row;
     .editLeft{
       width: 50%;
       .bookMess{
         margin-bottom: 10px;
         width: 80%;
       }
       span{
         font-weight: bold;
         font-size: 15px;
       }
       .textSpan{
         display:inline-block;
         margin-bottom: 28px;
       }
     }
     .editRight{
       width:50%;
       text-align:center;
       .avatar-uploader-icon {
         font-size: 28px;
         color: #8c939d;
         width: 350px;
         height: 350px;
         line-height: 350px;
         text-align: center;
       }
       .avatar {
         width: 350px;
         height: 350px;
         display: block;
       }
     }
   }

 }
</style>

<style scoped>
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
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
