<template>
  <div>
    <div>
      <mt-header :title="title" :fixed="true" :style="getBack">
        <mt-button slot="right" @click="manage">管理</mt-button>
      </mt-header>
    </div>
    <div style="height: 40px"></div>

    <div class="haveBook" v-if="getBook">
      <div v-for="(item,index) in cardList" :key="index" class="allshop">
        <div class="check">
          <van-checkbox v-model="checked[index]" @change="computedPrice"></van-checkbox>
        </div>
        <img :src="item.bookImg" class="img" @click="goShop(item.bookId)"/>
        <div class="shop_intro">
          <span class="car_intro">{{item.bookName}}</span>
          <div class="car_bottom">
            <span>￥{{item.bookPrice}}</span>
            <van-stepper
              v-model="item.count"
              integer
              :min="1"
              :max="20"
              :step="1"
              @change="handleChange(index)"
            />
          </div>
        </div>
      </div>
      <div class="Settlement">
        <div class="bottomset">
          <div class="bottom_div">
            <label style="display: flex;flex-direction:row;align-items: center;">
              <input type="checkbox" v-model="choose" @change="computedPrice" @click="isChooseAll"
                     class="checkBox_span"/>
              <span>全选</span>
            </label>
          </div>
          <div class="bottom_div">
            <span>合计:</span>
            <span class="price">￥{{allprice|numfilter}}</span>
          </div>
          <div>
            <van-button type="warning" @click="tocomputed" size="small" v-if="on">结算{{this.paynum}}</van-button>
            <van-button type="warning" @click="todelete" size="small" v-else>删除</van-button>
          </div>
        </div>
      </div>
    </div>

    <div v-if="!getBook">
      <div class="noBook">
        <img src="./images/shopcar.png" alt="" style="width: 22%">
        <span style="color: gray">购物车没有商品喔</span>
      </div>
      <div class="toOtherWay">
        <span class="toCollect" @click="toOther('/myCenter/collect')">去看收藏</span>
        <span class="toAny" @click="toOther('/firstPage')">随便逛逛</span>
      </div>
    </div>
  </div>
</template>

<script>
  import {Dialog, Toast} from 'vant';
  import {reqShopCar, delShopCar, getCarNum} from "../../api";

  export default {
    data() {
      return {
        allprice: 0,

        choose: false,//全选
        cardDataList: [],//接收购物车信息
        checked: [],//判断哪些书被选中
        on: true,
        getBook: true,
      }
    },

    computed: {
      //处理数据
      cardList() {
        return this.cardDataList.map(e => {
          let [count, [book]] = e
          return Object.assign({}, book, {
            count
          })
        })
      },

      //计算标题图书数量
      number() {
        let booknum = 0;
        this.cardList.forEach((item) => {
          booknum += item.count
        });
        return booknum;
      },

      title() {
        return '购物车(' + this.number + ')';
      },

      //计算结算图书数量
      paynum() {
        let num = 0;
        this.checked.forEach((item, index) => {
          if (item) {
            num += this.cardList[index].count;
          }
        });
        return num > 0 ? '(' + num + ')' : '';
      },

      getBack() {
        return {
          'background-color': this.$store.state.themeColor
        }
      }
    },

    created() {
      this.getShopcar();
    },

    watch: {
      cardDataList() {
        this.getBook = this.cardDataList.length > 0 ? true : false;
      }
    },

    methods: {

      //计算结算金额
      handleChange(index) {
        this.allprice = 0;
        this.checked.forEach((item, index) => {
          if (item) {
            this.allprice += this.cardList[index].count * this.cardList[index].bookPrice;
          }
        })
      },

      //全选
      isChooseAll() {
        this.choose = !this.choose;
        this.checked.forEach((item, index, array) => {
          array[index] = this.choose;
        });
      },

      //结算，跳转生成订单页面
      tocomputed() {
        if (this.allprice == 0) {
          Dialog.alert({
            message: '未勾选图书!'
          }).then(() => {
            // on close
          });
        } else {
          let payorder = [];
          //判断哪些被选中
          this.checked.forEach((item, index) => {
            if (item) {
              var list = {
                bookId: this.cardList[index].bookId,
                bookImg: this.cardList[index].bookImg,
                bookName: this.cardList[index].bookName,
                bookPrice: this.cardList[index].bookPrice,
                bookIntro: this.cardList[index].bookIntro,
                count: this.cardList[index].count
              }
              payorder.push(list);
            }
          });
          //把要买的东西存到Vuex
          this.$store.dispatch("saveWantBuy", payorder);

          this.$router.push({
            path: '/shopcar/payOrder'
          })
        }
      },

      //管理按钮
      manage() {
        this.on = !this.on;
      },

      //计算总价
      computedPrice() {
        this.allprice = 0;
        let isAll = 0;
        this.checked.forEach((item, index) => {
          if (item) {
            this.allprice += this.cardList[index].count * this.cardList[index].bookPrice;
            isAll += 1;
          }
        })

        //判断是否全选
        if (isAll == this.checked.length)
          this.choose = true;
        else
          this.choose = false;
      },

      //获取购物车信息
      async getShopcar() {
        let userId = this.$store.state.user.userId;
        let result = await reqShopCar(userId);
        if (result.statu === 0) {
          this.cardDataList = result.data
          this.$store.dispatch('saveShopCar', this.cardList);//将信息处理后存入Vuex
          this.checked = [];
          //默认全为未选中
          for (var i = 0; i < result.data.length; i++)
            this.checked.push(false);
          console.log(result.msg);
        } else {
          this.$store.dispatch('saveShopCarNum', '');
          this.getBook = false
        }
      },

      //删除购物车选中的书
      async todelete() {
        let delBookId = [];
        this.checked.forEach((item, index) => {
          if (item)
            delBookId.push(this.cardList[index].bookId);
        })
        if (delBookId.length > 0) {
          const userId = this.$store.state.user.userId
          let result = await delShopCar(userId,delBookId);
          if (result == 1) {
            Toast.success('删除成功');
            this.getBookNum()
            this.getShopcar();
          }
          else
            Toast.fail('删除失败');
          //全选则清空
        } else {
          Dialog.alert({
            message: '未选中图书'
          }).then(() => {
            // on close
          });
        }
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

      //获取购物车数量
      async getBookNum() {
        let userId = this.$store.state.user.userId;
        let result = await getCarNum(userId);
        if (result > 0)
          this.$store.dispatch('saveShopCarNum', result);
        else {
          this.$store.dispatch('saveShopCarNum', '');
          this.cardDataList = [];
          console.log('获取购物车数量失败！');
        }
      },

      //随便逛逛，去首页,//去收藏夹
      toOther(path) {
        this.$router.push({
          path:path
        })
      }

    },

    filters: {
      numfilter: function (data) {
        if (data == 0)
          return data;
        return data.toFixed(1)
      }
    },
  }
</script>

<style scoped>
  .allshop {
    width: 90%;
    margin: auto;
    padding: 10px 5px;
    display: flex;
    flex-direction: row;
    align-items: center;
  }

  .img {
    width: 100px;
    height: 100px;
  }

  .check {
    height: 100px;
    padding: 0 5px;
    line-height: 100px;
  }

  .shop_intro {
    margin-left: 5px;
    height: 100px;
    width: 56%;
    display: flex;
    flex-direction: column;
  }

  .car_intro {
    display: flex;
    height: 70%;
    align-items: flex-start;
    overflow: hidden;
  }

  .car_bottom {
    display: flex;
    height: 30%;
    align-items: flex-end;
    justify-content: space-between;
    align-items: center;
  }

  .allshop {
    margin-bottom: 15px;
    border-radius: 15px;
    border: 0.5px solid #e9e9e9;
    background: #f7f7f7;
  }

  .chooseinput {
    width: 18px;
    height: 18px;
    -webkit-appearance: radio;
  }

  .bottomset {
    height: 40px;
    width: 90%;
    margin: 0 auto;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
  }

  .Settlement {
    display: flex;
    width: 100%;
    position: fixed;
    bottom: 50px;
    z-index: 20;
  }

  .price {
    color: red;
  }

  .bottom_div {
    display: flex;
    align-items: center;
  }

  .haveBook {
    width: 100%;
    padding-top: 15px;
  }

  .noBook {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding: 30px 0px;
  }

  .toOtherWay {
    display: flex;
    flex-direction: row;
    justify-content: center;
  }

  .toAny, .toCollect {
    font-family: 'Arial Black';
    display: flex;
    width: 35%;
    height: 35px;
    justify-content: center;
    align-items: center;
  }

  .toAny {
    background: #00bac7;
    border-radius: 5px;
    border: 0.5px solid #00bac7;
    color: white;
  }

  .toCollect {
    background: #ffffff;
    margin-right: 10px;
    border-radius: 5px;
    border: 0.5px solid gray;
  }

  .checkBox_span {
    display: inline-block;
    position: relative;
    border: 1px solid #DCDFE6;
    border-radius: 2px;
    box-sizing: border-box;
    width: 14px;
    height: 14px;
    background-color: #FFF;
    z-index: 1;
    transition: border-color .25s cubic-bezier(.71, -.46, .29, 1.46), background-color .25s cubic-bezier(.71, -.46, .29, 1.46);
  }

</style>
