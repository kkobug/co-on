<template>
  <tchr-nav @startvideo="start"></tchr-nav>
  <h1>
    과제 관리 페이지
  </h1>

  <start-video-dialog
      :open="videoDialogOpen"
      @closeVideoDialog="end"
  ></start-video-dialog>
</template>

<script>
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

import Tchr_nav from './tchr_nav.vue'
import StartVideoDialog from './startvideo-dialog.vue'

export default {
  name: 'Tchr_HWManage',
  components: {
    "tchr-nav": Tchr_nav,
    StartVideoDialog
  },
  data(props){
    return {
      videoDialogOpen: false,
    }
  },
  setup() {

  },
  methods:{
    start (){
      this.videoDialogOpen= true
      console.log("열림")
    },
    end (){
      this.videoDialogOpen= false
    }
  }

}
</script>

<style>

</style>
