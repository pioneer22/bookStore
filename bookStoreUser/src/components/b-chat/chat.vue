<template>
  <div>
    <div>
      <mt-header title="消息" :fixed="true" :style="getBack">
        <div @click="goBack" slot="left">
          <mt-button icon="back">返回</mt-button>
        </div>
      </mt-header>
      <div style="height: 40px"></div>
    </div>

    <div id="chatContent">
      <div v-for="item in message" style="text-align: center">
        <span class="time_span">{{item.sendTime | formatTime}}</span>
        <div class="leftDiv" :class="{rightDiv:(item.statu == 0 || item.statu == 1)}">
          <div v-if="(item.statu == 0 || item.statu == 1)" style="padding: 5px">
            <img :src="imgPic" class="imgSize">
          </div>
          <div v-if="(item.statu == 2 || item.statu == 3)" style="padding: 5px">
            <img :src="business.businessImg" class="imgSize"/>
          </div>
          <div class="contentDiv" v-html="item.context.replace(/\#[\u4E00-\u9FA5]{1,3}\;/gi, emotion)"></div>
        </div>
      </div>
    </div>

    <div v-if="emot" class="emot">
      <emotion @emotion="handleEmotion" :height="250"></emotion>
    </div>

    <div style="height: 47px"></div>
    <div class="sendDiv">
      <div>
        <img src="./images/voice.png"/>
      </div>
      <div style="flex: 6">
        <input type="text" v-model='value' class="sendInput"/>
      </div>
      <div @click="emot = !emot">
        <img src="./images/expression.png"/>
      </div>
      <div>
        <img src="./images/more.png" v-if="!isSend"/>
        <van-button type="warning" size="small" v-if="isSend" @click="sendMess">发送</van-button>
      </div>
    </div>
  </div>
</template>

<script>
  import {getChat, saveChat} from "../../api"
  import {Toast} from 'vant'
  import Emotion from '@/common/Emotion/index'

  export default {
    data() {
      return {
        message: [],
        business: {},
        imgPic: '',
        value: '',
        websock: null,
        emot: false
      }
    },

    created() {
      this.imgPic = this.$store.state.user.userImg;
      this.getChatMess();
      this.initWebSocket();
    },

    computed: {
      getBack() {
        return {
          'background-color': this.$store.state.themeColor
        }
      },

      isSend() {
        return this.value.length > 0 ? true : false;
      }
    },

    methods: {
      scrollBottom() { // 聊天界面滑到最底部
        this.$nextTick(() => {
          document.scrollingElement.scrollTop = document.scrollingElement.scrollHeight
        })
      },

      handleEmotion(i) {
        this.value += i
      },

      // 将匹配结果替换表情图片
      emotion(res) {
        let word = res.replace(/\#|\;/gi, '')
        const list = ['微笑', '撇嘴', '色', '发呆', '得意', '流泪', '害羞', '闭嘴', '睡', '大哭', '尴尬', '发怒', '调皮', '呲牙', '惊讶', '难过', '酷', '冷汗', '抓狂', '吐', '偷笑', '可爱', '白眼', '傲慢', '饥饿', '困', '惊恐', '流汗', '憨笑', '大兵', '奋斗', '咒骂', '疑问', '嘘', '晕', '折磨', '衰', '骷髅', '敲打', '再见', '擦汗', '抠鼻', '鼓掌', '糗大了', '坏笑', '左哼哼', '右哼哼', '哈欠', '鄙视', '委屈', '快哭了', '阴险', '亲亲', '吓', '可怜', '菜刀', '西瓜', '啤酒', '篮球', '乒乓', '咖啡', '饭', '猪头', '玫瑰', '凋谢', '示爱', '爱心', '心碎', '蛋糕', '闪电', '炸弹', '刀', '足球', '瓢虫', '便便', '月亮', '太阳', '礼物', '拥抱', '强', '弱', '握手', '胜利', '抱拳', '勾引', '拳头', '差劲', '爱你', 'NO', 'OK', '爱情', '飞吻', '跳跳', '发抖', '怄火', '转圈', '磕头', '回头', '跳绳', '挥手', '激动', '街舞', '献吻', '左太极', '右太极']
        let index = list.indexOf(word)
        return `<img src="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/${index}.gif" align="middle">`
      },

      goBack() {
        this.$router.go('-1')
      },

      async getChatMess() { // 获取聊天信息
        let userId = this.$store.state.user.userId;
        let result = await getChat(userId);

        if (result.statu == 0) {
          this.message = result.data.chat;
          this.business = result.data.business;
          this.scrollBottom()
        }
      },

      async sendMess() {
        if (this.value === '') {
          Toast.fail("发送内容不能为空")
          return
        }
        // let time = formatData(new Date());//发送时间
        let time = Date.now();//发送时间戳,不能通过对象发送date类型，会被转成String
        let context = this.value;
        let userId = this.$store.state.user.userId;
        let businessId = this.business.businessId;
        let statu = 0;
        let chat = {
          'sendTime': time,
          'context': context,
          'statu': statu,
          'userId': userId,
          'businessId': businessId
        };
        let message = "0::8888" + "::" + this.value
        // 发送websocket
        this.websocketsend(message)
        this.message.push(chat);
        let result = await saveChat(chat);
        if (result == 1) {
          this.scrollBottom()
          console.log("保存消息成功");
          this.value = "";
        }
      },

      initWebSocket() { //初始化weosocket
        let userId = this.$store.state.user.userId
        const url = "ws://127.0.0.1:8080/api/websocket/" + userId;
        this.websock = new WebSocket(url);
        this.websock.onmessage = this.websocketonmessage;
        this.websock.onopen = this.websocketonopen;
        this.websock.onerror = this.websocketonerror;
        this.websock.onclose = this.websocketclose;
      },
      websocketonopen() { //连接建立之后执行send方法发送数据
        // this.websocketsend(JSON.stringify(this.value));
        console.log("连接成功")
      },
      websocketonerror() {//连接建立失败重连
        this.initWebSocket();
      },
      websocketonmessage(e) { //数据接收
        const temp = e.data.split("::")
        const mess = {
          "context": temp[1],
          "sendTime": temp[2],
          "statu": 2
        }
        this.message.push(mess)
        this.scrollBottom()
      },
      websocketsend(Data) {//数据发送
        this.websock.send(Data);
      },
      websocketclose(e) {  //关闭
        console.log('断开连接', e);
      }
    },

    destroyed() {
      this.websock.close() //离开路由之后断开websocket连接
    },

    components: {
      Emotion
    }
  }
</script>

<style scoped>
  .time_span {
    padding: 3px 5px;
    background: #D9D9D9;
    color: white;
    display: inline-block;
    border-radius: 3px;
    margin-top: 5px;
  }

  .imgSize {
    max-width: 40px;
    max-height: 40px;
  }

  .leftDiv {
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    align-items: center;
    /*margin-top: 5px;*/
    padding: 5px 10px;
  }

  .contentDiv {
    margin-left: 5px;
    margin-right: 5px;
    padding: 10px;
    background: #EAEAEA;
    border-radius: 5px;
    max-width: 70%;
  }

  .rightDiv {
    flex-direction: row-reverse;
  }

  .sendInput {
    width: 95%;
    border: 1px solid #ccc;
    padding: 5px 0px;
    border-radius: 3px;
    padding-left: 5px;
    -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
    box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
    -webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow ease-in-out .15s;
    -o-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
    transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s
  }

  .sendInput:focus {
    border-color: #66afe9;
    outline: 0;
    -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px rgba(102, 175, 233, .6);
    box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px rgba(102, 175, 233, .6)
  }

  .sendDiv {
    display: flex;
    align-items: center;
    padding: 7px 0px;
    background: #E4E3E3;
    position: fixed;
    left: 0;
    bottom: 0;
    z-index: 10;
    width: 100%;
  }

  .sendDiv div {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .emot {
    position: fixed;
    bottom: 47px;
    width: 100%;
    background: white;
  }
</style>
