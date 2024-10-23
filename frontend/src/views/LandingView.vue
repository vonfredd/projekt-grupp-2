<script setup>
import { RouterLink } from 'vue-router';
import {computed, ref, onMounted} from 'vue';

const movies = ref([]);
const fetchMovies = async () => {
  const response = await fetch('http://localhost:9000/movies');
  movies.value = await response.json();
};

onMounted(fetchMovies);

/*
Sort on rating and keep the top 5 movies!
*/
const arrayOfHighestRated = computed(() => {
  if (!Array.isArray(movies.value) || movies.value.length === 0) {
    return [{
    "imageUrl": ""
  },{
    "imageUrl": ""
  }];
  }
  const arr = movies.value.slice().sort((a, b) => b.rating - a.rating);
  return arr.slice(0, 5);
});

const topFirstIndex = ref(0);
const topSecondIndex = ref(1);

/*
Adjust the top 5 movies shown.
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

const query = ref('');
const isSearching = ref(false);
const moviesContainQuery = ref();

function displaySearch() {
  isSearching.value = true;
  if (query.value === '') {
    moviesContainQuery.value = movies.value;
    isSearching.value = false;
  }
  moviesContainQuery.value = movies.value.filter(movie => movie.name.toLowerCase().includes(query.value.toLowerCase()));
  query.value = '';
};

</script>

<template>
  <main class="p-0 h-full relative">
    <div class="h-full w-full bg-[length:600%] z-0 bg-[bottom_6rem_right] bg-fixed bg-[url('/img/cinemabg.jpg')]">
      <!-- Length is needed to properly "zoom in" on the image -->
      <!-- This div only contains the background image -->
      <div class="p-1 z-10">
        <!-- Need padding here to be able to add margin on P tag. Else the whole bg image will follow with the margin -->
        <p class="w-4/5 mt-5 m-auto text-white z-10">Which movie do you want to watch?</p>
        <form @submit.prevent="displaySearch()" class="mt-4 w-4/6 m-auto">
          <input v-model="query" class="p-2 h-10 w-full rounded-full m-auto text-black text-center" type="text"
            placeholder="Search movie...">
        </form>
      </div>
      <transition name="top" appear>
      <div v-if="!isSearching" class="w-full flex flex-col items-center">
        <div class="w-full">
          <h1 class="text-2xl ml-3 mt-16 text-left">Top 5</h1>
        </div>
        <div class="flex justify-center p-3 gap-5">
          <router-link v-if="arrayOfHighestRated[topFirstIndex]?.id" :to="{ name: 'movieProfile', params: { id: arrayOfHighestRated[topFirstIndex]?.id, title: arrayOfHighestRated[topFirstIndex]?.name.replace(/\s+/g, '-') } }" class="rounded-md bg-slate-200 shadow-[0px_0px_8px_6px_rgba(255,255,255,0.6)]">
            <img class="object-cover" :src="`https://image.tmdb.org/t/p/w500${arrayOfHighestRated[topFirstIndex]?.imageUrl}`" alt="">
          </router-link>
          <router-link v-if="arrayOfHighestRated[topSecondIndex]?.id"
            :to="{ name: 'movieProfile', params: { id: arrayOfHighestRated[topSecondIndex]?.id, title: arrayOfHighestRated[topSecondIndex]?.name.replace(/\s+/g, '-') } }"
            class="rounded-md bg-slate-200 shadow-[0px_0px_8px_6px_rgba(255,255,255,0.6)]">
            <img class="object-cover"
              :src="`https://image.tmdb.org/t/p/w500${arrayOfHighestRated[topSecondIndex]?.imageUrl}`" alt="">
          </router-link>
        </div>
        <nav>
          <button @click="adjustIndex('minus')"><span
              class="text-4xl material-symbols-outlined">chevron_left</span></button>
          <button @click="adjustIndex('plus')"><span
              class="text-4xl material-symbols-outlined">chevron_right</span></button>
        </nav>
      </div>
    </transition>
      <div>
        <h2 class="mt-8 mb-8 text-center text-4xl"> {{ isSearching ? 'Result:' : 'Movies' }}</h2>
        <transition>
        <div :class="['move-up', { 'move-up-active': isSearching }]" class="flex flex-col gap-10 items-center">
          <router-link  v-for="(movie, index) in  (isSearching ? moviesContainQuery : movies)" :key="index"
            :to="{ name: 'movieProfile', params: { id: movie.id, title: movie.name.replace(/\s+/g, '-') } }"
            class="flex flex-row rounded-2xl w-5/6 bg-gray-400 p-2 bg-opacity-40">
            <div v-if="index % 2 === 0" class="p-2 w-1/2">
              <img class="object-contain rounded-lg" :src="`https://image.tmdb.org/t/p/w500${movie.imageUrl}`">
            </div>
            <aside class="p-1 flex flex-col justify-between w-1/2">
              <h3 class="text-center text-xl border-b-2">{{ movie.name }}</h3>
              <p class="text-center line-clamp-5">{{ movie.description }}</p>
              <p class="mt-1 text-center">
              <p v-for="genre in movie.genres"> {{ genre }}</p>
              </p>
            </aside>
            <div v-if="index % 2 !== 0" class="p-2 w-1/2">
              <img class="object-contain rounded-lg" :src="`https://image.tmdb.org/t/p/w500${movie.imageUrl}`">
            </div>
          </router-link>
          </div>
        </transition>
        </div>
      </div>
  </main>
</template>


<style scoped>
.top-enter-from {
  opacity: 0;
  transform: translateY(20px);
}
.top-enter-to {
  opacity: 1;
  transform: translateY(0);
}
.top-leave-from {
  opacity: 1;
}
.top-leave-to {
  opacity: 0;
}

.top-enter-active{
  transition: all 0.4s ease;
}

.move-up-active {
  transform: translateY(-10px);
}

.move-up {
  transition: transform 0.5s ease;
}

</style>