<template>
  <div>
    <el-dialog custom-class="login-dialog" title="비밀번호 재설정" v-model="state.dialogVisible" @close="handleClose">
      <el-form :model="state.form" :rules="state.rules" ref="loginForm" :label-position="state.form.align">
        <el-form-item prop="id" label="ID" :label-width="state.formLabelWidth" >
          <el-input v-model="state.form.id" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="password" label="새 비밀번호" :label-width="state.formLabelWidth">
          <el-input v-model="state.form.newPassword" autocomplete="off" show-password></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="clickChangeStPassword" type="text">비밀번호 재설정(학생)</el-button>
          <el-button @click="clickChangeTchrPassword" type="text">비밀번호 재설정(교사)</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>
<style>
.login-dialog {
  width: 400px !important;
  height: auto;
  border-radius: 30px;
}
.login-dialog .el-dialog__headerbtn {
  float: right;
}
.login-dialog .el-form-item__content {
  margin-left: 0 !important;
  float: right;
  width: 200px;
  display: inline-block;
}
.login-dialog .el-form-item {
  margin-bottom: 20px;
}
.login-dialog .el-form-item__error {
  font-size: 12px;
  color: red;
}
.login-dialog .el-input__suffix {
  display: none;
}
.login-dialog .el-dialog__footer {
  margin: 0 calc(50% - 80px);
  padding-top: 0;
  display: inline-block;
}
.login-dialog .dialog-footer .el-button {
  width: 120px;
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
