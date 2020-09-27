
const isNumber = (value) => { // 判断是不是整数
  var reg = /^\d+$/
  var pattern = new RegExp(reg)
  return pattern.test(value)
}

const isDouble = (value) => { // 判断是不是Double
  var reg = /^\d+\.\d+$/
  var pattern = new RegExp(reg)
  return pattern.test(value)
}

export { isNumber, isDouble }
