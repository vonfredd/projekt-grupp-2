<script setup>
import UserBookings from "@/components/UserBookings.vue";
import ToTopButton from "@/components/ToTopButton.vue";
</script>

<template>
  <main
      class="bg-cover min-h-screen bg-fixed relative bg-left-top bg-[url('/img/userprofilebg.jpg')] flex justify-center">
    <div class="absolute inset-0 bg-black opacity-50"></div>
    <div class="  w-full xl:w-2/3 mx-5 md:mx-10 lg:mx-20 py-10 my-5 md:my-10 relative z-10">
      <UserBookings/>
    </div>
    <ToTopButton class="z-20"/>
  </main>

</template>


<style scoped>

</style>