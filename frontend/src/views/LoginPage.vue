<template>
	<div class="register-and-login">
		<div class="register-and-login-top">
			<h1>
				<router-link to="/"> Dislinkt </router-link>
			</h1>
			<p>The place to connect.</p>
		</div>
		<div
			class="register-and-login-bottom"
			style="display: flex; flex-direction: column; align-items: center"
		>
			<h4>Welcome back!</h4>
			<div class="input-div">
				<label>Username</label>
				<input id="username" />
			</div>
			<div class="input-div">
				<label>Password</label>
				<input id="password" type="password" />
			</div>
			<button @click="login">Log In</button>
		</div>
	</div>
</template>

<script>
export default {
	name: "LoginPage",
	components: {},
	methods: {
		login() {
			alert("loginujem se mraleee");
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