<template>
  <pro-layout
    :title="title"
    :menus="newMenus[appsSelect[0]]"
    :collapsed="collapsed"
    :mediaQuery="query"
    :isMobile="isMobile"
    :handleMediaQuery="handleMediaQuery"
    :handleCollapse="handleCollapse"
    :logo="logoRender"
    :i18nRender="i18nRender"
    v-bind="settings"
  >
    <ads v-if="isProPreviewSite && !collapsed" />

    <!-- layout content -->
    <a-layout-content :style="{ height: '100%', margin: '0 0 40px 0', paddingTop: fixedHeader ? '64px' : '0' }">
      <multi-tab v-if="multiTab"></multi-tab>
      <transition name="page-transition">
      </transition>
    </a-layout-content>

    <!-- 页面头-->
    <template v-slot:rightContentRender>
      <a-menu class="topNav" v-model="appsSelect" mode="horizontal" style="display: flex;">
        <a-menu-item @click="clickNav(item.id)" class="topNav-text" v-for="item in apps" :key="item.id">
          <a-icon :type="item.icon" />
          {{ item.name }}
        </a-menu-item>
      </a-menu>
      <right-content :top-menu="settings.layout === 'topmenu'" :is-mobile="isMobile" :theme="settings.theme" />
    </template>

    <!-- 页面内容-->
    <router-view />

    <!-- 页面脚-->
    <template v-slot:footerRender>
      <global-footer />
    </template>

    <global-web-socket uri="/websocket/user" v-if="enableWebSocket" />

  </pro-layout>
</template>

<script>
import MultiTab from '@/components/MultiTab'
import { SettingDrawer, updateTheme } from '@ant-design-vue/pro-layout'
import { i18nRender } from '@/locales'
import { mapState } from 'vuex'
import { SIDEBAR_TYPE, TOGGLE_MOBILE_TYPE } from '@/store/mutation-types'

import defaultSettings from '@/config/defaultSettings'
import RightContent from '@/components/GlobalHeader/RightContent'
import GlobalFooter from '@/components/GlobalFooter'
import Ads from '@/components/Other/CarbonAds'
import LogoSvg from '../assets/logo.svg?inline'
import GlobalWebSocket from '@/components/websocket/GlobalWebSocket'

export default {
  name: 'BasicLayout',
  components: {
    GlobalWebSocket,
    SettingDrawer,
    RightContent,
    GlobalFooter,
    Ads,
    MultiTab
  },
  data () {
    return {
      appsSelect: ['1'],
      // preview.pro.antdv.com only use.
      isProPreviewSite: process.env.VUE_APP_PREVIEW === 'true' && process.env.NODE_ENV !== 'development',
      // end
      multiTab: defaultSettings.multiTab,
      fixedHeader: defaultSettings.fixedHeader, // sticky header

      // base
      menus: [],
      newMenus:{},
      // 侧栏收起状态
      collapsed: false,
      title: defaultSettings.title,
      settings: {
        // 布局类型
        layout: defaultSettings.layout, // 'sidemenu', 'topmenu'
        // 定宽: true / 流式: false
        contentWidth: defaultSettings.layout === 'sidemenu' ? false : defaultSettings.contentWidth === 'Fixed',
        // 主题 'dark' | 'light'
        theme: defaultSettings.navTheme,
        // 主色调
        primaryColor: defaultSettings.primaryColor,
        fixedHeader: defaultSettings.fixedHeader,
        fixSiderbar: defaultSettings.fixSiderbar,
        colorWeak: defaultSettings.colorWeak,

        hideHintAlert: false,
        hideCopyButton: false
      },
      // 媒体查询
      query: {},

      // 是否手机模式
      isMobile: false,

      // 开启websocket
      enableWebSocket: false
    }
  },
  computed: {
    ...mapState({
      // 动态主路由
      mainMenu: state => state.permission.addRouters,
      apps: state => state.permission.apps
    })
  },
  created () {
    this.appsSelect[0] =  this.apps[0].id
    const routes = this.mainMenu.find(item => item.path === '/')
    this.menus = (routes && routes.children) || []
    for(let k in this.apps){
      this.newMenus[this.apps[k].id] = (routes && routes.children.filter(item => item.application === this.apps[k].id))
    }
    // 处理侧栏收起状态
    this.$watch('collapsed', () => {
      this.$store.commit(SIDEBAR_TYPE, this.collapsed)
    })
    this.$watch('isMobile', () => {
      this.$store.commit(TOGGLE_MOBILE_TYPE, this.isMobile)
    })
  },
  mounted () {
    const userAgent = navigator.userAgent
    if (userAgent.indexOf('Edge') > -1) {
      this.$nextTick(() => {
        this.collapsed = !this.collapsed
        setTimeout(() => {
          this.collapsed = !this.collapsed
        }, 16)
      })
    }

    // first update color
    updateTheme(this.settings.primaryColor)
  },
  methods: {
    i18nRender,
    clickNav (item) {
      this.appsSelect[0] = item 
    },
    handleMediaQuery (val) {
      this.query = val
      if (this.isMobile && !val['screen-xs']) {
        this.isMobile = false
        return
      }
      if (!this.isMobile && val['screen-xs']) {
        this.isMobile = true
        this.collapsed = false
        this.settings.contentWidth = false
        // this.settings.fixSiderbar = false
      }
    },
    handleCollapse (val) {
      this.collapsed = val
    },
    handleSettingChange ({ type, value }) {
      type && (this.settings[type] = value)
      switch (type) {
        case 'contentWidth':
          this.settings[type] = value === 'Fixed'
          break
        case 'layout':
          if (value === 'sidemenu') {
            this.settings.contentWidth = false
          } else {
            this.settings.fixSiderbar = false
            this.settings.contentWidth = true
          }
          break
      }
    },
    logoRender () {
      return <LogoSvg />
    }
  }
}
</script>

<style lang="less">
@import "./BasicLayout.less";
</style>
