<template>
  <div>
    <mt-header :title="title" :fixed="true" :style="getBack">
      <router-link to="/shopcar" slot="left">
        <mt-button icon="back">返回</mt-button>
      </router-link>
    </mt-header>
    <div style="height: 40px"></div>

    <div class="top_out_div">
      <div class="top_inner_div1">
        <img src="@/components/b-mycenter/images/address.png" style="width: 32px;height: 32px;"/>
      </div>

      <div class="top_inner_div2" @click="toChooseReceive">
          <span class="top_inner_div2_span1">
            <span>收货人:{{address.contactName}}</span><span>{{address.contactPhone}}</span>
          </span>
        <span class="top_inner_div2_span2">收货地址：{{address.contactAddress}}</span>
        <span class="top_inner_div2_span3">(收货不便时，可选择免费待收货服务)</span>
      </div>

      <div class="top_inner_div3">
        <span><i class="el-icon-arrow-right"></i></span>
      </div>
    </div>

    <div style="height: 3px;background: #00bac7"></div>

    <div style="background: #e9e9e9" v-for="(item,index) in payorder">
      <div class="top_out_div book_out_div">
        <div class="book_inner_div1">
          <img :src="item.bookImg" style="width: 80px;height: 80px;"/>
        </div>
        <div class="book_inner_div2">
          <span class="book_inner_div2_span1">{{item.bookName}}:{{item.bookIntro}}</span>
          <span class="book_inner_div2_span2">
          <span style="color: red">￥{{item.bookPrice}}</span>
          <span>X{{item.count}}</span>
        </span>
        </div>
      </div>
      <div class="book_inner_div3">
        <span>共{{item.count}}本图书</span>
        <span style="margin-left: 15px;margin-right: 15px">
          小计：<span style="color: red">￥{{item.count*item.bookPrice}}</span>
        </span>
      </div>
    </div>

    <div>
      <div class="freight_div">
        <span style="margin-left: 15px;color:#323233;">配送方式</span>
        <span v-if="isFree" class="span_right">快递 免邮<i class="el-icon-arrow-right"></i></span>
        <span v-else class="span_right">快递 10元<i class="el-icon-arrow-right"></i></span>
      </div>


      <div>
        <div class="freight_div" @click="show = true">
          <span style="margin-left: 15px;color:#323233;">优惠卷</span>
          <span class="span_right" v-if="Object.keys(coupon).length == 0">可用优惠({{getNum}})<i
            class="el-icon-arrow-right"></i></span>
          <span class="span_right" v-else>- ￥{{this.coupon.couponMoney}}<i class="el-icon-arrow-right"></i></span>

        </div>
      </div>

      <van-popup v-model="show" position="left">
        <div :style="setSize">
          <mt-navbar v-model="selected">
            <mt-tab-item id="1">可用优惠卷({{this.canUse.length}})</mt-tab-item>
            <mt-tab-item id="2">不可用优惠卷({{this.noUse.length}})</mt-tab-item>
          </mt-navbar>

          <mt-tab-container v-model="selected">
            <mt-tab-container-item id="1">
              <div style="width: 100%;">
                <div v-for="(item,index) in canUse" class="useDiv" @click="useCoupon(index)">
                  <div class="use_left">
                    <span class="left_span">{{item.couponMoney}}</span><span style="color: #f44">元</span><br>
                    <span>满{{item.couponUse}}元可用</span>
                  </div>
                  <div style="width: 70%;">
                    <span class="right_span">满{{item.couponMoney}}减{{item.couponUse}}</span><br>
                    <span>{{item.couponTime | formatTime('YYYY-MM-DD')}} - {{item.couponVaild | formatTime('YYYY-MM-DD')}}</span>
                  </div>
                </div>
                <div class="noUse" @click="noUseCoupon">
                  不使用优惠
                </div>
              </div>
            </mt-tab-container-item>

            <mt-tab-container-item id="2">
              <div style="width: 100%;">
                <div v-for="(item,index) in noUse" class="useDiv">
                  <div class="use_left">
                    <span class="left_span">{{item.couponMoney}}</span><span style="color: #f44">元</span><br>
                    <span>未达满减条件</span>
                  </div>
                  <div style="width: 70%;">
                    <span class="right_span">满{{item.couponMoney}}减{{item.couponUse}}</span><br>
                    <span>{{item.couponTime | formatTime('YYYY-MM-DD')}} - {{item.couponVaild | formatTime('YYYY-MM-DD')}}</span>
                  </div>
                </div>
              </div>
              <div class="noUse" @click="noUseCoupon">
                不使用优惠
              </div>
            </mt-tab-container-item>

          </mt-tab-container>
        </div>
      </van-popup>

      <div class="leave_mess_div">
        <van-cell-group>
          <van-field
            v-model="message"
            label="留言"
            type="text"
            placeholder="请输入留言"
            rows="1"
            style="height: 40px"
          />
        </van-cell-group>
      </div>
    </div>

    <div class="bottomDiv">
      <div class="allPrice">
        <span style="margin-right: 10px">共{{getBookNum}}本</span>
        <span>合计：</span>
        <span class="priceSpan">￥{{getAllPrice}}</span>
      </div>
      <div class="submitDiv" @click="onSubmit">
        提交订单
      </div>
    </div>

    <van-popup v-model="orderPay" position="bottom">
      <div class="popDiv" :style="setHeight">
        <div style="width: 90%;margin: auto;">
          <div class="popTitle">
            <img src="./images/exit.png" class="exitImg" @click="orderPay = false">
            <span class="popDiv_span">确认付款</span>
          </div>

          <div class="popDiv_price">
            ￥{{getAllPrice}}
          </div>

          <div style="padding:0px 0px 10px 0px">
            <!--<div style="padding: 2px 0px;text-align: center">-->
              <!--<span>账号：</span>-->
              <!--<span >{{this.$store.state.user.userId}}</span>-->
            <!--</div>-->
            <van-password-input
              :value="value"
              @focus="showKeyboard = true"
            />

            <van-number-keyboard
              :show="showKeyboard"
              @input="onInput"
              @delete="onDelete"
              @blur="showKeyboard = false"
            />
          </div>

          <div style="width: 100%;text-align: center">
            <van-button type="warning" @click="surePay">确认支付</van-button>
          </div>
        </div>
      </div>
    </van-popup>

    <div style="height: 50px"></div>
  </div>
</template>

<script>

  import {reqContacts, personCoupon, generateOrder,setOrderStatu} from "../../api";
  import {Toast} from 'vant';
  export default {
    data() {
      return {
        orderId:'',
        title: '确认订单',
        userId: '',
        address: {},//存收货地址
        payorder: [],//存购物车传过来的信息
        message: '',//留言
        show: false,
        setSize: {
          height: '',
          width: ''
        },
        selected: 1,
        canUse: [],
        noUse: [],
        coupon: {},//接收使用优惠卷
        orderPay: false,
        value:'',
        showKeyboard: true,
        setHeight:{
          height: '',
        }
      }
    },

    created() {
      if (Object.keys(this.$store.state.contact).length > 0)
        this.address = this.$store.state.contact;
      else
        this.getContact();
      this.userId = this.$store.state.user.userId;
      this.payorder = this.$store.state.payorder;

      this._getSize();
      this.getCoupon();
      this._getHeight();
    },

    computed: {
      //计算总价看是否包邮
      isFree() {
        let price = 0;
        this.payorder.forEach((item) => {
          price += item.count * item.bookPrice;
        })
        return price > 69 ? true : false;
      },

      getBookNum() {
        let num = 0;
        this.payorder.forEach((item) => {
          num += item.count;
        });
        return num;
      },

      getBack() {
        return {
          'background-color': this.$store.state.themeColor
        }
      },

      //可用优惠卷个数
      getNum() {
        return this.canUse.length > 0 ? this.canUse.length : 0;
      },

      //计算总价
      getAllPrice() {
        let price = 0;
        this.payorder.forEach((item) => {
          price += item.count * item.bookPrice;
        });

        //没有达到包邮条件
        if (price < 69)
          price += 10;
        if (Object.keys(this.coupon).length > 0)
          price -= this.coupon.couponMoney;
        return typeof (price) === "number" ? price.toFixed(1) : price
      }
    },

    methods: {
      async getContact() {
        //请求联系人信息
        this.userId = this.$store.state.user.userId;
        const {userId} = this;
        let result = await reqContacts({userId});
        if (result.statu == 0) {//请求联系人成功
          this.address = result.data[0];
          this.$store.dispatch('saveContact', result.data);
          this.$store.dispatch('saveReceive', result.data[0]);
        } else {
          // 请求联系人失败
          console.log(result.error);
        }
      },

      //拿回未使用优惠卷,分类
      async getCoupon() {
        let userId = this.$store.state.user.userId;
        let result = await personCoupon(userId);
        if (result.statu == 0) {
          let price = 0;
          this.payorder.forEach((item) => {
            price += item.count * item.bookPrice;
          });
          result.data.forEach((item) => {
            if (item.couponUse < price)
              this.canUse.push(item);
            else
              this.noUse.push(item);
          })
        }

      },

      //选择联系人
      toChooseReceive() {
        this.$router.push({
          path: '/shopcar/chooseReceive'
        })
      },

      //设置优惠卷页面宽高，防止出现字体模糊
      _getSize() {
        let height = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;
        let width = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
        this.setSize.height = Math.round(height) % 2 == 0 ? Math.round(height) + 'px' : Math.round(height) - 1 + 'px';
        this.setSize.width = Math.round(width * 0.8) % 2 == 0 ? Math.round(width * 0.8) + 'px' : Math.round(width * 0.8) + 1 + 'px';
      },

      _getHeight(){
        let height = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;
        this.setHeight.height = Math.round(height * 0.8) % 2 == 0 ? Math.round(height * 0.8) + 'px' : Math.round(height * 0.8) - 1 + 'px';
      },

      //选择优惠卷
      useCoupon(index) {
        this.coupon = this.canUse[index];
        this.show = false;
      },

      //不使用优惠
      noUseCoupon() {
        this.coupon = {};
        this.show = false;
      },

      //提交订单,生成订单，判断是否是购物车过来的，是的话把购物车中选中的图书删除
      //同时判断是否使用了优惠卷，若使用了优惠卷，标记优惠卷的状态为已使用
      async onSubmit() {
        //生成订单的信息
        let userId = this.$store.state.user.userId;
        let contactId = this.address.contactId;
        let allPrice = this.getAllPrice;
        let message = this.message.length > 0 ? this.message : '';

        //获取选中购物车图书Id
        let carBookId = [];
        this.payorder.forEach((item) => {
          let temp = {
            bookId: item.bookId,
            count: item.count,
          }
          carBookId.push(temp);
        });

        //使用优惠卷
        let couponId = 0;
        if (Object.keys(this.coupon).length > 0)
          couponId = this.coupon.couponId;

        let orderMess = [
          userId, contactId, allPrice, message, couponId
        ]
        let result = await generateOrder(orderMess, carBookId);
        if (result != '0') {
          //弹出支付界面
          this.orderPay = true;
          this.orderId = result;
        }
      },

      onInput(key) {
        this.value = (this.value + key).slice(0, 6);
      },
      onDelete() {
        this.value = this.value.slice(0, this.value.length - 1);
      },

     async surePay(){
        //假设密码123456
        if(this.value == '123456')
        {
          this.orderPay = false;
          Toast.success('支付成功');
          //修改订单状态为待收货
          const {userId,orderId} = this;
          let result = await setOrderStatu({userId,orderId});
          if(result == 1) {
            console.log('修改成功');
            this.$router.push({
              path: '/myCenter/orderStatu'
            })
          }
        }else
          Toast.fail('密码错误')
      },
    }
  }
</script>

<style scoped>
  .top_out_div {
    width: 100%;
    height: 110px;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .top_inner_div1 {
    flex: 2;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .top_inner_div2 {
    display: flex;
    flex-direction: column;
    flex: 16;
    height: 100%
  }

  .top_inner_div2_span1 {
    height: 36%;
    display: flex;
    align-items: flex-end;
    font-size: 18px;
    color: #333333;
    justify-content: space-between;
  }

  .top_inner_div2_span2 {
    height: 32%;
    font-size: 14px;
    color: gray;
    display: flex;
    align-items: center;
  }

  .top_inner_div2_span3 {
    height: 32%;
    font-size: 14px;
    color: #E6A23C;
  }

  .top_inner_div3 {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .book_out_div {
    width: 95%;
    margin: 5px auto;
  }

  .book_inner_div1 {
    display: flex;
    flex: 2;
    justify-content: center;
  }

  .book_inner_div2 {
    display: flex;
    flex: 7;
    flex-direction: column;
    justify-content: center;
    height: 100%;
    margin-left: 5px;
  }

  .book_inner_div2_span1 {
    display: flex;
    height: 53%;
    font-size: 15px;
  }

  .book_inner_div2_span2 {
    display: flex;
    height: 20%;
    align-items: flex-end;
    justify-content: space-between;
  }

  .book_inner_div3 {
    background: white;
    height: 44px;
    display: flex;
    justify-content: flex-end;
    align-items: center;
    border-bottom: 0.5px solid #e9e9e9;
    font-size: 14px;
  }

  .freight_div {
    height: 40px;
    display: flex;
    font-size: 14px;
    align-items: center;
    justify-content: space-between;
    border-bottom: 0.5px solid #e9e9e9;
  }

  .span_right {
    margin-right: 15px;
    color: #969799;
  }

  .leave_mess_div {
    height: 40px;
    border-bottom: 1px solid #e9e9e9;
  }

  .bottomDiv {
    display: flex;
    flex-direction: row;
    width: 100%;
    height: 50px;
    line-height: 50px;

    position: fixed;
    left: 0;
    bottom: 0;
  }

  .allPrice {
    width: 70%;
    font-size: 14px;
    font-weight: 500;
    color: #323233;
    text-align: right;
    background: white;
    padding-right: 10px;
  }

  .priceSpan {
    color: #f44;
    font-size: 18px;
  }

  .submitDiv {
    width: 30%;
    background: #f44;
    color: white;
    text-align: center;
    /*margin-left: 10px;*/
  }

  .useDiv {
    width: 86%;
    margin: auto;
    display: flex;
    flex-direction: row;
    font-size: 8px;
    color: #666666;
    border-radius: 5px;
    background: #69B2B4;
    padding: 10px;
    margin-top: 10px;
  }

  .use_left {
    width: 30%;
    margin-right: 15px;
  }

  .left_span {
    color: #f44;
    font-size: 18px;
  }

  .right_span {
    color: black;
    font-size: 14px;
    display: inline-block;
    padding: 2px;
  }

  .noUse {
    border-top: 0.5px solid #E6E2E4;
    position: fixed;
    left: 0;
    bottom: 0;
    width: 100%;
    height: 40px;
    line-height: 40px;
    text-align: center;
    font-size: 14px;
    color: gray;
  }

  .popDiv{
    width: 100%;
    padding: 10px 2px;
    border-bottom: 0.5px solid #666666;
    background: #E1E0E0;
  }

  .popDiv_span{
    display: inline-block;
    height: 28px;
    font-size: 16px;
    line-height: 28px;
  }

  .popTitle{
    text-align: center;
    position: relative;
  }

  .exitImg{
    width: 28px;
    position: absolute;
    left: 0;
  }

  .popDiv_price{
    padding: 15px 0px;
    font-size: 30px;
    text-align: center;
    width: 100%;
  }
</style>
