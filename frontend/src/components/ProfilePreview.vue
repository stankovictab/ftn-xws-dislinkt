<template>
    <div>
        <img :src="require('../assets/' + avatar)"/>
        <p id="username">{{user.username}}</p>
        <!-- <p>{{user.jobDesc}}</p> -->
        <!-- <p>Working at {{user.company}}</p> -->
        <router-link to="/profile">  
            <button>Profile</button>
        </router-link>
    </div>
</template>

<script>
import {mapState} from "vuex";
export default {
	name: "ProfilePreview",
	components: {},
	methods: {},
    computed: {
        ...mapState({
            user: 'user',
        }),
        avatar() {
            return this.user.avatar || "placeholder.png";
        },
    }
};
import "../style.css";
</script>

<style scoped>
div{
    border-radius: 5px;
	background-color: var(--backdrop);
    padding: 15px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
img {
    width: 80px;
    height: 80px;
    border-radius: 50%;
}
p{
    font-size: 20px;
    margin: 5px 0px;
}
#username{
    color: white;
    font-size: 25px;
}
</style>