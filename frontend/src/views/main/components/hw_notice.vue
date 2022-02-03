<template>
<<<<<<< HEAD
  <el-row>
    <el-col :span="20" :offset="2">
      <div class="common-layout">
        <!-- 공지사항 -->
        <h1 style="font-size: 25px; height: 4vh">공지사항</h1>
        <el-container style="height: 38vh">
          <el-main style="background-color: #fff; line-height: 100px">
            <el-row v-for="item in state.notice" :key="item.noticeId" style="background-color: #ecf0f1; border-radius: 20px">
              <el-col :span="3" style="border-radius: 20px">{{item.noticeId}}</el-col>
              <el-col :span="21">
                <div style="border-radius: 20px">
                  <el-popover
                    placement="bottom"
                    title="Title"
                    :width="200"
                    trigger="click"
                    content="item.noticeContent"
                  >
                    <template #reference>
                      <el-button type="text">{{item.noticeTitle}}</el-button>
                    </template>
                  </el-popover>
=======
  <div class="common-layout">
    <!-- 공지사항 -->
    <el-container>
      <el-header>공지사항</el-header>
      <el-main v-if="this.notices">
        <el-row v-for="notice in this.notices" :key="notice">
          <el-col :span="6"><div class="grid-content bg-purple-light">{{notice.noticeTitle}}</div></el-col>
          <el-col :span="18">
            <div class="grid-content bg-purple-light">
              <el-popover
                placement="bottom"
                title="Title"
                :width="200"
                trigger="click"
                content={{notice.noticeContent}}
              >
                <template #reference>
                  <el-button>{{notice.noticeContent}}</el-button>
                </template>
              </el-popover>
>>>>>>> feature/BE/conference

                </div>
              </el-col>

<<<<<<< HEAD
            </el-row>
          </el-main>
        </el-container>

        <!-- 과제 -->
        <h1 style="font-size: 25px; height: 4vh">과제목록</h1>
        <el-container style="height: 38vh">
          <el-main style="background-color: #fff; line-height: 100px">
            <el-row v-for="item in state.hw" :key="item.id" style="background-color: #ecf0f1; border-radius: 20px">
              <el-col :span="6"><div>{{item.hwTitle}}</div></el-col>
              <el-col :span="6"><div>{{item.studyroom.studyName}}</div></el-col>
              <el-col :span="6"><div>{{item.hwId}}</div></el-col>
              <el-col :span="6">
                <el-button type="text" @click="onOpenHwDialog(item)">
                  제출하기
                </el-button>
                <el-button type="text" @click="delStHw()">
                  삭제하기
                </el-button>
              </el-col>
=======
        </el-row>
      </el-main>
    </el-container>

    <!-- 과제 -->
    <el-container>
      <el-header>과제</el-header>
      <el-main>
        <el-row v-for="hw in this.hws" :key="hw">
          <el-col :span="3"><div class="grid-content bg-purple-light">수업명</div></el-col>
          <el-col :span="3"><div class="grid-content bg-purple">과제 제목</div></el-col>
          <el-col :span="6"><div class="grid-content bg-purple-light">과제 내용</div></el-col>
          <el-col :span="3"><div class="grid-content bg-purple">제출/채점</div></el-col>
          <el-col :span="6"><div class="grid-content bg-purple-light">제출마감기한</div></el-col>
          <el-col :span="3"><div class="grid-content bg-purple">제출하기</div>
          </el-col>
        </el-row>
        <el-row v-for="hw in this.hws" :key="hw">
          <el-col :span="3"><div class="grid-content bg-purple-light">{{hw.studyroom.studyName}}</div></el-col>
          <el-col :span="3"><div class="grid-content bg-purple">{{hw.hwTitle}}</div></el-col>
          <el-col :span="6"><div class="grid-content bg-purple-light">{{hw.hwContent}}</div></el-col>
          <el-col :span="3"><div class="grid-content bg-purple">제출/채점</div></el-col>
          <el-col :span="6"><div class="grid-content bg-purple-light">{{hw.hwDeadline}}</div></el-col>
          <el-col :span="3">
            <div class="grid-content bg-purple" @click="onOpenHwDialog()">
              제출하기
            </div>
          </el-col>
>>>>>>> feature/BE/conference

            </el-row>
          </el-main>
        </el-container>

        <!-- 과제 제출 -->
        <hw-dialog
          :open="state.hwDialogOpen"
          @closeHwDialog="onCloseHwDialog"
          v-bind:props_hw = state.props_hw
          style="box-shadow: 3px 3px 3px 3px gray;"
        />
      </div>
    </el-col>
  </el-row>
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
<<<<<<< HEAD
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
=======
  data(){
    return{
      hwDialogOpen:false,
      userId:undefined,
      notices:undefined,
      hws:undefined
    }
  },
  methods: {
    onOpenHwDialog () {
      this.hwDialogOpen = true
    },
    onCloseHwDialog () {
      this.hwDialogOpen = false
    },
    getNotice(){
      this.$store.dispatch('root/requestGetNotice',this.userId)
      .then(result =>{
        this.notices=result.data
      })
      .catch(function(err){
        alert(err)
      })
    },
    getHw(){
      this.$store.dispatch('root/requestGetSthwlist',this.userId)
      .then(result =>{
        this.hws=result.data
      })
      .catch(function(err){
        alert(err)
      })
    }
  },
  setup () {
    const router = useRouter()
    const store = useStore()
>>>>>>> feature/BE/conference

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
<<<<<<< HEAD
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
=======
    })
    return
>>>>>>> feature/BE/conference
  },
  created:function(){
    const localvuex=JSON.parse(localStorage.getItem('vuex'))
    this.userId = localvuex["root"]["userid"]
    this.getNotice()
    this.getHw()
  }
}
</script>
<style lang="scss">

</style>
