<template>
<<<<<<< HEAD
  <el-menu mode="horizontal" @select="handleSelect" background-color="#545c64" text-color="#fff" active-text-color="#fff">
    <el-menu-item index="1" class="navitem" style="margin-left: 10px">내 수업</el-menu-item>
    <el-menu-item index="2" class="navitem">우리반보기</el-menu-item>
    <el-menu-item index="3" class="navitem">출결관리</el-menu-item>
    <el-menu-item index="4" class = "lessonstr" @click="startVideoClass">수업 시작</el-menu-item>
  </el-menu>
=======
  <div>
    <el-menu class="topnav">
      <el-menu-item class ="navitem" index="1" @click="moveLesson">내 수업</el-menu-item>
      <el-menu-item class ="navitem" index="2" @click="moveClass">우리반보기</el-menu-item>
      <el-menu-item class ="navitem" index="3" @click="moveAttend">출결관리</el-menu-item>
      <button class = "lessonstr" @click="startVideoClass">화상수업 등록</button>
    </el-menu>
  </div>
>>>>>>> feature/BE/conference
</template>

<script>
import { reactive, computed, ref } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'


export default {
  name: 'Tchr_Lesson',
  setup(props, { emit }) {
    const router = useRouter()
    const store = useStore()
    const activeIndex = ref('1')
    const state = reactive({
      classtitle: computed(() => store.getters['root/getStudyName']),
      id: store.state.root.userid
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
        name: 'Tchr_contents'
      })
    }
<<<<<<< HEAD
    const handleSelect = (key) => {
      if (key === '1') {
        router.push({
        name: 'Tchr_contents'
        })
      } else if (key === '2'){
        router.push({
        name: 'Tchr_ourclass'
        })
      } else {
        router.push({
        name: 'Tchr_attend'
        })
      }
    }
    const startVideoClass = function(){
      emit('startvideo')
    }

    return {state, activeIndex, moveClass, moveAttend, moveLesson, handleSelect, startVideoClass}
=======
    const startVideoClass = function(){
      emit('startvideo')
    }
    return {state, moveClass, moveAttend, moveLesson, startVideoClass}
>>>>>>> feature/BE/conference
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
  /* position: relative; */
  color: #fff;
  display: flex;
  list-style:none;
  width: 100%;
}
.navitem{
  padding: 10px;
  border-bottom: solid 2px white;
  margin-right: 10px;
  height: 60px;
}
.navitem:hover{
  color: yellow;
  border-bottom: solid 2px yellow;
}
.lessonstr {
  right: 10px;
  position: absolute;
}
.sub_btn{
  padding: 5px;
  margin: 10px;
  background-color: red;
  float: right;
}
</style>
