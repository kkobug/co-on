<template>
  <div>
    <tchr-nav @startvideo="start"></tchr-nav>
    <div class="demo-date-picker">
      <div class="block">
        <el-date-picker
        class = "datepick"
        v-model="state.studyDate"
        type="String"
        placeholder="수업일"
        format="YYYY-MM-DD"
        value-format="YYYY-MM-DD"
        >
        </el-date-picker>
        <div class = "datepickbtn" @click ="getConfAttData">조회</div>
        <div>
          <el-select v-model="value" class="m-2" placeholder="Select" @change="getConfSTrecord">
            <el-option
              v-for="con in state.STTimeRecord"
              :key="con.confId"
              :label="con.confStart"
              :value="con"
            >
            </el-option>
          </el-select>
        </div>
      </div>
    </div>
    <table border="1">
      <th>학생이름</th>
      <th>입장시간</th>
      <th>퇴장시간</th>
      <th>출결여부</th>
      <tr v-for="tr in state.conferList.attendances" :key ="tr.confId">
        <td>{{tr.stId}}</td>
        <td><p v-for="(Rin, index) in makereco(tr).start" :key="index">{{Rin}}</p></td>
        <td><p v-for="(Rin, index) in makereco(tr).end" :key="index">{{Rin}}</p></td>
        <td>{{tr.attPass}}</td>
      </tr>
    </table>
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
      attreco:[],
      STTimeRecord:[
  {
    attendances: [
      {
        attId: 0,
        attPass: 0,
        attendanceRecords: [
          {
            attId: 0,
            confId: 0,
            recId: 0,
            recIn: "2022-02-08T07:11:07.272Z",
            recOut: "2022-02-08T07:11:07.272Z",
            stId: "string"
          }
        ],
        confId: 0,
        stId: "string"
      }
    ],
    confAtt: 0,
    confDes: "2022-02-08T07:11:07.272Z",
    confEnd: "2022-02-08T07:11:07.272Z",
    confId: 0,
    confInit: "2022-02-08T07:11:07.272Z",
    confStart: "2022-02-08T07:11:07.272Z",
    confTitle: "string",
    studyId: 0,
    tchrId: "string"
  }
],
    })
    const getConfAttData = function(){
      console.log("현재시간:", state.studyDate)
      store.dispatch("root/requestConfAttData",{
        studyId : parseInt(store.state.root.curClassId),
        tchrId : store.state.root.userid,
        targetDate: String(state.studyDate)
      })
      .then(res =>{
        state.STTimeRecord = res.data


      })
    }
    const getConfSTrecord = function(conf){
      state.conferList = conf
      console.log("aaaa", conf)
    }
    const makereco = function(data){
      var l_list = []
      var r_list = []
      for (var i =0; i<data.attendanceRecords.length; i++){
        if (data.attendanceRecords[i].recIn){
          l_list.push(data.attendanceRecords[i].recIn)
        } else {
          l_list.push("입장시간없음")
        }
        if (data.attendanceRecords[i].recOut){
          r_list.push(data.attendanceRecords[i].recOut)
        } else {
          r_list.push("퇴장시간없음")
        }
      }
      console.log("make", data, l_list, r_list)
      const tlist = {start : l_list, end : r_list}
      return tlist

    }

    return { state, getConfAttData, getConfSTrecord, makereco }
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
