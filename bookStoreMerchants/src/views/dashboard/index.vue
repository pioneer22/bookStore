<template>
  <div class="dashboard-container" >
    <div style="width: 68%;display: inline-block">
      <el-carousel :interval="4000" height="500px" class="dashboard-swipe">
        <el-carousel-item v-for="item in 4" :key="item">
          <img :src="img[item-1]" class="img" >
        </el-carousel-item>
      </el-carousel>
    </div>
    <div style="display: inline-block;width: 30%;height: 500px;">
      <box-card style="height: 100%"/>
    </div>
  </div>
</template>

<script>
// import { mapGetters } from 'vuex'
import BoxCard from './components/BoxCard'
export default {
  name: 'Dashboard',
  components: {
    "box-card": BoxCard
  },

  data() {
    return {
      img: [
        require('../../images/swipe1.jpg'),
        require('../../images/swipe2.jpg'),
        require('../../images/swipe3.jpg'),
        require('../../images/swipe4.jpg')
      ]
    }
  },
  computed: {
/*    ...mapGetters([
      'name',
      'roles'
    ])*/
  }
}
</script>

<style type="text/scss" rel="stylesheet/scss" lang="scss" scoped>
.dashboard {
  &-container {
    margin: 20px;
    /*&::after {
      display: inline-block;
      height: 100%;
      width: 0;
      vertical-align: middle;
    }*/
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
  &-swipe {
    margin: auto;
    text-align: center;
    vertical-align: middle;
    .img{
      max-width: 100%;
      height: 100%;
    }
  }
}
</style>
