<template>
  <div class="noticeDiv">
    <div class="topDiv">
      <div class="inputDiv">
        <el-input
          v-model="input"
          placeholder="用户Id"
          clearable>
        </el-input>
      </div>
      <div style="padding-right: 10px" @click="findChat">
        <el-button type="primary" icon="el-icon-search" circle ></el-button>
      </div>
      <div style="padding-right: 10px" @click="getChatMess">
        <el-button type="danger" icon="el-icon-refresh" circle></el-button>
      </div>
      <div @click="deleteChoose">
        <el-button type="success" icon="el-icon-delete" circle></el-button>
      </div>
    </div>

    <div style="box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)">
      <el-table
        ref="multipleTable"
        :data="tableData"
        :height="height"
        :default-sort = "{prop: 'sendTime', order: 'ascending'}"
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange">
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column
          label="用户Id"
          width="120">
          <template slot-scope="scope">{{ scope.row.userId }}</template>
        </el-table-column>
        <el-table-column
          prop="sendTime"
          label="发送时间"
          width="200"
          sortable>
          <template slot-scope="scope">{{ scope.row.sendTime | formatDate }}</template>
        </el-table-column>
        <el-table-column
          label="发送内容"
          show-overflow-tooltip>
          <template slot-scope="scope">
            <div v-html="scope.row.context.replace(/\#[\u4E00-\u9FA5]{1,3}\;/gi, emotion)"></div>
          </template>
        </el-table-column>
        <el-table-column
          label="状态"
          width="200">
          <template slot-scope="scope">
            <span v-if="scope.row.statu === 0 || scope.row.statu === 1">接收</span>
            <span v-if="scope.row.statu === 2 || scope.row.statu === 3">发出</span>
          </template>
        </el-table-column>
        <el-table-column
          label="操作">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-delete" circle @click="deleteOneChat(scope.row.chatId)"></el-button>
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
  </div>
</template>

<script>
import { reqChatMess, deleteChat, searchChat, deleteChooseChat } from '../../../api/notice'
export default {
  data() {
    return {
      input: '',
      tableData: [],
      multipleSelection: [],
      currentPage: 1,
      pageSize: 7, // 每页显示条数
      itemSize: 0,
      showChat: [],
      val: 1 // 存当前页数
    }
  },

  created() {
    this.getChatMess()
    this._getHeight()
  },

  methods: {
    async getChatMess() {
      const businessId = this.$store.state.admin.business.businessId
      const result = await reqChatMess(businessId)
      if (result.statu === 0) {
        this.itemSize = result.data.length
        this.showChat = []
        let temp = []
        result.data.forEach((item, index) => {
          temp.push(item)
          if (temp.length === this.pageSize) {
            this.showChat.push(temp)
            temp = []
          }
          if (index === result.data.length - 1) {
            this.showChat.push(temp)
            temp = []
          }
        })
        if (this.showChat[this.val - 1].length > 0) {
          this.tableData = this.showChat[this.val - 1]
        } else {
          this.tableData = this.showChat[this.val - 2]
        }
      }
    },

    handleSelectionChange(val) {
      this.multipleSelection = val;
    },

    handleCurrentChange(val) { // 显示的内容
      this.tableData = this.showChat[val - 1]
      this.val = val
    },

    _getHeight() {
      const height = window.innerHeight || document.body.clientHeight || document.documentElement.clientHeight
      this.height = height - 165
    },

    deleteOneChat(chatId) { // 删除聊天记录
      this.$confirm('删除该聊天记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async() => {
        const result = await deleteChat(chatId)
        if (result === 1) {
          this.$message({
            type: 'success',
            message: '删除成功'
          })
          this.getChatMess()
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },

    async findChat() { // 查找聊天记录
      const searchKey = "%"+this.input+"%"
      const businessId = this.$store.state.admin.business.businessId
      const result = await searchChat(businessId, searchKey)
      if (result.statu === 0) {
        this.itemSize = result.data.length
        this.showChat = []
        let temp = []
        result.data.forEach((item, index) => {
          temp.push(item)
          if (temp.length === this.pageSize) {
            this.showChat.push(temp)
            temp = []
          }
          if (index === result.data.length - 1) {
            this.showChat.push(temp)
            temp = []
          }
        })
        this.input = ''
        this.tableData = this.showChat[0]
      } else {
        this.$message({
          type: 'info',
          message: '找不到数据！'
        })
      }
    },

    deleteChoose() { // 删除选中聊天记录
      if (this.multipleSelection.length === 0) {
        this.$message({
          type: 'info',
          message: '未选中删除目标！'
        })
        return
      }
      this.$confirm('删除选中聊天记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async() => {
        const chatIdMess = []
        this.multipleSelection.forEach(item => {
          chatIdMess.push(item.chatId)
        })
        const result = await deleteChooseChat(chatIdMess)
        if (result === 1) {
          this.$message({
            type: 'success',
            message: '删除成功'
          })
          this.getChatMess()
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    // 将匹配结果替换表情图片
    emotion (res) {
      let word = res.replace(/\#|\;/gi,'')
      const list = ['微笑', '撇嘴', '色', '发呆', '得意', '流泪', '害羞', '闭嘴', '睡', '大哭', '尴尬', '发怒', '调皮', '呲牙', '惊讶', '难过', '酷', '冷汗', '抓狂', '吐', '偷笑', '可爱', '白眼', '傲慢', '饥饿', '困', '惊恐', '流汗', '憨笑', '大兵', '奋斗', '咒骂', '疑问', '嘘', '晕', '折磨', '衰', '骷髅', '敲打', '再见', '擦汗', '抠鼻', '鼓掌', '糗大了', '坏笑', '左哼哼', '右哼哼', '哈欠', '鄙视', '委屈', '快哭了', '阴险', '亲亲', '吓', '可怜', '菜刀', '西瓜', '啤酒', '篮球', '乒乓', '咖啡', '饭', '猪头', '玫瑰', '凋谢', '示爱', '爱心', '心碎', '蛋糕', '闪电', '炸弹', '刀', '足球', '瓢虫', '便便', '月亮', '太阳', '礼物', '拥抱', '强', '弱', '握手', '胜利', '抱拳', '勾引', '拳头', '差劲', '爱你', 'NO', 'OK', '爱情', '飞吻', '跳跳', '发抖', '怄火', '转圈', '磕头', '回头', '跳绳', '挥手', '激动', '街舞', '献吻', '左太极', '右太极']
      let index = list.indexOf(word)
      return `<img src="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/${index}.gif" align="middle">`
    }
  }
}
</script>

<style type="text/scss" rel="stylesheet/scss" lang="scss" scoped>
  .noticeDiv {
    width: 90%;
    margin: auto;
    .topDiv {
      display: flex;
      flex-direction: row;
      padding-top: 10px;
      padding-bottom: 10px;
      .inputDiv {
        width: 250px;
        padding-right: 10px;
      }
    }
    .block {
      padding: 10px;
      text-align: center;
    }
  }
</style>
