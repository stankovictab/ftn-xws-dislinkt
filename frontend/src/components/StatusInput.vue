<template>
    <div>
        <img :src="require('../assets/' + avatar)"/>
        <input placeholder="What's on your mind?"/> 
        <button>Add Image</button>
    </div>
</template>

<script>
import {mapState} from "vuex";
export default {
	name: "StatusInput",
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
    justify-content: space-around;
    align-items: center;
}
img {
    width: 50px;
    height: 50px;
    border-radius: 50%;
}
input {
    width: 60%;
}
</style>