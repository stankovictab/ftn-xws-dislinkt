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
	{
		path: '/profile/:userId',
		name: 'Profile',
		component: () => import('../views/ProfilePage.vue'),
		props: true
	}
]

const router = createRouter({
	history: createWebHashHistory(),
	routes
})

export default router
