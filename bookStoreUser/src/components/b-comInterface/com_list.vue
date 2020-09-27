<template>
  <div>
    <mt-header title="全部评论" :style="getBack">
      <a @click="toBack" slot="left">
        <mt-button icon="back">返回</mt-button>
      </a>
      <mt-button icon="more" slot="right"></mt-button>
    </mt-header>

    <div style="width:75%;margin: 5px 10px;">
      <el-button type="warning" round @click="toPath('/allCom')">全部</el-button>
      <el-button type="warning" round @click="toPath('/picCom')">有图</el-button>
      <el-button type="warning" round @click="toPath('/noPicCom')">无图</el-button>
    </div>
    <hr>

    <keep-alive>
      <router-view></router-view>
    </keep-alive>
  </div>

</template>

<script>
  export default {
    data() {
      return {
        bookId: '',
      }
    },

    methods: {
      getParams() {
        this.bookId = this.$route.query.bookId;
      },

      toBack() {
        this.$router.push({
          path: '/comInterface',
          query: {
            bookId: this.bookId
          }
        });
      },

      toPath(path) {
        this.$router.push(path)
      }
    },

    created() {
      this.getParams()
    },

    computed: {
      getBack() {
        return {
          'background-color': this.$store.state.themeColor
        }
      }
    },
  }
</script>

<style scoped>

</style>
