<template>
  <div style="width: 100%">
    <div>
      <mt-header title="登陆" :fixed="true" :style="getBack">
        <div @click="goBack" slot="left">
          <mt-button icon="back">返回</mt-button>
        </div>
      </mt-header>
      <div style="height: 40px"></div>
    </div>

    <div style="width:100%;margin-top: 40px;">
      <img src="./images/book_icon.png" style="display:block;margin: auto;width: 40%;"/>
    </div>

    <div>
      <div class="user_name">
        <!--<el-input v-model="username" placeholder="请输入用户名" @blur="judgeNull" ref="focusName" ></el-input>-->
        <el-input v-model="userid" placeholder="请输入用户ID" @blur="judgeNull"></el-input>
      </div>
      <!--<mt-popup v-model="popupVisible" popup-transition="popup-fade">输入的密码不能为空！</mt-popup>-->
      <div class="pass_word">
        <el-input v-model="password" type="password" placeholder="请输入密码" @blur="judgePass"></el-input>
      </div>
    </div>

    <div class="b_button">
      <el-button type="primary" @click="verifyLogin">登陆</el-button>

      <router-link to="/register" name="rl">
        <el-button type="primary">注册</el-button>
      </router-link>

    </div>

    <div class="b_button" style="margin-top: 15px;">
      <router-link to="/register" class="to_findmess" name="rl1"><span>忘记密码？</span></router-link>
    </div>
  </div>

</template>

<script>
  import {Toast} from 'vant';
  import {MessageBox} from 'mint-ui';
  import {reqLogin} from '../../api'

  export default {
    data() {
      return {
        userid: '',
        password: '',
      }
    },

    methods: {
      judgeNull() {
        if (this.userid == '') {
          MessageBox.alert('用户名不能为空！')
        }
      },
      judgePass() {
        if (this.password == '') {
          MessageBox.alert('输入密码不能为空！')
        }
        else if (this.password.length < 6) {
          this.password = '';
          MessageBox.alert('密码要大于等于6位！')
        }
      },

      //登陆
      async verifyLogin() {
        const {userid, password} = this;
        if (this.userid.length > 0 && this.password.length >= 6) {
          let result = await reqLogin({userid, password});
          if (result.statu == 0) {
            this.$store.dispatch('saveUser', result.data[0]);
            this.$store.dispatch('changeActive', 0)
            // this.getContact();
            Toast.success('登陆成功');
            //登陆成功请求联系人信息
            this.$router.push('./');
          } else {
            MessageBox.alert('账号或密码错误！');
          }
        }
      },
      goBack() {
        this.$store.dispatch('changeActive', 0)
        this.$router.push({
          path: '/firstPage'
        })
      },
    },

    computed: {
      getBack(){
        return {
          'background-color':this.$store.state.themeColor
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

  .el-button {
    display: inline-block;
    width: 49%;
  }

  .b_button {
    margin: auto;
    width: 70%;
  }

  .to_findmess {
    color: #E6A23C;
  }

</style>
