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
  const arr = movies.value.slice().sort((a, b) => b.averageRatingInPercentage - a.averageRatingInPercentage);
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
}

</script>

<template>
  <main class="p-0 h-full relative">
    <div class="h-full w-full bg-[length:600%] md:bg-[length:300%] lg:bg-[length:100%] z-0 bg-[bottom_6rem_right] lg:bg-[bottom_right] bg-fixed bg-[url('/img/cinemabg.jpg')]">
      <!-- Length is needed to properly "zoom in" on the image -->
      <!-- This div only contains the background image -->
      <div class="p-1 z-10">
        <!-- Need padding here to be able to add margin on P tag. Else the whole bg image will follow with the margin -->
        <p class="w-4/5 mt-6 md:mt-10 m-auto text-center text-white z-10">Which movie do you want to watch?</p>
        <form :class="{'flex':isSearching}" @submit.prevent="displaySearch()" class="text-center mt-4 w-4/6 sm:w-1/2 md:w-2/5 md:pb-5 m-auto">
          <input v-model="query" :class="['p-2 rounded-full h-10 md:h-14 w-full text-black text-center', { 'outline-none w-5/6 rounded-r-none': isSearching }]"
  type="text"
            placeholder="Search movie...">
            <button v-if="isSearching" class="w-1/6 flex items-center justify-center rounded-r-3xl border-l-black bg-red-600"><span class="material-symbols-outlined">
undo
</span></button>
        </form>
      </div>
      <transition name="top" appear>
      <div v-if="!isSearching" class="w-full flex flex-col items-center">
        <div class="px-1 md:px-2">
        <div class="w-full">
          <h3 class="ml-3 mt-16 text-left sm:pb-2 md:pb-2">Top 5</h3>
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
        </div>
        <nav>
          <button @click="adjustIndex('minus')"><span
              class="text-4xl material-symbols-outlined">chevron_left</span></button>
          <button @click="adjustIndex('plus')"><span
              class="text-4xl material-symbols-outlined">chevron_right</span></button>
        </nav>
      </div>
    </transition>
        <h2 v-if="isSearching && moviesContainQuery.length === 0" class="mt-8 mb-8 text-center"> Nothing found!</h2>
        <h2 v-else class="mt-8 mb-8 text-center"> {{ isSearching ? 'Result:' : 'Movies' }}</h2>
        <div class="m-auto" v-if="isSearching && moviesContainQuery.length === 0">
            <img class="h-full w-full rounded-2xl" src="/img/camera.png" alt="">
          </div>
        <transition>
        <div :class="['move-up', { 'move-up-active': isSearching }]" class="flex flex-col gap-10 items-center">
          <router-link  v-for="(movie, index) in  (isSearching ? moviesContainQuery : movies)" :key="index"
            :to="{ name: 'movieProfile', params: { id: movie.id, title: movie.name.replace(/\s+/g, '-') } }"
            class="flex flex-row rounded-2xl w-5/6 xl:w-2/3 2xl:w-[1000px] bg-gray-700 p-2 md:mt-5 xl:mt-8 md:p-6 md:gap-4 bg-opacity-50">
            <div v-if="index % 2 === 0" class="p-2 w-1/2">
              <img class="object-contain rounded-lg" :src="`https://image.tmdb.org/t/p/w500${movie.imageUrl}`">
            </div>
            <aside class="p-1 w-1/2">
              <h3 class="text-center border-b-2 pb-2 sm:pb-3 ml:pb-4">{{ movie.name }}</h3>
              <div class="flex flex-row justify-center align-super space-x-2 sm:space-x-3 md:space-x-4 ml:space-x-5 my-1 sm:my-2">
                <div v-for="(genre, index) in movie.genres" :key="index" class="text-xxs sm:text-xs md:text-md lg:text-lg pt-1 sm:pt-2 md:py-4">
                  <span>{{ genre }}</span>
                </div>
              </div>
              <p class="text-center line-clamp-5 sm:line-clamp-6">{{ movie.description }}</p>
            </aside>
            <div v-if="index % 2 !== 0" class="p-2 w-1/2">
              <img class="object-contain rounded-lg" :src="`https://image.tmdb.org/t/p/w500${movie.imageUrl}`">
            </div>
          </router-link>
          </div>
        </transition>
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

.move-up {
  transition: transform 0.5s ease;
}
</style>