// 通过mutations间接更新state的多个方法的对象

import {
  RECEIVE_BOOKMESS,
  RECEIVE_CONTACTS,
  RECEIVE_USER,
  RECEIVE_SHOPCAR,
  RECEIVE_MORNING_PAPER,
  RECEIVE_COMMENT,
  RECEIVE_PAYORDER,
  SAVE_ACTIVE,
  SAVE_CLASSIFIED,
  SAVE_CLASSIFIED_DATA,
  SAVE_THEME_COLOR,
  RECEIVE_CONTACT,
  SAVE_SELECTED,
  COMMENT_BOOK,
  SAVE_TOPIC,
  IS_HADMESS
} from "./mutations-type";

import {
  reqUnReadCount
} from '../api';

export default {

  //获取图书信息
  // async getBookMess({commit,state}){
  //   //发送异步ajax请求
  //   const result = await reqBookMess()
  //   //提交一个mutation
  //   if(result.code===0){
  //     const bookmess = result.data
  //     commit(RECEIVE_BOOKMESS,{bookmess})
  //   }
  // },


  //保存用户信息
  async saveUser({commit},user){
      commit(RECEIVE_USER,{user})
  },

  //保存收货人信息
  async saveContact({commit},contacts){
      commit(RECEIVE_CONTACTS,{contacts})
  },

  //保存早报
  async saveMorning({commit},morning_paper){
      commit(RECEIVE_MORNING_PAPER,{morning_paper})
  },

  //保存图书信息
  async saveBookMess({commit},bookmess){
      commit(RECEIVE_BOOKMESS,{bookmess})
  },

  //保存评论信息
  async saveComment({commit},comment){
    commit(RECEIVE_COMMENT,{comment})
  },

  //保存购物车信息
  async saveShopCar({commit},shopcar){
    commit(RECEIVE_SHOPCAR,{shopcar})
  },

  //保存即将生成订单的图书信息
  async saveWantBuy({commit},payorder){
    commit(RECEIVE_PAYORDER,{payorder})
  },

  //导航栏位置
  changeActive({commit},index){
    commit(SAVE_ACTIVE,{index});
  },

  //保存购物车数量
  saveShopCarNum({commit},num){
    commit('saveShopCarNum',{num});
  },

  //保存图书分类
  saveClassified({commit},classified){
    commit(SAVE_CLASSIFIED,{classified});
  },

  //保存图书分类数据
  saveClassifiedData({commit},classifiedData){
    commit(SAVE_CLASSIFIED_DATA,{classifiedData});
  },

  //保存主题颜色
  saveThemeColor({commit},color){
    commit(SAVE_THEME_COLOR,{color})
  },

  //保存下单的收货人信息
  saveReceive({commit},contact){
    commit(RECEIVE_CONTACT,{contact})
  },

  //保存跳转的订单状态下标
  saveSelected({commit},selected){
    commit(SAVE_SELECTED,{selected})
  },

  //保存要评论的图书订单
  commentBook({commit},orderMess){
    commit(COMMENT_BOOK,{orderMess})
  },

  //保存专题信息
  saveTopic({commit},saveTopic) {
    commit(SAVE_TOPIC,{saveTopic})
  },

  // 判断是否有未读信息
  async getMessCount({commit,state}){
    const userId = state.user.userId
    //发送异步ajax请求
    const result = await reqUnReadCount(userId)
    //提交一个mutation
    commit(IS_HADMESS,{result})
  },
}
