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
			<h4 v-if="registrationType == 'user'">Welcome to Agent App!</h4>
			<h4 v-if="registrationType == 'company'">
				Register a company to your account!
			</h4>
			<h5>General Information</h5>
			<div class="input-div-large" v-if="registrationType == 'company'">
				<div class="input-div">
					<label>Company Owner</label>
					<p>{{ ownerName }}</p>
				</div>
			</div>
			<div class="input-div-large" v-if="registrationType == 'company'">
				<div class="input-div">
					<label>Company Name</label>
					<input
						id="name"
						placeholder="Company, Inc."
						v-model="name"
					/>
				</div>
			</div>
			<div class="multiple-input-div" v-if="registrationType == 'user'">
				<div class="input-div">
					<label>Username</label>
					<input
						id="username"
						placeholder="johnjohnson"
						@change="checkUsername()"
						v-model="username"
					/>
				</div>
				<div class="input-div">
					<label>Password</label>
					<input
						id="passwordInput"
						type="password"
						placeholder="•••••••••••••••••"
						v-model="passwordInput"
					/>
				</div>
			</div>
			<div v-if="!usernameCheck">
				<p style="color: #ff6a6a; font-size: 20px">
					Username already exists, please choose another one.
				</p>
			</div>
			<div class="multiple-input-div" v-if="registrationType == 'user'">
				<div class="input-div">
					<label>First Name</label>
					<input
						id="firstname"
						placeholder="John"
						v-model="firstName"
					/>
				</div>
				<div class="input-div">
					<label>Last Name</label>
					<input
						id="lastname"
						placeholder="Johnson"
						v-model="lastName"
					/>
				</div>
			</div>
			<div class="multiple-input-div">
				<div class="input-div">
					<label>Email</label>
					<input
						id="email"
						type="email"
						placeholder="johnjohnson@gmail.com"
						v-model="email"
					/>
				</div>
				<div class="input-div">
					<label>Phone Number</label>
					<input
						id="phonenumber"
						type="number"
						placeholder="+381600000000"
						v-model="number"
					/>
				</div>
			</div>
			<div class="input-div-large" v-if="registrationType == 'company'">
				<div class="input-div">
					<label>Description</label>
					<input
						id="description"
						placeholder="Something about the company..."
						v-model="description"
					/>
				</div>
			</div>
			<div class="input-div-large" v-if="registrationType == 'company'">
				<div class="input-div">
					<label>Culture</label>
					<input
						id="culture"
						placeholder="Something about the culture..."
						v-model="culture"
					/>
				</div>
			</div>
			<button @click="register">Sign Up</button>
		</div>
	</div>
</template>
<script>
import { ref } from "vue";
import axios from "axios";
// import ListInputItem from "../components/ListInputItem.vue";

export default {
	name: "RegisterPage",
	props: {
		registrationType: String,
		ownerId: String,
		ownerName: String,
	},
	mounted() {},
	setup() {
		localStorage.clear();

		var username = ref(null);
		var passwordInput = ref(null);
		var firstName = ref(null);
		var lastName = ref(null);
		var email = ref(null);
		var number = ref(null);

		var name = ref(null);
		var description = ref(null);
		var culture = ref(null);

		var usernameCheck = ref(true);

		return {
			username,
			passwordInput,
			firstName,
			lastName,
			email,
			number,

			name,
			description,
			culture,

			usernameCheck,

			checkUsername() {
				axios
					.post(
						"http://localhost:5003/agent/checkUsername",
						this.username,
						{ headers: { "Content-Type": "text/plain" } }
					)
					.then(function (response) {
						usernameCheck.value = response.data;
					});
			},

			checkEmail(email) {
				if (
					email.includes("@") &&
					email.indexOf("@") != email.length - 1 &&
					email.indexOf("@") != 0 &&
					email.indexOf(".") != email.length - 1 &&
					email.indexOf("@") + 1 != email.indexOf(".")
				)
					return true;
			},
		};
	},
	methods: {
		register() {
			// TODO: IF za registrationType
			if (this.registrationType == "user") {
				if (
					this.username == null ||
					this.passwordInput == null ||
					this.firstName == null ||
					this.lastName == null ||
					this.email == null ||
					this.number == null
				) {
					alert("All fields need to be filled, try again.");
				} else if (!this.checkEmail(this.email)) {
					alert(
						"Email isn't in the correct form. Please fill out the form again."
					);
					return;
				} else if (this.usernameCheck == false) {
					alert(
						"Username already exists, please choose another one."
					);
					return;
				} else {
					var newAgent = {
						username: this.username,
						passwordInput: this.passwordInput,
						firstName: this.firstName,
						lastName: this.lastName,
						email: this.email,
						number: this.number,
					};

					const me = this;

					axios
						.post("http://localhost:5003/agent/register/", newAgent)
						.then(function (response) {
							alert(
								"Welcome to Agent App, " +
									newAgent.firstName +
									"!"
							);
							response.data.role = "User";
							me.$store.commit("setToken", response.data.id);
							me.$store.commit("setUser", response.data);
							me.$router.push("/");
						});
				}
			} else {
				if (
					this.name == null ||
					this.email == null ||
					this.number == null ||
					this.description == null ||
					this.culture == null
				) {
					alert("All fields need to be filled, try again.");
				} else if (!this.checkEmail(this.email)) {
					alert(
						"Email isn't in the correct form. Please fill out the form again."
					);
					return;
				} else if (this.usernameCheck == false) {
					alert(
						"Username already exists, please choose another one."
					);
					return;
				} else {
					var newCompany = {
						ownerId: this.ownerId,
						name: this.name,
						email: this.email,
						number: this.number,
						description: this.description,
						culture: this.culture,
					};

					const me = this;

					axios
						.post(
							"http://localhost:5003/firm/register/",
							newCompany
						)
						.then(function (response) {
							alert(
								"Welcome to Agent App, " + newCompany.name + "!"
							);
							response.data.role = "User";
							me.$store.commit("setToken", response.data.id);
							me.$store.commit("setUser", response.data);
							me.$router.push("/");
						});
				}
			}
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
.input-div-list {
	display: flex;
	align-items: center;
}
.input-div-list button {
	width: 35px;
	height: 36px;
	margin-left: 10px;
	margin-top: 0px;
	font-size: 26px;
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
label {
	margin-bottom: 5px;
}
input {
	height: 32px;
	width: 280px;
}
select {
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