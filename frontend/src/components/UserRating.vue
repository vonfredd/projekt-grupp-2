<template>
  <div class="rating-section flex flex-col items-center">
    <p>Rate this movie:</p>
    <div class="stars">
      <span
          v-for="star in 5"
          :key="star"
          :class="['star', { 'star-filled': star <= selectedRating }]"
          @click="submitRating(star)">
        ★
      </span>
    </div>
  </div>
</template>

<script setup>
import {onMounted, ref, watch} from 'vue';

const props = defineProps({
  movieId: {
    type: String,
    required: true
  }
});

const emit = defineEmits(['ratingSubmitted']);
const selectedRating = ref(0);

const fetchMovieRating = async () => {
  try {
    const response = await fetch(`http://localhost:9000/movies/${props.movieId}`);
    if (!response.ok) throw new Error('Failed to fetch movie');

    const movieData = await response.json();

    const ratings = Array.isArray(movieData.ratings) ? movieData.ratings : [];

    const userData = localStorage.getItem('userData');
    if (userData) {
      const user = JSON.parse(userData);
      const userRating = ratings.find((rating) => rating.userId === user.googleId);
      selectedRating.value = userRating ? userRating.movieRating : 0;
    }
  } catch (error) {
    console.error('Error fetching movie data:', error);
  }
};

// Fetch the movie data when the component is mounted or when movieId changes
onMounted(fetchMovieRating);

watch(() => props.movieId, fetchMovieRating);
const submitRating = async (rating) => {
  const userData = localStorage.getItem('userData');
  if (!userData) {
    console.error('User data not found in localStorage.');
    return;
  }

  const user = JSON.parse(userData);
  const ratingData = {
    userId: user.googleId,
    movieRating: rating
  };

  try {
    const response = await fetch(`http://localhost:9000/movies/${props.movieId}/rating`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(ratingData)
    });
    if (!response.ok) throw new Error('Failed to submit rating');

    const responseData = await response.json();
    selectedRating.value = rating;

    emit('ratingSubmitted',  props.movieId, responseData.averageRatingInPercentage);
  } catch (error) {
    console.error('Error submitting rating:', error);
    alert('Failed to submit rating. Please try again later.');
  }
};

</script>

<style scoped>
.stars {
  display: inline-flex;
}

.star {
  font-size: 24px;
  cursor: pointer;
  color: grey;
}

.star-filled {
  color: gold;
}
</style>
