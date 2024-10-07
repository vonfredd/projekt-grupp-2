<script setup>
import { ref } from 'vue';
const movieArr = ref(['placeholder1', 'placeholder2'])
</script>

<template>
  <main>
    <div>
      <p class="font-">Which movie do you want to watch?</p>
      <form>
        <input type="text" placeholder="Search movie...">
      </form>
    </div>
    <div class="flex flex-row gap-4 justify-center">
      <div v-for="key in movieArr" :key="index">
        <div class="w-28 h-56 bg-red-500">
          {{ key }}
        </div>
      </div>
    </div>
  </main>
</template>
