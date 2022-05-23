<template>
	<div class="main-container">
		<div class="sub-container">
			<img :src="require('../assets/' + avatar)" />
			<h4>{{ user.username }}</h4>
		</div>
		<textarea class="comment" placeholder="What's on your mind?"></textarea>
		<button class="alternate-button">Add Image</button>
	</div>
</template>

<script>
import { mapState } from "vuex";
export default {
	name: "StatusInput",
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
.main-container {
	border-radius: 20px;
	background-color: var(--backdrop2);
	padding: 25px 40px;
	display: flex;
	flex-direction: column;
	justify-content: space-around;
	align-items: flex-start;
	gap: 25px;
}
.sub-container {
	display: flex;
	flex-direction: row;
	align-items: center;
	gap: 20px;
}
img {
	width: 50px;
	height: 50px;
	border-radius: 50%;
}
input {
	width: 60%;
}
h4 {
	color: white;
	font-weight: 500;
}
</style>