<template>
	<div>
		<p>
			{{ searchTerm }}
		</p>
	</div>
</template>

<script>
export default {
	props: {
		searchTerm: String,
	},
	mounted() {
		alert(this.searchTerm);
	},
};
</script>

<style>
</style>