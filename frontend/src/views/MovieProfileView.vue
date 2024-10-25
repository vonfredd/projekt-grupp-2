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
        <div class="flex flex-col items-start justify-center mt-10">
          <div class="flex mb-2 justify-center">
            <h1 class="inline mr-2">{{ movie.name }}</h1>

            <div class="relative w-10 h-10 flex items-center justify-center">
              <span class="z-10">{{ movie.averageRating }}%</span>
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
      <div class="flex flex-col items-start justify-center mt-10">
        <div class="flex mb-2 mx-6 justify-center">
          <h2 class="text-5xl font-bold mr-2">{{ movie.name }}</h2>
          <RatingCircle :progress="movie.averageRating" :size="70"/>
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
      <Schedules :movie="movie" />
    </div>
    <div v-else class="p-4 relative z-2">
      <p>Loading...</p>
    </div>
  </main>

  <!-- Desktop Background -->
  <div class="hidden md:block bg-cover min-h-screen bg-fixed relative bg-[url('../../public/img/cinemacurtains.jpg')] bg-[length:200%] bg-[bottom_center]">
    <div v-if="movie" class="absolute inset-0 bg-black opacity-50 z-1"></div>
    <div v-if="movie" class="p-4 relative z-2">
      <div class="px-10 pt-2">
      <div class="md:show flex justify-center p-2 ">
        <img :src="`https://image.tmdb.org/t/p/w500${movie.imageUrl}`" alt="" class=" sm:size-1/2 md:size-2/5 lg:size-1/3 xl:size-1/4 shadow-[0px_0px_8px_6px_rgba(255,255,255,0.6)]">
      </div>
      <div class="mx-6">
        <!-- Same content as mobile or adjust as necessary for desktop -->
        <div class="flex flex-col items-start md:items-center justify-center mt-10">
          <div class="flex mb-2 justify-center">
            <h2 class="inline md:text-center mr-3">{{ movie.name }}</h2>
            <div class="relative w-10 h-10 flex items-center justify-center">
              <span class="z-10">{{ movie.rating }}%</span>
              <span class="absolute inset-0 bg-white opacity-30 rounded-full"></span>

            </div>

          </div>
          <div class="space-x-1 uppercase pb-16 text-sm pt-2">
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
        <hr class="border-2 size-2/3 mx-auto">
        <div class="py-10 flex size-2/3 mx-auto">
          <div class="lg:mx-4 sm:pr-0 lg:pr-20">
            <h3>Overview</h3>
            <p class="mt-2">{{ movie.description }}</p>
          </div>
        </div>
      </div>
      </div>
      <Schedules :movie="movie" class="sm:size-2/3 mx-auto" />
    </div>
    <div v-else class="p-4 relative z-2">
      <p>Loading...</p>
    </div>
  </div>
</template>