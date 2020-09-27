// 函数返回值promise对象
import ajax from './ajax'

// 获取图书信息
export const reqBook = () => ajax('/api/book/reqBook')

// 搜索图书
export const searchBook = (searchKey) => ajax('/api/book/searchBook', { searchKey })

// 修改图书信息
export const editBookMess = (searchMess) => ajax('/api/book/editBookMess', { searchMess }, 'POST')

// 删除图书
export const deleteBook = (bookId) => ajax('/api/book/deleteBook', { bookId })

// 清空图书
export const deleteAllBook = () => ajax('/api/book/deleteAllBook')

// 图书上架
export const insertBook = (insertMess) => ajax('/api/book/insertBook', { insertMess }, 'POST')

// 获取分类
export const getClassify = () => ajax('/api/book/getClassify')

// 添加分类
export const insertClassify = (classifiedName) => ajax('/api/book/insertClassify', { classifiedName }, 'POST')

// 删除分类
export const deleteClassify = (idArray) => ajax('/api/book/deleteClassify', { idArray }, 'POST')

// 单独操作添加分类信息
export const insertBookClassify = (bookId, idArray) => ajax('/api/book/insertBookClassify', { bookId, idArray }, 'POST')

// 图书分类页面获取图书和分类信息
export const getBookAndClassify = () => ajax('/api/book/getBookAndClassify')

// 查找要修改的图书和分类信息
export const searchBookAndClassify = (searchKey) => ajax('/api/book/searchBookAndClassify', { searchKey })

// 删除当前图书选中的分类
export const deleteChooseBookClassify = (bookId, classifyArray) => ajax('/api/book/deleteChooseBookClassify', { bookId, classifyArray }, 'POST')

// 为选中图书添加选中分类
export const insertMultiBookClassify = (bookArray, classifyArray) => ajax('/api/book/insertMultiBookClassify', { bookArray, classifyArray }, 'POST')

// 按分类查找图书
export const searchClassify = (searchKey) => ajax('/api/book/searchClassify', { searchKey })

// 获取订单信息
export const getOrder = () => ajax('/api/order/getOrder')

// 删除订单
export const deleteOrder = (orderId) => ajax('/api/order/deleteOrder', { orderId })

// 提醒付款
export const remindPay = (orderId, userId) => ajax('/api/order/remindPay', { orderId, userId }, 'POST')

// 修改订单收货人信息
export const changeContact = (contactMess) => ajax('/api/order/changeContact', { contactMess }, 'POST')

// 查找订单信息
export const searchOrder = (searchKey) => ajax('/api/order/searchOrder', { searchKey })
