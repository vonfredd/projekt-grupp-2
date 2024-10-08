<script setup>
import { RouterLink, RouterView } from "vue-router";
import { ref } from "vue";
const isMenuOpen = ref(false);

const toggleMenu = () => {
  isMenuOpen.value = !isMenuOpen.value;
};

const closeMenu = () => {
  isMenuOpen.value = false;
};
</script>

<template>
  <header>
    <nav class="bg-cinema p-3">
      <div class="flex items-center justify-between">
        <RouterLink to="/" class="block hover:text-gray-300"
          ><h1 class="text-white text-4xl">CINEMA</h1>
        </RouterLink>

        <!-- Hamburger Button -->
        <button
          @click="toggleMenu"
          class="block md:hidden text-white focus:outline-none"
        >
          <!-- Hamburger Icon -->
          <svg
            v-if="!isMenuOpen"
            class="w-6 h-6"
            fill="none"
            stroke="currentColor"
            viewBox="0 0 24 24"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M4 6h16M4 12h16m-7 6h7"
            ></path>
          </svg>
          <!-- Close Icon -->
          <svg
            v-if="isMenuOpen"
            class="w-6 h-6"
            fill="none"
            stroke="currentColor"
            viewBox="0 0 24 24"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M6 18L18 6M6 6l12 12"
            ></path>
          </svg>
        </button>

        <!-- Desktop Menu -->
        <div class="hidden md:flex space-x-6 text-white">
          <RouterLink to="/admin" class="block p-4 hover:text-gray-300"
            >Admin</RouterLink
          >
          <RouterLink to="/login" class="block p-4 hover:text-gray-300"
            >Login</RouterLink
          >
        </div>
      </div>

      <!-- Mobile Menu -->
      <div v-if="isMenuOpen" class="md:hidden">
        <RouterLink to="/" class="block p-4 hover:text-gray-300"
          >Admin</RouterLink
        >
        <RouterLink to="/about" class="block p-4 hover:text-gray-300"
          >Login</RouterLink
        >
      </div>
    </nav>
  </header>

  <RouterView />
</template>

<style scoped></style>
