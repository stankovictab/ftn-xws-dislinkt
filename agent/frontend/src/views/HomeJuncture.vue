<template>
	<component :is="userHome" />
</template>

<script>
import { mapGetters } from "vuex";
import Home from "./LandingPage.vue";
import AdminHome from "./AdminHome.vue";
import UserHome from "./HomePage.vue";

export default {
	name: "HomeJuncture",
	mounted() {},
	components: {
		Home,
		AdminHome,
		UserHome,
	},
	props: {},
	computed: {
		...mapGetters(["userRole"]),
		userHome() {
			return `${this.userRole}Home`;
		},
	},
};
</script>

<style scoped lang="scss">
</style>

