// 플랫폼 관련 정보 가져오기
export function getIsDesktopPlatform (state) {
  return state.isDesktopPlatform
}
// 메뉴 객체 가져오기
export function getMenus (state) {
	return state.menus
}
// Active된 메뉴 인덱스 가져오기
export function getActiveMenuIndex (state) {
	const keys = Object.keys(state.menus)
	return keys.findIndex(item => item === state.activeMenu)
}
export function getStudy (state) {
	return state.classList
}
export function getStudyName (state) {
	return state.curClassName
}
export function getStudyId (state) {
	return state.curClassId
}
export function getTchrNoticeList (state) {
	return state.TchrNoticeList
}
export function getTchrHomeworkList (state) {
	return state.TchrHomeworkList
}
export function getSession (state) {
	return state.Session
}
