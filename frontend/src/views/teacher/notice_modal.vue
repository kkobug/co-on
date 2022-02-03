<template>
  <div class ="modal">
    <!-- <div class="overlay" @click="$emit('close-modal')">X</div> -->
      <div><p>공지 등록</p></div>
      <!-- <el-form :model="state.form" :rules="state.rules" ref="signupForm" :label-position="state.form.align">
        <el-form-item prop="noticeTitle" label="제목" :label-width="state.formLabelWidth" >
          <el-input v-model="state.form.noticeTitle" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="noticeContent" label="내용" :label-width="state.formLabelWidth">
          <el-input v-model="state.form.noticeContent" autocomplete="off"></el-input>
        </el-form-item>
          <el-upload
            ref="upload"
            class="upload-demo"
            action="https://jsonplaceholder.typicode.com/posts/"
            :limit="5"
            :on-exceed="handleExceed"
            :auto-upload="false">
            <template #trigger>
              <el-button type="primary">select file</el-button>
            </template>
          </el-upload>
        <el-row>
          <el-button @click="addnotice">작성</el-button>
        </el-row>
      </el-form> -->
      <form action="#" id="noticeForm" method="post" enctype="multipart/form-data">
        <div><label for="noticeTitle">제목</label><input v-model="state.form.noticeTitle"  name="noticeTitle" type="text"></div>
        <div><label for="noticeContent">내용</label><textarea v-model="state.form.noticeContent" name="noticeContent" id="" cols="30" rows="10"></textarea></div>
        <div><label for="noticeFile">파일</label><input type="file" multiple="multiple" @change="addFile" ref="refNoticeFile" name="noticeFile" id="ntFile"></div>
        <button v-if="isupdate" @click="updatenotice">수정</button>
        <button v-else @click="addnotice">등록</button>
      </form>
  </div>
</template>

<script>
import { reactive, onMounted, ref } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
// import $axios from axios;
export default {
  name: 'ModalView',
  props: ["pdata", "isupdate"],
  setup(props, context) {
    const router = useRouter()
    const store = useStore()
    const upload = ref()

    const handleExceed = (files) => {
      upload.value.clearFiles()
      upload.value.handleStart(files[0])
    }
    const state = reactive({
      form : {
        isupdate: props.isupdate,
        noticeTitle : props.pdata.noticeTitle,
        noticeContent : props.pdata.noticeContent,
        ntid : props.pdata.noticeId,
        noticeFile : null,
        studyId: store.state.root.curClassId,
        tchrId: store.state.root.userid
      }
    })
    const closeModal = function(){
      state.form.noticeTitle = null
      state.form.noticeContent = null
      state.form.isupdate = false
      state.form.ntid = null
      state.form.noticeFile = null
      context.emit('close-modal')
    }
    const showRequest = (request)=> {
      const reqObject = JSON.parse(JSON.stringify(request));
      console.log('request', reqObject);
      dataChange(reqObject);
    };
    const addnotice = function(){
      var noticeFormData = new FormData(document.querySelector('#noticeForm'))
      // var noticeFormData = new FormData()
      // noticeFormData.append('noticeTitle', state.form.noticeTitle)
      // noticeFormData.append('noticeContent', state.form.noticeContent)
      // noticeFormData.append('noticeFile', state.form.noticeFile)
      noticeFormData.append('studyId', state.form.studyId)
      noticeFormData.append('tchrId', state.form.tchrId)

      // $axios.post(
      //   "http://localhost:8080/api/v1/notice/create", noticeFormData,
      //   // headers: {
      //   //   'Content-Type' : 'multipart/form-data'
      //   // },
      // )

      // console.log(noticeFormData)
      // for (var pair of noticeFormData.entries()) {
      //   console.log(pair[0] + ',' + pair[1])
      // }
      console.log("!!!!!!!!!!!!!!")
      store.dispatch('root/requestAddNotice', noticeFormData)
      closeModal()
    }
    const addFile = function(event) {
      var files = event.target.files
      var filesArr = Array.prototype.slice.call(files)
      console.log(filesArr)
      console.log(filesArr[0])
      state.form.noticeFile = filesArr
      console.log('파일이 들어가고 있을까요')
      console.log(state.form.noticeFile)

      // console.log(document.$ref)
      // state.form.noticeFile = document.$ref.refNoticeFile.files
    }
    const updatenotice = function(){
      var noticeFormData = new FormData(document.querySelector('#noticeForm'))
      noticeFormData.append('noticeId', props.pdata.noticeId)
      store.dispatch('root/requestUpdateNotice', noticeFormData)
      closeModal()
    }
    const dataChange = function(pdata){
      console.log("abab",pdata)
      state.noticeTitle = pdata.noticeTitle,
      state.noticeContent = pdata.noticeContent,
      state.date = pdata.noticePosted,
      state.ntid = pdata.noticeId,
      console.log(state.noticeTitle, pdata.noticeTitle)
    }
    onMounted(()=>{
      console.log("안녕하세요", props.pdata, props.isupdate)

      showRequest(props.pdata);
    })
    return {state, onMounted, addnotice, closeModal, dataChange, updatenotice, updatenotice, addFile}
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
