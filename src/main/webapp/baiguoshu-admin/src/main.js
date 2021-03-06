// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import VueRouter from 'vue-router'
import routes from './router'
import store from './store/'
import UrlConfig from './assets/js/UrlConfig.js'


import ElementUI from 'element-ui'
import 'element-ui/lib/theme-default/index.css'
import axios from 'axios';
Vue.config.productionTip = false

Vue.prototype.$http = axios;

Vue.prototype.HttpUrl = UrlConfig

Vue.use(ElementUI)
Vue.use(VueRouter)

const router = new VueRouter({
  routes
})


/* eslint-disable no-new */
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
