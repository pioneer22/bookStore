import moment from 'moment'
import Vue from 'vue'

// 时间格式化
Vue.filter('formatDate', (time, formatString = 'YYYY-MM-DD HH:mm:ss') => {
  if (time === '' || !time) {
    return '——'
  } else {
    var date = moment(time).format(formatString)
    return date
  }
})

const formatTime = (time, formatString = 'YYYY-MM-DD HH:mm:ss') => {
  if (time === '' || !time) {
    return '——'
  } else {
    var date = moment(time).format(formatString)
    return date
  }
}

export { formatTime }
