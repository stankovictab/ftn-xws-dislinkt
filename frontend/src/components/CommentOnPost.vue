<template>
    <div>
        <img :src="require('../assets/' + avatar)"/>
        <p>{{comment.comment}}</p>
    </div> 
</template>

<script>

export default {
	name: "CommentOnPost",
	components: {},
    props:{
        comment: Object
    },
    computed:{
        avatar() {
            return this.comment.avatar || "placeholder.png";
        },
    }
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
    width: 35px;
    height: 35px;
    border-radius: 50%;
    margin-right: 10px;
}
p{
    color: white;
    font-size: 16px;
}
</style>