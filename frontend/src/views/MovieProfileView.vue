<script setup>
import { ref, computed } from "vue";
import Seats from "@/components/Seats.vue";
const Movie = ref({
    name: "Inception",
    description:
        "A mind-bending thriller where dream invasion is possible. Dom Cobb is a skilled thief, the absolute best in the dangerous art of extraction: stealing valuable secrets from deep within the subconscious during the dream state, when the mind is at its most vulnerable.",
    genre: [
        { id: 28, name: "Action" },
        { id: 878, name: "Science Fiction" },
        { id: 12, name: "Adventure" },
    ],
    duration: 148,
    releaseDate: "2010-07-16",
    imageUrl: "https://image.tmdb.org/t/p/w500/8ZTVqvKDQ8emSGUEMjsS4yHAwrp.jpg",
    rating: 75,
});

const formattedDuration = computed(() => {
    const hours = Math.floor(Movie.value.duration / 60);
    const minutes = Movie.value.duration % 60;
    return `${hours}h ${minutes}m`;
});

//Detta kan också vara en lista hämtad från backend t.ex. Vi behöver bara listan av tillgängliga platser från schedule objektet. 
//Uppdatering av denna lista bör uppdateras i backend också.
const listOfSeats = ref(null);


//Hämtar data från seatcomponent vilket säte(index i listan) som är klickat på.
const receivedUpdate = (emitted)=>{
//Här är det bra att göra en post/update mot schedule objektet i backend så att platserna uppdateras, är bra om vi på något sätt får användarens id genom localstorage eller så.


    listOfSeats.value[emitted.indexOfSeat] = emitted.isBooked;
alert(`Användaren har nu bokat stol nr: ${emitted.indexOfSeat}`)

}

//Vilka stolar är tillgängliga visas här
function onlyAvailable(seats){
    return seats.filter(isBooked => !isBooked).length;
}

//Detta är funktionen som sätter vilken lista av stolar som skall visas och som skickas in i Seat component för att visa dom
function showSeats(seatsOfTheHall){
    listOfSeats.value = seatsOfTheHall;
}

//Arrayen är en samling av schedule objekt så som dom kommer från backend
const arrayOfSchedules = ref([
    {
        "schedule": {
            "cinema": {
                "name": "Trappan",
                "movies": [
                    {
                        "name": "Sample Movie"
                    }
                ],
                "halls": [
                    {
                        "name": "Main Hall",
                        "seats": [true, true, true, false, true, false, false, true, false, true, false, true, false, false, true, false, true, true, false, true, true, false, true, false, true, false, false, true, false, true]
                    }
                ]
            },
            "datetime": "2024-08-09 15:50"
        }
    },
    {
        "schedule": {
            "cinema": {
                "name": "Bio Palatset",
                "movies": [
                    {
                        "name": "Sample Movie"
                    }
                ],
                "halls": [
                    {
                        "name": "Main Hall",
                        "seats": [true, false, true, false, true, false, false,false,true,true, false, false,true, false, false, false, false, true, false, true, false, true, false, false, true, false, true, true, false, true, true, false, true, false, true, false, false, true, false, true]
                    }
                ]
            },
            "datetime": "2024-08-09 18:30"
        }
    },
    {
        "schedule": { 
            "cinema": {
                "name": "Bergakungen",
                "movies": [
                    {
                        "name": "Sample Movie"
                    }
                ],
                "halls": [
                    {
                        "name": "Main Hall",
                        "seats": [true, false, true, false, true, false, false, true, false, true, false, true, false, false, true, false, true, true, false, true, true, false, true, false, true, false, false, true, false, true]
                    }
                ]
            },
            "datetime": "2024-08-09 21:00"
        }
    }
]);


</script>

<template>
  <main
    :style="{ backgroundImage: `url(${Movie.imageUrl})` }"
    class="bg-cover bg-center min-h-screen relative z-0"
  >
    <div class="absolute inset-0 bg-black opacity-10 z-1"></div>
        <div class="p-4 relative z-2">
            <div class="flex justify-center">
                <div class="flex flex-col mt-10">
                    <div class="flex">
                            <h1 class="mr-2">{{ Movie.name }}</h1>
                            <div class="relative w-12 h-12 mt-5 flex items-center justify-center">
                                <p class="text-lg z-10">{{ Movie.rating }}%</p>
                                <div class="absolute inset-0 bg-white opacity-30 rounded-full"></div>
                            </div>
                        </div>
                    <div class="text-xs space-x-1 uppercase mb-10">
                         <span
                            v-for="(genre, index) in Movie.genre"
                             :key="genre.id"
                                class="inline"
                        >
                        {{ genre.name }}<span v-if="index < Movie.genre.length - 1">,</span>
                        </span>
                        <span class="mx-2">|</span>
                        <span>{{ formattedDuration }}</span>
                    </div>
                </div>
            </div>
            <div class="w-[80%] mx-auto mb-10">
                <h2 class="text-3xl">Overview</h2>
                <p class="mt-2">{{ Movie.description }}</p>
             </div>
<div class="flex">
             <!-- Här kan man loopa över olika schedules för denna filmen för att få reda på när och var den går och hur många platser som finns kvar-->
             <div @click="showSeats(item.schedule.cinema.halls[0].seats)" v-for="(item,index) in arrayOfSchedules" :key="index" class="rounded-lg m-auto text-center bg-black">
                <p>{{ item.schedule.cinema.name }}</p>
            <!-- För att dela på datum och tid splittar man vid mellanrummet mellan datumdelen och tiden-->
                <p class="text-xl p-3"> {{ item.schedule.datetime.split(" ")[0] }}</p>
                <p>KL: {{ item.schedule.datetime.split(" ")[1] }} </p>
                <p>Seats</p>
                <p class="text-2xl">{{ onlyAvailable(item.schedule.cinema.halls[0].seats) }} / {{ item.schedule.cinema.halls[0].seats.length }}</p>
            </div>
        </div>
        <!--Denna komponenten tar endast in listan av stolar från det scheduleobjektet (på hemsidan: svart ruta med text,datum och tid ) och låter oss välja stol i den listan-->
        <!--Värdet returneras sedan hit och manipulerar listan av stolar i objektet-->
            <Seats v-if="listOfSeats !== null" :listOfSeats="listOfSeats" @seatUpdate="receivedUpdate"/>
        </div>
  </main>
</template>
