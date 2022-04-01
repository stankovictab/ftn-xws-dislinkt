<template>
  <div style="display: flex; flex-direction: column; align-items: center">
    <router-link to="/">
      <h1>Dislinkt</h1>
    </router-link>
    <p>The place to connect.</p>
    <div
      class="bg-light"
      style="display: flex; flex-direction: column; align-items: center"
    >
      <h2 style="font-size: 48px">Welcome back!</h2>
      <label for="username">Username</label>
      <input id="username" />
      <label for="password">Password</label>
      <input id="password" type="password" />
      <button @click="login">Sign in</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "LoginPage",
  components: {},
  methods: {
    login() {
      alert("loginujem se mraleee");
    },
  },
};
import "../style.css";
</script>

