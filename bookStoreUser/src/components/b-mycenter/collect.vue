<template>
  <div>
    <div>
      <mt-header title="收藏夹" :fixed="true" :style="getBack">
        <div @click="goBack" slot="left">
          <mt-button icon="back">返回</mt-button>
        </div>
      </mt-header>
      <div style="height: 40px"></div>
    </div>

    <div @click="showCollectClassify" class="allDiv">
      <span>{{title}}</span>
      <van-icon name="arrow-down" v-if="isDown"/>
      <van-icon name="arrow-up" v-else/>
    </div>

    <div v-if="!isDown">
      <div class="outClassifiedDiv">
        <div class="classifiedDiv" @click="toAllCollect(-1)">
          <span class="title">全部类目</span>
          <span class="bookNum">{{getAllNum()}}</span>
        </div>

        <div v-for="(item,index) in classifiedItem" class="classifiedDiv" @click="toAllCollect(index)">
          <span class="title">{{item}}</span>
          <span class="bookNum">{{classifiedItemData[index].length}}</span>
        </div>
      </div>
    </div>

    <div class="itemOut">
      <div v-for="(item,index) in classifiedTemp" class="itemInner" @click="goShop(item.bookId)">
        <div class="imgDiv">
          <img :src="item.bookImg"/>
        </div>

        <div class="messDiv">
          <span class="messDiv_span1">{{item.bookIntro}}<br>
              <span>{{count[index]}}人收藏</span>
          </span>
          <span class="messDiv_span2">￥{{item.bookPrice}}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import {getAllCollect} from "../../api";

  export default {
    data() {
      return {
        isDown: true,
        title: "全部类目",
        classifiedItem: [],//保存分类条目
        classifiedItemData: [],//保存各个分类信息
        classifiedTemp: [],//用于切换显示信息
        count: [],//存放收藏人数
      }
    },

    created() {
      this.getPersonCollect();
    },

    methods: {
      goBack() {
        this.$router.push({
          path: '/myCenter'
        })
      },

      //展示类目
      showCollectClassify() {
        this.isDown = !this.isDown;
      },

      async getPersonCollect() {
        if (Object.keys(this.$store.state.user).length > 0) {
          let userId = this.$store.state.user.userId;
          let result = await getAllCollect(userId);
          if (result.statu == 0) {
            this.classifiedItem = result.data.classfiedNameSet;
            this.count = result.data.count;
            //获取对象名称
            this.classifiedItem.forEach((item, index) => {
              this.classifiedItemData[index] = result.data[item]
            })
            this.classifiedItemData.forEach(item => {
              item.forEach(message => {
                this.classifiedTemp.push(message);
              })
            })
          }
          console.log(result.msg)
        } else {
          console.log(result.error)
        }
      },

      getAllNum() {
        let AllNum = 0;
        this.classifiedItemData.forEach(item => {
          AllNum += item.length;
        })
        return AllNum;
      },

      toAllCollect(index) {
        if (index < 0) {
          this.title = '全部类目';
          this.classifiedTemp = [];
          this.classifiedItemData.forEach(item => {
            item.forEach(message => {
              this.classifiedTemp.push(message);
            })
          })
        } else {
          this.classifiedTemp = this.classifiedItemData[index];
          this.title = this.classifiedItem[index];
        }
        this.isDown = true;
      },

      goShop(bookId) {
        this.$router.push({
          path: '/comInterface',
          query: {
            bookId: bookId
          }
        })
      }
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
  .allDiv {
    height: 35px;
    line-height: 35px;
    font-size: 12px;
    padding-left: 10px;
  }

  .outClassifiedDiv {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
  }

  .classifiedDiv {
    display: flex;
    width: 43%;
    margin-left: 2%;
    justify-content: space-between;
    padding: 2%;
    background: #ECECE4;
    border-radius: 3px;
    margin-bottom: 2%;
  }

  .title {
    font-size: 12px;
    color: #52524F;
  }

  .bookNum {
    font-size: 9px;
    color: gray;
  }

  .itemOut {
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
  }

  .itemInner {
    display: flex;
    flex-direction: row;
    padding: 2%;
    width: 90%;
    margin-bottom: 2%;
    background: #F3F2F2;
  }

  .imgDiv {
    display: flex;
    flex: 1;
    height: 100px;
    align-items: center;
    justify-content: center;
    border-radius: 5px;
    background: white;
  }

  .imgDiv img {
    max-width: 100%;
    max-height: 100%;
  }

  .messDiv {
    flex: 3;
    display: flex;
    flex-direction: column;
    margin-left: 2%;
  }

  .messDiv_span1 {
    flex: 2;
    font-size: 14px;
  }

  .messDiv_span1 span {
    font-size: 10px;
    color: gray;
  }

  .messDiv_span2 {
    color: red;
    flex: 1;
    display: flex;
    align-items: flex-end;
  }
</style>
