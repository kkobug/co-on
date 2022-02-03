<template>
  <div>
    <div class="head" >
      Main
    </div>
    <!-- 대시보드 -->
    <el-row id="dashboard">
      <el-col :span="6">
        <el-card :body-style="{ padding: '0px' }" id="dash" shadow="always" v-if="this.dashHw">
          <div style="padding: 14px; text-align:left; background-color:#EADDFF">
            <font-awesome-icon icon="clock" style="font-size:80px; float:left" />
            <p style="font-weight:bold; color:#21005D; font-size:20px">
              마감이 임박한 과제
            </p>
            <el-button type="text" class="button">{{this.dashHw.title}}</el-button>
            <div class="bottom">
              <p>{{this.dashHw.content}}</p>
              <time class="time">{{ this.dashHw.end }}</time>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card :body-style="{ padding: '0px' }" id="dash" shadow="always" v-if="this.dashNotice">
          <div style="padding: 14px; text-align:left;background-color:#D9E7CB">
            <font-awesome-icon icon="bell" style="font-size:80px" />
            <span style="font-weight:bold; color:#273420; font-size:20px">
              New Notice
            </span>
            <el-button type="text" class="button">{{this.dashNotice.noticeTitle}}</el-button>
            <div class="bottom">
              <p>{{this.dashNotice.noticeContent}}</p>
              <time class="time">{{ this.dashNotice.noticePosted }}</time>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card :body-style="{ padding: '0px' }" id="dash" shadow="always">
          <div style="padding: 14px; text-align:left;background-color:#F9DEDC" class="">
            <font-awesome-icon icon="calendar-check" style="font-size:80px" />
            <span style="font-weight:bold; color:#410E08; font-size:20px">
              출석 확인
            </span>
            <div class="bottom">
              <time class="time">{{ this.today }}</time>
              <el-button type="text" class="button">Operating</el-button>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card :body-style="{ padding: '0px' }" id="dash" shadow="always">
          <div style="padding: 14px; text-align:left;background-color:#FFD8E4" class="">
            <font-awesome-icon icon="chalkboard-teacher" style="font-size:80px" />
            <span style="font-weight:bold; color:#31111D; font-size:20px">
              진행중인 수업
            </span>
            <div class="bottom">
              <time class="time">{{ this.today }}</time>
              <el-button type="text" class="button">Operating</el-button>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <!-- 원형 그래프 -->
    <graph/>
    <!-- 달력 -->
    <div class="calendar">
      <vue-cal
        class="vuecal--blue-theme cal"
        :selected-date="this.today"
        :disable-views="['years', 'year']"
        default-view="month"
        events-on-month-view="short"
        overlaps-per-time-step
        :events="events"
        style="height: auto"
      >
      </vue-cal>
    </div>
  </div>
</template>

<script>
import VueCal from 'vue-cal'
import "vue-cal/dist/vuecal.css";

import graph from './graph.vue'
export default {
  name: "Calendar",
  components: {
    VueCal,
    graph
    },

  data: () => ({
    events: [],
    today:new Date(),
    dashHw:undefined,
    dashNotice:undefined

  }),
  methods: {
    setEventVal() {
      this.events = [
        {
          start: "2022-1-21 15:25",
          end: "2022-1-21 16:25",
          title: "john 1",
          content: '<i class="v-icon material-icons">local_hospital</i>',
          class: "health",
          split: 2
        }
      ];
    },
    getNotice(){
      this.$store.dispatch('root/requestGetNotice',this.userId)
      .then(result =>{
        this.notices=result.data
        this.dashNotice=this.notices[this.notices.length-1]
        this.dashNotice.noticePosted=this.dashNotice.noticePosted.substring(0,10)
      })
      .catch(function(err){
        alert(err)
      })
    },
    getHw(){
      this.$store.dispatch('root/requestGetSthwlist',this.userId)
      .then(result =>{
        for (var j = 0; j < result.data.length; j++) {
          var hw = result.data[j];
          this.events.push({
            start: hw.hwPosted.substring(0,5)+hw.hwPosted.substring(6,16),//"2022-01-30 17:46:39.000000"
            end: hw.hwDeadline.substring(0,5)+hw.hwDeadline.substring(6,16),//"2022-02-18 12:00:00"
            title: hw.hwTitle + " - " + hw.studyroom.studyName,
            content: hw.hwContent,
            class: "health",
            split: 2
          });
        }
        const hws=[];
        for (var j = 0; j < this.events.length; j++) {
          var hw = this.events[j];
          console.log(hw.end,this.today)
          if (hw.end.substring(0,5)>=this.today.substring(0,5)){
            hws.push(hw);
          }
        }

        console.log(1111,hws)
        hws.sort(function(a, b) { // 오름차순
          return a["end"] - b["end"];
        });
        this.dashHw=hws[hws.length-1]
      })
      .catch(function(err){
        alert(err)
      })
    },
  },
  created:function(){
    const localvuex=JSON.parse(localStorage.getItem('vuex'))
    this.userId = localvuex["root"]["userid"]
    this.setEventVal()
    this.getHw()
    this.getNotice()
    this.today=this.today.getFullYear()+'-'+(this.today.getMonth()+1)+'-'+this.today.getDate()
  }
};
</script>

<style>
@import url('../../../../node_modules/vue-cal/dist/vuecal.css');
p{
  margin: 5px;
}
.head{
  padding: 15px;
  font-size: 30px;
}
.bottom{
  clear:both
}
.calendar{
  padding: 10px;
}
.cal{
  background-color: #C4C9BC;

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

.vuecal--month-view .vuecal__cell-content {
  justify-content: flex-start;
  height: 100%;
  align-items: flex-end;
}

.vuecal--month-view .vuecal__cell-date {
  padding: 4px;
}
.vuecal--month-view .vuecal__no-event {
  display: none;
}

/* Different color for different event types. */
.vuecal__event.leisure {
  background-color: rgba(253, 156, 66, 0.9);
  border: 1px solid rgb(233, 136, 46);
  color: #fff;
}
.vuecal__event.sport {
  background-color: rgba(255, 102, 102, 0.9);
  border: 1px solid rgb(235, 82, 82);
  color: #fff;
}
.vuecal__event.health {
  /* background-color: rgba(164, 230, 210, 0.9); */
  background-color: #8D9287;
  /* border: 1px solid rgb(144, 210, 190); */
  border: 1px solid #8D9287;
  color: black;
}
</style>




