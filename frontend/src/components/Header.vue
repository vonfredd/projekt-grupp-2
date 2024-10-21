<script setup>
import { RouterLink } from "vue-router";
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
  <header class="bg-primary">
    <nav class="p-3">
      <div class="flex items-center justify-between">
        <RouterLink to="/" class="block"
          ><h1 class="text-5xl p-1 px-7 uppercase">Cinema</h1>
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
        <div class="hidden md:flex text-white">
          <RouterLink to="/login">
            <div
              class="flex items-center justify-center bg-secondary bg-opacity-50 px-5 rounded-full mx-2"
            >
              <svg
                fill="#000000"
                width="20px"
                height="20px"
                viewBox="0 0 34 34"
                version="1.1"
                preserveAspectRatio="xMidYMid meet"
                xmlns="http://www.w3.org/2000/svg"
                xmlns:xlink="http://www.w3.org/1999/xlink"
              >
                <title>user-solid</title>
                <path
                  d="M30.61,24.52a17.16,17.16,0,0,0-25.22,0,1.51,1.51,0,0,0-.39,1v6A1.5,1.5,0,0,0,6.5,33h23A1.5,1.5,0,0,0,31,31.5v-6A1.51,1.51,0,0,0,30.61,24.52Z"
                  class="clr-i-solid clr-i-solid-path-1"
                ></path>
                <circle
                  cx="18"
                  cy="10"
                  r="7"
                  class="clr-i-solid clr-i-solid-path-2"
                ></circle>
                <rect x="0" y="0" width="36" height="36" fill-opacity="0" />
              </svg>

              <h5 class="block p-4 hover:text-gray-300 uppercase">Login</h5>
            </div>
          </RouterLink>

          <RouterLink to="/admin" class="block p-4 hover:text-gray-300"
            >Admin</RouterLink
          >
        </div>
      </div>

      <!-- Mobile Menu -->
      <div v-if="isMenuOpen" class="md:hidden">
        <RouterLink to="/admin" class="block p-4 hover:text-gray-300"
          >Admin</RouterLink
        >
        <RouterLink to="/login">
          <div
            class="flex items-center justify-center bg-gray-400 bg-opacity-50 rounded-3xl px-2 mx-2"
          >
            <svg
              fill="#000000"
              width="25px"
              height="25px"
              viewBox="0 0 36 36"
              version="1.1"
              preserveAspectRatio="xMidYMid meet"
              xmlns="http://www.w3.org/2000/svg"
              xmlns:xlink="http://www.w3.org/1999/xlink"
            >
              <title>user-solid</title>
              <path
                d="M30.61,24.52a17.16,17.16,0,0,0-25.22,0,1.51,1.51,0,0,0-.39,1v6A1.5,1.5,0,0,0,6.5,33h23A1.5,1.5,0,0,0,31,31.5v-6A1.51,1.51,0,0,0,30.61,24.52Z"
                class="clr-i-solid clr-i-solid-path-1"
              ></path>
              <circle
                cx="18"
                cy="10"
                r="7"
                class="clr-i-solid clr-i-solid-path-2"
              ></circle>
              <rect x="0" y="0" width="36" height="36" fill-opacity="0" />
            </svg>

            <h5 class="block p-4 hover:text-gray-300 uppercase">Login</h5>
          </div>
        </RouterLink>
      </div>
    </nav>
  </header>
</template>

<style scoped></style>
