// 直接更新state的多个方法的对象
import {
  RECEIVE_BOOKMESS,
  RECEIVE_BOOKTYPES,
  RECEIVE_USER,
  RECEIVE_CONTACTS,
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

export default {
  [RECEIVE_USER](state, {user}) {
    state.user = user;
  },

  [RECEIVE_BOOKTYPES](state, {booktypes}) {
    state.booktypes = booktypes;
  },

  [RECEIVE_CONTACTS](state, {contacts}) {
    state.contacts = contacts;
  },

  [RECEIVE_MORNING_PAPER](state, {morning_paper}) {
    state.morning_paper = morning_paper;
  },

  [RECEIVE_BOOKMESS](state, {bookmess}) {
    state.bookmess = bookmess;
  },

  [RECEIVE_COMMENT](state, {comment}) {
    state.comment = comment;
  },

  [RECEIVE_SHOPCAR](state, {shopcar}) {
    state.shopcar = shopcar;
  },

  [RECEIVE_PAYORDER](state, {payorder}) {
    state.payorder = payorder;
  },

  [SAVE_ACTIVE](state, {index}) {
    state.active = index;
  },

  saveShopCarNum(state, {num}) {
    state.shopCarNum = num;
  },

  [SAVE_CLASSIFIED](state, {classified}) {
    state.classified = classified;
  },

  [SAVE_CLASSIFIED_DATA](state, {classifiedData}) {
    state.classifiedData = classifiedData;
  },

  [SAVE_THEME_COLOR](state, {color}) {
    state.themeColor = color;
  },

  [RECEIVE_CONTACT](state, {contact}) {
    state.contact = contact;
  },

  [SAVE_SELECTED](state, {selected}) {
    state.selected = selected;
  },

  [COMMENT_BOOK](state, {orderMess}) {
    state.commentBook = orderMess;
  },

  [SAVE_TOPIC](state, {saveTopic}) {
    state.topicMess = saveTopic
  },

  [IS_HADMESS](state, {result}) {
    state.hadMess = result
  }
}
