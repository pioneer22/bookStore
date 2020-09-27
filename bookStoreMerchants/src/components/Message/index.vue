<template>
  <div @click="goPath">
    <el-badge :value="count" class="item" type="primary">
      <svg-icon class-name="message" icon-class="message" />
    </el-badge>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: "Message",
  created() {
    this.$store.dispatch("message/saveCount")
  },

  computed: {
    ...mapGetters([
      'count'
    ])
  },

  methods: {
    goPath() {
      this.$router.push({ path: '/noticeManage/message' })
    }
  }
}
</script>

<style>
  .el-badge__content.is-fixed {
    position: absolute;
    top: 15px;
    right: 10px;
    -webkit-transform: translateY(-50%) translateX(100%);
    transform: translateY(-50%) translateX(100%);
  }
</style>
