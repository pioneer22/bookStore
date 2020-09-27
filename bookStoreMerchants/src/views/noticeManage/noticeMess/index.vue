<template>
  <div class="noticeDiv">
    <div class="topDiv">
      <div class="inputDiv">
        <el-input
          v-model="input"
          placeholder="发布内容"
          clearable>
        </el-input>
      </div>
      <div style="padding-right: 10px">
        <el-button type="primary" @click="toSendNotice">发布信息</el-button>
      </div>
      <div>
        <el-button type="danger" @click="toClearNotice">清空消息</el-button>
      </div>
    </div>
    <div>
      <el-table
        :data="paperMess"
        :height="height"
        :default-sort = "{prop: 'paperTime', order: 'descending'}"
        highlight-current-row
        style="width: 100%">
        <el-table-column
          type="index"
          width="50">
        </el-table-column>
        <el-table-column
          prop="paperTime"
          label="发布时间"
          width="250"
          sortable>
          <template slot-scope="scope">
            {{ scope.row.paperTime | formatDate }}
          </template>
        </el-table-column>
        <el-table-column
          prop="paperMess"
          label="发送内容">
        </el-table-column>
        <el-table-column
          label="操作"
          width="150">
          <template slot-scope="scope">
            <el-button type="danger" icon="el-icon-delete" circle @click="toDeleteNotice(scope.row.paperId)"></el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import { getMorningPaper, sendNotice, deleteNotice, deleteAllNotice } from '../../../api/notice'
export default {
  data() {
    return {
      input: '',
      paperMess: [],
      height: '400'
    }
  },

  created() {
    this.getPaper()
    this._getHeight()
  },

  methods: {
    async getPaper() {
      const result = await getMorningPaper()
      if (result.statu === 0) {
        this.paperMess = result.data
      }
    },

    _getHeight() {
      const height = window.innerHeight || document.body.clientHeight || document.documentElement.clientHeight
      this.height = height - 120
    },

    toSendNotice() {
      if (this.input === '') {
        this.$message({
          message: '请输入发布内容！',
          type: 'warning'
        })
      } else {
        this.$confirm('确认内容发布无误?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(async() => {
          const noticeMess = this.input
          const businessId = this.$store.state.admin.business.businessId
          const result = await sendNotice(noticeMess, businessId)
          if (result === 1) {
            this.input = ''
            this.getPaper()
            this.$message({
              type: 'success',
              message: '发布成功!'
            })
          }
        }).catch(() => {
        })
      }
    },

    toDeleteNotice(paperId) {
      this.$confirm('确认删除该早报信息?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async() => {
        const result = await deleteNotice(paperId)
        if (result === 1) {
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
          this.getPaper()
        }
      }).catch(() => {
      })
    },

    toClearNotice() {
      this.$prompt('涉及全部清除请输入验证登陆密码', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(async({ value }) => {
        const businessPass = this.$store.state.admin.business.businessPass
        const businessId = this.$store.state.admin.business.businessId
        if (value === businessPass) {
          const result = await deleteAllNotice(businessId)
          if (result === 1) {
            this.$message({
              type: 'success',
              message: '所有信息已清空'
            })
            this.paperMess = []
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
          message: '取消输入'
        })
      })
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
      .inputDiv{
        width: 250px;
        padding-right: 10px;
      }
    }
  }
</style>
