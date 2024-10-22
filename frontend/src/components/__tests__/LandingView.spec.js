import { mount } from '@vue/test-utils'
import { describe, test, expect, beforeEach } from "vitest";
import { ref } from 'vue';
import LandingView from "@/views/LandingView.vue"
import { RouterLinkStub } from '@vue/test-utils'

describe('Make sure props work and can be processed', () => {
    let wrapper;

    const movieArr = ref([
        {
            "name": "The Matrix",
            "description": "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.",
            "genres": [
                "Action",
                "Science Fiction"
            ],
            "duration": 136,
            "releaseDate": "1999-03-31",
            "imageUrl": "/f89U3ADr1oiB1s9GkdPOEpXUk5H.jpg",
            "rating": 87
        },
        {
            "name": "Interstellar",
            "description": "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.",
            "genres": [
                "Adventure",
                "Drama",
                "Science Fiction"
            ],
            "duration": 169,
            "releaseDate": "2014-11-07",
            "imageUrl": "/rAiYTfKGqDCRIIqo664sY9XZIvQ.jpg",
            "rating": 81
        },
        {
            "name": "The Dark Knight",
            "description": "When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham.",
            "genres": [
                "Action",
                "Crime",
                "Drama"
            ],
            "duration": 152,
            "releaseDate": "2008-07-18",
            "imageUrl": "/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
            "rating": 85
        },
        {
            "name": "The Dark Knight",
            "description": "When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham.",
            "genres": [
                "Action",
                "Crime",
                "Drama"
            ],
            "duration": 152,
            "releaseDate": "2008-07-18",
            "imageUrl": "/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
            "rating": 75
        },
        {
            "name": "The Dark Knight",
            "description": "When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham.",
            "genres": [
                "Action",
                "Crime",
                "Drama"
            ],
            "duration": 152,
            "releaseDate": "2008-07-18",
            "imageUrl": "/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
            "rating": 82
        },
        {
            "name": "The Dark Knight",
            "description": "When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham.",
            "genres": [
                "Action",
                "Crime",
                "Drama"
            ],
            "duration": 152,
            "releaseDate": "2008-07-18",
            "imageUrl": "/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
            "rating": 65
        }
    ]);

    /*
      Load prop data before tests
    */
    beforeEach(() => {
        wrapper = mount(LandingView, {
            propsData: {
                listOfMovies: movieArr.value
            },
            global: {
                stubs: {
                    RouterLink: RouterLinkStub
                }
            }
        })
    })

    test('Load props', () => {
        expect(LandingView).toBeTruthy();
        expect(wrapper.vm.listOfMovies).toBeDefined();
    })

    test('Find first movie from proplist in DOM', () => {
        expect(wrapper.find('h2').text()).toBe('Movies');

        const firstMovieDescription = wrapper.findAll('p').at(1);
        const firstMovieTitle = wrapper.findAll('h3').at(0);
        const firstMovieImgUrl = wrapper.findAll('img').at(0).element.src;

        expect(firstMovieTitle.text()).toBe('The Matrix')
        expect(firstMovieDescription.text()).toContain('A computer hacker learns from mysterious rebels')
        expect(firstMovieImgUrl).toBe('https://image.tmdb.org/t/p/w500/f89U3ADr1oiB1s9GkdPOEpXUk5H.jpg')

    })

    test('arrayOfHighestRated is only 5 in length when keeping top rated movies ',()=>{
        expect(wrapper.vm.arrayOfHighestRated.length).toBe(5)
        expect(wrapper.vm.listOfMovies.length).toBe(6)
    })

})