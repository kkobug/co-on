// API
import $axios from 'axios'

// Auth
// 학생 로그인
export function requestStLogin ({ state }, payload) {
  console.log('requestLogin')
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


// User
// 회원 아이디 찾기
export function requestFindid ({ state }, payload) {
  console.log('requestFindid')
  const url = '/findId'
  let body = payload
  return $axios.post(url, body)
}
// 학생 비밀번호 재설정
export function requestChangeStPassword ({ state }, payload) {
  console.log('requestChangeStPassword')
  const url = `/findPassword?stId=${payload['stId']}&stPassword=${payload['stPassword']}`
  return $axios.put(url)
}
// 교사 아이디 찾기
export function requestFindTchrid ({ state }, payload) {
  const url = '/findteacherId'
  let body = payload
  return $axios.post(url, body)
}
// 교사 비밀번호 재설정
export function requestChangeTchrPassword ({ state }, payload) {
  console.log('requestChangeTchrPassword')
  const url = `/findTeacherPassword?tchrId=${payload['tchrId']}&tchrPassword=${payload['tchrPassword']}`
  return $axios.put(url)
}
// 학생 회원 본인 정보 조회 -- 일단 안 쓰는 기능
export function requestLookupstudent ({ state }, payload) {
  console.log('requestLookupstudent')
  const url = '/student/me'
  let body = payload
  return $axios.get(url, body)
}
// 학생 회원 정보 수정
export function requestModifyStudent ({ state }, payload) {
  const url = '/student/modify'
  let body = payload
  return $axios.put(url, body)
}
// 학생 회원 가입
export function requestStSignup ({ state }, payload) {
  console.log('requestSignup')
  const url = '/student/signup'
  let body = payload
  return $axios.post(url, body)
}
// 학생 회원 정보 삭제
export function requestDeleteStudent ({ state }, payload) {
  const url = `student/withdrawal/${payload}`
  return $axios.delete(url)
}
// 교사 정보 수정
export function requestModifyTeacher ({ state }, payload) {
  const url = '/teacher/modify'
  let body = payload
  return $axios.put(url, body)
}
// 교사 회원 가입
export function requestTchrSignup ({ state }, payload) {
  console.log('requestTchrSignup')
  const url = '/teacher/signup'
  let body = payload
  return $axios.post(url, body)
}
// 학생 조회
export function requestGetStudent ({ state }) {
  console.log('requestGetStudent')
  const url = `/teacher/studentlist`
  return $axios.get(url)
}
export function requestDeleteTeacher ({ state }, payload) {
  const url = `teacher/withdrawal/${payload}`
  return $axios.delete(url)
}

// StudyRoom
// 수업 개설
export function requestTchrCreateClass ({ state }, payload) {
  console.log('requestTchrCreateClass')
  const url = `/studyRoom/create`
  let body = payload
  return $axios.post(url, body)
}
// 수업 삭제
export function requestDeleteClass ({ state }, data) {
  console.log('requestDeleteClass')
  const url = '/studyRoom/delete'
  // return $axios.delete(url, {data:{body}})
  return $axios.delete(url, {data})

}
// 교사 수업 조회
export function requestGetTchrClass ({ state }, payload) {
  console.log('requestGetTchrClass')
  const url = `/studyRoom/teacher/list/${payload.tchrId}`
  let body = payload
  return $axios.get(url, body)
}

// StudyRoomDetail
// 학생 수업 학생 ID 조회
export function requestGetClass ({ state }, payload) {
  console.log('requestGetClass')
  const url = `/studyRoomDetail/student/list/${payload}`
  return $axios.get(url)
}
// 학생 수업 수업 ID 조회
export function requestGetClassStudyId ({ state }, payload) {
  console.log('requestGetClassStudyId')
  const url = `/studyRoomDetail/student/studylist/${payload}`
  return $axios.get(url)
}
// 학생 추가
export function requestAddStudentInStudy ({ state }, payload) {
  console.log('requestAddStudentInStudy')
  const url = `/studyRoomDetail/teacher/add`
  let body = payload
  return $axios.post(url, body)
}

// Notice
// 공지사항 정보 조회
export function requestgetNotice ({ state }, payload) {
  console.log('requestgetNotice')
  const url = `/notice/${payload.notice_id}`
  let body = payload
  return $axios.get(url, body)
}
// 교사 공지사항 입력
export function requestAddNotice ({ state }, payload) {
  console.log('requestAddNotice')
  const url = `/notice/create`
  let body = payload
  return $axios.post(url, body)
}
// 공지사항 삭제
export function requestDelNotice ({ state }, data) {
  console.log('requestDelNotice')
  const url = `/notice/delete`
  return $axios.delete(url, {data})
}
// 공지사항 수정
export function requestUpdateNotice ({ state }, payload) {
  console.log('requestUpdateNotice')
  const url = `/notice/modify/${payload.noticeId}`
  let body = payload
  return $axios.put(url, body)
}
// 학생이 속한 수업의 공지 조회
export function requestListNotice ({ state }, payload) {
  console.log('requestListNotice')
  const url = `/notice/study/list/${payload.studyId}`
  return $axios.get(url)
}
export function requestDelHomework ({ state }, data) {
  console.log('requestDelHomework', state, data)
  const url = `/homework/delete`
  return $axios.delete(url, {data})
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
// 교사가 출제한 과제 조회
export function requestTchrListHomework ({ state }, payload) {
  console.log('requestTchrListHomework')
  const url = `/homework/teacher/${payload.tchrId}`
  let body = payload
  return $axios.get(url, body)
}
export function requestGetLesson ({ state }, payload) {
  console.log('requestGetLesson', state, payload)
  const url = `studyRoomDetail/student/list/${payload.stId}`
  return $axios.get(url)
}

export function requestGetSTNotice ({ state }, payload) {
  console.log('requestGetSTNotice', state, payload)
  const url = `/notice/student/list/${payload.stId}`
  return $axios.get(url)
}
export function requestGetHW ({ state }, payload) {
  console.log('requestGetHW', state, payload)
  const url = `/homework/student/${payload.stId}`
  return $axios.get(url)
}
export function requestaddsthw ({ state }, payload) {
  console.log('requestaddsthw', state, payload)
  const url = `/studenthomework/create`
  let body = payload
  return $axios.post(url, body)
}
export function requestdelsthw ({ state }, data) {
  console.log('requestdelsthw', state, data)
  const url = `/studenthomework/delete`
  return $axios.delete(url, {data})
}
export function requestGetSthwdetail ({ state }, payload) {
  console.log('requestGetSthwdetail', state, payload)
  const url = `/studenthomework/detail/${payload}`
  let body = payload
  return $axios.get(url, body)
}
export function requestUpdateSthw ({ state }, payload) {
  console.log('requestgetHomework', state, payload)
  const url = `/studenthomework/modify/${payload}`
  let body = payload
  return $axios.put(url, body)
}
export function requestGetDonesthw ({ state }, payload) {
  console.log('requestGetDonesthw', state, payload)
  const url = `/studenthomework/student/list/${payload}`
  return $axios.get(url)
}
export function requestGetHwlist ({ state }, payload) {
  console.log('requestGetHwlist', state, payload)
  const url = `/studenthomework/teacher/list/${payload}`
  return $axios.get(url)
}





