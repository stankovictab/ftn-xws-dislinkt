<template>
	<div id="homepage">
		<section>
			<div style="width: 100%">
				<register-company v-if="!user.firmId"></register-company>
				<!-- TODO: Add v-if for when the company is registered to the user -->
				<company-info v-if="user.firmId"></company-info>
			</div>
		</section>
		<main>
			<status-input
				v-if="user.firmId"
				@reloadPosts="reloadPosts"
			></status-input>
			<h4 v-if="!user.firmId">
				Register a company to create job offers!
			</h4>
			<post-feed :posts="posts"></post-feed>
		</main>
		<section>
			<companies-list v-if="!user.firmId"></companies-list>
			<!-- <chat-links></chat-links> -->
		</section>
	</div>
</template>

<script>
import RegisterCompany from "../components/RegisterCompany.vue";
import CompanyInfo from "../components/CompanyInfo.vue";
import StatusInput from "../components/StatusInput.vue";
import CompaniesList from "../components/CompaniesList.vue";
// import ChatLinks from "../components/ChatLinks.vue";
import PostFeed from "../components/PostFeed.vue";
import { getFeed } from "../services/requests";
import { mapState } from "vuex";

export default {
	name: "HomePage",
	components: {
		// ProfilePreview,
		RegisterCompany,
		CompanyInfo,
		StatusInput,
		CompaniesList,
		// ChatLinks,
		PostFeed,
	},
	computed: {
		...mapState({
			user: "user",
		}),
	},
	mounted() {
		this.reloadPosts();
	},
	data: function () {
		return {
			posts: [],
		};
	},
	methods: {
		reloadPosts() {
			getFeed().then((response) => {
				this.posts = response;
			});
		},
	},
};
import "../style.css";
</script>

<style>
#homepage {
	display: flex;
	justify-content: space-evenly;
}

main {
	width: 35%;
}

section {
	width: 20%;
}
.set-api-token {
	display: flex;
	flex-direction: column;
	gap: 10px;
	background-color: var(--backdrop2);
	color: white;
	padding: 20px;
	border-radius: 20px;
	max-width: 383px;
	width: -webkit-fill-available;
}
.set-api-token h4 {
	color: white;
	font-size: 20px;
	text-align: left;
}
.set-api-token p {
	color: white;
	font-size: 13px;
}
.set-api-token input {
	width: -webkit-fill-available;
}
</style>