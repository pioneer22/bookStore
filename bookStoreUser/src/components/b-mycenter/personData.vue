<template>
  <div>
    <div>
      <mt-header title="修改个人资料" :fixed="true" :style="getBack">
        <router-link to="/myCenter" slot="left">
          <mt-button icon="back">返回</mt-button>
        </router-link>
      </mt-header>
      <div style="height: 40px"></div>
    </div>

    <div>
      <div class="outcenterHead">
        <span>我的头像</span>

        <label for="file">
          <div class="item_bock">
            <img :src="userimg"/>
            <input type="file" accept="image/*" class="hiddenInput" id="file" ref="imgUrl" @change="handleFile($event)">
          </div>
        </label>
      </div>


      <div class="outcenter">
        <span>用户ID</span>
        <span class="right_span">{{userid}}  <i class="el-icon-arrow-right"></i></span>
      </div>
      <div class="outcenter">
        <span>用户名</span>
        <span class="right_span">
              <input type="text" v-model="username"/>
               <i class="el-icon-arrow-right"></i>
            </span>
      </div>
      <div class="outcenter">
        <span>性别</span>
        <span class="right_span">
          <select v-model="usersex">
            <option value="男">男</option>
            <option value="女">女</option>
          </select>
          <i class="el-icon-arrow-right"></i>
        </span>
      </div>

      <div class="outcenter">
        <span>年龄</span>
        <span class="right_span">
          <input type="text" v-model="userage"/>
          <i class="el-icon-arrow-right"></i>
        </span>
      </div>

      <div class="outcenter">
        <span>手机号</span>
        <span class="right_span">
          <input type="text" v-model="userphone"/>
          <i class="el-icon-arrow-right"></i>
        </span>
      </div>
    </div>

    <div>
      <el-button @click="uploadHeadImg()" :style="getBack" class="elButton">确认修改信息
      </el-button>
    </div>

  </div>
</template>

<script>

  import {MessageBox} from 'mint-ui'
  import {changeUserImg} from "../../api";

  export default {
    data() {
      return {
        userid: this.$store.state.user.userId ? this.$store.state.user.userId : '未设置',
        username: this.$store.state.user.userName ? this.$store.state.user.userName : '未设置',
        usersex: this.$store.state.user.userSex ? this.$store.state.user.userSex : '男',
        userphone: this.$store.state.user.userPhone ? this.$store.state.user.userPhone : '未设置',
        userage: this.$store.state.user.userAge ? this.$store.state.user.userAge : '未设置',
        userimg: this.$store.state.user.userImg ? this.$store.state.user.userImg : "/api/Img/my.jpg",

      }
    },

    methods: {

      //显示图片
      handleFile($event) {
        let $target = $event.target || $event.srcElement
        let file = $target.files[0]
        var reader = new FileReader()
        reader.onload = (data) => {
          let res = data.target || data.srcElement
          this.userimg = res.result
        }
        reader.readAsDataURL(file)
      },

      uploadHeadImg() {
        this.upload();
      },

      // 打开图片上传
      async upload() {
        let formData = new window.FormData();
        var imgURL = this.$refs.imgUrl.files[0];

        if (!(/^1([38]\d|5[0-35-9]|7[3678])\d{8}$/).test(this.userphone)) {
          MessageBox.alert("手机号输入有误,请重新输入！");
          this.userphone = this.$store.state.user.userPhone;
          return;
        }

        const {userid, username, usersex, userphone, userage} = this;
        formData.append('userId',userid)
        formData.append('imgUrl', imgURL);
        formData.append('username', username);
        formData.append('usersex', usersex);
        formData.append('userphone', userphone);
        formData.append('userage', userage);

        let result = await changeUserImg(formData);
        if (result.statu == 0) {
          MessageBox.alert("个人信息修改成功！");
          //更新VueX数据
          this.$store.dispatch('saveUser', result.data);
        } else {
          MessageBox.alert("个人信息修改失败！")
        }
      },
    },

    computed: {
      getBack() {
        return {
          'background-color': this.$store.state.themeColor
        }
      },

    },

  }
</script>

<style scoped>

  .outcenter {
    margin: 5px 0px;
    padding: 10px 10px;
    background: #e9e9e9;
    /*border-radius: 10px;*/
    font-weight: bold;
    font-size: 16px;
    color: #333333;
  }

  .outcenterHead {
    height: 64px;
    line-height: 64px;
    margin: 5px 0px;
    padding: 5px 10px;
    background: #e9e9e9;
    /*border-radius: 10px;*/
    font-size: 20px;
    font-weight: bold;
    color: #333333;
  }

  .right_span {
    float: right;
    color: gray;
  }

  .right_span input, .right_span select {
    background-color: #e9e9e9;
    border: 0;
    outline: none;
    text-align: right;
    font-weight: bold;
    font-size: 16px;
    color: gray;
  }

  /*清除下拉箭头*/
  select {
    /*将默认的select选择框样式清除*/
    appearance: none;
    -moz-appearance: none;
    -webkit-appearance: none;
    padding-left: 15px;
  }

  select::-ms-expand {
    display: none;
  }

  .item_bock {
    align-items: center;
    justify-content: space-between;
    height: 64px;
    width: 64px;
    background: #fff;
    float: right;
  }

  .item_bock img {
    width: 64px;
    height: 64px;
    border-radius: 32px;
    background-color: #e9e9e9;
  }

  .hiddenInput {
    display: none;
  }

  .elButton {
    width: 100%;
    font-size: 16px;
    color: white;
  }
</style>
