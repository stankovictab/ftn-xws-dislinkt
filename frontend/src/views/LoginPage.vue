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
			<!-- <div style="margin-top: 20px"></div> -->
			<div class="input-div">
				<label>Username</label>
				<input id="username" />
			</div>
			<div class="input-div">
				<label>Password</label>
				<input id="password" type="password" />
			</div>
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
	margin-bottom: 60px;
}
.login-bottom {
	/* backdrop-filter: blur(4px) invert(0.05);*/
	background-color: var(--backdrop);
	padding: 20px 35px 30px 35px;
	border-radius: 20px;
	gap: 15px;
}
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