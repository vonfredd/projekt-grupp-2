<script setup>
import UserBookings from "@/components/UserBookings.vue";
</script>

<template>
  <main
      class="bg-cover min-h-screen bg-fixed relative bg-left-top bg-[url('/img/userprofilebg.jpg')] flex justify-center">
    <div class="absolute inset-0 bg-black opacity-50"></div>
    <div class="bg-gray-700 bg-opacity-50 rounded-3xl w-full xl:w-2/3 mx-5 md:mx-10 lg:mx-20 py-10 my-20 relative z-10">
      <UserBookings/>
    </div>
  </main>
</template>


<style scoped>

</style>