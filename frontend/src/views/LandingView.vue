<script setup>
import { computed, ref } from 'vue';

const props = defineProps(['listOfMovies'])


/*
Sort on rating and keep the top 5 movies!
*/
const arrayOfHighestRated = computed(() => {
  if (!Array.isArray(props.listOfMovies) || props.listOfMovies.length === 0) {
    return [{
    "imageUrl": ""
  },{ 
    "imageUrl": ""
  }]; 
  }
  const arr = props.listOfMovies.toSorted((a, b) => {
    return b.rating - a.rating;
  })
  if (arr.length >= 5) {
    return arr.splice(0, 5);
  } else {
    return arr;
  }
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
          <div class="flex flex-row rounded-2xl w-5/6 bg-gray-400 p-2 bg-opacity-40 " v-for="(movie, index) in props.listOfMovies"
            :key="index">
            <div v-if="index % 2 === 0" class="p-2 w-1/2">
              <img class="object-contain rounded-lg" :src="`https://image.tmdb.org/t/p/w500${movie.imageUrl}`">
            </div>
            <aside class="p-1 flex flex-col justify-between w-1/2">
              <h3 class="text-center text-xl border-b-2">{{ movie.name }}</h3>
              <p class="text-center line-clamp-5">{{ movie.description }}</p>
              <p class="mt-1 text-center">
                <p v-for="genre in movie.genre"> {{ genre.name }}</p>
              </p>
            </aside>
            <div v-if="index % 2 !== 0" class="p-2 w-1/2">
              <img class="object-contain rounded-lg" :src="`https://image.tmdb.org/t/p/w500${movie.imageUrl}`">
            </div>
          </div>
        </div>
      </div>
    </div>
  </main>
</template>
