import { vi, describe, it, expect, beforeEach, afterEach } from 'vitest';
import Seats from '../Seats.vue';
import { mount } from '@vue/test-utils';

describe('Booking success', () => {
    let wrapper;
    const mockSchedule = {
        "id": "671f48c9cd348034c46ead5f",
        "localDateTime": "2024-10-27T08:18:00",
        "cinema": {
            "name": "Trappan",
            "cinemaHalls": [
                {
                    "name": "Salong 1",
                    "nrOfSeats": 60
                },
                {
                    "name": "Salong 2",
                    "nrOfSeats": 30
                },
                {
                    "name": "VIP",
                    "nrOfSeats": 10
                },
                {
                    "name": "Salooong",
                    "nrOfSeats": 30
                }
            ]
        },
        "cinemaHall": {
            "name": "Salooong",
            "nrOfSeats": 30
        },
        "movie": {
            "id": "346",
            "name": "Seven Samurai",
            "description": "A samurai answers a village's request for protection after he falls on hard times. The town needs protection from bandits, so the samurai gathers six others to help him teach the people how to defend themselves, and the villagers provide the soldiers with food.",
            "genres": [
                "Action",
                "Drama"
            ],
            "duration": "207",
            "releaseDate": "1954-04-26",
            "imageUrl": "/8OKmBV5BUFzmozIC3pPWKHy17kx.jpg",
            "backdropPath": "/sJNNMCc6B7KZIY3LH3JMYJJNH5j.jpg",
            "averageRatingInPercentage": 0.0,
            "rating": []
        }
    };

    beforeEach(async () => {
        global.fetch = vi.fn()
            .mockResolvedValueOnce(
                Promise.resolve({
                    ok: true,
                    status: 200,
                    json: () => Promise.resolve([1, 2, 3, 4])
                }))
            .mockResolvedValueOnce(Promise.resolve({
                ok: true,
                status: 200,
                json: () => Promise.resolve()
            }));
        wrapper = mount(Seats, {
            props: {
                schedule: mockSchedule
            }
        }
        );
        window.alert = vi.fn();
    });

    afterEach(() => {
    })

    it('Should fetch booked seats from schedule', async () => {
        expect(fetch).toBeCalledWith(`http://localhost:9000/schedules/${mockSchedule.id}/booked-seats`);
        expect(wrapper.vm.seats[0].booked).toBe(true);
        expect(wrapper.vm.seats[1].booked).toBe(true);
    });

    it('Should alert on success', async () => {
        wrapper.vm.seats[0].chosen = true;
        expect(wrapper.vm.seats[0].chosen).toBe(true);
        await wrapper.vm.createBooking();
        expect(window.alert).toHaveBeenCalledWith('Booking successful!')
    })
})


describe('Booking failure', () => {
    let wrapper;
    const mockSchedule = {
        "id": "671f48c9cd348034c46ead5f",
        "localDateTime": "2024-10-27T08:18:00",
        "cinema": {
            "name": "Trappan",
            "cinemaHalls": [
                {
                    "name": "Salong 1",
                    "nrOfSeats": 60
                },
                {
                    "name": "Salong 2",
                    "nrOfSeats": 30
                },
                {
                    "name": "VIP",
                    "nrOfSeats": 10
                },
                {
                    "name": "Salooong",
                    "nrOfSeats": 30
                }
            ]
        },
        "cinemaHall": {
            "name": "Salooong",
            "nrOfSeats": 30
        },
        "movie": {
            "id": "346",
            "name": "Seven Samurai",
            "description": "A samurai answers a village's request for protection after he falls on hard times. The town needs protection from bandits, so the samurai gathers six others to help him teach the people how to defend themselves, and the villagers provide the soldiers with food.",
            "genres": [
                "Action",
                "Drama"
            ],
            "duration": "207",
            "releaseDate": "1954-04-26",
            "imageUrl": "/8OKmBV5BUFzmozIC3pPWKHy17kx.jpg",
            "backdropPath": "/sJNNMCc6B7KZIY3LH3JMYJJNH5j.jpg",
            "averageRatingInPercentage": 0.0,
            "rating": []
        }
    };

    beforeEach(async () => {
        global.fetch = vi.fn()
        .mockResolvedValueOnce(
            Promise.resolve({
                ok: true,
                status: 200,
                json: () => Promise.resolve([1, 2, 3, 4])
            }))
            .mockResolvedValueOnce(Promise.resolve({
                ok: false, 
                status: 500,
                json: () => Promise.resolve(), 
            }));
        wrapper = mount(Seats, {
            props: {
                schedule: mockSchedule
            }
        }
        );
        window.alert = vi.fn();
    });

    afterEach(() => {
    })

    it('Should fetch booked seats from schedule', async () => {
        expect(fetch).toBeCalledWith(`http://localhost:9000/schedules/${mockSchedule.id}/booked-seats`);
        expect(wrapper.vm.seats[0].booked).toBe(true);
        expect(wrapper.vm.seats[1].booked).toBe(true);
    });

    it('Should alert on failure', async () => {
        wrapper.vm.seats[0].chosen = true;
        expect(wrapper.vm.seats[0].chosen).toBe(true);
        await wrapper.vm.createBooking();
        expect(window.alert).toHaveBeenCalledWith('Booking failed. Please try again.')
    })
})