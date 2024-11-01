<script setup>
import { ref, onMounted } from "vue";


const props = defineProps({
  schedule: {
    type: Object,
    required: true 
  }
});

const emit = defineEmits(['update'])

const user = localStorage.getItem('userData') ? JSON.parse(localStorage.getItem('userData')) : null;

const seats = ref(Array.from({ length: props.schedule.cinemaHall.nrOfSeats }, (_, i) => ({
  seat: i + 1,
  booked: false,
  chosen: false,
})));

const isBooking = ref(false);

const fetchBookedSeats = async () => {
  try {
    const response = await fetch(`http://localhost:9000/schedules/${props.schedule.id}/booked-seats`);
    if (!response.ok) {
      throw new Error("Failed to fetch booked seats");
    }
    const bookedSeats = await response.json();
    bookedSeats.forEach(seat => {
      seats.value[seat - 1].booked = true;
    });
  } catch (error) {
    console.error("Error fetching booked seats:", error);
  }
};

const toggleChosen = (index) => {
  if (!seats.value[index].booked) {
    seats.value[index].chosen = !seats.value[index].chosen;
  }
};

const createBooking = async () => {
  const chosenSeats = seats.value
    .filter(seat => seat.chosen)
    .map(seat => seat.seat); 

  if (chosenSeats.length === 0) {
    alert("No seats chosen!");
    return; 
  }

  let booking;
  try{
  booking = {
    userId: user.googleId,
    schedule: props.schedule,
    bookedSeats: chosenSeats
  };
}catch(e){
  alert('You need to sign in with google to book a seat!');
  return;
}

  isBooking.value = true; 

  try {
    const response = await fetch('http://localhost:9000/bookings', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(booking),
    });

    if (!response.ok) {
      const errorResponse = await response.json();
      throw new Error(`Failed to create booking: ${errorResponse.message}`);
    }

    alert("Booking successful!");
    emit('update')

    seats.value.forEach(seat => {
      seat.chosen = false;
    });

    await fetchBookedSeats();
  } catch (error) {
    alert("Booking failed. Please try again.");
  } finally {
    isBooking.value = false; 
  }
};

onMounted(fetchBookedSeats);
</script>

<template>
    <div>
        <div class="flex justify-center items-center bg-darkgrey max-w-sm sm:max-w-lg mx-auto rounded-3xl p-4 mt-3 mb-6">
        <div class="seat-grid grid grid-cols-7 gap-3">
            <button
            v-for="(seat, index) in seats"
            :key="index"
            :class="[ 
                'w-10 h-8 sm:w-14 sm:h-10 rounded-3xl transition-colors duration-300',
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
      <button 
        class="text-center text-lg bg-secondary py-4 px-7 rounded-full uppercase mb-10"
        @click="createBooking"  
        :disabled="isBooking" 
      >
        Book Seats
      </button>
    </div>
  </div>
</template>

<style scoped>

</style>
