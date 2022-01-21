<template>
  <div class ="modal">
    <div class="overlay" @click="$emit('close-modal')">X</div>
    <div><p>수업 등록</p></div>
    <div><label for="title">제목</label><input v-model="state.form.title" name="title" type="text"></div>
    <div><label for="content">내용</label><textarea v-model="state.form.content" name="content" id="" cols="30" rows="10"></textarea></div>
    <button @click="addclass">등록</button>
  </div>
</template>

<script>
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
        content:"",
      }
    })
    const addclass = function(){
      store.dispatch('root/requestTchrCreateClass', {
          studyDesc : state.form.content,
          studyName: state.form.title,
          tchrId: store.state.root.userid})
      state.form=
      {
        title:"",
        content:"",
      }
      console.log(state.form)

    }
    return {state, addclass}
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
