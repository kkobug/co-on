<template>
  <div>
    <tchr-nav></tchr-nav>
    <div class="stud">
      <el-row>
        <el-col
        v-for="(o, index) in 3"
        :key="o"
        :span="8"
        :offset="index > 0 ? 2 : 0"
        >
          <el-card :body-style="{ padding: '0px' }">
            <img
              src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
              class="image"
            />
            <div style="padding: 14px">
              <span>Yummy hamburger</span>
              <div class="bottom">
                <el-button type="text" class="button">Operating</el-button>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>
<script>
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

import Tchr_nav from './tchr_nav.vue'

export default {
  name: 'ourClass',
  components: {
    "tchr-nav" : Tchr_nav,
  },
  methods:{
    moveClass: function(){
      this.$router.push({name:"Tchr_ourClass"})
    },
    moveAttend: function(){
      this.$router.push({name:"Tchr_attend"})
    },
    moveLesson: function(){
      this.$router.push({name:"Tchr_Lesson"})
    }
  }
}

</script>
<style scoped>
*, html, body {
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}
.topnav{
  background-color: #545c64;
  position: relative;
  color: #fff;
  display: flex;
  list-style:none;
  width: 100%;
}
.navitem{
  padding: 20px;
  border-bottom: solid 2px white;
  margin-right: 10px;
}
.navitem:hover{
  color: yellow;
  border-bottom: solid 2px yellow;
}
.lessonstr {
  right: 0;
  margin: 20px;
  position: absolute;
}
.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.button {
  padding: 0;
  min-height: auto;
}

.image {
  width: 100%;
  display: block;
}
.stud {
  display: flex;
}
</style>
