// 包含n个接口请求函数的模块
// 函数返回值promise对象
import ajax from './ajax'

//请求登陆
export const reqLogin = ({userid,password}) => ajax('/api/login',{userid,password},'POST');

//获取图书分类列表
export const reqBookTypes =  () => ajax('/api/reqBookTypes')

//获取联系人信息
export const reqContacts = ({userId}) => ajax('/api/mycenter/getContacts',{userId})

//请求修改密码
export const reqChangeMess = ({userId,oldPass,newPass})=>ajax('/api/mycenter/changeMess',{userId,oldPass,newPass},'POST')

//新增联系人
export const insertContacts = ({userId,contactName1,contactPhone1,contactAddress1})=>ajax('/api/mycenter/insertAddress',{userId,contactName1,contactPhone1,contactAddress1},'POST')

//编辑联系人
export const updateContants = ({contactId,contactName,contactPhone,contactAddress})=>ajax('/api/mycenter/updateContact',{contactId,contactName,contactPhone,contactAddress},'POST');

//删除联系人
export const deleteContacts = ({contactId})=>ajax('/api/mycenter/deleteContact',{contactId},'POST');

//获取早报
export const getMorning = ()=>ajax('/api/firstpage/getMorning');

//获取图书信息
export const reqBookMess = ()=> ajax('/api/firstpage/getBookMess');

//获取评论信息
export const getComments = ({bookId})=>ajax('/api/details/getComment',{bookId});

//通过图书id请求相应信息
export const getOneBooks = (bookId)=>ajax('/api/details/getOneBook',{bookId});

//更改头像
export const changeUserImg = (formData)=>ajax('/api/details/changeUserImg',formData,'POST');

//请求分类信息
export const reqClassify = ()=>ajax('/api/classify/getClassify');

//通过分类Id获取图书信息
export const reqClassifyBook = (classifiedId)=>ajax('/api/classify/getClassifyBook',{classifiedId});

//获得热销图书
export const reqSellWell = ()=>ajax('/api/firstpage/getSellWell');

//获取购物车信息
export const reqShopCar = (userId) => ajax('/api/Shopcar/getShopCar',{userId},'POST')

//加入购物车
export const addShopCar = (userId,bookId) =>ajax('/api/Shopcar/addShopCar',{userId,bookId},'POST')

//删除购物车
export const delShopCar = (userId,delBookId) =>ajax('/api/Shopcar/delBook',{userId,delBookId})

//获取收藏
export const getCollect = (userId,bookId) =>ajax('/api/Order/getCollect',{userId,bookId},'POST')

//点击收藏
export const toggleCollect = ({userId,bookId}) =>ajax('/api/Order/toggleCollect',{userId,bookId},'POST')

//实时查询
export const realTimeSearch = ({searchValue}) =>ajax('/api/firstpage/realTimeSearch',{searchValue})

//获取购物车数量
export const getCarNum = (userId)=>ajax('/api/Shopcar/getCarNum',{userId})

//获取订单状态那四个商品信息
export const getOrderStatuNum = (userId) =>ajax('/api/Order/getOrderStatuNum',{userId},'POST')

//获取搜索图书信息
export const getSearchBook = ({searchValue}) =>ajax('/api/firstpage/getSearchBook',{searchValue})

//获取收藏夹信息
export const getAllCollect = (userId) =>ajax('/api/mycenter/getCollect',{userId},'POST')

//获取排行榜
export const getSort = () => ajax('/api/firstpage/getSort')

//请求优惠卷
export const getCoupon = ()=>ajax('/api/firstpage/getCoupon')

//领取优惠卷
export const gainCoupon = (userId,couponId) =>ajax('/api/firstpage/gainCoupon',{userId,couponId},'POST')

//我的优惠卷
export const myCoupon = (userId) =>ajax('/api/mycenter/myCoupon',{userId},'POST')

//请求文本内容
export const getContent = ()=>ajax('/api/mycenter/aboutStore')

//获取信息
export const getMessage = (userId) =>ajax('/api/firstpage/getMessage',{userId},'POST')

//全部标记为已读
export const tagRead = (userId) =>ajax('/api/firstpage/tagRead',{userId},'POST')

//删除消息
export const deleteMessage = (userId,paperId) =>ajax('/api/firstpage/deleteMessage',{userId,paperId},'POST')

//标记单条消息已读
export const tagOneMessage = (userId,paperId) =>ajax('/api/firstpage/tagOneMessage',{userId,paperId},'POST')

//清空消息
export const clearMessage =(userId) =>ajax('/api/firstpage/clearMessage',{userId},'POST')

//获取聊天消息
export const getChat =(userId) =>ajax('/api/mycenter/getChat',{userId},'POST')

//保存聊天内容
export const saveChat = (chat) =>ajax('/api/mycenter/saveChat',chat,'POST')

//获取订单信息
export const getOrder = (userId) =>ajax('/api/mycenter/getOrder',{userId},'POST')

//删除个人订单
export const deleteOneOrder = (orderId) =>ajax('/api/mycenter/deleteOrder',{orderId},'POST')

//设置默认地址
export const defaultAddress = (userId,contactId) =>ajax('/api/mycenter/setDefaultAddress',{userId,contactId},'POST')

//获取个人优惠卷信息
export const personCoupon= (userId) =>ajax('/api/mycenter/personCoupon',{userId},'POST')

//生成订单
export const generateOrder = (orderMess,carBookId) =>ajax('/api/Order/generateOrder',{orderMess,carBookId},'POST')

//支付成功，修改订单状态为待收货
export const setOrderStatu = ({userId,orderId}) =>ajax('/api/Order/setOrderStatu',{userId,orderId},'POST');
export const setOrderStatu1 = (userId,orderId) =>ajax('/api/Order/setOrderStatu1',{userId,orderId},'POST');

//确认收货，修改订单状态为交易成功
export const surePay = (orderId) =>ajax('/api/Order/surePay',{orderId},'POST');

//取消订单
export const cancelOrder = (orderId) =>ajax('/api/Order/cancelOrder',{orderId},'POST');

//提交评价，修改订单状态为已评价
export const submitComment = (orderId,comment,userId) =>ajax('/api/Order/submitComment',{orderId,comment,userId},'POST')

// 获取专题
export const reqTopic = () => ajax('/api/firstpage/reqTopic')

//  判断是否有未读信息
export const reqUnReadCount = (userId) => ajax('/api/firstpage/reqUnReadCount', { userId })
