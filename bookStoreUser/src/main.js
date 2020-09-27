/* eslint-disable no-new */
import Vue from 'vue';
import Vuex from 'vuex'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import MintUI from 'mint-ui'
import 'mint-ui/lib/style.css'
import Vant from 'vant';
import 'vant/lib/index.css';

import App from './App.vue';
import router from './router';
import store from './store'
import VueBetterScroll from 'vue2-better-scroll'
import * as filter from './common/js/filter'

import vcolorpicker from 'vcolorpicker' //颜色选择器
// import selfAdaptation from './common/js/selfAdaptation'//移动端字体自适应
import Viewer from 'v-viewer'
import 'viewerjs/dist/viewer.css'

Vue.use(Vuex)
Vue.use(ElementUI);
Vue.use(MintUI)
Vue.use(Vant);
Vue.use(VueBetterScroll)
Vue.use(vcolorpicker)
Vue.config.productionTip = false
Vue.use(Viewer)
// Vue.use(selfAdaptation)

//全局过滤
Object.keys(filter).forEach(key => {
  Vue.filter(key, filter[key])
})

Viewer.setDefaults({
  Options: { 'inline': true, 'button': true, 'navbar': true, 'title': true, 'toolbar': true, 'tooltip': true, 'movable': true, 'zoomable': true, 'rotatable': true, 'scalable': true, 'transition': true, 'fullscreen': true, 'keyboard': true, 'url': 'data-source' }
})

new Vue({
  el: '#app',
  //需要使用javascript来创建html，这时候我们需要使用render函数
  render: h => h(App),
  router,
  store,
})
