<script setup>
import { ref, watch } from "vue";
import "@vuepic/vue-datepicker/dist/main.css";
import VueDatePicker from "@vuepic/vue-datepicker";

const date = ref(new Date());
const formattedDate = ref("");
const dateTime = ref("");

const movies = ref([]);
const filteredMovies = ref([]);
const movieQuery = ref("");

const cinema = ref("");
const cinemaHall = ref("");
const selectedMovie = ref("");

const cinemas = ref("");

// Function to format the selected date
const formatDate = (selectedDate) => {
  formattedDate.value = selectedDate ? selectedDate.toLocaleString() : "";
};

const convertDateToLocalDateTime = (selectedDate) => {
    dateTime.value = selectedDate? selectedDate.toISOString().slice(0, 19) : "";
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


// Watcher to update formattedDate and dateTime whenever date changes
watch(date, (newDate) => {
  formatDate(newDate);
  convertDateToLocalDateTime(newDate);
});

// Watcher to filter movies whenever movieQuery changes
watch(movieQuery, filterMovies);

//
const isFormValid = ref(false);

// Watcher to update form validity whenever any form field changes
watch([cinema, cinemaHall, selectedMovie, formattedDate], () => {
  isFormValid.value = cinema.value && cinemaHall.value && selectedMovie.value && formattedDate.value;
});

// Function to handle form submission
const handleSubmit = async () => {
  const submissionDetails = `
    Cinema: ${JSON.stringify(cinema.value)}
    Cinema Hall: ${JSON.stringify(cinemaHall.value)}
    Movie: ${JSON.stringify(selectedMovie.value)}
    Date: ${JSON.stringify(formattedDate.value)}
  `;
  alert(`Form submitted:\n${submissionDetails}`);

  const schedule = {
    localDateTime: dateTime.value,
    cinema: cinema.value,
    cinemaHall: cinemaHall.value,
    movie: selectedMovie.value
  };

  try {
    const response = await fetch('http://localhost:9000/schedules/new', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(schedule)
    });

    if (!response.ok) {
      throw new Error(`Error: ${response.statusText}`);
    }

    const result = await response.json();
    console.log('Schedule submitted successfully:', result);

    cinema.value = null;
    cinemaHall.value = null;
    selectedMovie.value = null;
    formattedDate.value = "";
    dateTime.value = "";

  } catch (error) {
    console.error('Error submitting schedule:', error);
  }
  formattedDate.value = "";
};
</script>


<template>
  <h2 class="text-center mb-6">Movie Schedule</h2>
  <div class="flex justify-between gap-10">
    <div class="max-w-lg ml-5">
      <form class="py-3 text-center">
        <label class="block uppercase" for="cinema">Cinema</label>
        <select
          @click="getCinemas"
          class="border-solid border border-black h-8 w-full"
          name="cinema"
          id="cinema"
          v-model="cinema"
          required
        >
          <option v-for="(cinema, index) in cinemas" :value="cinema" :key="index">{{ cinema.name }}</option>
        </select>
      </form>
      <form class="py-3 text-center">
        <label class="block uppercase" for="cinema-hall">Cinema Hall</label>
        <select
          class="border-solid border border-black h-8 w-full"
          name="cinema-hall"
          id="cinema-hall"
          v-model="cinemaHall"
          required
        >
          <option
              v-for="(hall, index) in cinema?.cinemaHalls"
              :value="hall"
              :key="index"
          >
          {{ hall.name }}
          </option>
        </select>
      </form>
      <form class="py-3 text-center">
        <label class="block uppercase" for="selected-movie">Movie</label>
        <select
          class="border-solid border border-black h-8 w-full"
          id="selected-movie"
          v-model="selectedMovie"
          @focus="searchMovies"
        >
          <option value="" disabled selected>Select a movie</option>
          <option
            v-for="movie in filteredMovies"
            :key="movie.id"
            :value="movie"
          >
            {{ movie.name }}
          </option>
        </select>
        <input
          class="border-solid border border-grey h-8 w-full mt-1 px-1"
          type="text"
          v-model="movieQuery"
          placeholder="Type to filter movies"
          @keydown.enter.prevent
        />
      </form>
      <form class="py-3 text-center">
        <label class="block uppercase" for="selected-date"
          >Selected Date & Time</label
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
      <button
          id="schedule-movie-button"
          type="button"
          class="mt-6 px-4 py-2 bg-primary text-white rounded disabled:bg-greyish hover:bg-secondary justify-start uppercase"
          :disabled="!isFormValid"
          @click="handleSubmit"
      >
        Schedule Movie
      </button>

    </div>

    <div class="p-5 my-10">
      <VueDatePicker v-model="date" inline auto-apply />
    </div>
  </div>
</template>
<style scoped></style>
