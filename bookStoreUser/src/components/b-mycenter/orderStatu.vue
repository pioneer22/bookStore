<template>
  <div>
    <div>
      <mt-header title="我的订单" :fixed="true" :style="getBack">
        <div @click="goBack('/myCenter')" slot="left">
          <mt-button icon="back">返回</mt-button>
        </div>
      </mt-header>
      <div style="height: 40px"></div>
    </div>

    <div>
      <van-tabs v-model="selected">
        <van-tab title="全部">
          <div class="outDiv" :style="setHeight">
            <div v-for="(item,index) in all" :key="index" class="orderDiv">
              <div v-if="item.orderInfo.orderStatus == 0" class="statu"><span>待付款</span></div>
              <!--<div v-if="item.orderInfo.orderStatus != 0"><span>卖家已发货</span></div>-->
              <div v-if="item.orderInfo.orderStatus == 1" class="statu"><span>待收货</span></div>
              <div v-if="item.orderInfo.orderStatus == 2" class="statu"><span>交易成功</span></div>
              <div v-if="item.orderInfo.orderStatus == 3" class="statu"><span>退换货</span></div>
              <div v-if="item.orderInfo.orderStatus == 4 " class="statu"><span>已评价</span></div>
              <div>
                <div v-for="(temp,index1) in item.item" :key="index1" class="itemDiv">
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
                <span>共{{item.item.length}}件商品</span>
                <span style="margin-left: 10px">合计：￥{{item.orderInfo.orderPrice}}</span>
              </div>
              <div class="backDiv">
                <span style="flex: 2" @click="showMenu(index)">更多</span>

                <div class="deleteOrder" :class="{ order: currentIndex === index  }"
                     @click="deleteOrder(item.orderInfo.orderId,0,index)">
                  <div class="dialogDiv">
                    删除订单
                  </div>
                </div>

                <div style="flex: 1" v-if="item.orderInfo.orderStatus > 0"
                     @click="goLogistics(item.orderInfo.orderStatus)">
                  <div class="buttonDiv">查看物流</div>
                </div>
                <div style="flex: 1" v-if="item.orderInfo.orderStatus == 0">
                  <div class="buttonDiv" @click="toPay1">付款</div>
                  <van-popup v-model="orderPay1" position="bottom">
                    <div class="popDiv" :style="setSize">
                      <div style="width: 90%;margin: auto;">
                        <div class="popTitle">
                          <img src="@/components/b-shopcar/images/exit.png" class="exitImg" @click="orderPay1 = false">
                          <span class="popDiv_span">确认付款</span>
                        </div>
                        <div class="popDiv_price">
                          ￥{{item.orderInfo.orderPrice}}
                        </div>
                        <div style="padding:0px 0px 10px 0px">
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
                          <van-button type="warning" @click="submitPay(item.orderInfo.orderId,index,0)">确认支付
                          </van-button>
                        </div>
                      </div>
                    </div>
                  </van-popup>
                </div>
                <div style="flex: 1" v-if="item.orderInfo.orderStatus == 1"
                     @click="surePayIt(item.orderInfo.orderId,index,0)">
                  <div class="buttonDiv">确认收货</div>
                </div>
                <!--<div style="flex: 1" v-if="item.orderInfo.orderStatus == 2">-->
                <!--<div class="buttonDiv">评价</div>-->
                <!--</div>-->
                <!--<div style="flex: 1" v-if="item.orderInfo.orderStatus == 3">-->
                <!--<div class="buttonDiv">查看进度</div>-->
                <!--</div>-->
                <div style="flex: 1" v-if="item.orderInfo.orderStatus == 4">
                  <div class="buttonDiv">已评价</div>
                </div>
              </div>
            </div>
          </div>
        </van-tab>

        <van-tab title="待付款">
          <div class="outDiv" :style="setHeight">
            <div v-for="(item,index) in Obligations" :key="index" class="orderDiv">
              <div class="statu"><span>待付款</span></div>
              <div>
                <div v-for="(temp,index1) in item.item" :key="index1" class="itemDiv">
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
                <span>共{{item.item.length}}件商品</span>
                <span style="margin-left: 10px">合计：￥{{item.orderInfo.orderPrice}}</span>
              </div>

              <div class="backDiv">
                <span style="flex: 3" @click="showMenu(index)">更多</span>
                <div class="deleteOrder" :class="{ order: currentIndex === index  }"
                     @click="deleteOrder(item.orderInfo.orderId,1,index)">
                  <div class="dialogDiv">
                    删除订单
                  </div>
                </div>
                <div style="flex: 2" @click="removeOrder(item.orderInfo.orderId,index)">
                  <div class="buttonDiv">取消订单</div>
                </div>
                <div style="flex: 2">
                  <div class="buttonDiv">朋友代付</div>
                </div>
                <div style="flex: 2">
                  <div class="buttonDiv" @click="toPay">付款</div>
                </div>
              </div>

              <van-popup v-model="orderPay" position="bottom">
                <div class="popDiv" :style="setSize">
                  <div style="width: 90%;margin: auto;">
                    <div class="popTitle">
                      <img src="@/components/b-shopcar/images/exit.png" class="exitImg" @click="orderPay = false">
                      <span class="popDiv_span">确认付款</span>
                    </div>
                    <div class="popDiv_price">
                      ￥{{item.orderInfo.orderPrice}}
                    </div>
                    <div style="padding:0px 0px 10px 0px">
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
                      <van-button type="warning" @click="submitPay(item.orderInfo.orderId,index,1)">确认支付</van-button>
                    </div>
                  </div>
                </div>
              </van-popup>
            </div>
          </div>
        </van-tab>

        <van-tab title="待收货">
          <div class="outDiv" :style="setHeight">
            <div v-for="(item,index) in receipt" :key="index" class="orderDiv">
              <div class="statu"><span>待收货</span></div>
              <div>
                <div v-for="(temp,index1) in item.item" :key="index1" class="itemDiv">
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
                <span>共{{item.item.length}}件商品</span>
                <span style="margin-left: 10px">合计：￥{{item.orderInfo.orderPrice}}</span>
              </div>

              <div class="backDiv">
                <span style="flex: 2" @click="showMenu(index)">更多</span>

                <div class="deleteOrder" :class="{ order: currentIndex === index  }"
                     @click="deleteOrder(item.orderInfo.orderId,2,index)">
                  <div class="dialogDiv">
                    删除订单
                  </div>
                </div>

                <div style="flex: 1" @click="goLogistics(item.orderInfo.orderStatus)">
                  <div class="buttonDiv">查看物流</div>
                </div>
                <div style="flex: 1" @click="surePayIt(item.orderInfo.orderId,index,1)">
                  <div class="buttonDiv">确认收货</div>
                </div>
              </div>
            </div>
          </div>
        </van-tab>

        <van-tab title="待评价">
          <div class="outDiv" :style="setHeight">
            <div v-for="(item,index) in evaluated" :key="index" class="orderDiv">
              <div class="statu"><span>待评价</span></div>
              <div>
                <div v-for="(temp,index1) in item.item" :key="index1" class="itemDiv">
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
                <span>共{{item.item.length}}件商品</span>
                <span style="margin-left: 10px">合计：￥{{item.orderInfo.orderPrice}}</span>
              </div>

              <div class="backDiv">
                <span style="flex: 2" @click="showMenu(index)">更多</span>

                <div class="deleteOrder" :class="{ order: currentIndex === index  }"
                     @click="deleteOrder(item.orderInfo.orderId,3,index)">
                  <div class="dialogDiv">
                    删除订单
                  </div>
                </div>

                <div style="flex: 1" @click="goLogistics(item.orderInfo.orderStatus)">
                  <div class="buttonDiv">查看物流</div>
                </div>
                <div style="flex: 1" @click="toComment(index)">
                  <div class="buttonDiv">评价</div>
                </div>
              </div>
            </div>
          </div>
        </van-tab>

        <van-tab title="退换货">
          <div class="outDiv" :style="setHeight">
            <div v-for="(item,index) in replace" :key="index" class="orderDiv">
              <div class="statu"><span>退换货</span></div>
              <div>
                <div v-for="(temp,index1) in item.item" :key="index1" class="itemDiv">
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
                <span>共{{item.item.length}}件商品</span>
                <span style="margin-left: 10px">合计：￥{{item.orderInfo.orderPrice}}</span>
              </div>

              <div class="backDiv">
                <span style="flex: 2" @click="showMenu(index)">更多</span>

                <div class="deleteOrder" :class="{ order: currentIndex === index  }"
                     @click="deleteOrder(item.orderInfo.orderId,4,index)">
                  <div class="dialogDiv">
                    删除订单
                  </div>
                </div>

                <div style="flex: 1" @click="goLogistics(item.orderInfo.orderStatus)">
                  <div class="buttonDiv">查看物流</div>
                </div>
                <!--<div style="flex: 1">-->
                <!--<div class="buttonDiv">查看进程</div>-->
                <!--</div>-->
              </div>
            </div>
          </div>
        </van-tab>
      </van-tabs>
    </div>
  </div>
</template>

<script>
  import {getOrder, deleteOneOrder, surePay, setOrderStatu1, cancelOrder} from "../../api";
  import {Toast} from 'vant'
  import {MessageBox} from 'mint-ui'

  export default {
    data() {
      return {
        selected: 0,
        all: [],//所有订单
        Obligations: [],//待付款
        receipt: [],//待收货
        evaluated: [],//待评价
        replace: [],//退换货
        setHeight: {
          "min-height": '',
        },
        currentIndex: -1,//判断点击哪个更多弹出的删除
        orderPay: false,//弹出支付框
        orderPay1: false,//弹出支付框
        showKeyboard: true,
        value: '',
        setSize: {
          height: ''
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

    methods: {
      goBack(path) {
        this.$router.push({
          path: path
        })
      },

      _getHeight() {
        let height = window.innerHeight || document.body.client.clientHeight || document.documentElement.clientHeight;
        this.setHeight["min-height"] = height - 84 + 'px';
      },

      async getOrderMess() {
        let userId = this.$store.state.user.userId;
        let result = await getOrder(userId);
        if (result.statu == 0) {
          this.all = result.data.all;
          this.Obligations = result.data.Obligations;
          this.receipt = result.data.receipt;
          this.evaluated = result.data.evaluated;
          this.replace = result.data.replace;
          console.log(result.data)
        } else
          console.log("获取订单失败！")
      },

      //判断点击了哪个订单的更多
      showMenu(index) {
        if (this.currentIndex < 0)
          this.currentIndex = index;
        else
          this.currentIndex = -1;
      },
      //删除订单,which判断哪个数组,index,获取需要删除的内容
      async deleteOrder(orderId, which, index) {
        let result = await deleteOneOrder(orderId);
        if (result == 1) {
          Toast.success('删除成功');
          switch (which) {
            case 0:
              this.all.splice(index, 1);
              break;
            case 1:
              this.Obligations.splice(index, 1);
              break;
            case 2:
              this.receipt.splice(index, 1);
              break;
            case 3:
              this.evaluated.splice(index, 1);
              break;
            case 4:
              this.replace.splice(index, 1);
              break;
          }
          this.currentIndex = -1
          this.getOrderMess()
        }
      },

      //传一个订单状态,判断物流到哪个步骤,虚拟的
      goLogistics(orderStatus) {
        this.$router.push({
          path: '/shopcar/logistics',
          query: {
            orderStatus: orderStatus
          }
        })
      },

      //确认收货,statu判断是全部订单的还是待收货的
      async surePayIt(orderId, index, statu) {
        MessageBox.confirm('确认收货？', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          center: true,
          roundButton: true
        }).then(async () => {
          let result = await surePay(orderId);
          if (result == 1) {
            Toast.success("收货成功");
            if (statu == 1) {
              this.evaluated.push(this.receipt[index]);
              this.receipt.splice(index, 1);
            } else {
              this.getOrderMess();
            }
          }
          else
            Toast.fail("收货失败")
        }).catch(() => {
        })
      },

      _getSize() {
        let height = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;
        this.setSize.height = Math.round(height * 0.8) % 2 == 0 ? Math.round(height * 0.8) + 'px' : Math.round(height * 0.8) - 1 + 'px';
      },

      onInput(key) {
        this.value = (this.value + key).slice(0, 6);
      },
      onDelete() {
        this.value = this.value.slice(0, this.value.length - 1);
      },

      //确认支付,如果是全部订单里实现付款，则重新调用
      async submitPay(orderId, index, statu) {
        //假设密码123456
        if (this.value == '123456') {
          this.orderPay = false;
          //修改订单状态为待收货
          let userId = this.$store.state.user.userId;
          let result = await setOrderStatu1(userId, orderId);
          if (result == 1) {
            Toast.success('支付成功');
            if (statu == 1) {
              this.receipt.push(this.Obligations[index]);
              this.Obligations.splice(index, 1);
            } else {
              this.getOrderMess();
            }
          }
        } else
          Toast.fail('密码错误')
      },

      //点击付款,弹出支付框
      toPay() {
        this.orderPay = true;
        this.showKeyboard = true;
        this.value = '';
      },

      toPay1() {
        this.orderPay1 = true;
        this.showKeyboard = true;
        this.value = '';
      },

      //取消订单
      async removeOrder(orderId, index) {
        let result = await cancelOrder(orderId);
        if (result == 1) {
          Toast.success("取消成功")
          this.Obligations.splice(index, 1)
        }
        else
          Toast.fail("取消失败")
      },

      //跳转评价页面
      toComment(index) {
        this.$store.dispatch('commentBook', this.evaluated[index]);
        this.$router.push({
          path: '/shopcar/comment'
        })
      }
    },

    created() {
      this.getOrderMess();
      this.selected = this.$store.state.selected;
      this._getHeight();
      this._getSize();
    }
  }
</script>

<style scoped>
  .statu {
    padding: 10px 15px;
    text-align: right;
    font-size: 12px;
    color: #E6A23C;
  }

  .outDiv {
    background: #E2E1E1;
    position: relative;
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

  .deleteOrder {
    position: absolute;
    left: 60px;
    display: none;
  }

  .backDiv {
    display: flex;
    flex-direction: row;
    padding: 10px;
    font-size: 14px;
    align-items: center;
    color: #717171;
  }

  .buttonDiv {
    padding: 5px;
    border: 0.5px solid gray;
    height: 20px;
    border-radius: 16px;
    width: 80%;
    text-align: center;
  }

  .dialogDiv {
    position: relative;
    background-color: #292929;
    width: 60px;
    height: 20px;
    line-height: 20px;
    font-size: 14px;
    border-radius: 10px;
    padding: 5px;

    color: white;
    text-align: center;
    font-family: sans-serif;
  }

  /*通过伪元素设置三角*/
  .dialogDiv:after {
    content: '';
    position: absolute;
    width: 0;
    height: 0;
    border: 5px solid;
    border-right-color: #292929;

    /*top: 100%;*/
    /*left: 50%;*/

    top: 50%;
    right: 100%;
    /*margin-left: -15px;*/
    margin-top: -5px;
  }

  .order {
    display: block;
  }

  .popDiv {
    width: 100%;
    padding: 10px 2px;
    border-bottom: 0.5px solid #666666;
    background: #E1E0E0;
  }

  .popDiv_span {
    display: inline-block;
    height: 28px;
    font-size: 16px;
    line-height: 28px;
  }

  .popTitle {
    text-align: center;
    position: relative;
  }

  .exitImg {
    width: 28px;
    position: absolute;
    left: 0;
  }

  .popDiv_price {
    padding: 15px 0px;
    font-size: 30px;
    text-align: center;
    width: 100%;
  }
</style>
<style>
  .van-tabs__wrap {
    top: 40px;
    left: 0;
    right: 0;
    z-index: 99;
    overflow: hidden;
    position: fixed;
  }
</style>
