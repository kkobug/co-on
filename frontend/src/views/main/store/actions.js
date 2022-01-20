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

export function requestLookupstudent ({ state }, payload) {
  console.log('requestLookupstudent', state, payload)
  const url = '/student/me'
  let body = payload
  return $axios.get(url, body)
}

export function requestModifystudent ({ state }, payload) {
  console.log('requestModifystudent', state, payload)
  const url = '/student/modify'
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


export function requestDeleteuser ({ state }, payload) {
  console.log('requestDeleteuser', state, payload)
  const url = `/withdrawal/${payload}`
  let body = payload
  return $axios.delete(url, body)
}
