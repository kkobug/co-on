<template>
  <div>
    <el-menu class="topnav">
      <el-menu-item class ="navitem" index="1" @click="moveLesson()">내 수업</el-menu-item>
      <el-menu-item class ="navitem" index="2" @click="moveClass()">우리반보기</el-menu-item>
      <el-menu-item class ="navitem" index="3" @click="moveAttend()">출결관리</el-menu-item>
      <button class = "lessonstr">수업 시작</button>
    </el-menu>
    <el-table :data="tableData" height="250" style="width: 100%">
      <el-table-column prop="date" label="Date" width="180" />
      <el-table-column prop="name" label="Name" width="180" />
      <el-table-column prop="address" label="Address" />
    </el-table>
  </div>
</template>
<script>
export default {
  name: 'attend',
  data:function(){
    return {
      tableData : [
        {
          date: '2016-05-03',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
        {
          date: '2016-05-02',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
        {
          date: '2016-05-04',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
        {
          date: '2016-05-01',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
        {
          date: '2016-05-08',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
        {
          date: '2016-05-06',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
        {
          date: '2016-05-07',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
      ],
    };
  },
  name: 'attend',
  methods:{
    moveClass: function(){
      this.$router.push({name:"Tchr_ourclass"})
    },
    moveAttend: function(){
      this.$router.push({name:"Tchr_attend"})
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
