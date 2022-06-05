<template>
	<div class="main-container">
		<div class="sub-container">
			<img :src="require('../assets/' + avatar)" />
			<h4>{{ user.username }}</h4>
		</div>
		<textarea
			class="comment"
			placeholder="Job Offer Title"
			v-model="title"
		></textarea>
		<textarea
			class="comment"
			placeholder="Job Offer Details"
			v-model="description"
		></textarea>
		<div style="display: flex; justify-content: space-between">
			<div>
				<label for="share-to-dislinkt">Share to dislinkt?	</label>
				<input id="share-to-dislinkt" type="checkbox" v-model="shareToDislinkt" />
			</div>
			<button class="alternate-button" @click="submitPost()">
				Submit
			</button>
		</div>
	</div>
</template>

<script>
import { mapState } from "vuex";
import { createOffer} from "../services/requests";

export default {
	name: "StatusInput",
	components: {},
	data() {
		return {
			title: "",
			description: "",
			embedLinkBool: false,
			embedLink: "",
			shareToDislinkt: false,
		};
	},
	methods: {
		embedLinkEnable() {
			this.embedLinkBool = true;
		},
		submitPost() {
			createOffer(this.user.firmId, this.title, this.description, this.shareToDislinkt).then((response) => {
				console.log(response);
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