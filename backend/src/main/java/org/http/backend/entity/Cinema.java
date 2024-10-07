package org.http.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.http.backend.util.ShowRoom;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cinema {

    private String name;
    private List<ShowRoom> showRooms;
    private List<Movie> movies;

}
