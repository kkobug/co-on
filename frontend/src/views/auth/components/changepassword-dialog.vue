<template>
  <div>
    <el-dialog custom-class="password-dialog" title="비밀번호 재설정" v-model="state.dialogVisible" @close="handleClose" top="30vh">
      <el-form :model="state.form" :rules="state.rules" ref="loginForm" :label-position="state.form.align">
        <el-form-item prop="id" label="ID" :label-width="state.formLabelWidth" >
          <el-input v-model="state.form.id" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="password" label="새 비밀번호" :label-width="state.formLabelWidth">
          <el-input v-model="state.form.newPassword" autocomplete="off" show-password></el-input>
        </el-form-item>
      </el-form>
      <!-- <template #footer>
        <span class="dialog-footer">
        </span>
      </template> -->
      <el-row class="row-btn" style="margin-top: 40px">
        <el-button @click="clickChangeStPassword" style="width: 45%; border-radius: 15px">비밀번호 재설정(학생)</el-button>
        <el-button @click="clickChangeTchrPassword" style="width: 45%; border-radius: 15px">비밀번호 재설정(교사)</el-button>
      </el-row>
    </el-dialog>
  </div>
</template>
<style>
.password-dialog {
  width: 400px !important;
  height: auto;
  border-radius: 30px;
}
</style>
<script>
import { reactive, computed, ref, onMounted } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: 'changePassword-dialog',

  props: {
    open: {
      type: Boolean,
      default: false
    }
  },

  setup(props, { emit }) {
    const router = useRouter()
    const store = useStore()
    // 마운드 이후 바인딩 될 예정 - 컨텍스트에 노출시켜야함. <return>
    const loginForm = ref(null)

    /*
      // Element UI Validator
      // rules의 객체 키 값과 form의 객체 키 값이 같아야 매칭되어 적용됨
      //
    */
    const state = reactive({
      form: {
        id: '',
        newPassword: '',
        align: 'left'
      },
      dialogVisible: computed(() => props.open),
      formLabelWidth: '120px'
    })

    onMounted(() => {
      // console.log(loginForm.value)
    })


    const handleClose = function () {
      state.form.id = ''
      state.form.newPassword = ''
      emit('closeChangePasswordDialog')
    }
    const clickChangeStPassword = function () {
      store.dispatch('root/requestChangeStPassword', {stId: state.form.id, stPassword: state.form.newPassword })
      .then(function (result) {
        alert('비밀번호 재설정(학생) : 성공')
        handleClose()
      })
      .catch(function (err) {
        alert(err)
      })
    }
    const clickChangeTchrPassword = function () {
      store.dispatch('root/requestChangeTchrPassword', {tchrId: state.form.id, tchrPassword: state.form.newPassword })
      .then(function (result) {
        alert('비밀번호 재설정(교사) : 성공')
        handleClose()
      })
      .catch(function (err) {
        alert(err)
      })
    }


    return { loginForm, state, handleClose, clickChangeStPassword, clickChangeTchrPassword }
  },

}
</script>
