<template>
	<article class="user-post">
		<!-- <router-link to="/profile/id">{{ post.username }}</router-link> -->
		<p>{{ post.authorName }}</p>
		<p style="font-weight: 600">{{ post.title }}</p>
		<p>{{ post.description }}</p>
		<div>
			<button @click="changeVote(1)" :class="{ active: myVote == 1 }">
				👍
			</button>
			{{ post.likes }}
			<button @click="changeVote(-1)" :class="{ active: myVote == -1 }">
				👎
			</button>
			{{ post.likes }}
		</div>
		<div class="comment-container">
			<comment-on-post
				v-for="comment in post.comments"
				:key="comment.id"
				:comment="comment"
			/>
		</div>
		<div class="comment-input" v-if="isCommenting">
			<img
				class="commenting-avatar"
				:src="require('../assets/' + avatar)"
			/>
			<input type="text" v-model="commentText" />
			<button @click="postComment">Post comment</button>
		</div>
		<div class="comment-control">
			{{ post.comments?.length }}
			<button @click="toggleCommenting">💬</button>
		</div>
	</article>
</template>

<script>
// import CommentOnPost from './CommentOnPost.vue';
import { mapState } from "vuex";

export default {
	name: "UserPost",
	components: {
		// CommentOnPost
	},
	props: {
		post: Object,
	},
	data: function () {
		return {
			myVote: 0, // treba da se dobije sa servera
			isCommenting: false,
			commentText: "",
		};
	},
	computed: {
		...mapState({
			user: "user",
		}),
		avatar() {
			return this.user.avatar || "placeholder.png";
		},
	},
	watch: {
		myVote: function (newVote, oldVote) {
			this.$emit(
				"vote",
				this.post.id,
				this.post.votes + newVote - oldVote
			);
		},
	},
	methods: {
		changeVote(newVote) {
			this.myVote = this.myVote == newVote ? 0 : newVote;
		},
		postComment() {
			this.$emit("post-comment", this.post.id, this.commentText);
			this.commentText = "";
			this.isCommenting = false;
		},
		toggleCommenting() {
			this.isCommenting = !this.isCommenting;
			if (!this.isCommenting) {
				this.commentText = "";
			}
		},
	},
};

import "../style.css";
</script>

<style scoped>
.user-post {
	border-radius: 5px;
	background-color: var(--backdrop);
	padding: 15px;
	display: flex;
	flex-direction: column;
	justify-content: space-around;
	margin: 5px 0px;
	width: 100%;
}
div {
	display: flex;
	align-items: center;
	justify-content: left;
	width: 100%;
}
img {
	width: 50px;
	height: 50px;
	border-radius: 50%;
	margin-right: 10px;
}
p {
	color: white;
	font-size: 20px;
}
button {
	font-size: 15px;
	height: auto;
	width: auto;
	margin: 5px;
	padding: 5px;
}
button.active {
	background-color: var(--yellow4);
}
.comment-control {
	display: flex;
	align-items: center;
	justify-content: right;
	width: 100%;
}
.commenting-avatar {
	width: 35px;
	height: 35px;
	border-radius: 50%;
	margin-right: 10px;
}
.comment-container {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	width: 100%;
}
</style>