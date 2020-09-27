<template>
    <div v-show="$route.meta.navShow==undefined||$route.meta.navShow">
      <van-tabbar v-model="active" active-color="red">
        <van-tabbar-item
          @click="goPath('/firstpage',0)"
          :style="getBack"
        >
          <span>首页</span>
          <img
            slot="icon"
            slot-scope="props"
            :src="props.active ? tabbars[0].active : tabbars[0].normal"
          >
        </van-tabbar-item>

        <van-tabbar-item
          @click="goPath('/classify',1)"
          :style="getBack"
        >
          <span>分类</span>
          <img
            slot="icon"
            slot-scope="props"
            :src="props.active ? tabbars[1].active : tabbars[1].normal"
          >

        </van-tabbar-item>

        <van-tabbar-item
          @click="goPath('/shopCar',2)"
          :style="getBack"
          :info="shopCarNum"
        >
          <span>购物车</span>
          <img
            slot="icon"
            slot-scope="props"
            :src="props.active ? tabbars[2].active : tabbars[2].normal"
          >
        </van-tabbar-item>

        <van-tabbar-item
          @click="goPath('/myCenter',3)"
         :style="getBack"
        >
          <span>我的</span>
          <img
            slot="icon"
            slot-scope="props"
            :src="props.active ? tabbars[3].active : tabbars[3].normal"
          >
        </van-tabbar-item>

      </van-tabbar>
    </div>
</template>

<script>
    import {Dialog} from 'vant'
    import {getCarNum} from "../../api";

    export default {
      data() {
        return {
          active: 0,
          isLogin:'',
          bookNum:"",

          tabbars: [
            {
              normal: require("./images/firstpage.png"),
              active: require("./images/firstpageC.png")
            },
            {
              normal: require("./images/classify.png"),
              active: require("./images/classifyC.png")
            },
            {
              normal: require("./images/shopcar.png"),
              active: require("./images/shopcarC.png")
            },
            {
              normal: require("./images/mycenter.png"),
              active: require("./images/mycenterC.png")
            }
          ]
        };
      },
      methods: {
        goPath(val,index){
          if(val == '/shopCar')
          {
            if(this.isLogin == 0)
            {
              Dialog.confirm({
                title: '未登录',
                message: '是否跳转登陆界面？',
              }).then(() => {
                this.$router.replace({path:'/login'})
              }).catch(() => {
                // on cancel
              });
              return;
            }
          }
          this.$store.dispatch('changeActive',index);
          this.$router.push(val);
        },

        //获取购物车数量
        async getBookNum(){
          if(this.bookNum == '' && Object.keys(this.$store.state.user).length>0){
            let userId = this.$store.state.user.userId;
            let result = await getCarNum(userId);
            if(result>0)
            {
              this.bookNum = result;
              this.$store.dispatch('saveShopCarNum',result);
            }else{
              console.log('获取购物车数量失败！');
            }
          }
        }

      },

      created(){
        this.isLogin = Object.keys(this.$store.state.user).length?1:0;
        this.getBookNum();
        this.active = this.$store.state.active;
      },

      computed:{
        shopCarNum(){
          this.bookNum = this.$store.state.shopCarNum;
          return this.bookNum;
        },

        getBack(){
          return {
            'background-color':this.$store.state.themeColor
          }
        }
      }
    }
</script>

<style scoped>
  .van-tabbar-item{
    font-size: 14px;
    color: black;
  }

</style>
