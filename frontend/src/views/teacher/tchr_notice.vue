<template>
  <div style="overflow: hidden; margin: 0vh 9vw">
    <div style="display:flex; justify-content: space-between;">
      <h1 style="padding: 1.5%; font-size:30px; margin-left:2vw; text-align: start;">공지사항</h1>
      <div style="">
        <el-button style="background-color: #91847A; color: #fff; width: 100px; height: 40px; border-radius: 15px; border:none;" class="sub_btn" @click="state.isVisible = true">공지하기</el-button>
      </div>
    </div>
    <el-row :gutter="24" style="margin : 5px; margin-bottom : 10px;" class ="el-item el-item-bgcolor1">
      <el-col :span="4" class ="li-title li-item">제목</el-col>
      <el-col :span="12" class ="li-lesson li-item"><div><span>공지</span></div></el-col>
      <el-col :span="2" class ="li-item filebar">파일</el-col>
      <el-col :span="3" class ="li-time li-item">공지일</el-col>
      <el-col :span="3" class ="li-time li-item">관리</el-col>
    </el-row>
    <ModalView style="box-shadow: 3px 3px 3px 3px gray;  z-index:11;" v-bind:isupdate= state.isupdate v-bind:pdata = state.props_data v-if ="state.isVisible" @close-modal="closemodal"></ModalView>
    <el-scrollbar style="height:31vh;">
      <div v-if="state.notices.length >= 1" style="margin:0 20px;">
        <el-row :gutter="24" v-for = "(ntice, index) in state.notices" :key = ntice.id class ="el-item">
            <el-col :span="4" class ="li-title li-item">{{ntice.noticeTitle}}</el-col>
            <el-col :span="12" class ="li-lesson li-item">{{ntice.noticeContent}}</el-col>
            <el-col :span="2" class ="li-item filebar"  style="padding:10px;" v-if="ntice.noticeFile.length">
              <span><font-awesome-icon icon="file-download" /></span>
              <ul>
                <h4>파일목록</h4>
                <div v-for="nf in ntice.noticeFile" :key = nf.fileId>
                  <hr>
                  <a @click="downNoticeFile(nf.fileName, nf.filePath, nf.fileOriginName)" class="filenamehover">💾 {{nf.fileOriginName}}</a>
                </div>
              </ul>
            </el-col>
            <el-col :span="2" class ="li-item filebar"  style="padding:10px;" v-else>
            </el-col>
            <el-col :span="3" class ="li-time li-item">{{ntice.noticePosted.substring(0, 10)}}</el-col>
            <el-col :span="1">
              <el-button type="text" class="li-item" @click="updatenotice(index)">수정</el-button>
            </el-col>
            <el-col :span="2">
              <el-button type="text" class="li-item" @click ="delNotice(ntice.noticeId)" style="color: red">삭제</el-button>
            </el-col>
        </el-row>
      </div>

      <div v-else style="height: 80%; padding: 100px">
        <h1>등록된 공지가 없습니다</h1>
      </div>
    </el-scrollbar>
  </div>
</template>

<script>
import ModalView from "./notice_modal"
import { reactive, computed, onMounted } from 'vue'
import { useStore } from 'vuex'

export default {
  name: 'Tchr_notice',
  components:{
    ModalView,
  },
  setup() {
    const store = useStore()
    const state = reactive({
      isupdate : false,
      isVisible :false,
      notices:computed(() => store.getters['root/getTchrNoticeList']),
      props_data:{}
    })
    const getNoticeList = function(){
      store.dispatch('root/requestListNotice', {
            studyId: store.state.root.curClassId})
        .then(res =>{
          store.state.root.TchrNoticeList = res.data
        })
    }
    const updatenotice = function(idx){
      state.props_data = state.notices[idx]
      state.isupdate = true
      state.isVisible = true
    }
    const delNotice = function(studyid){
      store.dispatch('root/requestDelNotice', {
            noticeId: studyid,
            tchrId: store.state.root.userid})
        .then(res =>{
          getNoticeList();
        })
    }
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
    const closemodal = function(){
      state.isVisible = false
      state.isupdate = false
      state.props_data={}
      getNoticeList();
    }
    onMounted(()=>{
        getNoticeList();
    })
    return {state, onMounted, getNoticeList, updatenotice, closemodal, delNotice, downNoticeFile}
  },
};
</script>
<style scoped>
  *, html, body {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
  }
  .el-item{
    background-color: #F5FdFF;
    align-items: center;
    border-radius: 10px;
    height: 60px;
    margin-bottom: 5px;
    box-shadow: 1px 1px 1px 1px #C0C4CC;
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
</style>
