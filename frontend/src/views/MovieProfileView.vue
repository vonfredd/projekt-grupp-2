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
    rating: 75,
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
    <div class="absolute inset-0 bg-black opacity-10 z-1"></div>
        <div class="p-4 relative z-2">
            <div class="flex justify-center">
                <div class="flex flex-col mt-10">
                    <div class="flex">
                            <h1 class="mr-2">{{ Movie.name }}</h1>
                            <div class="relative w-12 h-12 mt-5 flex items-center justify-center">
                                <p class="text-lg z-10">{{ Movie.rating }}%</p>
                                <div class="absolute inset-0 bg-white opacity-30 rounded-full"></div>
                            </div>
                        </div>
                    <div class="text-xs space-x-1 uppercase mb-10">
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
            </div>
            <div class="w-[80%] mx-auto mb-10">
                <h2 class="text-3xl">Overview</h2>
                <p class="mt-2">{{ Movie.description }}</p>
             </div>
            <Seats/>
        </div>
  </main>
</template>
