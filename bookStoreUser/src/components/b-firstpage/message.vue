<template>
  <div>
    <div>
      <mt-header title="消息" :fixed="true" :style="getBack">
        <div @click="goBack" slot="left">
          <mt-button icon="back">返回</mt-button>
        </div>
        <mt-button icon="more" slot="right" @click="openPopup"></mt-button>
      </mt-header>
      <div style="height: 40px"></div>
    </div>

    <van-popup v-model="showClear">
      <div class="imgOut">
        <div class="operation" @click="clearMess">清空消息</div>
      </div>
    </van-popup>

    <div>
      <div v-if="this.unread.length>0">
        <div class="title_div">
          <span>未读消息（{{this.unread.length}}）</span>
          <span class="title_span" @click="tagAllRead"><img src="./images/tag.png" style="max-width: 25px"></span>
        </div>

        <div v-for="(item,index) in unread" class="messDiv" @touchstart="gotouchstart('0')" @touchmove="gotouchmove" @touchend="gotouchend">
          <div class="messDiv_img">
            <img src="./images/unread.png" style="max-width: 100%"/>
          </div>
          <div class="messDiv_content">
            <span style="overflow: hidden">{{item.sendMess}}</span>
            <span class="content_span">{{item.sendTime | formatTime}}</span>
          </div>

          <van-popup v-model="showPicOne">
            <div class="imgOut">
              <div class="operation" @click="tagOneRead(index)">标记已读</div>
              <div @click = "deleteMess('0',index)" class="operation">删除消息</div>
            </div>
          </van-popup>
        </div>
      </div>

      <div v-if="this.read.length>0">
        <div class="title_div">
          <span>已读消息（{{this.read.length}}）</span>
        </div>
        <div v-for="(item,index) in read" class="messDiv" @touchstart="gotouchstart('1')" @touchmove="gotouchmove" @touchend="gotouchend">
          <div class="messDiv_img">
            <img src="./images/read.png"/>
          </div>

          <div class="messDiv_content">
            <span style="overflow: hidden">{{item.sendMess}}</span>
            <span class="content_span">{{item.sendTime | formatTime}}</span>
          </div>

          <!--长按弹出选项-->
          <van-popup v-model="showPic">
            <div class="imgOut">
              <div @click = "deleteMess('1',index)" class="operation">删除消息</div>
            </div>
          </van-popup>
        </div>
      </div>
    </div>

    <div class="clearDiv" v-show="isClear">
      <img src="./images/clear.png"/><br>
      <span style="font-size: 16px;color: gray">没有消息</span>
    </div>
  </div>
</template>

<script>
  import {getMessage, tagRead ,deleteMessage,tagOneMessage,clearMessage} from "../../api";
  // import {formatDay} from "../../common/js/filter";
  import {Toast} from 'vant'

  export default {
    data() {
      return {
        showPicOne:false,
        showPic:false,
        showClear:false,
        isClear:false,
        read: [],
        unread: [],
      }
    },

    computed: {
      getBack() {
        return {
          'background-color': this.$store.state.themeColor
        }
      },

    },

    methods: {
      async getMess() {
        let userId = this.$store.state.user.userId;
        let result = await getMessage(userId);
        this.read = result.data.read;
        this.unread = result.data.unread;
      },

      // getTime(time) {
      //   return formatDay(time);
      // },

      //全部标记为已读
      async tagAllRead() {
        let userId = this.$store.state.user.userId;
        let result = await tagRead(userId);
        if (result == 1) {
          Toast('标记成功！');
          this.read.push(...this.unread);
          this.unread = [];
          this.$store.dispatch('getMessCount')
        } else
          console.log("标记失败！")
      },

      //删除单条消息
      async deleteMess(statu,index){
        let userId = this.$store.state.user.userId;
        //判断是删除未读还是删除已读
        if(statu === '0')
        {
          let paperId = this.unread[index].paperId;
          let result = await deleteMessage(userId,paperId);
          if(result === 1)
          {
            Toast.success('删除成功');
            this.unread.splice(index,1);
            this.showPicOne = false;
            this.isClear = (this.read.length == 0 && this.unread.length == 0)?true:false;
            this.$store.dispatch('getMessCount')
          }
        }else if(statu === '1')
        {
          let paperId = this.read[index].paperId;
          let result = await deleteMessage(userId,paperId);
          if(result === 1)
          {
            Toast.success('删除成功');
            this.read.splice(index,1);
            this.showPic = false;
            this.isClear = (this.read.length == 0 && this.unread.length == 0)?true:false;
            this.$store.dispatch('getMessCount')
          }
        }
      },

      //标记单挑消息为已读
      async tagOneRead(index){
        let userId = this.$store.state.user.userId;
        let paperId = this.unread[index].paperId;
        let result = await tagOneMessage(userId,paperId);
        if(result == 1)
        {
          Toast('标记成功');
          this.read.push(this.unread[index]);
          this.unread.splice(index,1);
          this.showPicOne = false;
          this.$store.dispatch('getMessCount')
        }
      },

      openPopup(){
        this.showClear = true;
      },

      //清空消息
      async clearMess(){
        let userId = this.$store.state.user.userId;
        let result = await clearMessage(userId);
        if(result === 1)
        {
          Toast('已清空');
          this.read = [];
          this.unread = [];
          this.showClear = false;
          this.isClear = true;
          this.$store.dispatch('getMessCount')
        }

      },

      //实现长按
      gotouchstart(statu) {
        clearTimeout(this.timeOutEvent);
        this.timeOutEvent = 0;
        this.timeOutEvent = setTimeout(() => {
          if(statu == '1')
            this.showPic = true;
          else if(statu == '0')
            this.showPicOne = true;
        }, 600);
      },


      //手释放，如果在600毫秒内就释放，则取消长按事件，此时可以执行onclick应该执行的事件
      gotouchend() {
        clearTimeout(this.timeOutEvent);
        if (this.timeOutEvent != 0) {
          //这里写要执行的内容（尤如onclick事件）
        }
      },

      //如果手指有移动，则取消所有事件，此时说明用户只是要移动而不是长按
      gotouchmove() {
        clearTimeout(this.timeOutEvent);//清除定时器
        this.timeOutEvent = 0;
      },

      goBack() {
        this.$router.go('-1')
      },
    },

    created() {
      this.getMess();
    }
  }
</script>

<style scoped>
  .title_div {
    font-size: 18px;
    font-weight: bold;
    display: flex;
    justify-content: space-between;
    flex-direction: row;
    align-items: center;
    width: 94%;
    margin: auto;
    padding: 5px 10px;
  }

  .title_span {
    display: flex;
    align-items: center;
  }

  .messDiv {
    display: flex;
    flex-direction: row;
    width: 94%;
    margin: auto;
    height: 60px;
    font-size: 16px;
    background: #EFEEEE;
    border-radius: 5px;
    margin-bottom: 5px;
  }

  .messDiv_img {
    width: 25%;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .messDiv_content {
    width: 75%;
    display: flex;
    flex-direction: column;
    justify-content: center;
  }

  .content_span {
    color: gray;
    font-size: 12px;
  }

  .imgOut {
    background: white;
    text-align: center;
    width: 200px;
    padding: 10px;
  }

  .operation{
    border-bottom: 1px solid gray;
    padding: 8px 0px;
  }

  .clearDiv{
    text-align: center;
    padding:30px 0px;
  }
</style>
