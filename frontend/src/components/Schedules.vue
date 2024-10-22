<script setup>
import { onMounted, ref } from "vue";

const props = defineProps({
  movie: {
    type: Object,
    required: true
  }
});

const schedules = ref([]);
const groupedSchedules = ref({});

const fetchSchedules = async () => {

  try {
    const response = await fetch(`http://localhost:9000/schedules/movie/${props.movie.id}`);

    if (!response.ok) {
      throw new Error(`Failed to fetch schedules for movie with id ${props.movie.id}: ${response.statusText}`);
    }

    schedules.value = await response.json();

    alert("Fetched schedules: " + JSON.stringify(schedules.value));
    groupedSchedules.value = schedules.value.reduce((grouped, schedule) => {
      const cinemaName = schedule.cinema.name;
      if (!grouped[cinemaName]) {
        grouped[cinemaName] = [];
      }
      grouped[cinemaName].push(schedule);
      return grouped;
    }, {});

    console.log("Grouped Schedules:", groupedSchedules.value);

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




onMounted(() => {
  if (props.movie && props.movie.id) {
    fetchSchedules();
  } else {
    console.error("Movie object is not defined or missing ID");
  }
});
</script>

//Iterate through the schedules array
//Sort in array by cinemaName
//For a cinema, show schedules
//For a schedule, show:
//- date in format day/month
//- time in format hour:minutes
//- available seats/ total number of seats


<template>
  <div>
    <div class="max-w-screen-md mx-auto">
      <div class="mb-10 flex-col justify-center">
        <div class="bg-white bg-opacity-50 p-6 mb-10">
          <!--
          <div v-for="(schedules, cinemaName) in grouped" :key="cinemaName">
            <p class="text-black text-2xl uppercase font-semibold pb-6 pl-4">{{ cinemaName }}</p> -->
            <div class="flex justify-center items-center gap-10">
              <!-- Iterate over each schedule in the grouped schedules for the cinema -->
              <div v-for="(schedule, index) in schedules" :key="index" class="bg-secondary rounded-3xl p-8 uppercase flex flex-col items-center gap-2">
                <p class="text-3xl font-semibold">{{ formatDate(schedule.localDateTime) }}</p>
                <p class="text-xl uppercase pb-3">{{ formatTime(schedule.localDateTime) }}</p>
                <p class="text-sm uppercase">seats:</p>
                <p class="text-3xl font-semibold">{{ schedule.availableSeats }} / {{ schedule.cinemaHall.nrOfSeats }}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  <!--
  </div>
  -->
</template>