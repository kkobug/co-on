<template>
  <div>
    <el-dialog custom-class="login-dialog" title="ID 찾기" v-model="state.dialogVisible" @close="handleClose">
      <el-form :model="state.form" :rules="state.rules" ref="loginForm" :label-position="state.form.align">
        <el-form-item prop="id" label="과제명" :label-width="state.formLabelWidth" >
          <el-input v-model="state.form.hwname" autocomplete="off"></el-input>
        </el-form-item>

      </el-form>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="date" label="Date" width="180" />
        <el-table-column prop="name" label="Name" width="180" />
        <el-table-column prop="address" label="Address" />
      </el-table>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="submit">제출</el-button>
          <el-button @click="cancle">취소</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>
<style>
.login-dialog {
  width: 600px !important;
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
  name: 'hw-dialog',

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
    // const loginForm = ref(null)

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

    const tableData = [
      {
        date: '2016-05-03',
        name: 'Tom',
        address: 'No. 189, Grove St, Los Angeles',
      },
      {
        date: '2016-05-02',
        name: 'Tom',
        address: 'No. 189, Grove St, Los Angeles',
      },
      {
        date: '2016-05-04',
        name: 'Tom',
        address: 'No. 189, Grove St, Los Angeles',
      },
      {
        date: '2016-05-01',
        name: 'Tom',
        address: 'No. 189, Grove St, Los Angeles',
      },
    ]

    onMounted(() => {
      // console.log(loginForm.value)
    })

    const handleClose = function () {
      // state.form.email = ''
      // state.form.name = ''
      emit('closeHwDialog')
    }
    const cancle = () => {
      emit('closeHwDialog')
    }
    const submit = function () {
      store.dispatch('root/requestFindid', {stEmail: state.form.email, stName: state.form.name })
      .then(function (result) {
        alert('제출 성공')
        handleClose()
      })
      .catch(function (err) {
        alert(err)
      })
    }



    return { state, handleClose, submit, cancle, tableData }
  },

}
</script>
