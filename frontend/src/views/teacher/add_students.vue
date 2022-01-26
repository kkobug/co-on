<template>
  <div class ="modal">
    <div class="overlay" @click="closeModal">X</div>
    <div><p>학생 등록</p></div>
    <div><label for="studid">학생 아이디</label><input v-model="state.studId"  name="studid" type="text"></div>
    <button @click="addstudent">등록</button>
  </div>
</template>

<script>
import { reactive } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
export default {
  name: 'ModalView',
  setup(props, context) {
    const router = useRouter()
    const store = useStore()
    const state = reactive({
      studId:"",
    })
    const closeModal = function(){
      state.studId =""
      context.emit('close-modal')
    }
    const addstudent = function(){
      console.log(state.studId, store.state.root.studyId, store.state.root.userid)
      store.dispatch('root/requestAddStudentInStudy', {
        stId : state.studId,
        studyId: store.state.root.curClassId,
        tchrId: store.state.root.userid
      })
      closeModal()
    }
    return {state, addstudent, closeModal}
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
