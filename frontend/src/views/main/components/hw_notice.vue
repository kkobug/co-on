<template>
  <div style="overflow: hidden; margin: 0vh 15vw">
    <h1 style="padding: 1.5%; font-size:30px; margin-left:2vw; text-align: start;">공지사항</h1>
    <el-row :gutter="24" style="margin : 5px auto;" class ="el-item el-item-bgcolor1">
      <el-col :span="4" class ="li-title li-item">교과명</el-col>
      <el-col :span="14" class ="li-lesson li-item">
        <div>
          <span>공지</span>&nbsp;
          <el-tooltip
            content="제목을 클릭해 공지를 확인하세요"
            raw-content
          >
            <span><font-awesome-icon icon="question-circle" /></span>
          </el-tooltip>
        </div>
      </el-col>
      <el-col :span="2" class ="li-item filebar">파일</el-col>
      <el-col :span="4" class ="li-time li-item">공지일</el-col>
    </el-row>
    <el-scrollbar height="33.5vh" >
      <div v-if="state.notices.length >= 1">
        <el-row :gutter="24" v-for="notice in state.notices" :key = notice.id class ="el-item el-item-bgcolor1" style="margin : 5px auto;">
          <el-col :span="4" class ="li-title li-item" style="overflow: hidden;">{{notice.studyroom.studyName}}</el-col>
          <el-col :span="14" class ="li-lesson li-item" style="overflow: hidden;">
            <el-popover
              placement="bottom"
              :title="notice.noticeTitle"
              :width="600"
              trigger="click"
              :content="notice.noticeContent"
            >
              <template #reference>
                <el-button type="text" style="color:black;">{{notice.noticeTitle}}</el-button>
              </template>
            </el-popover>
          </el-col>
          <el-col :span="2" class ="li-item filebar" v-if="notice.noticeFile.length">
            <span><font-awesome-icon icon="file-download" /></span>
            <ul>
              <h4>파일목록</h4>
              <hr>
              <div v-for="nf in notice.noticeFile" :key = nf.fileId>
                <p><a @click="downNoticeFile(nf.fileName, nf.filePath, nf.fileOriginName)" class="filenamehover">💾 {{nf.fileOriginName}}</a></p>
              </div>
            </ul>
          </el-col>
          <el-col :span="2" class ="li-item filebar" v-else></el-col>
          <el-col :span="4" class ="li-time li-item">{{notice.noticePosted.substring(0, 10)}}</el-col>
        </el-row>
      </div>

      <div v-else style="height: 80%; padding: 100px">
        <h1>등록된 공지가 없습니다</h1>
      </div>
    </el-scrollbar>
    <!-- <hr> -->
    <!-- 과제 -->
    <h1 style="padding: 25px; font-size:30px; margin-left:2vw; text-align: start;">과제목록</h1>
    <el-row :gutter="24" class ="el-item el-item-bgcolor1" style="margin : 5px auto;">
      <el-col :span="4" class ="li-title li-item">교과명</el-col>
      <el-col :span="12" class ="li-lesson li-item">
        <div>
          <span>과제</span>&nbsp;
          <el-tooltip
            content="제목을 클릭해 과제를 확인하세요"
            raw-content
          >
            <span><font-awesome-icon icon="question-circle" /></span>
          </el-tooltip>
        </div>
      </el-col>
      <el-col :span="2" class ="li-item filebar">파일</el-col>
      <el-col :span="4" class ="li-time li-item">제출기한</el-col>
      <el-col :span="2" class ="li-time li-item">제출</el-col>
      <!-- <el-col :span="4" ></el-col> -->
    </el-row>
    <el-scrollbar height="33.5vh">
      <div v-if="state.hw.length >= 1"  >
        <span v-for="hw in state.hw" :key = hw.hwId>
          <el-row :gutter="24" class ="el-item" :class="{'el-item-bgcolor1' : isWork(hw.hwDeadline), 'el-item-bgcolor2': !isWork(hw.hwDeadline)}" style="margin : 5px auto;">
            <el-col :span="4" class ="li-title li-item" style="overflow: hidden;">{{hw.studyroom.studyName}}</el-col>
            <el-col :span="12" class ="li-lesson li-item" style="overflow: hidden;">
              <el-popover
                placement="bottom"
                :title="hw.hwTitle"
                :width="500"
                trigger="click"
                :content="hw.hwContent"
              >
                <template #reference>
                  <el-button type="text" style="color:black;">{{hw.hwTitle}}</el-button>
                </template>
              </el-popover>
            </el-col>
            <el-col :span="2" class ="li-item filebar" v-if="hw.hwFile.length">
              <span><font-awesome-icon icon="file-download" /></span>
              <ul>
                <h4>파일목록</h4>
                <hr>
                <div v-for="hf in hw.hwFile" :key=hf.fileId>
                  <p><a class="filenamehover" @click="downHWFile(hf.fileName, hf.filePath, hf.fileOriginName)">💾 {{hf.fileOriginName}}</a></p>
                </div>
              </ul>
            </el-col>
            <el-col :span="2" class ="li-item filebar" v-else></el-col>
            <el-col :span="4" class ="li-time li-item">{{hw.hwDeadline.substring(0, 10)}} 까지</el-col>
            <el-col :span="2" >
              <el-button type="text" class ="li-item" @click="onOpenHwDialog(hw)">제출하기</el-button>
            </el-col>
            <!-- <el-col :span="2" >
              <el-button type="text" class ="li-item" @click="delStHw(hw.hwid)" style="color: red">삭제하기</el-button>
            </el-col> -->
          </el-row>
        </span>
      </div>
      <div v-else style="height: 80%; padding: 100px">
        <h1>등록된 과제가 없습니다</h1>
      </div>
    </el-scrollbar>
    <!-- 과제 제출 -->
    <hw-dialog
      :open="state.hwDialogOpen"
      @closeHwDialog="onCloseHwDialog"
      v-bind:props_hw = state.props_hw
      style="box-shadow: 3px 3px 3px 3px gray;"
    />
  </div>
</template>

<script>
import { onMounted, reactive } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import HwDialog from './hw-dialog.vue'

export default {
  name: 'Lesson',
  components:{
    HwDialog
  },
  setup () {
    const router = useRouter()
    const store = useStore()
    const state = reactive({
      notices:{},
      hw:{},
      props_hw:{},
      hwDialogOpen:false,
      notice : [],
      hw : [],
    })
    // 공지사항
    const downNoticeFile = function(fileName, filePath, fileOriginName) {
      store.dispatch('root/requestNoticeFileDown', {
        fileName: fileName
      })
      .then(res => {
        const anchor = document.createElement('a')
        anchor.href = res.data
        anchor.download = fileOriginName
        document.body.appendChild(anchor)
        anchor.click()
        document.body.removeChild(anchor)
      })
    }
    // 과제
    const delStHw = function(hwid){
      store.dispatch('root/requestdelsthw' ,{
        "hwId": hwid,
        "stId": store.state.root.userid
      })
    }
    const onOpenHwDialog=function(item){
      state.props_hw = item
      state.hwDialogOpen = true
    }
    const onCloseHwDialog=function(){
      state.hwDialogOpen = false
      state.props_hw={}
    }
    const downHWFile = function(fileName, filePath, fileOriginName) {
      store.dispatch('root/requestHomeworkFileDown', {
        fileName: fileName
      })
      .then(res => {
        const anchor = document.createElement('a')
        anchor.href = res.data
        anchor.download = fileOriginName
        document.body.appendChild(anchor)
        anchor.click()
        document.body.removeChild(anchor)
      })
    }
    const isWork = function(dead){
      let now = new Date();
      var year = now.getFullYear();
      var month = ('0' + (now.getMonth() + 1)).slice(-2);
      var day = ('0' + now.getDate()).slice(-2);
      var hour = now.getHours();
      var minute = now.getMinutes();
      var dateString = year + '-' + month  + '-' + day +" "+hour+":"+minute;
      return dead.substring(0, 16) >= dateString
    }
    onMounted (() => {
      store.commit('root/setMenuActiveMenuName', 'history')
      // 과제 불러오기
      store.dispatch('root/requestGetHW',{
        stId : store.state.root.userid
      })
      .then(function(result){
        state.hw=result.data
      })
      .catch(function(err){
        alert(err)
      })
      // 공지사항 불러오기
      store.dispatch('root/requestGetSTNotice',{
        stId : store.state.root.userid
      })
      .then(function(result){
        state.notices=result.data
      })
      .catch(function(err){
        alert(err)
      })
    })
    return {state, downNoticeFile, isWork, onOpenHwDialog, onCloseHwDialog, delStHw, downHWFile}
  },
  created:function(){
    const localvuex=JSON.parse(localStorage.getItem('vuex'))
  }
}
</script>
<style scoped>
*, html, body {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
  }
  .el-item{
    align-items: center;
    border-radius: 10px;
    height: 60px;
    width: 95%;
    margin-bottom: 5px;
  }
  .el-item-bgcolor0{
    /* 상단 탭 */
  }
  .el-item-bgcolor1{
    background-color: #F5FdFF;  /* 남은 과제 */
    box-shadow: 1px 1px 1px 1px #C0C4CC;
  }
  .el-item-bgcolor2{
    background-color:grey;  /* 날짜 지났는데 못한 과제 */
    box-shadow: 1px 1px 1px 1px #C0C4CC;
  }
  .el-item-bgcolor3{
    /* 완료한 과제 */
  }
  .li-item{
    padding: 5px;
  }
  .sub_btn{
    padding: 5px;
    margin: 20px;

  }
  .filebar>ul {
    display: none;
    overflow: hidden;
    height: auto;
    padding: 8px;
    position: absolute;
    z-index: 10;
    min-width: 150px;
    background-color: #CFBCAE;
    color: #fff;
    border-radius: 10px;
    margin-top: 5px;
  }
  .filebar:hover>ul {
    display: block;
  }
  .filebar>ul>li{
    margin: 5px;
  }
  .filenamehover {
    cursor: pointer;
    padding: 10px;
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
</style>
