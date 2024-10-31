<template>
  <div v-if="bookings.length" class="booking-list">
    <div v-for="booking in bookings" :key="booking.id">

      <div>
        <h4>Cinema: {{ booking.schedule.cinema.name }} - {{ booking.schedule.cinemaHall.name }}</h4>

      </div>

      <div>
        <!--        <img :src="`https://image.tmdb.org/t/p/w200${booking.schedule.movie.imageUrl}`"-->
        <!--             :alt="booking.schedule.movie.name"/>-->
        <h5>{{ booking.schedule.movie.name }}</h5>
        <!--        <p>{{ booking.schedule.movie.description }}</p>-->
        <!--        <p><strong>Genres:</strong> {{ booking.schedule.movie.genres.join(', ') }}</p>-->
        <p><strong>Duration:</strong> {{ booking.schedule.movie.duration }} minutes</p>
        <!--        <p><strong>Release Date:</strong> {{ new Date(booking.schedule.movie.releaseDate).toLocaleDateString() }}</p>-->
        <p><strong>Average Rating:</strong> {{ booking.schedule.movie.averageRatingInPercentage }}%</p>
        <!--        <p><strong>User Ratings:</strong>-->
        <!--          <span v-for="(rating, index) in booking.schedule.movie.rating" :key="index">-->
        <!--            {{ rating.movieRating }}{{ index < booking.schedule.movie.rating.length - 1 ? ', ' : '' }}-->
        <!--          </span>-->
        <!--        </p>-->
      </div>

      <div>
        <p><strong>Showtime:</strong> {{ new Date(booking.schedule.localDateTime).toLocaleString() }}</p>
        <p><strong>Booked Seats:</strong> {{ booking.bookedSeats.join(', ') }}</p>
      </div>
      <user-rating :movieId="booking.schedule.movie.id"></user-rating>
    </div>


  </div>

  <p v-else>Loading bookings...</p>
</template>

<script setup>
import {onMounted, ref} from 'vue';
import UserRating from "@/components/UserRating.vue";

const bookings = ref([]);
const newRating = ref({});

const fetchBookings = async () => {
  const userData = localStorage.getItem('userData');
  if (!userData) return;

  const user = JSON.parse(userData);
  try {
    const response = await fetch(`http://localhost:9000/bookings/user/${user.googleId}`);
    if (!response.ok) throw new Error('Failed to fetch bookings');
    bookings.value = await response.json();
  } catch (error) {
    console.error('Error fetching bookings:', error);
  }
};

const submitRating = async () => {

}
onMounted(() => {
  fetchBookings();
});
</script>

<style scoped>
.booking-list {
  background-color: black;
}
</style>
