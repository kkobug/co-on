<template>
  <ModalView v-if ="isVisible" @close-modal="isVisible = false"></ModalView>
  <button @click="isVisible = true">숙제 생성</button>
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
</template>

<script>
import ModalView from "./homework_modal"
export default {
  name: 'Tchr_homework',
  components:{
    ModalView,
  },
  data() {
    return {
      isVisible:false,
    };
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
