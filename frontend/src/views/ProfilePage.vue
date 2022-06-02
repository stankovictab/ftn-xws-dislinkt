<template>
	<div id="profile-page">
		<section>
		</section>
		<main>
			<profile-info :userId="profileOwnerId"></profile-info>
			<post-feed :posts="posts"></post-feed>
		</main>
		<section>
			<friend-requests v-if="profileOwnerId === user.id" :userId="profileOwnerId"></friend-requests>
		</section>
	</div>
</template>

<script>
import PostFeed from '../components/PostFeed.vue';
import ProfileInfo from "../components/ProfileInfo.vue";
import FriendRequests from "../components/FriendRequests.vue";
import {mapState} from "vuex";
import {getOwnPosts} from "../services/requests";

export default {
	name: "ProfilePage",
	components: {
		PostFeed,
		ProfileInfo,
		FriendRequests
	},
	props: {
		profileOwnerId: {
			type: String,
			required: true,
		},
	},
	data: function () {
		return {
			posts: [],
		};
	},
	computed: {
		...mapState({
			user: "user",
		}),
	},
	mounted() {
		getOwnPosts(this.profileOwnerId).then(response => {
			this.posts = response;
		});
	},
};
import "../style.css";
</script>

<style>
#profile-page {
	display: flex;
	justify-content: space-evenly;
}

main {
	width: 35%;
}
section {
	width: 20%;
}
</style>