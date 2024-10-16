<-- Behöver antal sittplatser för en viss visning -->
<-- Behöver veta vilka sittplatser för en viss visning som är bokade -->

<script setup>
import { ref } from "vue";

// Simulate fetching seat count and booked seats for a given cinema hall and schedule
const cinemaHall = {
  seatCount: 56, // This should be passed dynamically from the cinema hall
};

const bookedSeats = [1, 2, 10, 25]; // Example: these seats are already booked for a particular schedule

// Initialize seats based on seat count and mark booked seats
const seats = ref(Array.from({ length: cinemaHall.seatCount }, (_, i) => ({
  seat: `${i + 1}`,
  chosen: false,
  booked: bookedSeats.includes(i + 1) // Mark seat as booked if it's in the bookedSeats array
})));

// Toggle seat selection if not already booked
const toggleChosen = (index) => {
  if (!seats.value[index].booked) {
    seats.value[index].chosen = !seats.value[index].chosen;
  }
};
</script>


<template>
  <div>
    <div class="flex justify-center items-center bg-darkgrey max-w-md mx-auto rounded-3xl p-7 mt-3 mb-5">
      <div class="seat-grid grid grid-cols-7 gap-3">
        <button
          v-for="(seat, index) in seats"
          :key="index"
          :class="[
            'w-12 h-9 rounded-3xl transition-colors duration-300',
            seat.booked ? 'bg-secondary' : seat.chosen ? 'bg-third' : 'bg-white',
            seat.booked ? 'cursor-not-allowed' : 'hover:bg-opacity-80',
            'focus:outline-none'
          ]"
          @click="toggleChosen(index)"
          :disabled="seat.booked"
        >
        </button>
      </div>
    </div>
  </div>
  <div class="flex justify-center">
    <button class="text-center bg-secondary py-3 px-5 rounded-3xl uppercase mb-10">Book Seats</button>
  </div>
</template>

  

  <style scoped>
  </style>
  