import { reqNoReadCount } from '../../api/notice'
import admin from './admin'

const state = {
  count: 0
}

const mutations = {
  SET_COUNT: (state, { count }) => {
    state.count = count
  }
}

const actions = {
  async saveCount({ commit }) {
    const businessId = admin.state.business.businessId
    const count = await reqNoReadCount( businessId ) // 获取未读信息条数
    commit('SET_COUNT', { count })
  },

}
// export default user

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
