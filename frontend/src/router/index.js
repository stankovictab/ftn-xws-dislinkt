import { createRouter, createWebHashHistory } from 'vue-router'
import HomeJuncture from '../views/HomeJuncture.vue'

const routes = [
	{
		path: '/',
		name: 'HomeJuncture',
		component: HomeJuncture
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
]

const router = createRouter({
	history: createWebHashHistory(),
	routes
})

export default router
