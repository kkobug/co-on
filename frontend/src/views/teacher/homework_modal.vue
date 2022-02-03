<template>
  <div class ="modal">
<<<<<<< HEAD
    <div class="overlay" @click="closeModal">X</div>
    <div><p>과제 등록</p></div>
    <form action="#" id="hwForm" method="post" enctype="multipart/form-data">
      <div><label for="title">제목</label><input v-model="state.title" name="title" type="text"></div>
      <div><label for="explain">설명</label><input v-model="state.contents" name="explain" type="text"></div>
      <div><label for="date">날짜</label><input v-model="state.date" name="date" type="text"></div>
      <div><label for="hwFile">파일</label><input type="file" multiple="multiple" @change="addFile" ref="refNoticeFile" name="hwFile" id="hwFile"></div>
      <button v-if="isupdate" @click="updatehomework">수정</button>
      <button v-else @click="addhomework">등록</button>
    </form>
=======
    <el-button type="text" class="overlay" @click="closeModal" style="margin-right: 5px">❌</el-button>
    <h2 style="margin-top: 4vh"><strong>과제 등록</strong></h2>
    <el-row>
      <el-col :span="20" :offset="2">
        <el-form>
          <el-form-item label="제목:">
            <el-input v-model="state.title"></el-input>
          </el-form-item>
          <el-form-item label="설명:">
            <el-input v-model="state.contents"></el-input>
          </el-form-item>
          <el-form-item label="날짜:">
            <el-date-picker
              v-model="state.date" type="date" placeholder="Pick your birthday" style="width: 100%" format="YYYY-MM-DD" value-format="YYYYMMDD">
            </el-date-picker>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
    <!-- <div><label for="title">제목: </label><input v-model="state.title" name="title" type="text"></div>
    <div><label for="explain">설명: </label><input v-model="state.contents" name="explain" type="text"></div>
    <div><label for="date">날짜: </label><input v-model="state.date" name="date" type="text"></div> -->
    <div class="btn-div">
      <el-button v-if="isupdate" @click="updatehomework">수정</el-button>
      <el-button v-else @click="addhomework">등록</el-button>
    </div>
>>>>>>> 8b565c0ec828069cd6401a87439399b4ced689c5
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
      posted : "2022-01-26 15:14:24",
      hwFile : null,
    })
    const closeModal = function(){
      state.title = null
      state.contents = null
      state.date = null
      state.isupdate = false
      state.hwid = null
      state.hwFile = null
      context.emit('close-modal')
    }
    const showRequest = (request)=> {
      const reqObject = JSON.parse(JSON.stringify(request));
      console.log('request', reqObject);
      dataChange(reqObject);
    };
    const addhomework = function(){
<<<<<<< HEAD
      var hwFormData = new FormData(document.querySelector('#hwForm'))
      hwFormData.append('studyId', state.id)
      hwFormData.append()

      store.dispatch('root/requestAddHomework', hwFormData)
      closeModal()
=======
      store.dispatch('root/requestAddHomework', {
        hwContent: state.contents,
        hwDeadline: "2022-02-18 12:00",
        hwTitle: state.title,
        studyId: store.state.root.curClassId,
        tchrId: store.state.root.userid})
      .then(res=>{
        closeModal();
      })
>>>>>>> 8b565c0ec828069cd6401a87439399b4ced689c5
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
    const addFile = function(event) {
      var files = event.target.files
      var filesArr = Array.prototype.slice.call(files)
      state.hwFile = filesArr
    }
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
  .btn-div .el-button {
    width: 20%;
    background-color: #6B3BE3;
    border-radius: 12px;
    text-align: center;
    color: #fff;
    margin-top: 10px;
    margin-bottom: 2vh;
  }
</style>
