<template>
    <div class="input-div-list">
        <input v-model="listItem" ref="input"/>
        <button v-if="showPlus" @click="lengthenList">+</button>
    </div>
</template>

<script>

export default {
	name: "ListInputItem",
	components: {},
    props: {
        modelValue: {
            type: String
        },
        showPlus: {
            type: Boolean,
            default: true
        },
    },
    computed: {
        listItem: {
            get() {
                this.$refs["input"]?.focus()
                return this.modelValue;
            },
            set(modelValue) {
                this.$emit('update:modelValue', modelValue);
            }
        }
    },
    methods: {
        lengthenList() {
            this.$emit('lengthen');
        }
    },
};
import "../style.css";
</script>

<style scoped>
div{
	display: flex;
	align-items: center;
    margin: 2px 0px;
}
button {
	width: 30px;
	height: 30px;
	margin-left: 10px;
	font-size: 26px;
    text-align:center;
    line-height:26px;
}
input{
    width: 97.3%;
}
</style>