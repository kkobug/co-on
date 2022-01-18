<template>
  <el-row :gutter="20">
    <el-col :span="16" :offset="8">
      <el-container>
        <el-form label-width="70px" label-position="left" ref="formRef" :model="form">
          <span>
            <strong style="font-size: xx-large">익명의 학생님의 회원 정보</strong>
          </span>
          <el-form-item label="이름" style="margin-top: 30px" required>
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="E-mail" required>
            <el-input v-model="form.email"></el-input>
          </el-form-item>
          <el-form-item label="학교">
            <!-- <el-col :span="18"> -->
            <el-input v-model="form.school"></el-input>
            <!-- <el-col :span="4">
              <el-button round style="background-color: #6B3BE3; color: white">찾기</el-button>
            </el-col> -->
          </el-form-item>
          <el-form-item label="학번">
            <el-input v-model="form.studentID"></el-input>
          </el-form-item>
          <el-form-item label="생일">
            <el-date-picker type="date" style="width: 100%" placeholder="Pick a date" v-model="form.birth"></el-date-picker>
          </el-form-item>
          <el-form-item label="전화번호">
            <el-input v-model="form.phone"></el-input>
          </el-form-item>
          <el-row :gutter="20">
            <el-col :span="8" style="color: #4267D6">
              <el-button type="text" style="color: #4267D6" @click="changePassword">비밀번호 변경</el-button>
            </el-col>
            <el-popconfirm title="Are you sure you want to leave?" cancel-button-text="아니요" confirm-button-text="네">
              <template #reference>
                <el-col :span="6" :offset="10">
                  <el-button type="text" style="color: red">계정 탈퇴</el-button>
                </el-col>
              </template>
            </el-popconfirm>
          </el-row>
          <el-button style="background-color: #6B3BE3; color: white; width: 60%; height: 40px; margin-top: 20px" @click="updateProfile">정보수정</el-button>
        </el-form>
      </el-container>
    </el-col>
  </el-row>
</template>

<script>
import { onMounted, reactive } from 'vue'
import { useStore } from 'vuex'

export default {
  name: 'History',
  setup () {
    const store = useStore()

    const form = reactive({
      name: '',
      email: '',
      school: '',
      studentID: '',
      birth: '',
      phone: '',
    })

    // 페이지 진입시 불리는 훅
    onMounted (() => {
      store.commit('root/setMenuActiveMenuName', 'history')
    })

    const changePassword = function () {
      alert('비밀번호 페이지로 이동')
    }

    const updateProfile = function () {
      alert('정보수정')
    }

    return { form, onMounted, changePassword, updateProfile }
  }
}
</script>
