<template>
	<div>
		<img :src="require('../assets/placeholder.png')" />
		<router-link to="/profile/id">{{ request.username }}</router-link>
        <button @click="approveFollow()">Accept</button>
        <button @click="declineFollow()">Decline</button>
	</div>
</template>

<script>
import { approveFollow, declineFollow } from "../services/requests";
import { mapState } from "vuex";

export default {
	name: "FriendRequest",
	components: {},
	props: {
		request: Object,
	},
	computed : {
		...mapState({
			user: "user",
		}),
	},
	methods: {
		approveFollow() {
			approveFollow(this.user.id, this.request.id).then(() => {
				this.$emit("approve", this.request.id);
			});
		},
		declineFollow() {
			declineFollow(this.user.id, this.request.id).then(() => {
				this.$emit("decline", this.request.id);
			});
		},
	},
};
import "../style.css";
</script>

<style scoped>
div {
	display: flex;
	align-items: center;
	margin: 5px 0px;
	width: 100%;
}
img {
	width: 50px;
	height: 50px;
	border-radius: 50%;
	margin-right: 10px;
}
a {
	color: white;
	font-size: 20px;
	font-weight: 500;
}
a:hover {
	color: var(--yellow4);
}
</style>