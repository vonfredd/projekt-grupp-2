<script setup>
import { onMounted, ref } from "vue";

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

    alert("Fetched schedules: " + JSON.stringify(schedules.value));

  } catch (error) {
    console.error(`Error fetching schedules for movie with id ${props.movie?.id || "unknown"}`, error);
  }
};

onMounted(() => {
  if (props.movie && props.movie.id) {
    fetchSchedules();
  } else {
    console.error("Movie object is not defined or missing ID");
  }
});
</script>


<template>
    <div>
        <div class="max-w-screen-md mx-auto">
            <div class="mb-10 flex-col justify-center">
                <div class="bg-white bg-opacity-50 p-6 mb-10">
                    <p class="text-black text-2xl uppercase font-semibold pb-6 pl-4">Cinema 1</p>
                    <div class="flex justify-center items-center gap-10">
                        <div class="bg-black rounded-3xl p-8 uppercase flex flex-col items-center gap-2">
                            <p class="text-3xl font-semibold">19/4</p>
                            <p class="text-xl uppercase pb-3">kl 15:00</p>
                            <p class="text-sm uppercase">seats:</p>
                            <p class="text-3xl font-semibold">63/63</p>
                        </div>
                        <div class="bg-secondary rounded-3xl p-8 uppercase flex flex-col items-center gap-2">
                            <p class="text-3xl font-semibold">20/4</p>
                            <p class="text-xl uppercase pb-3">kl 15:00</p>
                            <p class="text-sm uppercase">seats:</p>
                            <p class="text-3xl font-semibold">60/63</p>
                        </div>
                    </div>
                </div>
                <div class="bg-white bg-opacity-50 p-6">
                    <p class="text-black text-2xl uppercase font-semibold pb-6 pl-4">Cinema 2</p>
                    <div class="flex justify-center items-center gap-7">
                        <div class=" bg-black rounded-3xl p-8 uppercase flex flex-col items-center gap-2">
                            <p class="text-3xl font-semibold">19/4</p>
                            <p class="text-xl uppercase pb-3">kl 15:00</p>
                            <p class="text-xs uppercase">seats:</p>
                            <p class="text-3xl font-semibold">56/56</p>

                        </div>
                        <div class="bg-secondary rounded-3xl p-8 uppercase flex flex-col items-center gap-2">
                            <p class="text-3xl font-semibold">20/4</p>
                            <p class="text-xl uppercase pb-3">kl 15:00</p>
                            <p class="text-sm uppercase">seats:</p>
                            <p class="text-3xl font-semibold">42/56</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>