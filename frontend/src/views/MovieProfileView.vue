<script setup>
import { ref, onMounted, computed } from "vue";
import Seats from "@/components/Seats.vue";
import Schedules from "@/components/Schedules.vue";

const props = defineProps({
  id: {
    type: String,
    required: true
  }
});

const movie = ref(null);
const backdropPath = ref("");

const fetchMovie = async () => {
  const response = await fetch(`http://localhost:9000/movies/${props.id}`, {
    method: "GET",
  });
  movie.value = await response.json();
  backdropPath.value = `https://image.tmdb.org/t/p/w500${movie.value.backdropPath}`;
};

const formattedDuration = computed(() => {
  if (!movie.value) return "";
  const hours = Math.floor(movie.value.duration / 60);
  const minutes = movie.value.duration % 60;
  return `${hours}h ${minutes}min`;
});

onMounted(() => {
  fetchMovie();
  window.scrollTo(0, 0);
});

</script>

<template>
  <main
    :style="{ backgroundImage: `url(${backdropPath})` }"
    class="bg-cover bg-center min-h-screen bg-fixed relative"
  >
    <div v-if="movie" class="absolute inset-0 bg-black opacity-50 z-1"></div>
    <div v-if="movie" class="p-4 relative z-2">
      <div class="flex flex-col items-start justify-center mt-10">
        <div class="flex mb-2 mx-6 justify-center">
          <h1 class="mr-2">{{ movie.name }}</h1>
          <div class="relative w-7 h-7 flex items-center justify-center">
            <p class="z-10">{{ movie.rating }}%</p>
            <div class="absolute inset-0 bg-white opacity-50 rounded-full"></div>
          </div>
        </div>
        <div class="space-x-1 mx-12">
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
      <Schedules :movie="movie" />
      <Seats :movie="movie" />
    </div>
    <div v-else class="p-4 relative z-2">
      <p>Loading...</p>
    </div>
  </main>
</template>