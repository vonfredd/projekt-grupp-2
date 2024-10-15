<script setup>
import { ref } from 'vue';

const props = defineProps({
  movies: {
    type: Array,
    required: true
  }
});
</script>

<template>
  <div>
    <h3 class="text-center">Movie List</h3>
    <ul>
      <li v-for="movie in movies" :key="movie.id">{{ movie.name }}</li>
    </ul>
  </div>
</template>

<style scoped>

</style>