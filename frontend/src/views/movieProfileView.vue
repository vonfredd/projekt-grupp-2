<script setup>
import { ref, computed } from "vue";
const Movie = ref({
  name: "Inception",
  description:
    "A mind-bending thriller where dream invasion is possible. Dom Cobb is a skilled thief, the absolute best in the dangerous art of extraction: stealing valuable secrets from deep within the subconscious during the dream state, when the mind is at its most vulnerable.",
  genre: [
    { id: 28, name: "Action" },
    { id: 878, name: "Science Fiction" },
    { id: 12, name: "Adventure" },
  ],
  duration: 148,
  releaseDate: "2010-07-16",
  imageUrl: "https://image.tmdb.org/t/p/w500/8ZTVqvKDQ8emSGUEMjsS4yHAwrp.jpg",
  rating: 75,
});

const formattedDuration = computed(() => {
  const hours = Math.floor(Movie.value.duration / 60);
  const minutes = Movie.value.duration % 60;
  return `${hours}h ${minutes}min`;
});
</script>

<template>
  <main
    :style="{ backgroundImage: `url(${Movie.imageUrl})` }"
    class="bg-cover bg-center min-h-screen relative"
  >
    <div class="absolute inset-0 bg-black opacity-10 z-1"></div>
    <div class="p-4 relative z-2">
      <div class="flex flex-col items-start justify-center mt-10">
        <div class="flex mb-2 mx-6 justify-center">
          <h2 class="text-5xl font-bold mr-2">{{ Movie.name }}</h2>
          <div class="relative w-7 h-7 flex items-center justify-center">
            <p class="text-xs font-semibold z-10">{{ Movie.rating }}%</p>
            <div
              class="absolute inset-0 bg-white opacity-50 rounded-full"
            ></div>
          </div>
        </div>
        <div class="text-xs space-x-1 mx-12">
          <span
            v-for="(genre, index) in Movie.genre"
            :key="genre.id"
            class="inline text-xs font-semibold flex-flow-col"
          >
            {{ genre.name }}<span v-if="index < Movie.genre.length - 1">,</span>
          </span>
          <span class="mx-2">|</span>
          <p class="inline">{{ formattedDuration }}</p>
        </div>
      </div>
      <br />
      <div class="p-8">
        <h2 class="text-3xl">Overview</h2>
        <p class="mt-4">{{ Movie.description }}</p>
      </div>
    </div>
  </main>
</template>
