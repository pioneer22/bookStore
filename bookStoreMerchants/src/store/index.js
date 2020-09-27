import Vue from 'vue'
import Vuex from 'vuex'
import app from './modules/app'
import admin from './modules/admin'
import message from './modules/message'
import getters from './getters'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    admin,
    message
  },
  getters
})

export default store
