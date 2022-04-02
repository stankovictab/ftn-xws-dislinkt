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
			<h4>Welcome to Dislinkt!</h4>
			<h5>General Information</h5>
			<div class="multiple-input-div">
				<div class="input-div">
					<label>Username</label>
					<input id="username" placeholder="johnjohnson" />
				</div>
				<div class="input-div">
					<label>Password</label>
					<input
						id="password"
						type="password"
						placeholder="•••••••••••••••••"
					/>
				</div>
			</div>
			<div class="multiple-input-div">
				<div class="input-div">
					<label>First Name</label>
					<input id="firstname" placeholder="John" />
				</div>
				<div class="input-div">
					<label>Last Name</label>
					<input id="lastname" placeholder="Johnson" />
				</div>
			</div>
			<div class="multiple-input-div">
				<div class="input-div">
					<label>Email</label>
					<input
						id="email"
						type="email"
						placeholder="johnjohnson@gmail.com"
					/>
				</div>
				<div class="input-div">
					<label>Phone Number</label>
					<input
						id="phonenumber"
						type="tel"
						placeholder="+381600000000"
					/>
				</div>
			</div>
			<div class="multiple-input-div">
				<div class="input-div">
					<label>Gender</label>
					<select id="gender">
						<option value="" disabled selected></option>
						<option value="male">Male</option>
						<option value="female">Female</option>
					</select>
				</div>
				<div class="input-div">
					<label>Date of Birth</label>
					<input id="dateofbirth" type="date" />
				</div>
			</div>
			<h5>Biography & Work Experience</h5>
			<div class="input-div-large">
				<label>Biography</label>
				<textarea id="biography" cols="20" rows="3"></textarea>
			</div>
			<div class="input-div-large">
				<label>Work Experience</label>
				<textarea id="workexperience" cols="20" rows="3"></textarea>
			</div>
			<div class="input-div-large">
				<label>Studies</label>
				<input id="studies" />
			</div>
			<div class="input-div-large">
				<label>Skills</label>
				<textarea id="skills" cols="20" rows="3"></textarea>
			</div>
			<div class="input-div-large">
				<label>Interests</label>
				<textarea id="interests" cols="20" rows="3"></textarea>
			</div>
			<h5>Privacy</h5>
			<div id="privacy-radio-buttons" class="input-div-large">
				<div class="checkbox-div">
					<input type="checkbox" />
					<label for="public">Private Account</label>
				</div>
			</div>
			<button @click="register">Sign Up</button>
		</div>
	</div>
</template>
<script>
export default {
	name: "RegisterPage",
	components: {},
	methods: {
		register() {
			alert("registrujem se mraleee");
		},
	},
};
import "../style.css";
</script>
<style scoped>
.multiple-input-div {
	display: flex;
	flex-direction: row;
	gap: 80px;
}
.input-div {
	display: flex;
	flex-direction: column;
}
.input-div-large {
	display: flex;
	flex-direction: column;
	width: 100%;
}
.input-div-large input {
	width: 97.3%;
}
.checkbox-div {
	display: flex;
	flex-direction: row;
	justify-content: center;
	align-items: center;
	gap: 15px;
}
.checkbox-div input {
	width: 18px;
}
input {
	margin-top: 2px;
	height: 32px;
	width: 280px;
}
select {
	margin-top: 2px;
	height: 32px;
	width: 300px;
}
button {
	margin-top: 15px;
}
textarea {
	max-width: 660px;
	min-width: 660px;
	min-height: 20px;
}
</style>