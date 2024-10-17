<script setup>
import { ref, onMounted } from "vue";
import { defineProps } from 'vue';


const props = defineProps({
  movie: {
    type: Object,
    required: true
  }
});

const cinemaHall = {
  name: "Hall 1",
  seatCount: 56
};

const schedule = {
  id: 1,
  date: "2024-10-15",  // Assuming a string representation of the date
  time: "10:18:00",  // Time as a string
  cinemaHall: cinemaHall,  // Reference to the cinema hall
  movie: props.movie  // Reference to the movie
};


// Initialize seats array
const seats = ref(Array.from({ length: cinemaHall.seatCount }, (_, i) => ({
  seat: i + 1,
  booked: false,
  chosen: false,
})));

// Function to fetch booked seats for a specific schedule
const fetchBookedSeats = async () => {
  try {
    const response = await fetch(`/api/schedules/${scheduleId}/booked-seats`);
    if (!response.ok) {
      throw new Error("Failed to fetch booked seats");
    }
    const bookedSeats = await response.json();
    
    // Update the booked status of seats
    bookedSeats.forEach(seat => {
      seats.value[seat - 1].booked = true; // Assuming seat numbers are 1-based
    });
  } catch (error) {
    console.error("Error fetching booked seats:", error);
  }
};

// Function to toggle chosen seats (when clicked)
const toggleChosen = (index) => {
  if (!seats.value[index].booked) {
    seats.value[index].chosen = !seats.value[index].chosen;
  }
};

onMounted(fetchBookedSeats);
</script>

<template>
  <div>
    <h2>{{ props.movie.name }}</h2>
    <div class="flex justify-center items-center bg-darkgrey max-w-md mx-auto rounded-3xl p-7 mt-3 mb-5">
      <div class="seat-grid grid grid-cols-7 gap-3">
        <button
          v-for="(seat, index) in seats"
          :key="index"
          :class="[
            'w-12 h-9 rounded-3xl transition-colors duration-300',
            seat.booked ? 'bg-secondary' : seat.chosen ? 'bg-third' : 'bg-white',
            'hover:bg-opacity-80 focus:outline-none'
          ]"
          :disabled="seat.booked"
          @click="toggleChosen(index)"
        >
          {{ seat.seat }}
        </button>
      </div>
    </div>

    <div class="flex justify-center">
      <button class="text-center bg-secondary py-3 px-5 rounded-3xl uppercase mb-10">
        Book Seats
      </button>
    </div>
  </div>
</template>

<style scoped>
/* Add any additional styling here */
</style>
