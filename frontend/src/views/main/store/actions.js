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

export function requestFindpassword ({ state }, payload) {
  console.log('requestFindpassword', state, payload)
  const url = '/findPassword'
  let body = payload
  return $axios.put(url, body)
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

export function requestSignup ({ state }, payload) {
  console.log('requestSignup', state, payload)
  const url = '/student/signup'
  let body = payload
  return $axios.post(url, body)
}

export function requestDeleteuser ({ state }, payload) {
  console.log('requestDeleteuser', state, payload)
  const url = `/withdrawal/${payload}`
  let body = payload
  return $axios.delete(url, body)
}
