import { createRouter, createWebHistory } from 'vue-router'
import Login from '@/views/auth/login'
import ConferencesDetail from '@/views/conferences/conference-detail'
import App from '@/App'
import Mypage from '@/views/history/mypage'

//teacher
import Tchr_main from '@/views/teacher/tchr_main'
import Tchr_nav from '@/views/teacher/tchr_nav'
import Tchr_ourclass from '@/views/teacher/ourclass'
import Tchr_attend from '@/views/teacher/daily_attend'
import Tchr_contents from '@/views/teacher/tchr_contents'
import Tchr_HWManage from '@/views/teacher/homework_manage'
import Tchr_conference from '@/views/teacher/tchr_conference'

// import Main from '@/views/main/main'
import Main_calendar from '@/views/main/components/calendar'
import hw_notice from '@/views/main/components/hw_notice'
import Lesson from '@/views/main/components/lesson'

import Video from '@/views/video/video_main'

const fullMenu = require('@/views/main/menu.json')
function makeRoutesFromMenu () {
  let routes = Object.keys(fullMenu).map((key) => {
    if (key === 'home') {
      return { path: fullMenu[key].path, name: key, component: Home  }
    } else if (key === 'login') {
      return { path: fullMenu[key].path, name: key, component: Login }
    } else if (key === 'history') {
      return { path: fullMenu[key].path, name: key, component: History }
    } else { // menu.json 에 들어있는 로그아웃 메뉴
      return null
    }
  })
  // 로그아웃 파싱한 부분 제거
  routes = routes.filter(item => item)
  // menu 자체에는 나오지 않는 페이지 라우터에 추가(방 상세보기)
  routes.push({
    path: '/conferences/:conferenceId',
    name: 'conference-detail',
    component: ConferencesDetail
  })
  return routes
}

// const routes = makeRoutesFromMenu()
const routes = [
  {
    path: '/',
    name: 'Main_calendar',
    component: Main_calendar,
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
  },
  {
    path: '/app',
    name: 'App',
    component: App,
  },
  {
    path: '/hw_notice',
    name: 'hw_notice',
    component: hw_notice,
  },
  {
    path: '/lesson',
    name: 'Lesson',
    component: Lesson,
  },
  {
    path: '/mypage',
    name: 'Mypage',
    component: Mypage,
  },
  {
    path: "/tchr_main",
    name: "Tchr_main",
    component: Tchr_main,
  },
  {
    path: "/tchr_nav",
    name: "Tchr_nav",
    component: Tchr_nav,
    props:true
  },
  {
    path: "/tchr_attend",
    name: "Tchr_attend",
    component: Tchr_attend,
    props:true
  },
  {
    path: "/tchr_ourclass",
    name: "Tchr_ourclass",
    component: Tchr_ourclass,
    props:true
  },
  {
    path: "/video",
    name: "video",
    component: Video,
  },
  {
    path: "/tchr_contents",
    name: "Tchr_contents",
    component: Tchr_contents,
    props:true
  },
  {
    path: "/tchr_HWMange",
    name: "Tchr_HWManage",
    component: Tchr_HWManage,
    props: true
  },
  {
    path: "/tchr_conference",
    name: "Tchr_conference",
    component: Tchr_conference,
    props: true
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})
export default router
