<template>
	<div class="main-container">
		<div class="sub-container">
			<img :src="require('../assets/' + avatar)" />
			<h4>{{ user.username }}</h4>
		</div>
		<textarea
			class="comment"
			placeholder="Post Title"
			v-model="title"
		></textarea>
		<textarea
			class="comment"
			placeholder="What's on your mind?"
			v-model="description"
		></textarea>
		<textarea
			class="comment"
			placeholder="Embed link here."
			v-model="embedLink"
			v-if="this.embedLinkBool == true"
		></textarea>
		<div style="display: flex; justify-content: space-between">
			<button class="alternate-button" @click="addImage()">
				Add Image
			</button>
			<button
				class="alternate-button"
				@click="embedLinkEnable()"
				v-if="this.embedLinkBool == false"
			>
				Embed Link
			</button>
			<button class="alternate-button" @click="submitPost()">
				Submit
			</button>
		</div>
	</div>
</template>

<script>
import { mapState } from "vuex";
import axios from "axios";
export default {
	name: "StatusInput",
	components: {},
	data() {
		return {
			title: "",
			description: "",
			embedLinkBool: false,
			embedLink: "",
		};
	},
	methods: {
		embedLinkEnable() {
			this.embedLinkBool = true;
		},
		submitPost() {
			axios
				.post(
					"http://localhost:5002/post/create",
					{
						title: this.title,
						description: this.description,
						embedLink: this.embedLink,
						userId: this.user.id,
						authorName:
							this.user.firstName + " " + this.user.lastName,
					},
					{
						headers: {
							// TODO: Auth
							// Authorization: "Bearer " + this.$store.state.token
							"Content-Type": "application/json",
						},
					}
				)
				.then((response) => {
					console.log(response);
					alert("Post created!");
				});
		},
	},
	computed: {
		...mapState({
			user: "user",
		}),

		avatar() {
			return this.user.avatar || "placeholder.png";
		},
	},
};
import "../style.css";
</script>

<style scoped>
.main-container {
	border-radius: 20px;
	background-color: var(--backdrop2);
	padding: 25px 40px;
	display: flex;
	flex-direction: column;
	justify-content: space-around;
	gap: 25px;
}
.sub-container {
	display: flex;
	flex-direction: row;
	align-items: center;
	gap: 20px;
}
img {
	width: 50px;
	height: 50px;
	border-radius: 50%;
}
input {
	width: 60%;
}
h4 {
	color: white;
	font-weight: 500;
}
</style>