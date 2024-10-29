<script setup>
import {onMounted, ref, computed} from "vue";
import Seats from "@/components/Seats.vue";

const props = defineProps({
  movie: {
    type: Object,
    required: true
  }
});

const schedules = ref([]);

const fetchSchedules = async () => {
  try {
    const response = await fetch(`http://localhost:9000/schedules/movie/${props.movie.id}`);
    if (!response.ok) {
      throw new Error(`Failed to fetch schedules for movie with id ${props.movie.id}: ${response.statusText}`);
    }
    schedules.value = await response.json();
    schedules.value.forEach(schedule => fetchBookings(schedule.id))
  } catch (error) {
    console.error(`Error fetching schedules for movie with id ${props.movie?.id || "unknown"}`, error);
  }
};

const formatDate = (dateString) => {
  const date = new Date(dateString);
  return date.toLocaleDateString('en-GB', {
    day: '2-digit',
    month: '2-digit',
  });
};

const formatTime = (dateString) => {
  const date = new Date(dateString);
  return date.toLocaleTimeString('en-GB', {
    hour: '2-digit',
    minute: '2-digit',
  });
};

const groupedSchedules = computed(() => {
  return schedules.value.reduce((grouped, schedule) => {
    const cinemaName = schedule.cinema.name;
    if (!grouped[cinemaName]) {
      grouped[cinemaName] = [];
    }
    grouped[cinemaName].push(schedule);
    return grouped;
  }, {});
});

onMounted(() => {
  if (props.movie && props.movie.id) {
    fetchSchedules();
  } else {
    console.error("Movie object is not defined or missing ID");
  }
});

const chosenSchedule = ref(null);
const bookedSeatsMap = ref(new Map());

function displaySeat(schedule) {
  chosenSchedule.value = schedule;
  fetchBookings(schedule.id); 
}

async function fetchBookings(scheduleId) {
  try{
    const res = await fetch('http://localhost:9000/bookings');
    const allBookings = await res.json();
  
    const bookings = allBookings.filter(booking => booking.schedule.id === scheduleId);
    const totalBookedSeats = bookings.reduce((total, booking) => {
      return total + booking.bookedSeats.length;
    }, 0);
  
    bookedSeatsMap.value.set(scheduleId, totalBookedSeats);
  }catch(e){
    console.error(e)
  }
}

const sortedArr = computed(() => {
  return Object.fromEntries(
    Object.entries(groupedSchedules.value).map(([key, schedules]) => [
      key,
      schedules.sort((a, b) => new Date(a.localDateTime) - new Date(b.localDateTime)),
    ])
  );
});

</script>

<template>
  <div>
    <div class="max-w-screen-lg mx-auto">
      <div class="mb-10">
        <div class="max-w-screen-lg mx-auto pb-5">
          <h4 class="md:text-center">Choose time and place to book seats</h4>
        </div>
        <div class="text-center bg-gray-700 bg-opacity-50 pl-10 pr-10 pb-3 pt-3 mb-4" v-if="schedules.length === 0" >
          <p>No schedules available</p>
        </div>
        <div class="flex flex-col md:flex-row justify-center gap-4">
          <div v-for="(cinemaSchedules, cinemaName) in sortedArr" :key="cinemaName" class="bg-gray-700 bg-opacity-60 px-5 py-3 mb-4">
            <div class="px-3">
              <h4 class="text-2xl md:text-3xl lg:text-4xl uppercase pb-3 md:pb-4 lg:pb-5 xl:pb-6">{{ cinemaName }}</h4>
              <div class="flex flex-wrap justify-between gap-5">
                <div @click="displaySeat(schedule)" v-for="(schedule, index) in cinemaSchedules" :key="index"
                     :class="['rounded-3xl','flex','p-4','uppercase','flex','flex-col','items-center','gap-1',(schedule.cinemaHall.nrOfSeats - bookedSeatsMap.get(schedule.id)) === 0 ? 'bg-secondary' : 'bg-black']">
                  <p class="text-2xl md:text-3xl lg:text-4xl font-semibold">{{ formatDate(schedule.localDateTime) }}</p>
                  <p class="text-lg md:text-xl lg:text-2xl uppercase ">{{ formatTime(schedule.localDateTime) }}</p>
                  <p class="text-xs md:text-sm lg:text-md uppercase">seats left:</p>
                  <p class="text-2xl md:text-3xl lg:text-4xl font-semibold">
                    {{ schedule.cinemaHall.nrOfSeats - (bookedSeatsMap.get(schedule.id)) === 0 ? 'FULL' :  `${schedule.cinemaHall.nrOfSeats - (bookedSeatsMap.get(schedule.id) || 0)}/${schedule.cinemaHall.nrOfSeats }` }}
                    </p>
                </div>
              </div>
            </div>
          </div>
        </div>
        <Seats v-if="chosenSchedule !== null" @update="fetchBookings(chosenSchedule.id)" :key="chosenSchedule.id" :schedule="chosenSchedule" />
      </div>
    </div>
  </div>
</template>
