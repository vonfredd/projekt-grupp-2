<script setup>
import { ref, onMounted, computed } from "vue";
import Schedules from "@/components/Schedules.vue";
import RatingCircle from "@/components/RatingCircle.vue";

const props = defineProps({
  id: {
    type: String,
    required: true
  },
  title: {
    type: String,
    required:true,
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
  console.log(movie);
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
    <div v-if="movie" class="absolute inset-0 bg-black opacity-70 z-1"></div>
    <div v-if="movie" class="p-4 relative z-2">
      <div class="mx-6">
        <div class="flex justify-start max-w-[400px] mt-10">
          <h1 class="mr-2 pt-4">{{ movie.name }}</h1>
          <RatingCircle :progress="movie.averageRating" :size="60"/>
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
        <div class="py-8 mb-10 max-w-[400px] sm:max-w-[500px]">
          <h2>Overview</h2>
          <p class="mt-2">{{ movie.description }}</p>
        </div>
      <Schedules :movie="movie" />
    </div>
    </div>
    <div v-else class="p-4 relative z-2">
      <p>Loading...</p>
    </div>
  </main>

  <!-- Desktop Background -->
  <div class="hidden md:block bg-cover min-h-screen bg-fixed relative bg-[url('/img/cinemacurtains.jpg')] bg-[length:200%] bg-[bottom_center]">
    <div v-if="movie" class="absolute inset-0 bg-black opacity-50 z-1"></div>
    <div v-if="movie" class="p-4 relative z-2">
      <div class="md:show flex justify-center p-2 px-10 pt-2">
        <img :src="`https://image.tmdb.org/t/p/w500${movie.imageUrl}`" alt="" class=" sm:size-1/2 md:size-2/5 lg:size-1/3 xl:size-1/4 shadow-[0px_0px_8px_6px_rgba(255,255,255,0.6)]">
      </div>
      <div class="mx-6">
        <!-- Same content as mobile or adjust as necessary for desktop -->
        <div class="flex justify-center">
          <div class="place-self-center">
            <div class="text-center mt-10 mb-2">
              <h2 class="mr-3">{{ movie.name }}</h2>
            </div>
            <div class="space-x-1 uppercase pb-10 text-sm text-center pt-2">
              <span
                  v-for="(genre, index) in movie.genres"
                  :key="index"
                  class="inline flex-flow-col"
              >
                {{ genre }}<span v-if="index < movie.genres.length - 1">, </span>
              </span>
              <span class="mx-2">|</span>
              <p class="inline">{{ formattedDuration }}</p>
            </div>
          </div>
          <div class="place-self-start">
            <RatingCircle :progress="movie.averageRating" :size="60"/>
          </div>
        </div>
        <hr class="border-2 size-2/3 mx-auto">
        <div class="pt-10 pb-20 flex size-2/3 mx-auto">
          <div class="lg:mx-4 px-10">
            <h3>Overview</h3>
            <p class="mt-2">{{ movie.description }}</p>
          </div>
        </div>
      </div>
      <Schedules :movie="movie"  />
    </div>
    <div v-else class="p-4 relative z-2">
      <p>Loading...</p>
    </div>
  </div>
</template>