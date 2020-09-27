<template>
  <div>
    <div>
      <mt-header title="图书销量排行版" :fixed="true" :style="getBack">
        <div @click="goBack" slot="left">
          <mt-button icon="back">返回</mt-button>
        </div>
      </mt-header>
      <div style="height: 40px"></div>
    </div>

    <div v-for="(item,index) in classifiedData" @click="toAll(index)">
      <div class="classifiedTop">
        <span style="font-weight: bold">{{classified[index]}}图书畅销榜</span>
        <span class="classifiedTop_span_search">查看全部<van-icon name="arrow"/></span>
      </div>

      <div class="contentDiv">
        <img :src="item[0].bookImg" :style="setHeight" class="bookImg1"/>
        <div :style="setHeight" class="topDiv">
          <span class="topSpan1">Top1.《{{item[0].bookName}}》</span>
          <span class="topDiv_span">Top2.《{{item[1].bookName}}》</span>
          <span class="topDiv_span">Top3.《{{item[2].bookName}}》</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import {getSort} from "../../api";
  import $ from 'jquery'

  export default {
    data() {
      return {
        classified: [],//存放分类
        classifiedData: [[{bookImg: '', bookName: '',}, {bookImg: '', bookName: ''}, {bookImg: '', bookName: ''}]],//分类数据
        setHeight: {
          height: ''
        }
      }
    },

    methods: {
      goBack() {
        this.$router.push({
          path: '/firstPage'
        })
      },

      async getBookSort() {
        let result = await getSort();
        if (result.statu == 0) {
          this.classified = result.data.classified;
          this.$store.dispatch('saveClassified', this.classified);
          this.classified.forEach((item, index) => {
            this.classifiedData[index] = this.sortByKey(result.data[item], 'bookSale')
          })
          this.$store.dispatch('saveClassifiedData', this.classifiedData);
        } else
          console.log('fail')
      },

      //根据对象某个字段降序排序
      sortByKey(array, key) {
        return array.sort((a, b) => {
          let x = a[key];
          let y = b[key];
          return ((x > y) ? -1 : ((x < y) ? 1 : 0));
        })
      },

      //设置图片高度
      _getHeight() {
        let width = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
        this.setHeight.height = width * 0.225 + 'px';
      },

      //跳转排行
      toAll(index) {
        this.$router.push({
          path: '/firstPage/sort/showClickSort',
          query: {
            index: index
          }
        })
      }
    },

    created() {
      this._getHeight();
      this.getBookSort();
      // this.resetFontSize($(".topSpan1"), this._setAdaption(), 11, 20);
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
  .classifiedTop {
    display: flex;
    justify-content: space-between;
    padding: 15px 5px;
  }

  .classifiedTop_span_search {
    display: flex;
    align-items: center;
    font-size: 12px;
    color: gray;
  }

  .contentDiv {
    display: flex;
    flex-direction: row;
    width: 90%;
    margin: auto;
    background: #ECECEC;
    padding: 10px 5px;
    border-radius: 5px;
  }

  .topDiv_span {
    color: gray;
    font-size: 11px;
  }

  .bookImg1 {
    max-width: 25%;
    margin-left: 1%;
    flex: 1;
  }

  .topDiv {
    display: flex;
    flex-direction: column;
    justify-content: center;
    padding-left: 10px;
    flex: 3;
  }

  .topSpan1 {
    color: red;
    /*font-size: 18px;*/
    font-weight: bold;
    display: flex;
    height: 50%;
    align-items: center;
  }
</style>
