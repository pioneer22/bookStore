<template>
  <div>
    <page-header></page-header>
    <div style="height: 48px;"></div>
    <swipe></swipe>

    <div class="iconDiv">
      <div v-for="(item,index) in funcIcon" class="circle" :style="setHeight" @click="goIcon(index)">
        <img :src="item.img" style="max-width: 64%;max-height: 64%;" v-show="index>0"/>
        <colorPicker v-model="color" v-on:change="headleChangeColor" v-if="index == 0"></colorPicker>
        <span style="font-size: 10px;color: gray">{{item.title}}</span>
      </div>
    </div>

    <div class="morningPaper">
      <span class="newMess">书店早报</span>
      <van-notice-bar
        :text="all_mess[i%all_mess.length]"
        left-icon="volume-o"
        style="width: 65%"
      />
    </div>

    <div class="picDiv">
      <div class="pic_right">
        <div v-for="(book,index) in books" :key="index" style="width: 49.5%">
          <img :src="book.url" @click="toTopic(book.index)"/>
        </div>
      </div>
    </div>

    <div class="titleDiv">
      <img src="./images/selection.png" style="margin-left: 10px;"/>
      <span class="good_book">热销图书</span>
    </div>

    <div class="scroll_out">
      <ul>
        <li v-for="(item,index) in sellWell" @click="goShop(item.bookId)">
          <img :src="item.bookImg" style="width: 80px;height: 80px;"/><br>
          <span class="bookName">{{item.bookName}}</span><br>
          <span class="bookPrice">￥{{item.bookPrice}}</span>
        </li>
      </ul>
    </div>

    <div class="titleDiv">
      <img src="./images/newbook.png" style="margin-left: 10px;"/>
      <span class="good_book">新书速递</span>
    </div>

    <div class="scroll_out">
      <ul>
        <li v-for="(item,index) in newBook" @click="goShop(item.bookId)">
          <img :src="item.bookImg" style="width: 80px;height: 80px;"/><br>
          <span class="bookName">{{item.bookName}}</span><br>
          <span class="bookPrice">￥{{item.bookPrice}}</span>
        </li>
      </ul>
    </div>
    <div style="width: 100%;height: 55px;"></div>
  </div>
</template>

<script>
  import PageHeader from "./component/pageHeader";
  import swipe from "./component/swipe";

  import {getMorning, reqSellWell, getSort, reqTopic} from "../../api";

  export default {
    data() {
      return {
        i: 0,
        all_mess: [],
        books: [
          {
            url: require("./images/firstone.jpg"),
            index: 1
          }
          ,
          {
            url: require("./images/secondone.jpg"),
            index: 2
          },
          {
            url: require("./images/thirdone.jpg"),
            index: 3
          },
          {
            url: require("./images/fourthone.jpg"),
            index: 4
          }
        ],
        sellWell: [],//存放热销图书
        newBook: [],//存放新书
        funcIcon: [
          {
            img: require("./images/theme.png"),
            title: '主题换肤',
          },
          {
            img: require("./images/sort.png"),
            title: '排行榜',
            url: '/firstPage/sort'
          },
          {
            img: require("./images/volume.png"),
            title: '优惠卷',
            url: '/firstPage/volume'
          },
          {
            img: require("./images/topic.png"),
            title: '专题',
            url: '/firstPage/topic'
          },
        ],
        setHeight: {
          height: ''
        },
        color: '#E6A23C'
      }
    },

    created() {
      let isHave = Object.keys(this.$store.state.morning_paper).length ? this.$store.state.morning_paper : 0;
      if (isHave == 0) {
        this.getMornings();
      } else {
        this.$store.state.morning_paper.forEach(item => {
          this.all_mess.push(item.paperMess);
        });
      }
      this.getSellWell();
      this._getHeight();
      this.getClassify();
    },

    computed: {
      bookmess() {
        return this.$store.state.bookmess;
      }
    },

    watch: {
      bookmess: function () {
        for (let i = 0; i < 5; i++)
          this.newBook.push(this.$store.state.bookmess[i]);
      }
    },

    mounted() {
      this.i = setInterval(this.getNum, 3500) // 控制早报信息循环显示
    },

    beforeDestroy() {
      clearInterval(this.i);
    },

    methods: {
      //获取早报
      async getMornings() {
        let result = await getMorning();
        if (result.statu == 0) {
          //将早报信息存到vueX
          this.$store.dispatch('saveMorning', result.data);
          result.data.forEach(item => {
            this.all_mess.push(item.paperMess);
          });
          console.log(result.msg)
        } else {
          console.log('获取早报失败！')
        }
      },

      getNum() {//早报更新
        this.i++;
      },

      toTopic(index) {
        this.$router.push({
          path: '/firstPage/topic/topicMess',
          query: {
            index: index,
          }
        });
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

      async getSellWell() {//热销图书
        let result = await reqSellWell();
        if (result.statu == 0) {
          this.sellWell = this.sellWell.concat(result.data);
          console.log(result.msg);
        } else {
          console.log(result.error);
        }
      },

      _getHeight() {
        let width = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
        this.setHeight.height = width * 0.2 + 'px';
      },

      goIcon(index) {
        if(index == 0)
          return;
        this.$router.push({
          path: this.funcIcon[index].url
        })
      },

      //获得分类
      async getClassify() {
        let result = await getSort();
        if (result.statu == 0) {
          this.$store.dispatch('saveClassified', result.data.classified);
          let classified = [];
          result.data.classified.forEach((item, index) => {
            classified[index] = this.sortByKey(result.data[item], 'bookSale')
          })
          this.$store.dispatch('saveClassifiedData', classified);
          console.log(result.msg)
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

      //更换主题颜色
      headleChangeColor(){
        this.$store.dispatch('saveThemeColor',this.color)
      },

      async getTopic() { // 获取专题
        const result = await reqTopic()
        if (result.statu === 0) {
          this.$store.dispatch('saveTopic',result.data)
        }
      }
    },

    components: {
      PageHeader, swipe
    }
  }
</script>

<style scoped>
  .newMess {
    color: #E6A23C;
    font-family: "Arial Unicode MS";
    font-size: 14px;
    font-weight: bold;
    margin-left: 10px;
    margin-right: 5px;
  }

  .allMess {
    font-size: 16px;
    font-family: "Arial Unicode MS";
    display: inline-block;
    margin-left: 5px
  }

  .picDiv {
    width: 100%;
    background: #CCCBCB;
    padding-top: 1%;
  }

  .pic_right {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: space-between;
  }

  .titleDiv {
    width: 100%;
    padding: 5px 0px;
    border-bottom: 0.5px solid #CCCBCB;
    border-top: 0.5px solid #CCCBCB;
  }

  .good_book {
    font-size: 18px;
    font-family: Bahnschrift;
    font-weight: bold;
  }

  img {
    width: auto;
    height: auto;
    max-width: 100%;
    max-height: 100%;
  }

  .scroll_out {
    width: 100%;
    overflow: hidden;
    height: 160px;
  }

  .scroll_out ul {
    padding: 0 5px;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    overflow-x: auto;
    height: 170px;
  }

  .scroll_out li {
    text-align: center;
    font-size: 16px;
    -ms-flex-negative: 0;
    flex-shrink: 0;
    padding: 10px;
    width: 100px;
    margin: 0px;
    list-style: none;
  }

  .morningPaper {
    display: flex;
    flex-direction: row;
    align-items: center;
    background-color: #fffbe8;
  }

  .circle {
    width: 20%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    border-radius: 50%;
    background: white;
    margin-left: 4%;
  }

  .iconDiv {
    display: flex;
    padding: 5px 0px;
    flex-direction: row;
    background: #EEEDED;
  }

  .leftDiv{
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .bookName{
    color: gray;
    font-size: 14px;
    height: 23%;
    display: inline-block;
  }

  .bookPrice{
    color: red;
    font-weight: bold;
  }
</style>
<style>
  .colorBtn[data-v-11842410]{
    background: url("./images/theme.png") !important;
    background-size: 48px 48px !important;
  }

  .m-colorPicker .colorBtn[data-v-11842410] {
    width: 48px;
    height: 48px;
  }

  .box[data-v-11842410]{
    z-index: 100 !important;
  }
</style>
