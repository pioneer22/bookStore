// 状态对象

export default {

  user: {},//用户对象
  bookmess: [],//所有图书信息数组

  contacts: [],//所有联系人信息
  contact: {},//保存下单时的联系人信息
  morning_paper: [],//早报数组

  shopcar: [],//购物车信息数组
  comment: [],//评论数组

  payorder: [],//生成订单前存储的图书
  active: 0,//存放底部标签栏位置

  shopCarNum: '',//保存购物车数量

  classified: [],//保存分类信息
  classifiedData: [],//保存分类数据

  themeColor: '#E6A23C',//保存主题颜色

  selected: '',//保存跳转订单状态的下标

  commentBook:{},//保存要评价的订单信息
  topicMess: [],//保存专题信息

  hadMess: '' // 判断是否有未读信息
}
