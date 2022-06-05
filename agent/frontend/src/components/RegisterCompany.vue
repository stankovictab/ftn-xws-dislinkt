<template>
	<div>
		<p>No company registered.</p>
		<h4>Register a Company!</h4>
		<router-link
			:to="{
				name: 'Register',
				params: {
					registrationType: 'company',
					ownerId: user.id,
					ownerName: user.firstName + ' ' + user.lastName,
				},
			}"
		>
			<button class="alternate-button">Register</button>
		</router-link>
	</div>
</template>

<script>
import { mapState } from "vuex";
export default {
	name: "RegisterCompany",
	components: {},
	methods: {},
	computed: {
		...mapState({
			user: "user",
		}),
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
	font-weight: 500;
}
p {
	text-align: center;
	font-size: 22px;
	color: var(--button);
}
</style>