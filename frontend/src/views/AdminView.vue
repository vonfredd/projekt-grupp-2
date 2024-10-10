<script setup>
import { ref, watch } from "vue";
import VueDatePicker from "@vuepic/vue-datepicker";
import Accordion from "@/components/Accordion.vue";
import "@vuepic/vue-datepicker/dist/main.css";

const date = ref(new Date());
const formattedDate = ref("");

const formatDate = (selectedDate) => {
  const options = {
    year: "numeric",
    month: "long",
    day: "numeric",
    hour: "2-digit",
    minute: "2-digit",
  };
  formattedDate.value = selectedDate
    ? selectedDate.toLocaleString(undefined, options)
    : "";
};

watch(date, (newDate) => {
  formatDate(newDate);
});
</script>

<template>
  <div id="admin-view">
    <main class="px-[10%] py-[5%] flex justify-between">
      <div class="bg-gray-100 w-3/5 text-black p-10">
        <h2 class="text-center">Movie Schedule</h2>
        <div class="flex justify-between">
          <div class="text-center p-4 my-8 w-2/4">
            <div class="py-3">
              <label class="block uppercase" for="theatre">Theatre</label>
              <select
                class="border-solid border border-black h-8 w-full"
                name="theatre"
                id="theatre"
                required
              >
                <option value="cinema-1">Cinema 1</option>
                <option value="cinema-2">Cinema 2</option>
              </select>
            </div>
            <div class="py-3">
              <label class="block uppercase" for="movie-hall">Movie Hall</label>
              <select
                class="border-solid border border-black h-8 w-full"
                name="movie-hall"
                id="movie-hall"
                required
              >
                <option value="hall-1">Hall 1</option>
                <option value="hall-2">Hall 2</option>
              </select>
            </div>
            <div class="py-3">
              <label class="block uppercase" for="movie">Movie</label>
              <input
                class="border-solid border border-black h-8 w-full"
                type="text"
                id="movie"
              />
            </div>
            <div class="py-3">
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
            </div>
          </div>

          <div class="p-5 my-10">
            <VueDatePicker v-model="date" inline auto-apply />
          </div>
        </div>
      </div>

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

<style scoped></style>
