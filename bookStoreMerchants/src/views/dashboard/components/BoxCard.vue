<template>
  <el-card class="box-card-component" style="margin-left:8px;">
    <div slot="header" class="box-card-header">
      <img src="https://wpimg.wallstcn.com/e7d23d71-cf19-4b90-a1cc-f56af8c0903d.png">
    </div>
    <div style="position:relative;">
      <pan-thumb :image="avatar" class="panThumb" />
      <mallki class-name="mallki-text" text="商家" />
      <div style="padding-top:35px;" class="progress-item">
        <div class="content">
          <span>商家编号</span>
          <span>{{ businessId }}</span>
        </div>
        <el-progress :percentage="100" status="success"/>
      </div>
      <div class="progress-item">
        <div class="content">
          <span>商家昵称</span>
          <span>{{ businessName }}</span>
        </div>
        <el-progress :percentage="100" status="text"/>
      </div>
      <div class="progress-item">
        <div class="content">
          <span>手机号</span>
          <span>{{ businessPhone }}</span>
        </div>
        <el-progress :percentage="100" status="exception"/>
      </div>
    </div>
    <div >
      <el-button type="primary" icon="el-icon-edit" circle @click="changeDialog"></el-button>
    </div>
    <div>
      <el-dialog
        title="修改信息"
        :visible.sync="centerDialogVisible"
        width="40%"
        center>
        <div style="text-align: center">
          <el-input
            placeholder="用户名"
            v-model="name"
            clearable
            style="width: 70%;padding: 5px">
          </el-input>

          <el-input
            placeholder="手机号"
            v-model="phone"
            clearable
            style="width: 70%;padding: 5px">
          </el-input>
          <el-input
            placeholder="原密码"
            v-model="oldPass"
            clearable
            style="width: 70%;padding: 5px">
          </el-input>
          <el-input
            placeholder="新密码"
            v-model="newPass"
            clearable
            style="width: 70%;padding: 5px">
          </el-input>
        </div>
        <span slot="footer" class="dialog-footer">
          <el-button @click="changeMess">修 改 信 息</el-button>
          <el-button type="primary" @click="changePass">修 改 密 码</el-button>
        </span>
      </el-dialog>
    </div>
  </el-card>
</template>

<script>
// import { mapGetters } from 'vuex'
import PanThumb from '@/components/PanThumb'
import Mallki from '@/components/TextHoverEffect/Mallki'
import { changeMessage, changePassword } from "../../../api";

export default {
  components: { PanThumb, Mallki },

  filters: {
    statusFilter(status) {
      const statusMap = {
        success: 'success',
        pending: 'danger'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      statisticsData: {
        article_count: 1024,
        pageviews_count: 1024
      },
      businessId: '',
      businessName: '',
      businessPhone: '',
      centerDialogVisible: false,
      name: '',
      phone: '',
      oldPass: '',
      newPass: ''
    }
  },
  created() {
    this.businessId = this.$store.state.admin.business.businessId
    this.businessName = this.$store.state.admin.business.businessName
    this.businessPhone = this.$store.state.admin.business.businessPhone
  },
  computed: {
 /*   ...mapGetters([
      'name',
      'roles'
    ])*/
    avatar() {
      return this.$store.state.admin.business.businessImg
    }
  },

  methods: {
    changeDialog() {
      this.centerDialogVisible = true
      this.name = this.businessName
      this.phone = this.businessPhone
    },
    changeMess() {
      if (this.name === '' || this.phone === '') {
        this.$message({
          type: 'warning',
          message: '请输入修改信息'
        })
        return
      }
      if(!(/^1[34578]\d{9}$/.test(this.phone))){
        this.$message({
          type: 'warning',
          message: '手机号输入有误'
        })
        return
      }
      this.$confirm('是否修改?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        const { businessId, phone, name } = this
        const result = await changeMessage({ businessId, phone, name })
        if (result === 1) {
          this.$message({
            type: 'success',
            message: '修改成功!'
          })
          this.name = ''
          this.phone = ''
        }
      }).catch(() => {
      })
    },

    changePass() {
      if (this.oldPass === '' || this.newPass === '') {
        this.$message({
          type: 'warning',
          message: '请输入密码信息'
        })
        return
      }
      if(this.oldPass != this.$store.state.admin.business.businessPass){
        this.$message({
          type: 'warning',
          message: '密码输入错误'
        })
        return
      }
      this.$confirm('是否修改?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        const { businessId, newPass } = this
        const result = await changePassword({ businessId, newPass })
        if (result === 1) {
          this.$message({
            type: 'success',
            message: '修改成功!'
          })
          this.newPass = ''
          this.oldPass = ''
        }
      }).catch(() => {
      })
    }
  }
}
</script>

<style type="text/scss" rel='stylesheet/scss' lang="scss" >
.box-card-component{
  .el-card__header {
    padding: 0px!important;
  }
}
</style>
<style type="text/scss" rel="stylesheet/scss" lang="scss" scoped>
.box-card-component {
  .box-card-header {
    position: relative;
    height: 220px;
    img {
      width: 100%;
      height: 100%;
      transition: all 0.2s linear;
      &:hover {
        transform: scale(1.1, 1.1);
        filter: contrast(130%);
      }
    }
  }
  .mallki-text {
    position: absolute;
    top: 0px;
    right: 0px;
    font-size: 20px;
    font-weight: bold;
  }
  .panThumb {
    z-index: 100;
    height: 70px!important;
    width: 70px!important;
    position: absolute!important;
    top: -45px;
    left: 0px;
    border: 5px solid #ffffff;
    background-color: #fff;
    margin: auto;
    box-shadow: none!important;
    /deep/ .pan-info {
      box-shadow: none!important;
    }
  }
  .progress-item {
    margin-bottom: 10px;
    font-size: 16px;
    font-weight: bold;
    .content {
      display: flex;
      justify-content: space-between;
    }
  }
  @media only screen and (max-width: 1510px){
    .mallki-text{
      display: none;
    }
  }
}
</style>
<style>
  .el-progress-bar {
    padding-right: 0px;
  }
</style>
