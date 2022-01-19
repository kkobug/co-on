<template>

  <div class ="modal">
    <div class="overlay" @click="$emit('close-modal')">X</div>
    <div><p>공지 등록</p></div>
    <div><label for="title">제목</label><input name="title" type="text"></div>
    <div><label for="content">제목</label><textarea name="content" id="" cols="30" rows="10"></textarea></div>
  </div>
</template>

<script>

export default {
  name: 'ModalView',

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
