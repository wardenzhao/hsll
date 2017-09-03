// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import VueRouter from 'vue-router'
import routes from './router'
import store from './store/'
import UrlConfig from './assets/js/urlConfig.js'
Vue.config.productionTip = false

Vue.use(VueRouter)

Vue.prototype.HttpUrl = UrlConfig

const router = new VueRouter({
  routes
})
import $ from 'jquery'
  // 发送 ajax 请求

  import { WechatPlugin } from 'vux'
  Vue.use(WechatPlugin)

import {
  AjaxPlugin
}
from 'vux'
Vue.use(AjaxPlugin)

import {
  ToastPlugin
}
from 'vux'
Vue.use(ToastPlugin)







// 添加Fastclick移除移动端点击延迟
import FastClick from 'fastclick'
if ('addEventListener' in document) {
  document.addEventListener('DOMContentLoaded', function() {
    FastClick.attach(document.body);
  }, false);
}

/* eslint-disable no-new */
new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
