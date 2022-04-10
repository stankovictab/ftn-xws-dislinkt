<template>
	<div>
		<!-- TODO: Remove header and css if implemented in App.vue -->
		<header>
			<p class="mini-logo">Dislinkt</p>
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
		<div class="search-results-parent">
			<p>
				Search results for
				<span style="color: var(--yellow3)">{{ searchTerm }}</span>
			</p>
			<!-- TODO: Add v-if to list -->
			<p style="margin-top: 50px; color: var(--text2)">
				No results found.
			</p>
			<div class="search-results-child">
				<p>{{ searchResults }}</p>
			</div>
		</div>
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
		search() {
			// TODO: Add search functionality
			alert("TODO");
		},
		logout() {
			this.$store.commit("setToken", "");
			this.$store.commit("setUser", { username: "", role: "" });
			this.$router.push("/");
		},
	},
	data() {
		var searchResults = ref(null);
		return {
			searchResults,
		};
	},
	mounted() {
		// this.searchResults = "aaaaaaaa";
		// TODO: Can we call search() here?
		// TODO: Search only by searchTerm
		axios
			.post(
				"http://localhost:5001/user/find",
				{
					firstName: this.searchTerm.split(" ")[0],
					lastName: this.searchTerm.split(" ")[1],
				},
				{
					headers: { "Content-Type": "application/json" },
				}
			)
			.then(function (response) {
				// TODO: Add search results into a list
				console.log(response.data);
				console.log(response.data[0].username);
				this.searchResults.value = response.data[0].username;
			});
	},
};
import "../style.css";
import { ref } from "@vue/reactivity";
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
.search-results-parent {
	display: flex;
	align-items: center;
	flex-direction: column;
}
.search-results-parent p {
	color: var(--text1);
}
</style>