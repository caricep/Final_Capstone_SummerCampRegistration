import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';

Vue.use(Vuex);

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token');
const currentUser = JSON.parse(localStorage.getItem('user'));

if (currentToken != null) {
	axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
	state: {
		token: currentToken || '',
		user: currentUser || {},
		campers: [],
		changesMade: [],
		dorm1: []
	},
	mutations: {
		SET_AUTH_TOKEN(state, token) {
			state.token = token;
			localStorage.setItem('token', token);
			axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
		},
		SET_CAMPERS(state, campers) {
			state.campers = campers;
		},
		SET_CHANGES_MADE(state, changesMade) {
			state.changesMade.push(changesMade);
		},
		SET_USER(state, user) {
			state.user = user;
			localStorage.setItem('user', JSON.stringify(user));
		},
		SET_DORM(state, { camper, dormID }) {
			console.log(camper);
			console.log(dormID);
			if (dormID == 1) {
				state.dorm1.push(camper);
			}
		},
		LOGOUT(state) {
			localStorage.removeItem('token');
			localStorage.removeItem('user');
			state.token = '';
			state.user = {};
			axios.defaults.headers.common = {};
		}
	}
});
