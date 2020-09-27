<template>
  <div>
    <div class="contentDiv">
      <div class="userDiv">
        <div class="searchDiv">
          <el-input
            placeholder="搜索"
            v-model="input">
            <i slot="prefix" class="el-input__icon el-icon-search" @click="searchUser"></i>
          </el-input>
        </div>
        <div>
          <div class="itemDiv" @click="startChat('0')">
            <span class="el-icon-s-promotion"></span>
            <span>群发</span>
          </div>
          <div class="itemDiv" @click="show = !show">
            <span class="el-icon-caret-right" v-if="!show"></span>
            <span class="el-icon-caret-bottom" v-if="show"></span>
            <span>我的用户</span>
          </div>
        </div>
        <div v-if="show" class="userItem">
          <div v-for="(item,index) in showData" class="userMess" @click="startChat(showData[index])">
            <img :src="item.userImg" class="userImg"/>
            <span class="userName">{{ item.userName }}</span>
          </div>
        </div>
        <div v-if="!show" class="userItem">
          <div v-for="(item,index) in searchData" class="userMess" @click="startChat(searchData[index])">
            <img :src="item.userImg" class="userImg"/>
            <span class="userName">{{ item.userName }}</span>
          </div>
        </div>
      </div>

      <div class="chatDiv">
        <div class="topName">
          <span>{{ userName }}</span>
        </div>
        <div class="chatWindow" id="chatWindow">
          <div v-for="item in chatData" style="padding: 10px">
            <div style="text-align: center">
              <span class="sendTime">{{ item.sendTime | formatDate }}</span>
            </div>
            <div class="context" :class="{isBusiness : (item.statu === 2 || item.statu === 3)}">
              <div v-if="item.statu === 0 || item.statu === 1" style="padding-right: 10px">
                <img :src="userImg" class="chatImg">
              </div>
              <div v-if="item.statu === 2 || item.statu === 3" style="padding-left: 10px">
                <img :src="businessImg" class="chatImg">
              </div>
              <div class="chatContent" v-html="item.context.replace(/\#[\u4E00-\u9FA5]{1,3}\;/gi, emotion)"></div>
            </div>
          </div>
        </div>
        <div style="height: 35px">
          <img src="@/images/smile.svg" style="width: 35px;" @click="showEmoticon = !showEmoticon">
        </div>
        <div style="height:20%;position: relative">
          <el-input
            type="textarea"
            :rows="5"
            v-model="inputValue">
          </el-input>
          <el-button
            type="primary"
            size="small"
            style="position: absolute;right: 5px;bottom: 0px"
            @click="sendMess">发送(S)</el-button>
        </div>
      </div>
      <el-dialog
        :visible.sync="showEmoticon"
        :modal=false
        width="35%"
        center>
        <emotion @emotion="handleEmotion" :height="250"></emotion>
      </el-dialog>

      <!--<div class="text-place" v-if="false">-->
        <!-- /\#[\u4E00-\u9FA5]{1,3}\;/gi 匹配出含 #XXX; 的字段 -->
        <!--<p v-html="inputValue.replace(/\#[\u4E00-\u9FA5]{1,3}\;/gi, emotion)"></p>-->
      <!--</div>-->
    </div>
  </div>
</template>

<script>
import { reqUser, reqChat, saveMess, saveMessAll, searchUser } from '../../../api/notice'
import Emotion from '@/components/Emotion/index'

export default {
  name: '',
  data() {
    return {
      input: '',
      show: false,
      websock: null,
      user: [],
      showData: [],
      userName: '',
      userId: '',
      userImg: '',
      inputValue: '',
      showEmoticon: false, // 聊天表情
      chatData: [], // 聊天信息
      chatAll: [], // 暂存群发内容
      businessImg: '',
      searchData: []
    }
  },
  created() {
    this.initWebSocket()
    this.getUser()
  },
  methods: {
    scrollBottom() { // 聊天界面滑到最底部
      this.$nextTick(() => {
        document.getElementById("chatWindow").scrollTop = document.getElementById("chatWindow").scrollHeight
      })
    },

    async getUser() { // 请求用户
      this.businessImg = this.$store.state.admin.business.businessImg
      const result = await reqUser()
      if (result.statu === 0) {
        this.user = result.data
        this.showData = result.data
      }
    },

    async startChat(item) { // 发起聊天
      if (item != '0') {
        this.userName = item.userName
        this.userId = item.userId
        this.userImg = item.userImg
        const userId = this.userId
        const businessId = this.$store.state.admin.business.businessId
        const result = await reqChat(businessId,userId)
        if (result.statu === 0) {
          this.chatData = result.data
          this.scrollBottom()
        }
      } else { // 群发
        this.userName = ''
        this.chatData = this.chatAll
      }
    },

    async getChat() { // 重新获取聊天信息
      const businessId = this.$store.state.admin.business.businessId
      const userId = this.userId
      const result = await reqChat(businessId,userId)
      if (result.statu === 0) {
        this.chatData = result.data
      }
    },

    handleEmotion (i) {
      this.inputValue += i
    },

    // 将匹配结果替换表情图片
    emotion (res) {
      let word = res.replace(/\#|\;/gi,'')
      const list = ['微笑', '撇嘴', '色', '发呆', '得意', '流泪', '害羞', '闭嘴', '睡', '大哭', '尴尬', '发怒', '调皮', '呲牙', '惊讶', '难过', '酷', '冷汗', '抓狂', '吐', '偷笑', '可爱', '白眼', '傲慢', '饥饿', '困', '惊恐', '流汗', '憨笑', '大兵', '奋斗', '咒骂', '疑问', '嘘', '晕', '折磨', '衰', '骷髅', '敲打', '再见', '擦汗', '抠鼻', '鼓掌', '糗大了', '坏笑', '左哼哼', '右哼哼', '哈欠', '鄙视', '委屈', '快哭了', '阴险', '亲亲', '吓', '可怜', '菜刀', '西瓜', '啤酒', '篮球', '乒乓', '咖啡', '饭', '猪头', '玫瑰', '凋谢', '示爱', '爱心', '心碎', '蛋糕', '闪电', '炸弹', '刀', '足球', '瓢虫', '便便', '月亮', '太阳', '礼物', '拥抱', '强', '弱', '握手', '胜利', '抱拳', '勾引', '拳头', '差劲', '爱你', 'NO', 'OK', '爱情', '飞吻', '跳跳', '发抖', '怄火', '转圈', '磕头', '回头', '跳绳', '挥手', '激动', '街舞', '献吻', '左太极', '右太极']
      let index = list.indexOf(word)
      return `<img src="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/${index}.gif" align="middle">`
    },

    initWebSocket(){ // 初始化weosocket
      let userId = this.$store.state.admin.business.businessId
      const url = "ws://127.0.0.1:8080/api/websocket/"+userId
      this.websock = new WebSocket(url);
      this.websock.onmessage = this.websocketonmessage;
      this.websock.onopen = this.websocketonopen;
      this.websock.onerror = this.websocketonerror;
      this.websock.onclose = this.websocketclose;
    },
    websocketonopen(){ // 连接建立之后执行send方法发送数据
      // this.websocketsend(JSON.stringify(this.value));
      console.log("连接成功！")
    },
    websocketonerror(){ // 连接建立失败重连
      this.initWebSocket();
    },

    websocketonmessage(e){ //数据接收
      // const redata = JSON.parse(e.data)
      console.log(e.data)
      const temp = e.data.split("::")
      const mess = {
        "context": temp[1],
        "sendTime": temp[2],
        "statu": 0
      }
      this.chatData.push(mess)
      this.scrollBottom()
    },

    websocketsend(Data){ // 数据发送
      this.websock.send(Data)
    },

    websocketclose(e){  // 关闭
      console.log('断开连接',e)
    },

    async searchUser() { // 查找用户
      const searchKey = "%"+this.input+"%"
      const result = await searchUser(searchKey)
      if (result.statu === 0) {
        this.searchData = result.data
        this.input = ''
        this.show = false
      }
    },

    async sendMess() { // 发送信息
      const context = this.inputValue
      const date = new Date()
      const temp = {
        'sendTime': date,
        'statu': 2,
        'context': context
      }
      const businessId = this.$store.state.admin.business.businessId
      const userId = this.userId
      if (this.userName != '') { // 单发
        this.chatData.push(temp)
        const result = await saveMess(businessId,userId,context)
        let message = "1"+"::"+userId+"::"+this.inputValue
        // 发送websocket
        this.websocketsend(message)
        if (result === 1) {
          this.inputValue = ''
          this.getChat()
          console.log("保存信息成功！")
        }
      } else { // 群发
        this.chatData.push(temp)
        this.chatAll.push(temp)
        const result = await saveMessAll(businessId,context)
        let message = "2"+"::"+userId+"::"+this.inputValue
        // 发送websocket
        this.websocketsend(message)
        if (result === 1) {
          this.inputValue = ''
          console.log("保存信息成功！")
        }
      }
      this.scrollBottom()
    }
  },

  destroyed() {
    this.websock.close() // 离开路由之后断开websocket连接
  },

  components: {
    Emotion
  }
}
</script>

<style type="text/css" scoped>

  .contentDiv{
    display: flex;
    flex-direction: row;
    height: 500px;
    width: 70%;
    margin: auto;
    padding: 20px;
  }

  .userDiv{
    flex: 1;
    height: 500px;
    background: #EEEDED;
  }

  .searchDiv{
    text-align: center;
    padding: 10px;
  }
  .itemDiv{
    padding:5px 10px;
    cursor: pointer;
  }

  .userItem{
    height: 380px;
  }
  .userMess{
    padding: 10px 10px;
  }
  .userImg {
    width: 40px;
    height: 40px;
    border-radius: 20px;
  }
  .userName{
    padding-left:5px;
    position: relative;
    color: #7D7D7D;
    top: -5px;
  }
  .chatDiv{
    flex: 3;
    display:flex;
    flex-direction: column;
    height: 500px;
    border: 0.5px solid #F4F3F3;
  }
  .context {
    display: flex;
    flex-direction: row;
    padding: 10px 10px 0px 10px;
  }
  .isBusiness {
    display: flex;
    flex-direction: row-reverse;
    padding: 10px 10px 0px 10px;
  }

  .topName{
    height: 33px;
    line-height: 33px;
    padding: 5px;
    font-weight: bold;
    font-size: 16px;
  }

  .chatWindow{
    height: 63%;
    border-top: 0.5px solid #F6F5F5;
    overflow: auto;
  }

  .sendTime{
    padding: 5px;
    background: #FFF3B0;
    border-radius: 5px;
  }

  .chatImg{
    width: 35px;
    height: 35px;
    border-radius: 5px;
  }

  .chatContent{
    padding: 5px;
    background: #71C7D5;
    border-radius: 5px;
    max-width: 220px;
    cursor: pointer;
  }
</style>
