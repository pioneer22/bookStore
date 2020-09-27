<template>
  <div class="b-swipe">
    <mt-swipe :auto="4000">
      <mt-swipe-item v-for="(picture,index) in pictures" :key="index">
          <img :src="picture.bookImg" alt="" class="swipe_img" @click="toShop(picture.bookId)"/>
      </mt-swipe-item>
    </mt-swipe>
  </div>
</template>

<script>
  import {reqBookMess} from '../../../api/index';
    export default {
        data(){
          return {
            pictures:[],
          }
        },

        created(){
          // let pic = Object.keys(this.$store.state.bookmess).length?1:0;
          // //获取图书信息
          // if(pic == 0){
            this.getBooks();
          // }else{
          //   for(let i = 0;i<4;i++)
          //     this.pictures.push(this.$store.state.bookmess[i]);
          // }

        },

        methods:{
          async getBooks(){
            let result = await reqBookMess();
            if(result.statu == 0){
              //将图书信息存到VueX
              this.$store.dispatch('saveBookMess',result.data);
              //取前四个
              for(var i = 0;i < 4;i++)
                this.pictures.push(result.data[i])

            }else{
              console.log("获取信息失败！");
            }
          },

          //跳转商品详情页面把图书id传过去
          toShop(bookId){
              this.$router.push({
                path:'/comInterface',
                query:{
                  bookId:bookId
                }
              })
          }

        }

    }
</script>

<style scoped>
  .b-swipe{
    margin-top: 5px;
    width: 100%;
  }
 .mint-swipe{
   height:200px;
 }

  .swipe_img{
    display: block;
    margin: auto;
    height:200px;
    max-width: 100%;
  }

</style>
