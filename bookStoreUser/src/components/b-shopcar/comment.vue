<template>
  <div>
    <div>
      <mt-header title="评论" :fixed="true" :style="getBack">
        <div @click="goBack" slot="left">
          <mt-button icon="back">返回</mt-button>
        </div>
      </mt-header>
      <div style="height: 40px"></div>
    </div>

    <div class="outDiv" :style="setHeight">
      <div class="orderDiv">
        <div class="statu"><span>待评价</span></div>
        <div>
          <div v-for="(temp,index) in orderMess.item" :key="index" class="itemDiv">
            <div class="imgDiv">
              <img :src="temp.book.bookImg" style="max-width: 100%"/>
            </div>
            <div class="messDiv">
              <span style="flex: 3">{{temp.book.bookName}}:{{temp.book.bookIntro}}</span>
              <span class="messDiv_span">
                      <span>￥{{temp.book.bookPrice}}</span><br>
                      <span>X{{temp.item.shopNum}}</span>
                    </span>
            </div>
          </div>
        </div>

        <div class="countDiv">
          <span>共{{orderMess.item.length}}件商品</span>
          <span style="margin-left: 10px">合计：￥{{orderMess.orderInfo.orderPrice}}</span>
        </div>
      </div>

      <div style="width: 94%;margin: auto">
        <div v-for="(n,index) in orderMess.item.length" :key="index">
          <el-input
            type="textarea"
            :autosize="{ minRows: 2, maxRows: 4}"
            placeholder="请输入评论内容"
            style="margin-bottom: 5px"
            v-model="textarea[index]">
          </el-input>
        </div>
        <mt-button type="primary" class="submit" @click="onSubmit">提交评论</mt-button>
      </div>
    </div>

  </div>
</template>

<script>
  import {Toast} from 'vant'
  import {submitComment} from "../../api";

  export default {
    data() {
      return {
        orderMess: {},//保存要评论的图书订单信息
        setHeight: {
          "min-height": '',
        },
        textarea:[],
      }
    },

    created() {
      this.orderMess = this.$store.state.commentBook;//获取要评论的图书订单信息
      this._getHeight();

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

      _getHeight() {
        let height = window.innerHeight || document.body.client.clientHeight || document.documentElement.clientHeight;
        this.setHeight["min-height"] = height - 40 + 'px';
      },

      async onSubmit(){
          let orderId = this.orderMess.orderInfo.orderId;
          let userId = this.$store.state.user.userId;
          let comment = this.textarea + "";
          let result = await submitComment(orderId,comment,userId);
          if(result == 1){
            Toast.success("评论成功");
            //跳转订单页面
            this.$router.push({
              path:'/myCenter/orderStatu'
            })
          }
          else
            Toast.fail("评论失败");
      }
    },
  }
</script>

<style scoped>
  .outDiv {
    background: #E2E1E1;
    position: relative;
    padding-top: 5px;
  }

  .orderDiv {
    background: white;
    width: 94%;
    margin: auto;
    margin-bottom: 8px;
    border-radius: 10px;
  }

  .itemDiv {
    display: flex;
    flex-direction: row;
    border-top: 0.2px solid #E5E4E5;
    padding: 5px 0px;
  }

  .imgDiv {
    width: 25%;
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 5px;
  }

  .messDiv {
    width: 75%;
    display: flex;
    flex-direction: row;
    font-size: 14px;
    margin-right: 5px;
  }

  .messDiv_span {
    flex: 1;
    margin-left: 5px;
    text-align: right;
  }

  .countDiv {
    padding: 5px;
    text-align: right;
    font-size: 14px;
  }

  .statu {
    padding: 10px 15px;
    text-align: right;
    font-size: 12px;
    color: #E6A23C;
  }

  .submit{
    width: 100%;
    margin-top: 5px;
  }
</style>
