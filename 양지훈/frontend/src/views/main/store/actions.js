// API
import $axios from 'axios'

export function requestLogin ({ state }, payload) {
  console.log('requestLogin', state, payload)
  const url = '/auth/login'
  let body = payload
  return $axios.post(url, body)
}

// export function requestSignup ({ state }, payload) {
//   console.log('requestSignup', state, payload)
//   const url = '/auth/signup'
//   let body = payload
//   return $axios.post(url, body)
// }
