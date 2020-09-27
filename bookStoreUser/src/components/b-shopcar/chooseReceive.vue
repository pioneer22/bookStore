<template>
  <div>
    <div>
      <mt-header title="选择联系人" :fixed="true" :style="getBack">
        <div @click="goBack('/shopcar/payorder')" slot="left">
          <mt-button icon="back">返回</mt-button>
        </div>

        <div slot="right" @click="goBack('/myCenter/receiveAddress')">编辑</div>
      </mt-header>
      <div style="height: 40px"></div>
    </div>

    <div>
      <div class="addContact" @click="isShowPublish = true;">
        <div class="addIcon">
          <img src="./images/addContact.png">
          <span>添加联系人</span>
        </div>
        <span>
           <i class="el-icon-arrow-right"></i>
        </span>
      </div>
      <div class="devide"></div>

      <div class="contactItemDiv">
        <div style="padding: 10px;margin-top: 5px;">
          <span>联系人</span>
        </div>

        <div v-for="(item,index) in address " style="margin-bottom: 5px" @click="changeAddress(index)">
          <div class="ItemDiv">
            <div class="IconDiv">{{item.contactName.substring(0,1)}}</div>
            <div class="contentDiv">
              <span>{{item.contactName}}</span>
              <span class="phoneSpan">{{item.contactPhone}}</span><br>
              <span class="isDefault"  :class="{default:item.statu == 1}">默认</span>
              <span style="font-size: 14px">{{item.contactAddress}}</span>
            </div>
          </div>
          <div class="devide"></div>
        </div>

      </div>
    </div>

    <el-dialog title="添加联系人" :visible.sync="isShowPublish" center width="80%">
      <el-input placeholder="收货人" v-model="contactName1"></el-input>
      <el-input placeholder="联系电话" v-model="contactPhone1"></el-input>
      <el-input placeholder="收货地址" v-model="contactAddress1"></el-input>
      <el-button type="warning" @click="insertContact" style="width: 45%">添加</el-button>
      <el-button @click="isShowPublish = false" type="warning" style="width: 45%">取消</el-button>
    </el-dialog>
  </div>
</template>

<script>
  import {reqContacts, insertContacts} from "../../api";
  import {MessageBox} from 'mint-ui'
  import {Toast} from 'vant';
  export default {
    data() {
      return {
        userId:'',
        address: [],
        contactName1: '',
        contactPhone1: '',
        contactAddress1: '',
        isShowPublish: false,//弹框增加联系人
      }
    },
    created() {
      this.userId = this.$store.state.user.userId;
      this.address = this.$store.state.contacts;
    },

    computed: {
      getBack() {
        return {
          'background-color': this.$store.state.themeColor
        }
      }
    },

    methods: {
      goBack(path) {
        this.$router.push({
          path: path
        })
      },

      changeAddress(index){
        this.$store.dispatch('saveReceive',this.address[index]);
        this.$router.push({
          path:'/shopcar/payorder',
        })
      },

      async insertContact() {
        this.isShowPublish = false;
        //添加联系人
        MessageBox.confirm('确认添加？', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          center: true,
          roundButton: true
        }).then(async (action) => {
          const {userId,contactName1, contactPhone1, contactAddress1} = this;
          let isInsert = await insertContacts({userId,contactName1, contactPhone1, contactAddress1});
          if (isInsert == 1) {
            Toast.success('添加成功');
            this.getContact();
          } else {
            Toast.fail('添加失败');
            this.isShowPublish = true;
          }
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消添加'
          })
        })
      },

      async getContact() {
        //请求联系人信息
        const {userId} = this;
        let result = await reqContacts({userId});
        if (result.statu == 0) {//请求联系人成功
          this.address = result.data;
          this.$store.dispatch('saveContact', result.data);
          this.$store.dispatch('saveReceive',result.data[0]);
          console.log(result.msg);
        } else {
          // 请求联系人失败
          console.log(result.error);
        }
      },

    }
  }
</script>

<style scoped>
  .devide {
    height: 2px;
    background: -webkit-repeating-linear-gradient(135deg, #ff6c6c 0, #ff6c6c 20%, transparent 0, transparent 25%, #1989fa 0, #1989fa 45%, transparent 0, transparent 50%);
    background: repeating-linear-gradient(-45deg, #ff6c6c 0, #ff6c6c 20%, transparent 0, transparent 25%, #1989fa 0, #1989fa 45%, transparent 0, transparent 50%);
    background-size: 80px;
  }

  .addContact {
    padding: 10px;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
  }

  .addIcon {
    display: flex;
    align-items: center;
  }

  .addIcon span {
    margin-left: 10px;
    font-size: 14px;
    color: #323233;
  }

  .contactItemDiv{
    background: #EAEAEA;
    font-size: 14px;
    color: #323233;
  }

  .ItemDiv{
    background: white;
    padding: 10px;
    display: flex;
    flex-direction: row;
    align-items: center;
  }

  .IconDiv{
    padding: 10px;
    width: 16px;
    height: 16px;
    line-height:16px;
    border-radius: 18px;
    background: #818181;
    color: white;
  }

  .contentDiv{
    margin-left: 10px;
    font-size: 16px;
  }

  .phoneSpan{
    font-size: 12px;
    color: #666666;
  }

  .isDefault{
    display: none;
  }

  .default{
    padding: 4px;
    display: inline-block;
    border: 0.5px solid #EA3C25;
    background: #E1335F;
    font-size: 14px;
    line-height: 14px;
    color: white;
    border-radius: 5px;
  }

  .el-input {
    margin: 5px 0px;
  }

  .el-button {
    margin: 5px;
  }
</style>
