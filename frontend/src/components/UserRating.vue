<template>
  <div class="rating-section">
    <p>Rate this movie:</p>
    <div class="stars">
      <span
          v-for="star in 5"
          :key="star"
          :class="{'star-filled': star <= selectedRating}"
          @click="submitRating(star)">
        ★
      </span>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { onMounted, watch } from 'vue';

const props = defineProps({
  movieId: {
    type: String,
    required: true
  }
});

const selectedRating = ref(0);

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

    selectedRating.value = rating; // Update selected rating after submission
    alert(`You rated this movie ${rating} stars!`);
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

.stars span {
  font-size: 24px;
  cursor: pointer;
  color: grey;
}

.star-filled {
  color: gold;
}
</style>
