<template>
  <div>
    <tchr-nav @startvideo="start"></tchr-nav>
    <h1>과제 관리 페이지</h1>
    <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
      <el-tab-pane v-for="Hw in tableData" :key="Hw" :label="Hw.date" :name="Hw.Date">{{Hw}}</el-tab-pane>
    </el-tabs>

    <el-table :data="tableData" style="width: 100%">
      <el-table-column prop="date" label="Date" width="180" />
      <el-table-column prop="name" label="Name" width="180" />
      <el-table-column prop="address" label="Address" />
    </el-table>

    <start-video-dialog
        :open="videoDialogOpen"
        @closeVideoDialog="end"
    ></start-video-dialog>
  </div>
</template>

<script>
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import { ref, unref, reactive, computed, onMounted } from 'vue'

import Tchr_nav from './tchr_nav.vue'
import StartVideoDialog from './startvideo-dialog.vue'

export default {
  name: 'Tchr_HWManage',
  components: {
    "tchr-nav": Tchr_nav,
    StartVideoDialog
  },
  data(props){
    return {
      videoDialogOpen: false,
    }
  },
  setup() {
    const store = useStore()
    const Hws = undefined
    const activeName = ref('')
    const getHw = function () {
      store.dispatch('root/requestTchrListHomework',this.userId)
      .then(result =>{
        this.Hws=result.data
        console.log(this.Hws)
        this.Hws.forEach(function(item,index,arr2){
          console.log(item,index,arr2[index+1]);
        })
      })
      .catch(function(err){
        alert(err)
      })
    }
    const tableData = [
  {
    date: '2016-05-03',
    name: 'Tom',
    state: 'California',
    city: 'Los Angeles',
    address: 'No. 189, Grove St, Los Angeles',
    zip: 'CA 90036',
  },
  {
    date: '2016-05-02',
    name: 'Tom',
    state: 'California',
    city: 'Los Angeles',
    address: 'No. 189, Grove St, Los Angeles',
    zip: 'CA 90036',
  },
  {
    date: '2016-05-04',
    name: 'Tom',
    state: 'California',
    city: 'Los Angeles',
    address: 'No. 189, Grove St, Los Angeles',
    zip: 'CA 90036',
  }
]
    return {tableData,Hws,activeName, getHw}
  },
  methods:{
    start (){
      this.videoDialogOpen= true
    },
    end (){
      this.videoDialogOpen= false
    },

  },
  created: function(){
    const localvuex=JSON.parse(localStorage.getItem('vuex'))
    this.userId=localvuex["root"]["userid"]
    this.getHw()
  }

}
</script>

<style>
#hide_table{
  background-color: blueviolet;
}
</style>
