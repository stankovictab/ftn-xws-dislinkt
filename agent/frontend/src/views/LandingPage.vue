<template>
	<div>
		<div style="display: flex; justify-content: space-between">
			<div class="landing-left">
				<h1>Agent App</h1>
				<h2>Another place to connect!</h2>
			</div>
			<div class="landing-right">
				<h3>
					Continue your professional career <br />by creating an
					account.
				</h3>
				<router-link
					:to="{
						name: 'Register',
						params: { registrationType: 'user' },
					}"
					><button class="cta-large-button" href="#/register">
						Sign Up
					</button></router-link
				>
				<div style="margin-top: 20px"></div>
				<p>
					Already have an account?
					<router-link to="/login">Log in</router-link>.
				</p>
			</div>
		</div>
		<div style="margin-top: 300px"></div>
		<div style="display: flex; justify-content: center">
			<p class="withlove">
				Made by
				<a href="https://github.com/stankovictab/ftn-xws-dislinkt"
					>Kvantaš</a
				>
				with 💚
			</p>
		</div>
	</div>
</template>

<script>
import "../style.css";
export default {
	name: "App",
	components: {},
	data() {
		return {
			searchTerm: "",
		};
	},
	methods: {
		search() {
			this.guestMode();
			this.$router.push({
				name: "Search",
				params: {
					searchTerm: this.searchTerm,
				},
			});
		},
		guestMode() {
			this.$store.commit("setUser", { role: "Guest" });
			this.$router.push("/");
		},
	},
};
</script>

<style scoped>
.landing-left {
	margin-top: 180px;
	margin-left: 90px;
}

.landing-right {
	display: flex;
	flex-direction: column;
	margin-top: 330px;
	margin-right: 90px;
	align-items: center;
}
.withlove {
	font-weight: normal;
	font-size: 18px;
}
.cta-large-button {
	font-size: 1.8vw;
	width: 13vw;
	height: 7vh;
	box-shadow: 0px 7px 4px rgba(0, 0, 0, 0.4);
	margin: 30px 0px;
}
</style>