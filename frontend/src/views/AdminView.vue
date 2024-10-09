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
            <div class="flex gap-5">
              <div class="text-center p-5">
                <div class="py-3">
                  <label class="block uppercase" for="theatre">Theatre</label>
                  <input class="border-solid border border-black h-8" type="text" id="theatre">
                </div>
                <div class="py-3">
                  <label class="block uppercase" for="movie-hall">Movie Hall</label>
                  <input class="border-solid border border-black h-8" type="text" id="movie-hall">
                </div>
                <div class="py-3">
                  <label class="block uppercase" for="movie">Movie</label>
                  <input class="border-solid border border-black h-8" type="text" id="movie">
                </div>
                <div class="py-3">
                  <label class="block uppercase" for="date">Date</label>
                  <input class="border-solid border border-black h-8" type="text" id="date">
                </div>
              </div>
              <div class="p-5 bg-gray-100 my-10 mx-5 w-80 ">
                Calendar
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