<script setup>
import Accordion from '@/components/Accordion.vue';
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
          <div class="bg-gray-100 w-3/5 text-black p-10">
            <h2 class="text-center">Movie Schedule</h2>
            <div class="flex justify-between">
              <div class="text-center p-5 my-10">
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
import { ref } from 'vue';
import VueDatePicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css'

export default {
    components: { VueDatePicker },
    setup() {
        const date = ref(new Date());

        return {
            date
        }
    }
};
</script>