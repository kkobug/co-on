<template>
  <div>
    <tchr-nav @startvideo="start"></tchr-nav>
    <ModalView style="z-index:10;" v-if ="state.isVisible" @close-modal="state.isVisible = false"></ModalView>
    <button @click="state.isVisible=true">학생 추가</button>
    <div class="stud">
      <el-row v-if="studentlist">
        <el-col
        v-for="st in studentlist"
        :key="st"
        :span="8"
        >
          <el-card :body-style="{ padding: '0px' }">
            <img
              src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
              class="image"
            />
            <div style="padding: 14px">
              <span>{{st[0]}}</span>
              <div class="bottom">
                <el-button type="text" class="button">{{st[1]}}</el-button>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
    <start-video-dialog
      :open="videoDialogOpen"
      @closeVideoDialog="end"
    ></start-video-dialog>
  </div>
</template>
<script>
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

import ModalView from "./add_students.vue"
import Tchr_nav from './tchr_nav.vue'
import StartVideoDialog from './startvideo-dialog.vue'

export default {
  name: 'ourClass',
  components: {
    "tchr-nav" : Tchr_nav,
    ModalView,
    StartVideoDialog
  },
  data(){
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
      students:[]
    })

    return {state}
  },
  methods:{
    moveClass: function(){
      this.$router.push({name:"Tchr_ourClass"})
    },
    moveAttend: function(){
      this.$router.push({name:"Tchr_attend"})
    },
    moveLesson: function(){
      this.$router.push({name:"Tchr_Lesson"})
    },
    start (){
      this.videoDialogOpen= true
    },
    end (){
      this.videoDialogOpen= false
    }
  },
  created:function(){
    this.$store.dispatch('root/requestGetStudent')
      .then(result=> {
          this.studentlist = result.data
          console.log(result.data)

        })
        .catch(function (err) {
          alert(err)
        })
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
</style>
