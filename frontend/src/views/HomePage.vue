<template>
	<div id="homepage">
		<section>
			<div style="width: 100%">
				<profile-preview></profile-preview>
			</div>
		</section>
		<main>
			<status-input></status-input>
			<post-feed :posts="posts" @reload-posts="reloadPosts()"></post-feed>
		</main>
		<section>
			<chat-links></chat-links>
		</section>
	</div>
</template>

<script>
import ProfilePreview from "../components/ProfilePreview.vue";
import StatusInput from "../components/StatusInput.vue";
import ChatLinks from "../components/ChatLinks.vue";
import PostFeed from "../components/PostFeed.vue";
import { getFeed } from "../services/requests";
import { mapState } from "vuex";

export default {
	name: "HomePage",
	components: {
		ProfilePreview,
		StatusInput,
		ChatLinks,
		PostFeed,
	},
	computed: {
		...mapState({
			user: "user",
		}),
	},
	mounted() {
		this.reloadPosts()
	},
	data: function () {
		return {
			posts: [],
		};
	},
	methods:{
		reloadPosts(){
			getFeed(this.user.id).then((response) => {
			this.posts = response;
		});
		}
	}
};
import "../style.css";
</script>

<style>
#homepage {
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