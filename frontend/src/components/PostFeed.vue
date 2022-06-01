<template>
	<div id="post-feed" v-if="posts">
		<user-post
			v-for="post in posts"
			:key="post.id"
			:post="post"
			@vote="applyVote"
			@post-comment="postComment"
		/>
	</div>
</template>

<script>
// import { getFeed } from "../services/requests";
import UserPost from "../components/UserPost.vue";
import { mapState } from "vuex";
import { ref } from "vue";
export default {
	name: "PostFeed",
	components: { UserPost },
	setup() {
		var posts = ref(null);
		axios
			.post(
				"http://localhost:5002/post/getFeed",
				"629783322704f2575da2d8bf",
				{
					headers: { "Content-Type": "plain/text" },
				}
			)
			.then(function (response) {
				console.log(response.data[13].description);
				posts.value = response.data;
				// return response.data;
			});
		return {
			posts,
		};
	},
	// data: function () {
	// 	return {
	// 		posts: [],
	// 	};
	// },
	computed: {
		...mapState({
			user: "user",
		}),
	},
	created() {
		// this.posts = getFeed(this.user.id);
		// getFeed(this.user.id).then(function (response) {
		// 	console.log("REPSONES:");
		// 	console.log(response);
		// 	this.posts = response;
		// });
	},
	methods: {
		applyVote(postId, newVotes) {
			this.posts.find((post) => post.id === postId).votes = newVotes;
			// pozivamo metodu updatePost() iz services/requests.js
		},
		randomInt() {
			return Math.floor(Math.random() * 10);
		},
		postComment(postId, commentText) {
			console.log("posting comment");
			this.posts
				.find((post) => post.id === postId)
				.comments.push({
					id: this.posts[this.posts.length - 1].id + 1,
					avatar: this.user.avatar,
					text: commentText,
				});
			// pozivamo metodu postComment() iz services/requests.js
		},
	},
};
import "../style.css";
import axios from "axios";
</script>

<style scoped>
#post-feed {
	padding: 15px;
	display: flex;
	flex-direction: column;
	justify-content: space-around;
	align-items: center;
}
</style>