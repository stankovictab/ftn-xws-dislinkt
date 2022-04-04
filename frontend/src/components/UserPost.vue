<template>
    <article class="user-post">
        <div>
            <img :src="require('../assets/' + post.avatar)"/>
            <router-link to="/profile/id">{{post.username}}</router-link>
        </div>
        <p>{{post.text}}</p>
        <div>
            <button>💬</button>
            <button @click="changeVote(1)" :class="{active: myVote == 1}">👍</button>
            {{post.votes}}
            <button @click="changeVote(-1)" :class="{active: myVote == -1}">👎</button>
        </div>
    </article> 
</template>

<script>

export default {
	name: "UserPost",
	components: {},
    props:{
        post: Object
    },
    data: function() {
        return {
            myVote: 0 // treba da se dobije sa servera
        }
    },
    watch: {
        myVote: function(newVote, oldVote) {
            this.$emit('vote', this.post.id, this.post.votes + newVote - oldVote);
        }
    },
    methods:{
        changeVote(newVote){
            this.myVote = this.myVote == newVote ? 0 : newVote;
        },
    }
};
import "../style.css";
</script>

<style scoped>
.user-post{
    border-radius: 5px;
	background-color: var(--backdrop);
    padding: 15px;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    align-items: center;
    margin: 5px 0px;
    width: 100%;
}
div {
    display: flex;
    align-items: center;
    justify-content: left;
    width: 100%;
}
img {
    width: 50px;
    height: 50px;
    border-radius: 50%;
    margin-right: 10px;
}
p{
    color: white;
    font-size: 20px;
}
button{
    font-size: 15px;
    height: auto;
    width: auto;
    margin: 5px;
    padding: 5px;
}
button.active{
    background-color: var(--yellow4);
}
</style>