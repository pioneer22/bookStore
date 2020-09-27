/* eslint-disable */
<template>
  <div :style="setHeight" class="classifyOut">
    <div class="classifyTop">
      <div class="topDiv">
        <div class="topChoose">
          <div class="topLeftButton">
            <el-checkbox :indeterminate="isIndeterminate" v-model="checkAll" @change="handleCheckAllChange">全选</el-checkbox>
          </div>

          <div class="topLeftButton">
            <el-tooltip content="删除选中的分类" placement="top" effect="light">
              <el-button type="danger" icon="el-icon-delete" circle @click="deleteClassify"></el-button>
            </el-tooltip>
          </div>

          <div style="width:25%">
            <el-input
              v-model="input"
              placeholder="请输入内容"
              clearable>
            </el-input>
          </div>

          <div class="topButtonSpace">
            <el-tooltip content="添加分类种类" placement="top" >
              <el-button type="primary" @click="addClassify">添加分类</el-button>
            </el-tooltip>
          </div>

          <div class="topButtonSpace">
            <el-tooltip class="item" effect="dark" placement="top">
              <div slot="content">
                查找需要添加<br>分类的图书
              </div>
              <el-button type="warning" @click="goSearch">查找图书</el-button>
            </el-tooltip>
          </div>

          <div class="topButtonSpace">
            <el-tooltip class="item" effect="dark" placement="top">
              <div slot="content">
                显示所有图书<br>信息供分类
              </div>
              <el-button type="success" @click="reqBookAndClassify" icon="el-icon-refresh"></el-button>
            </el-tooltip>
          </div>

          <div class="topButtonSpace">
            <el-tooltip class="item" effect="dark" placement="top">
              <div slot="content">
                查找勾选到的<br>分类图书
              </div>
              <el-button type="info" @click="searchClassify">分类查找</el-button>
            </el-tooltip>
          </div>

          <div class="topButtonSpace">
            <el-tooltip class="item" effect="dark" placement="top">
              <div slot="content">
                将选中分类添加<br>到选中图书
              </div>
              <el-button type="danger" @click="addMultiClassify">多选添加</el-button>
            </el-tooltip>
          </div>
        </div>

        <div style="margin: 15px 0;"></div>
        <el-checkbox-group v-model="checkedClassify" @change="handleCheckedCitiesChange">
          <el-checkbox v-for="classify in bookClassify" :label="classify" :key="classify.classifiedId" class="topCheckBox">
            {{ classify.classifiedName }}</el-checkbox>
        </el-checkbox-group>
      </div>
    </div>

    <div class="classifyContent">
      <el-table
        ref="multipleTable"
        :data="showTen"
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange">
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column
          label="图书图片"
          width="150">
          <template slot-scope="scope">
            <img :src="scope.row.book.bookImg" class="contentImg">
          </template>
        </el-table-column>
        <el-table-column
          label="图书ID"
          width="150">
          <template slot-scope="scope">
            {{ scope.row.book.bookId }}
          </template>
        </el-table-column>
        <el-table-column
          label="图书名"
          width="250">
          <template slot-scope="scope">
            {{ scope.row.book.bookName }}
          </template>
        </el-table-column>

        <el-table-column
          label="当前分类"
          show-overflow-tooltip>
          <template slot-scope="scope">
            <el-checkbox-group v-model="checkList[getIndex(scope.row)]">
              <el-checkbox v-for="item in scope.row.classify" :label="item" :key="item.classifiedName">
                {{ item.classifiedName }}</el-checkbox>
            </el-checkbox-group>
          </template>
        </el-table-column>

        <el-table-column
          label="操作"
          width="200">
          <template slot-scope="scope">
            <div class="actionButton">
              <el-tooltip class="item" effect="dark" placement="left">
                <div slot="content">为当前图书添加<br>上面选中的分类</div>
                <el-button type="primary" icon="el-icon-plus" @click="addBookClassify(scope.row.book.bookId)"></el-button>
              </el-tooltip>
              <el-tooltip class="item" effect="dark" placement="right">
                <div slot="content">删除当前<br>图书分类</div>
                <el-button
                  type="primary"
                  icon="el-icon-delete"
                  @click="deleteChooseClassify(scope.row.book.bookId,getIndex(scope.row))"></el-button>
              </el-tooltip>
            </div>
          </template>
        </el-table-column>
      </el-table>

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
import { getClassify, insertClassify, deleteClassify, searchBookAndClassify, insertBookClassify, getBookAndClassify,
  deleteChooseBookClassify, insertMultiBookClassify, searchClassify } from '../../../api/book'
/* eslint no-use-before-define: 0 */
export default {
  data() {
    return {
      checkAll: false,
      checkedClassify: [],
      bookClassify: [],
      isIndeterminate: false,
      input: '',
      setHeight: {
        'min-height': ''
      },

      multipleSelection: [],
      bookMess: [],
      itemSize: 0, // 总条数
      pageSize: 4, // 显示条数
      currentPage: 1,
      showBook: [],
      showTen: [],
      // 选中当前图书删除的分类
      checkList: [[], [], [], []],
      val: 1
    }
  },
  created() {
    this._getHeight()
    this.getBookClassify()
    this.reqBookAndClassify()
  },

  methods: {
    handleCheckAllChange(val) {
      this.checkedClassify = val ? this.bookClassify : []
      this.isIndeterminate = false
    },
    handleCheckedCitiesChange(value) {
      const checkedCount = value.length
      this.checkAll = checkedCount === this.bookClassify.length
      this.isIndeterminate = checkedCount > 0 && checkedCount < this.bookClassify.length
    },

    async getBookClassify() {
      const result = await getClassify()
      if (result.statu === 0) {
        this.bookClassify = result.data
      }
    },

    _getHeight() {
      const height = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight
      this.setHeight['min-height'] = height - 50 + 'px'
    },

    async addClassify() {
      const classifiedName = this.input
      const result = await insertClassify(classifiedName)
      if (result === 1) {
        this.success('添加分类成功！')
        this.input = ''
        this.getBookClassify()
      }
    },

    deleteClassify() {
      if (this.checkedClassify.length === 0) {
        this.$message({
          type: 'warning',
          message: '未选中删除分类！'
        })
        return
      }
      this.$confirm('此操作将删除选中分类, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async() => {
        const idArray = []
        this.checkedClassify.forEach(item => {
          idArray.push(item.classifiedId)
        })
        const result = await deleteClassify(idArray)
        if (result === 1) {
          this.success('删除分类成功')
          this.checkAll = false
          this.getBookClassify()
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },

    handleSelectionChange(val) {
      this.multipleSelection = val
    },

    async reqBookAndClassify() { // 一开始请求图书和分类数据
      const result = await getBookAndClassify()
      if (result.statu === 0) {
        this.itemSize = result.data.length
        this.showBook = []
        let temp = []
        result.data.forEach((item, index) => {
          temp.push(item)
          if (temp.length === this.pageSize) {
            this.showBook.push(temp)
            temp = []
          }
          if (index === result.data.length - 1) {
            this.showBook.push(temp)
            temp = []
          }
        })
        if (this.showBook[this.val - 1].length > 0) {
          this.showTen = this.showBook[this.val - 1]
        } else {
          this.showTen = this.showBook[this.val - 2]
        }
      }
    },

    handleCurrentChange(val) { // 显示的内容
      this.showTen = this.showBook[val - 1]
      this.checkList = [[], [], [], []]
      this.val = val
    },

    success(message) {
      this.$message({
        message: message,
        type: 'success'
      })
    },

    async goSearch() { // 查找图书
      const searchKey = this.input
      const result = await searchBookAndClassify(searchKey)
      if (result.statu === 0) {
        this.itemSize = result.data.length
        this.input = ''
        this.showBook = []
        let temp = []
        result.data.forEach((item, index) => {
          temp.push(item)
          if (temp.length === this.pageSize) {
            this.showBook.push(temp)
            temp = []
          }
          if (index === result.data.length - 1) {
            this.showBook.push(temp)
            temp = []
          }
        })
        this.showTen = this.showBook[0]
        this.input = ''
      }
    },

    addBookClassify(bookId) { // 单独添加分类
      this.$confirm('确认添加已选分类?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async() => {
        if (this.checkedClassify.length > 0) {
          const idArray = []
          this.checkedClassify.forEach(item => {
            idArray.push(item.classifiedId)
          })
          const result = await insertBookClassify(bookId, idArray)
          if (result === 1) {
            this.success('本书设置分类成功')
            this.handleCheckAllChange(false)
            this.checkAll = false
            this.reqBookAndClassify()
          }
        } else {
          this.$message({
            message: '没有选中删除分类目标',
            type: 'warning'
          })
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消添加'
        })
      })
    },

    getIndex(row) { // 获取行数下标
      const index = this.showTen.findIndex(e => {
        return e === row
      })
      return index
    },

    deleteChooseClassify(bookId, index) { // 删除当前图书选中
      this.$confirm('删除该书选中分类, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async() => {
        if (this.checkList[index].length === 0) {
          this.$message({
            type: 'warning',
            message: '未勾选删除分类！'
          })
          return
        }
        const classifyArray = []
        this.checkList[index].forEach(item => {
          classifyArray.push(item.classifiedId)
        })
        const result = await deleteChooseBookClassify(bookId, classifyArray)
        if (result === 1) {
          this.success('成功删除选中的分类')
          this.reqBookAndClassify()
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },

    addMultiClassify() { // 对选中图书添加选中分类
      if (this.multipleSelection.length === 0 || this.checkedClassify.length === 0) {
        this.$message({
          type: 'warning',
          message: '请选择相应的图书及分类'
        })
        return
      }
      this.$confirm('确定给选中图书添加选中分类?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async() => {
        const bookArray = []
        const classifiedArray = []
        this.multipleSelection.forEach(item => {
          bookArray.push(item.book.bookId)
        })
        this.checkedClassify.forEach(item => {
          classifiedArray.push(item.classifiedId)
        })
        const result = await insertMultiBookClassify(bookArray, classifiedArray)
        if (result === 1) {
          this.success('分类添加成功！')
          this.reqBookAndClassify()
          this.handleCheckAllChange(false)
          this.checkAll = false
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消添加！'
        })
      })
    },

    async searchClassify() { // 按分类查找图书
      if (this.checkedClassify.length === 1) {
        const searchKey = this.checkedClassify[0].classifiedId
        const result = await searchClassify(searchKey)
        if (result.statu === 0) {
          this.itemSize = result.data.length
          this.input = ''
          this.showBook = []
          let temp = []
          result.data.forEach((item, index) => {
            temp.push(item)
            if (temp.length === this.pageSize) {
              this.showBook.push(temp)
              temp = []
            }
            if (index === result.data.length - 1) {
              this.showBook.push(temp)
              temp = []
            }
          })
          this.showTen = this.showBook[0]
          this.handleCheckAllChange(false)
          this.checkAll = false
        }
      } else {
        this.$message({
          type: 'warning',
          message: '请只勾选一个分类！'
        })
      }
    }
  }
}
</script>

<style type="text/scss" rel="stylesheet/scss" lang="scss" scoped>
.classifyOut{
  background: #D9FFFF;
  padding-top: 15px;
  .classifyTop{
    width: 90%;
    margin: 10px auto;
    display: flex;
    flex-direction: row;
    .topDiv{
      width: 100%;
      .topChoose{
        width: 90%;
        display: flex;
        flex-direction: row;
        align-items: center;
        .topLeftButton{
          width: 6%;
          text-align: center;
        }
        .topButtonSpace{
          margin-left: 10px;
        }
      }
    }
    .topCheckBox{
      margin-bottom: 10px;
    }
  }
  .classifyContent{
    width: 90%;
    margin: auto;
    .contentImg{
      max-width: 80px;
      max-height: 80px;
    }
    .actionButton{
      display: flex;
      flex-direction: row;
    }
    .block{
      text-align: center;
      padding: 5px 0px;
    }
  }
}
</style>

