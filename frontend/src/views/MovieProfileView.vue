<script setup>
import {ref, computed} from "vue";
import Seats from "@/components/Seats.vue";

const props = defineProps({
  movie: {
    type: Object,
    required: true
  }
});

const movie = ref(props.movie);
const imageUrl = `https://image.tmdb.org/t/p/w500${props.movie.imageUrl}`;

const formattedDuration = computed(() => {
  const hours = Math.floor(movie.value.duration / 60);
  const minutes = movie.value.duration % 60;
  return `${hours}h ${minutes}min`;
});
</script>

<template>
  <main
      :style="{ backgroundImage: `url(${imageUrl})` }"
      class="bg-cover bg-center min-h-screen relative"
  >
    <div class="absolute inset-0 bg-black opacity-10 z-1"></div>
    <div class="p-4 relative z-2">
      <div class="flex flex-col items-start justify-center mt-10">
        <div class="flex mb-2 mx-6 justify-center">
          <h2 class="text-5xl font-bold mr-2">{{ movie.name }}</h2>
          <div class="relative w-7 h-7 flex items-center justify-center">
            <p class="text-xs z-10">{{ movie.rating }}%</p>
            <div
                class="absolute inset-0 bg-white opacity-50 rounded-full"
            ></div>
          </div>
        </div>
        <div class="text-xs space-x-1 mx-12">
          <span
              v-for="(genre, index) in movie.genres"
              :key="index"
              class="inline text-xs font-semibold flex-flow-col"
          >
            {{ genre }}<span v-if="index < movie.genres.length - 1">,</span>
          </span>
          <span class="mx-2">|</span>
          <p class="inline">{{ formattedDuration }}</p>
        </div>
      </div>
      <br/>
      <div class="p-8">
        <h2 class="text-3xl">Overview</h2>
        <p class="mt-4">{{ movie.description }}</p>
      </div>
      <Seats :movie="movie"/>
    </div>
  </main>
</template>