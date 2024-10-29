<script setup>
import "@vuepic/vue-datepicker/dist/main.css";
import Accordion from "@/components/Accordion.vue";
import ScheduleMovie from "@/components/ScheduleMovie.vue";
</script>

<template>
  <div id="admin-view">
    <main class="px-10 py-20 flex gap-3 max-w-7xl mx-auto">
      <div class="bg-gray-100 w-2/3 text-black px-5 py-10">
        <ScheduleMovie/>
      </div>

      <div class="w-1/3 text-black">
        <Accordion/>
      </div>
    </main>

  </div>
</template>

<style scoped></style>
