<template>
	<div>
		<!-- TODO: Add unregisteredSearch check -->
		<header v-if="hasRole">
			<p class="mini-logo">Dislinkt</p>
			<!-- TODO: Search on Enter -->
			<input
				class="search-input"
				placeholder="Search Job Offers on Dislinkt"
				@keyup.enter="searchJobOffers"
				v-model="searchTerm"
			/>
			<button @click="logout">
				{{ isLoggedIn ? "Log Out" : "Sign Up" }}
			</button>
		</header>
		<link rel="preconnect" href="https://fonts.googleapis.com" />
		<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
		<link
			href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,300;0,400;0,500;0,700;0,800;1,900&display=swap"
			rel="stylesheet"
		/>
		<router-view />
	</div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
	name: "App",
	components: {},
	data() {
		return {
			searchTerm: "",
		};
	},
	computed: {
		...mapGetters(["isLoggedIn", "hasRole"]),
	},
	methods: {
		logout() {
			this.$store.commit("setToken", "");
			this.$store.commit("setUser", { username: "", role: "" });
			this.$router.push("/");
		},
		searchJobOffers() {
			this.$router.push({
				name: "Search",
				params: {
					searchTerm: this.searchTerm,
				},
			});
		},
	},
};
import "./style.css";
</script>

<style scoped>
header {
	background: var(--backdrop3);
	height: 60px;
	display: flex;
	justify-content: center;
	align-items: center;
	margin-bottom: 70px;
}
header p {
	position: absolute;
	left: 10%;
}
header button {
	position: absolute;
	right: 10%;
	height: 35px;
	width: 125px;
}
header input {
	font-size: 20px;
}
</style>
