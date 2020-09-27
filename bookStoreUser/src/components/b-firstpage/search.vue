<template>
  <div>
    <div class="search" :style="getBack">
      <div class="back" @click="toBack">
        <img src="./images/back.png"/>
      </div>
      <div style="width: 85%;">
        <van-search
          v-model="searchValue"
          placeholder="请输入搜索关键词"
          show-action
          shape="round"
          @search="onSearch"
          @input="realTime"
          style="padding:0px"
          :background="getBackColor"
          @click="getShow"
        >
          <div slot="action" @click="onSearch" :style="getBack">
            <van-button round type="warning" size="small">搜索</van-button>
          </div>
        </van-search>
      </div>
    </div>
    <div style="height: 44px"></div>

    <!--实时查询出来相应的词-->
    <div v-if="showList">
      <div v-for="(item,index) in dataList" @click="getSearchValue(index)" class="searchOutDiv">
        <div class="searchInDiv" v-html="item"></div>
        <img src="./images/lefttop.png" style="width: 24px;height: 24px"/>
      </div>
    </div>

    <div class="searchBox" v-if="!showList && !clickSearch">
      <img src="./images/searchBoy.jpg"/>
    </div>

    <div v-if="clickSearch">
      <van-tabs v-model="active">
        <van-tab>
          <div slot="title" @click="showCell">
            {{choose}}
            <van-icon name="arrow-down" v-if="down"/>
            <van-icon name="arrow-up" v-else/>
          </div>

          <div v-show="showCellList" class="showCell">
            <van-list>
              <van-cell
                v-for="(item,index) in list"
                :key="item"
                :title="item"
                @click="changeBook(index)"
              />
            </van-list>
          </div>
        </van-tab>

        <van-tab>
          <div slot="title" @click="showSale">
            <span>销量</span>
          </div>
        </van-tab>

        <van-tab>
          <div slot="title" class="cut" @click="toggleShow">
            <img :src="cut" class="iconSize"/>&nbsp
          </div>
        </van-tab>

        <van-tab>
          <div slot="title" class="screen" @click="screenBook">
            筛选<img src="./images/screen.png" class="iconSize"/>
          </div>
        </van-tab>
      </van-tabs>
    </div>

    <!--筛选弹框-->
    <van-popup v-model="screenShow" position="bottom">
      <div style="width: 90%;margin: auto;font-size: 14px">

        <div class="itemScreen" style="margin-top: 10px">
          <div class="itemTitle">价格区间(元)</div>
          <div class="itemShow itemShowMore">
            <input type="text" class="inputPrice" placeholder="最低价" v-model="downPrice">
            <img src="./images/to.png" style="width: 15%"/>
            <input type="text" class="inputPrice" placeholder="最高价" v-model="upPrice">
          </div>
        </div>

        <div class="itemScreen">
          <div class="itemTitle">折扣和服务</div>
          <div class="itemShow">
            <span v-for="(item,index) in discount" class="disCount" :class="'count'+index"
                       @click="getDiscount(index)">{{item.title}}</span>
          </div>
        </div>

        <div class="itemScreen" style="padding: 10px;text-align: center">
          <van-button type="info" size="small" round @click="sure">确定</van-button>
          <van-button type="info" size="small" round @click="reset">重置</van-button>
        </div>

      </div>
    </van-popup>

    <div v-for="(item,index) in dataTemp" class="bookList" v-if="showWay">
      <van-card
        tag="精品"
        :price="item.bookPrice"
        :desc="item.bookName"
        :title="item.bookIntro"
        :thumb="item.bookImg"
        @click="goShop(item.bookId)"
      >
        <div slot="num">
          <span>{{item.bookSale}}人付款</span>
        </div>
      </van-card>
    </div>

    <div class="cutDiv" v-if="!showWay">
      <div class="showDiv" v-for="(item,index) in dataTemp">
        <div class="parcelDiv" @click="goShop(item.bookId)">
          <div class="showImgDiv">
            <img :src="item.bookImg" class="imgBook"/>
          </div>

          <span class="cutDivSpan">{{item.bookName}}:{{item.bookIntro}}</span>
          <div class="bottomDiv">
            <span style="color: red;">￥{{item.bookPrice}}</span>
            <span>{{item.bookSale}}人付款</span>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
  import {realTimeSearch, getSearchBook} from "../../api";
  import {changeColor} from "@/common/js/RegExp";
  import $ from 'jquery'

  export default {
    data() {
      return {
        searchValue: '',
        dataList: [],//存放实时数据
        dataResult: [],
        finished: false,
        showList: false,
        clickSearch: false,
        showWay: true,
        screenShow: false,

        active: 0,
        cut: '',
        choose: '综合',
        down: true,
        bookData: [],//存放拿回来的数据

        list: ['综合', '价格升序', '价格降序'],
        showCellList: false,

        dataTemp: [],//用于数据切换

        downPrice: '',
        upPrice: '',
        discount: [
          {
            title: '包邮',
            bechecked: false
          },
          {
            title: '通用排序',
            bechecked: false
          }
        ],

      }
    },

    created() {
      this.cut = require('./images/cut1.png')
    },

    methods: {
      //查询点击事件
      async onSearch() {
        this.clickSearch = true;
        this.showList = false;
        const {searchValue} = this;

        let result = await getSearchBook({searchValue});
        if (result.statu == 0) {
          this.dataTemp = result.data.comprehensive;
          this.bookData = [];
          this.bookData.push(result.data.comprehensive);
          this.bookData.push(result.data.priceasc);
          this.bookData.push(result.data.pricedesc);
          this.bookData.push(result.data.saledesc);
          console.log(result.msg)
        } else {
          console.log(result.error)
        }
      },

      toBack() {
        this.$router.go('-1');
      },

      //实时获取查询信息
      realTime() {
        if (this.timer)
          clearTimeout(this.timer)
        if (this.searchValue && this.searchValue.length > 0) {
          this.timer = setTimeout(async () => {
            const {searchValue} = this;
            let result = await realTimeSearch({searchValue});
            if (result.statu == 0) {
              this.dataList = [];
              result.data.author.forEach(item => {
                this.dataList.push(item);
                this.dataResult.push(item);
              })
              result.data.bookName.forEach(item => {
                this.dataList.push(item);
                this.dataResult.push(item);
              })
              result.data.bookPress.forEach(item => {
                this.dataList.push(item);
                this.dataResult.push(item);
              })

              this.dataList = changeColor(this.dataList, this.searchValue);//高亮要v-html
              console.log(result.msg)
            } else {
              console.log(result.error)
            }
          }, 500)
        }
      },

      getSearchValue(index) {
        this.searchValue = this.dataResult[index];
        this.showList = false;
      },

      getShow() {
        this.dataList = [];
        this.dataResult = [];
        this.showList = true;
        this.showCellList = false;
      },

      //切换显示效果
      toggleShow() {
        if (this.cut == require('./images/cut1.png'))
          this.cut = require('./images/cut2.png')
        else
          this.cut = require('./images/cut1.png')
        this.showWay = !this.showWay;
      },

      //点击第一个标签
      showCell() {
        this.showCellList = !this.showCellList;
        this.down = !this.showCellList;
        // this.dataTemp = this.bookData[0];
      },

      //最左标签栏选中后对应排列数据
      changeBook(index) {
        if (index == 0)
          this.dataTemp = this.bookData[0];
        else if (index == 1)
          this.dataTemp = this.bookData[1];
        else if (index == 2)
          this.dataTemp = this.bookData[2];
        this.showCellList = false;
        this.down = true;
      },

      //按销量排列
      showSale() {
        this.dataTemp = this.bookData[3];
        this.showCellList = false;
        this.down = true;
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

      //筛选，弹框选择
      screenBook() {
        this.screenShow = true;
      },

      //筛选条件
      getDiscount(index) {
        this.discount[index].chenked = !this.discount[index].chenked;
        if (this.discount[index].chenked)
          $('.count' + index).css({background: '#ff976a', color: 'white'})
        else
          $('.count' + index).css({background: '#e9e9e9', color: 'black'})
      },

      //重置
      reset() {
        this.downPrice = '';
        this.upPrice = '';
        this.discount.forEach((item, index) => {
          this.discount[index].bechecked = false;
          $('.count' + index).css({background: '#e9e9e9', color: 'black'});
        })
      },

      //确定筛选
      sure() {
        if (this.downPrice != '' && this.upPrice != '' && this.downPrice < this.upPrice) {
          let temp = [];
          this.dataTemp.forEach(item => {
            if (item.bookPrice >= this.downPrice && item.bookPrice <= this.upPrice) {
              temp.push(item)
            }
          })
          this.dataTemp = temp;
        }

        this.discount.forEach((item, index) => {
          if (item && (index == 0)) {
            let temp = [];
            this.dataTemp.forEach(item => {
              item.bookPrice > 69 ? temp.push(item) : '';
            })
            this.dataTemp = temp;
          }
        })

        //筛选后清空条件
        this.screenShow = false;
        this.reset();
      }
    },

    computed:{
      getBackColor(){
        return this.$store.state.themeColor;
      },

      getBack(){
        return {
          'background':this.$store.state.themeColor
        }
      }

    }
  }
</script>

<style scoped>
  .search {
    display: flex;
    position: fixed;
    z-index: 200;
    width: 100%;
    flex-direction: row;
    align-items: center;
    /*background: #e9e9e9;*/
    padding: 5px;
  }

  .back {
    width: 15%;
    display: flex;
    justify-content: center;
  }

  .searchOutDiv {
    width: 100%;
    display: flex;
    padding: 10px 15px;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    line-height: 24px;
    position: relative;
    background-color: #fff;
    color: #323233;
    font-size: 14px;
    overflow: hidden;
    border-bottom: 0.2px solid gainsboro;
  }

  .searchInDiv {
    -webkit-box-flex: 1;
    flex: 1;
  }

  .searchBox {
    display: flex;
    justify-content: center;
    padding: 10px 0px;
  }

  .searchBox img {
    max-width: 100%;
  }

  .showCell {
    position: fixed;
    z-index: 150;
  }

  .van-tab {
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .screen, .cut {
    align-items: center;
    display: flex;
    justify-content: center;
  }

  .van-card__bottom {
    height: 60%;
    display: flex;
    align-items: flex-end;
    justify-content: space-between;
  }

  .van-card {
    width: 100%;
  }

  .bookList {
    padding: 5px 10px;
  }

  .imgBook {
    max-width: 100%;
  }

  .cutDiv {
    width: 100%;
    display: flex;
    flex-wrap: wrap;
    background-color: #e9e9e9;
    padding-bottom: 10px;
  }

  .showDiv {
    width: 46%;
    padding: 8px 0px 8px 8px;
    color: #323233;
    font-weight: bold;
    font-size: 12px;
    height: 260px;
  }

  .parcelDiv {
    background-color: white;
    padding: 3px;
    border-radius: 10px;
  }

  .showImgDiv {
    height: 190px;
    display: flex;
    align-items: center;
  }

  .bottomDiv {
    display: flex;
    justify-content: space-between;
    align-self: flex-end;
  }

  .cutDivSpan {
    display: inline-block;
    height: 56px;
  }

  .inputPrice {
    background-color: #fff;
    border-radius: 15px;
    border: 1px solid #dcdfe6;
    color: #606266;
    font-size: inherit;
    height: 30px;
    line-height: 30px;
    padding: 0 10px;
    width: 25%;
    text-align: center;
  }

  .inputPrice:hover {
    border-color: deepskyblue;
  }

  .disCount {
    display: inline-block;
    padding: 6px 10px;
    background: #e9e9e9;
    border-radius: 15px;
    margin-left: 10px;
  }

  .itemScreen {
    border-bottom: 0.2px solid gainsboro;
  }

  .itemTitle {
    padding: 10px;
    font-weight: bold;
  }

  .itemShow {
    display: flex;
    align-items: center;
    padding: 5px;
  }

  .itemShowMore {
    justify-content: center;
  }

  .iconSize {
    width: 24px;
    height: 24px;
  }


</style>

<style>
  .van-tabs__wrap {
    top: 44px;
    position: fixed;
  }
</style>
