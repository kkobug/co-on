<template>
  <el-menu class="topnav">
    <el-menu-item class ="navitem" index="1" @click="moveLesson()">내 수업</el-menu-item>
    <el-menu-item class ="navitem" index="2" @click="moveClass()">우리반보기</el-menu-item>
    <el-menu-item class ="navitem" index="3" @click="moveAttend()">출결관리</el-menu-item>
    <button class = "lessonstr">수업 시작</button>
  </el-menu>
  <el-container>
    수업 입니다.
  </el-container>
  <homework></homework>
  <notice></notice>

</template>

<script>

import Tchr_notice from './tchr_notice.vue'
import thcr_homework from './thcr_homework.vue'

export default {
  name: 'Lesson',
  data: function() {
    return {
      sche:{},

    }
  },
  components: {
    "homework" : thcr_homework,
    "notice" : Tchr_notice
  },
  methods:{
    moveClass: function(){
      this.$router.push({name:"Tchr_attend"})
    },
    moveAttend: function(){
      this.$router.push({name:"Tchr_ourclass"})
    },
    moveLesson: function(){
      this.$router.push({name:"Tchr_Lesson"})
    }
  }
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
