import Vue from 'vue';
import Antd from 'ant-design-vue';
import App from './App';
import 'ant-design-vue/dist/antd.css';
import VueRouter from 'vue-router';
import routers from './config/router'
Vue.config.productionTip = false;

Vue.use(Antd);
Vue.use(VueRouter);

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  scrollBehavior:()=>({y:0}),
  routes: [
    {
      path: '/admin/user',
      component: ()=> import('@/views/user/users.vue')
    }
  ]
})

/* eslint-disable no-new */
new Vue({
  router,
  el: '#app',
  components: { App },
  template: '<App/>',
});
