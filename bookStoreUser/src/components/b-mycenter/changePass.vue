<template>
  <div>
    <div>
      <mt-header title="修改密码" :style="getBack">
        <router-link to="/myCenter" slot="left">
          <mt-button icon="back">返回</mt-button>
        </router-link>
      </mt-header>
    </div>

    <div style="width: 90%;margin: auto">
      <div style="width: 100%;text-align: center">
        <img src="@/components/b-mycenter/images/changeIcon.png" class="passIcon"/>
      </div>
      <el-input placeholder="输入原密码" v-model="oldPass"></el-input>
      <el-input placeholder="输入新密码" v-model="newPass" type="password"></el-input>
      <el-input placeholder="重复输入新密码" v-model="reNewPass" type="password"></el-input>
      <el-button @click="confirmChange"  :style="getBack" class="elButton">确认修改</el-button>
    </div>
  </div>
</template>

<script>

  import {MessageBox} from 'mint-ui';
  import {reqChangeMess} from "../../api";

  export default {
    data() {
      return {
        userId: '',
        oldPass: '',
        newPass: '',
        reNewPass: '',
      }
    },

    methods: {
      //修改密码
      confirmChange() {
        if (this.oldPass == '') {
          MessageBox.alert('请输入原密码！');
          return;
        }
        if ((this.newPass != this.reNewPass) || this.newPass.length < 6) {
          MessageBox({
            title: '提示',
            message: '新密码输入不一致或密码小于6位',
            confirmButtonText: '我知道了'
          });
          this.newPass = '';
          this.reNewPass = '';
          return;
        }

        MessageBox.confirm('确定执行此操作?').then(async (action) => {
          this.userId = this.$store.state.user.userId
          const {userId, oldPass, newPass} = this;
          let result = await reqChangeMess({userId, oldPass, newPass});
          if (result.statu == 0) {
            MessageBox.alert('密码修改成功！');
            this.oldPass = "";
            this.newPass = "";
            this.reNewPass = '';
            this.$router.push('/myCenter');
          } else {
            MessageBox.alert('输入原密码有误！');
          }
        });
      },
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
  .elButton{
    width: 100%;
    margin-top: 5px;
  }

  .el-input {
    margin: 5px 0;
  }

  .passIcon {
    width: 100px;
    height: 100px;
  }
</style>
