<template>
  <div>
    <el-dialog custom-class="findid-dialog" title="ID 찾기" v-model="state.dialogVisible" @close="handleClose" top="30vh">
      <el-form :model="state.form" :rules="state.rules" ref="loginForm" :label-position="state.form.align">
        <el-form-item prop="id" label="이메일" :label-width="state.formLabelWidth" >
          <el-input v-model="state.form.email" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="password" label="이름" :label-width="state.formLabelWidth">
          <el-input v-model="state.form.name" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <el-row class="row-btn" style="margin-top: 40px">
          <el-button @click="clickFindid" style="width: 45%; border-radius: 15px">ID 찾기(학생)</el-button>
          <el-button @click="clickFindTchrid" style="width: 45%; border-radius: 15px">ID 찾기(교사)</el-button>
      </el-row>
    </el-dialog>
  </div>
</template>
<style>
.findid-dialog {
  width: 400px !important;
  height: auto;
  border-radius: 30px;
}
</style>
<script>
import { reactive, computed, ref} from 'vue'
import { useStore } from 'vuex'

export default {
  name: 'findid-dialog',

  props: {
    open: {
      type: Boolean,
      default: false
    }
  },

  setup(props, { emit }) {
    const store = useStore()
    const loginForm = ref(null)
    const state = reactive({
      form: {email: '',name: '',align: 'left'},
      dialogVisible: computed(() => props.open),
      formLabelWidth: '120px'
    })
    const handleClose = function () {
      state.form.email = ''
      state.form.name = ''
      emit('closeFindidDialog')
    }
    const clickLogin = () => {
      handleClose()
      emit('openLoginDialog')
    }
    const clickFindid = function () {
      store.dispatch('root/requestFindid', {stEmail: state.form.email, stName: state.form.name })
      .then(function (result) {
        alert('등록된 이메일로 ID를 보냈습니다.')
        handleClose()
      })
      .catch(function (err) {
        alert('이메일과 이름을 다시 확인해주세요.')
      })
    }
    const clickFindTchrid = function () {
      store.dispatch('root/requestFindTchrid', {tchrEmail: state.form.email, tchrName: state.form.name })
      .then(function (result) {
        alert('등록된 이메일로 ID를 보냈습니다.')
        handleClose()
      })
      .catch(function (err) {
        alert('이메일과 이름을 다시 확인해주세요.')
      })
    }

    return { loginForm, state, handleClose, clickFindid, clickFindTchrid, clickLogin }
  },

}
</script>
