<template>
  <div class="chart-container">
    <div style="padding: 10px;text-align: center">
      <el-select v-model="value" placeholder="请选择">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
    </div>
    <chart height="100%" width="100%" :action = "value"/>
  </div>
</template>

<script>
import Chart from '@/components/Charts/MixChart'

export default {
  name: 'MixChart',
  data() {
    return {
      options: [{
        value: '1',
        label: '图书销售统计'
      }, {
        value: '2',
        label: '图书分类统计'
      }, {
        value: '3',
        label: '图书销量排行'
      }],
      value: '1',
    }
  },
  components: { Chart }
}
</script>

<style scoped>
.chart-container{
  position: relative;
  width: 100%;
  height: calc(100vh - 84px);
}
</style>

