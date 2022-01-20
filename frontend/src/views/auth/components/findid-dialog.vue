<template>
  <div>
    <el-dialog custom-class="login-dialog" title="ID 찾기" v-model="state.dialogVisible" @close="handleClose">
      <el-form :model="state.form" :rules="state.rules" ref="loginForm" :label-position="state.form.align">
        <el-form-item prop="id" label="이메일" :label-width="state.formLabelWidth" >
          <el-input v-model="state.form.email" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="password" label="이름" :label-width="state.formLabelWidth">
          <el-input v-model="state.form.name" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="clickFindid">ID 찾기</el-button>
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
  name: 'findid-dialog',

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
        email: '',
        name: '',
        align: 'left'
      },
      dialogVisible: computed(() => props.open),
      formLabelWidth: '120px'
    })

    onMounted(() => {
      // console.log(loginForm.value)
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
        alert('id 찾기 결과 : 성공')
        handleClose()
      })
      .catch(function (err) {
        alert(err)
      })

    }


    return { loginForm, state, handleClose, clickFindid, clickLogin }
  },

}
</script>
