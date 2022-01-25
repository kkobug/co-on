<template>
  <div class="common-layout">
    <!-- 공지사항 -->
    <el-container>
      <p>공지사항</p>
      <el-main>
        <el-row v-for="item in this.notice" :key="item.id">
          <el-col :span="6"><div class="grid-content bg-purple-light">{{item.id}}</div></el-col>
          <el-col :span="18">
            <div class="grid-content bg-purple-light">
              <el-popover
                placement="bottom"
                title="Title"
                :width="200"
                trigger="click"
                content="this is content, this is content, this is content"
              >
                <template #reference>
                  <el-button>{{item.s}}</el-button>
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
        <el-row v-for="item in this.hw" :key="item.id">
          <el-col :span="6"><div class="grid-content bg-purple">과제명</div></el-col>
          <el-col :span="6"><div class="grid-content bg-purple-light">과목명</div></el-col>
          <el-col :span="6"><div class="grid-content bg-purple">제출/채점</div></el-col>
          <el-col :span="6">
            <div class="grid-content bg-purple-light" @click="onOpenHwDialog()">
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
    }
  },
  methods: {
    onOpenHwDialog () {
      this.hwDialogOpen = true
    },
    onCloseHwDialog () {
      this.hwDialogOpen = false
    },
  },
  setup () {
    const router = useRouter()
    const store = useStore()
    const notice = [
      {id:1,s:'How to do lists in Vue'},
      {id:2,s:'How to do lists in Vue'},
      {id:3,s:'How to do lists in Vue'}
    ]
    const hw = [
      {id:1,s:'How to do lists in Vue'},
      {id:2,s:'How to do lists in Vue'},
      {id:3,s:'How to do lists in Vue'}
    ]
    // function submitHw(){
    //   router.push({
    //     name:"Tchr_Lesson"
    //   })
    // }\

    // 페이지 진입시 불리는 훅
    onMounted (() => {
      store.commit('root/setMenuActiveMenuName', 'history')
      // 과제 불러오기
      // store.dispatch('root/setMenuActiveMenuName')
      // .then(function(result){
      //   alert('과제 조회 성공')
      //   console.log(result)
      //   this.object=result
      // })
      // .catch(function(err){
      //   alert(err)
      // })
      // 공지사항 불러오기
      // store.dispatch('root/setMenuActiveMenuName')
      // .then(function(result){
      //   console.log(result)
      //   this.object=result
      // })
      // .catch(function(err){
      //   alert(err)
      // })
    })
    return {notice,hw}
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
