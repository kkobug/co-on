<template>
  <el-container>
    안녕하세요
  </el-container>
  <div v-for= "(val, idx) in state.tchr_scha" :key=idx @click="MoveLesson(val)">{{val}}</div>
  <ModalView v-if ="state.isVisible" @close-modal="state.isVisible = false"></ModalView>
  <button @click="state.isVisible=true">수업생성</button>
</template>
<script>
import ModalView from "./tchr_create_lesson"
import { reactive, computed, ref, onMounted } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: 'tchr_main',
  components:{
    ModalView
  },
  setup() {
    const router = useRouter()
    const store = useStore()
    const state = reactive({
      isVisible: false,
      tchr_scha: {},
    })
    const MoveLesson = function(idx){
      store.commit('root/changeClassName', idx)
      router.push({
        name: 'Tchr_Lesson',
      })
    }
    onMounted(()=>{
      store.dispatch('root/requestGetTchrClass', {
          tchrId: store.state.root.userid})
      .then(res =>{
        console.log(res.data)
        state.tchr_scha = res.data
      })
    })

    return {state, MoveLesson, onMounted}
  },
}
</script>
