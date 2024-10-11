import { createRouter, createWebHistory } from "vue-router";
import LandingView from "@/views/LandingView.vue";
import MovieProfileView from "@/views/MovieProfileView.vue";
import AdminView from "@/views/AdminView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: LandingView,
    },
    {
      path: "/about",
      name: "about",
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
    },
    {
      path: "/movie",
      name: "movieProfile",
      component: MovieProfileView,
    },
    {
      path: "/admin",
      name: "admin",
      component: AdminView,
    },
  ],
});

export default router;
