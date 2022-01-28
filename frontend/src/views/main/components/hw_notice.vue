<template>
  <div class="common-layout">
    <!-- 공지사항 -->
    <el-container>
      <p>공지사항</p>
      <el-main>
        <el-row v-for="item in state.notice" :key="item.noticeId">
          <el-col :span="6"><div class="grid-content bg-purple-light">{{item.noticeId}}</div></el-col>
          <el-col :span="18">
            <div class="grid-content bg-purple-light">
              <el-popover
                placement="bottom"
                title="Title"
                :width="200"
                trigger="click"
                content="item.noticeContent"
              >
                <template #reference>
                  <el-button>{{item.noticeTitle}}</el-button>
                </template>
              </el-popover>

            </div>
          </el-col>

        </el-row>
      </el-main>
      <el-footer>Footer</el-footer>
    </el-container>

    <!-- 과제 -->
    <el-container>
      <p>과제</p>
      <el-main>
        <el-row v-for="item in state.hw" :key="item.id">
          <el-col :span="6"><div class="grid-content bg-purple">{{item.hwTitle}}</div></el-col>
          <el-col :span="6"><div class="grid-content bg-purple-light">{{item.studyroom.studyName}}</div></el-col>
          <el-col :span="6"><div class="grid-content bg-purple">{{item.hwId}}</div></el-col>
          <el-col :span="6">
            <div class="grid-content bg-purple-light" @click="onOpenHwDialog(item)">
              제출하기
            </div>
            <div class="grid-content bg-purple-light" @click="delStHw()">
              삭제하기
            </div>
          </el-col>

        </el-row>
      </el-main>
      <el-footer>Footer</el-footer>
    </el-container>

    <!-- 과제 제출 -->
    <hw-dialog
      :open="state.hwDialogOpen"
      @closeHwDialog="onCloseHwDialog"
      v-bind:props_hw = state.props_hw
    />
  </div>
</template>

<script>
import { onMounted, reactive } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import HwDialog from './hw-dialog.vue'

export default {
  name: 'Lesson',
  components:{
    HwDialog
  },
  // data(){
  //   return{
  //     hwDialogOpen:false,
  //   }
  // },
  // methods: {
  //   onOpenHwDialog () {
  //     this.hwDialogOpen = true
  //   },
  //   onCloseHwDialog () {
  //     this.hwDialogOpen = false
  //   },
  // },
  setup () {
    const router = useRouter()
    const store = useStore()
    const state = reactive({
      notice:{},
      hw:{},
      props_hw:{},
      hwDialogOpen:false,
      notice : [],
      hw : [],
    })
    // function submitHw(){
    //   router.push({
    //     name:"Tchr_Lesson"
    //   })
    // }\

    // 페이지 진입시 불리는 훅
    const delStHw = function(){
      // store.dispatch('root/requestdelsthw' ,{
      //   stHwId: 1,
      //   stId: "st_id"
      // })
    }
    const onOpenHwDialog=function(item){
      state.props_hw = item
      state.hwDialogOpen = true
      console.log("열림", state.hwDialogOpen)
    }
    const onCloseHwDialog=function(){
      state.hwDialogOpen = false
      state.props_hw={}
    }
    onMounted (() => {
      store.commit('root/setMenuActiveMenuName', 'history')
      // 과제 불러오기
      store.dispatch('root/requestGetHW',{
        stId : store.state.root.userid
      })
      .then(function(result){
        alert('과제 조회 성공')
        console.log("homework", result.data)
        state.hw=result.data
      })
      .catch(function(err){
        alert(err)
      })
      // 공지사항 불러오기
      store.dispatch('root/requestGetNotice',{
        stId : store.state.root.userid
      })
      .then(function(result){
        console.log("notice",result.data)
        state.notice=result.data
      })
      .catch(function(err){
        alert(err)
      })
    })
    return {state, onOpenHwDialog, onCloseHwDialog, delStHw}
  },
  created:function(){
      // this.$store.dispatch('root/requestGetLesson',this.myUserName)
      // .then(function (result) {
      //     alert('수업 리스트')
      //     console.log(result)
      //     // this.object = result
      //   })
      //   .catch(function (err) {
      //     alert(err)
      //   })

      // this.$store.dispatch('root/requestGetNotice',???)
      // .then(function (result) {
      //     alert('수업 공지사항')
      //     console.log(result)
      //   })
      //   .catch(function (err) {
      //     alert(err)
      //   })

      // this.$store.dispatch('root/requestGetHW',studyId)
      // .then(function (result) {
      //     alert('수업 과제')
      //     console.log(result)
      //   })
      //   .catch(function (err) {
      //     alert(err)
      //   })
  }
}
</script>
<style lang="scss">
.common-layout {
  .el-header,
  .el-footer {
    background-color: #b3c0d1;
    color: var(--el-text-color-primary);
    text-align: center;
    line-height: 60px;
  }

  .el-footer {
    line-height: 60px;
  }

  .el-aside {
    background-color: #d3dce6;
    color: var(--el-text-color-primary);
    text-align: center;
    line-height: 200px;
  }

  .el-main {
    background-color: #e9eef3;
    color: var(--el-text-color-primary);
    text-align: center;
    line-height: 160px;
    width: 100%;
  }

  body > .el-container {
    margin-bottom: 40px;
  }

  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }

  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }
  .el-row {
    margin-bottom: 20px;
  }
  .el-row:last-child {
    margin-bottom: 0;
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
}
</style>
