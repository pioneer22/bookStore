// vuex最核心的管理对象store

import Vue from 'vue'
import Vuex from 'vuex'
import state from './state'
import mutations from './mutations'
import getters from './getters'
import actions from './actions'

//Vuex持久化存储之vuex-persist
import VuexPersistence from 'vuex-persist'
const vuexLocal = new VuexPersistence({
  storage: window.sessionStorage
})
Vue.use(Vuex)

export default new Vuex.Store({
  state,
  mutations,
  getters,
  actions,
  plugins: [vuexLocal.plugin]
})
