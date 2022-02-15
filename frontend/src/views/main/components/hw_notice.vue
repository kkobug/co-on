<template>
  <div style="overflow: hidden; margin: 0vh 15vh">
    <h1 style="padding: 1.5%; font-size:30px; margin-left:10%; text-align: start;">공지사항</h1>
    <el-row :gutter="24" style="margin : 5px auto;" class ="el-item el-item-bgcolor1">
      <el-col :span="4" class ="li-title li-item">교과명</el-col>
      <el-col :span="12" class ="li-lesson li-item">내용</el-col>
      <el-col :span="4" class ="li-time li-item">게시일</el-col>
      <el-col :span="4" class ="li-item filebar">파일목록</el-col>
    </el-row>
    <el-scrollbar height="33.5vh" >
      <div v-if="state.notices.length >= 1">
        <el-row :gutter="24" v-for="notice in state.notices" :key = notice.id class ="el-item el-item-bgcolor1" style="margin : 5px auto;">
          <el-col :span="4" class ="li-title li-item" style="overflow: hidden;">{{notice.studyroom.studyName}}</el-col>
          <el-col :span="12" class ="li-lesson li-item" style="overflow: hidden;">
            <el-popover
              placement="bottom"
              :title="notice.noticeTitle"
              :width="500"
              trigger="click"
              :content="notice.noticeContent"
            >
              <template #reference>
                <el-button type="text">{{notice.noticeTitle}}</el-button>
              </template>
            </el-popover>
          </el-col>
          <el-col :span="4" class ="li-time li-item">{{notice.noticePosted.substring(0, 10)}}</el-col>
          <el-col :span="4" class ="li-item filebar">
            첨부파일
            <ul>
              <h4>파일목록</h4>
              <div v-for="nf in notice.noticeFile" :key = nf.fileId>
                <hr>
                <a @click="downNoticeFile(nf.fileName, nf.filePath, nf.fileOriginName)" class="filenamehover">💾 {{nf.fileOriginName}}</a>
              </div>
            </ul>
          </el-col>
        </el-row>
      </div>

      <div v-else style="height: 80%; padding: 100px">
        <h1>등록된 공지가 없습니다</h1>
      </div>
    </el-scrollbar>
    <!-- <hr> -->
    <!-- 과제 -->
    <h1 style="padding: 25px; font-size:30px; margin-left:10%; text-align: start;">전체 과제 목록</h1>
    <el-row :gutter="24" class ="el-item el-item-bgcolor1" style="margin : 5px auto;">
      <el-col :span="4" class ="li-title li-item">교과명</el-col>
      <el-col :span="9" class ="li-lesson li-item">내용</el-col>
      <el-col :span="4" class ="li-time li-item">제출기한</el-col>
      <el-col :span="3" class ="li-item filebar">파일 목록</el-col>
      <el-col :span="3" >
      </el-col>
    </el-row>
    <el-scrollbar height="33.5vh">
      <div v-if="state.hw.length >= 1"  >
        <span v-for="hw in state.hw" :key = hw.hwId>
          <el-row :gutter="24" class ="el-item" :class="{'el-item-bgcolor1' : isWork(hw.hwDeadline), 'el-item-bgcolor2': !isWork(hw.hwDeadline)}" style="margin : 5px auto;">
            <el-col :span="4" class ="li-title li-item" style="overflow: hidden;">{{hw.studyroom.studyName}}</el-col>
            <el-col :span="10" class ="li-lesson li-item" style="overflow: hidden;">
              <el-popover
                placement="bottom"
                :title="hw.hwTitle"
                :width="200"
                trigger="click"
                :content="hw.hwContent"
              >
                <template #reference>
                  <el-button type="text">{{hw.hwTitle}}</el-button>
                </template>
              </el-popover>
            </el-col>
            <el-col :span="4" class ="li-time li-item">{{hw.hwDeadline.substring(0, 10)}} 까지</el-col>
            <el-col :span="3" class ="li-item filebar">
              첨부파일
              <ul>
                <h4>파일목록</h4>
                <div v-for="hf in hw.hwFile" :key=hf.fileId>
                  <hr>
                  <a class="filenamehover" @click="downHWFile(hf.fileName, hf.filePath, hf.fileOriginName)">💾 {{hf.fileOriginName}}</a>
                </div>
              </ul>
            </el-col>
            <el-col :span="3" >
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
      const fileurl = `http://localhost:8080/api/v1/notice/download-file?fileName=${fileName}&filePath=${filePath}`
      const anchor = document.createElement('a')
      anchor.href = fileurl
      anchor.download = fileOriginName
      document.body.appendChild(anchor)
      anchor.click()
      document.body.removeChild(anchor)
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
      console.log("열림", state.props_hw)
    }
    const onCloseHwDialog=function(){
      state.hwDialogOpen = false
      state.props_hw={}
    }
    const downHWFile = function(fileName, filePath, fileOriginName) {
      const fileurl = `http://localhost:8080/api/v1/homework/download-file?fileName=${fileName}&filePath=${filePath}`
      const anchor = document.createElement('a')
      anchor.href = fileurl
      anchor.download = fileOriginName
      document.body.appendChild(anchor)
      anchor.click()
      document.body.removeChild(anchor)
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
        console.log("rhkwp", result.data)
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
        console.log(result.data)
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
    width:80%;
    margin-bottom: 5px;
  }
  .el-item-bgcolor1{
    background-color: #ecf0f1;
  }
  .el-item-bgcolor2{
    background-color:grey;
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
    height: auto;
    padding: 8px;
    position: absolute;
    z-index: 10;
    min-width: 150px;
    background-color: #6B3BE3;
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
  .filebar:hover>ul {
    height: 150px;
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
