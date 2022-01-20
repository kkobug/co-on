<template>
  <el-row :gutter="20">
    <el-col :span="16" :offset="8">
      <el-container>
        <el-form label-width="70px" label-position="left">
          <span>
            <strong style="font-size: xx-large">{{ username }}님의 회원 정보</strong>
          </span>
          <el-form-item prop="name" label="이름" >
            <el-input v-model="state.form.name" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item prop="passwordCheck" label="이메일" >
            <el-input v-model="state.form.email" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item prop="department" label="학교" >
            <el-input v-model="state.form.school" autocomplete="off"></el-input>
          </el-form-item>
          <!-- <el-form-item label="학교">
            <el-col :span="18">
              <el-input></el-input>
            </el-col>
            <el-col :span="4">
              <el-button round style="background-color: #6B3BE3; color: white">찾기</el-button>
            </el-col>
          </el-form-item> -->
          <el-form-item label="학번">
            <el-input></el-input>
          </el-form-item>
          <el-form-item prop="id" label="아이디" >
            <el-input v-model="state.form.id" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item prop="position" label="연락처" >
            <el-input v-model="state.form.contact" autocomplete="off"></el-input>
          </el-form-item>
          <el-row :gutter="20">
            <el-col :span="8">비밀번호 변경</el-col>
            <el-col :span="6" :offset="10">계정 탈퇴</el-col>
          </el-row>
          <el-button
            style="background-color: #6B3BE3; color: white; width: 60%; height: 40px; margin-top: 20px"
            @click="clickModifystudent"
            >정보수정</el-button>
        </el-form>
      </el-container>
    </el-col>
  </el-row>
</template>

<script>
import { onMounted,reactive,computed } from 'vue'
import { useStore } from 'vuex'
import $axios from 'axios'

export default {
  name: 'History',
  setup () {
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
      formLabelWidth: '120px',
    })
    const store = useStore()
    const username = store.state.root.userid
    // const modifyInfo = function(){
    //   store.dispatch('root/requestModifystudent',{
    //         st_contact: state.form.contact,
    //         st_email: state.form.email,
    //         st_id: state.form.id,
    //         st_name: state.form.name,
    //         st_password: state.form.password, //이게 왜 있는 거임
    //         st_school: state.form.school,
    //       })
    //       .then(function (result) {
    //         alert('정보 수정 성공')
    //       })
    //       .catch(function (err) {
    //         alert(err)
    //       })
    // }
    const clickModifystudent = function () {
      store.dispatch('root/requestModifystudent', {
          st_contact: state.form.contact,
          st_email: state.form.email,
          st_id: state.form.id,
          st_name: state.form.name,
          st_password: state.form.password, //이게 왜 있는 거임
          st_school: state.form.school,
      })
      .then(function (result) {
        alert('정보 수정 성공')
      })
      .catch(function (err) {
        alert(err)
      })
    }

    const clickDeletestudent = function () {
      store.dispatch('root/requestDeleteuser', {
          st_contact: state.form.contact,
          st_email: state.form.email,
          st_id: state.form.id,
          st_name: state.form.name,
          st_password: state.form.password, //이게 왜 있는 거임
          st_school: state.form.school,
      })
      .then(function (result) {
        alert('정보 수정 성공')
      })
      .catch(function (err) {
        alert(err)
      })

    }

      // 페이지 진입시 불리는 훅
    onMounted (() => {
      store.commit('root/setMenuActiveMenuName', 'history')
      // store.dispatch('root/requestLookupstudent')
      console.log(store.state.root.userid, store.state.root.whetherTchr)
      if (store.state.root.whetherTchr) {
        $axios.get(`/teacher/${store.state.root.userid}?tchrId=` + store.state.root.userid )
        .then(res => {
          console.log(res.data)
        })
      } else {
        $axios.get(`/student/${store.state.root.userid}?stId=` + store.state.root.userid )
        .then(res => {
          console.log(res.data)
        })
      }
    })

    return {state,clickModifystudent,clickDeletestudent, username}
  },
  // created: function(){
  //   this.$store.dispatch('root/requestModifystudent')
  // },
}
</script>
