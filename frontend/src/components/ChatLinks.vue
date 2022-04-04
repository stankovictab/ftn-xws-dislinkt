<template>
    <nav v-if="friends">
        <h2>Chat</h2>
        <chat-link v-for="friend in friends" :key="friend.username" :friend='friend'/>
    </nav> 
</template>

<script>
import {getFriends} from '../services/requests';
import ChatLink from '../components/ChatLink.vue';

export default {
	name: "ChatLinks",
	components: { ChatLink },
    data: function() {
        return {
            friends: []
        }
    },
    mounted() {
      getFriends().then(response => {
        this.friends = response.data;
      });
    },
	methods: {},
};
import "../style.css";
</script>

<style scoped>
h2{
    font-size: 35px;
}
nav{
    border-radius: 5px;
	background-color: var(--backdrop);
    padding: 15px;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    align-items: center;
}
</style>