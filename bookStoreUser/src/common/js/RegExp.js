
//关键字颜色
  function changeColor(resultsList,searchValue){
  resultsList.map((item, index) => {
    if (searchValue && searchValue.length > 0) {
      // 匹配关键字正则
      let replaceReg = new RegExp(searchValue, 'g')
      // 高亮替换v-html值
      let replaceString =
        '<span style="font-weight: bold;color: red">' + searchValue + '</span>'
      resultsList[index] = item.replace(
        replaceReg,
        replaceString
      )
    }
  })
  return resultsList;
}

export {changeColor}
