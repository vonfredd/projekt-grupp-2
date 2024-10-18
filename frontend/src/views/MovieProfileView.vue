<script setup>
import { ref, computed } from "vue";
import Seats from "@/components/Seats.vue";
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

});

const formattedDuration = computed(() => {
    const hours = Math.floor(Movie.value.duration / 60);
    const minutes = Movie.value.duration % 60;
    return `${hours}h ${minutes}m`;
});
</script>

<template>
  <main
    :style="{ backgroundImage: `url(${Movie.imageUrl})` }"
    class="bg-cover bg-center min-h-screen relative z-0"
  >
    <div class="absolute inset-0 bg-black opacity-50"></div>
        <div class="p-6 relative">
            <div class="max-w-screen-md px-4 mx-auto">
                <div class="flex flex-col mt-10 mb-5">
                    <div class="inline-flex">
                        <h1 class="inline mr-5">{{ Movie.name }}</h1>
                        <div class="h-12 w-10 flex items-center justify-center">
                            <p class="text-md py-3 px-2 bg-white bg-opacity-30 rounded-full">{{ Movie.rating }}%</p>
                        </div>
                    </div>
                    <div class="text-xs space-x-1 uppercase mt-2 mb-10">
                         <span
                            v-for="(genre, index) in Movie.genre"
                             :key="genre.id"
                                class="inline"
                        >
                        {{ genre.name }}<span v-if="index < Movie.genre.length - 1">,</span>
                        </span>
                        <span class="mx-2">|</span>
                        <span>{{ formattedDuration }}</span>
                    </div>
                </div>
            
                <div class="mb-10 max-w-lg">
                    <h2 class="text-3xl">Overview</h2>
                    <p class="mt-2">{{ Movie.description }}</p>
                </div>
             </div>
            <Seats :movie="Movie" />
        </div>
  </main>
</template>
