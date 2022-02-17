<template>
  <div>
    <el-dialog custom-class="login-dialog" title="반갑습니다!" v-model="state.dialogVisible" @close="handleClose" top="25vh">
      <el-form :model="state.form" :rules="state.rules" ref="loginForm" :label-position="state.form.align">
        <el-form-item prop="id" label="아이디" :label-width="state.formLabelWidth" >
          <el-input v-model="state.form.id" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="password" label="비밀번호" :label-width="state.formLabelWidth">
          <el-input v-model="state.form.password" autocomplete="off" show-password></el-input>
        </el-form-item>
      </el-form>

      <el-row class="row-btn" style="margin-top: 40px">
        <el-button @click="clickStLogin" style="width: 48%">학생 로그인</el-button>
        <el-button @click="clickTchrLogin" style="width: 48%">교사 로그인</el-button>
      </el-row>
      <el-row class="row-btn-text">
        <el-button @click="clickFindid" type="text">회원 ID 찾기</el-button>
        <el-button @click="clickChangePassword" type="text">비밀번호 재설정</el-button>
      </el-row>
      <el-row class="row-btn">
        <el-button @click="clickSignup" style="width: 100%">회원가입</el-button>
      </el-row>
    </el-dialog>
  </div>
</template>
<style>
.login-dialog {
  width: 400px !important;
  height: auto;
  border-radius: 30px;
}
.login-dialog .el-dialog__footer {
  margin-left: 0 calc(50% - 100px);
  padding-top: 0;
  display: inline-block;
}
.login-dialog .dialog-footer .el-button {
  width: 130px;
  margin-top: 10px;
}
.row-btn {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 10px;
}
.row-btn-text {
  display: flex;
  align-items: center;
  justify-content: space-around;
  margin-bottom: 15px;
}
.row-btn .el-button {
  background-color: #91847A;
  color: #fff;
  height: 45px;
  border-radius: 15px;
}
</style>
<script>
import { reactive, computed, ref} from 'vue'
import { useStore } from 'vuex'

export default {
  name: 'login-dialog',

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
      form: {
        id: '',
        password: '',
        align: 'left'
      },
      rules: {
        id: [
          { required: true, message: 'Please input ID', trigger: 'blur' }
        ],
        password: [
          { required: true, message: 'Please input password', trigger: 'blur' }
        ]
      },
      dialogVisible: computed(() => props.open),
      formLabelWidth: '120px'
    })
    const clickStLogin = function () {
      loginForm.value.validate((valid) => {
        if (valid) {
          store.dispatch('root/requestStLogin', { st_id: state.form.id, st_password: state.form.password })
          .then(function (result) {
            localStorage.setItem('jwt',result.data.accessToken)
            store.commit('root/checkStudent')
            store.commit('root/registerUser',state.form.id)
            store.commit('root/jwtToken',result.data.accessToken)
            handleClose()
            emit('login')
          })
          .catch(function (err) {
            alert('아이디/비밀번호를 확인하세요')
          })
        } else {
          alert('아이디/비밀번호를 확인하세요')
        }
      });
    }
    const clickTchrLogin = function () {
      loginForm.value.validate((valid) => {
        if (valid) {
          store.dispatch('root/requestTchrLogin', { tchr_id: state.form.id, tchr_password: state.form.password })
          .then(function (result) {
            localStorage.setItem('jwt',result.data.accessToken)
            store.commit('root/checkTeacher')
            store.commit('root/registerUser',state.form.id)
            store.commit('root/jwtToken',result.data.accessToken)
            handleClose()
            emit('login')
          })
          .catch(function (err) {
            alert('아이디/비밀번호를 확인하세요')
          })
        } else {
          alert('아이디/비밀번호를 확인하세요')
        }
      });
    }
    const clickSignup = () => {
      handleClose()
      emit('openSignupDialog')
    }
    const clickFindid = () => {
      handleClose()
      emit('openFindidDialog')
    }
    const clickChangePassword = () => {
      handleClose()
      emit('openChangePasswordDialog')
    }
    const handleClose = function () {
      state.form.id = ''
      state.form.password = ''
      emit('closeLoginDialog')
    }

    return { loginForm, state, clickStLogin, clickTchrLogin, clickSignup, handleClose, clickFindid, clickChangePassword }
  },
}
</script>
