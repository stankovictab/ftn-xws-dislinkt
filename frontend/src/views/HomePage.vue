<template>
	<div id="homepage">
		<section>
			<div
				style="
					display: flex;
					flex-direction: column;
					align-items: center;
					gap: 30px;
				"
			>
				<div style="width: 100%">
					<profile-preview></profile-preview>
				</div>
				<div>
					<router-link to="/companySearch">
						<button style="width: 220px">Search Job Offers</button>
					</router-link>
				</div>
			</div>
		</section>
		<main>
			<status-input></status-input>
			<post-feed :posts="posts"></post-feed>
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
		getFeed(this.user.id).then((response) => {
			this.posts = response;
		});
	},
	data: function () {
		return {
			posts: [],
		};
	},
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