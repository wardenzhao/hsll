// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import VueRouter from 'vue-router'
import routes from './router'

Vue.config.productionTip = false

Vue.use(VueRouter)

const router = new VueRouter({
  routes
})

// 发送 ajax 请求
import {
  AjaxPlugin
}
from 'vux'
Vue.use(AjaxPlugin)

// 添加Fastclick移除移动端点击延迟
const FastClick = require('fastclick')
FastClick.attach(document.body)

/* eslint-disable no-new */
new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
