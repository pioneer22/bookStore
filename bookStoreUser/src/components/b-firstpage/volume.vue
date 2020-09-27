<template>
  <div>
    <div>
      <mt-header title="领卷中心" :fixed="true" :style="getBack">
        <div @click="goBack" slot="left">
          <mt-button icon="back">返回</mt-button>
        </div>
      </mt-header>
      <div style="height: 40px"></div>
    </div>

    <img src="./images/couponTop.jpg" style="max-width: 100%"/>

    <div v-for="item in category" class="outDiv">
      <div class="outDiv_div1">
        <img :src="item.couponImg"/>
      </div>

      <div class="outDiv_div2">
        <span class="outDiv_div2_span1">全网图书通用券</span>
        <span class="outDiv_div2_span2">
          <span class="couponMoney">{{item.couponMoney}}</span>
               &nbsp满{{item.couponUse}}可用
        </span>
      </div>

      <div class="outDiv_div3">
        <el-progress type="circle" :percentage="Math.round(item.couponGetnum/item.couponNum*100)"
                     :width="50"></el-progress>
        <div class="sureButton" @click="gainCoupons(item.couponId)">
          <span>立即领取</span>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
  import {getCoupon, gainCoupon} from "../../api";
  import {Toast} from 'mint-ui';

  export default {
    data() {
      return {
        category: [],
      }
    },

    methods: {
      goBack() {
        this.$store.dispatch('changeActive',0)
        this.$router.push({
          path: '/firstPage'
        })
      },

      async getCouponMess() {
        let result = await getCoupon();
        if (result.statu == 0) {
          this.category = result.data;
          console.log(result.msg);
        } else {
          console.log(result.error);
        }
      },

      //领取优惠卷
      async gainCoupons(couponId) {
        if (Object.keys(this.$store.state.user).length > 0) {
          let userId = this.$store.state.user.userId;
          let result = await  gainCoupon(userId, couponId);
          if (result == 1)
            Toast({
              message: '领取成功',
              iconClass: 'icon icon-success'
            });
          else if (result == 0)
            Toast({
              message: '请勿重复领取',
              iconClass: 'icon icon-warning'
            });
          else
            Toast({
              message: '领取失败',
              iconClass: 'icon icon-error'
            });
        } else
          Toast("请先登陆！");
      }
    },

    created() {
      this.getCouponMess();
    },

    computed: {
      getBack(){
        return {
          'background-color':this.$store.state.themeColor
        }
      }
    },

  }
</script>

<style scoped>
  .outDiv {
    background: url("./images/background.jpg");
    padding: 10px;
    margin-bottom: 10px;
    display: flex;
    flex-direction: row;
    align-items: center;
  }

  .outDiv_div1 {
    flex: 1;
    display: flex;
    justify-content: center;
  }

  .outDiv_div2 {
    display: flex;
    flex-direction: column;
    flex: 2;
    height: 70px;
  }

  .outDiv_div2_span1 {
    flex: 1;
    font-size: 14px;
  }

  .outDiv_div2_span2 {
    display: flex;
    flex: 1;
    align-items: flex-end;
    color: red;
  }

  .couponMoney {
    font-weight: bold;
    font-size: 25px;
  }

  .outDiv_div3 {
    display: flex;
    justify-content: center;
    flex-direction: column;
    align-items: center;
    flex: 1;
  }

  .sureButton {
    padding: 2px 5px;
    font-size: 6px;
    background: red;
    color: white;
    border-radius: 5px;
  }
</style>
