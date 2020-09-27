<template>
  <div>
    <div>
      <mt-header title="关于书店" :fixed="true" :style="getBack">
        <router-link to="/myCenter" slot="left">
          <mt-button icon="back">返回</mt-button>
        </router-link>
      </mt-header>
      <div :style="setHeight"></div>
    </div>

    <img src="./images/storeLogo.png" class="img"/>

    <div class="intro">
      <span v-for="(item,index) in content" :class="{title:(index == 0 || index == 5)}">{{item}}<br /></span>
    </div>
  </div>
</template>

<script>
  import {getContent} from "../../api";

  export default {

    data(){
      return {
        content:[],
        setHeight:{
          height:''
        }
      }
    },

    computed: {
      getBack() {
        return {
          'background-color': this.$store.state.themeColor
        }
      },
    },

    methods:{
      async getContent(){
        let result = await getContent();
        this.content = result;
      },

      _getHeight(){
         let width = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
         this.setHeight.height = width * 107 /409 +40+'px'
       }
    },

    created(){
      this.getContent();//获取放在后台的txt文件内容
      this._getHeight();
    }
  }
</script>

<style scoped>
  .img{
    max-width: 100%;
    position: fixed;
    left: 0;
    top: 40px;
  }

  .intro{
    background: url("./images/wordback.png");
    background-size: 100%;
    padding:5px 12px;
    font-size: 18px;
  }

  .title{
    color: red;
    font-size: 20px;
    font-weight: bold;
  }
</style>
