<script setup>
import "@vuepic/vue-datepicker/dist/main.css";
import Accordion from "@/components/Accordion.vue";
import ScheduleMovie from "@/components/ScheduleMovie.vue";
</script>

<template>
  <div id="admin-view">
    <main class="px-[10%] py-[5%] flex justify-between max-w-[2000px] mx-auto">
      <div class="bg-gray-100 w-3/5 text-black p-10">
        <ScheduleMovie/>
      </div>

      <div class="w-1/3 text-black">
        <Accordion/>
      </div>
    </main>

  </div>
</template>

<style scoped></style>
