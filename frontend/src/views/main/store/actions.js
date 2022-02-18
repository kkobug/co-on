// API
import $axios from 'axios'
// Auth
// 학생 로그인
export function requestStLogin ({ state }, payload) {
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
  const url = '/findId'
  let body = payload
  return $axios.post(url, body)
}
// 학생 비밀번호 재설정
export function requestChangeStPassword ({ state }, payload) {
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
  const url = `/findTeacherPassword?tchrId=${payload['tchrId']}&tchrPassword=${payload['tchrPassword']}`
  return $axios.put(url)
}
// 학생 회원 본인 정보 조회 -- 일단 안 쓰는 기능
export function requestLookupstudent ({ state }, payload) {
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
  const url = '/teacher/signup'
  let body = payload
  return $axios.post(url, body)
}
// 학생 조회
export function requestGetStudent ({ state }) {
  const url = `/teacher/studentlist`
  return $axios.get(url)
}
export function requestDeleteTeacher ({ state }, payload) {
  const url = `teacher/withdrawal/${payload}`
  return $axios.delete(url)
}
// 프사 수정
export function requestUpdateTchrProfImg ({ state }, payload) {
  const url = `/teacher/profile`
  let body = payload
  return $axios.post(url, body)
}
export function requestUpdateStProfImg ({ state }, payload) {
  const url = `/student/profile`
  let body = payload
  return $axios.post(url, body)
}
// StudyRoom
// 수업 개설
export function requestTchrCreateClass ({ state }, payload) {
  const url = `/studyRoom/create`
  let body = payload
  return $axios.post(url, body)
}
// 수업 삭제
export function requestDeleteClass ({ state }, data) {
  const url = '/studyRoom/delete'
  // return $axios.delete(url, {data:{body}})
  return $axios.delete(url, {data})

}
// 교사 수업 조회
export function requestGetTchrClass ({ state }, payload) {
  const url = `/studyRoom/teacher/list/${payload.tchrId}`
  let body = payload
  return $axios.get(url, body)
}

// StudyRoomDetail
// 학생 수업 학생 ID 조회
export function requestGetClass ({ state }) {
  const url = `/studyRoomDetail/student/list/${state.userid}`
  return $axios.get(url)
}
// 학생 수업 수업 ID 조회
export function requestGetClassStudyId ({ state }, payload) {
  const url = `/studyRoomDetail/student/studylist/${payload.studyId}`
  return $axios.get(url)
}
// 학생 수업 학생 ID 조회 + 화상 정보
export function requestGetClassConfStudyId ({ state }, payload) {
  const url = `/studyRoomDetail/student/studylistwithconference/${payload}`
  return $axios.get(url)
}
// 학생 추가
export function requestAddStudentInStudy ({ state }, payload) {
  const url = `/studyRoomDetail/teacher/add`
  let body = payload
  return $axios.post(url, body)
}
// 학생 삭제
export function requestdelStudyStudent ({ state }, data) {
  const url = `/studyRoomDetail/teacher/deletestudent`
  return $axios.delete(url, {data})
}
// 우리반 조회
export function requestTchrStlist ({ state }, data) {
  const url = `/studyRoomDetail/teacher/studentList/${data}`
  return $axios.get(url, {data})
}
// Notice
// 공지사항 정보 조회
export function requestgetNotice ({ state }, payload) {
  const url = `/notice/${payload.notice_id}`
  let body = payload
  return $axios.get(url, body)
}
// 교사 공지사항 입력
export function requestAddNotice ({ state }, payload) {
  const url = `/notice/create`
  let body = payload
  return $axios.post(url, body)
}
// 공지사항 삭제
export function requestDelNotice ({ state }, data) {
  const url = `/notice/delete`
  return $axios.delete(url, {data})
}
// 공지사항 수정
export function requestUpdateNotice ({ state }, payload) {
  const url = `/notice/modify`
  let body = payload
  return $axios.post(url, body)
}
// 과제 관련
export function requestAddHomework ({ state }, payload) {
  const url = `/homework/create`
  let body = payload
  return $axios.post(url, body)
}
// 학생이 속한 수업의 공지 조회
export function requestListNotice ({ state }, payload) {
  const url = `/notice/study/list/${payload.studyId}`
  return $axios.get(url)
}
export function requestDelHomework ({ state }, data) {
  const url = `/homework/delete`
  return $axios.delete(url, {data})
}
export function requestgetHomework ({ state }, payload) {
  const url = `/homework/detail/${payload.hwId}`
  let body = payload
  return $axios.get(url, body)
}
export function requestupdateHomework ({ state }, payload) {
  const url = `/homework/modify`
  let body = payload
  return $axios.post(url, body)
}
export function requestListHomework ({ state }, payload) {
  const url = `/homework/study/${payload.studyId}`
  let body = payload
  return $axios.get(url, body)
}
// 교사가 출제한 과제 조회
export function requestTchrListHomework ({ state }) {
  const url = `/homework/teacher/${state.userid}`
  return $axios.get(url)
}
export function requestGetLesson ({ state }, payload) {
  const url = `studyRoomDetail/student/list/${payload.stId}`
  return $axios.get(url)
}

export function requestGetSTNotice ({ state }, payload) {
  const url = `/notice/student/list/${state.userid}`
  return $axios.get(url)
}
export function requestGetTchrNotice ({ state }) {
  const url = `/notice/teacher/list/${state.userid}`
  return $axios.get(url)
}
export function requestGetHW ({ state }, payload) {
  const url = `/homework/student/${state.userid}`
  return $axios.get(url)
}
export function requestRateHW ({ state }) {
  const url = `/homework/student/rate/${state.userid}`
  return $axios.get(url)
}
export function requestRateHW2 ({ state }) {
  const url = `/homework/teacher/rate/${state.userid}`
  return $axios.get(url)
}
export function requestaddsthw ({ state }, payload) {
  const url = `/studenthomework/create`
  let body = payload
  return $axios.post(url, body)
}
export function requestdelsthw ({ state }, data) {
  const url = `/studenthomework/delete`
  return $axios.delete(url, {data})
}
export function requestGetSthwdetail ({ state }, payload) {
  const url = `/studenthomework/detail/${payload}`
  let body = payload
  return $axios.get(url, body)
}
export function requestUpdateSthw ({ state }, payload) {
  const url = `/studenthomework/modify/${payload.get('stHwId')}`
  let body = payload
  return $axios.post(url, body)
}
export function requestGetDonesthw ({ state }, payload) {
  const url = `/studenthomework/student/list/${payload}`
  return $axios.get(url)
}
export function requestGetHwlist ({ state }, payload) {
  const url = `/studenthomework/teacher/list/${payload}`
  return $axios.get(url)
}
export function requestPutScore ({ state }, payload) {
  const url = `/studenthomework/score`
  let body = payload
  return $axios.put(url, body)
}

// Conference
// 수업 입실
export function requestConfEnter ({ state }, payload) {
  const url = `/conference/enter?stId=${payload['stId']}&confId=${payload['confId']}`
  return $axios.post(url)
}
//  수업 퇴실
export function requestConfExit ({ state }, payload) {
  const url = `/conference/exit?stId=${payload['stId']}&confId=${payload['confId']}`
  return $axios.put(url)
}
// 수업 시작/종료/인정시간 입력
export function requestConfCreate ({ state }, payload) {
  const url = `/conference/create`
  return $axios.post(url,payload)
}
// 마지막 화상정보 조회
export function requestConfInfo ({ state }, payload) {
  const url = `/conference/info?studyId=${payload['studyId']}&tchrId=${payload['tchrId']}`
  return $axios.get(url)
}

// 출결 관련 api
export function requestConfAttData ({ state }, payload) {
  const url = `/conference/infobydate?studyId=${payload['studyId']}&tchrId=${payload['tchrId']}&targetDate=${payload['targetDate']}`

  return $axios.get(url)
}
//마일리지
export function requestPlusMil ({ state }, payload) {
  const url = `/studyRoomDetail/teacher/score`
  let body = payload
  return $axios.put(url, body)
}
export function requeststLesson2 ({ state }, payload) {
  const url = `/studyRoomDetail/student/studyroomdetailinfo/${payload}`
  return $axios.get(url)
}
export function requestSoonLesson ({ state }, payload) {
  const url = `/conference/impendingconferenceinfo?tchrId=${payload.tchrId}`
  return $axios.get(url)
}

export function requestNoticeFileDown ({ state }, payload) {
  const url = `/notice/download-file?fileName=${payload.fileName}`
  return $axios.get(url)
}

export function requestHomeworkFileDown ({ state }, payload) {
  const url = `/homework/download-file?fileName=${payload.fileName}`
  return $axios.get(url)
}

export function requestStHomeworkFileDown ({ state }, payload) {
  const url = `/studenthomework/download-file?fileName=${payload.fileName}`
  return $axios.get(url)
}

export function requestStProf ({ state }, payload) {
  const url = `/student/profile-img?fileName=${payload.fileName}`
  return $axios.get(url)
}
