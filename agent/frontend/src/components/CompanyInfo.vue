<template>
	<div>
		<p>Your Company</p>
		<h4>{{ firmName }}</h4>
		<p v-if="returnedApiToken">Your API Token :</p>
		<p v-if="returnedApiToken">{{ apiToken }}</p>
		<button
			style="width: 220px"
			@click="setApiTokenEnable()"
			v-if="!setApiTokenBoolean"
		>
			Set API Token
		</button>
		<div class="set-api-token" v-if="setApiTokenBoolean">
			<h4>Enter your API Token :</h4>
			<input type="text" v-model="apiToken" />
			<button @click="setApiToken()">Set</button>
		</div>
	</div>
</template>

<script>
import { mapState } from "vuex";
import axios from "axios";
export default {
	name: "CompanyInfo",
	components: {},
	props: {},
	data() {
		return {
			firmName: "",
			apiToken: "",
			returnedApiToken: "",
			setApiTokenBoolean: false,
		};
	},
	computed: {
		...mapState({
			user: "user",
		}),
	},
	mounted() {
		axios
			.post("http://localhost:5003/firm/get", this.user.firmId, {
				headers: {
					"Content-Type": "plain/text",
				},
			})
			.then((response) => {
				this.firmName = response.data.name;
			});
	},
	methods: {
		setApiTokenEnable() {
			this.setApiTokenBoolean = true;
		},
		setApiToken() {
			axios
				.post(
					"http://localhost:5003/firm/setApiToken",
					{
						firmId: this.user.firmId,
						apiToken: this.apiToken,
					},
					{
						headers: {
							"Content-Type": "application/json",
						},
					}
				)
				.then(
					(response) => {
						alert("API Token set successfully :\n" + response.data);
						this.returnedApiToken = response.data;
					},
					(error) => {
						alert(
							"There has been an error while setting the API token :\n" +
								error
						);
					}
				);
		},
	},
};
import "../style.css";
</script>

<style scoped>
div {
	border-radius: 20px;
	background-color: var(--backdrop2);
	padding: 15px;
	display: flex;
	flex-direction: column;
	align-items: center;
	height: 200px;
	/* width: 330px; */
	justify-content: space-around;
}
img {
	width: 55px;
	height: 55px;
	border-radius: 50%;
}
h4 {
	color: white;
	font-size: 40px;
	font-weight: 500;
}
p {
	text-align: center;
	font-size: 22px;
	color: var(--button);
}
</style>