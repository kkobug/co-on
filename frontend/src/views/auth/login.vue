<template>
  <div id="login_page">
    <div style="height:4vh" @click="clickLogin">로그인</div>
    <!-- <div v-scroll:#login_page="handleScroll">
      <el-carousel height="96vh" :interval="3000" direction="vertical" >
        <el-carousel-item v-for="item in 4" :key="item">
          <h3>{{ item }}</h3>
        </el-carousel-item>
      </el-carousel>
    </div> -->
  </div>
</template>
<style scoped>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 100vh;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
#loginwall{
  width: 100vw;
  height: 100vh;
}

.infinite-list {
  padding-left: 0;
  max-height: calc(100% - 35px);
}

@media (min-width: 701px) and (max-width: 1269px) {
  .infinite-list {
    min-width: 700px;
  }
}

@media (min-width: 1270px) {
  .infinite-list {
    min-width: 1021px;
  }
}

.infinite-list .infinite-list-item {
  min-width: 335px;
  max-width: 25%;
  display: inline-block;
  cursor: pointer;
}
</style>
<script>
import { reactive } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: 'Login',
  data() {
    return {
      scrollPostion : 0
    }
  },
  method: {
    handleScroll(e){ this.scrollPostion = e.target.scrollTop;
      if(this.scrollPosition > 100){
        console.log("UP")
      } else {
        console.log("DOWN")
      }
    }
  },
  setup(props, { emit }) {
    const store = useStore()
    const router = useRouter()
    const clickLogin = () => {
      emit('openLoginDialog')
    }

    const changeCollapse = () => {
      state.isCollapse = !state.isCollapse
    }
    return {clickLogin, changeCollapse}
  }
}
</script>
