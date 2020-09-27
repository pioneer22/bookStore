<template>
  <div>
    <div>
      <mt-header title="我的收货地址" :fixed="true" :style="getBack">
        <div @click="goBack" slot="left">
          <mt-button icon="back">返回</mt-button>
        </div>
        <b slot="right" @click="addNewAddr">新增</b>
      </mt-header>

      <div style="height: 40px"></div>
    </div>

    <div style="width: 90%;margin: auto">
      <div style="width: 100%;text-align: center;margin-top: 10px;">
        <img src="./images/receiveAddress.png" class="passIcon"/>
      </div>

      <div v-for="(item,index) in address" :key="index" class="addressItem" :style="getBack"
           @touchstart="gotouchstart(index)" @touchmove="gotouchmove" @touchend="gotouchend">
        <span style="flex: 4">
          <span @click="currentIndex = -1">{{item.contactName}}</span>
          <span style="margin-left: 5%" @click="currentIndex = -1">{{item.contactPhone}}</span>
          <div class="setDefault" :class="{ showDefault: currentIndex === index  }"
               @click="setDefault(item.contactId)">
            <div class="dialogDiv">
                    设为默认
            </div>
          </div>
          <br>
          <span class="isDefault" :class="{default:item.statu == 1}">默认</span>
          <span @click="currentIndex = -1">{{item.contactAddress}}</span>
        </span>

        <span class="imgSpan">
          <img src="./images/edit.png" style="width: 45%" @click="updateAddress(index)"/>
          <img src="./images/delete.png" style="width: 45%" @click="deleteContact(index)"/>
        </span>
      </div>

      <el-dialog title="新增联系人" :visible.sync="isShowPublish" center width="80%">
        <el-input placeholder="收货人" v-model="contactName1"></el-input>
        <el-input placeholder="联系电话" v-model="contactPhone1"></el-input>
        <el-input placeholder="收货地址" v-model="contactAddress1"></el-input>
        <el-button type="warning" @click="insertContact" style="width: 45%">添加</el-button>
        <el-button @click="isShowPublish = false" type="warning" style="width: 45%">取消</el-button>
      </el-dialog>

      <el-dialog title="修改联系人信息" :visible.sync="dialogFormVisible" center width="80%">
        <el-input placeholder="ID" v-model="contactId" width="80%" v-if="false"></el-input>
        <el-input placeholder="收货人" v-model="contactName" width="80%"></el-input>
        <el-input placeholder="联系电话" v-model="contactPhone" width="80%"></el-input>
        <el-input placeholder="收货地址" v-model="contactAddress" width="80%"></el-input>
        <el-button type="warning" @click="updateContact" style="width: 45%">修改</el-button>
        <el-button @click="dialogFormVisible = false" type="warning" style="width: 45%">取消</el-button>
      </el-dialog>

    </div>
  </div>
</template>

<script>
  import {reqContacts, insertContacts, updateContants, deleteContacts,defaultAddress} from "../../api";
  import {MessageBox} from 'mint-ui'
  import {Dialog, Toast} from 'vant';

  export default {
    data() {
      return {
        dialogFormVisible: false,//弹框修改收货人信息
        isShowPublish: false,//弹框增加联系人
        contactId: '',
        contactName: '',
        contactPhone: '',
        contactAddress: '',
        contactName1: '',
        contactPhone1: '',
        contactAddress1: '',
        userId: '',
        address: '',
        currentIndex:-1,
      }
    },
    methods: {

      //弹出新增联系人的框
      addNewAddr() {
        this.isShowPublish = true;
      },

      updateAddress(index) {
        this.dialogFormVisible = true;//弹框
        this.contactId = parseInt(this.address[index].contactId);
        this.contactName = this.address[index].contactName;
        this.contactPhone = this.address[index].contactPhone;
        this.contactAddress = this.address[index].contactAddress;
      },

      deleteContact(index) {
        this.contactId = parseInt(this.address[index].contactId);
        MessageBox.confirm('确认删除？', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          center: true,
          roundButton: true
        }).then(async (action) => {
          const {contactId} = this;
          let result = await deleteContacts({contactId});
          if (result == true) {
            // MessageBox.alert('删除成功！');
            Toast.success("删除成功");
            this.getContact();
          } else {
            MessageBox.alert('删除出错，请重试！')
          }
        }).catch(() => {
        })
      },

      async getContact() {
        //请求联系人信息
        const {userId} = this;
        let result = await reqContacts({userId});
        if (result.statu == 0) {//请求联系人成功
          this.address = result.data;
          this.$store.dispatch('saveContact', result.data);
          this.$store.dispatch('saveReceive', result.data[0]);
          console.log(result.msg);
        } else {
          // 请求联系人失败
          console.log(result.error);
        }
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
          const {userId, contactName1, contactPhone1, contactAddress1} = this;
          let isInsert = await insertContacts({userId, contactName1, contactPhone1, contactAddress1});
          if (isInsert == 1) {
            Toast.success('添加成功');
            this.getContact();
          } else {
            Toast.fail('添加失败');
            this.isShowPublish = true;
          }
        }).catch(() => {
        })
      },

      updateContact() {
        //编辑联系人信息
        this.dialogFormVisible = false;
        Dialog.confirm({
          title: '删除提示',
          message: '确定执行此操作?'
        }).then(async () => {
          const {contactId, contactName, contactPhone, contactAddress} = this;
          let result = await updateContants({contactId, contactName, contactPhone, contactAddress});
          if (result == 1) {
            Toast.success('修改成功');
            this.getContact();
          } else {
            Toast.fail('修改失败');
            this.dialogFormVisible = true;
          }
        }).catch(() => {
          Toast('已取消修改');
        });
      },

      goBack() {
        this.$router.go('-1');
      },

      //设置默认地址
      async setDefault(contactId){
        let userId = this.$store.state.user.userId;
        let result = await defaultAddress(userId,contactId);
        if(result == 1)
        {
          Toast.success("设置成功");
          this.getContact();
        }else{
          Toast.fail("设置失败");
        }
        this.currentIndex = -1;
      },

      //实现长按
      gotouchstart(index) {
        clearTimeout(this.timeOutEvent);
        this.timeOutEvent = 0;
        this.timeOutEvent = setTimeout(() => {
          if(this.currentIndex < 0)
            this.currentIndex = index;
        }, 600);
      },


      //手释放，如果在700毫秒内就释放，则取消长按事件，此时可以执行onclick应该执行的事件
      gotouchend() {
        clearTimeout(this.timeOutEvent);
        if (this.timeOutEvent != 0) {
          //这里写要执行的内容（尤如onclick事件）

        }
      },

      //如果手指有移动，则取消所有事件，此时说明用户只是要移动而不是长按
      gotouchmove() {
        clearTimeout(this.timeOutEvent);//清除定时器
        this.timeOutEvent = 0;
      },
    },

    mounted() {
      this.userId = this.$store.state.user.userId;
      this.address = Object.keys(this.$store.state.contacts).length ? this.$store.state.contacts : 0;
      if (this.address == 0) {
        this.getContact();
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
  .addressItem {
    width: 90%;
    margin: 7px auto;
    /*background: #E6A23C;*/
    border-radius: 10px;
    padding: 5px 10px;
    color: #5c6b77;
    display: flex;
    flex-direction: row;
    position: relative;
  }

  .main {
    margin: auto;
  }

  .center {
    width: 320px;
  }

  .el-input {
    margin: 5px 0px;
  }

  .el-button {
    margin: 5px;
  }

  .imgSpan {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  .isDefault {
    display: none;
  }

  .default {
    padding: 4px;
    display: inline-block;
    border: 0.5px solid #EA3C25;
    background: #E1335F;
    font-size: 14px;
    line-height: 14px;
    color: white;
    border-radius: 5px;
  }

  .dialogDiv{
    position: relative;
    background-color: #292929;
    width: 50px;
    height: 18px;
    line-height: 18px;
    font-size: 12px;
    border-radius: 10px;
    padding: 5px;

    color: white;
    text-align: center;
    font-family: sans-serif;
  }

  /*通过伪元素设置三角*/
  /*.dialogDiv:after {*/
    /*content: '';*/
    /*position: absolute;*/
    /*width: 0;*/
    /*height: 0;*/
    /*border: 5px solid;*/
    /*border-right-color: #292929;*/

    /*top: 50%;*/
    /*right: 100%;*/
    /*!*margin-left: -15px;*!*/
    /*margin-top: -5px;*/
  /*}*/

  .setDefault{
    position: absolute;
    top: 0px;
    left: 190px;
    display: none;
  }

  .showDefault{
    display: block;
  }

</style>
