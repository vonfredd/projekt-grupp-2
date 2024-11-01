<script setup>
import "@vuepic/vue-datepicker/dist/main.css";
import VueDatePicker from "@vuepic/vue-datepicker";
import { onMounted, ref, computed } from 'vue';
import Seats from "./Seats.vue";



async function getDates() {
    try {
        const response = await fetch('http://localhost:9000/schedules');
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        showtimes.value = await response.json();
    } catch (e) {
        console.error(e)
    }
    showDates();

}
const showtimes = ref([]);
const allowedDates = ref([])
const showDates = () => {
    allowedDates.value = showtimes.value.map(schedule => schedule.localDateTime.split('T')[0]);
}

const selectedDate = ref('');

onMounted(() => { getDates() })

const formattedDate = computed(() => {
    if (!selectedDate.value) return '';
    const date = new Date(selectedDate.value);
    return date.toISOString().split('T')[0];
});

const schedulesByDate = computed(() => {
    return showtimes.value.filter(schedule => schedule.localDateTime.split('T')[0] == formattedDate.value);
})
</script>

<template>
    <VueDatePicker id="picker" v-model="selectedDate" :allowedDates="allowedDates" inline auto-apply />
    <div class="flex justify-center flex-wrap p-2 gap-2">
        <div class="bg-slate-200 rounded" v-for="schedule in schedulesByDate">
            <router-link :to="{ name: 'movieProfile', params: { id: schedule.movie.id, title: schedule.movie.name.replace(/\s+/g, '-') } }">
                <div class="p-2">
                    <p class="text-black"> {{ schedule.cinema.name }} </p>
                    <p class="text-black"> {{ schedule.movie.name }} </p>
                    <p class="text-black"> {{ schedule.localDateTime.split('T')[1].slice(0, 5) }} </p>
                </div>
            </router-link>
        </div>
    </div>
</template>

<style scoped>
#picker{
    justify-content: center;
}
</style>