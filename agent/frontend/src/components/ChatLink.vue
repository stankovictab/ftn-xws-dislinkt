<template>
	<div @click="todo()">
		<img :src="require('../assets/' + friend.avatar)" />
		<a>{{ friend.username }}</a>
	</div>
</template>

<script>
export default {
	name: "ChatLink",
	components: {},
	props: {
		friend: Object,
	},
	methods: {
		todo() {
			alert("To be implemented!");
		},
	},
};
import "../style.css";
</script>

<style scoped>
div {
	background-color: var(--backdrop3);
	border-radius: 10px;
	padding: 10px;
	display: flex;
	align-items: center;
	margin: 5px 0px;
	width: 100%;
}
div:hover {
	cursor: pointer;
	background-color: var(--backdrop);
}
img {
	width: 50px;
	height: 50px;
	border-radius: 50%;
	margin-right: 10px;
}
a {
	color: white;
	font-size: 20px;
	font-weight: 500;
}
a:hover {
	color: var(--yellow4);
}
</style>