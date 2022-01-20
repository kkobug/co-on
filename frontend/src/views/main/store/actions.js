// API
import $axios from 'axios'

export function requestLogin ({ state }, payload) {
  console.log('requestLogin', state, payload)
  const url = '/auth/studentlogin'
  let body = payload
  return $axios.post(url, body)
}

export function requestFindid ({ state }, payload) {
  console.log('requestFindid', state, payload)
  const url = '/findId'
  let body = payload
  return $axios.post(url, body)
}

export function requestChangePassword ({ state }, payload) {
  console.log('requestChangePassword', state, payload)
  const url = `/findPassword?stId=${payload['stId']}&stPassword=${payload['stPassword']}`
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
