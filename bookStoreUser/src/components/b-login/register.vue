<template>
  <div style="width: 100%">
    <div>
      <mt-header title="注册" :style="getBack">
        <router-link to="/login" slot="left">
          <mt-button icon="back">返回</mt-button>
        </router-link>
      </mt-header>
    </div>

    <div style="width:100%;margin-top: 40px;">
      <img src="@/components/b-login/images/book_icon.png" style="display:block;margin: auto;width: 40%;"/>
    </div>

    <div>
      <div class="user_name">
        <el-input v-model="username" placeholder="请输入用户名"></el-input>
      </div>
      <div class="phone_num">
        <el-input v-model="phonenum" placeholder="请输入手机号"></el-input>
      </div>
      <div class="pass_word">
        <el-input v-model="password" type="password" placeholder="请输入密码"></el-input>
      </div>
      <div class="re_password">
        <el-input v-model="repassword" type="password" placeholder="重复输入密码"></el-input>
      </div>
    </div>

    <div class="b_button">
      <el-button type="primary">确认信息</el-button>
    </div>

  </div>

</template>

<script>
  export default {
    data() {
      return {
        username: '',
        phonenum: '',
        password: '',
        repassword: '',
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
  .el-input {
    display: block;
    width: 70%;
    margin: 15px auto;
  }

  .b_button {
    margin: auto;
    width: 70%;
  }

  .el-button {
    display: inline-block;
    width: 100%;
  }
</style>
