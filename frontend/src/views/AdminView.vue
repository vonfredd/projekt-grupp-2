<script setup>
import { ref, watch } from 'vue';  // Make sure to import watch from Vue
import VueDatePicker from '@vuepic/vue-datepicker';
import Accordion from '@/components/Accordion.vue';  // Accordion component
import '@vuepic/vue-datepicker/dist/main.css';  // DatePicker styles

// Accordion items array
const accordionItems = [
    { id: 0, label: 'Add movie', inputId: 'add-movie', placeholder: 'Enter movie name' },
    { id: 1, label: 'Remove movie from db', inputId: 'remove-movie', placeholder: 'Enter movie ID' },
    { id: 2, label: 'Add theatre', inputId: 'add-theatre', placeholder: 'Enter theatre name' },
    { id: 3, label: 'Add movie hall', inputId: 'add-movie-hall', placeholder: 'Enter hall name' }
];

// Ref for date picker and formatted date
const date = ref(new Date());
const formattedDate = ref('');

// Function to format the selected date
const formatDate = (selectedDate) => {
    const options = { year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit' };
    formattedDate.value = selectedDate ? selectedDate.toLocaleString(undefined, options) : '';
};

// Watch for changes in the date picker and update formattedDate
watch(date, (newDate) => {
    formatDate(newDate);
});
</script>

<template>
  <div id="admin-view">
    <header class="bg-primary text-white px-[10%] py-2 flex justify-between items-center">
      <h1>Cinema</h1>
      <div class="flex items-center gap-2">
        <div class="text-[2em] text-black">
          <i class="fa-regular fa-user"></i>
        </div>
        <RouterLink to="/login" class="bg-secondary px-6 py-2 text-[20px] uppercase rounded-3xl">
          Login
        </RouterLink>
      </div>
    </header>

    <main class="px-[10%] py-[5%] flex justify-between">
      <!-- Movie Schedule Section -->
      <div class="bg-gray-100 w-3/5 text-black p-10">
        <h2 class="text-center">Movie Schedule</h2>
        <div class="flex justify-between">
          <!-- Input Fields -->
          <div class="text-center p-4 my-8 w-2/4">
            <div class="py-3">
              <label class="block uppercase" for="theatre">Theatre</label>
              <select class="border-solid border border-black h-8 w-full" name="theatre" id="theatre" required>
                <option value="cinema-1">Cinema 1</option>
                <option value="cinema-2">Cinema 2</option>
              </select>
            </div>
            <div class="py-3">
              <label class="block uppercase" for="movie-hall">Movie Hall</label>
              <select class="border-solid border border-black h-8 w-full" name="movie-hall" id="movie-hall" required>
                <option value="hall-1">Hall 1</option>
                <option value="hall-2">Hall 2</option>
              </select>
            </div>
            <div class="py-3">
              <label class="block uppercase" for="movie">Movie</label>
              <input class="border-solid border border-black h-8 w-full" type="text" id="movie">
            </div>
            <div class="py-3">
                <label class="block uppercase" for="selected-date" >Selected Date and Time</label>
                <input class="px-1 border-solid border border-black h-8 w-full" type="text" id="selected-date" v-model="formattedDate"
                    placeholder="Select using calendar" readonly/>
            </div>
          </div>

          <!-- Date Picker -->
          <div class="p-5 my-10">
            <VueDatePicker v-model="date" inline auto-apply />
          </div>
        </div>

        <!-- Selected Date and Time Display -->
        
      </div>

      <!-- Accordion Section -->
      <div class="w-1/3 text-black">
        <Accordion :items="accordionItems" />
      </div>
    </main>

    <RouterView />
  </div>
</template>

<script>
export default {
    components: { VueDatePicker, Accordion },
};
</script>

<style scoped>
/* Add any custom styles if needed */
</style>
