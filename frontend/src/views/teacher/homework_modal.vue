<template>
  <div class ="modal">
    <el-button type="text" class="overlay" @click="closeModal" style="margin-right: 5px">❌</el-button>
    <h2 style="margin-top: 4vh"><strong>과제 등록</strong></h2>
    <form action="#" id="hwForm" method="post" enctype="multipart/form-data">
      <div>
        <label for="hwTitle">제목: </label>
        <input v-model="state.form.hwTitle" name="hwTitle" type="text" style="height: 80%">
      </div>
      <div>
        <label for="hwContent">설명: </label>
        <input v-model="state.form.hwContent" name="hwContent" type="text" style="height: 80%">
      </div>
      <div>
        <label for="hwDeadline">날짜: </label>
        <input v-model="state.form.hwDeadline" name="hwDeadline" type="text" style="height: 80%">
      </div>
      <div>
        <label for="hwFile">파일: </label>
        <input type="file" multiple="multiple" @change="addFile" ref="refHwFile" name="hwFile" id="hwFile" style="width: 62%">
      </div>
      <div v-if="isupdate" @click="updatehomework" class="purplebtn"><strong>수정</strong></div>
      <div v-else @click="addhomework" class="purplebtn"><strong>등록</strong></div>
    </form>

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
      form : {
        isupdate: props.isupdate,
        hwTitle : props.pdata.hwTitle,
        hwContent : props.pdata.hwContent,
        hwDeadline : props.pdata.hwDeadline,
        hwId : props.pdata.hwId,
        hwFile : null,
        studyId: store.state.root.curClassId,
        tchrId: store.state.root.userid
      }
    })
    const closeModal = function(){
      state.form.hwTitle = null
      state.form.hwContent = null
      state.form.hwDeadline = null
      state.form.isupdate = false
      state.form.hwId = null
      state.form.hwFile = null
      context.emit('close-modal')
    }
    const showRequest = (request)=> {
      const reqObject = JSON.parse(JSON.stringify(request));
      console.log('request', reqObject);
      dataChange(reqObject);
    };
    const addhomework = function(){
      var hwFormData = new FormData(document.querySelector('#hwForm'))
      hwFormData.append('studyId', state.form.studyId)
      hwFormData.append('tchrId', state.form.tchrId)
      // hwFormData.append('hwTitle', state.form.hwTitle)
      // hwFormData.append('hwContent', state.form.hwContent)
      // hwFormData.append('hwDeadline', state.form.hwDeadline)
      // hwFormData.append('hwFile', state.form.hwFile)
      console.log("!!")

      store.dispatch('root/requestAddHomework', hwFormData)
      .then(res => {
        closeModal()
      })
    }
    const addFile = function(event) {
      var files = event.target.files
      var filesArr = Array.prototype.slice.call(files)
      state.form.hwFile = filesArr
    }
    const updatehomework = function(){
      var hwFormData = new FormData()
      hwFormData.append('hwId', state.form.hwId)
      hwFormData.append('hwTitle', state.form.hwTitle)
      hwFormData.append('hwContent', state.form.hwContent)
      hwFormData.append('hwDeadline', state.form.hwDeadline)
      hwFormData.append('hwFile', state.form.hwFile)
      store.dispatch('root/requestUpdateNotice', hwFormData)
      .then(res => {
        closeModal()
      })
    }
    const dataChange = function(pdata){
      console.log("abab",pdata)
      state.form.hwTitle = pdata.hwTitle
      state.form.hwContent = pdata.hwContent
      state.form.hwDeadline = pdata.hwDeadline
      state.form.hwId = pdata.hwId
    }
    onMounted(()=>{
      console.log("안녕하세요", props.pdata, props.isupdate)
      showRequest(props.pdata);
    })
    return {state, addhomework, closeModal, dataChange, updatehomework, addFile}
  },

};
</script>

<style scoped>
  .modal{
    width: 500px;
    height: auto;
    position: fixed;
    top: 50%;
    left: 50%;
    -webkit-transform: translate(-50%, -50%);
    -moz-transform: translate(-50%, -50%);
    -ms-transform: translate(-50%, -50%);
    -o-transform: translate(-50%, -50%);
    transform: translate(-50%, -50%);
    /* border : solid 2px black; */
    border-radius: 20px;
    background-color: white;
  }
  .overlay{
    position: absolute;
    width : 20px;
    height : 20px;
    right: 10px;
    top: 10px;
  }
  .purplebtn {
    width: 20%;
    height: 30px;
    background-color: #6B3BE3;
    border: none;
    border-radius: 12px;
    text-align: center;
    color: #fff;
    margin-top: 10px;
    margin-bottom: 2vh;
    cursor: pointer;
  }
  .purplebtn:hover {background-color: #fff; color: #6B3BE3;}
  label {
    display: inline-block;
    width: 10%;
    text-align: start;
  }
  input {
    width: 60%;
    border-color: #f0f0f1;
    border-radius: 5px;
  }
  div {
    margin-bottom: 1vh;
    height: 25px;
  }
  .form-indiv{
    margin : 50px;
  }
  .form-label{
    margin: 15px;
  }

</style>
