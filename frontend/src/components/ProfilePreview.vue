<template>
	<div>
		<img :src="require('../assets/' + avatar)" />
		<h4>{{ user.username }}</h4>
		<!-- <p>{{ user.jobDesc }}</p> -->
		<!-- <p>Working at {{ user.company }}</p> -->
		<router-link :to="`/profile/${user.id}`">
			<button class="alternate-button">View Profile</button>
		</router-link>
	</div>
</template>

<script>
import { mapState } from "vuex";
export default {
	name: "ProfilePreview",
	components: {},
	methods: {},
	computed: {
		...mapState({
			user: "user",
		}),
		avatar() {
			return this.user.avatar || "placeholder.png";
		},
	},
};
import "../style.css";
</script>

<style scoped>
div {
	border-radius: 20px;
	background-color: var(--backdrop2);
	padding: 15px;
	display: flex;
	flex-direction: column;
	align-items: center;
	height: 200px;
	width: 330px;
	justify-content: space-around;
}
img {
	width: 55px;
	height: 55px;
	border-radius: 50%;
}
h4 {
	color: white;
	font-weight: 500;
}
</style>