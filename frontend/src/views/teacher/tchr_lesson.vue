<template>
  <div>
    <el-menu class="topnav">
      <el-menu-item class ="navitem" index="1" @click="moveLesson">내 수업</el-menu-item>
      <el-menu-item class ="navitem" index="2" @click="moveClass">우리반보기</el-menu-item>
      <el-menu-item class ="navitem" index="3" @click="moveAttend">출결관리</el-menu-item>
      <button class = "lessonstr">수업 시작</button>
      <button @click ="delClass">수업 삭제</button>
    </el-menu>
    <homework></homework>
    <notice></notice>
  </div>
</template>

<script>
import { reactive } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

import Tchr_notice from './tchr_notice.vue'
import Thcr_homework from './thcr_homework.vue'

export default {
  name: 'Tchr_sLesson',
  components: {
    "homework" : Thcr_homework,
    "notice" : Tchr_notice
  },
  setup() {
    const router = useRouter()
    const store = useStore()
    const state = reactive({
      form: {
        classtitle: store.state.root.curClassName,
        id: store.state.root.userid
      }
    })
    const moveClass = function(){
      router.push({
        name: 'Tchr_ourclass'
      })
    }
    const moveAttend = function(){
      router.push({
        name: 'Tchr_attend'
      })
    }
    const moveLesson = function(){
      router.push({
        name: 'Tchr_Lesson'
      })
    }
    const delClass = function(){
      console.log(state.form.id ,state.form.classtitle)
      store.dispatch('root/requestDeleteClass', {
          studyName: state.form.classtitle,
          tchrId: state.form.id
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
    return {state, moveClass, moveAttend, moveLesson, delClass}
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
</style>
