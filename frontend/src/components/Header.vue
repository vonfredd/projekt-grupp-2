<script setup>
import { RouterLink } from "vue-router";
import { ref, onMounted } from "vue";
const isMenuOpen = ref(false);

const toggleMenu = () => {
  isMenuOpen.value = !isMenuOpen.value;
};

const closeMenu = () => {
  isMenuOpen.value = false;
};

const isLoggedIn = ref(false);
const isAdmin = ref(false);

onMounted(() => {
  isLoggedIn.value = localStorage.getItem('userData') !== null;
  window.addEventListener('storage', () => {
    isLoggedIn.value = !isLoggedIn.value;
  });
  window.addEventListener('admin', () => {
    isAdmin.value = !isAdmin.value;
    isLoggedIn.value = !isLoggedIn.value;
  })
});
</script>

<template>
  <header class="bg-primary p-3">
    <nav>
      <div class="flex items-center justify-between md:px-3 lg:px-5">
        <RouterLink to="/" class="block">
          <h1 class="py-1 px-2 uppercase">Cinema</h1>
        </RouterLink>
          
        <!-- Hamburger Button -->
        <button @click="toggleMenu" class="block md:hidden">
          <!-- Hamburger Icon -->
          <svg v-if="!isMenuOpen" class="w-6 h-6" stroke="currentColor" viewBox="0 0 24 24"
            xmlns="http://www.w3.org/2000/svg">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16m-7 6h7"></path>
          </svg>
          <!-- Close Icon -->
          <svg v-if="isMenuOpen" class="w-6 h-6" stroke="currentColor" viewBox="0 0 24 24"
            xmlns="http://www.w3.org/2000/svg">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
          </svg>
        </button>

        <!-- Desktop Menu -->
        <div class="hidden md:flex justify-center items-center">
          <RouterLink class="mr-4 flex items-center " v-if="isAdmin" to="/admin"> <span class=" text-black text-6xl material-symbols-outlined">
              edit_calendar
            </span></RouterLink>
          <RouterLink v-if="isLoggedIn" to="/user">
          <div class="md:mx-4 lg:mx-6">
            <i class="fa-solid fa-user text-black hover:text-gray-700 text-4xl mr-4 mt-1"></i>
          </div>
          </RouterLink>
          <RouterLink v-if="isLoggedIn" to="/logout">
            <div class="flex items-center justify-center bg-secondary bg-opacity-70 px-4 rounded-full">
              <i class="fa-solid fa-right-to-bracket"></i>
              <h5 class="block px-3 py-3 hover:text-gray-300 uppercase">Logout</h5>
            </div>
          </RouterLink>

          <RouterLink v-if="!isLoggedIn" to="/login">
            <div class="flex items-center justify-center bg-secondary bg-opacity-70 px-4 rounded-full mx-2">
              <i class="fa-solid fa-right-to-bracket"></i>
              <h5 class="block px-4 py-3 hover:text-gray-300 uppercase">Login</h5>
            </div>
          </RouterLink>

          <RouterLink v-if="!isLoggedIn" to="/admin"></RouterLink>
        </div>
      </div>

      <!-- Mobile Menu -->
      <div v-if="isMenuOpen" class="md:hidden">
        <RouterLink v-if="isLoggedIn" to="/user">
          <div class="flex items-center justify-center bg-secondary px-5 rounded-full m-2">
            <i class="fa-solid fa-user"></i>
            <h5 class="block p-3 hover:text-gray-300 uppercase">My account</h5>
          </div>
        </RouterLink>
        <RouterLink v-if="isLoggedIn" to="/logout">
          <div class="flex items-center justify-center bg-secondary px-5 rounded-full mx-2">
            <i class="fa-solid fa-right-to-bracket"></i>
            <h5 class="block p-3 hover:text-gray-300 uppercase">Logout</h5>
          </div>
        </RouterLink>
        <RouterLink v-if="!isLoggedIn" to="/login">
          <div class="flex items-center justify-center bg-secondary rounded-full px-5 mx-2 mt-2">
            <i class="fa-solid fa-right-to-bracket"></i>
            <h5 class="block p-3 hover:text-gray-300 uppercase">Login</h5>
          </div>
        </RouterLink>
      </div>
    </nav>
  </header>
</template>

