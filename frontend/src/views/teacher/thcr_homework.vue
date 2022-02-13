<template>
  <div>
    <h1 style="margin: 25px; font-size:30px; text-align: start;">제출된 과제</h1>
    <ModalView v-bind:isupdate= state.isupdate v-bind:pdata = state.props_data style="box-shadow: 3px 3px 3px 3px gray; z-index:11;" v-if ="state.isVisible" @close-modal="closemodal"></ModalView>
    <div style="text-align: right">
      <el-button style="background-color: #6B3BE3; color: #fff; width: 100px; height: 40px; border-radius: 15px; border:none;" class="sub_btn" @click="state.isVisible = true">과제 생성</el-button>
    </div>
    <el-scrollbar wrap-style="max-height: 220px;" style="min-height:200px;">
      <div v-if="state.homeworks.length >= 1" style="margin:0 20px;">
        <el-row :gutter="24" v-for="(hw, index) in state.homeworks" :key = hw.hwId class ="el-item">
            <el-col :span="5" class ="li-title li-item">{{hw.hwTitle}}</el-col>
            <el-col :span="7" class ="li-lesson li-item">{{hw.hwContent}}</el-col>
            <el-col :span="3" class ="li-time li-item">{{hw.hwDeadline.substring(0, 10)}} 까지</el-col>
            <el-col :span="3" class ="li-time li-item">{{hw.hwPosted.substring(0, 10)}}</el-col>
            <el-col :span="2" class ="li-item filebar">
              첨부파일
              <ul>
                <h4>파일목록</h4>
                <div v-for="hf in hw.hwFile" :key=hf.fileId>
                  <hr>
                  <a class="filenamehover" @click="downHWFile(hf.fileName, hf.filePath, hf.fileOriginName)">💾 {{hf.fileOriginName}}</a>
                </div>
              </ul>
            </el-col>
            <el-col :span="2" >
              <el-button type="text" class ="li-item" @click = "updatehomework(index)">수정</el-button>
            </el-col>
            <el-col :span="2" >
              <el-button type="text" class ="li-item" @click = "delhomeworks(hw.hwId)" style="color: red">삭제</el-button>
            </el-col>
        </el-row>
      </div>
      <div v-else style="height: 80%; padding: 100px">
        <h1>등록된 과제가 없습니다</h1>
      </div>
    </el-scrollbar>
  </div>
</template>

<script>
import ModalView from "./homework_modal"
import { reactive, computed, ref, onMounted } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
export default {
  name: 'Tchr_homework',
  components:{
    ModalView,
  },
  setup() {
    const router = useRouter()
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
      console.log(state.homeworks[idx])
      state.isVisible = true
    }
    const closemodal = function(){
      gethomeworksList();
      state.isVisible = false
      state.isupdate = false
      state.props_data={}
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
    onMounted(()=>{
      gethomeworksList();
    })

    return {state, onMounted, closemodal, updatehomework, delhomeworks, gethomeworksList, downHWFile}
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
    background-color: #ecf0f1;
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
    height: auto;
    position: absolute;
    z-index: 10;
    min-width: 150px;
    background-color: #6B3BE3;
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
