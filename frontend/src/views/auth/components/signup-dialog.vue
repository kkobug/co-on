<template>
<!-- 소속, 직책, 이름 -->
  <div>
    <el-dialog custom-class="signup-dialog" title="회원가입" v-model="state.dialogVisible" @close="handleClose">
      <el-form :model="state.form" :rules="state.rules" ref="signupForm" :label-position="state.form.align">
        <el-form-item prop="id" label="아이디" :label-width="state.formLabelWidth" >
          <el-input v-model="state.form.id" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="password" label="비밀번호" :label-width="state.formLabelWidth">
          <el-input v-model="state.form.password" autocomplete="off" show-password></el-input>
        </el-form-item>
        <el-form-item prop="passwordCheck" label="이메일" :label-width="state.formLabelWidth">
          <el-input v-model="state.form.email" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="department" label="학교" :label-width="state.formLabelWidth">
          <el-input v-model="state.form.school" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="position" label="연락처" :label-width="state.formLabelWidth">
          <el-input v-model="state.form.contact" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="name" label="이름" :label-width="state.formLabelWidth">
          <el-input v-model="state.form.name" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="clickStSignup">회원가입(학생)</el-button>
          <el-button @click="clickTchrSignup">회원가입(교사)</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>
<style>
.signup-dialog {
  width: 400px !important;
  height: 500px;
  border-radius: 30px;
}
.signup-dialog .el-dialog__headerbtn {
  float: right;
}
.signup-dialog .el-form-item__content {
  margin-left: 0 !important;
  float: right;
  width: 200px;
  display: inline-block;
}
.signup-dialog .el-form-item {
  margin-bottom: 20px;
}
.signup-dialog .el-form-item__error {
  font-size: 12px;
  color: red;
}
.signup-dialog .el-input__suffix {
  display: none;
}
.signup-dialog .el-dialog__footer {
  margin: 0 calc(50% - 80px);
  padding-top: 0;
  display: inline-block;
}
.signup-dialog .dialog-footer .el-button {
  width: 120px;
}
</style>
<script>
import { reactive, computed, ref, onMounted } from 'vue'
import { useStore } from 'vuex'

export default {
  name: 'signup-dialog',

  props: {
    open: {
      type: Boolean,
      default: false
    }
  },

  setup(props, { emit }) {
    const store = useStore()
    // 마운드 이후 바인딩 될 예정 - 컨텍스트에 노출시켜야함. <return>
    const signupForm = ref(null)

    /*
      // Element UI Validator
      // rules의 객체 키 값과 form의 객체 키 값이 같아야 매칭되어 적용됨
      //
    */
    const state = reactive({
      form: {
        id: '',
        password: '',
        email: '',
        school: '',
        contact: '',
        name: '',
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

    const clickStSignup = function () {
      // 로그인 클릭 시 validate 체크 후 그 결과 값에 따라, 로그인 API 호출 또는 경고창 표시
      signupForm.value.validate((valid) => {
        if (valid) {
          store.dispatch('root/requestStSignup', {
            st_contact: state.form.contact,
            st_email: state.form.email,
            st_id: state.form.id,
            st_name: state.form.name,
            st_password: state.form.password,
            st_school: state.form.school,
          })
          .then(function (result) {
            // alert('accessToken: ' + result.data.accessToken)
            alert('회원가입 성공')
            handleClose()
          })
          .catch(function (err) {
            alert(err)
          })
        } else {
          alert('Validate error!')
        }
      });
    }

    const clickTchrSignup = function () {
      // 로그인 클릭 시 validate 체크 후 그 결과 값에 따라, 로그인 API 호출 또는 경고창 표시
      signupForm.value.validate((valid) => {
        if (valid) {
          store.dispatch('root/requestTchrSignup', {
            tchr_contact: state.form.contact,
            tchr_email: state.form.email,
            tchr_id: state.form.id,
            tchr_name: state.form.name,
            tchr_password: state.form.password,
            tchr_school: state.form.school,
          })
          .then(function (result) {
            // alert('accessToken: ' + result.data.accessToken)
            alert('교사 회원가입 성공')
            handleClose()
          })
          .catch(function (err) {
            alert(err)
          })
        } else {
          alert('Validate error!')
        }
      });
    }

    const handleClose = function () {
      state.form.id = ''
      state.form.password = '',
      state.form.email = '',
      state.form.school = '',
      state.form.contact = '',
      state.form.name = '',
      emit('closeSignupDialog')
    }

    return { signupForm, state, clickStSignup, clickTchrSignup, handleClose }
  }
}
</script>
