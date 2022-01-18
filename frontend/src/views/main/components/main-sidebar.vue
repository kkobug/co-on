<template>
  <!-- <el-row
    class="main-sidebar"
    :gutter="10"
    :style="{ 'width': width }">
    <div class="hide-on-small">
      <el-menu
        :default-active="String(state.activeIndex)"
        active-text-color="#ffd04b"
        class="el-menu-vertical-demo"
        @select="menuSelect">
        <el-image style="width: 100%" :fit="contain" :src="require(`@/assets/images/프로필테스트.jpeg`)"/>
        <el-menu-item v-for="(item, index) in state.menuItems" :key="index" :index="index.toString()">
          <i v-if="item.icon" :class="['ic', item.icon]"/>
          <span>{{ item.title }}</span>
        </el-menu-item>
      </el-menu>
    </div>
  </el-row> -->
  <el-row style="height: 100%">
    <el-col :span="24" style="heght: 100%">
      <el-menu
        :default-active="String(state.activeIndex)"
        active-text-color="#ffd04b"
        background-color="#4267D6"
        text-color="#fff"
        style="height: 100%"
        class="el-menu-vertical-demo"
        @select="menuSelect">
        <el-container class="profile-card">
          <el-card style="text-align: center; width: 220px; margin: 5px; background-color: #1B2A57; color: white; border-radius: 20px">
            <p>
              <!-- <img style="width: 100px; height: 130px" :src="require(`@/assets/images/프로필테스트.jpeg`)" alt="FAILED"> -->
              <el-avatar :size="80" :fit="cover" :src="require(`@/assets/images/프로필테스트.jpeg`)"></el-avatar>
            </p>
            <div>
              <span><strong>익명의 학생</strong></span>
            </div>
            <el-button color="#626aef" style="margin-top: 5px; background-color: #6B3BE3; color: white; border-color: #6B3BE3">마이페이지</el-button>
          </el-card>
        </el-container>
        <el-menu-item v-for="(item, index) in state.menuItems" :key="index" :index="index.toString()">
          <span>{{ item.title }}</span>
        </el-menu-item>
        <el-menu-item class="mt-auto" style="position: absolute; bottom: 0; width: 100%">
          <span @click="clickLogout">로그아웃</span>
        </el-menu-item>
      </el-menu>
    </el-col>
  </el-row>
</template>
<style>
.profile-card {
  padding: auto;
  justify-content: center;
}
.main-sidebar .el-menu {
  margin-top: 0;
  padding-left: 0;
}
.main-sidebar .hide-on-small {
  height: 100%;
}
.main-sidebar .hide-on-small .el-menu {
  height: 100%;
}
.main-sidebar .el-menu .el-menu-item {
  cursor: pointer;
  border-right: none;
}
.main-sidebar .el-menu .el-menu-item .ic {
  margin-right: 5px;
}
</style>
<script>
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: 'main-header',

  props: {
    width: {
      type: String,
      default: '240px'
    }
  },
  setup() {
    const store = useStore()
    const router = useRouter()

    const state = reactive({
      searchValue: null,
      menuItems: computed(() => {
        const MenuItems = store.getters['root/getMenus']
        let keys = Object.keys(MenuItems)
        let menuArray = []
        for (let i = 0; i < keys.length; ++i) {
          let menuObject = {}
          menuObject.icon = MenuItems[keys[i]].icon
          menuObject.title = MenuItems[keys[i]].name
          menuArray.push(menuObject)
        }
        return menuArray
      }),
      activeIndex: computed(() => store.getters['root/getActiveMenuIndex'])
    })

    if (state.activeIndex === -1) {
      state.activeIndex = 0
      store.commit('root/setMenuActive', 0)
    }

    const menuSelect = function (param) {
      store.commit('root/setMenuActive', param)
      const MenuItems = store.getters['root/getMenus']
      let keys = Object.keys(MenuItems)
      router.push({
        name: keys[param]
      })
    }

    const clickLogout = function () {
      alert('로그아웃 클릭')
      store.commit('root/removeToken')
    }

    return { state, menuSelect, clickLogout }
  }
}
</script>
