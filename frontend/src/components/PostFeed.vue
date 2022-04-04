<template>
    <div id="post-feed" v-if="posts">
        <user-post v-for="post in posts" :key="post.id" :post='post' @vote="applyVote"/>
    </div> 
</template>

<script>
import {getFeed} from '../services/requests';
import UserPost from '../components/UserPost.vue';

export default {
	name: "PostFeed",
	components: { UserPost },
    data: function() {
        return {
            posts: []
        }
    },
    mounted() {
      getFeed().then(response => {
        this.posts = response.data;
      });
    },
	methods: {
        applyVote(postId, newVotes) {
            this.posts.find(post => post.id === postId).votes = newVotes;
            // pozivamo metodu updatePost() iz services/requests.js
        }
    },
};
import "../style.css";
</script>

<style scoped>
#post-feed{
    padding: 15px;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    align-items: center;
}
</style>