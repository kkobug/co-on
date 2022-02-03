<template>
  <div class ="modal">
    <div class="overlay" @click="closeModal">X</div>
    <div><p>과제 등록</p></div>
    <form action="#" id="hwForm" method="post" enctype="multipart/form-data">
      <div><label for="hwTitle">제목</label><input v-model="state.hwTitle" name="hwTitle" type="text"></div>
      <div><label for="explain">설명</label><input v-model="state.hwContent" name="explain" type="text"></div>
      <div><label for="hwDeadline">날짜</label><input v-model="state.hwDeadline" name="hwDeadline" type="text"></div>
      <div><label for="hwFile">파일</label><input type="file" multiple="multiple" @change="addFile" ref="refNoticeFile" name="hwFile" id="hwFile"></div>
      <button v-if="isupdate" @click="updatehomework">수정</button>
      <button v-else @click="addhomework">등록</button>
    </form>
    <!-- <el-button type="text" class="overlay" @click="closeModal" style="margin-right: 5px">❌</el-button>
    <h2 style="margin-top: 4vh"><strong>과제 등록</strong></h2>
    <el-row>
      <el-col :span="20" :offset="2">
        <el-form>
          <el-form-item label="제목:">
            <el-input v-model="state.form.hwTitle"></el-input>
          </el-form-item>
          <el-form-item label="설명:">
            <el-input v-model="state.form.hwContent"></el-input>
          </el-form-item>
          <el-form-item label="날짜:">
            <el-date-picker
              v-model="state.form.hwDeadline" type="date" placeholder="Pick your birthday" style="width: 100%" format="YYYY-MM-DD" value-format="YYYYMMDD">
            </el-date-picker>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
    <div class="btn-div">
      <el-button v-if="isupdate" @click="updatehomework">수정</el-button>
      <el-button v-else @click="addhomework">등록</el-button>
    </div> -->
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
