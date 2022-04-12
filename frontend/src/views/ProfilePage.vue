<template>
	<div id="profile-page">
		<section>
			<!-- <profile-preview></profile-preview> -->
		</section>
		<main>
            <profile-info :userId = userId></profile-info>
			<!-- <post-feed></post-feed> -->
		</main>
		<section>
			<!-- <chat-links></chat-links> -->
		</section>
	</div>
</template>

<script>
// import ProfilePreview from '../components/ProfilePreview.vue';
// import ChatLinks from '../components/ChatLinks.vue';
// import PostFeed from '../components/PostFeed.vue';
import ProfileInfo from '../components/ProfileInfo.vue';

export default {
	name: "HomePage",
	components: {
		// ProfilePreview,
		// ChatLinks,
		// PostFeed,
        ProfileInfo
	},
    props: {
        userId: {
            type: String,
            required: true
        }
    },
};
import "../style.css";
</script>

<style>
#profile-page {
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