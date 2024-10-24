<script setup>
import { ref, onMounted } from "vue";

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
  id: "67156dab33fe431712f1cbf3",
  localDateTime: "2024-10-15T10:18:00",
  cinemaHall: cinemaHall, 
  movie: props.movie  
};

const user = {
  googleId: "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOJ2ZXJzaW9uIjoxfQ.pOo7q5ZE-XrArgA-nTmbX2SqGLTWtP_qFJs6u8c7rgo",
  name: "Emmelie",
  email: "ej224sk@student.lnu.se"
};

const seats = ref(Array.from({ length: cinemaHall.seatCount }, (_, i) => ({
  seat: i + 1,
  booked: false,
  chosen: false,
})));

const isBooking = ref(false);

const fetchBookedSeats = async () => {
  try {
    const response = await fetch(`http://localhost:9000/schedules/${schedule.id}/booked-seats`);
    console.log('Schedule ID:', schedule.id);
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

  const booking = {
    userId: user.googleId,
    schedule: schedule,
    bookedSeats: chosenSeats
  };

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
/* Additional styling can go here */
</style>
