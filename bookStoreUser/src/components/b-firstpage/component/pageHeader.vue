<template>
  <div class="top" :style="getBack">
    <!--回车事件可以，搜索事件不行-->
    <van-search
      v-model="search_mess"
      @search="onSearch"
      @cancel="onSearch"
      placeholder="请输入搜索关键词"
      :background="backColor"
      class="vSearch"
      @click="toSearch"></van-search>

    <div class="top_div" @click="toChat">
      <el-badge :is-dot="hadMess" style="margin-top: 5px;margin-right: 2px" type="danger">
        <img src="../images/message.png" style="width:90%;"/>
      </el-badge>
    </div>
  </div>

</template>

<script>
  import {Dialog} from 'vant'
  import {mapGetters} from 'vuex'

  export default {
    data() {
      return {
        search_mess: '',
        istabBar: false,
        hadMess: true
      }
    },

    created() {
      this.$store.dispatch("getMessCount")
    },

    computed: {
      backColor() {
        return this.$store.state.themeColor;
      },

      getBack() {
        return {
          'background': this.$store.state.themeColor
        }
      },

      ...mapGetters([
        'isHad'
      ])

    },

    methods: {
      onSearch() {
        console.log('我服了你的搜索！');
      },

      toSearch() {
        this.$router.push({
          path: '/firstPage/search'
        })
      },

      toChat() {
        if (Object.keys(this.$store.state.user).length > 0)
          this.$router.push({
            path: '/firstPage/message'
          })
        else
          Dialog.confirm({
            title: '登陆后可用',
            message: '是否跳转登陆界面？'
          }).then(() => {
            this.$router.push({
              path: '/login'
            })
          }).catch(() => {
            // on cancel
          });
      }
    }
  }
</script>

<style scoped>
  .vSearch {
    padding: 7px 0px 7px 5px;
    flex: 8;
    height: 48px;
  }

  .top {
    width: 100%;
    padding-left: 2px;
    position: fixed;
    z-index: 40;
    display: flex;
    flex-direction: row;
    align-items: center;
    /*background: #E6A23C;*/
  }

  .top_div {
    display: flex;
    flex: 1;
    align-items: center;
    justify-content: center;
    padding-right: 3px
  }
</style>
