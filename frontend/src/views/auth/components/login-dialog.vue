<template>
  <div>
    <el-dialog custom-class="login-dialog" title="로그인" v-model="state.dialogVisible" @close="handleClose">
      <el-form :model="state.form" :rules="state.rules" ref="loginForm" :label-position="state.form.align">
        <el-form-item prop="id" label="아이디" :label-width="state.formLabelWidth" >
          <el-input v-model="state.form.id" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="password" label="비밀번호" :label-width="state.formLabelWidth">
          <el-input v-model="state.form.password" autocomplete="off" show-password></el-input>
        </el-form-item>
        <!-- <el-radio-group v-model="checkTchr">
          <el-radio :label="1">학생</el-radio>
          <el-radio :label="2">교사</el-radio>
        </el-radio-group> -->
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="clickStLogin">로그인(학생)</el-button>
          <el-button @click="clickTchrLogin">로그인(교사)</el-button>
          <el-button @click="clickSignup">회원가입</el-button>
          <el-button @click="handleLogin">Google ID로 로그인</el-button>
          <el-button @click="clickFindid">회원 ID 찾기</el-button>
          <el-button @click="clickChangePassword">비밀번호 재설정</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>
<style scoped>
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
  width: 130px;
}


</style>
<script>
import { reactive, computed, ref, onMounted } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: 'login-dialog',

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
    // const checkTchr = ref(1)
    /*
      // Element UI Validator
      // rules의 객체 키 값과 form의 객체 키 값이 같아야 매칭되어 적용됨
      //
    */
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

    onMounted(() => {
      // console.log(loginForm.value)
    })

    const clickStLogin = function () {
      // 로그인 클릭 시 validate 체크 후 그 결과 값에 따라, 로그인 API 호출 또는 경고창 표시
      loginForm.value.validate((valid) => {
        if (valid) {
          store.dispatch('root/requestStLogin', { st_id: state.form.id, st_password: state.form.password })
          .then(function (result) {
            alert('학생 로그인성공')
            localStorage.setItem('jwt',result.data.accessToken)
            store.commit('root/checkStudent')
            store.commit('root/registerUser',state.form.id)
            store.commit('root/jwtToken',result.data.accessToken)
            handleClose()
            emit('login')
          })
          .catch(function (err) {
            alert(err)
          })
        } else {
          alert('Validate error!')
        }
      });
    }

    const clickTchrLogin = function () {
      // 로그인 클릭 시 validate 체크 후 그 결과 값에 따라, 로그인 API 호출 또는 경고창 표시
      loginForm.value.validate((valid) => {
        if (valid) {
          store.dispatch('root/requestTchrLogin', { tchr_id: state.form.id, tchr_password: state.form.password })
          .then(function (result) {
            alert('교사 로그인성공')
            localStorage.setItem('jwt',result.data.accessToken)
            store.commit('root/checkTeacher')
            store.commit('root/registerUser',state.form.id)
            store.commit('root/jwtToken',result.data.accessToken)
            handleClose()
            emit('login')
          })
          .catch(function (err) {
            alert(err)
          })
        } else {
          alert('Validate error!')
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

  methods: {
    async handleLogin() {
      try {
        const GoogleUser = await this.$gAuth.signIn();
        console.log(GoogleUser);
      } catch (e) {
        console.error(e);
      }
    },
  },
}
</script>
