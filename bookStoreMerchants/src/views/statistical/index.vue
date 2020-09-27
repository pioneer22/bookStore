<template>
  <div class="dashboard-editor-container">
<!--    <panel-group @handleSetLineChartData="handleSetLineChartData" />
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <line-chart :chart-data="lineChartData" />
    </el-row>-->

    <div style="text-align: center;padding: 10px;font-size: 20px;font-weight: bold">
      <span>图书销量分类扇形图</span>
    </div>
    <div class="chart-wrapper">
      <pie-chart :dataAll="dataShow" key="dataShow"/>
    </div>
   <!-- <div style="text-align: center;padding: 10px;font-size: 20px;font-weight: bold">
      <span>销售额月份占比</span>
    </div>
    <div class="chart-wrapper">
      <pie-chart :data="dataSale"/>
    </div>-->
    <div style="text-align: center;padding: 10px;font-size: 20px;font-weight: bold">
      <span>图书月份销量额扇形图</span>
    </div>
    <div class="chart-wrapper">
      <pie-chart :dataAll="dataSale" key="dataSale"/>
    </div>
<!--    <el-row :gutter="32">
&lt;!&ndash;      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <raddar-chart />
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <bar-chart />
        </div>
      </el-col>&ndash;&gt;
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <pie-chart />
        </div>
      </el-col>
    </el-row>-->

<!--    <el-row :gutter="8">
     &lt;!&ndash; <el-col :xs="{span: 24}" :sm="{span: 24}" :md="{span: 24}" :lg="{span: 12}" :xl="{span: 12}" style="padding-right:8px;margin-bottom:30px;">
        <transaction-table />
      </el-col>
      <el-col :xs="{span: 24}" :sm="{span: 12}" :md="{span: 12}" :lg="{span: 6}" :xl="{span: 6}" style="margin-bottom:30px;">
        <todo-list />
      </el-col>&ndash;&gt;
      <el-col :xs="{span: 24}" :sm="{span: 12}" :md="{span: 12}" :lg="{span: 6}" :xl="{span: 6}" style="margin-bottom:30px;">
        <box-card />
      </el-col>
    </el-row>-->
  </div>
</template>

<script>
/*import PanelGroup from './components/PanelGroup'
import LineChart from './components/LineChart'
import RaddarChart from './components/RaddarChart'
import BarChart from './components/BarChart'
import TransactionTable from './components/TransactionTable'
import TodoList from './components/TodoList'*/
import PieChart from './components/PieChart'
import BoxCard from './components/BoxCard'
import { reqPieData, reqSaleMoney } from '../../api/echarts'

/*const lineChartData = {
  newVisitis: {
    expectedData: [100, 120, 161, 134, 105, 160, 165],
    actualData: [120, 82, 91, 154, 162, 140, 145]
  },
  messages: {
    expectedData: [200, 192, 120, 144, 160, 130, 140],
    actualData: [180, 160, 151, 106, 145, 150, 130]
  },
  purchases: {
    expectedData: [80, 100, 121, 104, 105, 90, 100],
    actualData: [120, 90, 100, 138, 142, 130, 130]
  },
  shoppings: {
    expectedData: [130, 140, 141, 142, 145, 150, 160],
    actualData: [120, 82, 91, 154, 162, 140, 130]
  }
}*/

export default {
  name: 'DashboardAdmin',
  components: {
 /*   PanelGroup,
    LineChart,
    RaddarChart,
    BarChart,
    TransactionTable,
    TodoList,*/

    PieChart,
    BoxCard
  },
  data() {
    return {
      // lineChartData: lineChartData.newVisitis
      dataShow: {
        showData: [],
        pieData: [],
        title: '销售份额'
      },

      dataSale: {
        showData: [],
        pieData: [],
        title: '月份销售金额'
      }
    }
  },

  created() {
    this.getPieData()
    this.getBookSaleMoney()
  },

  methods: {
  /*  handleSetLineChartData(type) {
      this.lineChartData = lineChartData[type]
    }*/
    async getPieData() {
      const result = await reqPieData()
      if (result.statu === 0) {
        this.dataShow.showData = result.data.saleNum
        this.dataShow.pieData = []
        result.data.saleNum.forEach(item => {
          const temp = { value: parseInt(result.data.classifiedName[item]),name: item }
          this.dataShow.pieData.push(temp)
        })
      }
    },

    async getBookSaleMoney() {
      const result = await reqSaleMoney()
      if (result.statu === 0) {
        this.dataSale.pieData = []
        result.data.saleMoney.forEach((item,index) => {
          this.dataSale.showData.push((index+1)+'月')
          const temp = { value: item,name: (index+1)+'月' }
          this.dataSale.pieData.push(temp)
        })
      }
    }
  }
}
</script>

<style type="text/scss" rel="stylesheet/scss" lang="scss" scoped>
.dashboard-editor-container {
  padding: 0px 20px;
  background-color: rgb(240, 242, 245);
  position: relative;

/*  .github-corner {
    position: absolute;
    top: 0px;
    border: 0;
    right: 0;
  }*/

  .chart-wrapper {
    background: #fff;
    padding: 10px 10px 0;
    margin-bottom: 5px;
  }
}
</style>
