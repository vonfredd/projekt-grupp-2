import {createRouter, createWebHistory} from "vue-router";
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
            props: route => ({ listOfMovies: route.params.listOfMovies })
        },
        {
            path: "/about",
            name: "about",
            // route level code-splitting
            // this generates a separate chunk (About.[hash].js) for this route
            // which is lazy-loaded when the route is visited.
        },
        {
            path: "/movie/:id-:title",
            name: "movieProfile",
            component: MovieProfileView,
            props: route => ({ movie: JSON.parse(route.params.movie) })
        //     props: route => ({ movie: route.meta.movie}),
        //     meta: {
        //         movie: {
        //             id: 27205,
        //             name: "Inception",
        //             description:
        //                 "A mind-bending thriller where dream invasion is possible. Dom Cobb is a skilled thief, the absolute best in the dangerous art of extraction: stealing valuable secrets from deep within the subconscious during the dream state, when the mind is at its most vulnerable.",
        //             genre: [
        //                 {id: 28, name: "Action"},
        //                 {id: 878, name: "Science Fiction"},
        //                 {id: 12, name: "Adventure"},
        //             ],
        //             duration: 148,
        //             releaseDate: "2010-07-16",
        //             imageUrl: "/8ZTVqvKDQ8emSGUEMjsS4yHAwrp.jpg",
        //             rating: []
        //         }
        //     }
        // },
        },
        {
            path: "/admin",
            name: "admin",
            component: AdminView,
        },
        {
            path: '/login',
            name: 'Login',
            beforeEnter() {
                window.location.href = 'http://localhost:9000/login';
            }
        }
    ],
});

export default router;
