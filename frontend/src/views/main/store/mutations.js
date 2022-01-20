export function setPlatform (state, isDesktop) {
  state.isDesktopPlatform = isDesktop
}

export function setMenuActive (state, index) {
	console.log('setMenuActive', state,index)
	const keys = Object.keys(state.menus)
	state.activeMenu = keys[index]
}

export function setMenuActiveMenuName (state, menuName) {
	state.activeMenu = menuName
}

export function registerUser (state, id) {
	state.userid = id
}
export function removeUser (state, id) {
	state.userid = ''
}
export function logout (state) {
	state.authtoken = ''
}
export function jwtToken (state,jwt) {
	state.authtoken = jwt
}
export function checkTeacher (state) {
  state.whetherTchr = true
}
export function checkStudent (state) {
  state.whetherTchr = false
}
