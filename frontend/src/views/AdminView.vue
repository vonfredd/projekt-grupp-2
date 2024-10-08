<script setup>
import { ref } from 'vue';
</script>

<template>
    <div id="admin-view">
        <header class="bg-[#CC0028] text-white px-20 p-5 flex justify-between items-center">
            <h1>Cinema</h1>
         
            <button>
            <RouterLink to="/login">Login</RouterLink>
            </button>
          

          <RouterView />
        </header>
      
        <main>
          This is my main content
        </main>
    </div>
</template>