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
        <div class="p-8 bg-gray-50 h-20 flex items-center">
          <input class="border-solid border border-black h-8" :id="item.inputId" :placeholder="item.placeholder" type="text" />
        </div>
      </div>
    </section>
  </div>
</template>
