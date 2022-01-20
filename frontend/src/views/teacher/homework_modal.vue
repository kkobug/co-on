<template>
  <div class ="modal">
    <div class="overlay" @click="$emit('close-modal')">X</div>
    <div><p>과제 등록</p></div>
<<<<<<< HEAD
    <div><label for="title">제목</label><input name="title" type="text"></div>
    <div><label for="explain">설명</label><input name="explain" type="text"></div>
    <div><label for="target">목표</label><input name="target" type="text"></div>
    <div><label for="date">날짜</label><input name="date" type="text"></div>
=======
    <div><label for="title">제목</label><input v-model="state.form.title" name="title" type="text"></div>
    <div><label for="explain">설명</label><input v-model="state.form.explain" name="explain" type="text"></div>
    <div><label for="target">목표</label><input v-model="state.form.target" name="target" type="text"></div>
    <div><label for="date">날짜</label><input v-model="state.form.date" name="date" type="text"></div>
    <button @click="addhomework">등록</button>
>>>>>>> develop_frontend
  </div>
</template>

<script>
<<<<<<< HEAD

export default {
  name: 'ModalView',
=======
import { reactive, computed, ref, onMounted } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
export default {
  name: 'ModalView',
  setup() {
    const router = useRouter()
    const store = useStore()
    const state = reactive({
      form:{
        title:"",
        explain:"",
        target:"",
        date:"",
      }
    })
    const addhomework = function(){
      console.log(state.form)
    }
    return {state, addhomework}
  },
>>>>>>> develop_frontend

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
