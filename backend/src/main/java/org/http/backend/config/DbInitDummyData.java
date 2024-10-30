package org.http.backend.config;

import org.http.backend.entity.Booking;
import org.http.backend.entity.Cinema;
import org.http.backend.entity.Movie;
import org.http.backend.entity.Schedule;
import org.http.backend.repository.BookingRepository;
import org.http.backend.repository.CinemaRepository;
import org.http.backend.repository.MovieRepository;
import org.http.backend.repository.ScheduleRepository;
import org.http.backend.util.CinemaHall;
import org.http.backend.util.Rating;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
public class DbInitDummyData {

    @Bean
    ApplicationRunner dbInit(MovieRepository movieRepository, CinemaRepository cinemaRepository, ScheduleRepository scheduleRepository, BookingRepository bookingRepository) {
        return args -> {
            List<Rating> ratingsList1 = Arrays.asList(
                    new Rating("120001", 5),
                    new Rating("101002", 4),
                    new Rating("100053", 2),
                    new Rating("100404", 1),
                    new Rating("102003", 2),
                    new Rating("105004", 1),
                    new Rating("105603", 2),
                    new Rating("180004", 1),
                    new Rating("100303", 2),
                    new Rating("104055", 3)
            );

            List<Rating> ratingsList2 = Arrays.asList(
                    new Rating("100006", 1),
                    new Rating("100111", 1),
                    new Rating("100088", 2),
                    new Rating("100022", 2),
                    new Rating("100008", 5),
                    new Rating("100009", 2),
                    new Rating("100010", 3)
            );

            List<Rating> ratingsList3 = Arrays.asList(
                    new Rating("100011", 5),
                    new Rating("100012", 2),
                    new Rating("100013", 5),
                    new Rating("100623", 4),
                    new Rating("100456", 1),
                    new Rating("100014", 4),
                    new Rating("100015", 5)
            );

            List<Rating> ratingsList4 = Arrays.asList(
                    new Rating("100016", 3),
                    new Rating("100017", 2),
                    new Rating("100018", 2),
                    new Rating("100019", 1),
                    new Rating("100033", 2),
                    new Rating("100665", 1),
                    new Rating("100020", 1)
            );

            List<Rating> ratingsList5 = Arrays.asList(
                    new Rating("100021", 4),
                    new Rating("100022", 2),
                    new Rating("100023", 3),
                    new Rating("100024", 5),
                    new Rating("100025", 4)
            );

            List<Rating> ratingsList6 = Arrays.asList(
                    new Rating("100021", 4),
                    new Rating("100022", 2),
                    new Rating("100023", 3),
                    new Rating("100018", 2),
                    new Rating("100019", 1),
                    new Rating("100018", 2),
                    new Rating("100024", 5),
                    new Rating("100019", 1),
                    new Rating("100018", 2),
                    new Rating("100024", 5),
                    new Rating("100025", 4)
            );

            List<Rating> ratingsList7 = Arrays.asList(
                    new Rating("100021", 4),
                    new Rating("100023", 3),
                    new Rating("100018", 2),
                    new Rating("100019", 1),
                    new Rating("100011", 2),
                    new Rating("100022", 2),
                    new Rating("101243", 3),
                    new Rating("100024", 5),
                    new Rating("100025", 4)
            );
            cinemaRepository.deleteAll();
            Cinema[] cinemas = new Cinema[] {
                    new Cinema("Trappan Bio", Arrays.asList(
                            new CinemaHall("VIP", 30),
                            new CinemaHall("Hall 1", 50)
                    )),
                    new Cinema("Biopalatset", Arrays.asList(
                            new CinemaHall("VIP", 40)
                    )),
                    new Cinema("Bergakungen", Arrays.asList(
                            new CinemaHall("Hall 1", 60),
                            new CinemaHall("Hall 2", 45),
                            new CinemaHall("VIP", 70)
                    )),
                    new Cinema("Capitol", Arrays.asList(
                            new CinemaHall("Hall 1", 80),
                            new CinemaHall("Hall 2", 20),
                            new CinemaHall("VIP", 35),
                            new CinemaHall("Hall 3", 55)
                    ))
            };
            cinemaRepository.saveAll(Arrays.asList(cinemas));
            movieRepository.deleteAll();
            Movie[] movies = new Movie[]{
                    new Movie("238", "The Godfather", "Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family. When organized crime family patriarch, Vito Corleone barely survives an attempt on his life, his youngest son, Michael steps in to take care of the would-be killers, launching a campaign of bloody revenge.",
                            Arrays.asList("Drama", "Crime"), "175", "1972-03-14", "/3bhkrj58Vtu7enYsRolD1fZdja1.jpg", ratingsList6, "/ejdD20cdHNFAYAN2DlqPToXKyzx.jpg"),

                    new Movie("278", "The Shawshank Redemption", "Imprisoned in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.",
                            Arrays.asList("Drama", "Crime"), "142", "1994-09-23", "/9cqNxx0GxF0bflZmeSMuL5tnGzr.jpg", ratingsList7,"/zfbjgQE1uSd9wiPTX4VzsLi0rGG.jpg" ),

                    new Movie("240", "The Godfather Part II", "In the continuing saga of the Corleone crime family, a young Vito Corleone grows up in Sicily and in 1910s New York. In the 1950s, Michael Corleone attempts to expand the family business into Las Vegas, Hollywood and Cuba.",
                            Arrays.asList("Drama", "Crime"), "202", "1974-12-20", "/hek3koDUyRQk7FIhPXsa6mT2Zc3.jpg", ratingsList1,"/kGzFbGhp99zva6oZODW5atUtnqi.jpg"),

                    new Movie("424", "Schindler's List", "The true story of how businessman Oskar Schindler saved over a thousand Jewish lives from the Nazis while they worked as slaves in his factory during World War II.",
                            Arrays.asList("Drama", "History", "War"), "195", "1993-12-15", "/sF1U4EUQS8YHUYjNl3pMGNIQyr0.jpg", ratingsList5, "/zb6fM1CX41D9rF9hdgclu0peUmy.jpg"),

                    new Movie("389", "12 Angry Men", "The defense and the prosecution have rested and the jury is filing into the jury room to decide if a young Spanish-American is guilty or innocent of murdering his father. What begins as an open and shut case soon becomes a mini-drama of each of the jurors' prejudices and preconceptions about the trial, the accused, and each other.",
                            Collections.singletonList("Drama"), "97", "1957-04-10", "/ow3wq89wM8qd5X7hWKxiRfsFf9C.jpg", ratingsList4,"/qqHQsStV6exghCM7zbObuYBiYxw.jpg"),

                    new Movie("129", "Spirited Away",
                            "A young girl, Chihiro, becomes trapped in a strange new world of spirits. When her parents undergo a mysterious transformation, she must call upon the courage she never knew she had to free her family.",
                            Arrays.asList("Animation", "Family", "Fantasy"), "125", "2001-07-20", "/39wmItIWsg5sZMyRUHLkWBcuVCM.jpg", ratingsList2, "/mSDsSDwaP3E7dEfUPWy4J0djt4O.jpg"),

                    new Movie("19404", "Dilwale Dulhania Le Jayenge",
                            "Raj is a rich, carefree, happy-go-lucky second generation NRI. Simran is the daughter of Chaudhary Baldev Singh, who in spite of being an NRI is very strict about adherence to Indian values. Simran has left for India to be married to her childhood fiancé. Raj leaves for India with a mission at his hands, to claim his lady love under the noses of her whole family. Thus begins a saga.",
                            Arrays.asList("Comedy", "Drama", "Romance"), "190", "1995-10-20", "/lfRkUr7DYdHldAqi3PwdQGBRBPM.jpg", ratingsList6 ,"/90ez6ArvpO8bvpyIngBuwXOqJm5.jpg"),

                    new Movie("155", "The Dark Knight",
                            "Batman raises the stakes in his war on crime. With the help of Lt. Jim Gordon and District Attorney Harvey Dent, Batman sets out to dismantle the remaining criminal organizations that plague the streets. The partnership proves to be effective, but they soon find themselves prey to a reign of chaos unleashed by a rising criminal mastermind known to the terrified citizens of Gotham as the Joker.",
                            Arrays.asList("Drama", "Action", "Crime", "Thriller"), "152", "2008-07-16", "/qJ2tW6WMUDux911r6m7haRef0WH.jpg", ratingsList2, "/nMKdUUepR0i5zn0y1T4CsSB5chy.jpg"),

                    new Movie("496243", "Parasite",
                            "All unemployed, Ki-taek's family takes peculiar interest in the wealthy and glamorous Parks for their livelihood until they get entangled in an unexpected incident.",
                            Arrays.asList("Comedy", "Thriller", "Drama"), "133", "2019-05-30", "/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg", ratingsList4, "/8eihUxjQsJ7WvGySkVMC0EwbPAD.jpg"),

                    new Movie("497", "The Green Mile",
                            "A supernatural tale set on death row in a Southern prison, where gentle giant John Coffey possesses the mysterious power to heal people's ailments. When the cell block's head guard, Paul Edgecomb, recognizes Coffey's miraculous gift, he tries desperately to help stave off the condemned man's execution.",
                            Arrays.asList("Fantasy", "Drama", "Crime"), "189", "1999-12-10", "/8VG8fDNiy50H4FedGwdSVUPoaJe.jpg", ratingsList5, "/vxJ08SvwomfKbpboCWynC3uqUg4.jpg"),

                    new Movie("680", "Pulp Fiction",
                            "A burger-loving hit man, his philosophical partner, a drug-addled gangster's moll and a washed-up boxer converge in this sprawling, comedic crime caper. Their adventures unfurl in three stories that ingeniously trip back and forth in time.",
                            Arrays.asList("Thriller", "Crime"), "154", "1994-09-10", "/d5iIlFn5s0ImszYzBPb8JPIfbXD.jpg", ratingsList2, "/suaEOtk1N1sgg2MTM7oZd2cfVp3.jpg"),

                    new Movie("372058", "Your Name.",
                            "High schoolers Mitsuha and Taki are complete strangers living separate lives. But one night, they suddenly switch places. Mitsuha wakes up in Taki’s body, and he in hers. This bizarre occurrence continues to happen randomly, and the two must adjust their lives around each other.",
                            Arrays.asList("Animation", "Romance", "Drama"), "106", "2016-08-26", "/vfJFJPepRKapMd5G2ro7klIRysq.jpg", ratingsList1, "/mMtUybQ6hL24FXo0F3Z4j2KG7kZ.jpg"),

                    new Movie("122", "The Lord of the Rings: The Return of the King",
                            "As armies mass for a final battle that will decide the fate of the world--and powerful, ancient forces of Light and Dark compete to determine the outcome--one member of the Fellowship of the Ring is revealed as the noble heir to the throne of the Kings of Men. Yet, the sole hope for triumph over evil lies with a brave hobbit, Frodo, who, accompanied by his loyal friend Sam and the hideous, wretched Gollum, ventures deep into the very dark heart of Mordor on his seemingly impossible quest to destroy the Ring of Power.​",
                            Arrays.asList("Adventure", "Fantasy", "Action"), "201", "2003-12-17", "/rCzpDGLbOoPwLjy3OAm5NUPOTrC.jpg", ratingsList5, "/2u7zbn8EudG6kLlBzUYqP8RyFU4.jpg"),

                    new Movie("13", "Forrest Gump",
                            "A man with a low IQ has accomplished great things in his life and been present during significant historic events—in each case, far exceeding what anyone imagined he could do. But despite all he has achieved, his one true love eludes him.",
                            Arrays.asList("Comedy", "Drama", "Romance"), "142", "1994-06-23", "/arw2vcBveWOVZr6pxd9XTd1TdQa.jpg", ratingsList1, "/ghgfzbEV7kbpbi1O8eIILKVXEA8.jpg"),

                    new Movie("429", "The Good, the Bad and the Ugly",
                            "While the Civil War rages on between the Union and the Confederacy, three men – a quiet loner, a ruthless hitman, and a Mexican bandit – comb the American Southwest in search of a strongbox containing $200,000 in stolen gold.",
                            Collections.singletonList("Western"), "161", "1966-12-22", "/bX2xnavhMYjWDoZp1VM6VnU1xwe.jpg", ratingsList4, "/Adrip2Jqzw56KeuV2nAxucKMNXA.jpg"),

                    new Movie("769", "GoodFellas",
                            "The true story of Henry Hill, a half-Irish, half-Sicilian Brooklyn kid who is adopted by neighbourhood gangsters at an early age and climbs the ranks of a Mafia family under the guidance of Jimmy Conway.",
                            Arrays.asList("Drama", "Crime"), "145", "1990-09-12", "/aKuFiU82s5ISJpGZp7YkIr3kCUd.jpg", ratingsList4, "/7TF4p86ZafnxFuNqWdhpHXFO244.jpg"),

                    new Movie("346", "Seven Samurai",
                            "A samurai answers a village's request for protection after he falls on hard times. The town needs protection from bandits, so the samurai gathers six others to help him teach the people how to defend themselves, and the villagers provide the soldiers with food.",
                            Arrays.asList("Action", "Drama"), "207", "1954-04-26", "/8OKmBV5BUFzmozIC3pPWKHy17kx.jpg", ratingsList2, "/sJNNMCc6B7KZIY3LH3JMYJJNH5j.jpg"),

                    new Movie("12477", "Grave of the Fireflies",
                            "In the final months of World War II, 14-year-old Seita and his sister Setsuko are orphaned when their mother is killed during an air raid in Kobe, Japan. After a falling out with their aunt, they move into an abandoned bomb shelter. With no surviving relatives and their emergency rations depleted, Seita and Setsuko struggle to survive.",
                            Arrays.asList("Animation", "Drama", "War"), "89", "1988-04-16", "/k9tv1rXZbOhH7eiCk378x61kNQ1.jpg", ratingsList1, "/gwj4R8Uy1GwejKqfofREKI9Jh7L.jpg"),

                    new Movie("11216", "Cinema Paradiso",
                            "A filmmaker recalls his childhood, when he fell in love with the movies at his village's theater and formed a deep friendship with the theater's projectionist.",
                            Arrays.asList("Drama", "Romance"), "124", "1988-11-17", "/gCI2AeMV4IHSewhJkzsur5MEp6R.jpg", ratingsList1,"/7lyq8hK0MhPHpUXdnqbFvZYSfkk.jpg"),

                    new Movie("637", "Life Is Beautiful",
                            "A touching story of an Italian book seller of Jewish ancestry who lives in his own little fairy tale. His creative and happy life would come to an abrupt halt when his entire family is deported to a concentration camp during World War II. While locked up he tries to convince his son that the whole thing is just a game.",
                            Arrays.asList("Comedy", "Drama"), "116", "1997-12-20", "/74hLDKjD5aGYOotO6esUVaeISa2.jpg", Collections.emptyList(), "/gavyCu1UaTaTNPsVaGXT6pe5u24.jpg"),
            };
            movieRepository.saveAll(Arrays.asList(movies));

            scheduleRepository.deleteAll();
            Schedule[] schedules = new Schedule[]{
                    new Schedule(LocalDateTime.of(2024, 10, 30, 18, 0), cinemas[0], cinemas[0].getCinemaHalls().get(0), movies[0]),
                    new Schedule(LocalDateTime.of(2024, 10, 30, 20, 0), cinemas[1], cinemas[1].getCinemaHalls().get(0), movies[1]),
                    new Schedule(LocalDateTime.of(2024, 10, 30, 19, 30), cinemas[2], cinemas[2].getCinemaHalls().get(1), movies[2]),
                    new Schedule(LocalDateTime.of(2024, 10, 30, 21, 0), cinemas[3], cinemas[3].getCinemaHalls().get(2), movies[3]),
                    new Schedule(LocalDateTime.of(2024, 10, 31, 17, 15), cinemas[0], cinemas[0].getCinemaHalls().get(1), movies[4]),
                    new Schedule(LocalDateTime.of(2024, 10, 31, 19, 45), cinemas[1], cinemas[1].getCinemaHalls().get(0), movies[5]),
                    new Schedule(LocalDateTime.of(2024, 10, 31, 20, 30), cinemas[2], cinemas[2].getCinemaHalls().get(0), movies[6]),
                    new Schedule(LocalDateTime.of(2024, 10, 31, 22, 0), cinemas[3], cinemas[3].getCinemaHalls().get(3), movies[7]),
                    new Schedule(LocalDateTime.of(2024, 11, 1, 18, 0), cinemas[0], cinemas[0].getCinemaHalls().get(0), movies[8]),
                    new Schedule(LocalDateTime.of(2024, 11, 1, 20, 0), cinemas[1], cinemas[1].getCinemaHalls().get(0), movies[9]),
                    new Schedule(LocalDateTime.of(2024, 11, 1, 19, 30), cinemas[2], cinemas[2].getCinemaHalls().get(1), movies[10]),
                    new Schedule(LocalDateTime.of(2024, 11, 1, 21, 0), cinemas[3], cinemas[3].getCinemaHalls().get(2), movies[2]),
                    new Schedule(LocalDateTime.of(2024, 11, 2, 17, 15), cinemas[0], cinemas[0].getCinemaHalls().get(1), movies[16]),
                    new Schedule(LocalDateTime.of(2024, 11, 2, 19, 45), cinemas[1], cinemas[1].getCinemaHalls().get(0), movies[4]),
                    new Schedule(LocalDateTime.of(2024, 10, 12, 21, 0), cinemas[3], cinemas[3].getCinemaHalls().get(3), movies[9]),
                    new Schedule(LocalDateTime.of(2024, 11, 6, 14, 0), cinemas[2], cinemas[0].getCinemaHalls().get(0), movies[4]),
                    new Schedule(LocalDateTime.of(2024, 11, 24, 22, 0), cinemas[1], cinemas[1].getCinemaHalls().get(0), movies[9]),
                    new Schedule(LocalDateTime.of(2024, 11, 12, 15, 30), cinemas[2], cinemas[2].getCinemaHalls().get(1), movies[12]),
                    new Schedule(LocalDateTime.of(2024, 10, 19, 20, 0), cinemas[3], cinemas[3].getCinemaHalls().get(2), movies[3]),
                    new Schedule(LocalDateTime.of(2024, 10, 16, 17, 20), cinemas[0], cinemas[0].getCinemaHalls().get(1), movies[4]),
                    new Schedule(LocalDateTime.of(2024, 10, 14, 22, 30), cinemas[1], cinemas[1].getCinemaHalls().get(0), movies[4]),
                    new Schedule(LocalDateTime.of(2024, 11, 2, 20, 30), cinemas[2], cinemas[2].getCinemaHalls().get(0), movies[3]),
                    new Schedule(LocalDateTime.of(2024, 11, 2, 22, 0), cinemas[3], cinemas[3].getCinemaHalls().get(3), movies[1]),
                    new Schedule(LocalDateTime.of(2024, 11, 3, 18, 0), cinemas[0], cinemas[0].getCinemaHalls().get(0), movies[17]),
                    new Schedule(LocalDateTime.of(2024, 11, 3, 20, 0), cinemas[1], cinemas[1].getCinemaHalls().get(0), movies[5]),
                    new Schedule(LocalDateTime.of(2024, 11, 3, 19, 30), cinemas[2], cinemas[2].getCinemaHalls().get(1), movies[2]),
                    new Schedule(LocalDateTime.of(2024, 11, 3, 21, 0), cinemas[3], cinemas[3].getCinemaHalls().get(2), movies[7])
            };
            scheduleRepository.saveAll(Arrays.asList(schedules));

            List<Schedule> schedulesToBook = scheduleRepository.findAll();
           bookingRepository.deleteAll();
            Booking[] bookings = new Booking[]{
                    new Booking(null, "user1", schedulesToBook.get(0), List.of(1, 2, 3)),
                    new Booking(null, "user2", schedulesToBook.get(1), List.of(4, 5)),
                    new Booking(null, "user3", schedulesToBook.get(2), List.of(6)),
                    new Booking(null, "user4", schedulesToBook.get(3), List.of(7, 8, 9)),
                    new Booking(null, "user5", schedulesToBook.get(4), List.of(2, 3)),
                    new Booking(null, "user6", schedulesToBook.get(5), List.of(4, 5, 6)),
                    new Booking(null, "user7", schedulesToBook.get(6), List.of(7)),
                    new Booking(null, "user8", schedulesToBook.get(7), List.of(1, 3)),
                    new Booking(null, "user9", schedulesToBook.get(8), List.of(2, 4, 6)),
                    new Booking(null, "user10", schedulesToBook.get(9), IntStream.rangeClosed(1, 40).boxed().collect(Collectors.toList())),
                    new Booking(null, "user11", schedulesToBook.get(10), List.of(1)),
                    new Booking(null, "user12", schedulesToBook.get(11), List.of(2, 5, 7)),
                    new Booking(null, "user13", schedulesToBook.get(12), List.of(3, 6)),
                    new Booking(null, "user14", schedulesToBook.get(13), List.of(8, 10)),
                    new Booking(null, "user15", schedulesToBook.get(14), List.of(1, 4, 9)),
                    new Booking(null, "user16", schedulesToBook.get(15), List.of(2, 3, 10)),
                    new Booking(null, "user17", schedulesToBook.get(16), List.of(5)),
                    new Booking(null, "user18", schedulesToBook.get(17), List.of(1, 7)),
                    new Booking(null, "user19", schedulesToBook.get(18), List.of(6, 8)),
                    new Booking(null, "user20", schedulesToBook.get(19), List.of(2, 9)),
                    new Booking(null, "user1", schedulesToBook.get(10), List.of(4, 7, 8)),
                    new Booking(null, "user2", schedulesToBook.get(3), List.of(1, 5)),
                    new Booking(null, "user3", schedulesToBook.get(7), List.of(6, 9)),
                    new Booking(null, "user4", schedulesToBook.get(14), List.of(2, 3)),
                    new Booking(null, "user5", schedulesToBook.get(0), List.of(1, 10)),
                    new Booking(null, "user6", schedulesToBook.get(11), List.of(5, 7)),
                    new Booking(null, "user7", schedulesToBook.get(15), List.of(3)),
                    new Booking(null, "user9", schedulesToBook.get(13), List.of(1, 5, 8)),
                    new Booking(null, "user10", schedulesToBook.get(2), List.of(3, 7))
            };
            bookingRepository.saveAll(Arrays.asList(bookings));
        };
    }
}
