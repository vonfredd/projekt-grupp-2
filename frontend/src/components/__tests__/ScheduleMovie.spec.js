import {afterEach, beforeEach, describe, expect, test, vi} from "vitest";
import {mount} from '@vue/test-utils';
import {ref} from 'vue';
import ScheduleMovie from '../ScheduleMovie.vue';

// Function to reset form fields and component state
const resetFormFields = (wrapper) => {
    wrapper.vm.cinema = "";
    wrapper.vm.cinemaHall = "";
    wrapper.vm.selectedMovie = "";
    wrapper.vm.formattedDate = "";
    wrapper.vm.date = new Date();
};

// Function to mock POST schedule response
const mockPostSchedule = (url, scheduleRequest) => {
    if (url === 'http://localhost:9000/schedules/new' && scheduleRequest.method === 'POST') {
        const body = JSON.parse(scheduleRequest.body);

        if (body.movie && body.cinema && body.cinemaHall && body.localDateTime) {
            return Promise.resolve({
                ok: true,
                status: 200,
                statusText: 'OK',
                json: () => Promise.resolve({})
            });
        } else {
            return Promise.resolve({
                ok: false,
                status: 500,
                statusText: 'Internal Server Error',
                json: () => Promise.resolve({})
            });
        }
    }
};

describe('ScheduleMovie Component Default Setup', () => {
    let wrapper;
    const movieQuery = ref("");

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

    const cinemaArr = ref([
        {name: "Cinema 1", cinemaHalls: [{name: "Hall 1"}]},
        {name: "Cinema 2", cinemaHalls: [{name: "Hall 2"}, {name: "Hall 3"}]}
    ]);

    // Mock fetch API
    beforeEach(async () => {
        global.fetch = vi.fn((url, scheduleRequest) => {
            if (url === `http://localhost:9000/movies/name?name=${movieQuery.value}`) {
                return Promise.resolve({
                    json: () => Promise.resolve(movieArr.value)
                });
            } else if (url === 'http://localhost:9000/cinemas') {
                return Promise.resolve({
                    json: () => Promise.resolve(cinemaArr.value)
                });
            } else {
                return mockPostSchedule(url, scheduleRequest);
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

    afterEach(() => {
        resetFormFields(wrapper);
        vi.clearAllMocks();
    });


    test('should load props and mount component', () => {
        expect(wrapper).toBeTruthy();
        expect(wrapper.vm.movies).toBeDefined();
        expect(wrapper.vm.cinemas).toBeDefined();
    });

    test('should fill out the form, submit and verify values', async () => {
        // Select Cinema
        wrapper.vm.cinema = cinemaArr.value[0];

        // Select cinema hall
        wrapper.vm.cinemaHall = cinemaArr.value[0].cinemaHalls[0];

        // Select movie
        wrapper.vm.selectedMovie = movieArr.value[0];

        // Set date
        const dateInput = wrapper.find('#selected-date');
        await dateInput.setValue('2023-10-10 10:00:00');
        wrapper.vm.date = new Date('2023-10-10T10:00:00');
        await wrapper.vm.$nextTick();

        // Verify form values before submission
        expect(wrapper.vm.cinema.name).toBe("Cinema 1");
        expect(wrapper.vm.cinemaHall.name).toBe("Hall 1");
        expect(wrapper.vm.selectedMovie.name).toBe("The Godfather");
        expect(wrapper.vm.formattedDate).toBe('2023-10-10 10:00:00');

        // Submit form by clicking the button
        await wrapper.find('button[type="button"]').trigger('click');
        await wrapper.vm.$nextTick();
    });

    test('should disable schedule-movie button unless all forms are filled', async () => {

        const button = wrapper.find('#schedule-movie-button');
        // Initially, the button should be disabled
        expect(button.element.disabled).toBe(true);

        // Select cinema
        wrapper.vm.cinema = cinemaArr.value[0];
        await wrapper.vm.$nextTick();
        expect(button.element.disabled).toBe(true);

        // Select cinema hall
        wrapper.vm.cinemaHall = cinemaArr.value[0].cinemaHalls[0];
        await wrapper.vm.$nextTick();
        expect(button.element.disabled).toBe(true);

        // Select movie
        wrapper.vm.selectedMovie = movieArr.value[0];
        await wrapper.vm.$nextTick();
        expect(button.element.disabled).toBe(true);

        // Set date
        const dateInput = wrapper.find('#selected-date');
        await dateInput.setValue('2023-10-10 10:00:00');
        await wrapper.vm.$nextTick();

        // Now the button should be enabled
        expect(button.element.disabled).toBe(false);
    });

    test('should disable schedule-movie button after submission', async () => {
        // Select cinema
        wrapper.vm.cinema = cinemaArr.value[0];

        // Select cinema hall
        wrapper.vm.cinemaHall = cinemaArr.value[0].cinemaHalls[0];

        // Select movie
        wrapper.vm.selectedMovie = movieArr.value[0];

        // Set date
        const dateInput = wrapper.find('#selected-date');
        await dateInput.setValue('2023-10-10 10:00:00');
        wrapper.vm.date = new Date('2023-10-10T10:00:00');
        await wrapper.vm.$nextTick();

        // Submit form by clicking the button
        await wrapper.find('button[type="button"]').trigger('click');
        await wrapper.vm.$nextTick();

        // Verify button is disabled after submission
        const button = wrapper.find('#schedule-movie-button');
        expect(button.element.disabled).toBe(true);
    });

    test('should filter movies based on query', async () => {
        wrapper.vm.movieQuery = 'godfather';
        await wrapper.vm.$nextTick();
        expect(wrapper.vm.filteredMovies.length).toBe(1);
        expect(wrapper.vm.filteredMovies[0].name).toBe('The Godfather');
    });

    test('should fetch and populate cinemas', async () => {
        await wrapper.vm.getCinemas();
        expect(wrapper.vm.cinemas.length).toBe(2);
        expect(wrapper.vm.cinemas[0].name).toBe('Cinema 1');
    });

    test('should format date correctly', async () => {
        const date = new Date('2023-10-10T10:00:00');
        wrapper.vm.date = date;
        await wrapper.vm.$nextTick();
        expect(wrapper.vm.formattedDate).toBe('2023-10-10 10:00:00');
    });
});

describe('ScheduleMovie Component - test response', () => {
    let wrapper;

    beforeEach(async () => {
    global.fetch = vi.fn((url, scheduleRequest) => {
        return mockPostSchedule(url, scheduleRequest);
    });

    wrapper = mount(ScheduleMovie, {
        global: {
            mocks: {
                fetch: global.fetch
            }
        }
    });

    await wrapper.vm.$nextTick();
});

    afterEach(() => {
        resetFormFields(wrapper);
        vi.clearAllMocks();
    });

    test('should verify 200 OK response when posting a schedule', async () => {
        wrapper.vm.cinema = {name: "Cinema 1", cinemaHalls: [{name: "Hall 1"}]};
        wrapper.vm.cinemaHall = {name: "Hall 1"};
        wrapper.vm.selectedMovie = {id: "238", name: "The Godfather"};
        wrapper.vm.date = new Date('2023-10-10T10:00:00');
        await wrapper.vm.$nextTick();

        await wrapper.find('button[type="button"]').trigger('click');
        await wrapper.vm.$nextTick();

        const expectedDateTime = new Date('2023-10-10T10:00:00').toISOString().slice(0, 19);

        expect(global.fetch).toHaveBeenCalledWith('http://localhost:9000/schedules/new', expect.objectContaining({
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                localDateTime: expectedDateTime,
                cinema: {name: "Cinema 1", cinemaHalls: [{name: "Hall 1"}]},
                cinemaHall: {name: "Hall 1"},
                movie: {id: "238", name: "The Godfather"}
            })
        }));

        // Assert the response code
        const response = await global.fetch.mock.results[0].value;
        expect(response.status).toBe(200);
    });

    test('should handle 500 Internal Server Error response when some fields are missing', async () => {
        // Fill only cinema field
        wrapper.vm.cinema = {name: "Cinema 1", cinemaHalls: [{name: "Hall 1"}]};
        wrapper.vm.cinemaHall = null;
        wrapper.vm.selectedMovie = null;
        wrapper.vm.date = null;
        await wrapper.vm.$nextTick();

        // Override the button's disabled state
        wrapper.vm.isFormValid = true;
        await wrapper.vm.$nextTick();

        // Submit form by clicking the button
        await wrapper.find('button[type="button"]').trigger('click');
        await wrapper.vm.$nextTick();

        // Check if the error message is logged
        expect(global.fetch).toHaveBeenCalledWith('http://localhost:9000/schedules/new', expect.objectContaining({
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                localDateTime: "",
                cinema: {name: "Cinema 1", cinemaHalls: [{name: "Hall 1"}]},
                cinemaHall: null,
                movie: null
            })
        }));

        // Assert the response code
        const response = await global.fetch.mock.results[0].value;
        expect(response.status).toBe(500);
    });
});

