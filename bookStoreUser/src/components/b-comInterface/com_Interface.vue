<template>
  <div>
    <div>
      <mt-header :title="bookmess.bookName" :fixed="true" :style="getBack">
        <div @click="goBack" slot="left">
          <mt-button icon="back">返回</mt-button>
        </div>
      </mt-header>
    </div>

    <div style="height: 40px"></div>

    <div class="imgSet" @click="getBigPic">
      <img :src="bookmess.bookImg" alt="" class="img">
    </div>

    <van-popup v-model="showPic">
      <div class="imgOut" :style="setWidth">
        <viewer>
          <img :src="bookmess.bookImg" alt="">
        </viewer>
      </div>
    </van-popup>

    <div class="all_div">
      <span class="price_span">￥{{bookmess.bookPrice}}</span><br>
      <span class="intro_span">{{bookmess.bookName}}: {{bookmess.bookIntro}}</span><br><br>
      <span class="express_span">快递：{{bookmess.bookPrice>69?'免运费':'10元'}}</span>
      <span class="volume_span" style="float: right">销量:{{bookmess.bookSale}}</span>
    </div>
    <hr>

    <div style="background-color: #e9e9e9;margin-bottom: 5px">
      <div class="para_div" @click="isShowPublish = true">
        <span class="para_span">参数</span>
        <span style="float: right"><i class="el-icon-arrow-right"></i></span>
      </div>
    </div>

    <van-popup v-model="isShowPublish" position="top" :overlay="true">
      <div class="center">
        <div style="width: 80%;margin: auto">
          <div v-for="(para,index) in paras" class="para_item" :key="index">
            <span style="display: inline-block;width: 80px;text-align: right">{{para}}</span>
            <span>{{bookParam[index]}}</span>
          </div>
          <div class="para_item" @click="isShowPublish = false" style="text-align: center;color: white">
            返回
          </div>
        </div>
      </div>
    </van-popup>

    <div style="background-color: #e9e9e9;" @click="open_allcom()">
      <div class="para_div">
        <span>宝贝评价({{commentNum>0?commentNum:0}})</span>
        <span class="com_span">全部评价<i class="el-icon-arrow-right"></i></span>
      </div>
    </div>

    <div>
      <div v-for="item in comments" class="comment_div" style="display: flex;flex-direction: row">
        <div style="color: gray;"><img :src="item.userImg" style="width: 40px;height: 40px;border-radius: 20px"></div>
        <div style="display: flex;flex-direction: column">
          <span style="margin-left: 10px"><span style="display: inline-block;padding-right: 10px">{{item.userName}}</span> {{item.comment.comTime | formatData}}</span>
          <span style="margin-left: 10px">{{item.comment.comMess}}</span>
        </div>
      </div>
    </div>

    <div class="bookAll" :style="getBack">
      图书详情
    </div>
    <hr>
    <div>
      找到图书详情信息
    </div>

    <div class="bottom">
      <div class="collect_span" @click=toggleCollect()>
        <span class="collect_span2">
          <img :src="collect"/>收藏
        </span>
      </div>

      <div class="collect_span" @click="toChat">
        <span class="collect_span2">
          <img src="./images/service.png"/>客服
        </span>
      </div>

      <div class="addShopCar" @click="addCar()">
        <span>加入购物车</span>
      </div>

      <div class="buy" :style="getBack" @click="toBuy()">
        <span>立即购买</span>
      </div>

    </div>
    <ul>
      <li></li>
      <li></li>
      <li></li>
      <li></li>
      <li></li>
      <li></li>
      <li></li>
      <li></li>
    </ul>

    <div style="height: 50px;"></div>
  </div>
</template>

<script>

  import moment from 'moment'
  import {getComments, getOneBooks, getCollect, toggleCollect, addShopCar} from "../../api";
  import {Toast} from 'vant';

  export default {
    data() {
      return {
        userId: '',
        bookId: '',//图书id
        bookmess: {},//图书对象
        comments: [],//评论数组
        commentNum: '',//总评论数
        paras: ['商品编号:', '书名:', '作者:', '出版时间:', '出版社:', '定价:'],
        bookParam: [],//参数数组

        isShowPublish: false,
        collect: '',//收藏
        showPic: false,//大图显示
        setWidth: {
          width: ''
        },
        show:false
      }
    },
    watch: {
      // comments() {
      //   if(this.comments.length>0) {
      //     this.show = true
      //   }
      // }
    },
    methods: {

      toChat() {
        this.$router.push({
          path: '/myCenter/chat'
        })
      },
      goBack() {
        // this.$router.go("-1");
        this.$router.push({
          path: '/firstpage'
        })
        this.$store.dispatch('changeActive', 0);
      },

      open_allcom() {//跳转全部评论
        this.$router.push({
          path: '/comInterface/comlist',
          replace: true,
          query: {
            bookId: this.bookId
          }
        })
      },

      async getComment() {//获取评价信息
        const {bookId} = this;
        let result = await getComments({bookId});
        if (result.statu == 0) {
          this.$store.dispatch('saveComment', result.data);
          //只显示两条数据
            if (result.data.length>2){
              this.comments = result.data.slice(0,2)
            } else {
              this.comments = result.data
            }
          this.commentNum = result.data.length;
          console.log(result.msg);
        } else {
          console.log('获取评论失败！');
        }
      },

      async getOneBook() {//获取当前图书信息
        const bookId = parseInt(this.bookId)
        let result = await getOneBooks(bookId);

        if (result.statu == 0) {
          console.log(result.msg);
          this.bookmess = result.data;
          this.bookParam = [this.bookmess.bookId, this.bookmess.bookName, this.bookmess.bookAuthor,
            moment(this.bookmess.bookTime).format('YYYY-MM-DD'), this.bookmess.bookPress, this.bookmess.bookPrice];
        } else {
          console.log("获取失败！");
        }
      },

      //获取收藏
      async getcollect() {
        const userId = this.$store.state.user.userId
        const bookId = parseInt(this.bookId)
        let result = await getCollect(userId,bookId);
        if (result == 0)
          this.collect = require('./images/nocollect.png');
        else
          this.collect = require('./images/havecollect.png');
      },

      //点击收藏
      async toggleCollect() {
        if (Object.keys(this.$store.state.user).length > 0) {
          if (this.collect == require('./images/nocollect.png'))
            this.collect = require('./images/havecollect.png');
          else
            this.collect = require('./images/nocollect.png');
          this.userId = this.$store.state.user.userId
          const {userId,bookId} = this;
          let result = await toggleCollect({userId,bookId});
          if (result == 1)
            Toast.success('收藏成功');
          else
            Toast.success('取消收藏成功');
        } else {
          Toast.fail('请先登陆');
        }
      },

      async addCar() {
        const bookId = parseInt(this.bookId);
        const userId = this.$store.state.user.userId
        let result = await addShopCar(userId,bookId);
        if (result == 1) {
          Toast.success('加购成功')
        } else {
          Toast.fail('加购失败');
        }
      },

      getBigPic() {
        this.showPic = true;
      },

      getParams() {//取到路由带过来的参数
        this.bookId = this.$route.query.bookId;

        this.getOneBook();
        this.getComment();
        if (Object.keys(this.$store.state.user).length > 0) {
          this.getcollect();
        } else {
          this.collect = require('./images/nocollect.png');
        }
      },

      //获取屏幕宽度
      _getWidth() {
        let width = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
        this.setWidth.width = width + 'px';
      },

      toBuy() { //点击立即购买
        if (Object.keys(this.$store.state.user).length > 0){
          let payorder = [];
          //判断哪些被选中
          var list = {
            bookId: this.bookmess.bookId,
            bookImg: this.bookmess.bookImg,
            bookName: this.bookmess.bookName,
            bookPrice: this.bookmess.bookPrice,
            bookIntro: this.bookmess.bookIntro,
            count: 1
          }
          payorder.push(list);
          //把要买的东西存到Vuex
          this.$store.dispatch("saveWantBuy", payorder);
          this.$router.push({
            path: '/shopcar/payOrder'
          })
        } else {
          Toast.fail('请先登陆!');
        }
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
      this.getParams();
      this._getWidth();
    },

  }
</script>

<style scoped>

  .imgSet {
    height: 200px;
    text-align: center;
    padding: 15px 0px;
  }

  .img {
    max-width: 100%;
    max-height: 100%;
  }

  .price_span {
    font-size: 28px;
    font-weight: bold;
    color: #c91518;
  }

  .intro_span {
    font-weight: bold;
  }

  .express_span, .volume_span {
    color: gray;
  }

  .para_div {
    color: gray;
    height: 40px;
    line-height: 40px;
    padding: 0 12px;
  }

  .com_span {
    float: right;
    color: red;
    font-weight: bold;
  }

  .comment_div {
    padding: 5px 12px;
    margin: 5px 0px;
    background: gainsboro;
    font-size: 14px;
    color: #333333;
  }

  .all_div {
    margin-bottom: 5px;
    padding: 0 12px;
  }

  .center {
    width: 100%;
    padding: 30px 5px;
  }

  .para_item {
    padding: 5px 5px;
    background-color: #E6A23C;
    margin: 5px 10px;
    border-radius: 5px;
  }

  .bookAll {
    text-align: center;
    height: 30px;
    line-height: 30px;
    /*background-color: #E6A23C;*/
    color: white;
  }

  .collect_span {
    width: 18%;
    text-align: center;
    font-size: 10px;
    font-weight: 100;
    height: 52px;
    background: #f7f7f7;
  }

  .buy, .addShopCar {
    text-align: center;
    width: 32%;
    height: 52px;
    line-height: 52px;
    color: #e9e9e9;
    font-size: 18px;
  }

  .addShopCar {
    background: #00bac7;
  }

  .collect_span2 {
    display: inline-block;
    width: 50%;
  }

  .bottom {
    position: fixed;
    width: 100%;
    left: 0;
    bottom: 0;
    z-index: 20;
    display: flex;
    flex-direction: row;
  }

  .imgOut {
    background: white;
    text-align: center;
  }
</style>
