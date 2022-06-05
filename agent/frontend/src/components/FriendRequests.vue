<template>
	<nav>
		<h2>Friend Requests</h2>
		<friend-request
			v-for="request in friendRequests"
			:key="request.id"
			:request="request"
			@approve="removeFriendRequest"
			@decline="removeFriendRequest"
		/>
	</nav>
</template>

<script>
import { getFriendRequests } from "../services/requests";
import FriendRequest from "../components/FriendRequest.vue";

export default {
	name: "ChatLinks",
	components: { FriendRequest },
    props: {
        userId: String
    },
	data: function () {
		return {
			friendRequests: [],
		};
	},
	created() {
		getFriendRequests(this.userId).then((response) => {
			this.friendRequests = response;
		});
	},
	methods: {
		removeFriendRequest(requestId) {
			this.friendRequests = this.friendRequests.filter((request) => {
				return request.id !== requestId;
			});
		},
	},
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