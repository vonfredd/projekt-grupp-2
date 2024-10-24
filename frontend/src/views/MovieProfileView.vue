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
  return `${hours} h ${minutes} min`;
});

onMounted(() => {
  fetchMovie();
  window.scrollTo(0, 0);
});

</script>

<template>
  <!-- Mobile Background -->
  <main
      :style="{ backgroundImage: `url(${backdropPath})` }"
      class="bg-cover bg-center min-h-screen bg-fixed relative md:hidden"
  >
    <!-- Mobile content goes here -->
    <div v-if="movie" class="absolute inset-0 bg-black opacity-50 z-1"></div>
    <div v-if="movie" class="p-4 relative z-2">
      <div class="mx-6">
        <div class="flex flex-col items-start md:items-center justify-center mt-10">
          <div class="flex mb-2 justify-center items-center">
            <h1 class="inline md:text-center mr-2">{{ movie.name }}</h1>
            <div class="relative w-10 h-10 flex items-center justify-center">
              <span class="z-10">{{ movie.rating }}%</span>
              <span class="absolute inset-0 bg-white opacity-30 rounded-full"></span>
            </div>
          </div>
          <div class="space-x-1 uppercase">
            <span
                v-for="(genre, index) in movie.genres"
                :key="index"
                class="inline flex-flow-col"
            >
              {{ genre }}<span v-if="index < movie.genres.length - 1">,</span>
            </span>
            <span class="mx-2">|</span>
            <p class="inline">{{ formattedDuration }}</p>
          </div>
        </div>
        <br />
        <div class="py-8">
          <h2>Overview</h2>
          <p class="mt-2">{{ movie.description }}</p>
        </div>
      </div>
      <Schedules :movie="movie" />
      <Seats :movie="movie" />
    </div>
    <div v-else class="p-4 relative z-2">
      <p>Loading...</p>
    </div>
  </main>

  <!-- Desktop Background -->
  <div class="hidden md:block bg-cover bg-center min-h-screen bg-fixed relative bg-[url('../../public/img/cinemabg.jpg')] bg-[top_center]">
    <div v-if="movie" class="absolute inset-0 bg-black opacity-50 z-1"></div>
    <div v-if="movie" class="p-4 relative z-2">
      <div class="md:show flex justify-center pt-4">
        <img :src="`https://image.tmdb.org/t/p/w500${movie.imageUrl}`" alt="">
      </div>
      <div class="mx-6">
        <!-- Same content as mobile or adjust as necessary for desktop -->
        <div class="flex flex-col items-start md:items-center justify-center mt-10">
          <div class="flex mb-2 justify-center items-center">
            <h1 class="inline md:text-center mr-2">{{ movie.name }}</h1>
            <div class="relative w-10 h-10 flex items-center justify-center">
              <span class="z-10">{{ movie.rating }}%</span>
              <span class="absolute inset-0 bg-white opacity-30 rounded-full"></span>
            </div>
          </div>
          <div class="space-x-1 uppercase">
            <span
                v-for="(genre, index) in movie.genres"
                :key="index"
                class="inline flex-flow-col"
            >
              {{ genre }}<span v-if="index < movie.genres.length - 1">,</span>
            </span>
            <span class="mx-2">|</span>
            <p class="inline">{{ formattedDuration }}</p>
          </div>
        </div>
        <br />
        <div class="py-8">
          <h2>Overview</h2>
          <p class="mt-2 md:mt-4">{{ movie.description }}</p>
        </div>
      </div>
      <Schedules :movie="movie" />
      <Seats :movie="movie" />
    </div>
    <div v-else class="p-4 relative z-2">
      <p>Loading...</p>
    </div>
  </div>
</template>
