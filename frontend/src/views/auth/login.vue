<template>
  <div id="login_page">
    <div style="height:5vh; display:flex;justify-content: space-between;align-items:center" >
      <div>Co-on</div>
      <div>
        <el-button color="#626aef" @click="clickSignup" plain>Sign up</el-button>
        <el-button type="primary" @click="clickLogin" plain style="margin:3px;height:auto">Log in</el-button>
      </div>
    </div>
    <div >
      <el-carousel height="95vh" :interval="5000" direction="vertical" ref="carousel">
        <el-carousel-item v-for="item in 4" :key="item">

          <div v-if="item===1" class="container">
            <div style="margin:20px; width:40vw">
              <h3>안녕하십니까!</h3>
              <h3> 교육용 화상회의 플랫폼</h3>
              <div style="height:5vh"></div>
              <h3> "Co-On" 입니다!!</h3>
            </div>
            <div style="height:96vh; display: flex;
              justify-content: center;
              align-items:center ;
              width:40vw
              ">
              <img :src="require('@/assets/images/Online_Study.png')">
            </div>
          </div >

          <div v-else-if="item===2" class="container">

            <div style="height:96vh; display: flex;
              justify-content: center;
              align-items:center ;
              width:40vw
              ">
              <img :src="require('@/assets/images/mask_student.png')" style="width:40vw">
            </div>
            <div style="display:flex;flex-direction:column;align-items:center ;">
              <h3>코로나 시대,</h3>
              <h3>늘어나는 온라인수업</h3>
              <hr style="width:40vw; color:#4a00e0; border:thin solid">
              <p style="font-size:50px; width:30vW">쉽게 온라인 수업을 열고 학생들의 출결과 과제를 관리할 수 있다면 어떨까요?</p>
            </div>
          </div>

          <div v-else-if="item===3" class="container">
            <div style="height:96vh; display: flex;
              justify-content: center;
              flex-direction:column;
              align-items:center ;">
              <div style="height:25vh;display: flex;justify-content: center;align-items:center ;"><h3> 과제&공지관리</h3></div>
              <img :src="require('@/assets/images/선생님.png')" style="height:30vh;margin:10px; box-shadow: 12px 12px 2px 1px rgba(0, 0, 255,.2)">
              <div style="height:25vh;display: flex;justify-content: center;align-items:center ;"><h3> 화상회의</h3></div>
            </div>
            <div style="width:6vw"></div>
            <div style="display:inline-block;">
              <img :src="require('@/assets/images/공지&과제.png')" style="height:30vh;margin:10px; box-shadow: 12px 12px 2px 1px rgba(0, 0, 255,.2)">
              <div style="height:20vh;display: flex;justify-content: center;align-items:center ;"><h3>메인 UI</h3></div>
              <img :src="require('@/assets/images/conference.png')" style="height:30vh;margin:10px; box-shadow: 12px 12px 2px 1px rgba(0, 0, 255,.2)">
            </div>
          </div>
          <div v-else-if="item==4">
            <div style="height:76vh; flex-direction: column" class="container bg_img">
              <div style="font-size:30px;margin-bottom:55px;font-weight:bold;color:white;">이제 시작해볼까요?</div>
              <el-button class="btn" round plain><div style="padding:5px;font-family: 'Poppins', sans-serif;" @click="clickLogin">Start Co-on</div></el-button>
            </div>
            <div style="background-color:#43483E; height:20vh;">
              <div style="margin-left:40px;display:flex;justify-content: space-between;" >
                <div>
                  <div style="font-size:20px;margin-top:10px;margin-bottom:10px;font-weight:bold">만든이</div>
                  <div style="display: flex;align-items:center;">
                    <div style="margin-right:10px;font-size:20px">
                      <p>팀장/backend: 양지훈</p>
                      <p>backend: 김윤지</p>
                      <p>backend: 차상훈</p>
                    </div>
                    <div style="margin-left:10px;font-size:20px">
                      <p>frontend: 이상엽</p>
                      <p>frontend: 이승훈</p>
                      <p>frontend: 정인수</p>
                    </div>
                  </div>
                </div>
                <div>
                  <img :src="require('@/assets/images/github.png')" style="width:30px; margin:5px">
                  <img :src="require('@/assets/images/youtube.png')" style="width:30px; margin:5px">
                  <img :src="require('@/assets/images/instagram.png')" style="width:30px; margin:5px">
                  <img :src="require('@/assets/images/facebook.png')" style="width:30px; margin:5px">
                </div>
              </div>
            </div>
          </div>
        </el-carousel-item>
      </el-carousel>
    </div>
  </div>
</template>
<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@500&display=swap');
.bg_img{
  background-image: url("../../assets/images/twinkle.gif");
  /* opacity: 0.9; */
}
.btn{
  background:linear-gradient(to right, rgb(172, 182, 229), rgb(134, 253, 232));
  font-size: 30px;
  height: auto;
  padding: 10px;
}
.container {
	display: flex;
  justify-content: center;
  align-items:center ;
}
.el-carousel__item h3 {
  color: white;
  font-size: 7vh;
  opacity: 0.75;
  line-height: 10vh;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(n) {
  background: linear-gradient(to right, rgb(86, 204, 242), rgb(47, 128, 237));
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
      scrollPostion : 0,
      btnEvent : false
    }
  },
  mounted() {
    window.scrollTo(0, 0);
    document.addEventListener('wheel', this.wheelEvents);
  },
  unmounted() {
    document.removeEventListener('wheel', this.wheelEvents);
  },
  methods: {
    // 위화감을 느끼지 않을 200ms 간격으로 scroll 데이터를 변경하는 예
    wheelEvents() {
      this.$refs.carousel.next()
    },
    btn_off() {
      console.log(32312)
      this.btnEvent=true
    },
    btn_on() {
      console.log(32312)
      this.btnEvent=false
    },
  },
  setup(props, { emit }) {
    const store = useStore()
    const router = useRouter()
    const clickLogin = () => {
      emit('openLoginDialog')
    }
    const clickSignup = () => {
      emit('openSignupDialog')
    }
    const changeCollapse = () => {
      state.isCollapse = !state.isCollapse
    }
    return {clickLogin, changeCollapse, clickSignup}
  }
}
</script>
