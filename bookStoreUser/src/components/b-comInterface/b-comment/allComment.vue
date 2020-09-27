<template>
    <div>
      <div v-for="item in comment" class="comment_div" style="display: flex;flex-direction: row">
        <!--<span style="color: gray;">用户：{{item.userId.substring(0,1)}}**{{item.userId.substring(item.userId.length-1,item.userId.length)}}</span>-->
        <div style="color: gray;"><img :src="item.userImg" style="width: 40px;height: 40px;border-radius: 20px"></div>
        <div style="display: flex;flex-direction: column">
          <span style="margin-left: 10px"><span style="display: inline-block;padding-right: 10px">{{item.userName}}</span> {{item.comment.comTime | formatData}}</span>
          <span style="margin-left: 10px">{{item.comment.comMess}}</span>
        </div>
      </div>
    </div>
</template>

<script>
    import {getUserImg} from "../../../api";
    import axios from 'axios'

    export default {
        data(){
          return {
            comment:[],
            userImg:''
          }
        },

        created(){
          this.comment = this.$store.state.comment;
        },

      methods:{

      },

      computed:{

      },


    }
</script>

<style scoped>
  .comment_div{
    padding: 7px 12px;
    margin: 5px 0px;
    background: gainsboro;
    font-size: 14px;
    color: #333333;
  }
</style>
