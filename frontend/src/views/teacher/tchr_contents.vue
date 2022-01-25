<template>
  <div>
    <tchr-nav></tchr-nav>
    <h1 style="text-align:left">{{ state.classtitle }} 반의 수업</h1>
    <homework></homework>
    <notice></notice>
    <button class="sub_btn" @click ="delClass">수업 삭제</button>
  </div>
</template>
<script>
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

import Tchr_notice from './tchr_notice.vue'
import Tchr_homework from './thcr_homework.vue'
import Tchr_nav from './tchr_nav.vue'

export default {
  name: 'Tchr_contents',
  components: {
    "homework" : Tchr_homework,
    "notice" : Tchr_notice,
    "tchr-nav" : Tchr_nav,
  },
  setup() {
    const router = useRouter()
    const store = useStore()
    const state = reactive({
      classtitle: computed(() => store.getters['root/getStudyName']),
      id: store.state.root.userid
    })
    const delClass = function(){
      console.log(state.id ,state.classtitle)
      store.dispatch('root/requestDeleteClass', {
          studyName: state.classtitle,
          tchrId: state.id
          })
      .then(function (result) {
        store.dispatch('root/requestGetTchrClass', {
            tchrId: store.state.root.userid})
        .then(res =>{
          store.state.root.classList = res.data
        })
        router.push({
          name: 'Main_calendar',
        })
      })
      .catch(function (err) {
        alert(err)
      })
    }
    return {state, delClass}
  },

}
</script>
<style scoped>
*, html, body {
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}
.topnav{
  background-color: #545c64;
  position: relative;
  color: #fff;
  display: flex;
  list-style:none;
  width: 100%;
}
.navitem{
  padding: 20px;
  border-bottom: solid 2px white;
  margin-right: 10px;
}
.navitem:hover{
  color: yellow;
  border-bottom: solid 2px yellow;
}
.lessonstr {
  right: 0;
  margin: 20px;
  position: absolute;
}
.sub_btn{
  padding: 5px;
  margin: 20px;
  background-color: red;
  float: right;
}
</style>
