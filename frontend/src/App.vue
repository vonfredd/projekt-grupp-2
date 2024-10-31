<script setup>
import { RouterView } from "vue-router";
import Header from "./components/Header.vue";
import Footer from "./components/Footer.vue";
import ToTopButton from "@/components/ToTopButton.vue";


</script>

<template>
  <Header />
  <RouterView />
  <Footer />
</template>
<style scoped></style>
