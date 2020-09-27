<template>
  <div>
    <div>
      <mt-header title="更多图书专题" :fixed="true" :style="getBack">
        <div @click="goBack" slot="left">
          <mt-button icon="back">返回</mt-button>
        </div>
      </mt-header>
      <div style="height: 38px"></div>
    </div>

    <div>
      <img src="./images/topic.jpg"/>
      <div v-for="(item,index) in topic" style="margin-bottom: 5px" @click="toTopic(index)">
        <img :src="item.topicImg"/>
      </div>
    </div>
  </div>
</template>

<script>

  import {reqTopic} from "../../api";

  export default {
    data() {
      return {
        topic: []
      }
    },

    created() {
      if (this.$store.state.topicMess.length > 0)
        this.topic = this.$store.state.topicMess
      else
        this.getTopic()
    },

    methods: {
      goBack() {
        this.$router.push({
          path: '/firstPage'
        })
      },

      toTopic(index) {
        this.$router.push({
          path: '/firstPage/topic/topicMess',
          query: {
            index: index,
          }
        })
      },

      async getTopic() { // 获取专题
        const result = await reqTopic()
        if (result.statu === 0) {
          this.$store.dispatch('saveTopic', result.data)
          this.topic = result.data
          /*  this.topic.map(e => {
                return delete e.books
            })*/
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
  }
</script>

<style scoped>
  img {
    max-width: 100%;
    max-height: 150px;
  }
</style>
