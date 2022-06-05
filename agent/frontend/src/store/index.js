import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'

export default new Vuex.Store({
	plugins: [createPersistedState({
		storage: window.sessionStorage,
	})],
	state: {
		user: { role: "" }, // "" = not logged in, "Guest" = guest, "Admin" = admin, "Client" = client
		token: null,
	},
	mutations: {
		setUser(state, user) {
			state.user = user;
		},
		setToken(state, token) {
			state.token = token;
		},
	},
	getters: {
		hasRole(state) {
			return !!state.user?.role;
		},
		isLoggedIn(state) {
			return !!state.token
		},
		// isRole(state, getters) {
		//   return getters.isLoggedIn(state) && state.user?.role === 'Role';
		// },
		userRole(state) {
			return state.user?.role;
		},
	},
	actions: {
	},
	modules: {
	}
})
