<template>
  <div class="progress-circle" :style="{ width: size + 'px', height: size + 'px' }">
    <svg class="circle" :width="size" :height="size">
      <circle :cx="size / 2" :cy="size / 2" :r="radius" class="bg-circle" />
      <circle
          :cx="size / 2"
          :cy="size / 2"
          :r="radius"
          class="progress-circle"
          :stroke-dasharray="circumference"
          :stroke-dashoffset="circumference - (progress / 100) * circumference"
      />
    </svg>
    <div class="progress-text">{{ progress }}%</div>
  </div>
</template>

<script setup>
import { computed, defineProps } from 'vue';

const props = defineProps({
  progress: {
    type: Number,
    required: true
  },
  size: {
    type: Number,
    default: 80 // Default size for the SVG container
  }
});

const radius = computed(() => (props.size / 2) - 8);
const circumference = computed(() => 2 * Math.PI * radius.value);
</script>

<style scoped>
.progress-circle {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  position: relative;
}

.circle {
  transform: rotate(-90deg); /* Start the progress at the top */
}

.bg-circle {
  fill: none;
  stroke: #e6e6e6;
  stroke-width: 8;
}

.progress-circle {
  fill: none;
  stroke: #4caf50;
  stroke-width: 8;
  stroke-linecap: round;
  transition: stroke-dashoffset 0.3s;
}

.progress-text {
  position: absolute;
  font-size: 1em;
  font-weight: bold;
}
</style>
