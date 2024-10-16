<!-- <-- Behöver antal sittplatser för en viss visning -->
<!-- Behöver veta vilka sittplatser för en viss visning som är bokade -->

<script setup>

//Listan med sittplatser kan vara en prop som tar in objektet med platser, då man man se vilka som är tagna och vilka som är tillgängliga på den schemaläggningen!
const props = defineProps(['listOfSeats'])
//definiera värdena till parent för att uppdatera seats-listan i schedule objektet i backend
const emit = defineEmits(['seatUpdate']);
//Funktionen som skickar värdena till parent
function toggleBooking(indexOfSeat, isBooked){
  emit('seatUpdate',{indexOfSeat ,isBooked})
}

</script>

<template>
    <div>
        <div class="flex justify-center items-center bg-darkgrey max-w-md mx-auto rounded-3xl py-7 mt-3 mb-10">
        <div class="seat-grid grid grid-cols-7 gap-3">
      <button
        v-for="(isBooked,index) in props.listOfSeats"
        :key="index"
        :disabled="isBooked"
        :class="[
          'w-12 h-9 rounded-3xl transition-colors duration-300',
          isBooked ?  'bg-secondary' : 'bg-green-600',
          ' focus:outline-none'
        ]"
        @click="toggleBooking(index,true)"
      >
        
      </button>
    </div>
</div>
</div>
  </template>

 
  <style scoped></style>
  