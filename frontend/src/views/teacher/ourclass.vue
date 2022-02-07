<template>
  <div>
    <tchr-nav @startvideo="start"></tchr-nav>
    <ModalView style="z-index:10;" v-if ="state.isVisible" @close-modal="closemodal()"></ModalView>
    <el-row :gutter="20" style="margin-top: 2vh">
      <el-col :span="20" style="margin-left: 15vh">
        <el-button class="staddbtn" @click="state.isVisible=true">학생 추가</el-button>
        <datepicker format="yyyy/MM/dd" v-model="state.testDate"></datepicker>
        <button @click="test">test</button>
        <div class="stud">
          <el-row>
            <el-col
            v-for="(o, index) in state.students"
            :key="o"
            :span="4"
            :offset="index > 0 ? 2 : 0"
            >
              <el-card :body-style="{ padding: '5px' }">
                <img
                  src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
                  class="image"
                />
                <div style="padding: 14px">
                  <span>{{o[1]}}</span>
                  <div class="bottom">
                    <el-button type="text" class="button" @click="delstudent(o[1])">삭제</el-button>
                  </div>
                </div>
              </el-card>
            </el-col>
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
import { useRouter } from 'vue-router'
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
    const router = useRouter()
    const store = useStore()
    const state = reactive({
      isVisible :false,
      students:[],
      testDate: new Date(),
      classtitle: computed(() => store.getters['root/getStudyName']),
      classId : computed(() => store.getters['root/getStudyId']),
      id: store.state.root.userid
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
      console.log(String(state.testDate.getUTCFullYear()) + month + day)
    }
    const getStudentList = function(){
      store.dispatch("root/requestGetClassStudyId", {
        studyId :store.state.root.curClassId
      })
      .then(res =>{
        state.students = res.data
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
    onMounted(()=>{
      getStudentList();
    })
    return {state, test, getStudentList, onMounted, delstudent, closemodal}
  },
  methods:{
    // moveClass: function(){
    //   this.$router.push({name:"Tchr_ourClass"})
    // },
    // moveAttend: function(){
    //   this.$router.push({name:"Tchr_attend"})
    // },
    // moveLesson: function(){
    //   this.$router.push({name:"Tchr_Lesson"})
    // },
    start (){
      this.videoDialogOpen= true
      console.log("열림")
    },
    end (){
      this.videoDialogOpen= false
    }
  },
  // created:function(){
  //   this.$store.dispatch('root/requestGetStudent')
  //     .then(result=> {
  //         this.studentlist = result.data
  //         console.log(result.data)

  //       })
  //       .catch(function (err) {
  //         alert(err)
  //       })
  // }
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
}

.image {
  width: 100%;
  display: block;
}
.stud {
  display: flex;
}
.staddbtn {
    width: 10%;
    background-color: #6B3BE3;
    border-radius: 15px;
    text-align: center;
    color: #fff;
    margin-top: 10px;
    margin-bottom: 2vh;
}
</style>
