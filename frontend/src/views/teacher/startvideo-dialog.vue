<template>
  <div>
    <el-dialog custom-class="findid-dialog" title="화상 수업 등록" v-model="state.dialogVisible" @close="handleClose" top="30vh">
      <el-form :model="state.form" :rules="state.rules" ref="loginForm" :label-position="state.form.align">
        <el-form-item prop="id" label="수업" :label-width="state.formLabelWidth" >
          <el-input v-model="state.form.conferenceName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="id" label="시작/종료시간" :label-width="state.formLabelWidth" >
          <el-time-select
            v-model="state.startTime"
            :max-time="state.endTime"
            class="mr-4"
            placeholder="Start time"
            start="08:30"
            step="00:15"
            end="18:30"
          >
          </el-time-select>
          <el-time-select
            v-model="state.endTime"
            :min-time="state.startTime"
            placeholder="End time"
            start="08:30"
            step="00:15"
            end="18:30"
          >
          </el-time-select>
        </el-form-item>
        <el-form-item prop="password" label="출석인정시간" :label-width="state.formLabelWidth">
          <el-time-select
            v-model="state.atdTime"
            placeholder="attendance time"
            start="00:15"
            step="00:15"
            end="03:00"
          >
          </el-time-select>
        </el-form-item>
      </el-form>
      <el-row class="row-btn" style="margin-top: 40px">
          <el-button @click="EnrollClass" style="width: 45%; border-radius: 15px">등록</el-button>
          <el-button @click="handleClose" style="width: 45%; border-radius: 15px">취소</el-button>
      </el-row>
    </el-dialog>
  </div>
</template>
<script>
import { reactive, computed, ref, onMounted, unref } from 'vue'
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
    const state = reactive({
      form: {
        startTime: ref(''),
        endTime: ref(''),
        atdTime: '',
        align: 'left',
        conferenceName: (new Date()).toLocaleString().substring(6,).replace(' ','')+' - '+store.getters['root/getStudyName'],
        classId : computed(() => store.getters['root/getStudyId']),
        id: store.state.root.userid,
      },
      dialogVisible: computed(() => props.open),
      formLabelWidth: '120px',
    })

    const handleClose = function () {
      state.startTime = ''
      state.endTime = ''
      state.atdTime = ''
      emit('closeVideoDialog')
    }
    const EnrollClass = function () {
      console.log('시작/종료:'+unref(state.startTime)+unref(state.endTime))
      console.log('출석인정:'+unref(state.atdTime))
    //   // 수정 필요
    //   store.dispatch('root/requestFindid',
    //    {stEmail: state.form.classname})
    //   .then(function (result) {
    //     alert('화상 수업 등록')
    //     handleClose()
    //   })
    //   .catch(function (err) {
    //     alert(err)
    //   })
      console.log('등록')
      }
    onMounted(()=>{
      // state.classId=store.getters['root/getStudyId']
    })
    return { state, handleClose, EnrollClass }
  },
}
</script>
