import { createRouter, createWebHistory } from 'vue-router'
import LandingView from '@/views/LandingView.vue'
import AdminView from '@/views/AdminView.vue'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: LandingView
        },
        {
            path: '/admin', // Give AdminView its own path
            name: 'admin',
            component: AdminView
        }
    ]
})

export default router
