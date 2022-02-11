<template>
  <div>
    <el-dialog custom-class="findid-dialog" title="화상 수업 등록" v-model="state.dialogVisible" @close="handleClose" top="30vh">
      <el-form :model="state.form" :rules="state.rules" ref="loginForm" :label-position="state.form.align">
        <el-form-item prop="id" label="수업" :label-width="state.formLabelWidth" >
          <el-input v-model="state.form.conferenceName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="id" label="시작/종료시간" :label-width="state.formLabelWidth" >
          <el-time-picker
            v-model="state.start_endTime"
            is-range
            range-separator="To"
            start-placeholder="Start time"
            end-placeholder="End time"
          >
          </el-time-picker>
        </el-form-item>
        <el-form-item prop="password" label="출석인정시간" :label-width="state.formLabelWidth">
          <el-time-picker
            v-model="state.atdTime"
            placeholder="Arbitrary time"
          >
          </el-time-picker>
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
        align: 'left',
        conferenceName: (new Date()).toISOString().substring(0,19)+' - '+store.getters['root/getStudyName'],
        classId : computed(() => store.getters['root/getStudyId']),
        id: store.state.root.userid,
      },
      start_endTime: [new Date(), new Date()],
      atdTime: new Date(2022,1,1,0,0),
      dialogVisible: computed(() => props.open),
      formLabelWidth: '120px',
    })

    const handleClose = function () {
      state.start_endTime = ref([new Date(), new Date()]),
      state.atdTime = ref(new Date(2022,1,1,0,0)),
      emit('closeVideoDialog')
    }
    const EnrollClass = function () {
      if(!state.atdTime||!state.start_endTime){
        alert("시간을 모두 입력해주세요")
      }else{
        store.dispatch('root/requestConfCreate',
          {
            confAtt:state.atdTime.toTimeString().substring(0,2)*60 + state.atdTime.toTimeString().substring(3,5)*1,
            confEnd:state.start_endTime[1].toTimeString().substring(0,5),
            confStart:state.start_endTime[0].toTimeString().substring(0,5),
            confTitle:state.form.conferenceName,
            studyId:state.form.classId,
            tchrId:state.form.id
          }).then(function (result) {
            console.log('등록')
            handleClose()
          }).catch(function (err) {
            alert(err)
          })
        }
      }
    onMounted(()=>{
      // state.classId=store.getters['root/getStudyId']
    })
    return { state, handleClose, EnrollClass }
  },
}
</script>
