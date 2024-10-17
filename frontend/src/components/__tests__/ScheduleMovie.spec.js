import { describe, test, expect, beforeEach, vi } from "vitest";
import { mount } from '@vue/test-utils';
import { ref } from 'vue';
import ScheduleMovie from '../ScheduleMovie.vue';

describe('ScheduleMovie Component', () => {
    let wrapper;

    const movieArr = ref([
        {
            id: "238",
            name: "The Godfather",
            description: "Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family. When organized crime family patriarch, Vito Corleone barely survives an attempt on his life, his youngest son, Michael steps in to take care of the would-be killers, launching a campaign of bloody revenge.",
            genre: ["Drama", "Crime"],
            duration: "175",
            releaseDate: "1972-03-14",
            imageUrl: "/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",
            rating: []
        },
        {
            id: "239",
            name: "The Dark Knight",
            description: "When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham. The Dark Knight must accept one of the greatest psychological and physical tests of his ability to fight injustice.",
            genre: ["Action", "Crime", "Drama"],
            duration: "152",
            releaseDate: "2008-07-18",
            imageUrl: "/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
            rating: []
        },
        {
            id: "240",
            name: "Inception",
            description: "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O., but his tragic past may doom the project and his team to disaster.",
            genre: ["Action", "Sci-Fi", "Thriller"],
            duration: "148",
            releaseDate: "2010-07-16",
            imageUrl: "/9gk7adHYeDvHkCSEqAvQNLV5Uge.jpg",
            rating: []
        }
    ]);

    const theatreArr = ref([
        { name: "Theatre 1", showRooms: [{ name: "Hall 1" }] },
        { name: "Theatre 2", showRooms: [{ name: "Hall 2" }, { name: "Hall 3" }] }
    ]);

    // Mock fetch API
    beforeEach(async () => {
        global.fetch = vi.fn((url) => {
            if (url.includes('movies/name')) {
                return Promise.resolve({
                    json: () => Promise.resolve(movieArr.value)
                });
            } else if (url.includes('cinemas')) {
                return Promise.resolve({
                    json: () => Promise.resolve(theatreArr.value)
                });
            }
        });

        // Mock window.alert
          global.alert = vi.fn();

        // Mount the component with global mocks
        wrapper = mount(ScheduleMovie, {
            global: {
                mocks: {
                    fetch: global.fetch
                }
            }
        });

        // Trigger the fetch methods to populate data
        await wrapper.vm.searchMovies();
        await wrapper.vm.getCinemas();
        await wrapper.vm.$nextTick();
    });

    test('should load props and mount component', () => {
        expect(wrapper).toBeTruthy();
        expect(wrapper.vm.movies).toBeDefined();
        expect(wrapper.vm.cinemas).toBeDefined();
    });

    test('should fill out the form, submit and verify values', async () => {
        // Select theatre
        wrapper.vm.theatre = theatreArr.value[0];

        // Select movie hall
        wrapper.vm.movieHall = theatreArr.value[0].showRooms[0];

        // Select movie
        wrapper.vm.selectedMovie = movieArr.value[0];

        // Set date
        const dateInput = wrapper.find('#selected-date');
        await dateInput.setValue('2023-10-10 10:00:00');

        // Submit form by clicking the button
        await wrapper.find('button[type="button"]').trigger('click');

        // Verify form values after submission
        expect(wrapper.vm.theatre.name).toBe("Theatre 1");
        expect(wrapper.vm.movieHall.name).toBe("Hall 1");
        expect(wrapper.vm.selectedMovie.name).toBe("The Godfather");
        expect(wrapper.vm.formattedDate).toBe('2023-10-10 10:00:00');
    });
});