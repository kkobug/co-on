<template>
  <div >
    <tchr-nav @startvideo="start"></tchr-nav>
    <ModalView style="z-index:11;" v-if ="state.isVisible" @close-modal="closemodal()"></ModalView>
    <el-row :gutter="24" style="margin: auto; margin-top: 2vh;">
      <el-col :span="20" style="margin: auto; min-height: 600px;">
        <el-button class="staddbtn" style="border:none; position:absolute; right: 30px; top: 10px;" @click="state.isVisible=true">학생 추가</el-button>
        <div :span="24" class="stud" style="margin-top:65px;">
          <el-row style="width: 100%" :gutter="24">
            <el-col v-for="(o, index) in state.students" :key="o" :span="4" >
              <el-card :body-style="{ padding: '5px' }" style="border-radius:5px; width: 100%; position:relative; padding: 7px; margin-bottom : 2vh;">
                <el-avatar :size="80" fit=cover :src="state.profiles.o[0]" v-if="o[7]"></el-avatar>
                <el-avatar :size="80" fit=cover :src="require('@/assets/images/기본프로필.png')" v-else></el-avatar>
                <div style="padding: 14px">
                  <span>{{o[1]}}</span>
                </div>
                <div>
                  <el-form>
                    <p>마일리지 : {{o[6]}}</p>
                    <el-form-item style="margin:5px;">
                      <el-input v-model="state.mil[index]"></el-input>
                    </el-form-item>
                    <el-button class="staddbtn" style="min-width:80px; border:none;" @click="addmil(o[0], index)">적립/차감</el-button>
                  </el-form>
                </div>
                <el-button type="text" class="button" @click="delstudent(o[0])">X</el-button>
              </el-card>
            </el-col>
            <div v-if="state.isdata" style="width:80%; height:300px; margin:auto; font-size:40px; color:grey; text-align:center; padding-top:20vh;"> 학생을 추가해주세요 </div>
          </el-row>
        </div>
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
import Datepicker from 'vuejs3-datepicker'

import ModalView from "./add_students.vue"
import Tchr_nav from './tchr_nav.vue'
import StartVideoDialog from './startvideo-dialog.vue'

export default {
  name: 'ourClass',
  components: {
    "tchr-nav" : Tchr_nav,
    ModalView,
    Datepicker,
    StartVideoDialog
  },
  data(props){
    return {
      videoDialogOpen:false,
      studentlist:null
    }
  },
  setup() {
    const store = useStore()
    const state = reactive({
      isdata:true,
      isVisible :false,
      students:[],
      mil:{},
      testDate: new Date(),
      classtitle: computed(() => store.getters['root/getStudyName']),
      classId : computed(() => store.getters['root/getStudyId']),
      id: store.state.root.userid,
      profiles: {},
    })
    const test = function () {
      let month = String(state.testDate.getUTCMonth()+1)
      if (month.length === 1) {
        month = '0' + month
      }
      let day = String(state.testDate.getUTCDate())
      if (day.length === 1) {
        day = '0' + day
      }
    }
    const getStudentList = function(){
      store.dispatch("root/requestTchrStlist", store.state.root.curClassId)
      .then(res =>{
        state.students = res.data
        if(res.data.length){
          state.isdata=false
          for (let i=0; i<res.data.length; i++) {
            if (res.data[i][7]) {
              store.dispatch("root/requestStProf", {
                fileName: res.data[i][7],
              })
              .then(resp => {
                state.profiles[res.data[i][0]] = resp
              })
            }
          }
        }else{
          state.isdata=true
        }
      })
    }
    const delstudent = function(studentID){
      store.dispatch("root/requestdelStudyStudent", {
        stId: studentID,
        studyId :store.state.root.curClassId
      })
      .then(res =>{
        getStudentList();
      })
    }
    const closemodal = function(){
      state.isVisible=false
      getStudentList();
    }
    const addmil = function(id, pl){
      store.dispatch('root/requestPlusMil',{
        point: state.mil[pl],
        stId: id,
        studyId: store.state.root.curClassId
      })
      .then(res=>{
        getStudentList();
      })
    }
    onMounted(()=>{
      getStudentList();
    })
    return {state, test, addmil, getStudentList, onMounted, delstudent, closemodal}
  },
  methods:{
    start (){
      this.videoDialogOpen= true
    },
    end (){
      this.videoDialogOpen= false
    }
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
.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.button {
  padding: 0;
  min-height: auto;
  position: absolute;
  right: 5px;
  top: 3px;
  z-index:10;
  color:black;
}

.image {
  width: 100%;
  display: block;
}
.stud {
  display: flex;
}
.staddbtn {
    width: 7.5%;
    background-color: #91847A;
    border-radius: 15px;
    text-align: end !important;
    color: #fff;
    margin-top: 10px;
    margin-bottom: 2vh;
}
</style>
