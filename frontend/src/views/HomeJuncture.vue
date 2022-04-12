<template>
	<component :is="userHome" />
</template>

<script>
import { mapGetters } from "vuex";
import Home from "./LandingPage.vue";
import AdminHome from "./AdminHome.vue";
import ClientHome from "./HomePage.vue";

export default {
	name: "HomeJuncture",
	components: {
		Home,
		AdminHome,
		ClientHome,
	},
	props: {},
	computed: {
		...mapGetters(["userRole"]),
		userHome() {
			console.log("User Role: " + this.userRole);
			return `${this.userRole}Home`;
		},
	},
};
</script>

<style scoped lang="scss">
</style>

