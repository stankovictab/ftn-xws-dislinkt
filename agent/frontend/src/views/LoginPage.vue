<template>
	<div class="register-and-login">
		<div class="register-and-login-top">
			<h1>
				<router-link to="/"> Agent App </router-link>
			</h1>
			<p>Another place to connect!</p>
		</div>
		<div
			class="register-and-login-bottom"
			style="display: flex; flex-direction: column; align-items: center"
		>
			<h4>Welcome back!</h4>
			<div class="input-div">
				<label>Username</label>
				<input id="username" v-model="username" />
			</div>
			<div class="input-div">
				<label>Password</label>
				<input
					id="password"
					type="password"
					v-model="password"
					@keyup.enter="login"
				/>
			</div>
			<button @click="login">Log In</button>
		</div>
	</div>
</template>

<script>
import { login } from "../services/requests";
import { mapGetters } from "vuex";

export default {
	name: "LoginPage",
	components: {},
	data: function () {
		return {
			username: "",
			password: "",
		};
	},
	computed: {
		...mapGetters(["hasRole"]),
	},
	created() {
		if (this.hasRole) {
			this.$router.push("/");
		}
	},
	methods: {
		async login() {
			const res = await login({
				username: this.username,
				passwordInput: this.password,
			});
			this.$store.commit("setToken", res.data.id);
			this.$store.commit("setUser", res.data);
			this.$router.push("/");
		},
	},
};
import "../style.css";
</script>

<style scoped>
.input-div {
	display: flex;
	flex-direction: column;
}
input {
	margin-top: 2px;
	height: 32px;
	width: 280px;
}
button {
	margin-top: 15px;
}
</style>