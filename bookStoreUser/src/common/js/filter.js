
import moment from 'moment';
import Vue from 'vue'

//时间格式化
  let formatData = time => {
    if (time == '' || !time){
      return '——'
    }else{
      var date = moment(time).format('YYYY-MM-DD HH:mm:ss');
      return date;
    }
}

Vue.filter('formatTime', (time,formatString = 'YYYY-MM-DD HH:mm:ss')=>{
  if (time == '' || !time){
    return '——'
  }else{
    var date = moment(time).format(formatString);
    return date;
  }
})

export {formatData}
