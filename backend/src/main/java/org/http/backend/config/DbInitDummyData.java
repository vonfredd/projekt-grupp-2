package org.http.backend.config;

import org.http.backend.entity.Movie;
import org.http.backend.repository.MovieRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class DbInitDummyData {

    private static final String DRAMA = "Drama";
    private static final String CRIME = "Crime";
    private static final String HISTORY = "History";
    private static final String WAR = "War";
    private static final String ANIMATION = "Animation";
    private static final String FAMILY = "Family";
    private static final String FANTASY = "Fantasy";
    private static final String COMEDY = "Comedy";
    private static final String ROMANCE = "Romance";
    private static final String ACTION = "Action";
    private static final String THRILLER = "Thriller";
    private static final String WESTERN = "Western";
    private static final String ADVENTURE = "Adventure";

    @Bean
    ApplicationRunner dbInit(MovieRepository movieRepository) {
        return dummyMovies -> {
            movieRepository.deleteAll();
            Movie[] movies = new Movie[]{
                    new Movie("238", "The Godfather", "Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family...",
                            Arrays.asList(DRAMA, CRIME), "175", "1972-03-14", "/3bhkrj58Vtu7enYsRolD1fZdja1.jpg", Collections.emptyList()),

                    new Movie("278", "The Shawshank Redemption", "Imprisoned in the 1940s for the double murder of his wife and her lover...",
                            Arrays.asList(DRAMA, CRIME), "142", "1994-09-23", "/9cqNxx0GxF0bflZmeSMuL5tnGzr.jpg", Collections.emptyList()),

                    new Movie("240", "The Godfather Part II", "In the continuing saga of the Corleone crime family...",
                            Arrays.asList(DRAMA, CRIME), "202", "1974-12-20", "/hek3koDUyRQk7FIhPXsa6mT2Zc3.jpg", Collections.emptyList()),

                    new Movie("424", "Schindler's List", "The true story of how businessman Oskar Schindler saved over a thousand Jewish lives...",
                            Arrays.asList(DRAMA, HISTORY, WAR), "195", "1993-12-15", "/sF1U4EUQS8YHUYjNl3pMGNIQyr0.jpg", Collections.emptyList()),

                    new Movie("389", "12 Angry Men", "The defense and the prosecution have rested...",
                            Collections.singletonList(DRAMA), "97", "1957-04-10", "/ow3wq89wM8qd5X7hWKxiRfsFf9C.jpg", Collections.emptyList()),

                    new Movie("129", "Spirited Away", "A young girl, Chihiro, becomes trapped in a strange new world of spirits...",
                            Arrays.asList(ANIMATION, FAMILY, FANTASY), "125", "2001-07-20", "/39wmItIWsg5sZMyRUHLkWBcuVCM.jpg", Collections.emptyList()),

                    new Movie("19404", "Dilwale Dulhania Le Jayenge", "Raj is a rich, carefree, happy-go-lucky second generation NRI...",
                            Arrays.asList(COMEDY, DRAMA, ROMANCE), "190", "1995-10-20", "/lfRkUr7DYdHldAqi3PwdQGBRBPM.jpg", Collections.emptyList()),

                    new Movie("155", "The Dark Knight", "Batman raises the stakes in his war on crime...",
                            Arrays.asList(DRAMA, ACTION, CRIME, THRILLER), "152", "2008-07-16", "/qJ2tW6WMUDux911r6m7haRef0WH.jpg", Collections.emptyList()),

                    new Movie("496243", "Parasite", "All unemployed, Ki-taek's family takes peculiar interest in the wealthy...",
                            Arrays.asList(COMEDY, THRILLER, DRAMA), "133", "2019-05-30", "/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg", Collections.emptyList()),

                    new Movie("497", "The Green Mile", "A supernatural tale set on death row in a Southern prison...",
                            Arrays.asList(FANTASY, DRAMA, CRIME), "189", "1999-12-10", "/8VG8fDNiy50H4FedGwdSVUPoaJe.jpg", Collections.emptyList()),

                    new Movie("680", "Pulp Fiction", "A burger-loving hit man, his philosophical partner...",
                            Arrays.asList(THRILLER, CRIME), "154", "1994-09-10", "/d5iIlFn5s0ImszYzBPb8JPIfbXD.jpg", Collections.emptyList()),

                    new Movie("372058", "Your Name.", "High schoolers Mitsuha and Taki are complete strangers...",
                            Arrays.asList(ANIMATION, ROMANCE, DRAMA), "106", "2016-08-26", "/vfJFJPepRKapMd5G2ro7klIRysq.jpg", Collections.emptyList()),

                    new Movie("122", "The Lord of the Rings: The Return of the King", "As armies mass for a final battle...",
                            Arrays.asList(ADVENTURE, FANTASY, ACTION), "201", "2003-12-17", "/rCzpDGLbOoPwLjy3OAm5NUPOTrC.jpg", Collections.emptyList()),

                    new Movie("13", "Forrest Gump", "A man with a low IQ has accomplished great things in his life...",
                            Arrays.asList(COMEDY, DRAMA, ROMANCE), "142", "1994-06-23", "/arw2vcBveWOVZr6pxd9XTd1TdQa.jpg", Collections.emptyList()),

                    new Movie("429", "The Good, the Bad and the Ugly", "While the Civil War rages on...",
                            Collections.singletonList(WESTERN), "161", "1966-12-22", "/bX2xnavhMYjWDoZp1VM6VnU1xwe.jpg", Collections.emptyList()),

                    new Movie("769", "GoodFellas", "The true story of Henry Hill, a half-Irish...",
                            Arrays.asList(DRAMA, CRIME), "145", "1990-09-12", "/aKuFiU82s5ISJpGZp7YkIr3kCUd.jpg", Collections.emptyList()),

                    new Movie("346", "Seven Samurai", "A samurai answers a village's request for protection...",
                            Arrays.asList(ACTION, DRAMA), "207", "1954-04-26", "/8OKmBV5BUFzmozIC3pPWKHy17kx.jpg", Collections.emptyList()),

                    new Movie("12477", "Grave of the Fireflies", "In the final months of World War II...",
                            Arrays.asList(ANIMATION, DRAMA, WAR), "89", "1988-04-16", "/k9tv1rXZbOhH7eiCk378x61kNQ1.jpg", Collections.emptyList()),

                    new Movie("11216", "Cinema Paradiso", "A filmmaker recalls his childhood...",
                            Arrays.asList(DRAMA, ROMANCE), "124", "1988-11-17", "/gCI2AeMV4IHSewhJkzsur5MEp6R.jpg", Collections.emptyList()),

                    new Movie("637", "Life Is Beautiful", "A touching story of an Italian book seller...",
                            Arrays.asList(COMEDY, DRAMA), "116", "1997-12-20", "/74hLDKjD5aGYOotO6esUVaeISa2.jpg", Collections.emptyList())
            };
            movieRepository.saveAll(Arrays.asList(movies));
        };
    }
}
