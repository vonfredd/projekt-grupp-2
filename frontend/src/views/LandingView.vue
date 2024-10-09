<script setup>
import { computed, ref } from 'vue';

const props = defineProps(['listOfMovies'])
const arrayOfHighestRated = computed(() => {
  /*
  USE THE PROPS IN PRODUCTION like:
  return props.listOfMovies.filter(movie => movie.rating > 84) */
  return movieArr.value.filter(movie => movie.rating > 70)
})

const topFirstIndex = ref(0);
const topSecondIndex = ref(1);



/*
Adjust the top 10 movies shown. 
If the user press forward and first placeholder holds last index, then the first placeholder holds the first element.
If user press backwards and first placeholder holds first element,  then the first placeholder holds the last element
*/
function adjustIndex(index) {

  if (index === 'plus') {
    if (topFirstIndex.value === arrayOfHighestRated.value.length - 1) {
      topFirstIndex.value = arrayOfHighestRated.value.indexOf(arrayOfHighestRated.value[0]);
    } else {
      topFirstIndex.value++;
    }

    if (topSecondIndex.value === arrayOfHighestRated.value.length - 1) {
      topSecondIndex.value = arrayOfHighestRated.value.indexOf(arrayOfHighestRated.value[0]);
    } else {
      topSecondIndex.value++;
    }
  }
  else {
    if (topFirstIndex.value === 0) {
      topFirstIndex.value = arrayOfHighestRated.value.length - 1;
    } else {
      topFirstIndex.value--;
    }

    if (topSecondIndex.value === 0) {
      topSecondIndex.value = arrayOfHighestRated.value.length - 1;
    } else {
      topSecondIndex.value--;
    }
  }
}


/*
  DummyData 'movieArr', use props in production!!!
*/
const movieArr = ref([
  {
    "name": "The Matrix",
    "description": "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.",
    "genre": [
      { "id": 28, "name": "Action" },
      { "id": 878, "name": "Science Fiction" }
    ],
    "duration": 136,
    "releaseDate": "1999-03-31",
    "imageUrl": "https://image.tmdb.org/t/p/w500/f89U3ADr1oiB1s9GkdPOEpXUk5H.jpg",
    "rating": 87,
    "path": "/thematrix"
  },
  {
    "name": "Interstellar",
    "description": "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.",
    "genre": [
      { "id": 12, "name": "Adventure" },
      { "id": 18, "name": "Drama" },
      { "id": 878, "name": "Science Fiction" }
    ],
    "duration": 169,
    "releaseDate": "2014-11-07",
    "imageUrl": "https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg",
    "rating": 81
  },
  {
    "name": "The Dark Knight",
    "description": "When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham.",
    "genre": [
      { "id": 28, "name": "Action" },
      { "id": 80, "name": "Crime" },
      { "id": 18, "name": "Drama" }
    ],
    "duration": 152,
    "releaseDate": "2008-07-18",
    "imageUrl": "https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
    "rating": 85
  }
]);



</script>

<template>
  <main class="p-0 h-full relative">
    <div class="h-full w-full bg-[length:600%] z-0 bg-[bottom_6rem_right] bg-fixed bg-[url('/img/cinemabg.jpg')]">
      <!-- Length is needed to properly "zoom in" on the image -->
      <!-- This div only contains the background image -->
      <div class="p-1 z-10">
        <!-- Need padding here to be able to add margin on P tag. Else the whole bg image will follow with the margin -->
        <p class="w-4/5 mt-5 m-auto text-white z-10">Which movie do you want to watch?</p>
        <form class="mt-4 w-4/6 m-auto">
          <input class="p-2 h-10 w-full rounded-full m-auto text-black text-center" type="text"
            placeholder="Search movie...">
        </form>
      </div>
      <div class="w-full flex flex-col items-center">
        <div class="w-full">
          <h1 class="text-2xl ml-3 mt-16 text-left">Top 10</h1>
        </div>
        <div class="flex justify-center p-3 gap-5">
          <div class="rounded-md bg-slate-200 shadow-[0px_0px_8px_6px_rgba(255,255,255,0.6)]">
            <img class="object-cover" :src="arrayOfHighestRated[topFirstIndex].imageUrl" alt="">
          </div>
          <div class="rounded-md bg-slate-200 shadow-[0px_0px_8px_6px_rgba(255,255,255,0.6)]">
            <img class="object-cover" :src="arrayOfHighestRated[topSecondIndex].imageUrl" alt="">
          </div>
        </div>
        <nav>
          <button @click="adjustIndex('minus')"><span class="text-4xl material-symbols-outlined">
              chevron_left
            </span>
          </button>
          <button @click="adjustIndex('plus')"><span class="text-4xl material-symbols-outlined">
              chevron_right
            </span>
          </button>
        </nav>
      </div>

      <div>
        <h2 class="mt-8 mb-8 text-center text-4xl">Movies</h2>
        <div class="flex flex-col gap-10 items-center">
          <div class="flex flex-row rounded-2xl w-5/6 bg-gray-400 p-2 bg-opacity-40 " v-for="(movie, index) in movieArr"
            :key="index">
            <div v-if="index % 2 === 0" class="p-2 w-1/2">
              <img class="object-contain rounded-lg" :src=movie.imageUrl>
            </div>
            <aside class="p-1 flex flex-col justify-between w-1/2">
              <h3 class="text-center text-xl border-b-2">{{ movie.name }}</h3>
              <p class="text-center line-clamp-5">{{ movie.description }}</p>
              <p class="mt-1 text-center">
              <p v-for="genre in movie.genre"> {{ genre.name }}</p>
              </p>
            </aside>
            <div v-if="index % 2 !== 0" class="p-2 w-1/2">
              <img class="object-contain rounded-lg" :src=movie.imageUrl>
            </div>
          </div>
        </div>
      </div>
    </div>
  </main>
</template>
