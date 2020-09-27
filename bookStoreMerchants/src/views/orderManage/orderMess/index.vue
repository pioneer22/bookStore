<template>
  <div>
    <div class="includeDiv">
      <div class="searchDiv">
        <div class="searchInput">
          <el-input v-model="input" placeholder="请输入搜索信息" class="input-with-select" style="width: 400px">
            <el-button slot="append" icon="el-icon-search" @click="goSearch"></el-button>
          </el-input>
        </div>
        <div>
          <el-button type="primary" @click="getOrderMess" icon="el-icon-refresh"></el-button>
        </div>
      </div>

      <div class="orderTag">
        <el-tabs v-model="active" type="card" @tab-click="handleClick">
          <el-tab-pane label="全部订单" name="0"></el-tab-pane>
          <el-tab-pane label="待付款" name="1"></el-tab-pane>
          <el-tab-pane label="待收货" name="2"></el-tab-pane>
          <el-tab-pane label="退换货" name="3"></el-tab-pane>
          <el-tab-pane label="已完成订单" name="4"></el-tab-pane>
        </el-tabs>
      </div>

      <div>
        <el-table
          :data="showOrder"
          :default-sort = "{prop: 'allOrder.orderId', order: 'descending'}"
          border
          style="width: 100%">
          <el-table-column
            prop="allOrder.orderId"
            label="订单号"
            width="150"
            sortable>
          </el-table-column>
          <el-table-column
            label="订单时间"
            prop="allOrder.orderTime"
            width="180"
            sortable>
            <template slot-scope="scope">
              {{ scope.row.allOrder.orderTime | formatDate }}
            </template>
          </el-table-column>
          <el-table-column
            prop="allOrder.userLeaveMess"
            label="留言">
          </el-table-column>
          <el-table-column
            label="收货信息"
            width="280">
            <template slot-scope="scope">
              <div>
                <span style="font-weight: bold">姓名：</span>
                <span style="padding-right: 10px">{{ scope.row.contacts.contactName }}</span>
                <span style="font-weight: bold">电话：</span>
                <span>{{ scope.row.contacts.contactPhone }}</span><br>
                <span style="font-weight: bold">地址：</span>
                <span>{{ scope.row.contacts.contactAddress }}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column
            label="订单总价"
            prop="allOrder.orderAllprice"
            width="150"
            sortable>
            <template slot-scope="scope">
              ￥{{ scope.row.allOrder.orderAllprice }}
            </template>
          </el-table-column>
          <el-table-column
            label="操作"
            width="180">
            <template slot-scope="scope">
              <div style="display: flex;flex-direction: row">
                <el-tag style="margin-right: 5px;cursor: pointer" @click="showItem(scope.row.bookList)">详细订单</el-tag>
                <el-tag
                  v-if="scope.row.allOrder.orderStatus == 0"
                  type="success"
                  @click="toRemind(scope.row.allOrder.orderId,scope.row.allOrder.userId)"
                  style="margin-right: 5px;cursor: pointer">提醒付款</el-tag>
                <el-tag
                  v-if="scope.row.allOrder.orderStatus == 1"
                  type="warning"
                  @click="changeMess(scope.row.contacts,scope.row.allOrder.orderId)"
                  style="margin-right: 5px;cursor: pointer">修改信息</el-tag>
                <el-tag
                  v-if="scope.row.allOrder.orderStatus == 2"
                  type="warning"
                  @click="goSend(scope.row.allOrder.orderId)"
                  style="margin-right: 5px;cursor: pointer">订单发货</el-tag>
                <el-tag
                  v-if="scope.row.allOrder.orderStatus == 4"
                  type="info"
                  @click="deleteChooseOrder(scope.row.allOrder.orderId)"
                  style="margin-right: 5px;cursor: pointer">删除订单</el-tag>
              </div>
            </template>
          </el-table-column>
        </el-table>

        <div style="padding: 5px">
          <el-dialog :visible.sync="dialogTableVisible" title="订单详情">
            <el-table
              :data="showList"
              height="400"
              border
              style="width: 100%">
              <el-table-column
                label="图书图片"
                width="150">
                <template slot-scope="scope">
                  <img :src="scope.row.book.bookImg" style="width: 80px;height: 80px">
                </template>
              </el-table-column>
              <el-table-column
                prop="book.bookName"
                label="图书名">
              </el-table-column>
              <el-table-column
                label="图书价格"
                width="120">
                <template slot-scope="scope">
                  ￥{{ scope.row.book.bookPrice }}
                </template>
              </el-table-column>
              <el-table-column
                prop="count"
                label="图书数量"
                width="120">
              </el-table-column>
            </el-table>
          </el-dialog>
        </div>
      </div>

      <el-dialog :visible.sync="dialogFormVisible" title="收货信息">
        <div>
          <el-form :model="form" style="margin: auto">
            <el-form-item :label-width="formLabelWidth" label="收货人">
              <el-input v-model="form.name" autocomplete="off" style="width: 400px"></el-input>
            </el-form-item>
            <el-form-item :label-width="formLabelWidth" label="联系电话">
              <el-input v-model="form.phone" autocomplete="off" style="width: 400px"></el-input>
            </el-form-item>
            <el-form-item :label-width="formLabelWidth" label="收货地址">
              <el-input v-model="form.address" autocomplete="off" style="width: 400px"></el-input>
            </el-form-item>
          </el-form>
        </div>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="onSubmit">确 定</el-button>
        </div>
      </el-dialog>

      <div class="block">
        <el-pagination
          :current-page="currentPage"
          :page-size="pageSize"
          :total="itemSize"
          background
          layout="total, prev, pager, next, jumper"
          @current-change="handleCurrentChange">
        </el-pagination>
      </div>

    </div>
  </div>
</template>

<script>
import { getOrder, deleteOrder, remindPay, changeContact, searchOrder } from '../../../api/book'

export default {
  data() {
    return {
      active: 0,
      input: '',
      classifyOrder: [],
      showOrder: [],
      allOrder: [],
      waitPay: [],
      waitGet: [],
      replace: [],
      achieve: [],

      itemSize: 0, // 总条数
      pageSize: 6, // 显示条数
      currentPage: 1,

      dialogTableVisible: false,
      showList: [],
      dialogFormVisible: false,
      form: {
        name: '',
        phone: '',
        address: '',
        orderId: ''
      },
      formLabelWidth: '100px'
    }
  },

  created() {
    this.getOrderMess()
  },

  methods: {
    handleClick(tab, event) {

/*      if (this.active === '0') {
        this.turnOrder(this.allOrder)
      } else if (this.active === '1') {
        this.turnOrder(this.waitPay)
      } else if (this.active === '2') {
        this.turnOrder(this.waitGet)
      } else if (this.active === '3') {
        this.turnOrder(this.replace)
      } else if (this.active === '4') {
        this.turnOrder(this.achieve)
      }*/
      switch (this.active) {
        case '0':
          this.turnOrder(this.allOrder)
          break
        case '1':
          this.turnOrder(this.waitPay)
          break
        case '2':
          this.turnOrder(this.waitGet)
          break
        case '3':
          this.turnOrder(this.replace)
          break
        case '4':
          this.turnOrder(this.achieve)
          break
      }
    },

    async getOrderMess() {
      const result = await getOrder()
      if (result.statu === 0) {
        this.itemSize = result.data.allOrder.length
        this.allOrder = result.data.allOrder
        this.waitPay = result.data.waitPay
        this.waitGet = result.data.waitGet
        this.replace = result.data.replace
        this.achieve = result.data.achieve

        this.classifyOrder = []
        this.showOrder = []
        this.active = '0'
        let temp = []
        result.data.allOrder.forEach((item, index) => {
          temp.push(item)
          if (temp.length === this.pageSize) {
            this.classifyOrder.push(temp)
            temp = []
          }
          if (index === result.data.allOrder.length - 1) {
            this.classifyOrder.push(temp)
            temp = []
          }
        })
        this.showOrder = this.classifyOrder[0]
      }
    },

    handleCurrentChange(val) { // 显示的内容
      this.showOrder = this.classifyOrder[val - 1]
    },

    turnOrder(dataOrder) {
      this.itemSize = dataOrder.length
      this.classifyOrder = []
      this.showOrder = []
      let temp = []
      dataOrder.forEach((item, index) => {
        temp.push(item)
        if (temp.length === this.pageSize) {
          this.classifyOrder.push(temp)
          temp = []
        }
        if (index === dataOrder.length - 1) {
          this.classifyOrder.push(temp)
          temp = []
        }
      })
      this.showOrder = this.classifyOrder[0]
    },

    showItem(showList) { // 详细订单信息
      this.showList = showList
      this.dialogTableVisible = true
    },

    deleteChooseOrder(orderId) { // 删除订单
      this.$confirm('确定删除该订单?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async() => {
        const result = await deleteOrder(orderId)
        if (result === 1) {
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
          this.getOrderMess()
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },

    toRemind(orderId, userId) { // 提醒付款
      this.$confirm('确定提醒用户付款?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async() => {
        const result = await remindPay(orderId, userId)
        if (result === 1) {
          this.$message({
            type: 'success',
            message: '已成功提醒!'
          })
        } else if (result === 0) {
          this.$message({
            type: 'warning',
            message: '请勿重复提醒!'
          })
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消提醒'
        })
      })
    },

    changeMess(contacts, orderId) {
      this.dialogFormVisible = true
      this.form.name = contacts.contactName
      this.form.phone = contacts.contactPhone
      this.form.address = contacts.contactAddress
      this.form.orderId = orderId
    },

    onSubmit() {
      this.$confirm('确定修改当前收货信息?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async() => {
        const contactMess = [this.form.name, this.form.phone, this.form.address, this.form.orderId]
        const result = await changeContact(contactMess)
        if (result === 1) {
          this.getOrderMess()
          this.$message({
            type: 'success',
            message: '成功修改收货信息!'
          })
          this.dialogFormVisible = false
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消修改！'
        })
      })
    },

    goSend(orderId) { // 去发货
      this.$prompt('请输入快递单号', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputPattern: /\d{10,14}/,
        inputErrorMessage: '快递单号位数不正确'
      }).then(({ value }) => {
        this.$message({
          type: 'success',
          message: '订单已发货'
        })
      }).catch(() => {
      })
    },

    async goSearch() {
      const searchKey = this.input
      if (searchKey.length === 0) {
        this.$message({
          type: 'warning',
          message: '请输入搜索单号'
        })
      } else {
        const result = await searchOrder(searchKey)
        if (result.statu === 0) {
          this.itemSize = result.data.allOrder.length
          this.allOrder = result.data.allOrder
          this.waitPay = result.data.waitPay
          this.waitGet = result.data.waitGet
          this.replace = result.data.replace
          this.achieve = result.data.achieve

          this.classifyOrder = []
          this.showOrder = []
          let temp = []
          result.data.allOrder.forEach((item, index) => {
            temp.push(item)
            if (temp.length === this.pageSize) {
              this.classifyOrder.push(temp)
              temp = []
            }
            if (index === result.data.allOrder.length - 1) {
              this.classifyOrder.push(temp)
              temp = []
            }
          })
          this.showOrder = this.classifyOrder[0]
        }
      }
    }
  }
}
</script>

<style type="text/scss" rel="stylesheet/scss" lang="scss" scoped>
  .includeDiv{
    width: 90%;
    margin: auto;
    .searchDiv{
      display: flex;
      flex-direction: row;
      padding-top: 15px;
      .searchInput{
        margin-right: 10px;
      }
    }
    .orderTag{
      margin-top: 10px;
    }
    .block{
      text-align: center;
      margin-top: 10px;
    }
  }
</style>
