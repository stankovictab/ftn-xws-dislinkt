<template>
	Hi User!
</template>

<script>
export default {
	name: "HomePage",
	components: {},
	methods: {
		logout(){
			this.$store.commit("setToken", "");
			this.$store.commit("setUser", {"username": "", "role": ""});
			this.$router.push("/");
		},
	},
};
import "../style.css";
</script>

<style>
</style>