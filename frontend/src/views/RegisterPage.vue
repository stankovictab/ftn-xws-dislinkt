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
			<div class="input-div-large" v-if="registrationType == 'company'">
				<div class="input-div">
					<label>Company Name</label>
					<input
						id="firstName"
						placeholder="John"
						v-model="firstName"
					/>
				</div>
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
			<div class="multiple-input-div" v-if="registrationType == 'user'">
				<div class="input-div">
					<label>Gender</label>
					<select id="gender" v-model="gender">
						<!-- <option value="" selected></option> -->
						<option value="male">Male</option>
						<option value="female">Female</option>
					</select>
				</div>
				<div class="input-div">
					<label>Date of Birth</label>
					<input id="dateofbirth" type="date" v-model="dateOfBirth" />
				</div>
			</div>
			<h5 v-if="registrationType == 'user'">
				Biography & Work Experience
			</h5>
			<div class="input-div-large">
				<label v-if="registrationType == 'user'">Biography</label>
				<label v-if="registrationType == 'company'">Description</label>
				<textarea
					id="biography"
					cols="20"
					rows="3"
					v-model="biography"
				></textarea>
			</div>
			<div class="input-div-large" v-if="registrationType == 'user'">
				<label>Work Experience</label>
				<list-input-item
					v-for="(expItem, index) in workExperience"
					:key="'workExpItem' + index"
					v-model="workExperience[index]"
					:showPlus="index == workExperience.length - 1"
					@lengthen="workExperience.push('')"
				>
				</list-input-item>
			</div>
			<div class="input-div-large" v-if="registrationType == 'user'">
				<label>Studies</label>
				<list-input-item
					v-for="(studiesItem, index) in studies"
					:key="'studiesItem' + index"
					v-model="studies[index]"
					:showPlus="index == studies.length - 1"
					@lengthen="studies.push('')"
				>
				</list-input-item>
			</div>
			<div class="input-div-large" v-if="registrationType == 'user'">
				<label>Skills</label>
				<list-input-item
					v-for="(skillsItem, index) in skills"
					:key="'skillsItem' + index"
					v-model="skills[index]"
					:showPlus="index == skills.length - 1"
					@lengthen="skills.push('')"
				>
				</list-input-item>
			</div>
			<div class="input-div-large" v-if="registrationType == 'user'">
				<label>Interests</label>
				<list-input-item
					v-for="(interestsItem, index) in interests"
					:key="'interestsItem' + index"
					v-model="interests[index]"
					:showPlus="index == interests.length - 1"
					@lengthen="interests.push('')"
				>
				</list-input-item>
			</div>
			<h5 v-if="registrationType == 'user'">Privacy</h5>
			<div
				id="privacy-radio-buttons"
				class="input-div-large"
				v-if="registrationType == 'user'"
			>
				<div class="checkbox-div">
					<input type="checkbox" v-model="privateAccount" />
					<label for="public">Private Account</label>
				</div>
			</div>
			<button @click="register">Sign Up</button>
		</div>
	</div>
</template>
<script>
import { ref } from "vue";
import axios from "axios";
import ListInputItem from "../components/ListInputItem.vue";

export default {
	name: "RegisterPage",
	components: {
		ListInputItem,
	},
	props: {
		registrationType: String,
	},
	setup() {
		localStorage.clear();

		var username = ref(null);
		var passwordInput = ref(null);
		var firstName = ref(null);
		var lastName = ref(null);
		var email = ref(null);
		var number = ref(null);
		var gender = ref(null);
		var dateOfBirth = ref(null);
		var biography = ref(null);
		var workExperience = ref([""]);
		var studies = ref([""]);
		var skills = ref([""]);
		var interests = ref([""]);
		var privateAccount = ref(false);

		var usernameCheck = ref(true);

		return {
			username,
			passwordInput,
			firstName,
			lastName,
			email,
			number,
			gender,
			dateOfBirth,
			biography,
			workExperience,
			studies,
			skills,
			interests,
			privateAccount,

			usernameCheck,

			checkUsername() {
				axios
					.post(
						"http://localhost:5001/user/checkUsername",
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
					// email is in correct form !!!
					return true;
			},
		};
	},
	methods: {
		register() {
			if (this.registrationType == "user") {
				if (
					this.username == null ||
					this.passwordInput == null ||
					this.firstName == null ||
					this.lastName == null ||
					this.email == null ||
					this.number == null ||
					this.gender == null ||
					this.dateOfBirth == null ||
					this.biography == null ||
					!this.workExperience ||
					!this.studies ||
					!this.skills ||
					!this.interests
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
					var newUser = {
						username: this.username,
						passwordInput: this.passwordInput,
						firstName: this.firstName,
						lastName: this.lastName,
						email: this.email,
						number: this.number,
						gender: this.gender,
						dateOfBirth: this.dateOfBirth,
						biography: this.biography,
						workExperience: this.workExperience,
						studies: this.studies,
						skills: this.skills,
						interests: this.interests,
						privateAccount: this.privateAccount,
					};

					const me = this;

					axios
						.post("http://localhost:5001/user/register/", newUser)
						.then(function (response) {
							alert(
								"Welcome to Dislinkt, " +
									newUser.firstName +
									"!"
							);
							response.data.role = "Client";
							me.$store.commit("setToken", response.data.id);
							me.$store.commit("setUser", response.data);
							me.$router.push("/");
						});
				}
			} else {
				if (
					this.username == null ||
					this.passwordInput == null ||
					this.firstName == null ||
					this.email == null ||
					this.number == null ||
					this.biography == null
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
						username: this.username,
						passwordInput: this.passwordInput,
						firstName: this.firstName,
						email: this.email,
						number: this.number,
						biography: this.biography,
						privateAccount: false,
					};

					const me = this;

					axios
						.post(
							"http://localhost:5001/user/register/",
							newCompany
						)
						.then(function (response) {
							alert(
								"Welcome to Dislinkt, " +
									newCompany.firstName +
									"!"
							);
							response.data.role = "Client";
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