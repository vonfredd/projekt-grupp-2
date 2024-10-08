<script setup>
import { RouterView } from "vue-router";
import Header from "./components/Header.vue";
</script>

<template>
  <Header></Header>

  <RouterView />
  <footer class="bg-black bottom-0 p-3 text-center">© CINEMA</footer>
</template>

<style scoped></style>
