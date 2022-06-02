<template>
	<nav v-if="friends">
		<h2>Chat</h2>
		<chat-link
			v-for="friend in friends"
			:key="friend.username"
			:friend="friend"
		/>
	</nav>
</template>

<script>
import { getFriends } from "../services/requests";
import ChatLink from "../components/ChatLink.vue";

export default {
	name: "ChatLinks",
	components: { ChatLink },
	data: function () {
		return {
			friends: [],
		};
	},
	created() {
		getFriends().then((response) => {
			this.friends = response.data;
		});
	},
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