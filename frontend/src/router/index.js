import { createRouter, createWebHashHistory } from 'vue-router'
import LandingPage from '../views/LandingPage.vue'

const routes = [
	{
		path: '/',
		name: 'LandingPage',
		component: LandingPage
	},
	{
		path: '/login',
		name: 'Login',
		component: () => import('../views/LoginPage.vue')
	},
	{
		path: '/register',
		name: 'Register',
		component: () => import('../views/RegisterPage.vue')
	},
	{
		path: '/homepage',
		name: 'HomePage',
		component: () => import('../views/HomePage.vue')
	}
]

const router = createRouter({
	history: createWebHashHistory(),
	routes
})

export default router
