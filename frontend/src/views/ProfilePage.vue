<template>
	<div id="profile-page">
		<section>
			<div
				style="
					display: flex;
					flex-direction: column;
					align-items: center;
					gap: 30px;
					max-width: 383px;
				"
			>
				<button
					style="width: 220px"
					@click="generateApiToken()"
					v-if="!generateApiTokenBoolean"
				>
					Generate API Token
				</button>
				<div class="generate-api-token" v-if="generateApiTokenBoolean">
					<h4>Your API Token :</h4>
					<p>{{ user.apiToken }}</p>
				</div>
			</div>
		</section>
		<main>
			<profile-info :userId="profileOwnerId"></profile-info>
			<post-feed :posts="posts" @reload-posts="reloadPosts()"></post-feed>
		</main>
		<section>
			<friend-requests
				v-if="profileOwnerId === user.id"
				:userId="profileOwnerId"
			></friend-requests>
		</section>
	</div>
</template>

<script>
import PostFeed from "../components/PostFeed.vue";
import ProfileInfo from "../components/ProfileInfo.vue";
import FriendRequests from "../components/FriendRequests.vue";
import { mapState } from "vuex";
import { getOwnPosts } from "../services/requests";
import axios from "axios";

export default {
	name: "ProfilePage",
	components: {
		PostFeed,
		ProfileInfo,
		FriendRequests,
	},
	props: {
		profileOwnerId: {
			type: String,
			required: true,
		},
	},
	data() {
		return {
			generateApiTokenBoolean: false,
			posts: [],
		};
	},
	computed: {
		...mapState({
			user: "user",
		}),
	},
	methods: {
		generateApiToken() {
			this.generateApiTokenBoolean = true;
			axios
				.post(
					"http://localhost:5001/user/generateApiToken",
					this.user.id,
					{
						headers: {
							"Content-Type": "plain/text",
						},
					}
				)
				.then(
					(response) => {
						console.log(response.data);
						this.user.apiToken = response.data;
					},
					(error) => {
						console.log(error);
					}
				);
		},
		reloadPosts(){
			getOwnPosts(this.profileOwnerId).then(response => {
				this.posts = response;
				console.log(this.posts )
			});
		}
	},
	mounted() {
		if (this.user.apiToken != null) {
			this.generateApiTokenBoolean = true;
		}
		this.reloadPosts()
	}
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
	max-width: 383px;
}
.generate-api-token {
	display: flex;
	flex-direction: column;
	gap: 10px;
	background-color: var(--backdrop2);
	color: white;
	padding: 20px;
	border-radius: 20px;
	max-width: 383px;
}
.generate-api-token h4 {
	color: white;
	font-size: 20px;
}
.generate-api-token p {
	color: white;
	font-size: 13px;
}
</style>