<template>
	<div>
		<!-- TODO: Remove header and css if implemented in App.vue -->
		<header>
			<input
				class="search-input"
				placeholder="Search Dislinkt"
				@keyup.enter="search()"
			/>
			<!-- TODO: Add v-model to input -->
			<button @click="logout">
				{{ isLoggedIn ? "Log Out" : "Go Back" }}
			</button>
		</header>
		<p style="color: pink">
			{{ searchTerm }}
		</p>
	</div>
</template>

<script>
import axios from "axios";

export default {
	name: "SearchPage",
	props: {
		searchTerm: String,
	},
	methods: {
		logout() {
			this.$store.commit("setToken", "");
			this.$store.commit("setUser", { username: "", role: "" });
			this.$router.push("/");
		},
	},
	mounted() {
		axios
			.post("http://localhost:5001/user/findByName", this.searchTerm, {
				headers: { "Content-Type": "text/plain" },
			})
			.then(function (response) {
				console.log(response.data);
			});
	},
};
import "../style.css";
</script>

<style scoped>
header {
	background: #0e131c;
	height: 50px;
	display: flex;
	justify-content: center;
	align-items: center;
	margin-bottom: 20px;
}
header button {
	position: absolute;
	top: 5px;
	right: 2%;
}
</style>