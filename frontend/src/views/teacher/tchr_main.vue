<template>
  <el-container>
    안녕하세요
  </el-container>
  <div @click="moveLesson()">수업 가기</div>
</template>

<script>

export default {
  name: 'tchr_main',
  data: function() {
    return {
      sche:{},
    }
  },
  methods : {
    moveLesson: function(){
      this.$router.push({name:"Tchr_Lesson"})
    }
  },
}
</script>
