import Vue from 'vue'

import Cookies from 'js-cookie'

import Element from 'element-ui'
import './assets/styles/element-variables.scss'

import '@/assets/styles/index.scss' // global css
import '@/assets/styles/ruoyi.scss' // ruoyi css
import '@/assets/styles/marker.scss' //marker 端点
import '@/assets/js/iconfont.js'//iconfont

import App from './App'
import store from './store'
import router from './router'
import permission from './directive/permission'

import './assets/icons' // icon
import './permission' // permission control
import { getDicts } from "@/api/system/dict/data";
import { getConfigKey } from "@/api/system/config";
import { parseTime, resetForm, addDateRange, selectDictLabel, selectDictLabels, download, handleTree } from "@/utils/ruoyi";
import Pagination from "@/components/Pagination";
// 自定义表格工具扩展
import RightToolbar from "@/components/RightToolbar"
// 降水预警弹窗
import AllArea from "@/layout/components/BigScreen/allArea"
import SingleArea from "@/layout/components/BigScreen/singleArea"

// 全局方法挂载
Vue.prototype.getDicts = getDicts
Vue.prototype.getConfigKey = getConfigKey
Vue.prototype.parseTime = parseTime
Vue.prototype.resetForm = resetForm
Vue.prototype.addDateRange = addDateRange
Vue.prototype.selectDictLabel = selectDictLabel
Vue.prototype.selectDictLabels = selectDictLabels
Vue.prototype.download = download
Vue.prototype.handleTree = handleTree

//全局变量挂载
// Vue.prototype.mapBoxAccessToken = 'pk.eyJ1IjoidmFnYW50IiwiYSI6ImNraTZzNTQ1MTJudzUyeHFxNzdqcGtiMHAifQ.dkbQf9d1Xpt5Iygu55fJVg';
Vue.prototype.mapBoxAccessToken = 'pk.eyJ1IjoibG9uZ3ZpZXcyMDIxIiwiYSI6ImNrbW9ucGpuazA4bHEycGtuNGE4ZnI2ZmYifQ.G3Xq0FBEOXdGYKkGj1wuew';
// Vue.prototype.mapBoxStyleURI = 'mapbox://styles/vagant/ckla87r8p05bl17ocdxx1xch4';
Vue.prototype.mapBoxStyleURI = 'mapbox://styles/longview2021/ckmonsuhb4e2r17o0h8rhqv7d'

Vue.prototype.weChatLoginUrl = `${process.env.VUE_APP_BASE_API}/weChatLogin`;

Vue.prototype.msgSuccess = function (msg) {
  this.$message({ showClose: true, message: msg, type: "success" });
}

Vue.prototype.msgError = function (msg) {
  this.$message({ showClose: true, message: msg, type: "error" });
}

Vue.prototype.msgInfo = function (msg) {
  this.$message.info(msg);
}

Vue.directive('title', {
  inserted: function (el) {
    document.title = el.dataset.title
  }
})

// 全局组件挂载
Vue.component('Pagination', Pagination)
Vue.component('RightToolbar', RightToolbar)
Vue.component('AllArea', AllArea)
Vue.component('SingleArea', SingleArea)

Vue.use(permission)

/**
 * If you don't want to use mock-server
 * you want to use MockJs for mock api
 * you can execute: mockXHR()
 *
 * Currently MockJs will be used in the production environment,
 * please remove it before going online! ! !
 */

Vue.use(Element, {
  size: Cookies.get('size') || 'medium' // set element-ui default size
})

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
