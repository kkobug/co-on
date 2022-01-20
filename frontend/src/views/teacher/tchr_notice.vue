<template>
<<<<<<< HEAD
  <ModalView v-if ="isVisible" @close-modal="isVisible = false"></ModalView>
  <button @click="isVisible = true">공지 생성</button>
  <el-container>
    <div class ="el-item">
      <div class="li-left li-sec">
        <div class ="li-title li-item">긴장하지 않는법</div>
        <div class ="li-lesson li-item">수업1</div>
      </div>
      <div class="li-right li-sec">
        <div class ="li-time li-item">2022-01-18</div>
        <div class ="li-button li-item">수정</div>
      </div>
    </div>
  </el-container>
=======
  <ModalView v-if ="state.isVisible" @close-modal="state.isVisible = false"></ModalView>
  <button @click="state.isVisible = true">공지 생성</button>
  <div>
    <div v-for = "ntice in state.notices" :key = ntice.id class ="el-item">
      <div class="li-left li-sec">
        <div class ="li-title li-item">{{ntice.title}}</div>
        <div class ="li-lesson li-item">{{ntice.subject}}</div>
      </div>
      <div class="li-right li-sec">
        <div class ="li-time li-item">{{ntice.date}}</div>
        <div class ="li-button li-item">수정</div>
      </div>
    </div>
  </div>
>>>>>>> develop_frontend
</template>

<script>
import ModalView from "./notice_modal"
<<<<<<< HEAD
=======
import { reactive, computed, ref, onMounted } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
>>>>>>> develop_frontend
export default {
  name: 'Tchr_notice',
  components:{
    ModalView,
  },
<<<<<<< HEAD
  data() {
    return {
      isVisible:false,
    };
=======
  setup() {
    const router = useRouter()
    const store = useStore()
    const state = reactive({
      isVisible :false,
      notices:[
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
>>>>>>> develop_frontend
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
