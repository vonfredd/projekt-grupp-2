<template>
    <div>
        <div class="flex justify-center items-center bg-darkgrey max-w-md mx-auto rounded-2xl py-4">
        <div class="seat-grid grid grid-cols-6 gap-4 max-w-[90%] mx-auto">
      <button
        v-for="(seat, index) in seats"
        :key="index"
        :class="[
          'w-13 h-9 rounded-2xl transition-colors duration-300',
          seat.booked ? 'bg-secondary' : 'bg-primary',
          'hover:bg-opacity-80 focus:outline-none'
        ]"
        @click="toggleBooking(index)"
      >
        {{ seat.label }}
      </button>
    </div>
</div>
</div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        // Define a 7x6 grid of seats with a booking status
        seats: Array.from({ length: 42 }, (_, i) => ({
          label: `Seat ${i + 1}`,
          booked: false,
        })),
      };
    },
    methods: {
      // Toggles the booking status of a seat
      toggleBooking(index) {
        this.seats[index].booked = !this.seats[index].booked;
      },
    },
  };
  </script>
  
  <style scoped></style>
  