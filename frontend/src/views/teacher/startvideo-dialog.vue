<template>
  <div>
    <el-dialog custom-class="findid-dialog" title="화상 수업 등록" v-model="state.dialogVisible" @close="handleClose" top="30vh">
      <el-form :model="state.form" :rules="state.rules" ref="loginForm" :label-position="state.form.align">
        <el-form-item prop="id" label="수업" :label-width="state.formLabelWidth" >
          <el-input v-model="state.form.classname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="password" label="시작시간" :label-width="state.formLabelWidth">
          <el-input v-model="state.form.starttime" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="password" label="종료시간" :label-width="state.formLabelWidth">
          <el-input v-model="state.form.endtime" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="password" label="출석인정시간" :label-width="state.formLabelWidth">
          <el-input v-model="state.form.atdtime" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <el-row class="row-btn" style="margin-top: 40px">
          <el-button @click="EnrollClass" style="width: 45%; border-radius: 15px">등록</el-button>
      </el-row>
    </el-dialog>
  </div>
</template>
<script>
import { reactive, computed, ref, onMounted } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: 'startvideo-dialog',

  props: {
    open: {
      type: Boolean,
      default: false
    }
  },

  setup(props, { emit }) {
    const router = useRouter()
    const store = useStore()
    const loginForm = ref(null)

    const state = reactive({
      form: {
        classname: '',
        starttime: '',
        endtime: '',
        atdtime: '',
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
      emit('closeVideoDialog')
    }
    const EnrollClass = function () {
    //   // 수정 필요
    //   store.dispatch('root/requestFindid',
    //    {stEmail: state.form.classname,
        // stName: state.form.starttime })
        // stName: state.form.endtime })
        // stName: state.form.atdtime })
    //   .then(function (result) {
    //     alert('화상 수업 등록')
    //     handleClose()
    //   })
    //   .catch(function (err) {
    //     alert(err)
    //   })
      console.log('등록')
    }


    return { loginForm, state, handleClose, EnrollClass }
  },

}
</script>
