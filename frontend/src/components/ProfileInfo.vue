<template>
    <header>
        <img :src="require('../assets/' + user.avatar)"/>
        <h1>{{user.username}}</h1>
    </header> 
</template>

<script>
import {mapState} from "vuex";
import {getUserById} from "../services/requests";

export default {
	name: "ProfileInfo",
	components: {},
    props: {
        userId: String
    },
    computed: {
        ...mapState({
            user: 'user',
        })
    },
    created() {
        getUserById(this.userId).then(user => {
            this.user = user;
        });
    },
    
};

import "../style.css";
</script>

<style scoped>
header{
    display: flex;
    align-items: center;
    width: 100%;
}
img {
    width: 80px;
    height: 80px;
    border-radius: 50%;
    margin-right: 30px;
}
h1{
    color: white;
}
</style>