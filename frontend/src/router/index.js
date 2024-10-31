import {createRouter, createWebHistory} from "vue-router";
import LandingView from "@/views/LandingView.vue";
import MovieProfileView from "@/views/MovieProfileView.vue";
import AdminView from "@/views/AdminView.vue";

const isAdminLoggedIn = () => {
    const userData = JSON.parse(localStorage.getItem('userData'));
    return userData && userData.role === 'admin';
};

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: "/",
            name: "home",
            component: LandingView
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
            props: route => ({
                id: route.params.id,
                title: route.params.title
            })
        },
        {
            path: "/admin",
            name: "admin",
            component: AdminView,
            beforeEnter: (to, from, next) => {
                if (isAdminLoggedIn()) {
                    next();
                } else {
                    alert('Only admin is allowed to enter /admin')
                    next({ name: 'home' }); 
                }
            }
        },
        {
            path: '/login',
            name: 'Login',
            beforeEnter() {
                window.location.href = 'http://localhost:9000/login';
            }
        },
        {
            path:'/logout',
            name: 'Logout',
            beforeEnter() {
                localStorage.removeItem('userData');
                window.location.href = 'http://localhost:9000/logout';
            },
        },
        {
            path: '/redirect',
            name: 'RedirectHandler',
            beforeEnter(to, from, next) {
                if (to.query.redirected) {
                    fetch('http://localhost:9000/users/data', {
                        method: 'GET', 
                        credentials: 'include' 
                    }) 
                        .then(response => {
                            if (!response.ok) {
                                throw new Error('Network response was not ok');
                            }
                            return response.json();
                        })
                        .then(userData => {
                            localStorage.setItem('userData', JSON.stringify(userData));
                            window.dispatchEvent(new Event("storage"));
                            next({ name: 'home' });
                        })
                        .catch(error => {
                            console.error('Failed to fetch user data:', error);
                            next({ name: 'home' }); 
                        });
                }else if(to.query.admin){
                    const adminData = { role: 'admin' }; 
                    localStorage.setItem('userData', JSON.stringify(adminData));
                    window.dispatchEvent(new Event("admin"));
                    next({name: 'admin'})
                } else {
                    next();
                }
            }
        }
    ],
});

export default router;
