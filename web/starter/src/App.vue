<template>


  <div id="app">
    <a-layout id="components-layout-demo-fixed-sider">
      <a-layout-sider :style="{ overflow: 'auto', height: '100vh', position: 'fixed', left: 0 }">
        <div class="logo" />
        <a-menu theme="dark" mode="inline" :default-selected-keys="['dashboard']">
          <a-menu-item key="dashboard" @click="()=> $router.push({path: '/admin/dashboard'})">
            <a-icon type="dashboard" />
            <span  class="nav-text">Dashboard</span>
          </a-menu-item>
          <a-menu-item key="1" @click="()=> $router.push({path: '/admin/user'})">
            <a-icon type="user" />
            <span  class="nav-text">Users</span>
          </a-menu-item>
          <a-menu-item key="2" @click="()=> $router.push({path: '/admin/post'})">
            <a-icon type="file-text" />
            <span class="nav-text">Posts</span>
          </a-menu-item>
          <a-menu-item key="3">
            <a-icon type="setting" />
            <span class="nav-text">Settings</span>
          </a-menu-item>

        </a-menu>
      </a-layout-sider>
      <a-layout :style="{ marginLeft: '200px' }">
        <a-layout-header :style="{ background: '#fff', padding: 0 }" />
        <a-layout-content :style="{ margin: '24px 16px 0', overflow: 'initial' }">
          <div :style="{ padding: '24px', background: '#fff', textAlign: 'center' }">
            <router-view />
          </div>
        </a-layout-content>
        <a-layout-footer :style="{ textAlign: 'center' }">
          Ant Design ©2018 Created by Ant UED
        </a-layout-footer>
      </a-layout>
    </a-layout>
  </div>
</template>

<script>

import Header from "@/components/Header";
import Aside from "@/components/Aside";
import Pagination from "@/components/Pagination";

export default {
  name: 'App',
  components: {
    Header,
    Aside,
    Pagination
  }
}
</script>

<style>
#components-layout-demo-fixed-sider .logo {
  height: 32px;
  background: rgba(255, 255, 255, 0.2);
  margin: 16px;
}
</style>
