<script setup>
import { ref } from 'vue';
const movieArr = ref(['placeholder1', 'placeholder2'])
</script>

<template>
  <main>
    <div class="flex flex-row gap-4 justify-center">
      <div v-for="key in movieArr" :key="index">
        
          {{ key }}
        
      </div>
    </div>
  </main>
</template>
