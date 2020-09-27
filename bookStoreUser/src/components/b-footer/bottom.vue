<template>
  <div class="bottom-tab" v-show="$route.meta.navShow==undefined||$route.meta.navShow">
    <div class="tab-item" @click="switchTo('/firstpage')">
      <img :src="'/firstpage' === $route.path ? tabBarImgArr[0].selected : tabBarImgArr[0].normal" alt="首页">
      <span :class="{on: '/firstpage' === $route.path}">首页</span>
    </div>
    <div class="tab-item" @click="switchTo('/classify')">
      <img :src="'/classify' === $route.path ? tabBarImgArr[1].selected : tabBarImgArr[1].normal" alt="分类">
      <span :class="{on: '/classify' === $route.path}">分类</span>
    </div>
    <div class="tab-item" @click="switchTo('/shopCar')">
      <img :src="'/shopCar' === $route.path ? tabBarImgArr[2].selected : tabBarImgArr[2].normal" alt="购物车">
      <span :class="{on: '/shopCar' === $route.path}">购物车</span>
    </div>

    <div class="tab-item" @click="switchTo('/myCenter')">
      <img :src="'/myCenter' === $route.path ? tabBarImgArr[3].selected : tabBarImgArr[3].normal" alt="我的">
      <span :class="{on: '/myCenter' === $route.path}">我的</span>
    </div>
  </div>
</template>

<script>
  import {Dialog} from 'vant'
    export default {
        data(){
          return {
            tabBarImgArr:[   //图片切换
              {normal: require('@/components/b-footer/images/firstpage.png'), selected: require('@/components/b-footer/images/firstpageC.png')},
              {normal: require('@/components/b-footer/images/classify.png'), selected: require('@/components/b-footer/images/classifyC.png')},
              {normal: require('@/components/b-footer/images/shopcar.png'), selected: require('@/components/b-footer/images/shopcarC.png')},
              {normal: require('@/components/b-footer/images/mycenter.png'), selected: require('@/components/b-footer/images/mycenterC.png')},
            ],
            isLogin:'',
          }
        },

      methods:{
        switchTo(path){
          if(path == '/shopCar')
          {
              if(this.isLogin == 0)
              {
                Dialog.confirm({
                  title: '未登录',
                  message: '是否跳转登陆界面？'
                }).then(() => {
                  this.$router.replace({path:'/login'})
                }).catch(() => {
                  // on cancel
                });
                return;
              }
          }
          this.$router.replace(path)
        }
      },

      created(){
          this.isLogin = Object.keys(this.$store.state.user).length?1:0;
      }

    }
</script>

<style scoped>
  .bottom-tab{
    width:100%;
    height:55px;
    background:#E6A23C;
    position:fixed;
    left:0px;
    bottom:0px;
    display:flex;
    z-index:100;
    padding-top: 3px;
  }

  .tab-item{
    display:flex;
    flex:1;
    flex-direction:column;
    align-items:center;
    justify-content:center;
    font-size:14px;
    color:#666;
  }
  img{
    width:35%;
    margin-bottom:2px;
  }
  .on{
    color:red;
  }

</style>
