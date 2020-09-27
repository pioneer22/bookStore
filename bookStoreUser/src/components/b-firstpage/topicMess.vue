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

    <img :src="topic.topicImg" style="max-width: 100%"/>

    <div>
      <div v-for="(item,index) in topic.books" :class="{double:index%2 == 0}" class="odd">

        <div class="itemDiv" v-if="(index%2 == 0)">
          <div style="width: 25%;padding: 5px">
            <img :src="item.bookImg" style="max-width: 100%"/>
          </div>
          <div class="messageDiv">
            <span class="messageDiv_span1">
              <span class="book_span">{{item.bookName}}</span>
              作者：{{item.bookAuthor}}
            </span><br>
            <span class="intro_span">
              {{item.bookIntro}}
            </span><br>
            <span class="include_span">
              售价：<span class="price_span">{{item.bookPrice}}</span>
              <span class="buy_span" @click="goShop(item.bookId)">立即购买</span>
            </span>
          </div>
        </div>

        <div class="itemDiv" v-if="(index%2 == 1)">
          <div class="messageDiv">
            <span class="messageDiv_span1">
              <span class="book_span">{{item.bookName}}</span>
              作者：{{item.bookAuthor}}
            </span><br>
            <span class="intro_span">
              {{item.bookIntro}}
            </span><br>
            <span class="include_span">
              售价：<span class="price_span">{{item.bookPrice}}</span>
              <span class="buy_span" @click="goShop(item.bookId)">立即购买</span>
            </span>
          </div>
          <div style="width: 25%;padding: 5px">
            <img :src="item.bookImg" style="max-width: 100%"/>
          </div>
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
        topic: []
      }
    },

    methods: {
      goBack() {
        // this.$router.push({
        //   path: '/firstPage/topic'
        // })
        this.$router.go('-1')
      },

      goShop(bookId) {
        this.$router.push({
          path: '/comInterface',
          query: {
            bookId: bookId,
          }
        })
      }
    },

    computed: {
      getBack() {
        return {
          'background-color': this.$store.state.themeColor
        }
      }
    },

    created() {
      this.topic = this.$store.state.topicMess[this.$route.query.index]
      this.title = this.topic.topicName + '图书专题'
    }
  }
</script>

<style scoped>
  .odd {
    background: #CEFFFF;
  }

  .double {
    background: #FFFFBF;
  }

  .itemDiv {
    display: flex;
    flex-direction: row;
    margin-top: 5px;
    padding: 5px 0px;
  }

  .messageDiv {
    width: 75%;
    font-size: 14px;
    color: #A1895F;
    padding: 8px 5px;
  }

  .messageDiv_span1 {
    border: 0.5px solid #E8B229;
    width: 100%;
    display: inline-block;
    height: 22px;
    line-height: 22px;
    overflow: hidden;
  }

  .book_span {
    background: #E8B229;
    color: white;
    display: inline-block;
    height: 100%;
    padding: 0px 6px;
  }

  .intro_span {
    display: inline-block;
    height: 50%;
  }

  .include_span {
    display: inline-block;
    width: 80%;
  }

  .price_span {
    font-size: 20px;
    color: #DF7236;
  }

  .buy_span {
    border: 0.5px solid #C38D79;
    padding: 2px 4px;
    background: #FFF2D1;
    color: #C58E7B;
    border-radius: 2px;
    float: right;
  }
</style>
