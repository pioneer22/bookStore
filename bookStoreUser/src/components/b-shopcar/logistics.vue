<template>
    <div>
      <div>
        <mt-header title="物流详情" :fixed="true" :style="getBack">
          <div @click="goBack" slot="left">
            <mt-button icon="back">返回</mt-button>
          </div>
        </mt-header>
        <div style="height: 40px"></div>
      </div>

      <div>
        <van-steps :active="active">
          <van-step>买家下单</van-step>
          <van-step>等待收货</van-step>
          <van-step>买家提货</van-step>
          <van-step>交易完成</van-step>
        </van-steps>
      </div>

      <div>
        <van-steps direction="vertical" :active="active1">
          <van-step v-for="(item,index) in mockLogi" v-if="index<=active1" :key="index">
            <h3>{{item.message}}</h3>
            <p>{{item.time}}</p>
          </van-step>
        </van-steps>
      </div>
    </div>
</template>

<script>
    export default {
      data(){
        return {
          active:0,
          active1:1,

          mockLogi:[
            {
              message:"【广州】快件已发出",
              time:"2019-4-11 12:40"
            },
            {
              message:"【广州】快件到达转运中心",
              time:"2019-4-13 15：20"
            },
            {
              message:"【广州】快件员正在派送",
              time:"2019-4-14 17：20"
            },
            {
              message:"【广州】已签收",
              time:"2019-4-15 15：20"
            }
          ]
        }
      },
      computed: {
        getBack() {
          return {
            'background-color': this.$store.state.themeColor
          }
        }
      },
      methods: {
        goBack() {
          this.$router.go('-1');
        },
      },

      created(){
        this.active = this.$route.query.orderStatus;
        if(this.$route.query.orderStatus == 2)
          this.active = 3
        if(this.$route.query.orderStatus == 3)
          this.active = 2
        this.active1 = this.active;
      }
    }
</script>

<style scoped>

</style>
