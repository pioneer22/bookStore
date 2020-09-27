<template>
  <div class="couponDiv">
    <div class="tagDiv">
      <el-tag type="success" @click="openCoupon(1)">全部优惠卷</el-tag>
      <el-tag type="info" @click="openCoupon(2)">有效优惠卷</el-tag>
      <el-tag type="warning" @click="openCoupon(3)">失效优惠卷</el-tag>
      <el-tag type="danger" @click="dialogVisible = true">发布优惠卷</el-tag>
    </div>

    <div>
      <el-table
        :data="couponData"
        :height="height"
        border
        style="width: 100%">
        <el-table-column
          fixed
          prop="couponTime"
          label="发布日期"
          width="200">
          <template slot-scope="scope">
            {{ scope.row.couponTime | formatDate }}
          </template>
        </el-table-column>
        <el-table-column
          prop="couponImg"
          label="优惠卷图片"
          width="150">
          <template slot-scope="scope">
            <div>
              <img :src="scope.row.couponImg" style="max-width: 80px;max-height:80px">
            </div>
          </template>
        </el-table-column>
        <el-table-column
          label="优惠信息"
          width="200">
          <template slot-scope="scope">
            <div>
              满{{ scope.row.couponUse }}减{{ scope.row.couponMoney }}
            </div>
          </template>
        </el-table-column>
        <el-table-column
          prop="couponVaild"
          label="有效期"
          width="200">
          <template slot-scope="scope">
            <div>
              {{ scope.row.couponVaild | formatDate }}
            </div>
          </template>
        </el-table-column>
        <el-table-column
          prop="couponNum"
          label="数量"
          width="150">
        </el-table-column>
        <el-table-column
          prop="couponGetnum"
          label="已领取"
          width="150">
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="100">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-delete" circle @click="handleClick(scope.row)"></el-button>
          </template>
        </el-table-column>
      </el-table>

      <el-dialog
        :visible.sync="dialogVisible"
        :before-close="handleClose"
        width="50%">
        <span slot="title" class="title">发布优惠卷</span>

        <div class="sendCouponDiv">
          <div class="couponMess">
            <div class="discountDiv">
              <span class="discountSpan">优惠信息</span>
              <div class="discountMess">
                满<el-input v-model="arrived" placeholder="达到金额" class="arrived"></el-input>
                减<el-input v-model="discount" placeholder="减少金额" style="width: 90px"></el-input>
              </div>
            </div>
            <div class="discountDiv center" >
              <span class="discountSpan">有效期</span>
              <div class="block">
                <el-date-picker
                  v-model="time"
                  type="date"
                  placeholder="选择日期"
                  format="yyyy 年 MM 月 dd 日"
                  value-format="timestamp"
                  style="width: 231px">
                </el-date-picker>
              </div>
            </div>
            <div class="discountDiv center">
              <span class="discountSpan">数量</span>
              <div>
                <el-input
                  v-model="couponNum"
                  placeholder="优惠卷数量"
                  clearable
                  style="width: 231px">
                </el-input>
              </div>
            </div>
          </div>

          <div style="padding:5px 10px">
            <el-upload
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
              class="avatar-uploader"
              action="/api/upload">
              <img v-if="imageUrl" :src="imageUrl" class="avatar" >
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </div>
        </div>

        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="sendCoupon">确 定</el-button>
        </span>
      </el-dialog>
    </div>

  </div>
</template>

<script>
import { reqCoupon, insertCoupon, deleteCoupon } from '../../../api/coupon'

export default {
  data() {
    return {
      coupon: [],
      valid: [],
      disabled: [],
      couponData: [],
      height: 400,
      dialogVisible: false,
      imageUrl: '',
      arrived: '',
      discount: '',
      time: '',
      couponNum: ''
    }
  },

  created() {
    this.getCoupon()
    this._getHeight()
  },

  methods: {
    handleClick(row) {
      this.$confirm('确认删除?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async() => {
        const couponId = row.couponId
        const result = await deleteCoupon(couponId)
        if (result === 1) {
          this.getCoupon()
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

    _getHeight() {
      const height = window.innerHeight || document.body.clientHeight || document.documentElement.clientHeight
      this.height = height - 122 + 'px'
    },

    async getCoupon() {
      const businessId = this.$store.state.admin.business.businessId
      const result = await reqCoupon(businessId)
      if (result.statu === 0) {
        this.couponData = result.data.coupon
        this.coupon = result.data.coupon
        this.valid = result.data.valid
        this.disabled = result.data.disabled
      }
    },

    openCoupon(index) {
      switch (index) {
        case 1:
          this.couponData = this.coupon
          break
        case 2:
          this.couponData = this.valid
          break
        case 3:
          this.couponData = this.disabled
          break
      }
    },

    sendCoupon() { // 发布优惠卷
      if (parseInt(this.arrived) < parseInt(this.discount)) {
        this.$message({
          type: 'warning',
          message: '减免金额大于条件金额!'
        })
        return
      }
      if (this.time < (new Date()).getTime()) {
        this.$message({
          type: 'warning',
          message: '有效期输入有误!'
        })
        return
      }
      this.$confirm('消息无误，确认发布?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async() => {
        const couponMess = [
          this.$store.state.admin.business.businessId,
          this.arrived,
          this.discount,
          this.time,
          this.couponNum,
          this.imageUrl
        ]
        const result = await insertCoupon(couponMess)
        if (result === 1) {
          this.dialogVisible = false
          this.arrived = ''
          this.discount = ''
          this.time = ''
          this.couponNum = ''
          this.imageUrl = ''
          this.getCoupon()
          this.$message({
            type: 'success',
            message: '发布成功!'
          })
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消发布'
        })
      })
    },

    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done()
        })
        .catch(_ => {})
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
    }
  }
}
</script>

<style type="text/scss" rel="stylesheet/scss" lang="scss" scoped>
  .couponDiv{
    width: 84%;
    margin:auto;
    .tagDiv{
      padding: 10px 0px;
      .el-tag{
        cursor: pointer;
      }
    }
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 135px;
    height: 135px;
    line-height: 135px;
    text-align: center;
  }
  .title{
    font-size: 18px;
    font-weight: bold;
    color: #666666;
  }
  .sendCouponDiv{
    display: flex;
    flex-direction: row;
    .couponMess{
      font-weight: bold;
      font-size: 18px;
      .discountDiv{
        display: flex;
        flex-direction: row;
        padding: 5px 0px;
        .discountSpan{
          letter-spacing: 0px;
          display: inline-block;
          padding:0px 10px;
          width: 100px;
          text-align: right;
          line-height: 40px;
        }
        .discountMess{
          display: flex;
          flex-direction: row;
          align-items: center;
          letter-spacing: 5px;
          color: red;
          .arrived{
            width: 90px;
            margin-right: 5px;
          }
        }
      }
      .center{
        align-items: center;
      }
    }
    .avatar{
      max-width: 135px;
      max-height: 135px;
    }
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
