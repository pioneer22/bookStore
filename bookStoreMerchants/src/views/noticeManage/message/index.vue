<template>
  <div style="width: 80%;margin: auto">
    <div style="padding:10px 10px 10px 0px">
      <el-tabs type="border-card">
        <el-tab-pane label="聊天">
          <el-table
            :data="chatMess"
            :default-sort = "{prop: 'sendTime', order: 'descending'}"
            :height="height"
            border
            style="width: 100%">
            <el-table-column
              prop="userId"
              label="用户Id"
              width="220"
              sortable>
            </el-table-column>
            <el-table-column
              prop="sendTime"
              label="发送时间"
              width="220"
              sortable>
              <template slot-scope="scope">
                {{ scope.row.sendTime | formatDate }}
              </template>
            </el-table-column>
            <el-table-column
              prop="context"
              label="内容"
              width="220">
              <template slot-scope="scope">
                <div v-html="scope.row.context.replace(/\#[\u4E00-\u9FA5]{1,3}\;/gi, emotion)"></div>
              </template>
            </el-table-column>
            <el-table-column
              align="center"
              label="操作">
              <template slot-scope="scope">
                <el-button type="primary" icon="el-icon-switch-button" circle @click="toRead(scope.row.chatId,1)"></el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>

        <el-tab-pane label="订单">
          <el-table
            :data="orderMess"
            :default-sort = "{prop: 'orderId', order: 'descending'}"
            :height="height"
            border
            style="width: 100%">
            <el-table-column
              prop="orderId"
              label="订单Id"
              width="220"
              sortable>
            </el-table-column>
            <el-table-column
              prop="orderAllprice"
              label="订单总价"
              width="150"
              sortable>
              <template slot-scope="scope">
                ￥{{ scope.row.orderAllprice }}
              </template>
            </el-table-column>
            <el-table-column
              prop="orderTime"
              label="订单时间"
              width="220"
              sortable>
              <template slot-scope="scope">
                {{ scope.row.orderTime | formatDate }}
              </template>
            </el-table-column>
            <el-table-column
              prop="userLeaveMess"
              label="留言信息"
              width="180">
            </el-table-column>
            <el-table-column
              align="center"
              label="操作">
              <template slot-scope="scope">
                <el-button type="primary" icon="el-icon-switch-button" circle @click="toRead(scope.row.orderId,2)"></el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
import { reqNoReadMess, turnRead, turnKnow } from '../../../api/notice'
export default{
  data() {
    return {
      chatMess: [],
      orderMess: [],
      tab: '1',
      height: 250,
    }
  },
  created() {
    this.getMessage()
    this._getHeight()
  },
  methods: {
    async getMessage() {
      const businessId = this.$store.state.admin.business.businessId
      const result = await reqNoReadMess( businessId )
      this.chatMess = result.data.unReadChat
      this.orderMess = result.data.unReadOrder
    },

    _getHeight() {
      const height = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight
      this.height = height - 150 + 'px'
    },

    async toRead(Id,tag) {
      if (tag === 1) {
        const result = await turnRead(Id)
        if (result === 1) {
          this.$store.dispatch('message/saveCount')
          this.getMessage()
        }
      } else {
        const result = await turnKnow(Id)
        if (result === 1) {
          this.$store.dispatch('message/saveCount')
          this.getMessage()
        }
      }
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

<style scoped>

</style>
