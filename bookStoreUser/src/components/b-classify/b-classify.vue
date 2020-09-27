<template>
  <div class="books">
    <page-header></page-header>

    <div class="content">
      <div class="left" ref="left">
        <ul style="padding: 0">
          <li v-for="(item,index) in left" :class="{current:currentIndex === index}" @click="selectItem(index,$event)">
              <span class="left-item">
                {{item}}
              </span>
          </li>
        </ul>
      </div>

      <div class="right" ref="right">
        <ul style="padding-left: 10px;">
          <li v-for="(item,index) in right" class="right-item right-item-hook">
            <h3 style="background: #e9e9e9">{{left[index]}}</h3>
            <ul style="padding: 0px">
              <li v-for="list in item">
                <div class="right_out_div">
                  <img :src="list.bookImg" class="img" @click="goShop(list.bookId)"/>
                  <div class="right_inner_div">
                    <span class="right_inner_span1" @click="goShop(list.bookId)">{{list.bookName}}</span>
                    <span class="right_inner_span2" @click="goShop(list.bookId)">{{list.bookIntro}}</span>
                    <span class="right_inner_span3">
                        <span>￥{{list.bookPrice}}</span>
                        <img src="./images/addcar.png" class="icon" @click.stop.prevent="addCar($event,list.bookId)"/>
                      </span>
                  </div>
                </div>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </div>

<!--    <div class="ball-container">
      <div v-for="(ball,index) of balls" :key="index">
        <transition @before-enter="handleBeforeEnter"
                    @enter="handleEnter"
                    @after-enter="handleAfterEnter">
          <div class="ball" v-show="ball.show" :css="false" >&lt;!&ndash;外层盒子&ndash;&gt;
            <div class="inner inner-hook" style="background: red; border-radius: 4px;width: 8px;height: 8px"></div> &lt;!&ndash;内层盒子&ndash;&gt;
          </div>
        </transition>
      </div>
    </div>-->
  </div>


</template>
<script>

  import PageHeader from "components/b-firstpage/component/pageHeader";
  import BetterScroll from 'better-scroll'
  import {getSort, addShopCar} from "../../api";
  import {Toast} from 'vant'

  export default {
    data() {
      return {
        listHeight: [],
        left: [],
        right: [],
        scrollY: 0,//时时获取当前Y轴高度

    /*    balls: [{show: false}, {show: false}, {show: false}, {show: false}, {show: false}],
        dropBalls: [] // 用dropBalls来存放掉落的小球*/
      }

    },

    created() {
      this.left = this.$store.state.classified;
      this.right = this.$store.state.classifiedData;
    },

    mounted() {
      this.$nextTick(() => {
        this._initScroll();
        this._getHeight()
      });
    },

    computed: {

      currentIndex() {
        for (let i = 0; i < this.listHeight.length; i++) {
          let height = this.listHeight[i]
          let height2 = this.listHeight[i + 1]
          if (!height2 || (this.scrollY >= height && this.scrollY < height2)) {
            return i;
          }
        }
        //this.listHeight没有则返回0
        return 0;
      },

    },

    methods: {
      _initScroll() {
        this.lefts = new BetterScroll(this.$refs.left, {
          click: true,
        })

        this.rights = new BetterScroll(this.$refs.right, {
          probeType: 3, //探针效果时时获取滚动高度
          click: true,
          tap: true
        })

        //rights这个对象的监听事件,时时获取位置pos.y
        this.rights.on('scroll', (pos) => {
          this.scrollY = Math.abs(Math.round(pos.y))
        })

      },

      _getHeight() {
        let rightItems = this.$refs.right.getElementsByClassName('right-item-hook')
        let height = 0;
        this.listHeight.push(height);
        //rightItems没东西
        for (let i = 0; i < rightItems.length; i++) {
          let item = rightItems[i];
          height += item.clientHeight;
          this.listHeight.push(height);
        }
      },

      selectItem(index, event) {
        //better-scroll的派发事件event和普通浏览器点击事件event的属性区别_constructed
        //浏览器原生点击事件没有_constructed，所以监听该属性时要return
        if (!event._constructed) {
          return;
        } else {
          let rightItems = this.$refs.right.getElementsByClassName('right-item-hook')
          let el = rightItems[index]
          this.rights.scrollToElement(el, 300)
        }
      },


      //获得分类
      async getClassify() {
        let result = await getSort();
        if (result.statu == 0) {
          this.left = result.data.classified;
          this.$store.dispatch('saveClassified', this.left);
          this.left.forEach((item, index) => {
            this.right[index] = this.sortByKey(result.data[item], 'bookSale')
          })
          this.$store.dispatch('saveClassifiedData', this.right);
        } else
          console.log('fail')
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

      //加入购物车
      async addCar(event,bookId) {
        const userId = this.$store.state.user.userId
        let result = await addShopCar(userId,bookId);
        this.drop(event.target);
        if (result == 1) {
          this.$store.dispatch('saveShopCarNum',this.$store.state.shopCarNum+1);
          Toast.success('加购成功')
        } else {
          Toast.fail('加购失败');
        }
      },

     /* drop (el) {
        console.log("进入drop")
        for (let i = 0; i < this.balls.length; i++) { // 遍历这5个小球
          let ball = this.balls[i]
          if (!ball.show) {    // 当小球显示状态为隐藏时
            ball.show = true   // 将这个小球的显示状态设置为true
            ball.el = el       // 将cartControl传过来的对象挂载到ball的el属性上
            this.dropBalls.push(ball) // 将这个小球放入到dropBalls数组中
            return
          }
        }
      },*/

      // js动画钩子
      // beforeenter
     /* handleBeforeEnter: function (el) {
        console.log("进入beforeenter")
        let count = this.balls.length
        while (count--) {
          let ball = this.balls[count]
          if (ball.show) {
            let rect = ball.el.getBoundingClientRect() // getBoundingClientRect()获取小球相对于视窗的位置，屏幕左上角坐标为0，0
            let x = rect.left - 32 // 小球x方向位移= 小球距离屏幕左侧的距离-外层盒子距离水平的距离
            let y = -(window.innerHeight - rect.top - 22) // 负数，因为是从左上角向下
            el.style.display = ''
            el.style.webkitTransform = `translate3d(0,${y}px,0)` // 设置外层盒子，即小球垂直方向的位移
            el.style.transform = `translate3d(0,${y}px,0)`
            let inner = el.getElementsByClassName('inner-hook')[0]
            inner.style.webkitTransform = `translate3d(${x}px,0,0)` // 设置内层盒子，即小球水平方向的距离
            inner.style.transform = `translate3d(${x}px,0,0)`
          }
        }
      },*/

      // enter
    /*  handleEnter: function (el, done) {
        console.log("进入handleEnter")
        /!* eslint-disable no-unused-vars *!/
        // 触发浏览器重绘
        let rf = el.offsetHeight
        this.$nextTick(() => { // 让动画效果异步执行,提高性能
          el.style.webkitTransform = 'translate3d(0, 0, 0)'// 设置小球掉落后最终的位置
          el.style.transform = 'translate3d(0, 0, 0)'
          let inner = el.getElementsByClassName('inner-hook')[0]
          inner.style.webkitTransform = 'translate3d(0, 0, 0)'
          inner.style.transform = 'translate3d(0, 0, 0)'
          el.addEventListener('transitionend', done) // Vue为了知道过渡的完成，必须设置相应的事件监听器。它可以是transitionend或 animationend
        })
      },*/

   /*   handleAfterEnter: function (el) {
        console.log("进入handleAfterEnter")
        let ball = this.dropBalls.shift() // 完成一次动画就删除一个dropBalls的小球
        if (ball) {
          ball.show = false
          el.style.display = 'none'
        }
      },*/

    },

    components: {
      PageHeader
    }
  };
</script>
<style type="text/css">
  .content {
    display: flex;
    position: absolute;
    width: 100%;
    top: 48px;
    bottom: 50px;
    margin-top: 2px;
    margin-bottom: 2px;
    overflow: hidden;
    background: white;
    position: fixed;
  }

  .left {
    flex: 0 0 20%;
    width: 20%;
    background: #e9e9e9;
  }

  .left li {
    width: 100%;
    height: 100%;
    list-style: none;
    padding: 0;
    margin: 0;
  }

  .current {
    background: white;
  }

  .left-item {
    display: block;
    width: 100%;
    height: 45px;
    line-height: 45px;
    text-align: center;
    color: #666666;
  }

  .right {
    flex: 1;
  }

  .right-item li {
    font-family: Arial;
    width: 100%;
    height: 100px;
    list-style: none;
    margin: 0;
    padding: 0;
  }

  ul {
    list-style: none;
    margin: 0;
  }

  .right_out_div {
    width: 90%;
    height: 80px;
    margin: auto;
    display: flex;
  }

  .right_inner_div {
    display: flex;
    flex-direction: column;
    height: 80px;
    width: 68%;
    margin-left: 5px;
  }

  .right_inner_span1 {
    color: #2e2c2d;
    font-size: 16px;
    display: block;
    width: 100%;
    height: 25%;
    overflow: hidden;
  }

  .right_inner_span2 {
    color: gray;
    display: block;
    font-size: 13px;
    width: 100%;
    height: 50%;
    overflow: hidden;
  }

  .right_inner_span3 {
    font-weight: bold;
    align-self: flex-end;
    width: 100%;
    height: 25%;
  }

  .icon {
    width: 20px;
    height: 20px;
    margin-right: 5px;
    float: right;
  }

  .img {
    max-width: 30%;
  }
</style>
