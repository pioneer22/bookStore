<template>
  <div class="outDiv">
    <div class="dialog">
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

      <div class="editLeft">
        <div>
          <span>图书名称：</span>
          <el-input
            v-model="bookName"
            placeholder="请输入书名"
            clearable
            class="bookMess">
          </el-input>
        </div>

        <div>
          <span>图书作者：</span>
          <el-input
            v-model="bookAuthor"
            placeholder="请输入作者"
            clearable
            class="bookMess">
          </el-input>
        </div>

        <div>
          <span>出版日期：</span>
          <el-date-picker
            v-model="bookTime"
            type="date"
            placeholder="选择日期"
            class="bookMess">
          </el-date-picker>
        </div>

        <div>
          <span>出版社区：</span>
          <el-input
            v-model="bookPress"
            placeholder="请输入出版社"
            clearable
            class="bookMess">
          </el-input>
        </div>

        <div>
          <span>图书价格：</span>
          <el-input
            v-model="bookPrice"
            placeholder="请输入价格"
            clearable
            class="bookMess">
          </el-input>
        </div>

        <div>
          <span>图书库存：</span>
          <el-input
            v-model="bookNum"
            placeholder="请输入库存"
            clearable
            class="bookMess">
          </el-input>
        </div>

        <div>
          <span class="textSpan">图书简介：</span>
          <el-input
            :rows="2"
            v-model="bookIntro"
            type="textarea"
            placeholder="请输入简介"
            class="bookMess">
          </el-input>
        </div>

        <div style="text-align: right">
          <el-button type="primary" style="margin-right: 38px" @click="toUpload">图书上架</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { insertBook } from '../../../api/book'
import { isNumber, isDouble } from '../../../utils/Reg'

export default {
  data() {
    return {
      bookName: '',
      bookAuthor: '',
      bookPress: '',
      bookPrice: '',
      bookNum: '',
      bookIntro: '',
      bookTime: '',
      imageUrl: ''
    }
  },

  methods: {
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

    toUpload() { // 图书上架
      if (this.bookName === '' || this.bookAuthor === '' ||
        this.bookPress === '' || this.bookPrice === '' ||
        this.bookNum === '' || this.bookIntro === '' ||
        this.bookTime === '' || this.imageUrl === '') {
        this.$message({
          message: '注意信息输入不能为空！',
          type: 'warning'
        })
        return
      }
      if (!isNumber(this.bookNum) || !(isDouble(this.bookPrice) || isNumber(this.bookPrice))) {
        this.$message({
          message: '价格或库存输入有误！',
          type: 'warning'
        })
        this.bookNum = ''
        this.bookPrice = ''
        return
      }
      this.$confirm('确认图书信息并上架?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async() => {
        const time = Date.parse(this.bookTime) + '' // 转时间戳
        const insertMess = [
          this.bookName,
          this.bookAuthor,
          this.bookPress,
          this.bookPrice,
          this.bookNum,
          this.bookIntro,
          time,
          this.imageUrl]
        const result = await insertBook(insertMess)
        if (result === 1) {
          this.success('图书成功上架！')
          this.bookName = ''
          this.bookAuthor = ''
          this.bookPress = ''
          this.bookPrice = ''
          this.bookNum = ''
          this.bookIntro = ''
          this.bookTime = ''
          this.imageUrl = ''
        }
      }).catch(() => {
      })
    },

    success(message) {
      this.$message({
        message: message,
        type: 'success'
      })
    }
  }
}
</script>

<style type="text/scss" rel="stylesheet/scss" lang="scss" scoped>
  .outDiv{
    padding-top: 80px;
    padding-bottom: 195px;
    background: #D9FFFF;
    /*background-image: url('../../../images/backmoon.png');*/
 /*   max-width: 100%;
    max-height: 100%;*/
    background-size: 100% 100%;
    .dialog{
      width: 90%;
      margin:0 auto;
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

<style>
  .avatar-uploader .el-upload {
    border: 2px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
</style>
