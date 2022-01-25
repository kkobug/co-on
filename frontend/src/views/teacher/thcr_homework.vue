<template>
  <div>
    <ModalView v-if ="state.isVisible" @close-modal="state.isVisible = false"></ModalView>
    <button @click="state.isVisible = true">숙제 생성</button>
    <div>
      <div v-for = "hw in state.homeworks" :key = hw.id class ="el-item">
        <div class="li-left li-sec">
          <div class ="li-title li-item">{{hw.title}}</div>
          <div class ="li-lesson li-item">{{hw.subject}}</div>
        </div>
        <div class="li-right li-sec">
          <div class ="li-time li-item">{{hw.date}}</div>
          <div class ="li-button li-item">수정</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ModalView from "./homework_modal"
import { reactive, computed, ref, onMounted } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
export default {
  name: 'Tchr_homework',
  components:{
    ModalView,
  },
  setup() {
    const router = useRouter()
    const store = useStore()
    const state = reactive({
      isVisible :false,
      homeworks:[
        {
          id: 1,
          title: "멋들어진 제목1",
          subject: "수학",
          date: "2022-01-19",
          contents: "",
        },
        {
          id: 2,
          title: "멋들어진 제목1",
          subject: "사학",
          date: "2022-01-19",
          contents: "",
        },
        {
          id: 3,
          title: "멋들어진 제목1",
          subject: "음악",
          date: "2022-01-19",
          contents: "",
        },
      ]
    })

    return {state}
  },
};
</script>
<style scoped>
*, html, body {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
.el-item{
  display: flex;
  justify-content: space-between;
  border: solid 1px black;
  align-items: center;
  border-radius: 10px;
  height: 100px;
}
.li-sec{
  display: flex;
}
.li-item{
  margin: 20px;
}
</style>
