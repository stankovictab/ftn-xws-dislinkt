<template>
	<header>
		<div class="header-head">
			<img :src="require('../assets/placeholder.png')" />
			<div>
				<div style="display: flex; gap: 20px">
					<h2>{{ user.firstName }} {{ user.lastName }}</h2>
					<button>✏️</button>
				</div>
				<div style="display: flex; justify-content: space-between">
					<h4>@{{ user.username }}</h4>
					<h4 v-if="user.privateAccount">Private</h4>
					<h4 v-if="!user.privateAccount">Public</h4>
				</div>
			</div>
		</div>
		<div class="header-body">
			<div>
				<h3>Role</h3>
				<p>{{ user.role }}</p>
			</div>
			<div>
				<h3>Email</h3>
				<p>{{ user.email }}</p>
			</div>
			<div>
				<h3>Phone Number</h3>
				<p>{{ user.number }}</p>
			</div>
			<div>
				<h3>Gender</h3>
				<p>{{ user.gender }}</p>
			</div>
			<div>
				<h3>Date of Birth</h3>
				<p>{{ user.dateOfBirth }}</p>
			</div>
			<div>
				<h3>Biography</h3>
				<p>{{ user.biography }}</p>
			</div>
			<div>
				<h3>Work Experience</h3>
				<p>{{ user.workExperience }}</p>
			</div>
			<div>
				<h3>Studies</h3>
				<p>{{ user.studies }}</p>
			</div>
			<div>
				<h3>Skills</h3>
				<p>{{ user.skills }}</p>
			</div>
			<div>
				<h3>Interests</h3>
				<p>{{ user.interests }}</p>
			</div>
		</div>
	</header>
</template>

<script>
import { mapState } from "vuex";

export default {
	name: "ProfileInfo",
	components: {},
	props: {
		userId: String,
	},
	computed: {
		...mapState({
			user: "user",
		}),
	},
};

import "../style.css";
</script>

<style scoped>
header {
	background-color: var(--backdrop3);
	padding: 25px 35px;
	border-radius: 20px;
}
.header-head {
	display: flex;
	gap: 60px;
	align-items: center;
}
.header-body {
	margin-top: 20px;
	display: flex;
	flex-direction: column;
	gap: 20px;
}
.header-body div {
	display: flex;
	gap: 20px;
	align-content: center;
	justify-content: space-between;
}
img {
	width: 100px;
	height: 100px;
	border-radius: 50%;
}
p {
	color: white;
	font-size: 30px;
	text-align: right;
}
h2 {
	color: white;
	font-size: 36px;
	font-weight: 600;
}
h3 {
	color: var(--text1);
	font-size: 30px;
	font-weight: 600;
	text-align: left;
}
h4 {
	font-size: 20px;
	font-weight: 600;
	color: var(--text3);
}
button {
	font-size: 15px;
	height: 30px;
	width: 30px;
	margin: 5px;
	padding: 5px;
}
button.active {
	background-color: var(--yellow4);
}
</style>