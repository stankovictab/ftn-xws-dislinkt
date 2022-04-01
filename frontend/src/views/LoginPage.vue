<template>
	<div class="login">
		<div class="login-top">
			<h1>Dislinkt</h1>
			<p>The place to connect.</p>
		</div>
		<div
			class="login-bottom"
			style="display: flex; flex-direction: column; align-items: center"
		>
			<h4>Welcome back!</h4>
			<div style="margin-top: 20px"></div>
			<div class="input-div">
				<label for="username">Username</label>
				<input id="username" />
			</div>
			<div class="input-div">
				<label for="password">Password</label>
				<input id="password" type="password" />
			</div>
			<div style="margin-top: 40px"></div>
			<button @click="login">Sign in</button>
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

<style>
.login {
	margin-top: 50px;
	display: flex;
	flex-direction: column;
	align-items: center;
}
.login-top {
	display: flex;
	flex-direction: column;
	align-items: center;
	margin-bottom: 20px;
}
.login-bottom {
	background-color: #0e1c31;
	padding: 20px 50px 30px 50px;
	border-radius: 20px;
}
input {
	border: none;
}
.input-div {
	display: flex;
	flex-direction: column;
}
</style>