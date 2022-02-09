<template>
  <div>
    <tchr-nav @startvideo="start"></tchr-nav>
    <div class="demo-date-picker">
      <div class="block">
        <el-date-picker class = "datepick" v-model="state.studyDate" type="date" placeholder="수업일">
        </el-date-picker>
        <div class = "datepickbtn" @click ="getConfTime">조회</div>
        <div>
          <select class = "timepick">
            <option value="none">===============</option>
            <option v-for="con in state.conferList" @click="getConfSTrecord" :key ="con.id" :value="con.Time"></option>
          </select>
        </div>
      </div>
    </div>
    <el-row>
      <el-col :span="20" style="margin-left: 15vh">
        <el-table :data="state.STTimeRecord" height="250" style="width: 100%">
          <el-table-column prop="date" label="Date" width="180" />
          <el-table-column prop="name" label="Name" width="180" />
          <el-table-column prop="entryTime" label="entryTime" width="180" />
          <el-table-column prop="exitTime" label="exitTime" width="180" />
          <el-table-column prop="status" label="status" />
        </el-table>
      </el-col>
    </el-row>
    <start-video-dialog
      :open="videoDialogOpen"
      @closeVideoDialog="end"
    ></start-video-dialog>
  </div>
</template>
<script>
import { reactive, computed, onMounted } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

import Tchr_nav from './tchr_nav.vue'
import StartVideoDialog from './startvideo-dialog.vue'

export default {
  name: 'attend',
  setup() {
    const router = useRouter()
    const store = useStore()
    const state = reactive({
      studyDate : "",
      chooseConfer: "",
      videoDialogOpen : false,
      conferList: {},
      STTimeRecord:[],
    })
    const getConfTime = function(){
      console.log("현재시간:",state.studyDate)
      // store.dispatch("root/requestConfTime")
      // .then(res =>{
      //   state.conferList = res.data
      //   getConfSTrecord();
      // })
    }
    const getConfSTrecord = function(){
      // store.dispatch("root/requestConfStrecord")
      // .then(res =>{
      //   state.STTimeRecord = res.data
      // })
    }

    return { state, getConfTime }
  },
  components: {
    "tchr-nav" : Tchr_nav,
    StartVideoDialog
  },
  methods:{
    start (){
      state.videoDialogOpen= true
      console.log("열림")
    },
    end (){
      state.videoDialogOpen= false
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
.demo-date-picker {
  display: flex;
  width: 100%;
  padding: 0;
  flex-wrap: wrap;
}
.demo-date-picker .block {
  padding: 30px 0;
  display: flex;
  text-align: center;
  border-right: solid 1px var(--el-border-color-base);
  flex: 1;
}
.demo-date-picker .block:last-child {
  border-right: none;
}

</style>
