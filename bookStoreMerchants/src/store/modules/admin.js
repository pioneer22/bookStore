const state = {
  business: {}
}

const mutations = {
  SET_BUSINESS: (state, { business }) => {
    state.business = business
  }
}

const actions = {
  saveBusiness({ commit }, business) {
    commit('SET_BUSINESS', { business })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}




