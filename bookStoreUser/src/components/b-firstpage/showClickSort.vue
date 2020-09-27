<template>
  <div>
    <div>
      <mt-header :title="title" :fixed="true" :style="getBack">
        <div @click="goBack" slot="left">
          <mt-button icon="back">返回</mt-button>
        </div>
      </mt-header>
      <div style="height: 40px"></div>
    </div>

    <div v-for="(item,index) in classifiedData" @click="goShop(item.bookId)">
      <div class="contentDiv">
        <img :src="item.bookImg" :style="setHeight" class="bookImg1"/>
        <div :style="setHeight" class="topDiv">
          <span style="flex: 1">{{item.bookName}}</span>
          <span class="topDiv_span">￥：{{item.bookPrice}}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        title: '',
        classifiedData: [],//分类数据
        setHeight: {
          height: ''
        }
      }
    },

    methods: {
      goBack() {
        this.$router.push({
          path: '/firstPage/sort'
        })
      },

      getData() {
        let ArrayIndex = this.$route.query.index;
        this.title = this.$store.state.classified[ArrayIndex] + '图书畅销榜';
        this.classifiedData = this.$store.state.classifiedData[ArrayIndex];
      },

      //设置图片高度
      _getHeight() {
        let width = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
        this.setHeight.height = width * 0.225 + 'px';
      },

      //跳转商品详情页面把图书id传过去
      goShop(bookId) {
        this.$router.push({
          path: '/comInterface',
          query: {
            bookId: bookId
          }
        })
      },
    },

    created() {
      this.getData();
      this._getHeight();
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
  .contentDiv {
    display: flex;
    flex-direction: row;
    width: 90%;
    margin: auto;
    margin-top: 2%;
    background: #ECECEC;
    padding: 10px 5px;
    border-radius: 5px;
  }

  .bookImg1 {
    max-width: 25%;
    margin-left: 1%;
    flex: 1;
  }

  .topDiv {
    display: flex;
    flex-direction: column;
    padding-left: 10px;
    flex: 3;
  }

  .topDiv_span {
    flex: 1;
    display: flex;
    justify-content: flex-end;
    align-items: flex-end;
    color: red;
    font-size: 14px;
  }
</style>
