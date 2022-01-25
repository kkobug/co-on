<template>
  <div>
    <el-menu class="topnav">
      <el-menu-item class ="navitem" index="1" @click="moveLesson">내 수업</el-menu-item>
      <el-menu-item class ="navitem" index="2" @click="moveClass">우리반보기</el-menu-item>
      <el-menu-item class ="navitem" index="3" @click="moveAttend">출결관리</el-menu-item>
      <button class = "lessonstr">수업 시작</button>
    </el-menu>
  </div>
</template>

<script>
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'


export default {
  name: 'Tchr_Lesson',
  setup() {
    const router = useRouter()
    const store = useStore()
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
    return {state, moveClass, moveAttend, moveLesson}
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
