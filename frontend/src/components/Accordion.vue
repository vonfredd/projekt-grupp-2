<script setup>
import { ref } from 'vue';

const activeAccordion = ref(null);

const toggleAccordion = (index) => {
    activeAccordion.value = activeAccordion.value === index ? null : index;
};
</script>

<template>
  <div class="w-full max-w-lg mx-auto" id="accordion">
    <section class="border-b border-gray-300">
      <h3>
        <button
          @click="toggleAccordion(0)"
          class="w-full text-left px-4 py-2 bg-gray-100 hover:bg-gray-200 focus:outline-none h-20">
          <div class="flex justify-between items-center uppercase">
            <label class="block uppercase" for="add-movie">Add movie</label>
            <span :class="{ 'rotate-180': activeAccordion === 0 }" class="transform transition-transform">
              <i class="fas fa-chevron-down"></i>
            </span>
          </div>
        </button>
      </h3>

      <div
        id="section-1"
        v-show="activeAccordion === 0"
        class="overflow-hidden transition-all duration-300">
        <div class="p-8 bg-gray-50 h-20 flex items-center">
          <input class="border-solid border-2 border-black h-8" type="text" id="add-movie" placeholder="Enter movie name">
        </div>
      </div>
    </section>

    <section class="border-b border-gray-300">
      <h3>
        <button
          @click="toggleAccordion(1)"
          class="w-full text-left px-4 py-2 bg-gray-100 hover:bg-gray-200 focus:outline-none h-20"
          :aria-expanded="activeAccordion === 1"
          aria-controls="section-2">
          <div class="flex justify-between items-center uppercase">
            <label class="block uppercase" for="remove-movie">Remove movie from db</label>
            <span :class="{ 'rotate-180': activeAccordion === 1 }" class="transform transition-transform">
              <i class="fas fa-chevron-down"></i>
            </span>
          </div>
        </button>
      </h3>

      <div
        id="section-2"
        v-show="activeAccordion === 1"
        class="overflow-hidden transition-all duration-300">
        <div class="p-8 bg-gray-50 h-20 flex items-center">
          <input class="border-solid border-2 border-black h-8" type="text" id="remove-movie" placeholder="Enter movie ID">
        </div>
      </div>
    </section>

    <section class="border-b border-gray-300">
      <h3>
        <button
          @click="toggleAccordion(2)"
          class="w-full text-left px-4 py-2 bg-gray-100 hover:bg-gray-200 focus:outline-none h-20">
          <div class="flex justify-between items-center uppercase">
            <label class="block uppercase" for="add-theatre">Add theatre</label>
            <span :class="{ 'rotate-180': activeAccordion === 2 }" class="transform transition-transform">
              <i class="fas fa-chevron-down"></i>
            </span>
          </div>
        </button>
      </h3>

      <div
        id="section-3"
        v-show="activeAccordion === 2"
        class="overflow-hidden transition-all duration-300">
        <div class="p-8 bg-gray-50 h-20 flex items-center">
          <input class="border-solid border-2 border-black h-8" type="text" id="add-theatre" placeholder="Enter theatre name">
        </div>
      </div>
    </section>

    <section class="border-b border-gray-300">
      <h3>
        <button
          @click="toggleAccordion(3)"
          class="w-full text-left px-4 py-2 bg-gray-100 hover:bg-gray-200 focus:outline-none h-20">
          <div class="flex justify-between items-center uppercase">
            <label class="block uppercase" for="add-movie-hall">Add movie hall</label>
            <span :class="{ 'rotate-180': activeAccordion === 3 }" class="transform transition-transform">
              <i class="fas fa-chevron-down"></i>
            </span>
          </div>
        </button>
      </h3>

      <div
        id="section-4"
        v-show="activeAccordion === 3"
        class="overflow-hidden transition-all duration-300">
        <div class="p-8 bg-gray-50 h-20 flex items-center">
          <input class="border-solid border-2 border-black h-8" type="text" id="add-movie-hall" placeholder="Enter hall name">
        </div>
      </div>
    </section>
  </div>
</template>
