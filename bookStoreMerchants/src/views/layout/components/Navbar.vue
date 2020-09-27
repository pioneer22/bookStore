<template>
  <div class="navbar">
    <hamburger :toggle-click="toggleSideBar" :is-active="sidebar.opened" class="hamburger-container"/>
    <breadcrumb />

   <!-- <el-tooltip :content="('navbar.size')" effect="dark" placement="bottom">
      <size-select id="size-select" class="right-menu-item hover-effect" />
    </el-tooltip>-->

    <div class="right-menu">
      <template>
        <screenfull id="screenfull" class="right-menu-item hover-effect" />

        <message class="right-menu-item hover-effect"/>
      </template>

      <el-dropdown class="avatar-container right-menu-item hover-effect" trigger="click">
        <div class="avatar-wrapper">
          <img :src="icon" class="user-avatar">
          <i class="el-icon-caret-bottom" />
        </div>
        <el-dropdown-menu slot="dropdown" class="user-dropdown">
          <router-link class="inlineBlock" to="/">
            <el-dropdown-item>
              <span>主页</span>
            </el-dropdown-item>
          </router-link>
          <el-dropdown-item divided>
            <span style="display:block;" @click="logout">退出</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Breadcrumb from '@/components/Breadcrumb'
import Hamburger from '@/components/Hamburger'
import Screenfull from '@/components/Screenfull'
import Message from '@/components/Message'

export default {
  data() {
    return {
      websock: null
    }
  },

  created() {
    this.initWebSocket()
  },

  components: {
    Breadcrumb,
    Hamburger,
    Screenfull,
    Message
  },

  computed: {
    ...mapGetters([
      'sidebar'
    ]),

    icon() {
      return this.$store.state.admin.business.businessImg
    }

  },
  methods: {
    toggleSideBar() {
      this.$store.dispatch('ToggleSideBar')
    },
    logout() {
      // this.$store.dispatch('saveBusiness',{})
      sessionStorage.clear()
      this.$router.push({ path: '/Login' })
    },

    initWebSocket() { //初始化weosocket
      let userId = this.$store.state.admin.business.businessId
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
      this.$store.dispatch("message/saveCount")
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
  }
}
</script>

<style type="text/scss'" rel="stylesheet/scss" lang="scss" scoped>
.navbar {
  height: 50px;
  line-height: 50px;
  box-shadow: 0 1px 3px 0 rgba(0,0,0,.12), 0 0 3px 0 rgba(0,0,0,.04);
  .hamburger-container {
    line-height: 58px;
    height: 50px;
    float: left;
    padding: 0 10px;
  }
  .screenfull {
    position: absolute;
    right: 90px;
    top: 16px;
    color: red;
  }
/*  .avatar-container {
    height: 50px;
    display: inline-block;
    position: absolute;
    right: 35px;
    .avatar-wrapper {
      cursor: pointer;
      margin-top: 5px;
      position: relative;
      line-height: initial;
      .user-avatar {
        width: 40px;
        height: 40px;
        border-radius: 10px;
      }
      .el-icon-caret-bottom {
        position: absolute;
        right: -20px;
        top: 25px;
        font-size: 12px;
      }
    }
  }*/
  .right-menu {
    float: right;
    height: 100%;
    line-height: 50px;

    &:focus {
       outline: none;
    }

    .right-menu-item {
      display: inline-block;
      padding: 0 8px;
      height: 100%;
      font-size: 18px;
      color: #5a5e66;
      vertical-align: text-bottom;

      &.hover-effect {
        cursor: pointer;
        transition: background .3s;

        &:hover {
           background: rgba(0, 0, 0, .025)
        }
       }
     }

    .avatar-container {
      margin-right: 30px;

    .avatar-wrapper {
      margin-top: 5px;
      position: relative;

    .user-avatar {
      cursor: pointer;
      width: 40px;
      height: 40px;
      border-radius: 10px;
    }

    .el-icon-caret-bottom {
      cursor: pointer;
      position: absolute;
      right: -20px;
      top: 25px;
      font-size: 12px;
    }
    }
  }
 }
}
</style>

