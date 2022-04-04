<template>
    <div>
        <img :src="require('../assets/' + friend.avatar)"/>
        <router-link to="/profile/id">{{friend.username}}</router-link>
    </div> 
</template>

<script>

export default {
	name: "ChatLink",
	components: {},
    props:{
        friend: Object
    },
};
import "../style.css";
</script>

<style scoped>
div{
    display: flex;
    align-items: center;
    margin: 5px 0px;
    width: 100%;
}
img {
    width: 50px;
    height: 50px;
    border-radius: 50%;
    margin-right: 10px;
}
</style>