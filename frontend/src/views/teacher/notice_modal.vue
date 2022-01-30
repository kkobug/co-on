<template>
  <div class ="modal">
    <el-button type="text" class="overlay" @click="$emit('close-modal')" style="margin-right: 5px">❌</el-button>
    <h2 style="margin-top: 4vh"><strong>공지 등록</strong></h2>
    <el-row>
      <el-col :span="20" :offset="2">
        <el-form>
          <el-form-item label="제목:">
            <el-input v-model="state.title"></el-input>
          </el-form-item>
          <el-form-item label="내용:">
            <el-input v-model="state.contents" type="textarea"></el-input>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
    <!-- <div><label for="title">제목</label><input v-model="state.title"  name="title" type="text"></div>
    <div><label for="content">내용</label><textarea v-model="state.contents" name="content" id="" cols="30" rows="10"></textarea></div> -->
    <div class="btn-div">
      <el-button v-if="isupdate" @click="updatenotice">수정</el-button>
      <el-button v-else @click="addnotice">등록</el-button>
    </div>
  </div>
</template>

<script>
import { reactive, onMounted } from 'vue'
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
      title : props.pdata.noticeTitle,
      contents : props.pdata.noticeContent,
      date : props.pdata.noticePosted,
      ntid :props.pdata.noticeId,
      posted : "2022-01-26 15:14:24"
    })
    const closeModal = function(){
      state.title = null
      state.contents = null
      state.date = null
      state.isupdate = false
      state.ntid = null
      context.emit('close-modal')
    }
    const showRequest = (request)=> {
      const reqObject = JSON.parse(JSON.stringify(request));
      console.log('request', reqObject);
      dataChange(reqObject);
    };
    const addnotice = function(){
      store.dispatch('root/requestAddNotice', {
        noticeContent: state.contents,
        noticeTitle: state.title,
        studyId: store.state.root.curClassId,
        tchrId: store.state.root.userid})
      closeModal()
    }
    const updatenotice = function(){
      store.dispatch('root/requestUpdateNotice', {
        noticeContent: state.contents,
        noticeId: state.ntid,
        noticePosted: state.date,
        noticeTitle: state.title})
      closeModal()
    }
    const dataChange = function(pdata){
      console.log("abab",pdata)
      state.title = pdata.noticeTitle,
      state.contents = pdata.noticeContent,
      state.date = pdata.noticePosted,
      state.ntid = pdata.noticeId,
      console.log(state.title, pdata.noticeTitle)
    }
    onMounted(()=>{
      console.log("안녕하세요", props.pdata, props.isupdate)

      showRequest(props.pdata);
    })
    return {state, onMounted, addnotice, closeModal, dataChange, updatenotice, updatenotice}
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
    /* border : solid 1px black; */
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
