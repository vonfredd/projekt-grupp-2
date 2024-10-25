<script setup>
import {onMounted, ref} from 'vue';

const activeAccordion = ref(null);
const cinemas = ref([]);
const selectedCinema = ref(null);
const nrOfSeats = ref(null);

// Load the list of cinemas on page load
onMounted(() => {
  populateCinemas();
});
const populateCinemas = async () => {
  const response = await fetch("http://localhost:9000/cinemas");
  cinemas.value = await response.json();
}

const addMovieHall = async (hallName, numberOfSeats, cinemaName) => {
  try {
    const response = await fetch(`http://localhost:9000/cinemas/${cinemaName}/halls`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ name: hallName, nrOfSeats: Number(numberOfSeats) }),
    });

    if (!response.ok) {
      throw new Error('HTTP error ' + response.status);
    }

    await populateCinemas()
    const responseJson = await response.json();
    console.log(responseJson);
  } catch (error) {
    console.error('Error adding movie hall:', error);
  }
};

const toggleAccordion = (index) => {
  activeAccordion.value = activeAccordion.value === index ? null : index;
};

const accordionItems = ref([
  {id: 0, label: 'Add movie', inputId: 'add-movie', placeholder: 'Enter movie name', value: ''},
  {id: 1, label: 'Remove movie from db', inputId: 'remove-movie', placeholder: 'Enter movie ID', value: ''},
  {id: 2, label: 'Add Cinema', inputId: 'add-cinema', placeholder: 'Enter cinema name', value: ''},
  {
    id: 3,
    label: 'Add movie hall',
    inputId: 'add-movie-hall',
    placeholder: 'Enter hall name',
    value: ''
  }
]);

const addMovieToDb = async (idToFetch) => {
  console.log('Fetch movie details from backend');
  const response = await fetch(`http://localhost:9000/movies/fetch/${idToFetch}`);

  if (!response.ok) {
    alert("Movie not found");
    return;
  }

  const data = await response.json();

  const userConfirmed = confirm(`Do you want to add "${data.title}" to our database?`);

  if (!userConfirmed) {
    return;
  }

  const addResponse = await fetch("http://localhost:9000/movies", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  });

  const responseJson = await addResponse.json();
  console.log(responseJson);
  alert("Movie added: " + JSON.stringify(responseJson));
}

const removeMovie = async (idToDelete) => {

  await fetch(`http://localhost:9000/movies/${idToDelete}`, {
    method: "DELETE"
  });

}

const addCinema = async (newCinemaName) => {

  const response = await fetch("http://localhost:9000/cinemas", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({name: newCinemaName}),
  });
  await populateCinemas()
  const responseJson = await response.json();
  console.log(responseJson);
}

function switchAccordionEvent(idOfItem) {

  const accordionItem = accordionItems.value[idOfItem];

  switch (idOfItem) {
    case 0:
      addMovieToDb(accordionItem.value)
      accordionItem.value = '';
      break;
    case 1:
      removeMovie(accordionItem.value)
      accordionItem.value = '';
      break;
    case 2:
      addCinema(accordionItem.value)
      accordionItem.value = '';
      break;
    case 3:
      addMovieHall(accordionItem.value, nrOfSeats.value, selectedCinema.value)
      accordionItem.value = '';
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
          <div v-if="item.id === 3" class="flex-grow">
            <select v-model="selectedCinema" class="border-solid border border-black mb-2">
              <option disabled value="">Select a cinema</option>
              <option v-for="cinema in cinemas" :key="cinema.name" :value="cinema.name">
                {{ cinema.name }}
              </option>
            </select>
            <input v-model="nrOfSeats" class="border-solid border border-black mb-2"
                   type="text" min="1" placeholder="Enter number of seats"/>
          </div>

          <input v-model="item.value" class="border-solid border border-black h-8 px-1" :id="item.inputId"
                 :placeholder="item.placeholder" type="text"/>
          <input type="submit" hidden/>
        </form>
      </div>
    </section>
  </div>
</template>
