<template>
	<div class="main">
		<h4>{{ company.name }}</h4>
		<div>
			<p>Comment</p>
			<input type="text" v-model="comment" v-if="!commentMade" />
			<h5 v-if="commentMade">{{ comment }}</h5>
		</div>
		<div>
			<p>Rating</p>
			<input type="text" v-model="rating" v-if="!commentMade" />
			<h5 v-if="commentMade">{{ rating }}</h5>
		</div>
		<div>
			<p>Salary</p>
			<input type="text" v-model="salary" v-if="!commentMade" />
			<h5 v-if="commentMade">{{ salary }}</h5>
		</div>
		<button @click="submit()" v-if="!commentMade">Comment</button>
	</div>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";
export default {
	name: "CompaniesListItem",
	components: {},
	data() {
		return {
			commentMade: false,
			comment: "",
			rating: "",
			salary: "",
		};
	},
	computed: {
		...mapState({
			user: "user",
		}),
	},
	props: {
		company: Object,
	},
	mounted() {
		this.reloadComments();
	},
	methods: {
		reloadComments() {
			axios
				.post(
					"http://localhost:5003/comment/getByUserIdAndFirmId",
					{
						firmId: this.company.id,
						userId: this.user.id,
					},
					{
						headers: {
							"Content-Type": "application/json",
						},
					}
				)
				.then((response) => {
					if (response.data == null) {
						this.commentMade = false;
						return;
					}
					this.commentMade = true;
					this.comment = response.data.comment;
					this.rating = response.data.rating;
					this.salary = response.data.salary;
				});
		},
		submit() {
			axios
				.post(
					"http://localhost:5003/comment/create",
					{
						firmId: this.company.id,
						userId: this.user.id,
						comment: this.comment,
						rating: this.rating,
						salary: this.salary,
					},
					{
						headers: {
							"Content-Type": "application/json",
						},
					}
				)
				.then((response) => {
					alert("Comment created successfully :\n" + response.data);
					this.reloadComments();
				});
		},
	},
};
</script>

<style scoped>
.main {
	background-color: var(--backdrop3);
	border-radius: 10px;
	padding: 10px;
	display: flex;
	flex-direction: column;
	align-items: flex-start;
	gap: 20px;
	margin: 5px 0px;
	width: 100%;
}
.main div {
	width: -webkit-fill-available;
}
.main input {
	width: -webkit-fill-available;
}
p {
	font-size: 20px;
	margin: 0px;
}
</style>