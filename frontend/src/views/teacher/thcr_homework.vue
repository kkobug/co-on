<template>
  <div style="overflow: hidden; margin: 0vh 9vw">
    <div style="display:flex; justify-content: space-between;">
      <h1 style="padding: 1.5%; font-size:30px; margin-left:2vw; text-align: start;">과제목록</h1>
      <div style="">
        <el-button style="background-color: #91847A; color: #fff; width: 100px; height: 40px; border-radius: 15px; border:none;" class="sub_btn" @click="state.isVisible = true">과제 생성</el-button>
      </div>
    </div>
    <el-row :gutter="24" style="margin : 5px; margin-bottom : 10px;" class ="el-item el-item-bgcolor1">
      <el-col :span="4" class ="li-title li-item">교과명</el-col>
      <el-col :span="11" class ="li-lesson li-item"><div><span>과제</span></div></el-col>
      <el-col :span="2" class ="li-item filebar">파일</el-col>
      <el-col :span="4" class ="li-time li-item">제출기한</el-col>
      <el-col :span="3" class ="li-time li-item">관리</el-col>
    </el-row>
    <ModalView v-bind:isupdate= state.isupdate v-bind:pdata = state.props_data style="box-shadow: 3px 3px 3px 3px gray; z-index:11;" v-if ="state.isVisible" @close-modal="closemodal"></ModalView>
    <el-scrollbar style="height:31vh;">
      <div v-if="state.homeworks.length >= 1" style="margin:0 20px;">
        <span v-for="(hw, index) in state.homeworks" :key = hw.hwId >
          <el-row :gutter="24" class ="el-item" :class="{'el-item-bgcolor1' : isWork(hw.hwDeadline), 'el-item-bgcolor2': !isWork(hw.hwDeadline)}">
            <el-col :span="4" class ="li-title li-item">{{hw.hwTitle}}</el-col>
            <el-col :span="11" class ="li-lesson li-item">{{hw.hwContent}}</el-col>
            <el-col :span="2" class ="li-item filebar" v-if="hw.hwFile.length">
              <span><font-awesome-icon icon="file-download" /></span>
              <ul>
                <h4>파일목록</h4>
                <div v-for="hf in hw.hwFile" :key=hf.fileId>
                  <hr>
                  <a class="filenamehover" @click="downHWFile(hf.fileName, hf.filePath, hf.fileOriginName)">💾 {{hf.fileOriginName}}</a>
                </div>
              </ul>
            </el-col>
            <el-col :span="2" class ="li-item filebar" v-else></el-col>
            <el-col :span="4" class ="li-time li-item">{{hw.hwDeadline.substring(0, 10)}} 까지</el-col>
            <el-col :span="1" >
              <el-button type="text" class ="li-item" @click = "updatehomework(index)">수정</el-button>
            </el-col>
            <el-col :span="2" >
              <el-button type="text" class ="li-item" @click = "delhomeworks(hw.hwId)" style="color: red">삭제</el-button>
            </el-col>
          </el-row>
        </span>
      </div>
      <div v-else style="height: 80%; padding: 100px">
        <h1>등록된 과제가 없습니다</h1>
      </div>
    </el-scrollbar>
  </div>
</template>

<script>
import ModalView from "./homework_modal"
import { reactive, computed,onMounted } from 'vue'
import { useStore } from 'vuex'
export default {
  name: 'Tchr_homework',
  components:{
    ModalView,
  },
  setup() {
    const store = useStore()
    const state = reactive({
      isupdate : false,
      isVisible :false,
      homeworks:computed(() => store.getters['root/getTchrHomeworkList']),
      props_data:{}
    })
    const gethomeworksList = function(){
      store.dispatch('root/requestListHomework', {
            studyId: store.state.root.curClassId})
        .then(res =>{
          store.state.root.TchrHomeworkList = res.data
          state.homeworks = store.getters['root/getTchrHomeworkList']
        })
    }
    const delhomeworks = function(homwid){
      store.dispatch('root/requestDelHomework', {
            hwId: homwid,
            tchrId: store.state.root.userid})
        .then(res =>{
          gethomeworksList();
        })
    }
    const updatehomework = function(idx){
      state.props_data = state.homeworks[idx]
      state.isupdate = true
      state.isVisible = true
    }
    const closemodal = function(){
      gethomeworksList();
      state.isVisible = false
      state.isupdate = false
      state.props_data={}
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
    onMounted(()=>{
      gethomeworksList();
    })

    return {state, onMounted, isWork, closemodal, updatehomework, delhomeworks, gethomeworksList, downHWFile}
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
    align-items: center;
    border-radius: 10px;
    height: 60px;
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
  .el-item2{
    background-color: grey;
    align-items: center;
    border-radius: 20px;
    height: 60px;
    margin-bottom: 10px;
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
    position: absolute;
    z-index: 10;
    min-width: 150px;
    background-color: #CFBCAE;
    padding: 8px;
    color: #fff;
    border-radius: 10px;
    margin-top: 5px;
  }
  .filebar:hover>ul {
    display: block;
  }
  .filebar:hover>ul>li {
    margin:5px;
  }
  .filenamehover {
    cursor: pointer;
    padding: 10px;
  }
</style>
