<template>
	<div id="post-feed" v-if="posts">
		<user-post
			v-for="post in posts"
			:key="post.id"
			:post="post"
			@post-comment="postComment"
			@voted="$emit('reload-posts')"
		/>
	</div>
</template>

<script>
import UserPost from "../components/UserPost.vue";
import { mapState } from "vuex";

export default {
	name: "PostFeed",
	components: { UserPost },
	props: {
		posts: Array,
	},
	
	computed: {
		...mapState({
			user: "user",
		}),
	},
	
	methods: {
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