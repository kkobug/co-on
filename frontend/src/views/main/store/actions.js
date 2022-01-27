// API
import $axios from 'axios'

// 학생 로그인
export function requestStLogin ({ state }, payload) {
  console.log('requestLogin', state, payload)
  const url = '/auth/studentlogin'
  let body = payload
  return $axios.post(url, body)
}
// 교사 로그인
export function requestTchrLogin ({ state }, payload) {
  const url = '/auth/teacherlogin'
  let body = payload
  return $axios.post(url, body)
}
// 학생 ID 찾기
export function requestFindid ({ state }, payload) {
  console.log('requestFindid', state, payload)
  const url = '/findId'
  let body = payload
  return $axios.post(url, body)
}
// 교사 ID 찾기
export function requestFindTchrid ({ state }, payload) {
  const url = '/findteacherId'
  let body = payload
  return $axios.post(url, body)
}
// 학생 비밀번호 재설정
export function requestChangeStPassword ({ state }, payload) {
  console.log('requestChangeStPassword', state, payload)
  const url = `/findPassword?stId=${payload['stId']}&stPassword=${payload['stPassword']}`
  return $axios.put(url)
}
// 교사 비밀번호 재설정
export function requestChangeTchrPassword ({ state }, payload) {
  console.log('requestChangeTchrPassword', state, payload)
  const url = `/findTeacherPassword?tchrId=${payload['tchrId']}&tchrPassword=${payload['tchrPassword']}`
  return $axios.put(url)
}
// 학생 조회 -- 일단 안 쓰는 기능
export function requestLookupstudent ({ state }, payload) {
  console.log('requestLookupstudent', state, payload)
  const url = '/student/me'
  let body = payload
  return $axios.get(url, body)
}
// 학생 정보 수정
export function requestModifyStudent ({ state }, payload) {
  const url = '/student/modify'
  let body = payload
  return $axios.put(url, body)
}
// 교사 정보 수정
export function requestModifyTeacher ({ state }, payload) {
  const url = '/teacher/modify'
  let body = payload
  return $axios.put(url, body)
}


export function requestStSignup ({ state }, payload) {
  console.log('requestSignup', state, payload)
  const url = '/student/signup'
  let body = payload
  return $axios.post(url, body)
}

export function requestTchrSignup ({ state }, payload) {
  console.log('requestTchrSignup', state, payload)
  const url = '/teacher/signup'
  let body = payload
  return $axios.post(url, body)
}


export function requestDeleteStudent ({ state }, payload) {
  const url = `student/withdrawal/${payload}`
  return $axios.delete(url)
}

export function requestDeleteTeacher ({ state }, payload) {
  const url = `teacher/withdrawal/${payload}`
  return $axios.delete(url)
}

export function requestTchrCreateClass ({ state }, payload) {
  console.log('requestTchrCreateClass', state, payload)
  const url = `/studyRoom/create`
  let body = payload
  return $axios.post(url, body)
}

export function requestDeleteClass ({ state }, data) {
  console.log('requestDeleteClass', state, data)
  const url = '/studyRoom/delete'
  // return $axios.delete(url, {data:{body}})
  return $axios.delete(url, {data})

}

export function requestGetTchrClass ({ state }, payload) {
  console.log('requestGetTchrClass', state, payload)
  const url = `/studyRoom/teacher/list/${payload.tchrId}`
  let body = payload
  return $axios.get(url, body)
}
//새로추가
//학생 추가
export function requestAddStudentInStudy ({ state }, payload) {
  console.log('requestAddStudentInStudy', state, payload)
  const url = `/studyRoomDetail/teacher/add`
  let body = payload
  return $axios.post(url, body)
}
//공지관련
export function requestgetNotice ({ state }, payload) {
  console.log('requestgetNotice', state, payload)
  const url = `/notice/${payload.notice_id}`
  let body = payload
  return $axios.get(url, body)
}
export function requestAddNotice ({ state }, payload) {
  console.log('requestAddNotice', state, payload)
  const url = `/notice/create`
  let body = payload
  return $axios.post(url, body)
}
export function requestDelNotice ({ state }, data) {
  console.log('requestDelNotice', state, data)
  const url = `/notice/delete`
  return $axios.delete(url, {data})
}
export function requestListNotice ({ state }, payload) {
  console.log('requestListNotice', state, payload)
  const url = `/notice/study/list/${payload.studyId}`
  return $axios.get(url)
}
export function requestUpdateNotice ({ state }, payload) {
  console.log('requestUpdateNotice', state, payload)
  const url = `/notice/modify/${payload.noticeId}`
  let body = payload
  return $axios.put(url, body)
}
// 과제 관련
export function requestAddHomework ({ state }, payload) {
  console.log('requestUpdateHomework', state, payload)
  const url = `/homework/create`
  let body = payload
  return $axios.post(url, body)
}
export function requestDelHomework ({ state }, body) {
  console.log('requestDelHomework', state, paylbodyoad)
  const url = `/homework/delete`
  return $axios.delete(url, {body})
}
export function requestgetHomework ({ state }, payload) {
  console.log('requestgetHomework', state, payload)
  const url = `/homework/detail/${payload.hwId}`
  let body = payload
  return $axios.get(url, body)
}
export function requestupdateHomework ({ state }, payload) {
  console.log('requestgetHomework', state, payload)
  const url = `/homework/modify/${payload.hwId}`
  let body = payload
  return $axios.put(url, body)
}
export function requestListHomework ({ state }, payload) {
  console.log('requestListHomework', state, payload)
  const url = `/homework/study/${payload.studyId}`
  let body = payload
  return $axios.get(url, body)
}
export function requestTchrListHomework ({ state }, payload) {
  console.log('requestTchrListHomework', state, payload)
  const url = `/homework/teacher/${payload.tchrId}`
  let body = payload
  return $axios.get(url, body)
}
export function requestGetLesson ({ state }, payload) {
  console.log('requestGetLesson', state, payload)
  const url = `/student/list/${payload}`
  return $axios.get(url)
}

export function requestGetNotice ({ state }, payload) {
  console.log('requestGetNotice', state, payload)
  const url = `/notice/list/${payload}`
  return $axios.get(url)
}
export function requestGetHW ({ state }, payload) {
  console.log('requestGetHW', state, payload)
  const url = `/homework/study/${payload}`
  return $axios.get(url)
}

