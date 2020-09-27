<template>
    <div id="app">
      <!--<keep-alive>-->
        <router-view/>
      <!--</keep-alive>-->
    </div>
</template>

<script>
  export default {
    name:'',
    watch:{
      // '$route':(to,from) => {
      //   console.log(to.path)
      //   console.log(from.path)
      // }
    }
  }
</script>

<style scoped>
  [v-cloak]{
    display: none;
  }

</style>
