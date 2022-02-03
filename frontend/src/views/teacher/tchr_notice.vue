<template>
  <div>
    <ModalView style="box-shadow: 3px 3px 3px 3px gray;" v-bind:isupdate= state.isupdate v-bind:pdata = state.props_data v-if ="state.isVisible" @close-modal="closemodal"></ModalView>
    <div style="text-align: right">
      <el-button style="background-color: #6B3BE3; color: #fff; width: 100px; height: 40px; border-radius: 15px" class="sub_btn" @click="state.isVisible = true">공지 생성</el-button>
    </div>
    <div>
      <div v-for = "(ntice, index) in state.notices" :key = ntice.id class ="el-item">
        <div class="li-left li-sec">
          <div class ="li-title li-item">{{ntice.noticeTitle}}</div>
          <div class ="li-lesson li-item">{{ntice.noticeContent}}</div>
          <div class ="li-lesson li-item">{{ntice.noticeFile}}</div>
        </div>
        <div class="li-right li-sec">
          <div class ="li-time li-item">{{ntice.noticePosted}}</div>
          <el-button type="text" class="li-item" @click="updatenotice(index)">수정</el-button>
          <el-button type="text" class="li-item" @click ="delNotice(ntice.noticeId)">삭제</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ModalView from "./notice_modal"
import { reactive, computed, ref, onMounted } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
export default {
  name: 'Tchr_notice',
  components:{
    ModalView,
  },
  setup() {
    const router = useRouter()
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
        console.log("get data", store.state.root.TchrNoticeList)
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
  const closemodal = function(){
    state.isVisible = false
    state.isupdate = false
    state.props_data={}
    getNoticeList();
  }
  onMounted(()=>{
      getNoticeList();
    })
    return {state, onMounted, getNoticeList, updatenotice, closemodal, delNotice}
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
  display: flex;
  justify-content: space-between;
  background-color: #ecf0f1;
  align-items: center;
  border-radius: 20px;
  height: 80px;
  margin-bottom: 10px;
}
.li-sec{
  display: flex;
}
.li-item{
  margin: 20px;
}
.sub_btn{
  padding: 5px;
  margin: 20px;
}
</style>
