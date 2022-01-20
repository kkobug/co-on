<template>
  <el-container>
    안녕하세요
  </el-container>
  <div v-for= "scha in state.tchr_scha" :key=scha.id @click="MoveLesson(scha.id)">{{scha.title}}</div>
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
      tchr_scha:[
        {
          id: 1,
          title:"수학",
        },
        {
          id: 2,
          title:"과학",
        },
        {
          id: 3,
          title:"영어",
        },
      ],
    })
    const MoveLesson = function(idx){
      router.push({
        name: 'Tchr_Lesson',
        params:{id:idx},
      })
    }
    return {state, MoveLesson}
  },
}
</script>
