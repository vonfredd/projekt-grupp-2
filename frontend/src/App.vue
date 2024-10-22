<script setup>
import { ref, onMounted } from 'vue';
import { RouterView } from "vue-router";
import Header from "./components/Header.vue";
import Footer from "./components/Footer.vue";

const listOfMovies = ref([]);

const fetchMovies = async () => {
  const response = await fetch('http://localhost:9000/movies');
  listOfMovies.value = await response.json();
};

onMounted(fetchMovies);

</script>

<template>
  <Header />
  <RouterView :listOfMovies="listOfMovies" />
  <Footer />
</template>

<style scoped></style>
