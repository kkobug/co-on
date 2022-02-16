<template>
  <div>
    <tchr-nav @startvideo="start"></tchr-nav>
    <div class="selectBox">
      <el-select v-model="state.value" class="m-2" placeholder="Select" @change="changeHw">
        <el-option
          v-for="(Hw, idx) in state.Hws"
          :key="idx"
          :value="Hw.studentHomeworks"
          :label="Hw.hwTitle"
        />
      </el-select>
    </div>
    <el-tabs v-model="activeName" class="demo-tabs" style="margin: 10px 10vh; padding: 0 3vh; border-radius: 10px; min-height:90vh; background-color: white">
      <!-- <el-tab-pane label="User" name="first">
      </el-tab-pane> -->
      <!-- <el-tab-pane v-for="(nhw, idx) in state.nowHw" :key="idx" :label="Hw.hwTitle"> -->
        <el-scrollbar height="100%">
          <el-row>
            <el-col :span="1"><div class="grid-content bg-head">번호</div></el-col>
            <el-col :span="3"><div class="grid-content bg-head">ID</div></el-col>
            <el-col :span="6"><div class="grid-content bg-head">내용</div></el-col>
            <el-col :span="12"><div class="grid-content bg-head">파일명</div></el-col>
            <el-col :span="2"><div class="grid-content bg-head">점수</div></el-col>
          </el-row>
          <el-row v-for="(item,index) in state.nowHw" :key="index" >
            <el-col :span="1"><div class="grid-content bg-content">{{index+1}}</div></el-col>
            <el-col :span="3"><div class="grid-content bg-content">{{item.stId}}</div></el-col>
            <el-col :span="6"><div class="grid-content bg-content">{{item.stHwcontent}}</div></el-col>
            <el-col :span="12">
              <div class="grid-content bg-content" v-if="item.stHwFile.length">
                <div v-for="file in item.stHwFile" :key="file">
                  <a @click="downStHomeworkFile(file.fileName, file.filePath, file.fileOriginName)" class="filenamehover">💾 {{file.fileOriginName}}</a>&nbsp;
                </div>
              </div>
              <div class="grid-content bg-content" v-else>
                파일이 없습니다
              </div>
            </el-col>
            <el-col :span="1"><el-input v-model="state.score[index]" :placeholder="item.stHwscore"></el-input></el-col>
            <el-col :span="1"><el-button @click="ent(state.score[index], item.stHwscore, item.stHwId, index)">채점</el-button></el-col>
          </el-row>
        </el-scrollbar>
      <!-- </el-tab-pane> -->
    </el-tabs>

    <start-video-dialog
        :open="videoDialogOpen"
        @closeVideoDialog="end"
    ></start-video-dialog>
  </div>
</template>

<script>
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import { ref, unref, reactive, computed, onMounted } from 'vue'

import Tchr_nav from './tchr_nav.vue'
import StartVideoDialog from './startvideo-dialog.vue'
export default {
  name: 'Tchr_HWManage',
  components: {
    "tchr-nav": Tchr_nav,
    StartVideoDialog,
  },
  data(props){
    return {
      videoDialogOpen: false,
    }
  },
  setup() {
    const store = useStore()
    const activeName = ref('')
    const state = reactive({
      value : ref(''),
      score : [],
      Hws : [],
      nowHw : [],
    })
    const getHw = function () {
      store.dispatch('root/requestTchrListHomework',this.userId)
      .then(result =>{
        this.Hws=result.data
        for (let i=0; i<result.data.length; i++) {
          state.score[i] = {}
          for (let j=0; j<result.data[i].studentHomeworks.length; j++) {
            state.score[i][j] = result.data[i].studentHomeworks[j].stHwscore
          }
        }
      })
      .catch(function(err){
        alert(err)
      })
    }
    const downStHomeworkFile = function(fileName, filePath, fileOriginName) {
      const fileurl = `http://localhost:8080/api/v1/studenthomework/download-file?fileName=${fileName}&filePath=${filePath}`
      const anchor = document.createElement('a')
      anchor.href = fileurl
      anchor.download = fileOriginName
      document.body.appendChild(anchor)
      anchor.click()
      document.body.removeChild(anchor)
    }
    const ent = function(changed, origin, id, idx){
      console.log(changed, origin, id)
      if (changed !== null) {
        if (origin === null) {
          origin = 0
        }
        store.dispatch('root/requestPutScore', {
            chgstHwscore: changed,
            stHwId: id,
            stHwscore: origin
        })
        .then(res => {
          state.score[idx]=changed
          state.nowHw[idx].stHwscore = changed
          console.log("채점 완료")
        })
      }
    }
    const changeHw = function(e) {
      state.nowHw = e
      state.score = []
      for (let i=0; i<e.length; i++) {
        state.score.push(e[i].stHwscore)
      }
    }
    const pageload = function() {
      console.log("pageload in!!!!!!!!!!!")
      store.dispatch('root/requestListHomework', {
        studyId: store.state.root.curClassId,
      })
      .then (res => {
        state.Hws = res.data
        console.log("mounted axios success")
        console.log(state.Hws)
      })
      .catch (err => {
        console.log("mounted axios fail")
        console.log(err)
      })
    }
    onMounted (() => {
      pageload()
    })
    return {activeName, getHw, ent, downStHomeworkFile, state, changeHw, pageload}
  },
  methods:{
    start (){this.videoDialogOpen= true},
    end (){this.videoDialogOpen= false},
  },
  created: function(){
    const localvuex=JSON.parse(localStorage.getItem('vuex'))
    this.userId=localvuex["root"]["userid"]
    // this.getHw()
  },

}
</script>

<style scoped>
.el-row {
  margin: 4px;
  /* border: solid;
  border-bottom-width: thick 2px;
  border-color: #3D6657; */

}
.el-row:last-child {
  margin-bottom: 0;
}
.el-col {
  padding: 2px;

  border-radius: 4px;
}
.bg-head {
  background: #83B1C9;
  color: white;
}
.bg-content {
  background: #EBFBFF;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
.scrollbar-demo-item {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50px;
  margin: 10px;
  text-align: center;
  border-radius: 4px;
  background: var(--el-color-primary-light-9);
  color: var(--el-color-primary);
}
.filebar>ul {
    overflow: hidden;
    height: 0;
    position: absolute;
    z-index: 10;
    min-width: 100px;
    background-color: #6B3BE3;
    transition: height;
    transition-duration: 0.5s;
    color: #fff;
    border-radius: 10px;
    margin-top: 5px;
  }
.filebar:hover>ul {
  height: auto;
}
.filenamehover {
  cursor: pointer;
  padding: 10px;
}
.selectBox {
  display: flex;
  justify-content: flex-end;
  margin: 2vh 10vh 2vh 0vh;
}
</style>
