<template>
  <div class ="modal">
    <div class="overlay" @click="closeModal">X</div>
    <div><p>과제 등록</p></div>
    <div><label for="title">제목</label><input v-model="state.title" name="title" type="text"></div>
    <div><label for="explain">설명</label><input v-model="state.contents" name="explain" type="text"></div>
    <div><label for="date">날짜</label><input v-model="state.date" name="date" type="text"></div>
    <button v-if="isupdate" @click="updatehomework">수정</button>
    <button v-else @click="addhomework">등록</button>
  </div>
</template>

<script>
import { reactive, computed, ref, onMounted } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
export default {
  name: 'ModalView',
  props: ["pdata", "isupdate"],
  setup(props, context) {
    const router = useRouter()
    const store = useStore()
    const state = reactive({
      isupdate: props.isupdate,
      title : props.pdata.hwTitle,
      contents : props.pdata.hwContent,
      date : props.pdata.hwDeadline,
      hwid :props.pdata.hwId,
      posted : "2022-01-26 15:14:24"
    })
    const closeModal = function(){
      state.title = null
      state.contents = null
      state.date = null
      state.isupdate = false
      state.hwid = null
      context.emit('close-modal')
    }
    const showRequest = (request)=> {
      const reqObject = JSON.parse(JSON.stringify(request));
      console.log('request', reqObject);
      dataChange(reqObject);
    };
    const addhomework = function(){
      store.dispatch('root/requestAddHomework', {
        hwContent: state.contents,
        hwDeadline: "2022-02-18 12:00",
        hwTitle: state.title,
        studyId: store.state.root.curClassId,
        tchrId: store.state.root.userid})
      closeModal()
    }
    const updatehomework = function(){
      store.dispatch('root/requestupdateHomework', {
        hwContent: state.contents,
        hwDeadline: state.date,
        hwTitle: state.title,
        hwId:state.hwid,
        hwPosted:state.posted})
      closeModal()
    }
    const dataChange = function(pdata){
      console.log("abab",pdata)
      state.title = pdata.hwTitle
      state.contents = pdata.hwContent
      state.date = pdata.hwDeadline
      console.log(state.title, pdata.hwTitle)
    }
    onMounted(()=>{
      console.log("안녕하세요", props.pdata, props.isupdate)

      showRequest(props.pdata);
    })
    return {state, addhomework, closeModal, dataChange, updatehomework}
  },

};
</script>

<style scoped>
  .modal{
    width: 500px;
    height: 300px;
    position: fixed;
    top: 50%;
    left: 50%;
    -webkit-transform: translate(-50%, -50%);
    -moz-transform: translate(-50%, -50%);
    -ms-transform: translate(-50%, -50%);
    -o-transform: translate(-50%, -50%);
    transform: translate(-50%, -50%);
    border : solid 1px black;
    border-radius: 10px;
    background-color: white;
  }
  .overlay{
    position: absolute;
    width : 20px;
    height : 20px;
    right: 10px;
    top: 10px;
  }
</style>
