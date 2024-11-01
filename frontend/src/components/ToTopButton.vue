<script setup>
import { onMounted, onUnmounted, ref } from "vue";

const scrollTopButton = ref(null);

const scrollToTop = () => {
  window.scrollTo({ top: 0, behavior: "smooth" });
};

const handleScroll = () => {
  if (window.scrollY > 0) {
    scrollTopButton.value.classList.remove("invisible");
  } else {
    scrollTopButton.value.classList.add("invisible");
  }
};

onMounted(() => {
  window.addEventListener("scroll", handleScroll);
});

onUnmounted(() => {
  window.removeEventListener("scroll", handleScroll);
});
</script>

<template>
  <div
      ref="scrollTopButton"
      class="invisible sticky w-full flex justify-end bottom-0 pb-3 pr-5 lg:pr-10 transition"
  >
    <div class="hover:text-gray-400 transition">
      <button @click="scrollToTop" role="button"
              aria-label="scroll to top of the page"
              class="bg-black px-6 py-4 rounded">
        <i class="fa-solid fa-up-long"></i>
      </button>
    </div>
  </div>
</template>

<style scoped>
</style>
