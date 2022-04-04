<template>
	<header v-if="hasRole">
		<input placeholder="Search Dislinkt"/>
		<button @click="logout">{{isLoggedIn ? "Log Out" : "Sign Up"}}</button>
	</header>
	<link rel="preconnect" href="https://fonts.googleapis.com" />
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
	<link
		href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,300;0,400;0,500;0,700;0,800;1,900&display=swap"
		rel="stylesheet"
	/>
	<router-view />
</template>

<script>
import { mapGetters } from "vuex";

export default {
	name: "App",
	components: {},
	computed: {
    ...mapGetters(["isLoggedIn", "hasRole"]),
  },
	methods: {
		logout(){
			this.$store.commit("setToken", "");
			this.$store.commit("setUser", {"username": "", "role": ""});
			this.$router.push("/");
		},
	},
};
import "./style.css";
</script>

<style scoped>
header {
	background: #0E131C;
	height: 50px;
	display:flex;
	justify-content:center;
	align-items:center;
	margin-bottom: 20px;
}
header button {
	position: absolute;
	top: 5px;
	right: 2%;
}

</style>
