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
   <div class="fixed bottom-0 right-0 mr-10 mb-24 mb-4">
      <button @click="scrollToTop" role="button"
              aria-label="scroll to top of the page"
              ref="scrollTopButton"
              class="invisible sticky bottom-0 hover:text-gray-400 transition bg-black px-6 py-4 rounded">
        <i class="fa-solid fa-up-long"></i>
      </button>
    </div>
</template>

<style scoped>
</style>
