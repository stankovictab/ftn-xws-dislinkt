<template>
	<nav>
		<h2>Companies</h2>
		<h4 style="text-align: center; font-size: 20px">
			Leave some comments...
		</h4>
		<companies-list-item
			v-for="company in companies"
			:key="company"
			:company="company"
		/>
	</nav>
</template>

<script>
// import { getFriends } from "../services/requests";
import CompaniesListItem from "../components/CompaniesListItem.vue";
import axios from "axios";

export default {
	name: "CompaniesList",
	components: { CompaniesListItem },
	data: function () {
		return {
			companies: "test",
		};
	},
	mounted() {
		axios.post("http://localhost:5003/firm/getAll").then((response) => {
			this.companies = response.data;
		});
	},
	// created() {
	// 	getFriends().then((response) => {
	// 		this.friends = response.data;
	// 	});
	// },
	methods: {},
};
import "../style.css";
</script>

<style scoped>
h2 {
	font-size: 30px;
	color: white;
	font-weight: 600;
}
nav {
	border-radius: 20px;
	background-color: var(--backdrop2);
	padding: 25px 40px;
	display: flex;
	flex-direction: column;
	justify-content: space-around;
	align-items: center;
}
</style>