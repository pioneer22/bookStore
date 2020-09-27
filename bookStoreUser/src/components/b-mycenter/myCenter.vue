<template xmlns:0 xmlns:0>

  <div style="width: 100%;">
    <div class="re_top" :style="getBack">
      <img :src="top_pic"/>
      <span class="tologin" @click="goto('/login')" v-if="!isLogin">{{name}}</span>
      <span class="tologin" v-else>{{name}}</span>
    </div>

    <div class="myOrder" @click="goPath('/myCenter/orderStatu',4)">
      <span class="span_left">我的订单</span>
      <span class="span_right">全部订单
           <i class="el-icon-arrow-right"></i>
      </span>
    </div>

    <div style="border-bottom: 1px solid gray">
      <van-tabbar>
        <van-tabbar-item
          v-for="(item,index) in pictures"
          :key="index"
          :info="orderStatu[index]>0?orderStatu[index]:''"
          @click="goPath('/myCenter/orderStatu',index)"
        >
          <span>{{item.title}}</span>
          <img
            slot="icon"
            :src="item.url"
          >
        </van-tabbar-item>
      </van-tabbar>
    </div>

    <div @click="goPath(item.link)" class="bottom_div" v-for="(item,index) in bottoms" :key="index">
      <div class="bottom_list">
        <img :src="item.url"/>
        <span class="span_title">{{item.title}}</span>
      </div>
      <div>
        <i class="el-icon-arrow-right"></i>
      </div>
    </div>
    <div style="height: 45px;"></div>
  </div>
</template>

<script>
  import {getOrderStatuNum} from "../../api";
  import {Toast, Dialog} from 'vant';
  import {MessageBox} from 'mint-ui'

  export default {
    data() {
      return {
        pictures: [
          {
            url: require("./images/withoutpay.png"),
            title: '待付款',
          },
          {
            url: require("./images/withoutget.png"),
            title: '待收货',
          },
          {
            url: require("./images/withoutcom.png"),
            title: '待评价',
          },
          {
            url: require("./images/reget.png"),
            title: '退换货',
          },
        ],

        bottoms: [
          {
            url: require("./images/personalData.png"),
            title: '个人资料',
            link: '/myCenter/personData'
          },
          {
            url: require("./images/changepass.png"),
            title: '修改密码',
            link: '/myCenter/changePass'
          },
          {
            url: require("./images/collect.png"),
            title: '收藏夹',
            link: '/myCenter/collect'
          },
          {
            url: require("./images/address.png"),
            title: '收货地址',
            link: '/myCenter/receiveAddress'
          },
          {
            url: require("./images/coupon.png"),
            title: '优惠卷',
            link: '/myCenter/myCoupon'
          },
          {
            url: require("./images/service.png"),
            title: '客服',
            link: '/myCenter/chat'
          },
          {
            url: require("./images/set.png"),
            title: '设置',
            link: ''
          },
          {
            url: require("./images/about.png"),
            title: '关于书店',
            link: '/myCenter/aboutStore'
          },
          {
            url: require("./images/exit.png"),
            title: '退出',
            link: 'exit'
          }
        ],
        active: 0,
        orderStatu: [],
      }
    },

    computed: {
      top_pic() {
        return this.$store.state.user.userImg ? this.$store.state.user.userImg : "/api/Img/my.jpg"
      },
      name() {
        return this.$store.state.user.userName ? this.$store.state.user.userName : "登陆/注册"
      },
      userId() {
        return this.$store.state.user.userId ? this.$store.state.user.userId : "000000"
      },
      isLogin() {
        return this.$store.state.user.userId ? true : false
      },

      getBack() {
        return {
          'background-color': this.$store.state.themeColor
        }
      }
    },

    methods: {
      goto(path) {
        this.$router.push(path);
      },

      goPath(path, index) {

        if (index != null)
          index == 4?this.$store.dispatch('saveSelected',0):this.$store.dispatch('saveSelected',index+1);
        //退出
        if (path == 'exit') {
          if (Object.keys(this.$store.state.user).length > 0) {
            MessageBox.confirm('确定退出登陆?').then(action => {
              this.$store.dispatch('saveUser', {});
              this.$store.dispatch('saveShopCarNum', '');
              Toast.success('成功退出');
              this.orderStatu = [];
              this.$router.push({
                path: '/myCenter'
              });
            }).catch(() => {
              Toast('取消退出');
            });

            return;
          } else
            Toast.fail('还未登陆');

        }
        if (Object.keys(this.$store.state.user).length > 0) {
          // if(index != null)//传递跳转下标

          this.$router.push(path)
        }
        else
          Toast.fail('请先登陆');
      },

      async getOrderNum() {
        if (Object.keys(this.$store.state.user).length > 0) {
          let userId = this.$store.state.user.userId;
          let result = await getOrderStatuNum(userId);
          if (result.statu == 0) {
            this.orderStatu = result.data;
          }
        }
      }

    },

    created() {
      this.getOrderNum();
    }

  }
</script>

<style scoped>
  .re_top {
    width: 100%;
    display: flex;
    align-items: center;
    height: 100px;
    /*background-color: #E6A23C;*/
  }

  .re_top img {
    margin-left: 10%;
    border-radius: 32px;
    width: 64px;
    height: 64px;
  }

  .tologin {
    font-size: 25px;
    font-weight: bold;
    color: white;
    margin-left: 15px;
  }

  .myOrder {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    padding: 10px 5px;
    border-bottom: 0.5px solid gray;
  }

  .span_left, .span_right {
    font-weight: bold;
  }

  .span_left {
    padding-left: 5px;
  }

  .item_div {
    display: flex;
    flex-direction: row;
  }

  .rou-link {
    flex: 1;
    font-size: 13px;
    color: black;
  }

  .van-tabbar--fixed {
    position: relative;
  }

  .rou-link img {
    width: 40px;
    height: 40px;
  }

  .bottom_div {
    font-family: "Arial Unicode MS";
    font-size: 17px;
    border-bottom: 0.5px solid #ebedf0;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
    padding: 5px;
  }

  /*下面的标题*/
  .span_title {
    color: gray;
    margin-left: 10px;
  }

  .bottom_list {
    display: flex;
    align-items: center;
    padding-left: 5px;
  }

  .van-tabbar--fixed {
    padding-top: 5px;
    padding-bottom: 5px;
  }

  .van-tabbar-item {
    color: black;
  }

</style>
