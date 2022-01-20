<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <el-row >
          <el-col :span="6"><div class="grid-content bg-purple">과목명</div></el-col>
          <el-col :span="6"><div class="grid-content bg-purple-light">교사</div></el-col>
          <el-col :span="6"><div class="grid-content bg-purple">수업설명</div></el-col>
          <el-col :span="6"><div class="grid-content bg-purple-light">화상회의</div></el-col>
        </el-row>
      </el-header>
      <el-main>
        <el-row v-for="item in this.object" :key="item.id">
          <el-col :span="6"><div class="grid-content bg-purple">{{item.id}}</div></el-col>
          <el-col :span="6"><div class="grid-content bg-purple-light">{{item.s}}</div></el-col>
          <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>
          <el-col :span="6"><div class="grid-content bg-purple-light" @click="moveLesson()">이동: 일단 아무데나 라우터 걸어둠</div></el-col>
        </el-row>

      </el-main>
      <el-footer>Footer</el-footer>
    </el-container>

  </div>
</template>

<script>
import { onMounted } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: 'Lesson',
  // data() {
  //    const object = [
  //     {id:1,s:'How to do lists in Vue'},
  //     {id:2,s:'How to do lists in Vue'},
  //     {id:3,s:'How to do lists in Vue'}
  //   ]
  //   return {object}
  // },
  setup () {
    const router = useRouter()
    const store = useStore()
    const object = [
      {id:1,s:'How to do lists in Vue'},
      {id:2,s:'How to do lists in Vue'},
      {id:3,s:'How to do lists in Vue'}
    ]

    function moveLesson(){
      router.push({
        name:"Tchr_Lesson"
      })
    }

    // 페이지 진입시 불리는 훅
    onMounted (() => {
      store.commit('root/setMenuActiveMenuName', 'history')
    })
    return {object,moveLesson}
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
