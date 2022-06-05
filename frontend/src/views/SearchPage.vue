<template>
	<div style="display: flex">
		<!-- TODO: Remove header and css if implemented in App.vue -->
		<div class="search-results-parent">
			<p>
				Users matching search
				<span style="color: var(--yellow3)">{{
					searchTermDisplay
				}}</span>
			</p>
			<!-- TODO: Add v-if to list -->
			<p
				style="margin-top: 50px; color: var(--text2)"
				v-if="!userSearchResults"
			>
				No results found.
			</p>
			<div class="search-results-child">
				<div class="search-result" v-for="i in userSearchResults" :key="i">
					<!-- <p>{{ i }}</p> -->
					<img src="../assets/placeholder.png" />
					<h3>{{ i.firstName }} {{ i.lastName }}</h3>
					<router-link style="font-size: 20px" :to="`/profile/${i.id}`">@{{ i.username }}</router-link>
					<!-- <p>{{ i.workExperience }}</p> -->
					<button>View Profile</button>
				</div>
			</div>
		</div>
		<div class="search-results-parent" v-if="isLoggedIn">
			<p>
				Posts matching search
				<span style="color: var(--yellow3)">{{
					searchTermDisplay
				}}</span>
			</p>
			<p
				style="margin-top: 50px; color: var(--text2)"
				v-if="!postsSearchResults"
			>
				No results found.
			</p>
			<div class="search-results-child">
				<div class="search-result" v-for="i in postsSearchResults" :key="i">
					<h3>{{ i.title }} {{ i.description }}</h3>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
// import { ref } from "@vue/reactivity";
import axios from "axios";
import { mapGetters } from "vuex";

export default {
	name: "SearchPage",
	props: {
		searchTerm: String,
	},
	methods: {
		search(searchBy) {
			var me = this;
			this.userSearchResults = null;
			this.searchTermDisplay = searchBy;
			// TODO: Search only by searchTerm, without splitting?
			axios
				.post(
					"http://localhost:5001/user/find",
					{
						firstName: searchBy.split(" ")[0],
						lastName: searchBy.split(" ")[1],
					},
					{
						headers: { "Content-Type": "application/json" },
					}
				)
				.then(function (response) {
					me.userSearchResults = response.data;
				});
			if(this.isLoggedIn){
				axios
					.post(
						"http://localhost:5002/post/searchOffers",
						{
							query: searchBy,
							field: ""
						},
						{
							headers: { "Content-Type": "application/json" },
						}
					)
					.then(function (response) {
						me.postSearchResults = response.data;
					});
			}
		},
		logout() {
			this.$store.commit("setToken", "");
			this.$store.commit("setUser", { username: "", role: "" });
			this.$router.push("/");
		},
	},
	data() {
		return {
			searchTermDisplay: "",
			inPlaceSearchTerm: this.searchTerm,
			userSearchResults: null,
			postsSearchResults: null,
		};
	},
	computed: {
		...mapGetters(["isLoggedIn", "hasRole"]),
	},
	mounted() {
		this.search(this.searchTerm);
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

	min-height: 0px;
	min-width: 0px;
	flex-basis: 50%;
	flex-grow: 1;
}
.search-results-parent p {
	color: var(--text1);
}
.search-results-child {
	display: flex;
	flex-direction: row;
	flex-wrap: wrap;
	justify-content: center;
}
.search-result {
	height: 250px;
	width: 320px;
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
	font-size: 26px;
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