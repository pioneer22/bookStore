<template>
  <div>
    <div>
      <mt-header title="我的优惠卷" :fixed="true" :style="getBack">
        <div @click="goBack('/myCenter')" slot="left">
          <mt-button icon="back">返回</mt-button>
        </div>
      </mt-header>
      <div style="height: 40px"></div>
    </div>

    <van-tabs>
      <van-tab :title="used[index]+'('+category[index].length +')'" v-for="(itemTemp,index) in category" :key="index">
        <div v-for="(item,index1) in itemTemp" class="outDiv" :class="{'noused':index>0}" @click="goUse(index1)" :key="index1">
          <div class="outDiv_div1">
            <img :src="item.couponImg"/>
          </div>
          <div class="outDiv_div2">
            <span class="outDiv_div2_span1">全网图书通用券</span>
            <span class="outDiv_div2_span2">
              <span class="couponMoney">{{item.couponMoney}}</span>
                   &nbsp满{{item.couponUse}}可用
            </span>
            <span class="dateTime">{{item.couponTime | formatTime('YYYY-MM-DD')}}-{{(item.couponVaild) | formatTime('YYYY-MM-DD')}}</span>
          </div>
          <div class="outDiv_div3">
            <img src="./images/noused.png" v-show="index == 0"/>
            <img src="./images/used.png" v-show="index == 1"/>
            <img src="./images/invalid.png" v-show="index == 2"/>
          </div>
        </div>
      </van-tab>
    </van-tabs>

    <div class="getCoupon" @click="goBack('/firstPage/volume')">
      领更多好卷
    </div>
  </div>
</template>

<script>
  import {myCoupon} from "../../api";

  export default {
    data() {
      return {
        category: [],
        used: [
          '未使用',
          '已使用',
          '已失效'
        ]
      }
    },

    methods: {
      goBack(path) {
        this.$router.push({
          path: path
        })
      },

      async getMyCoupon() {
        let userId = this.$store.state.user.userId;
        let result = await myCoupon(userId);
        if (result.statu == 0) {
          this.category = result.data;
          console.log(result.data)
        }
      },

      goUse(index) {
        if (index == 0) {
          this.$store.dispatch('changeActive', 0)
          this.$router.push({
            path: '/firstPage'
          })
        }
      }
    },

    created() {
      this.getMyCoupon();
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
  .outDiv {
    background: url("../b-firstpage/images/background.jpg");
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
    flex: 2;
    align-items: flex-end;
    color: red;
  }

  .couponMoney {
    font-weight: bold;
    font-size: 25px;
  }

  .dateTime {
    font-size: 6px;
    color: gray;
    display: flex;
    align-items: flex-end;
    flex: 1;
  }

  .outDiv_div3 {
    display: flex;
    justify-content: center;
    flex-direction: column;
    align-items: center;
    flex: 1;
  }

  .outDiv_div3 img {
    width: 60px;
    height: 50px;
  }

  .noused {
    background: #E4E3E3;
  }

  .getCoupon {
    text-align: center;
    font-size: 10px;
    color: gray;
    padding: 10px 0px;
    border-top: 3px solid #D2D1D1;
    position: fixed;
    left: 0;
    bottom: 0;
    width: 100%;
  }
</style>
