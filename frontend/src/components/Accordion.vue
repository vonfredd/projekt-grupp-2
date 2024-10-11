<script setup>
import { ref } from 'vue';

const activeAccordion = ref(null);

const toggleAccordion = (index) => {
    activeAccordion.value = activeAccordion.value === index ? null : index;
};

const accordionItems = [
    { id: 0, label: 'Add movie', inputId: 'add-movie', placeholder: 'Enter movie name' },
    { id: 1, label: 'Remove movie from db', inputId: 'remove-movie', placeholder: 'Enter movie ID' },
    { id: 2, label: 'Add theatre', inputId: 'add-theatre', placeholder: 'Enter theatre name' },
    { id: 3, label: 'Add movie hall', inputId: 'add-movie-hall', placeholder: 'Enter hall name' }
];

const addMovieToDb = async (idToFetch) => {
    console.log('Add movie to db');
    const url = "https://api.themoviedb.org/3/movie/" + idToFetch;
    const ACCESS_TOKEN = "";

    const req = await fetch(url,{ headers: { 'Authorization': `Bearer ${ACCESS_TOKEN}`}});
    const data = await req.json();

  console.log(JSON.stringify(data))

    const response = await fetch("http://localhost:9000/movies",{
      method:"POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    });
    const responseJson = await response.json();
    console.log(responseJson);
}



const accordionInput = ref('');

function switchAccordionEvent(idOfItem) {

  switch (idOfItem) {
    case 0:
      addMovieToDb(accordionInput.value)
      break;
    case 1:
    //  removeMovie(accordionInput.value)
      break;
    case 2:
     // addTheatre(accordionInput.value)
      break;
    case 3:
     // addMovieHall(accordionInput.value)
      break;
  }
}
</script>

<template>
  <div class="w-full max-w-lg mx-auto" id="accordion">
    <section v-for="item in accordionItems" :key="item.id" class="border-b border-gray-300">
      <h4>
        <button
          @click="toggleAccordion(item.id)"
          class="w-full text-left px-4 py-2 bg-gray-100 hover:bg-gray-200 focus:outline-none h-20"
          :aria-expanded="activeAccordion === item.id"
          :aria-controls="'section-' + item.id"
        >
          <div class="flex justify-between items-center uppercase">
            <label class="block uppercase" :for="item.inputId">{{ item.label }}</label>
            <span :class="{ 'rotate-180': activeAccordion === item.id }" class="transform transition-transform">
              <i class="fas fa-chevron-down"></i>
            </span>
          </div>
        </button>
      </h4>

      <div
        :id="'section-' + item.id"
        v-show="activeAccordion === item.id"
        class="overflow-hidden transition-all duration-300"
      >
        <form @submit.prevent="switchAccordionEvent(item.id)" class="p-8 bg-gray-50 h-20 flex items-center">
          <input v-model="accordionInput" class="border-solid border border-black h-8 px-1" :id="item.inputId" :placeholder="item.placeholder" type="text" />
          <input type="submit" hidden />
        </form>
      </div>
    </section>
  </div>
</template>
