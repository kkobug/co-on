<template>
  <el-row>
    <el-col :span="22" :offset="1">
      <div>
        <el-row id="dashboard">
          <el-col :span="6">
            <el-card :body-style="{ padding: '0px' }" id="dash" shadow="always">
              <div style="padding: 14px; text-align:left; background-color:#EADDFF;height:12vh">
                <div>
                  <font-awesome-icon icon="clock" style="font-size:20px"/>
                  <span style="font-weight:bold; color:#21005D; font-size:20px">
                    곧 마감인 과제
                  </span>
                </div>
                <div class="bottom" v-if="this.dashHw">
                  <p>{{ this.dashHw.className }} / {{this.dashHw.title}}</p>
                  <p><time class="time">{{ this.dashHw.end.substring(0, 10) }}</time></p>
                </div>
                <div class="bottom" v-else>
                  <p>등록된 과제가 없습니다</p>
                </div>
              </div>
            </el-card>
          </el-col>


          <el-col :span="6">
            <el-card :body-style="{ padding: '0px' }" id="dash" shadow="always">
              <div style="padding: 14px; text-align:left;background-color:#D9E7CB;height:12vh">
                <div>
                  <font-awesome-icon icon="bell" />
                  <span style="font-weight:bold; color:#273420; font-size:20px">
                    최신 공지
                  </span>
                </div>
                <div class="bottom" v-if="this.dashNotice">
                  <p>{{this.dashNotice.noticeTitle}}</p>
                  <p><time class="time">{{ this.dashNotice.noticePosted }}</time></p>
                </div>
                <div class="bottom" v-else>
                  <p>등록된 공지가 없습니다</p>
                </div>
              </div>
            </el-card>
          </el-col>


          <el-col :span="6">
            <el-card :body-style="{ padding: '0px' }" id="dash" shadow="always">
              <div style="padding: 14px; text-align:left;background-color:#F9DEDC;height:12vh">
                <div>
                  <font-awesome-icon icon="calendar-check" />
                  <span style="font-weight:bold; color:#410E08; font-size:20px">
                    최근 종료된 과제
                  </span>
                </div>
                <div class="bottom"  v-if="this.endhw">
                  <p>{{this.endhw.title}}</p>
                  <p><time class="time">{{ this.endhw.end.substring(0, 10) }}</time></p>
                </div>
                <div class="bottom" v-else>
                  <p>기한이 지난 과제가 없습니다</p>
                </div>
              </div>
            </el-card>
          </el-col>

          <el-col v-if="!this.tchrOrNot" :span="6">
            <el-card :body-style="{ padding: '0px' }" id="dash" shadow="always">
              <div style="padding: 14px; text-align:left;background-color:#FFD8E4; height:12vh">
                <div>
                  <font-awesome-icon icon="chalkboard-teacher" />
                  <span style="font-weight:bold; color:#31111D; font-size:20px">
                    마일리지
                  </span>
                </div>
                <div class="bottom">
                  <p>{{ this.mil_title }}</p>
                  <p><time class="time">{{ this.max_mil }}</time></p>
                </div>
              </div>
            </el-card>
          </el-col>

          <el-col v-else :span="6">
            <el-card :body-style="{ padding: '0px' }" id="dash" shadow="always">
              <div style="padding: 14px; text-align:left;background-color:#FFD8E4" class="">
                <div>
                  <font-awesome-icon icon="chalkboard-teacher" />
                  <span style="font-weight:bold; color:#31111D; font-size:20px">
                    진행중인 수업
                  </span>
                </div>
                <div class="bottom">
                  <p><el-button type="text" class="button">{{ this.soonlessontitle }}</el-button></p>
                  <p><time class="time">~{{ this.soonlessontime.substring(0, 16) }}</time></p>
                </div>
              </div>
            </el-card>
          </el-col>
        </el-row>
        <el-row style="padding-bottom: 15px">
        <!-- 달력 -->
          <el-col :span="18" style="padding:12px;">
              <div class="calendar" style="margin-left: 10px; border-radius:20px; box-shadow: 0px 4px 10px 2px #C0C4CC;">
                <vue-cal
                  class="vuecal--blue-theme cal"
                  :selected-date="this.today"
                  :disable-views="['years', 'year','week','day']"
                  active-view="month"
                  events-on-month-view="short"
                  overlaps-per-time-step
                  :events="events"
                  style="height: auto; overflow: hidden; border-radius:20px;"
                >
                </vue-cal>
              </div>
          </el-col>
        <!-- 원형 그래프 -->
          <el-col :span="6" style="padding:12px;">
            <div style="margin-bottom: 4vh; margin-top: 0.5vh; border-radius: 10px; box-shadow: 0px 4px 10px 2px #C0C4CC; background-color:#F5FDFF;">
              <canvas id="myChart" style="padding-top: 10px; padding-bottom: 20px"></canvas>
              <div v-if="this.tchrOrNot">
                <h4 style="margin-top: 2px; padding-bottom: 15px;">출제한 과제 비율</h4>
              </div>
              <div v-else>
                <h4 style="margin-top: 2px; padding-bottom: 15px;">Todo Homework</h4>
              </div>
            </div>
            <div style="margin-bottom: 4vh; border-radius: 10px; box-shadow: 0px 4px 10px 2px #C0C4CC; background-color:#F5FDFF;">
              <el-progress type="dashboard" :percentage="this.percentageHW" style="padding-top: 10px; margin-top: 3%">
                <template #default="{ percentage }">
                  <span class="percentage-value">{{ percentage }}%</span>
                  <span class="percentage-label">과제 제출률</span>
                </template>
              </el-progress>
              <h4 style="margin-top: 2px; padding-bottom: 15px;">제출한 과제: {{ this.doneHW }}  /  미제출 과제: {{ this.notyetHW }}</h4>
            </div>
          </el-col>
        </el-row>
      </div>
    </el-col>
  </el-row>
</template>

<script>
import VueCal from 'vue-cal'
import "vue-cal/dist/vuecal.css";

import Chart from 'chart.js'

export default {
  name: "Calendar",
  components: {
    VueCal,
    },

  data: () => ({
    events: [],
    today:new Date(),
    dashHw:undefined,
    dashNotice:undefined,
    drawerHW: false,
    drawer: false,
    doneHW: 0,
    notyetHW: 0,
    beforeDeadHW: 0,
    afterDeadHW: 0,
    percentageHW: 0,
    myTchr: [],
    inProgressClass: [],
    progresstitle: '',
    progressend: '',
    endhw:undefined,
    max_mil:0,
    mil_title:undefined,
    soonlessontitle:"예정된 수업이 없습니다.",
    soonlessontime:"",

 }),
  methods: {
    getNotice(){
      this.$store.dispatch('root/requestGetSTNotice',this.userId)
      .then(result =>{
        this.notices=result.data
        this.dashNotice=this.notices[0]
        if (result.data.length >= 1) {
            this.dashNotice.noticePosted=this.dashNotice.noticePosted.substring(0,10)
        }
      })
    },
    getNoticeTchr(){
      this.$store.dispatch('root/requestGetTchrNotice')
      .then(result => {
        this.notices=result.data
        this.dashNotice=this.notices[0]
        if (result.data.length >= 1) {
            this.dashNotice.noticePosted=this.dashNotice.noticePosted.substring(0,10)
        }
      })
    },
    getHw(){
      this.$store.dispatch('root/requestGetHW',this.userId)
      .then(result =>{
        for (var j = 0; j < result.data.length; j++) {
          var hw = result.data[j];
          if (this.isWork(hw.hwDeadline)){
            this.dashHw = {
                start: hw.hwPosted.substring(0, 16), //"2022-02-08 14:00"
                end: hw.hwDeadline.substring(0,16),
                title: hw.hwTitle,
                className :hw.studyroom.studyName,
                content: hw.hwContent,
                teacher: hw.tchrId,
                fileName: "none",
                class: ["a","b","c","d","e","f","g"][j%3],
                split: 2
              }
          }else if(!this.isWork(hw.hwDeadline) && !this.endhw){
            this.endhw = {
                start: hw.hwPosted.substring(0, 16), //"2022-02-08 14:00"
                end: hw.hwDeadline.substring(0,16),
                title: hw.hwTitle,
                className :hw.studyroom.studyName,
                content: hw.hwContent,
                teacher: hw.tchrId,
                fileName: "none",
                class: ["a","b","c","d","e","f","g"][j%3],
                split: 2
              }
          }
          if (hw.hwDeadline > this.today) {
            if (hw.hwFile.length){
              this.events.push({
                start: hw.hwPosted.substring(0, 16), //"2022-02-08 14:00"
                end: hw.hwDeadline.substring(0,16),
                title: hw.hwTitle,
                className :hw.studyroom.studyName,
                content: hw.hwContent,
                teacher: hw.tchrId,
                fileName: hw.hwFile[0].fileOriginName,
                class: ["a","b","c","d","e","f","g"][j%3],
                split: 2
              });
            }else{
              this.events.push({
                start: hw.hwPosted.substring(0, 16), //"2022-02-08 14:00"
                end: hw.hwDeadline.substring(0,16),
                title: hw.hwTitle,
                className :hw.studyroom.studyName,
                content: hw.hwContent,
                teacher: hw.tchrId,
                fileName: "none",
                class: ["a","b","c","d","e","f","g"][j%3],
                split: 2
              });
            }
          }
        }
      })
    },
    getTchtHW(){
      this.$store.dispatch('root/requestTchrListHomework')
      .then(result =>{
        for (var j = 0; j < result.data.length; j++) {

          var hw = result.data[j];
          if (this.isWork(hw.hwDeadline)){
            this.dashHw = {
                start: hw.hwPosted.substring(0, 16), //"2022-02-08 14:00"
                end: hw.hwDeadline.substring(0,16),
                title: hw.hwTitle,
                className :hw.studyroom.studyName,
                content: hw.hwContent,
                teacher: hw.tchrId,
                fileName: "none",
                class: ["a","b","c","d","e","f","g"][j%3],
                split: 2
              }
          }else if(!this.endhw){
            this.endhw = {
                start: hw.hwPosted.substring(0, 16), //"2022-02-08 14:00"
                end: hw.hwDeadline.substring(0,16),
                title: hw.hwTitle,
                className :hw.studyroom.studyName,
                content: hw.hwContent,
                teacher: hw.tchrId,
                fileName: "none",
                class: ["a","b","c","d","e","f","g"][j%3],
                split: 2
              }
          }
          if (hw.hwDeadline > this.today) {
            if (hw.hwFile.length){
              this.events.push({
                start: hw.hwPosted.substring(0, 16),
                end: hw.hwDeadline.substring(0,16),
                title: hw.hwTitle,
                className :hw.studyroom.studyName,
                content: hw.hwContent,
                teacher: hw.tchrId,
                fileName: hw.hwFile[0].fileOriginName,
                class: ["a","b","c","d","e","f","g"][j%3],
                split: 2
              });
            }else{
              this.events.push({
                start: hw.hwPosted.substring(0, 16),
                end: hw.hwDeadline.substring(0,16),
                title: hw.hwTitle,
                className :hw.studyroom.studyName,
                content: hw.hwContent,
                teacher: hw.tchrId,
                fileName: "none",
                class: ["a","b","c","d","e","f","g"][j%3],
                split: 2
              });
            }
          }
        }
      })
    },
    getProgress(){
      this.$store.dispatch('root/requestRateHW')
      .then(result => {
        this.beforeDeadHW = result.data[0]
        this.afterDeadHW = result.data[1]
        this.notyetHW = result.data[2]
        this.doneHW = result.data[3]
        if (this.beforeDeadHW + this.afterDeadHW == 0) {
          this.percentageHW = 0
        } else {
          this.percentageHW = Math.floor(100 * this.doneHW / (this.beforeDeadHW + this.afterDeadHW))
        }
      })
    },
    getMyTchr(){
      this.$store.dispatch('root/requestGetClass')
      .then(result => {
        for (let j = 0; j < result.data.length; j++) {
          this.myTchr.push({
            studyId: result.data[j].studyId,
            tchrId: result.data[j].tchrId
          })
        }
        for (let i = 0; i < this.myTchr.length; i++) {
          this.$store.dispatch('root/requestConfAttData', {studyId: this.myTchr[i].studyId, targetDate: this.today, tchrId: this.myTchr[i].tchrId })
          .then(res => {
            const thishour = new Date().getHours()
            const thisminutes = new Date().getMinutes()
            let tmphour = ''
            let tmpmin = ''
            if (thishour < 10) {
              tmphour = '0' + thishour
            } else {
              tmphour = thishour
            }
            if (thisminutes < 10) {
              tmpmin = '0' + thisminutes
            } else {
              tmpmin = thisminutes
            }
            const thisTime = tmphour + ':' + tmpmin
            for (let k = 0; k < res.data.length; k++) {
              if (this.inProgressClass.length >= 1) {
                break
              }
              const tmpclass = res.data[k]
              if (res.data[k].confStart.substring(11, 16) < thisTime && res.data[k].confEnd.substring(11, 16) > thisTime) {
                this.inProgressClass.push(tmpclass)
                this.progresstitle = tmpclass.confTitle
                this.progressend = tmpclass.confEnd
              }
            }
          })
        }
      })
    },
    isWork(dead){
      let now = new Date();
      var year = now.getFullYear();
      var month = ('0' + (now.getMonth() + 1)).slice(-2);
      var day = ('0' + now.getDate()).slice(-2);
      var hour = now.getHours();
      var minute = now.getMinutes();
      var dateString = year + '-' + month  + '-' + day +" "+hour+":"+minute;

      return dead.substring(0, 16) >= dateString
    },
    getmil(){
      const localvuex=JSON.parse(localStorage.getItem('vuex'))
      this.$store.dispatch("root/requeststLesson2", localvuex["root"]["userid"])
      .then(res=>{
        for (var ele of res.data) {
          if (ele[0].stPoint >= this.max_mil){
            this.max_mil = ele[0].stPoint
            this.mil_title = ele[0].studyroom.studyName
          }
        }
      })
    },
    getsoon(){
      const localvuex=JSON.parse(localStorage.getItem('vuex'))
      this.$store.dispatch("root/requestSoonLesson", {tchrId : localvuex["root"]["userid"]})
      .then(res=>{
        if(res.data.length>0){
          this.soonlessontitle=res.data[0][0].studyName
          this.soonlessontime=res.data[0][1].confStart
        }
      })
    },

  },
  created:function(){
    this.getmil()
    this.getsoon()
    const localvuex=JSON.parse(localStorage.getItem('vuex'))
    this.userId = localvuex["root"]["userid"]
    this.tchrOrNot = localvuex["root"]["whetherTchr"]
    const nowyear = new Date().getFullYear()
    let nowmonth = new Date().getMonth()+1
    let nowday = new Date().getDate()
    if (nowmonth < 10){
      nowmonth = '0' + nowmonth
    }
    if (nowday < 10) {
      nowday = '0' + nowday
    }
    this.today=nowyear + '-' + nowmonth + '-' + nowday
    if (this.tchrOrNot) {
      this.getNoticeTchr()
      this.getTchtHW()
    } else {
      this.getNotice()
      this.getHw()
      this.getMyTchr()
    }
    this.getProgress()
  },
  mounted() {
      this.getmil()
      // cdn chart.js
      let recaptchaScript = document.createElement('script')
      recaptchaScript.setAttribute('src', 'https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js')
      document.head.appendChild(recaptchaScript)

      let hwlabels = []
      let hwdata = []
      if (this.tchrOrNot) {
        this.$store.dispatch('root/requestTchrListHomework')
        .then(result => {
          let hwClass = {}
          for (var j = 0; j < result.data.length; j++) {
            if (result.data[j].studyroom.studyName in hwClass) {
              hwClass[result.data[j].studyroom.studyName] += 1
            } else {
              hwClass[result.data[j].studyroom.studyName] = 1
            }
          }
          hwlabels = Object.keys(hwClass)
          for (var key in hwClass) {
            hwdata.push(hwClass[key])
          }
          if (hwlabels.length == 0) {
            hwlabels = ['과제없음']
            hwdata = [0]
          }

          const ctx = document.getElementById('myChart');
            var myChart = new Chart(ctx, {
            type: 'doughnut',
              data: {
                labels: hwlabels,
                datasets: [{
                  data: hwdata,
                  backgroundColor: [
                    '#9DCEFF',
                    '#F2F3F6',
                    '#B3261E',
                    '#21005D',
                  ],
                  borderWidth: 0,
                  scaleBeginAtZero: true,
                }
              ]
            },
          });
        })
      } else {
        this.$store.dispatch('root/requestGetHW',this.userId)
        .then(result => {
          let hwClass = {}
          for (var j = 0; j < result.data.length; j++) {
            for (var k = 0; k < result.data[j].studentHomeworks.length; k++) {
              if (result.data[j].studentHomeworks[k].stId === this.userId) {
                if (result.data[j].studentHomeworks[k].stHwposted === null) {
                  if (result.data[j].studyroom.studyName in hwClass) {
                    hwClass[result.data[j].studyroom.studyName] += 1
                  } else {
                    hwClass[result.data[j].studyroom.studyName] = 1
                  }
                }
              }
            }
          }
          hwlabels = Object.keys(hwClass)
          for (var key in hwClass) {
            hwdata.push(hwClass[key])
          }
          if (hwlabels.length == 0) {
            hwlabels = ['과제없음']
            hwdata = [0]
          }

          const ctx = document.getElementById('myChart');
            var myChart = new Chart(ctx, {
            type: 'doughnut',
              data: {
                labels: hwlabels,
                datasets: [{
                  data: hwdata,
                  backgroundColor: [
                    '#9DCEFF',
                    '#F2F3F6',
                    '#B3261E',
                    '#21005D',
                  ],
                  borderWidth: 0,
                  scaleBeginAtZero: true,
                }
              ]
            },
          });
        })
      }
  },
};
</script>

<style>
@import url('../../../../node_modules/vue-cal/dist/vuecal.css');
canvas{
  width:100%!important;
}
p{
  margin: 5px;
}
.head{
  padding: 15px;
  font-size: 30px;
}
.bottom{
  clear:both;
}
.cal{
  background-color: white;
}

#dashboard{
  display: flex;
  justify-content: space-between;
  align-content: center;
  padding: 10px
}

#dash {
  border-radius: 10px;
}

*.vuecal--month-view .vuecal__cell {
  height: 80px;
}

.vuecal--blue-theme .vuecal__cell-events-count, .vuecal--blue-theme .vuecal__menu {
    background-color: #83B1C9;
}

.vuecal--blue-theme .vuecal__title-bar {
    background-color: #b2d1e4;
}

.vuecal--month-view .vuecal__cell-content {
  justify-content: flex-start;
  align-items: flex-end;
}

.vuecal--month-view .vuecal__cell-date {
  padding: 4px;
}
.vuecal--month-view .vuecal__no-event {
  display: none;
}

/* Different color for different event types. */
.vuecal__cell{
  height:94px !important;
  overflow: hidden;

}
.vuecal__event.a {
   background-color: rgb(3, 126, 243, 0.8);
  border: 1px solid rgba(3, 126, 243);
  color: #fff;
}
.vuecal__event.b {
  background-color: rgba(248, 90, 64, 0.8);
  border: 1px solid rgb(248, 90, 64);
  color: #fff;
}
.vuecal__event.c {
  background-color: rgba(0, 193, 110, 0.8);
  border: 1px solid rgb(0, 193, 110);
  color: #fff;
}
.vuecal__event.d {
   background-color: rgb(117, 82, 204, 0.8);
  border: 1px solid rgba(117, 82, 204);
  color: #fff;
}
.vuecal__event.e {
  background-color: rgba(12, 185, 193, 0.8);
  border: 1px solid rgb(12, 185, 193);
  color: #fff;
}
.vuecal__event.f {
  background-color: rgba(244, 137, 36, 0.8);
  border: 1px solid rgb(244, 137, 36);
  color: #fff;
}
.vuecal__event.g {
   background-color: rgb(82, 86, 94, 0.8);
  border: 1px solid rgba(82, 86, 94);
  color: #fff;
}
.percentage-value {
  display: block;
  margin-top: 10px;
  font-size: 28px;
}

.percentage-label {
  display: block;
  margin-top: 10px;
  font-size: 12px;
}
</style>




