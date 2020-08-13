import Vue from 'vue';
import Router from 'vue-router';
import Home from '../views/Home.vue';
import Login from '../views/Login.vue';
import Logout from '../views/Logout.vue';
import Register from '../views/Register.vue';
import Upload from '../views/Upload.vue';
import History from '../views/History.vue';
import Campers from '../views/Campers.vue';
import Team from '../views/Team.vue';
import Email from '../views/Email.vue';
import DormAssignment from '../views/DormAssignment.vue';
import store from '../store/index';

Vue.use(Router);

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
	mode: 'history',
	base: process.env.BASE_URL,
	routes: [
		{
			path: '/',
			name: 'home',
			component: Home,
			meta: {
				requiresAuth: false,
				hideNavigation: true,
				homePage: true
			}
		},
		{
			path: '/login',
			name: 'login',
			component: Login,
			meta: {
				requiresAuth: false,
				hideNavigation: true
			}
		},
		{
			path: '/logout',
			name: 'logout',
			component: Logout,
			meta: {
				requiresAuth: true
			}
		},
		{
			path: '/register',
			name: 'register',
			component: Register,
			meta: {
				requiresAuth: true
			}
		},
		{
			path: '/upload',
			name: 'upload',
			component: Upload,
			meta: {
				requiresAuth: true
			}
		},
		{
			path: '/history',
			name: 'history',
			component: History,
			meta: {
				requiresAuth: true
			}
		},
		{
			path: '/campers',
			name: 'campers',
			component: Campers,
			meta: {
				requiresAuth: true
			}
		},
		{
			path: '/team',
			name: 'team',
			component: Team,
			meta: {
				requiresAuth: true
			}
		},
		{
			path: '/emails',
			name: 'emails',
			component: Email,
			meta: {
				requiresAuth: true
			}
		},
		{
			path: '/dormassignments',
			name: 'dormassignments',
			component: DormAssignment,
			meta: {
				requiresAuth: true
			}
		}
	]
});

router.beforeEach((to, from, next) => {
	// Determine if the route requires Authentication
	const requiresAuth = to.matched.some((x) => x.meta.requiresAuth);

	// if (!requiresAuth) {
	// 	next('/home');
	// }
	// If it does and they are not logged in, send the user to "/login"
	if (requiresAuth && store.state.token === '') {
		next('/login');
	} else {
		// Else let them go to their next destination
		next();
	}
});

export default router;
