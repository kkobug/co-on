<template>
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

            </div>
          </el-col>

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

        </el-row>
      </el-main>
      <el-footer>Footer</el-footer>
    </el-container>

    <!-- 과제 제출 -->
    <hw-dialog
      :open="hwDialogOpen"
      @closeHwDialog="onCloseHwDialog"
    />
  </div>
</template>

<script>
import { onMounted } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import HwDialog from './hw-dialog.vue'

export default {
  name: 'Lesson',
  components:{
    HwDialog
  },
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

    // 페이지 진입시 불리는 훅
    onMounted (() => {
      store.commit('root/setMenuActiveMenuName', 'history')
    })
    return
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
