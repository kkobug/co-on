<template>
  <div>
    <ModalView v-bind:isupdate= state.isupdate v-bind:pdata = state.props_data style="box-shadow: 3px 3px 3px 3px gray;" v-if ="state.isVisible" @close-modal="closemodal"></ModalView>
    <div style="text-align: right">
      <el-button style="background-color: #6B3BE3; color: #fff; width: 100px; height: 40px; border-radius: 25px" class="sub_btn" @click="state.isVisible = true">숙제 생성</el-button>
    </div>
    <div v-if="state.homeworks">
      <div v-for="(hw, index) in state.homeworks" :key = hw.hwId class ="el-item">
        <div class="li-left li-sec">
          <div class ="li-title li-item">{{hw.hwTitle}}</div>
          <div class ="li-lesson li-item">{{hw.hwContent}}</div>
        </div>
        <div class="li-right li-sec">
          <div class ="li-time li-item">{{hw.hwPosted}}</div>
          <el-button type="text" class ="li-item" @click = "updatehomework(index)">수정</el-button>
          <el-button type="text" class ="li-item" @click = "delhomeworks(hw.hwId)">삭제</el-button>
        </div>
      </div>
    </div>
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
    onMounted(()=>{
      gethomeworksList();
    })

    return {state, onMounted, closemodal, updatehomework, delhomeworks, gethomeworksList}
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
  /* border: solid 1px black; */
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
