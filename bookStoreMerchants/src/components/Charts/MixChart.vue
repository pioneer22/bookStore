<template>
  <div :id="id" :class="className" :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
import resize from './mixins/resize'
import { reqShowData } from '../../api/echarts'

export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    id: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '200px'
    },
    height: {
      type: String,
      default: '200px'
    },
    action: { // 接收操作
      type: String,
      default: '1'
    }
  },
  data() {
    return {
      chart: null,
      saleNum: [],
      saleMoney: [],
      classified: [], // 分类名
      classifiedNum: [], // 图书分类数量
      showNum: [],
      showLine: [],
      xData: [], // 存放过去一年
      showData: [],
      bookName: [],
      bookSale: [],
      bookNum: [],
      title: '图书销量',
      barName: '销量(本)',
      lineName: '销售额(元)'
    }
  },
  watch: {
    action(newValue, oldValue) {
      switch (newValue) {
        case '1':
          this.showNum = this.saleNum
          this.showLine = this.saleMoney
          this.showData = this.xData
          this.title = '图书销量'
          this.barName = '销量(本)'
          this.lineName = '销售额(元)'
          this.initChart()
          break
        case '2':
          this.showNum = this.classifiedNum
          this.showLine = this.classifiedNum
          this.showData = this.classified
          this.title = '图书分类'
          this.barName = '图书数量'
          this.lineName = '图书数量'
          this.initChart()
          break
        case '3':
          this.showNum = this.bookSale
          this.showLine = this.bookNum
          this.showData = this.bookName
          this.title = '销量排行'
          this.barName = '图书销量'
          this.lineName = '图书库存'
          this.initChart()
          break
      }
    }
  },
  mounted() {
    this.getShowData()
    // this.initChart()
    this.getMonth()
  },

  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },

  methods: {
    async getShowData() {
      const result = await reqShowData()
      if (result.statu === 0) {
        this.showNum = result.data.saleNum
        this.showLine = result.data.saleMoney
        this.saleMoney = result.data.saleMoney
        this.saleNum = result.data.saleNum
        this.classified = result.data.classified
        this.classifiedNum = result.data.classifiedNum
        this.bookName = result.data.bookName
        this.bookSale = result.data.bookSale
        this.bookNum = result.data.bookNum
        this.initChart()
      }
    },

    getMonth() {
      this.xData = (function() {
        const data = []
        const date = new Date()
        const month = date.getMonth() + 1
        for (let i = 0; i < 12; i++) {
          month + i > 12 ? data.push((month + i - 12) + '月') : data.push(month + i + '月')
        }
        return data
      }())
      this.showData = this.xData
    },

    initChart() {
      this.chart = echarts.init(document.getElementById(this.id))
      this.chart.setOption({
        backgroundColor: '#344b58',
        title: {
          text: this.title,
          x: '20',
          top: '20',
          textStyle: {
            color: '#fff',
            fontSize: '22'
          },
          subtextStyle: {
            color: '#90979c',
            fontSize: '16'
          }
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            textStyle: {
              color: '#fff'
            }
          }
        },
        grid: {
          left: '5%',
          right: '5%',
          borderWidth: 0,
          top: 150,
          bottom: 95,
          textStyle: {
            color: '#fff'
          }
        },
        legend: {
          x: '5%',
          top: '10%',
          textStyle: {
            color: '#90979c'
          },
          data: [this.barName, this.lineName]
        },
        calculable: true,
        xAxis: [{
          type: 'category',
          axisLine: {
            lineStyle: {
              color: '#90979c'
            }
          },
          splitLine: {
            show: false
          },
          axisTick: {
            show: false
          },
          splitArea: {
            show: false
          },
          axisLabel: {
            interval: 0,
            rotate: 30
          },
          data: this.showData
        }],
        yAxis: [{
          type: 'value',
          splitLine: {
            show: false
          },
          axisLine: {
            lineStyle: {
              color: '#90979c'
            }
          },
          axisTick: {
            show: false
          },
          axisLabel: {
            interval: 0
          },
          splitArea: {
            show: false
          }
        }],
        dataZoom: [{
          show: true,
          height: 30,
          xAxisIndex: [
            0
          ],
          bottom: 30,
          start: 10,
          end: 80,
          // handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
          handleSize: '110%',
          handleStyle: {
            color: '#d3dee5'
          },
          textStyle: {
            color: '#fff' },
          borderColor: '#90979c'

        }, {
          type: 'inside',
          show: true,
          height: 15,
          start: 1,
          end: 35
        }],
        series: [{
          name: this.barName,
          type: 'bar',
          stack: 'total',
          barMaxWidth: 35,
          barGap: '10%',
          itemStyle: {
            normal: {
              color: 'rgba(255,144,128,1)',
              label: {
                show: true,
                textStyle: {
                  color: '#fff'
                },
                position: 'insideTop',
                formatter(p) {
                  return p.value > 0 ? p.value : ''
                }
              }
            }
          },
          data: this.showNum
        },
        {
          name: this.lineName,
          type: 'line',
          stack: 'total',
          symbolSize: 10,
          symbol: 'circle',
          itemStyle: {
            normal: {
              color: 'rgba(252,230,48,1)',
              barBorderRadius: 0,
              label: {
                show: true,
                position: 'top',
                formatter(p) {
                  return p.value > 0 ? p.value : ''
                }
              }
            }
          },
          data: this.showLine
        }
        ]
      }, true) // true防止切换时数据合并，造成开始时的错觉
    }
  }
}
</script>
