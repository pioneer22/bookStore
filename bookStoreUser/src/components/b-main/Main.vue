<template>
    <div>
      <router-view></router-view>
      <!--<Bottom></Bottom>-->
      <Footer></Footer>
    </div>
</template>

<script>
  import Bottom from "components/b-footer/bottom"
  import Footer from "components/b-footer/footer"
  export default {

      methods:{

      },

      components:{
        Bottom,Footer
      },
    }
</script>

<style scoped>

</style>
