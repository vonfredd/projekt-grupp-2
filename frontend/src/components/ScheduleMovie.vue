<script setup>
import { ref, watch, computed } from "vue";
import "@vuepic/vue-datepicker/dist/main.css";
import VueDatePicker from "@vuepic/vue-datepicker";

// Date handling
const date = ref(new Date());
const formattedDate = ref("");

// Movie handling
const movies = ref([]);
const filteredMovies = ref([]);
const movieQuery = ref("");
const selectedMovie = ref(null);

// Form fields
const theatre = ref("");
const movieHall = ref("");
const selectedMovieId = ref("");
const selectedDate = ref("");

//Save fetched cinema
const cinemas = ref("");

// Function to format the selected date
const formatDate = (selectedDate) => {
  formattedDate.value = selectedDate ? selectedDate.toLocaleString() : "";
};
// Functions to search and handle movie selection
const searchMovies = async () => {
  const response = await fetch(
    `http://localhost:9000/movies/name?name=${movieQuery.value}`,
    {
      method: "GET",
    }
  );
  movies.value = await response.json();
  filteredMovies.value = movies.value;
};

const getCinemas = async () => {
  const response = await fetch(
    `http://localhost:9000/cinemas`,
    {
      method: "GET",
    }
  );
  cinemas.value = await response.json();
};

// Function to filter movies based on query
const filterMovies = () => {
  filteredMovies.value = movies.value.filter((movie) =>
    movie.name.toLowerCase().includes(movieQuery.value.toLowerCase())
  );
};


// Watcher to update formattedDate whenever date changes
watch(date, (newDate) => {
  formatDate(newDate);
});

// Watcher to filter movies whenever movieQuery changes
watch(movieQuery, filterMovies);

// Computed property to check if all forms are filled
const isFormValid = computed(() => {
  return (
    theatre.value &&
    movieHall.value &&
    selectedMovieId.value &&
    formattedDate.value
  );
});

// Function to handle form submission
const handleSubmit = () => {
  const submissionDetails = `
    Theatre: ${JSON.stringify(theatre.value)}
    Movie Hall: ${JSON.stringify(movieHall.value)}
    Movie: ${JSON.stringify(selectedMovieId.value)}
    Date: ${JSON.stringify(formattedDate.value)}
  `;
  alert(`Form submitted:\n${submissionDetails}`);
};
</script>
<template>
  <h2 class="text-center">Movie Schedule</h2>
  <div class="flex justify-between">
    <div class="text-center p-4 my-8 w-2/4">
      <form class="py-3">
        <label class="block uppercase" for="theatre">Theatre</label>
        <select
          @click="getCinemas"
          class="border-solid border border-black h-8 w-full"
          name="theatre"
          id="theatre"
          v-model="theatre"
          required
        >
          <option v-for="(cinema, index) in cinemas" :value=cinema :key=index>{{ cinema.name }}</option>
        </select>
      </form>
      <form class="py-3">
        <label class="block uppercase" for="movie-hall">Movie Hall</label>
        <select
          class="border-solid border border-black h-8 w-full"
          name="movie-hall"
          id="movie-hall"
          v-model="movieHall"
          required
        >
          <option 
          v-for="(hall,index) in theatre.showRooms"
          :value=hall 
          :key=index
          >
          {{ hall.name }}
        </option>
        </select>
      </form>
      <form class="py-3">
        <label class="block uppercase" for="selected-movie">Movie</label>
        <select
          class="border-solid border border-black h-8 w-full"
          id="selected-movie"
          v-model="selectedMovieId"
          @focus="searchMovies"
        >
          <option value="" disabled selected>Select a movie</option>
          <option
            v-for="movie in filteredMovies"
            :key="movie.id"
            :value=movie
          >
            {{ movie.name }}
          </option>
        </select>
        <input
          class="border-solid border border-grey h-6 w-full mt-1 ml-0"
          type="text"
          v-model="movieQuery"
          placeholder="Type to filter movies"
          @keydown.enter.prevent
        />
      </form>
      <form class="py-3">
        <label class="block uppercase" for="selected-date"
          >Selected Date and Time</label
        >
        <input
          class="px-1 border-solid border border-black h-8 w-full"
          type="text"
          id="selected-date"
          v-model="formattedDate"
          placeholder="Select using calendar"
          readonly
        />
      </form>
    </div>

    <div class="p-5 my-10">
      <VueDatePicker v-model="date" inline auto-apply />
    </div>
  </div>
  <button
    type="button"
    class="mt-3 px-4 py-2 self-center bg-primary text-white rounded disabled:bg-greyish hover:bg-secondary"
    :disabled="!isFormValid"
    @click="handleSubmit"
  >
    Schedule Movie
  </button>
</template>
<style scoped></style>
