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
			<p
				style="margin-top: 50px; color: var(--text2)"
				v-if="searchResults == null"
			>
				No results found.
			</p>
			<div class="search-results-child">
				<div class="search-result" v-for="i in searchResults" :key="i">
					<!-- <p>{{ i }}</p> -->
					<img src="../assets/placeholder.png" />
					<h3>{{ i.firstName }} {{ i.lastName }}</h3>
					<p style="font-size: 20px">@{{ i.username }}</p>
					<p>{{ i.workExperience }}</p>
					<button>View Profile</button>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
// import { ref } from "@vue/reactivity";
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
		// var searchResults = ref(null);
		return {
			searchResults: Object,
		};
	},
	mounted() {
		var me = this;
		this.searchResults = null;
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
				me.searchResults = response.data;
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
.search-results-child {
	display: flex;
	flex-direction: row;
}
.search-result {
	height: 270px;
	width: 370px;
	margin: 20px 40px;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: space-between;
	background-color: var(--backdrop);
	border-radius: 20px;
	padding: 30px 40px;
}
.search-result h3 {
	color: white;
	font-weight: 600;
	font-size: 32px;
}
.search-result button {
	width: 200px;
	height: 40px;
	border-radius: 20px;
}
.search-result p {
	font-weight: 400;
}
.search-result img {
	width: 75px;
	height: 75px;
	border-radius: 50%;
}
</style>