<template>
  <link rel="preconnect" href="https://fonts.googleapis.com" />
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
  <link
    href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,300;0,400;0,500;0,700;0,800;1,900&display=swap"
    rel="stylesheet"
  />
  <!--  -->
  <div style="display: flex; justify-content: space-between">
    <div class="landing-left">
      <router-link to="/">
        <h1>Dislinkt</h1>
      </router-link>
      <h2>The place to connect.</h2>
    </div>
    <div class="landing-right">
      <h3>Start your professional career <br />by creating an account.</h3>
      <button style="margin: 30px 0px">
        <router-link to="/register"> Sign Up </router-link>
      </button>
      <p>
        Already have an account? <router-link to="/login">Login</router-link>
      </p>
    </div>
  </div>
  <div style="margin-top: 220px"></div>
  <div style="display: flex; justify-content: center">
    <p class="withlove">
      Made by
      <a href="https://github.com/stankovictab/ftn-xws-dislinkt">Kvantaš</a>
      with 💙
    </p>
  </div>
</template>

<script>
export default {
  name: "App",
  components: {},
};
import "../style.css";
</script>