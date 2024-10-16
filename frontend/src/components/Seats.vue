<-- Behöver antal sittplatser för en viss visning -->
<-- Behöver veta vilka sittplatser för en viss visning som är bokade -->


<template>
    <div>
        <div class="flex justify-center items-center bg-darkgrey max-w-md mx-auto rounded-3xl py-7 mt-3 mb-10">
        <div class="seat-grid grid grid-cols-7 gap-3">
      <button
        v-for="(seat, index) in seats"
        :key="index"
        :class="[
          'w-12 h-9 rounded-3xl transition-colors duration-300',
          seat.booked ? 'bg-secondary' : 'bg-[#F34444]',
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
        seats: Array.from({ length: 56 }, (_, i) => ({
          label: `${i + 1}`,
          booked: false,
        })),
      };
    },
    methods: {
      toggleBooking(index) {
        this.seats[index].booked = !this.seats[index].booked;
      },
    },
  };
  </script>
  <style scoped></style>
  