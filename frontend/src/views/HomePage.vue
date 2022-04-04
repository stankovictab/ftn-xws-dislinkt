<template>
	<div id="homepage">
		<section>
			<profile-preview></profile-preview>
		</section>
		<main></main>
		<section></section>
	</div>
</template>

<script>
import ProfilePreview from '../components/ProfilePreview.vue';

export default {
	name: "HomePage",
	components: {ProfilePreview}
};
import "../style.css";
</script>

<style>
#homepage {
	display: flex;
	justify-content: space-evenly;
}

main{
	width: 35%;
}
section {
	width: 20%;
}
</style>