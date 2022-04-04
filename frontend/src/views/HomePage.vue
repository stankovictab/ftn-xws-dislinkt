<template>
	<div id="homepage">
		<section>
			<profile-preview></profile-preview>
		</section>
		<main>
			<status-input></status-input>
		</main>
		<section>
			<chat-links></chat-links>
		</section>
	</div>
</template>

<script>
import ProfilePreview from '../components/ProfilePreview.vue';
import StatusInput from '../components/StatusInput.vue';
import ChatLinks from '../components/ChatLinks.vue';

export default {
	name: "HomePage",
	components: {
		ProfilePreview, 
		StatusInput,
		ChatLinks
	},
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