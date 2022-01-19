<template>
<!-- 로그인 -->
  <div v-if="isLogin">
    <Main @logout3="onLogout"/>
    <!-- <router-view @logout3="onLogout"/> -->
  </div>
<!-- 비로그인 -->
  <div v-else>
    <Login
    @openLoginDialog="onOpenLoginDialog"
    />
    <p>{{isLogin}}</p>
  </div>
<!-- 로그인 -->
  <login-dialog
    :open="loginDialogOpen"
    @closeLoginDialog="onCloseLoginDialog"
    @openSignupDialog="onOpenSignupDialog"
    @openFindidDialog="onOpenFindidDialog"
    @login="onLogin"
  />
<!-- 회원가입 -->
  <signup-dialog
    :open="signupDialogOpen"
    @closeSignupDialog="onCloseSignupDialog"
  />
<!-- ID찾기 -->
  <findid-dialog
    :open="findidDialogOpen"
    @closeFindidDialog="onCloseFindidDialog"
  />
</template>

<script>
import Main from './views/main/main.vue'
import Login from './views/auth/login.vue'
import LoginDialog from './views/auth/components/login-dialog.vue'
import SignupDialog from './views/auth/components/signup-dialog.vue'
import FindidDialog from './views/auth/components/findid-dialog.vue'

import { useStore } from 'vuex'

export default {
  name: 'App',

  components: {
    LoginDialog,
    SignupDialog,
    FindidDialog,
    Login,
    Main,
  },

  data () {
    return {
      // isLogin: false,
      isLogin:false,
      loginDialogOpen: false,
      signupDialogOpen: false,
      findidDialogOpen: false,

    }
  },
  methods: {
    onOpenLoginDialog () {
      this.loginDialogOpen = true
    },
    onCloseLoginDialog () {
      this.loginDialogOpen = false
    },
    onOpenSignupDialog () {
      this.signupDialogOpen = true
    },
    onCloseSignupDialog () {
      this.signupDialogOpen = false
    },
    onOpenFindidDialog () {
      this.findidDialogOpen = true
    },
    onCloseFindidDialog () {
      this.findidDialogOpen = false
    },
    onLogin(){
      this.isLogin = true
      this.$router.push({name:'Main_calendar'})
    },
    onLogout(){
      this.$store.commit('root/logout')
      this.$store.commit('root/removeUser')
      localStorage.removeItem('jwt')
      this.isLogin = false
    },
  },
  created: function(){
    const token = localStorage.getItem('jwt')
    if(token){
      this.isLogin = true
    }
    console.log("isLogin : "+this.isLogin)
  },
}
</script>
